package org.example;

import java.util.Scanner;

public class East extends Castle{
    private int scienceLevel;
    private int demography;

    East(String name, int walls, int army, int food, int water, int gold, int weapon, int scienceLevel, int demography) {
        super(name, walls, army, food, water, gold, weapon);
        this.scienceLevel = scienceLevel;
        this.demography = demography;
    }

    @Override
    void repairWalls() {
        if (this.walls < 100 && walls >= 85) walls = 100; //изменена логика
        else if (walls < 85) walls += 15;
        System.out.println("Walls is repaired");
    }

    @Override
    void buyWeapon() {
        if (gold > 10) {
            gold -= 10;
            weapon += 5;
        } else {
            System.out.println("You don`t have enough money for it");
        }
    }

    @Override
    int harvest(int amount) {
        food += (int) Math.round(amount*1.2);
        return food;
    }

    @Override
    void CheckInformation() {
        System.out.println("\n"+name + ":\nWalls's strength: " + walls + "/100\nArmy's size: "+ army + "/100\nFood amount: "+ food + "/100\nWater amount: "+ water + "/100\nGold amount: "+ gold + "/100\nWeapon quality: "+ weapon + "/100\n\nSpecial:\nDemography level: " + demography + "/100\nScience level: " + scienceLevel + "/100");
        if(this.isRuined()){
            System.out.println("This castle is ruined");
        }
    }

    @Override
    void Special() {
        System.out.println("\nThis special function can use demography for build walls, make weapon or increase army. Continue?\n1.Yes\n2.No");
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        choice = sc.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.println("\nChoose parameter to increase.\n1. Walls\n2. Weapon\n3. Army");
                choice = sc.nextLine();
                switch (choice) {
                    case "1" -> {
                        this.walls += (int) Math.round(this.demography * 0.5);
                    }
                    case "2" -> {
                        this.weapon += (int) Math.round(this.demography * 0.7);
                    }
                    case "3" -> {
                        this.army += this.demography;
                    }
                    case null, default -> {
                        System.out.println("\nTry again");
                        Special();
                    }
                }
            }
            case "2" -> {
                System.out.println("\nIt's ok, let's continue ");
            }
            case null, default -> {
                System.out.println("\nTry again");
                Special();
            }
        }
    }
}
