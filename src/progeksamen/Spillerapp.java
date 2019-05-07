/*
 * Main
 */
package progeksamen;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
    
    @Override
    public void start(Stage primaryStage) {
        
        ////////////////////////////////////////////////////////////////
        // Root Pane
        StackPane root = new StackPane();
        root.setMinSize(800, 600);
        
        ////////////////////////////////////////////////////////////////
        // Body Panes
        BorderPane tournamentBody = new BorderPane();
        tournamentBody.getStyleClass().add("body");
        
        root.getChildren().add(tournamentBody);
        
        ////////////////////////////////////////////////////////////////
        // Title Pane og Title
        HBox titlePane = new HBox();
        titlePane.setPadding(new Insets(20, 20, 20, 20));
        titlePane.setSpacing(10);
        titlePane.getStyleClass().add("title-pane");
        
        Text title = new Text("Tournaments");
        title.getStyleClass().add("title");
        
        titlePane.getChildren().add(title);
        
        ////////////////////////////////////////////////////////////////
        // Crumb Pane & Crumb
        HBox crumbPane = new HBox();
        crumbPane.setPadding(new Insets(10, 20, 10, 20));
        titlePane.setSpacing(10);
        crumbPane.getStyleClass().add("crumb-pane");
        
        Text crumb = new Text("Tournaments");
        crumb.getStyleClass().add("crumb");
        
        crumbPane.getChildren().add(crumb);
        
        ////////////////////////////////////////////////////////////////
        // Header Pane
        BorderPane header = new BorderPane();
        
        header.setCenter(titlePane);
        header.setBottom(crumbPane);
        
        tournamentBody.setTop(header);
        
        ////////////////////////////////////////////////////////////////
        // List
        ObservableList<Tournament> listItems = FXCollections.<Tournament>observableArrayList(new Tournament());
        
        ListView<Tournament> tournamentList = new ListView<>(listItems);
        tournamentList.getItems().addAll();
        tournamentList.setCellFactory(new TournamentCellFactory());
        tournamentList.setOrientation(Orientation.VERTICAL);
        tournamentList.setFocusTraversable(false);
        tournamentList.getSelectionModel().selectedItemProperty().addListener(this::tournamentChanged);
        
        
        ////////////////////////////////////////////////////////////////
        // Main Pane
        StackPane main = new StackPane();
        main.getStyleClass().add("main");
        
        main.getChildren().add(tournamentList);
        
        tournamentBody.setCenter(main);
        
        ////////////////////////////////////////////////////////////////
        // Scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add("progeksamen/main.css");
        
        ////////////////////////////////////////////////////////////////
        // PrimaryStage
        primaryStage.setTitle("ChessX");
        primaryStage.setScene(scene);
        primaryStage.show();
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void tournamentChanged(ObservableValue<? extends Tournament> observable, Tournament oldValue, Tournament newValue) {
        System.out.println("Value changed: old = " + oldValue + ", new = " + newValue);
    }
    
}
