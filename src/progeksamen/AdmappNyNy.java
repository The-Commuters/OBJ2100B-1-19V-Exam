/*
 * Main
 */
package progeksamen;

import gui.components.Title;
import gui.components.Body;
import gui.components.Crumb;
import gui.components.Menu;
import gui.components.Container;
import gui.components.Page;
import gui.components.Tools;
import gui.tools.Back;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import static progeksamen.Admapp.tournamentList;
import static progeksamen.Data.tournaments;

public class AdmappNyNy extends Application {
    
    Container container;
            Player playerName1;
          Player playerName2;
     Dialog dialog;
           Button newUsers = new Button();
    
    @Override
    public void start(Stage primaryStage) {
 
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

         // Saves the list of  on exit.
        primaryStage.setOnHiding( event -> {
            System.out.println("Your progress have been saved on exit.");
            // Saves here to the "Database" where the array lies
            Data.saveTournaments();
        });
        
        ////////////////////////////////////////////////////////////////
        // PrimaryStage
        primaryStage.setTitle("ChessX");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(container.getMinWidth());
        primaryStage.setMinHeight(container.getMinHeight());
        primaryStage.show();
        
    }
    /**
     * This method will take in the 3 Strings from 
     * the method caller and use them in the dialog 
     * that will be shown to the user
     * 
     * It will try to stop the user 
     * from not entering anything
     * 
     * 
     * @param HeaderTxt
     * @param tittle
     * @param warning
     * @return the users input in to the dialog
     */
    
    public String TextDialog(String HeaderTxt, String tittle, String warning) {

        TextInputDialog textin = new TextInputDialog();
                textin.setGraphic(null);
        textin.setHeaderText(null);
        
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
 
    public Page tournament() {
        Body body = new Body("Tournament");
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(body.getName()));
        Crumb crumb = new Crumb("Tournament");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
        
        // New tournament button
        Button newTournBtn = new Button();
        newTournBtn.setText("New Tournament");
   
        // Save button -
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        
        tools.getChildren().addAll(saveBtn, newTournBtn);
  
        BorderPane header = new BorderPane(menu,title , null , null, null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main
       
        // LIST
        ListView<Tournament> list = new ListView<>(Data.getTournaments());
        list.setCellFactory(new TournamentCellFactory());
        list.setOrientation(Orientation.VERTICAL);
        list.setFocusTraversable(false);
        list.getSelectionModel().selectedItemProperty().addListener(this::chooseTournament);
        list.getStyleClass().add("list");
        
      

        // The button that adds a new tournament to the tournaments-list
        newTournBtn.setOnAction((ActionEvent event) -> {
            String tournamentNameIn = TextDialog("Enter tournament name", "Tournament Name", "Name can not be empty");
            Tournament newTournament = new Tournament(tournamentNameIn);
            Data.tournaments.add(newTournament);
            ObservableList<Tournament> newTournaments = FXCollections.<Tournament>observableArrayList(tournaments);
            list.setItems(newTournaments);
            chooseTournament(newTournament,newTournament);
            newUsers.fire();
        });
        
        // Event that saves the current list of users.
        saveBtn.setOnAction(( event) -> {
            System.out.println("Your progress is saved with the save-button.");
            Data.saveTournaments();
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
        
        Button test = new Button("test");
        Button leaderBoardBtn = new Button("Show Leaderboard");
        
        // Button for the new player-feature
        newUsers.setText("New Player");
 
        // The comboboxes where the admin chooses the to players.
        ComboBox<Player> playerMenuPlayer1 = new ComboBox<>();
        ComboBox<Player> playerMenuPlayer2 = new ComboBox<>();
        
        // Sets the max and min width of the player 
        playerMenuPlayer1.setMaxWidth(130);
        playerMenuPlayer2.setMaxWidth(130);
        playerMenuPlayer1.setMinWidth(130);
        playerMenuPlayer2.setMinWidth(130);
        
        // This is the textfield used to search the tournament for games.
        TextField searchField = new TextField();
        searchField.setMaxWidth(60);
        
        // Button that creates a new game-object.
        Button newGameBtn = new Button("New game");
      
        tools.getChildren().addAll(leaderBoardBtn, playerMenuPlayer1, playerMenuPlayer2, newGameBtn, newUsers, searchField);
        BorderPane header = new BorderPane(menu, title, null , null, null);
        body.setTop(header);
        
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
        
        // LISTENERs
        //The dialogpane that pops up after the name for a new tournament have been written in.
        newUsers.setOnAction((ActionEvent event) -> {
            final Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            TextField newUserField = new TextField();
            Button addUser = new Button("Add a new user");
            Button done = new Button("Done");
            
            //LIST
            ObservableList<Player> newPlayers = FXCollections.<Player>observableArrayList(tournament.getPlayers());
            ListView<Player> playerList = new ListView<>(newPlayers);
            playerList.setOrientation(Orientation.VERTICAL);
            playerList.setFocusTraversable(false);
            playerList.getStyleClass().add("list");
            HBox hBox = new HBox(playerList);
            
            // Event that fire when the addUser-button is pressed.
            addUser.setOnAction((ActionEvent e) -> {
                Player player = new Player(newUserField.getText());
                Data.tournaments.get(Data.tournaments.size() - 1).getPlayers().add(player);
                Tournament newTournament = Data.tournaments.get(Data.tournaments.size() - 1);
                ObservableList<Player> updatedPlayerlist = FXCollections.<Player>observableArrayList(newTournament.getPlayers());                //ListView<Tournament> list = new ListView<>(Data.getTournaments());
                playerList.setItems(updatedPlayerlist);
                playerMenuPlayer1.setItems(updatedPlayerlist);
                playerMenuPlayer2.setItems(updatedPlayerlist);
            });
            
            // Event that fires when the done-button is pressed.
            done.setOnAction((ActionEvent e) -> {
                dialogStage.close();
            });
            
            // Adds the elements to the new page.
            VBox vBox = new VBox();
            vBox.getChildren().addAll(newUserField, addUser, playerList, done);
            dialogStage.setScene(new Scene(vBox));
            dialogStage.show();
        });
        
         ObservableList<Player> playerLists = FXCollections.<Player>observableArrayList(tournament.getPlayers());
         playerMenuPlayer1.setItems(playerLists);
         playerMenuPlayer2.setItems(playerLists);
         
        playerMenuPlayer1.setOnAction(e -> {
               playerName1 = playerMenuPlayer1.getSelectionModel().getSelectedItem();
               System.out.println(playerName1);
        });
        
        playerMenuPlayer2.setOnAction(e -> {
               playerName2 = playerMenuPlayer2.getSelectionModel().getSelectedItem();
               System.out.println(playerName2);
        });
        
          //System.out.println(tournament.getPlayers().get(0).getScore());
        
           // Main
//        test.setOnAction(e ->{
//            
//            System.out.println(tournament.getDetails());
//            //tournament.getPlayers().get(0).setScore(7);
//             System.out.println(tournament.getDetails());
//          tournament.sortScoreBoard();
//         System.out.print(tournament.getDetails());
//          
//         //tournament.getPlayers().get(0).reversed();
//         
//        });
        
         

         dialog = new Dialog();
        dialog.initModality(Modality.APPLICATION_MODAL); 
        dialog.setTitle("Leaderboard");
        
        
        //alert.initModality(Modality.APPLICATION_MODAL);
         Window window = dialog.getDialogPane().getScene().getWindow();
         
          window.setOnCloseRequest(e -> dialog.close());   
       
         leaderBoardBtn.setOnAction(e ->{
             dialog.setContentText(tournament.getLeaderBoard());
          //System.out.print(tournament.getPlayers().get(0).compare(playerName1, playerName2));
         dialog.showAndWait();
        });
        
        // LIST INPUT
        ObservableList<Game> listGameItems = FXCollections.<Game>observableArrayList(tournament.getGames());
        
        // LIST
        ListView<Game> gameList = new ListView<>(listGameItems);
        gameList.getItems().addAll();
        gameList.setCellFactory(new GameCellFactory());
        gameList.setOrientation(Orientation.VERTICAL);
        gameList.setFocusTraversable(false);
        gameList.getSelectionModel().selectedItemProperty().addListener(this::chooseGame);
        gameList.getStyleClass().add("list");
        
        // LISTENER
        newGameBtn.setOnAction(e -> {
           //Data.tournaments.get(0).getGames().add(new Game(playerName1, playerName2));
            int index = Data.tournaments.indexOf(tournament); 
            System.out.println("All ok!");
            tournament.getGames().add(new Game(playerName1, playerName2));
            Data.tournaments.set(index, tournament);
            ObservableList<Game> games = FXCollections.<Game>observableArrayList(tournament.getGames());
            gameList.setItems(games);
        });
        
        // USED for the search function, listens to the textfield searchField.
        // DOES NOT WORK PERFECTLY!!!!!!!!!!
        searchField.textProperty().addListener((obs, oldText, newText) -> {
            String[] stringArray = newText.split("");
            ArrayList<Game> newGameList = new ArrayList<Game>();
            Tournament tournamentSearchTest = tournament;
            for (String character : stringArray) 
                newGameList = tournamentSearchTest.search(character);
            ObservableList<Game> games = FXCollections.<Game>observableArrayList(newGameList);
            listItems.setAll(games);
        });
        
        StackPane main = new StackPane(gameList);
        body.setCenter(main);
        
        return new Page(body);
    }
    
    public Page game(Game game) {
        Body body = new Body("Game");

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
            game.handleGameResult(game);
        });
        
        tools.getChildren().addAll(editResultBtn);
        
        BorderPane header = new BorderPane(menu, title, null, null, null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main

        
        // CHESS
        HBox chessboardContainer = new HBox();
        
        // CHESSBOARD
        
   
        
        // CHESSBOARD
        

        
       
        // CHESS
       

        
        
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
    
       public void chooseTournament(Tournament previousTournament, Tournament currentTournament) {
        System.out.println("Moved from " + previousTournament + "page, to " + currentTournament + "page");
        container.put(lobby(currentTournament));
    }
    
    public void chooseGame(ObservableValue<? extends Game> observable, Game previousGame, Game currentGame) {
        System.out.println("Moved from " + previousGame + "page, to " + currentGame + "page");
        container.put(game(currentGame));
    }
}
