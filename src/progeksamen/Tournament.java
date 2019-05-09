package progeksamen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tournament implements Serializable {
    
    private String name;
    private ArrayList<Player> players = new ArrayList();
    private ArrayList<Game> games = new ArrayList();

    /**
     * Constructor
     * @param name 
     */
    public Tournament(String name){
        this.name = name;
      
    }
    
    /**
     * Constructor
     * @param name
     * @param players
     * @param games 
     */
    public Tournament(String name, ArrayList<Player> players, ArrayList<Game> games) {
        this.name = name;
        this.players = players;
        this.games = games;
    }
    
    /**
     * Used in the search function in Admapp and Spillerapp.
     * @param input
     * @return 
     */
    public ArrayList<Game> search(String input) { 
        ArrayList<Game> newPlayerList = new ArrayList<Game>();
        for (Game game : this.games) {
            if(game.nameContainString(input)) {
                newPlayerList.add(game);
            }
        }
        return newPlayerList;
    }
    
    /**
     * Gets name
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * The method that sorts 
     */
    public void sortScoreBoard() {
        Comparator c = Comparator.naturalOrder();
        Collections.sort(players, c);
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    /**
     * 
     * @return 
     */
    public String getLeaderBoard() {
         
        sortScoreBoard(); 
        String text ="";
        for (Player p : players)
            text += (players.indexOf(p)+1+".")+p.getName() + " "  + p.getScore()/2 + "\n";
        
        return text;
    }
     
    /**
     * 
     * @return 
     */
    public ArrayList<Game> getGames() {
        return games;
    }
    
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param players 
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    /**
     * 
     * @param games 
     */
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
    
    /**
     * 
     * @return 
     */
    public String getDetails() {
        return "Name : " + this.name + " " + this.players + " Games : " + this.games;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.name;
    }

   
   

}
