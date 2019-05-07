package progeksamen;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    List<Player> players = new ArrayList();
    List<Game> games = new ArrayList();
    List<Result> results = new ArrayList();
    
    String name;

    //Player player;
    public Tournament(String name){
        this.name = name;
      
    }
    
    public Tournament(String name,List players, List games, List results) {
        this.name = name;
        this.players = players;
        this.games = games;
        this.results = results;

    }   

    @Override
    public String toString() {
        return "Name : " + this.name + " " + this.players + " Games : " + this.games + " Winners : " + this.results;
    }

}
