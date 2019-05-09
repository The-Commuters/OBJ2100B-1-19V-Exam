/**
 * Main for the application that the administrators use,
 * it should have more features that the application that
 * are designed for the normal users. The admins can create
 * touraments, players and games.
 */
package progeksamen;

import Pieces.ChessBoardModel;
import Pieces.Move;
import Pieces.Parser;
import gui.chess.ChessSimulator;
import gui.components.Title;
import gui.components.Body;
import gui.components.Crumb;
import gui.components.Menu;
import gui.components.Container;
import gui.components.Page;
import gui.components.Tools;
import gui.tools.Back;
import gui.tools.TextDialog;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import static progeksamen.Data.tournaments;

public class Admapp extends Application {
    
    Container container;
    Player playerName1;
    Player playerName2;
    Dialog dialog;
    Button newUsers = new Button();
    ComboBox<Player> playerMenuPlayer1 = new ComboBox<>();
    ComboBox<Player> playerMenuPlayer2 = new ComboBox<>();
    
    @Override
    public void start(Stage primaryStage) {
 
        // The first page
        Page tournamentPage = tournament();
        
        // Root & Home page
        container = new Container(800, 600);
        container.put(tournamentPage);
        
        // Scene with a container object, and the CSS file.
        Scene scene = new Scene(container);
        scene.getStylesheets().add("progeksamen/main.css");

         // Saves the list of  on exit.
        primaryStage.setOnHiding( event -> {
            System.out.println("Your progress have been saved on exit.");
            // Saves here to the "Database" where the array lies
            Data.saveTournaments();
        });

        // PrimaryStage setup
        primaryStage.setTitle("ChessX");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(container.getMinWidth());
        primaryStage.setMinHeight(container.getMinHeight());
        primaryStage.getIcons().add(new Image("gui/images/king-white.png"));
        primaryStage.show();
        
    }
//    /**
//     * This method will take in the 3 Strings from 
//     * the method caller and use them in the dialog 
//     * that will be shown to the use
//     * @param HeaderTxt
//     * @param tittle
//     * @param warning
//     * @return the users input in to the dialog
//     */
//    public String TextDialog(String HeaderTxt, String tittle, String warning) {
//
//        TextInputDialog textin = new TextInputDialog();
//                textin.setGraphic(null);
//        textin.setHeaderText(null);
//        
//        textin.initStyle(StageStyle.UTILITY);
//        textin.setHeaderText(HeaderTxt);
//        textin.setTitle(HeaderTxt);
//        Optional<String> result = textin.showAndWait();
//        String text ="";
//        if (result.isPresent()) {
//            while (result.get().isEmpty()){
//                Alert alert = new Alert(Alert.AlertType.WARNING, warning);
//                alert.showAndWait();
//                result = textin.showAndWait();
//            }
//            text = result.get();
//        }
//        return text;
//    }
//    
    
    
    /**
     * The uppermost layer of the pages, the tournament is where one
     * starts as one open up the the application, on this layer the
     * user will be able to see the different tournaments.
     * @return 
     */
    public Page tournament() {
        Body body = new Body("Tournament");
        
        // Header
        Title title = new Title(new Text(body.getName()));
        Crumb crumb = new Crumb("Tournament");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
        
        // New tournament button
        Button newTournBtn = new Button();
        newTournBtn.setText("New Tournament");
   
        // Save button
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        
        // Adds the elements to the tool-bar
        tools.getChildren().addAll(saveBtn, newTournBtn);
        
        BorderPane header = new BorderPane(menu,title , null , null, null);
        body.setTop(header);
        
        // LIST THAT COLLECTS THE Tournament OBJECTS
        ListView<Tournament> list = new ListView<>(Data.getTournaments());
        list.setCellFactory(new TournamentCellFactory());
        list.setOrientation(Orientation.VERTICAL);
        list.setFocusTraversable(false);
        list.getSelectionModel().selectedItemProperty().addListener(this::chooseTournament);
        list.getStyleClass().add("list");

        // The button that adds a new tournasment to the tournaments-list
        newTournBtn.setOnAction((ActionEvent event) -> {
            String tournamentNameIn = new TextDialog("Enter tournament name", "Tournament Name", "Name can not be empty").toString();
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
        
        // Places the main stackpane in the body.
        StackPane main = new StackPane(list);
        body.setCenter(main);
        return new Page(body);
    }
    
    /**
     * The lobby is shown when someone presses one of the objects in the 
     * list that are shown there, one can add a new user to the tournament.
     * @param tournament
     * @return 
     */
    public Page lobby(Tournament tournament) {
        Body body = new Body(tournament.getName());
        
        // Header
        Title title = new Title(new Text(body.getName()));
        title.addButton(new Back(container));
        Crumb crumb = new Crumb("Tournament", "Lobby");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
       
        // The button that shows the leaderboard.
        Button leaderBoardBtn = new Button("Show Leaderboard");
        
        // Button that creates a new game-object.
        Button newGameBtn = new Button("New game");
        
        // Button that opens the search dialog for the games.
        Button searchBtn = new Button("Search");
        
        // Button for the new player-feature
        newUsers.setText("New Player");
        
        // Places the elements into the tool-bar.
        tools.getChildren().addAll(leaderBoardBtn, newGameBtn, newUsers, searchBtn);
        BorderPane header = new BorderPane(menu, title, null , null, null);
        body.setTop(header);
        
        // LIST INPUT FOR GAMELIST
        ObservableList<Game> listGameItems = FXCollections.<Game>observableArrayList(tournament.getGames());
        
        // LIST FOR THE GAMES
        ListView<Game> gameList = new ListView<>(listGameItems);
        gameList.getItems().addAll();
        gameList.setCellFactory(new GameCellFactory());
        gameList.setOrientation(Orientation.VERTICAL);
        gameList.setFocusTraversable(false);
        gameList.getSelectionModel().selectedItemProperty().addListener(this::chooseGame);
        gameList.getStyleClass().add("list");
        
        
        // LISTENER FOR THE ADDING OF USERS IN DIALOGPANE
        //////////////////////////////////////////////////////////////////////////////////////
        // The dialogpane that pops up after the name for a new tournament have been written in.
        newUsers.setOnAction((ActionEvent event) -> {
            final Stage dialogStage = new Stage();
            dialogStage.setWidth(600);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            TextField newUserField = new TextField();
            Button addUser = new Button("Add a new user");
            Button done = new Button("Done");
            
            //LIST THAT COLLECTS THE Player OBJECTS
            ObservableList<Player> newPlayers = FXCollections.<Player>observableArrayList(tournament.getPlayers());
            ListView<Player> playerList = new ListView<>(newPlayers);
            playerList.setOrientation(Orientation.VERTICAL);
            playerList.setFocusTraversable(false);
            playerList.getStyleClass().add("list");

            // Event that fire when the addUser-button is pressed, and adds the users to the tournament and updates lists.
            addUser.setOnAction((ActionEvent e) -> {
                Player player = new Player(newUserField.getText());
                Data.tournaments.get(Data.tournaments.size() - 1).getPlayers().add(player);
                Tournament newTournament = Data.tournaments.get(Data.tournaments.size() - 1);
                ObservableList<Player> updatedPlayerlist = FXCollections.<Player>observableArrayList(newTournament.getPlayers());
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
            vBox.setPadding(new Insets(20, 20, 20, 20));
            vBox.setSpacing(10);
            dialogStage.setScene(new Scene(vBox));
            dialogStage.show();
        });      
             
        
        // LISTENER FOR THE ADDING OF GAMES IN DIALOGPANE
        ////////////////////////////////////////////////////////////////////////////////////
        // This opens a dialogStage where the Admin can place two players and then press the button to create a game.
        newGameBtn.setOnAction((ActionEvent event) -> {
            final Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            
            // The comboboxes where the admin chooses the to players.
            Button addGame = new Button("Create Game");
            Label addGameLabel = new Label("Choose two players");

            // Sets the max and min width of the player 
            playerMenuPlayer1.setMaxWidth(200);
            playerMenuPlayer2.setMaxWidth(200);
            playerMenuPlayer1.setMinWidth(300);
            playerMenuPlayer2.setMinWidth(300);
            
            // Adds the game to the list's of other games.
            addGame.setOnAction(e -> {
                int index = Data.tournaments.indexOf(tournament); 
                tournament.getGames().add(new Game(playerName1, playerName2));
                Data.tournaments.set(index, tournament);
                ObservableList<Game> games = FXCollections.<Game>observableArrayList(tournament.getGames());
                gameList.setItems(games);
                dialogStage.close();
            });
           
            // sets the list of players aas items in hte comboboxes.
            ObservableList<Player> playerLists = FXCollections.<Player>observableArrayList(tournament.getPlayers());
            playerMenuPlayer1.setItems(playerLists);
            playerMenuPlayer2.setItems(playerLists);
            
            // Places the new selected item in playerName1 and playerName2.
            playerMenuPlayer1.setOnAction(e -> {
                  playerName1 = playerMenuPlayer1.getSelectionModel().getSelectedItem();
                  System.out.println(playerName1);
            });
            playerMenuPlayer2.setOnAction(e -> {
                  playerName2 = playerMenuPlayer2.getSelectionModel().getSelectedItem();
                  System.out.println(playerName2);
            });
            
            // Adds the elements to the new page.
            VBox vBox = new VBox(addGameLabel, playerMenuPlayer1, playerMenuPlayer2, addGame);
            vBox.setPadding(new Insets(20, 20, 20, 20));
            vBox.setSpacing(10);
            vBox.getChildren().addAll();
            dialogStage.setScene(new Scene(vBox));
            dialogStage.show();
        });
        
        
        // LISTENER FOR OPENING THE SEARCH-DIALOGPANE
        //////////////////////////////////////////////////////////////////////////////////////
        // The dialogpane that pops up after the name for a new tournament have been written in.
        searchBtn.setOnAction((ActionEvent event) -> {
            final Stage dialogStage = new Stage();
            dialogStage.setWidth(600);
            
            // This is the textfield used to search the tournament for games.
            TextField searchField = new TextField();
            searchField.setMaxWidth(600);
            
            // LIST INPUT FOR GAMELIST
            ObservableList<Game> searchListItems = FXCollections.<Game>observableArrayList(tournament.getGames());

            // List of games, placed in the search-dialog.
            ListView<Game> searchList = new ListView<>(searchListItems);
            searchList.getItems().addAll();
            searchList.setCellFactory(new GameCellFactory());
            searchList.setOrientation(Orientation.VERTICAL);
            searchList.setFocusTraversable(false);
            searchList.getSelectionModel().selectedItemProperty().addListener(this::chooseGame);
            searchList.getStyleClass().add("list");
            
            // LIST FOR SEARCHING TROUGH        
            searchList.setOrientation(Orientation.VERTICAL);
            searchList.setFocusTraversable(false);
            searchList.getStyleClass().add("list");
            Button done = new Button("Done");
         
            // USED for the search function, listens to the textfield searchField.
            searchField.textProperty().addListener((obs, oldText, newText) -> {
                String[] stringArray = newText.split("");
                ArrayList<Game> newGameList = new ArrayList<Game>();
                Tournament tournamentSearchTest = tournament;
                for (String character : stringArray) 
                    newGameList = tournamentSearchTest.search(character);
                ObservableList<Game> games = FXCollections.<Game>observableArrayList(newGameList);
                searchListItems.setAll(games);
            });
        
            // Event that fires when the done-button is pressed.
            done.setOnAction((ActionEvent e) -> {
                dialogStage.close();
            });

            // Adds the elements to the new page.
            VBox vBox = new VBox(searchField, searchList, done);
            vBox.getChildren().addAll();
            vBox.setPadding(new Insets(20, 20, 20, 20));
            vBox.setSpacing(10);
            dialogStage.setScene(new Scene(vBox));
            dialogStage.show();
        });
        
        // The dialog-window that shows of the dialog window.
        dialog = new Dialog();
        dialog.initModality(Modality.APPLICATION_MODAL); 
        dialog.setTitle("Leaderboard");        //alert.initModality(Modality.APPLICATION_MODAL);
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> dialog.close());
        
        // Places the strings on the leaderboard.
        leaderBoardBtn.setOnAction(e ->{
            dialog.setContentText(tournament.getLeaderBoard());
            dialog.showAndWait();
        });

        // The stackpane that the list with the games is placed on.
        StackPane main = new StackPane(gameList);
        body.setCenter(main);
        return new Page(body);
    }
    
    /**
     * The innermost layer of the GUI, here the users can watch the 
     * @param game
     * @return 
     */
    public Page game(Game game) {
        Body body = new Body("Game");

        // Header
        Title title = new Title(new Text(game.getPlayer1().getName() + " VS " + game.getPlayer2().getName()));
        title.addButton(new Back(container));
        Crumb crumb = new Crumb("Tournament", "Lobby", "Game");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
       
        // The button that lets you edit or add a new result to a game.
        Button editResultBtn = new Button();
        editResultBtn.setText("Edit Result");
        editResultBtn.setOnAction(( event) -> {
            game.handleGameResult(game);
        });
        
        // Things are added to the tool-bar and the header.
        tools.getChildren().addAll(editResultBtn);
        BorderPane header = new BorderPane(menu, title, null, null, null);
        body.setTop(header);
        
        // Input from administator under a match.
        
        TextField moveInput = new TextField();
        TextArea moveShow = new TextArea();
        for(String move : game.getScore())
            moveShow.appendText(move + " ");
        
        moveInput.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
               
                Move[] moves;
                moves = Parser.parseLANArray(moveInput.getText());
                ChessBoardModel boardBehind = new ChessBoardModel();
                
                for (Move move : moves) {
                    boardBehind.move(move);
                }
                
                game.addMove(moveInput.getText());
               moveShow.appendText(moveInput.getText());
            }
        });
        
        HBox centerHorizontal = new HBox();
        centerHorizontal.setAlignment(Pos.CENTER);
       
        VBox centerVertical = new VBox(moveShow, moveInput);
        centerVertical.setAlignment(Pos.CENTER);
        
        StackPane main = new StackPane(centerVertical);
        body.setCenter(main);
        
        
        return new Page(body);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Used to move the user of the application between the pages when they presses
     * one one of the list-elements.
     * @param observable
     * @param previousTournament
     * @param currentTournament 
     */
    public void chooseTournament(ObservableValue<? extends Tournament> observable, Tournament previousTournament, Tournament currentTournament) {
        System.out.println("Moved from " + previousTournament + "page, to " + currentTournament + "page");
        container.put(lobby(currentTournament));
    }
    
    /**
     * Same as the one above it, but it is used to move between the pages without using
     * a list with an observablelist.
     * @param previousTournament
     * @param currentTournament 
     */
    public void chooseTournament(Tournament previousTournament, Tournament currentTournament) {
        System.out.println("Moved from " + previousTournament + "page, to " + currentTournament + "page");
        container.put(lobby(currentTournament));
    }
    /**
     * The same as the two methods above, but this will move the user of the application 
     * to one of the game-pages when they press one of the items in the game-list.
     * @param observable
     * @param previousGame
     * @param currentGame 
     */
    public void chooseGame(ObservableValue<? extends Game> observable, Game previousGame, Game currentGame) {
        System.out.println("Moved from " + previousGame + "page, to " + currentGame + "page");
        container.put(game(currentGame));
    }
}
