/*
 * Main for the Admin application.
 */
package progeksamen;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Admapp extends Application implements Constants {
    
    // Current string for the Tournament, needed to find/create the .dat to store binary And the .txt.
    public static String tournament = "Tournament";
    
    BorderPane root = new BorderPane();
    
    // The list's used to store data.
    public static ArrayList<Player> playerList    = new ArrayList<Player>();
    public static ArrayList<Game> gameList        = new ArrayList<Game>();
    public static ArrayList<Result> resultList    = new ArrayList<Result>();
    
    /** Metodeforklaringer -- MÅ SLETTES SENERE --
     * getTournament() collects the data from the "database"-file.
     * saceTournament() Saves the data to the "database"-file. 
     */
    
    @Override
    public void start(Stage primaryStage) {
        
        // The method that collects the data from the Binary file.
        getTournament();
        
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
       
        playerList.add(player);  playerList.add(player1); playerList.add(player2);
        gameList.add(game1);     gameList.add(game2);     gameList.add(game3);
        resultList.add(result1); resultList.add(result2); resultList.add(result3);
        
        // Test's the method that saves the tournment-data.
        // SaveTournament(playerList, gameList, resultList);
       
        
        
        //----------------------------------/Test of tournment---------------------------------
        
        //---- Save Button ----
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(( event) -> {
            System.out.println("Your progress is saved with the save-button.");
            saveTournament(playerList, gameList, resultList);
        });
        root.setTop(saveBtn);
        //---- Edit result Button ----
            //---To be removed---
            Player playerTest1   = new Player("Harry");
            Player playerTest2  = new Player("Ron");
            Game gameTest      = new Game(playerTest1, playerTest2, result1, gameArraylist, date, date);
            //-------------------
        Button editResultBtn = new Button();
        editResultBtn.setText("Edit Result");
        editResultBtn.setOnAction(( event) -> {
            result1.handleGameResult(game1);
        });
        root.setRight(editResultBtn);
        //----/Edit result Button ----        
           
        Button newBtn = new Button();
        newBtn.setText("New Tournament");
        newBtn.setOnAction((ActionEvent event) -> {
            String tournamentNameIn;
            
            tournamentNameIn = TextDialog("Enter tournament name", "Tournament Name", "Name can not be empty");
            
                 Tournament test = new Tournament(tournamentNameIn, playerList, gameList, resultList);
          
            

            System.out.println(test.toString());
        });
        root.setLeft(newBtn);
        
          Button newPlayerBtn = new Button();
        newPlayerBtn.setText("New Player");
        newPlayerBtn.setOnAction((ActionEvent event) -> {
            String playerNameIn;
            
            playerNameIn = TextDialog("Enter player name", "Player Name", "Name can not be empty");
            
                playerList.add(new Player(playerNameIn));   
                playerList.toString();
           
        });
        root.setRight(newPlayerBtn);
        
        // Saves the lists on exit.
        primaryStage.setOnHiding( event -> {
            System.out.println("Your progress have been saved on exit.");
            saveTournament(playerList, gameList, resultList);
        });
 
        // Setter her opp scene, for å ha en måte å lukke applikasjonen enklere på.
        Scene scene = new Scene(root, SCREEN_HEIGHT, SCREEN_WIDTH);
        primaryStage.setTitle("Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    /**
     * This method is the one that saves the Arraylist's into the file
     * to store them.
     * @param players is the ArrayList of player-objects.
     * @param games is the ArrayList of game-objects.
     * @param results is the ArrayList of result-objects. 
     */
    public void saveTournament(ArrayList<Player> players, ArrayList<Game> games, ArrayList<Result> results) {
        
        try {
            
            // First the objects is written to the file that holds the binary data...
            FileOutputStream fileOut = new FileOutputStream(tournament + ".dat");
            
            // Places the object-array's in the binary-file.
            try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                // Places the object-array's in the binary-file.
                objectOut.writeObject(players);
                objectOut.writeObject(games);
                objectOut.writeObject(results);
            }
            
            System.out.println("The Objects was succesfully written to a file in binary");
            
            //------------------------ Start of backup ---------------------
            
            // ...Then it creates the backup in clear text.
            BufferedWriter outStream = new BufferedWriter(new FileWriter(tournament + ".txt"));
            
            outStream.write("The Players of the tournament:");
            for (Player player : players) {
                outStream.newLine();
                outStream.write(player.toString());
            }

            // Places the list of games.
            outStream.newLine();
            outStream.newLine();
            outStream.write("The Games of the tournament:");
            for (Game game : games) {
                outStream.newLine();
                outStream.write(game.toString());
            }
            
            outStream.close();
            System.out.println("The Objects was succesfully written to a file in text");
            
        } catch (IOException ex) {}
    }

    /**
     * The getTorunament is the method that collects the data from the 
     * binary file where it was stored, and places them into the list
     * that they should be in before the application really starts. 
     */
    public void getTournament() {
        
        try {
            // create an ObjectInputStream for the file we created before
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(tournament + ".dat"));
            
            // Collects the player's from the storage-file.
            try{
                
                playerList = (ArrayList<Player>) input.readObject();
                gameList = (ArrayList<Game>) input.readObject();
                resultList = (ArrayList<Result>) input.readObject();
                
            } catch (ClassCastException | ClassNotFoundException e){
            }
        } catch (IOException ex) {}
    }
    
    public String TextDialog(String HeaderTxt, String tittle, String warning) {

        TextInputDialog textin = new TextInputDialog();
        textin.initStyle(StageStyle.UTILITY);
        textin.setHeaderText(HeaderTxt);
        textin.setTitle(HeaderTxt);
        Optional<String> result = textin.showAndWait();
        String text ="";
        if (result.isPresent()) {
            while (result.get().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, warning);
            alert.showAndWait();
            result = textin.showAndWait();
            }
            
            text = result.get();
        }
        return text;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
