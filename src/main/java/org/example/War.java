package org.example;

public class War {
    public Castle opponent1;
    public Castle opponent2;
    public Boolean IsActive;

    public void EndTheWar(){
        Castle winner = null;
        Castle loser = null;
        if (this.opponent1.getStrength() > this.opponent2.getStrength()){
            winner = this.opponent1;
            loser = this.opponent2;
        }
        else if (this.opponent1.getStrength() < this.opponent2.getStrength()){
            winner = this.opponent2;
            loser = this.opponent1;
        }
        else{
            this.opponent1.army -=15;
            this.opponent1.weapon -=10;
            this.opponent1.food -=10;
            this.opponent1.water -=5;
            this.opponent1.gold -=15;
            this.opponent1.walls -=30;

            opponent2.army -=15;
            opponent2.weapon -=10;
            opponent2.food -=10;
            opponent2.water -=5;
            opponent2.gold -=15;
            opponent2.walls -=30;
        }
        System.out.println(winner.name + " is a winner");
        winner.army -= 10;
        winner.weapon -= 5;
        winner.gold +=20;
        winner.food +=15;
        winner.water +=5;

        loser.army -=30;
        loser.weapon -=25;
        loser.food -=20;
        loser.water -=5;
        loser.gold -=25;
        loser.walls -=50;
        this.IsActive = false;
    }
}
