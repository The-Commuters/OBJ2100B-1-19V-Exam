package progeksamen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static progeksamen.Admapp.tournamentList;

public class Tournament implements Serializable {
    
    private ArrayList<Tournament> tournaments;
    private String name;
    private ArrayList<Player> players = new ArrayList();
    private ArrayList<Game> games = new ArrayList();

    // Constructors
    public Tournament(String name){
        this.name = name;
      
    }
    
    public Tournament(String name, ArrayList<Player> players, ArrayList<Game> games) {
        this.name = name;
        this.players = players;
        this.games = games;
    }

    public ArrayList<Game> search(String input) {
        
        ArrayList<Game> newPlayerList = new ArrayList<Game>();
        for (Game game : this.games) {
            if(game.nameContainString(input)) {
                newPlayerList.add(game);
            }
        }
        
        System.out.println(newPlayerList.size());
        return newPlayerList;
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
    
    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Name : " + this.name + " " + this.players + " Games : " + this.games;
    }

}
