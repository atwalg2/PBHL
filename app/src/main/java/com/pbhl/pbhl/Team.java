package com.pbhl.pbhl;

import java.util.ArrayList;

/**
 * Created by Home on 2017-04-23.
 */

public class Team {
    private int wins, loss, tie, OTL, P, GF, GA, GD, SF, SFPG, SA, SAPG, PIM;
    String name;
    ArrayList<String> schedule;

    public Team(String name) {
        this.name = name;
    }

    // ---      SETTERS        ---
    public void setWLT(int wins, int loss, int tie){
        this.wins = wins;
        this.loss = loss;
        this.tie = tie;
        this.OTL = tie;

        this.P = (this.wins*2) + (this.tie);
    }

    public void setGoals(int GF, int GA, int GD){
        this.GF = GF;
        this.GA = GA;
        this.GD = GD;

    }

    public void setShots(int SF, int SA, int SFPG, int SAPG){
        this.SF = SF;
        this.SA = SA;
        this.SFPG = SFPG;
        this.SAPG = SAPG;
    }

    public void setPIM(int PIM){
        this.PIM = PIM;
    }





    // ---     GETTERS    ---
    public String getTeamName(){
        return this.name;
    }

    public int getWins(){
        return wins;
    }

    public int getLoss(){
        return loss;
    }

    public int getTie(){
        return tie;
    }

}
