/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author voje
 */
public class Data {
    
    // The list of tounaments is stored in when gotten from the database or saved.
    public static ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
    public static String tournament = "tournament";
    
    /**
     * This method is the one that saves the Arraylist's into the file
     * to store them. 
     * @param tournaments
     */
    public static void saveTournaments() {
        
        try {
            
            // First the objects is written to the file that holds the binary data...
            FileOutputStream fileOut = new FileOutputStream("Tournament.dat");
            
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
                    outStream.write("Player-id: " + player.getId() + "; Playername: "+player.getName()+"; Playerscore: "+player.getScore());
                }
                
                for (Game game : tournament.getGames()) {
                    outStream.newLine();
                    outStream.write("Gamestate: " + game.getGameState() + "; Player 1: " + game.getPlayer1()+ "; Player 2: " + game.getPlayer2()+ "; Score:");
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
    public static ObservableList<Tournament> getTournaments()  {
        
        //tempgetTournaments();
        
        File file = new File(tournament + ".dat");
        
        if(!file.isFile() && !file.canRead()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            // create an ObjectInputStream for the file we created before
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(tournament + ".dat"));

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
        
       
        //ObservableList<Tournament> tournamentsOL = FXCollections.<Tournament>observableArrayList(tournaments);
        return null;
    }
        
    
    // Creates temp information to use undr testing.
    public static void tempgetTournaments() {

          //----------------------------------Test of tournment---------------------------------
        // Test-input used when writing into the file.
        Player player   = new Player("Harry");
        Player player1  = new Player("Ron");
        Player player2  = new Player("Hermoine"); 
        
        ArrayList<String> gameArraylist = new ArrayList<>();

        
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
        
        tournaments.add(tournament1); tournaments.add(tournament2); tournaments.add(tournament3);
        
        // Test's the method that saves the tournment-data.
        // SaveTournament(playerList, gameList, resultList);
       Data.saveTournaments();
        //----------------------------------/Test of tournment---------------------------------*/
        
    } 
    
       public static ArrayList<Player> getPlayers() {
        
        ArrayList players = (ArrayList<Player>)getTournaments().get(0).getPlayers();
        
        return players;
    }
    
    public static ArrayList<Tournament> getTournementArrayList() {
        
        //tournaments = (ArrayList<Tournament>)getTournaments();
        
        return tournaments;
    }
    
}
