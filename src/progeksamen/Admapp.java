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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Admapp extends Application implements Constants {
    
    // Current string for the Tournament, needed to find/create the .dat to store binary And the .txt.
    public static String tournament = "Tournament";
    
    BorderPane root = new BorderPane();
    
    public static ArrayList<Tournament> tournamentList    = new ArrayList<Tournament>();
    
    /** Metodeforklaringer -- MÅ SLETTES SENERE --
     * getTournament() collects the data from the "database"-file.
     * saceTournament() Saves the data to the "database"-file. 
     */
    
    @Override
    public void start(Stage primaryStage) {
        
        // The method that collects the tournaments-arraylist.
        
        
        
        tournamentList = Data.tempgetTournaments();
        
        // Test's the method that saves the tournment-data.
        // SaveTournament(playerList, gameList, resultList);
       Data.saveTournaments(tournamentList);
       
       
       
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
       
       
       
        //----------------------------------/Test of tournment---------------------------------
        
        //---- Save Button ----
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        
        // Event that sav
        saveBtn.setOnAction(( event) -> {
            System.out.println("Your progress is saved with the save-button.");
            Data.saveTournaments(tournamentList);
        });
        root.setTop(saveBtn);
        //---- Edit result Button ----
        
            //---To be removed---
            Player playerTest1   = new Player("Harry");
            Player playerTest2   = new Player("Ron");
            Game gameTest        = new Game(playerTest1, playerTest2, result1, gameArraylist, date, date);
            //-------------------
            
            
        Button editResultBtn = new Button();
        editResultBtn.setText("Edit Result");
        editResultBtn.setOnAction(( event) -> {
            result1.handleGameResult(game1);
        });
        //root.setRight(editResultBtn);
        //----/Edit result Button ---- 
        
        //----- Search for game ------
        
        TextField searchField = new TextField();
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            //System.out.println("Text changed from "+oldText+" to "+newText);
            ArrayList<Game> newGameList = new ArrayList<Game>();
            Tournament tournamentSearchTest = tournamentList.get(1);
            newGameList = tournamentSearchTest.search(newText);
        });
        
        //-----------------------------
           
        Button newBtn = new Button();
        newBtn.setText("New Tournament");
        newBtn.setOnAction((ActionEvent event) -> {
            String tournamentNameIn;
            
            tournamentNameIn = TextDialog("Enter tournament name", "Tournament Name", "Name can not be empty");
            
            Tournament test = new Tournament(tournamentNameIn, playerList, gameList);
                 
            tournamentList.add(test);

            System.out.println(test.toString());
        });
        //root.setLeft(newBtn);
        
        Button newPlayerBtn = new Button();
        newPlayerBtn.setText("New Player");
        newPlayerBtn.setOnAction((ActionEvent event) -> {
            String playerNameIn;
            
            playerNameIn = TextDialog("Enter player name", "Player Name", "Name can not be empty");
            
                playerList.add(new Player(playerNameIn));   
                playerList.toString();
           
        });
        
        ComboBox<Player> playerMenu = new ComboBox<>();
        
        ObservableList playerComboList = FXCollections.observableList(getList());
        
        playerMenu.setItems(playerComboList);
         
                 
        playerMenu.setOnAction(e -> {
               Player player_int = playerMenu.getSelectionModel().getSelectedItem();
        
               System.out.println(player_int);
        });
           
      
        HBox hbox = new HBox();
        hbox.getChildren().addAll(playerMenu, newPlayerBtn, newBtn, editResultBtn, saveBtn, searchField);

        //root.setTop(playerMenu);
        root.setTop(hbox);
        
        // Saves the lists on exit.
        primaryStage.setOnHiding( event -> {
            System.out.println("Your progress have been saved on exit.");
            Data.saveTournaments(tournamentList);
            
        });
 
        // Setter her opp scene, for å ha en måte å lukke applikasjonen enklere på.
        Scene scene = new Scene(root, SCREEN_HEIGHT, SCREEN_WIDTH);
        primaryStage.setTitle("Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
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
 
   public List<Player> getList(){
      return tournamentList.get(0).getPlayers();
  }
   
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
