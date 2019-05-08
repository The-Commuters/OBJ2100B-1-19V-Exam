/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static progeksamen.Admapp.tournament;
import static progeksamen.Admapp.tournamentList;

/**
 *
 * @author voje
 */
public class Data {
    
    private static ArrayList<Tournament> tournaments;
    
    /**
     * This method is the one that saves the Arraylist's into the file
     * to store them. 
     * @param tournaments
     */
    public static void saveTournaments(ArrayList<Tournament> tournaments) {
        
        try {
            
            // First the objects is written to the file that holds the binary data...
            FileOutputStream fileOut = new FileOutputStream(tournament + ".dat");
            
            // Places the object-array's in the binary-file.
            try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(tournaments);
                System.out.println("The Objects was succesfully written to a file in binary");
            }
            
            // ...Then it creates the backup in clear text.
            BufferedWriter outStream = new BufferedWriter(new FileWriter(tournament + ".txt"));
            
            PrintWriter cleaner = new PrintWriter(tournament + ".txt");
            cleaner.print("");
            cleaner.close();
            
            for (Tournament tournament : tournaments) {
                outStream.newLine();
                outStream.write("-------------------------------------- " + tournament.getName() + " ---------------------------------------------");
                
                for (Player player : tournament.getPlayers()) {
                    outStream.newLine();
                    outStream.write(player.toString());
                }
                
                for (Game game : tournament.getGames()) {
                    outStream.newLine();
                    outStream.write(game.toString());
                }
                outStream.newLine();
            }
            
            outStream.close();
            System.out.println("The Objects was succesfully written to a file in text");
            
        } catch (IOException ex) {}
    }

    /**
     * The getTorunament is the method that collects the data from the 
     * binary file where it was stored, and places them into the list
     * that they should be in before the application really starts. 
     * @return 
     */
    public static ObservableList<Tournament> getTournaments() {

        try {
            // create an ObjectInputStream for the file we created before
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Tournament.dat"));
            System.out.println(1);
            
            // Collects the player's from the storage-file.
            try{
                tournaments = (ArrayList<Tournament>) input.readObject();
                System.out.println("The list of tournaments have been collected fron the binary-file");
                
                ObservableList<Tournament> tournamentsOL = FXCollections.<Tournament>observableArrayList(tournaments);

                return tournamentsOL;
            } catch (ClassCastException | ClassNotFoundException e){
                System.err.println(e);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
        
    } 
    
    
    
        public static ArrayList<Tournament> tempgetTournaments() {

          //----------------------------------Test of tournment---------------------------------
        // Test-input used when writing into the file.
        Player player   = new Player("Harry");
        Player player1  = new Player("Ron");
        Player player2  = new Player("Hermoine"); 
        
        ArrayList<String> gameArraylist = new ArrayList<>();
        gameArraylist.add("Number1");
        gameArraylist.add("Number2");
        gameArraylist.add("Number3");
        
        Date date = new Date();
        
        Result result1  = new Result(player1, player2, true);
        Result result2  = new Result(player2, player1, true);
        Result result3  = new Result(player, player1, false);
        
        Game game1      = new Game(player1, player2, result1, gameArraylist, date, date);
        Game game2      = new Game(player2, player1, result1, gameArraylist, date, date);
        Game game3      = new Game(player, player1, result1, gameArraylist, date, date);
        
        // The list's used to store data.
        ArrayList<Player> playerList    = new ArrayList<Player>();
        ArrayList<Game> gameList        = new ArrayList<Game>();
       
        playerList.add(player);  playerList.add(player1); playerList.add(player2);
        gameList.add(game1);     gameList.add(game2);     gameList.add(game3);
        
        Tournament tournament1 = new Tournament("Team Nado: Vers 2", playerList, gameList);
        Tournament tournament2 = new Tournament("Team Nado: Vers 3", playerList, gameList);
        Tournament tournament3 = new Tournament("Team Nado: Vers 4", playerList, gameList);
        
        tournamentList.add(tournament1); tournamentList.add(tournament2); tournamentList.add(tournament3);
        
        // Test's the method that saves the tournment-data.
        // SaveTournament(playerList, gameList, resultList);
       Data.saveTournaments(tournamentList);
        //----------------------------------/Test of tournment---------------------------------
        return tournamentList;
        
    } 
}
