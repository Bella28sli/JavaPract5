package org.example;

import java.util.Scanner;

public class North extends Castle{
    private int furProduction;
    private int deers;

    public North(String name, int walls, int army, int food, int water, int gold, int weapon, int furProduction, int deers) {
        super(name, walls, army, food, water, gold, weapon);
        this.furProduction = furProduction;
        this.deers = deers;
    }

    @Override
    public void repairWalls() {
        if (this.walls < 100 && walls >= 80) walls = 100; //изменена логика
        else if (walls < 80) walls += 20;
        System.out.println("Walls is repaired");
    }

    @Override
    public void buyWeapon() {
        if (gold > 10) {
            gold -= 10;
            weapon += 5;
        } else {
            System.out.println("You don`t have enough money for it");
        }
    }

    @Override
    public int harvest(int amount) {
        food += amount;
        return food;
    }

    @Override
    public void CheckInformation() {
        System.out.println("\n"+name + ":\nWalls's strength: " + walls + "/100\nArmy's size: "+ army + "/100\nFood amount: "+ food + "/100\nWater amount: "+ water + "/100\nGold amount: "+ gold + "/100\nWeapon quality: "+ weapon + "/100\n\nSpecial:\nDeers amount: " + deers + "/100\nFur production level amount: " + furProduction + "/100");
        if(this.isRuined()){
            System.out.println("This castle is ruined");
        }
    }

    @Override
    void Special() {
        System.out.println("\nThis special function can sell fur for increase specific parameter. Continue?\n1.Yes\n2.No");
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        choice = sc.nextLine();
        switch (choice){
            case "1" ->{
                this.furProduction -= 10;
                System.out.println("\nChoose parameter to increase.\n1. Walls\n2. Food\n3. Weapon\n4. Army");
                choice = sc.nextLine();
                switch (choice){
                    case "1"->{
                        this.walls +=15;
                    }
                    case "2" ->{
                        this.food +=15;
                    }
                    case "3" ->{
                        this.weapon +=10;
                    }
                    case "4" ->{
                        this.army+=5;
                    }
                    case null, default -> {
                        System.out.println("\nTry again");
                        Special();
                    }
                }
            }
            case "2" ->{
                System.out.println("\nIt's ok, let's continue ");
            }
            case null, default -> {
                System.out.println("\nTry again");
                Special();
            }
        }
    }
}
