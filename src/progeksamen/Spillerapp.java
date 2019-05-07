/*
 * Main
 */
package progeksamen;

import gui.Title;
import gui.Body;
import gui.Crumb;
import gui.Menu;
import gui.Container;
import gui.Page;
import gui.Tools;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Spillerapp extends Application {
    
    Container container;
    
    // Current string for the Tournament, needed to find/create the .dat to store binary And the .txt.
    public static String tournament = "Tournament";
    
    // The list's used to store data.
    public static ArrayList<Player> playerList    = new ArrayList<Player>();
    public static ArrayList<Game> gameList        = new ArrayList<Game>();
    public static ArrayList<Result> resultList    = new ArrayList<Result>();
     public static ArrayList<Tournament> tournamentListA    = new ArrayList<Tournament>();
    
    
    @Override
    public void start(Stage primaryStage) {
        
        // The method that collects the data from the Binary file.
        getTournament();
        
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
        // List
        ObservableList<Tournament> listItems = FXCollections.<Tournament>observableArrayList(getList());
        
        ////////////////////////////////////////////////////////////////
        // Main
        ObservableList<Tournament> listItems = FXCollections.<Tournament>observableArrayList(new Tournament("Pokemon PVP: Killfuck"), new Tournament("League of Factoria: PVP"));
        
        // LIST
        ListView<Tournament> list = new ListView<>(listItems);
        list.getItems().addAll();
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
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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
                 tournamentListA = (ArrayList<Tournament>) input.readObject();
                
            } catch (ClassCastException | ClassNotFoundException e){
            }
        } catch (IOException ex) {}
    }
    
     public List<Tournament> getList(){
      return tournamentListA;
  }
    public void chooseTournament(ObservableValue<? extends Tournament> observable, Tournament oldValue, Tournament newValue) {
        System.out.println("Moved from " + oldValue + "page, to " + newValue + "page");
        container.put(tournament());
    }
}
