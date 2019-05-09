/*
 * Main
 */
package progeksamen;

import gui.chess.ChessSimulator;
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
import javafx.application.Application;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.stage.Stage;
import static progeksamen.Admapp.tournamentList;

public class Spillerapp extends Application {
    
    Container container;
    
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
       
        
        ////////////////////////////////////////////////////////////////
        // PrimaryStage
        primaryStage.setTitle("ChessX");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(container.getMinWidth());
        primaryStage.setMinHeight(container.getMinHeight());
        primaryStage.show();
        
    }
    
    public Page tournament() {
        Body body = new Body("Tournament");
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(body.getName()));
        Crumb crumb = new Crumb("Tournament");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
  
        BorderPane header = new BorderPane(menu, title, null, null, null);
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
        // LIST
        
        StackPane main = new StackPane(list);
        body.setCenter(main);
        
        return new Page(body);
    }
    
    public Page lobby(Tournament tournament) {
        Body body = new Body("Tournament");
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(tournament.getName()));
        title.addButton(new Back(container));
        Crumb crumb = new Crumb("Tournament", "Lobby");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
  
        BorderPane header = new BorderPane(menu, title, null, null, null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main
        
        // LIST
        ListView<Game> list = new ListView<>(FXCollections.<Game>observableArrayList(tournament.getGames()));
        list.setCellFactory(new GameCellFactory());
        list.setOrientation(Orientation.VERTICAL);
        list.setFocusTraversable(false);
        list.getSelectionModel().selectedItemProperty().addListener(this::chooseGame);
        list.getStyleClass().add("list");
        // LIST
        
        StackPane main = new StackPane(list);
        body.setCenter(main);
        
        return new Page(body);
    }
    
    public Page game(Game game) {
        Body body = new Body("Game");
        
        ////////////////////////////////////////////////////////////////
        // Header
        Title title = new Title(new Text(game.getPlayer1().getName() + " VS " + game.getPlayer2().getName()));
        title.addButton(new Back(container));
        Crumb crumb = new Crumb("Tournament", "Lobby", "Game");
        Tools tools = new Tools();
        Menu menu = new Menu(crumb, tools);
  
        BorderPane header = new BorderPane(menu, title, null, null, null);
        body.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // Main
        
        // CHESS SIMULATOR
        ChessSimulator chessSimulator = new ChessSimulator(320, game.getScore());
        // CHESS SIMULATOR
        
        HBox centerHorizontal = new HBox(chessSimulator);
        centerHorizontal.setAlignment(Pos.CENTER);
        
        VBox centerVertical = new VBox(centerHorizontal);
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
    
    public void chooseTournament(ObservableValue<? extends Tournament> observable, Tournament previousTournament, Tournament currentTournament) {
        System.out.println("Moved from " + previousTournament + "page, to " + currentTournament + "page");
        container.put(lobby(currentTournament));
    }
    
    public void chooseGame(ObservableValue<? extends Game> observable, Game previousGame, Game currentGame) {
        System.out.println("Moved from " + previousGame + "page, to " + currentGame + "page");
        container.put(game(currentGame));
    }
}
