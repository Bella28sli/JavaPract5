package org.example;

import java.util.Scanner;

public class South extends Castle{
    private int religionLevel;
    private int pastures;

    public South(String name, int walls, int army, int food, int water, int gold, int weapon, int religionLevel, int pastures) {
        super(name, walls, army, food, water, gold, weapon);
        this.religionLevel = religionLevel;
        this.pastures = pastures;
    }

    @Override
    void repairWalls() {
        if (this.walls < 100 && walls >= 90) walls = 100; //изменена логика
        else if (walls < 90) walls += 10;
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
        food += amount * 2;
        return food;
    }

    @Override
    void CheckInformation() {
        System.out.println("\n"+name + ":\nWalls's strength: " + walls + "/100\nArmy's size: "+ army + "/100\nFood amount: "+ food + "/100\nWater amount: "+ water + "/100\nGold amount: "+ gold + "/100\nWeapon quality: "+ weapon + "/100\n\nSpecial:\nReligion level: " + religionLevel + "/100\nPastures amount: " + pastures + "/100");
        if(this.isRuined()){
            System.out.println("This castle is ruined");
        }
    }

    @Override
    void Special() {
        System.out.println("\nThis special function can use pastures for making food or use religion to increase army. Continue?\n1.Yes\n2.No");
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        choice = sc.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.println("\nChoose parameter to increase.\n1. Food\n2. Army");
                choice = sc.nextLine();
                switch (choice) {
                    case "1" -> {
                        this.food += Math.round(pastures/2);
                    }
                    case "2" -> {
                        this.army += (int) Math.round(this.religionLevel * 0.7);
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
