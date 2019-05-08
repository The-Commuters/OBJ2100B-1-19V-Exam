/*
 * Main for the Admin application.
 */
package progeksamen;

import gui.components.Body;
import gui.components.Container;
import gui.components.Crumb;
import gui.components.Menu;
import gui.components.Page;
import gui.components.Title;
import gui.components.Tools;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdmappNy extends Application {
    
    // Current string for the Tournament, needed to find/create the .dat to store binary And the .txt.
    public static String tournament = "Tournament";
    
    BorderPane root = new BorderPane();
     static ArrayList<Tournament> tournamentList    = new ArrayList<Tournament>();
    
        Container container;
    
    /** Metodeforklaringer -- MÅ SLETTES SENERE --
     * getTournament() collects the data from the "database"-file.
     * saceTournament() Saves the data to the "database"-file. 
     */
    
    @Override
    public void start(Stage primaryStage) {
        
        /*
        //---To be removed ---
            ArrayList<Tournament> tournamentsTestList = Data.getTournementArrayList();
        //-------------------
        
        //---- Save Button ----
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        
        // Event that saves
        saveBtn.setOnAction(( event) -> {
            System.out.println("Your progress is saved with the save-button.");
            Data.saveTournaments(Data.getTournementArrayList());
        });
        root.setTop(saveBtn);
        

        

           
        Button newBtn = new Button();
        newBtn.setText("New Tournament");
        newBtn.setOnAction((ActionEvent event) -> {
            String tournamentNameIn;
            
            tournamentNameIn = TextDialog("Enter tournament name", "Tournament Name", "Name can not be empty");
            
            Tournament test = new Tournament(tournamentNameIn, tournamentsTestList.get(0).getPlayers(), tournamentsTestList.get(0).getGames());
                 
            tournamentList.add(test);

            System.out.println(test.toString());
        });
        //root.setLeft(newBtn);
        
        Button newPlayerBtn = new Button();
        newPlayerBtn.setText("New Player");
        newPlayerBtn.setOnAction((ActionEvent event) -> {
            String playerNameIn;
            
            playerNameIn = TextDialog("Enter player name", "Player Name", "Name can not be empty");
            
                tournamentsTestList.get(0).getPlayers().add(new Player(playerNameIn));   
                tournamentsTestList.get(0).getPlayers().toString();
           
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
            
        });*/
 
        // The first page
        Page tournamentPage = tournament();
        
        ////////////////////////////////////////////////////////////////
        // Root & Home page
        container = new Container(800, 600);
        container.put(tournamentPage);
        
        ////////////////////////////////////////////////////////////////
        // Scene
        Scene scene = new Scene(container);
        scene.getStylesheets().add("progeksamen/main.css");

         /*// Saves the list of  on exit.
        primaryStage.setOnHiding( event -> {
            System.out.println("Your progress have been saved on exit.");
            Data.saveTournaments(tournamentList);
        });*/
        
        ////////////////////////////////////////////////////////////////
        // PrimaryStage
        primaryStage.setTitle("ChessX");
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
   
   public Page tournament() {
        Body body = new Body("Tournament");
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(body.getName()));
        Crumb crumb = new Crumb("Tournament");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
        
        // Save button -
        Button saveBtn = new Button();
        saveBtn.setText("Save");
  
        BorderPane header = new BorderPane(menu, title, saveBtn, null, null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main
       
        // LIST
        ListView<Tournament> list = new ListView<>(Data.getTournaments());
        list.getItems().addAll();
        list.setCellFactory(new TournamentCellFactory());
        list.setOrientation(Orientation.VERTICAL);
        list.setFocusTraversable(false);
        list.getSelectionModel().selectedItemProperty().addListener(this::chooseTournament);
        list.getStyleClass().add("list");
        // LIST
       
        // Event that saves the current list of users.
        saveBtn.setOnAction(( event) -> {
            System.out.println("Your progress is saved with the save-button.");
            
            List<Tournament> listOfTournaments = list.getItems();
            ArrayList<Tournament> arrayListOfTournaments;
            if (listOfTournaments instanceof ArrayList<?>) {
                arrayListOfTournaments = (ArrayList<Tournament>) listOfTournaments;
            } else {
                arrayListOfTournaments = new ArrayList<>(listOfTournaments);
            }
            Data.saveTournaments(arrayListOfTournaments);
            tournamentList = arrayListOfTournaments;
        });
        
        StackPane main = new StackPane(list);
        body.setCenter(main);
        
        return new Page(body);
    }
    
    public Page lobby(Tournament tournament) {
        Body body = new Body(tournament.getName());
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(body.getName()));
        Crumb crumb = new Crumb("Tournament", "Lobby");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
       
        
        ComboBox<Player> playerMenu = new ComboBox<>();
        // This is the textfield used to search the tournament for games.
        TextField searchField = new TextField();
        
        BorderPane header = new BorderPane(menu, title, playerMenu,searchField,  null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main
          

         ObservableList<Player> playerLists = FXCollections.<Player>observableArrayList(Data.getPlayers());
        
         playerMenu.setItems(playerLists);
         
                 
        playerMenu.setOnAction(e -> {
               Player player_int = playerMenu.getSelectionModel().getSelectedItem();
        
               System.out.println(player_int);
        });
        // LIST INPUT
        ObservableList<Game> listItems = FXCollections.<Game>observableArrayList(tournament.getGames());
        
        
        // LIST
        ListView<Game> list = new ListView<>(listItems);
        list.getItems().addAll();
        list.setCellFactory(new GameCellFactory());
        list.setOrientation(Orientation.VERTICAL);
        list.setFocusTraversable(false);
        list.getSelectionModel().selectedItemProperty().addListener(this::chooseGame);
        list.getStyleClass().add("list");
        // LIST
        
        // USED for the search function, listens to the textfield searchField.
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            String[] stringArray = newText.split("");
            ArrayList<Game> newGameList = new ArrayList<Game>();
            Tournament tournamentSearchTest = tournament;
            for (String character : stringArray) 
                newGameList = tournamentSearchTest.search(character);
            listItems.setAll(newGameList);
        });
        
        StackPane main = new StackPane(list);
        body.setCenter(main);
        
        return new Page(body);
    }
    
    public Page game(Game game) {
        Body body = new Body("DANIEL VS David");
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(game.getPlayer1().getName() + " VS " + game.getPlayer2().getName()));
        Crumb crumb = new Crumb("Tournament", "Lobby", "Game");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
        
        // The button that lets you edit or add a new result to a game.
        Button editResultBtn = new Button();
        editResultBtn.setText("Edit Result");
     
        editResultBtn.setOnAction(( event) -> {
            game.getResult().handleGameResult(game);
        });
  
        BorderPane header = new BorderPane(menu, title, editResultBtn, null, null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main
        
        return new Page(body);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void chooseTournament(ObservableValue<? extends Tournament> observable, Tournament previousTournament, Tournament currentTournament) {
        System.out.println("Moved from " + previousTournament + "page, to " + currentTournament + "page");
        container.put(lobby(currentTournament));
    }
    
    public void chooseGame(ObservableValue<? extends Game> observable, Game previousGame, Game currentGame) {
        System.out.println("Moved from " + previousGame + "page, to " + currentGame + "page");
        container.put(game(currentGame));
    }
    
}
