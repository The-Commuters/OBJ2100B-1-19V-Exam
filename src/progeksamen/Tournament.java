package progeksamen;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    List<Player> players = new ArrayList();
    List<Game> games = new ArrayList();
    List<Result> results = new ArrayList();

    //Player player;
    public Tournament(List players, List games, List results) {
        this.players = players;
        this.games = games;
        this.results = results;

    }   

    @Override
    public String toString() {
        return this.players + " Games : " + this.games + " Winners : " + this.results;
    }

}
