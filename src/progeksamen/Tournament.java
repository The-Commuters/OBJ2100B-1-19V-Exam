package progeksamen;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    String name;
    List<Player> players = new ArrayList();
    List<Game> games = new ArrayList();
    List<Result> results = new ArrayList();
    

    // Constructors
    public Tournament(String name){
        this.name = name;
      
    }
    
    public Tournament(String name,List players, List games, List results) {
        this.name = name;
        this.players = players;
        this.games = games;
        this.results = results;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Result> getResults() {
        return results;
    }
    
    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
    
    // Methods
    public String getDetails() {
        return "Name : " + this.name + " " + this.players + " Games : " + this.games + " Winners : " + this.results;
    }
    

    @Override
    public String toString() {
        return this.name;
    }

}
