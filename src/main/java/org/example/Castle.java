package org.example;

import java.util.ArrayList;

abstract class Castle {
    Castle(String name, int walls, int army, int food, int water, int gold, int weapon){
        this.name = name;
        this.walls = walls;
        this.army = army;
        this.food = food;
        this.water = water;
        this.gold = gold;
        this.weapon = weapon;
    }
    protected String name;
    protected int walls;
    protected int army;
    protected int food;
    protected int water;
    protected int gold;
    protected int weapon;
    protected ArrayList<War> wars = new ArrayList<War>();
    abstract void repairWalls();
    abstract void buyWeapon();
    abstract int harvest(int amount);
    abstract void CheckInformation();
    abstract void  Special();

    public int getFood() {
        return this.food;
    }
    public void allMyEnemies() { //подстроено под новый класс -> сильно изменена логика
        if (!(wars.isEmpty()) ) {
            for(War war: wars){
                if(war.IsActive){
                    String opponent;
                    if (war.opponent2 != this){
                        opponent = war.opponent2.name;
                    }
                    else {
                        opponent = war.opponent1.name;
                    }
                    System.out.println(opponent);
                }
            }
        }
        else System.out.println("U don`t have enemies");
    }
    public int getStrength() { //static
        return walls * army * weapon;
    }
    public Boolean isRuined() { //static
        if (walls == 0 && army == 0 && weapon == 0) {
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }
}
