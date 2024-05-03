package org.example;

import java.util.Scanner;

public class West extends Castle {
    private int economyLevel;
    private int culture;

    West(String name, int walls, int army, int food, int water, int gold, int weapon, int culture, int economy) {
        super(name, walls, army, food, water, gold, weapon);
        this.culture = culture;
        this.economyLevel = economy;
    }

    @Override
    void repairWalls() {
        if (this.walls < 100 && walls >= 90) walls = 100; //изменена логика
        else if (walls < 90) walls += 10;
        System.out.println("Walls is repaired");
    }

    @Override
    void buyWeapon() {
        if (gold > 5) {
            gold -= 5;
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
        System.out.println("\n"+name + ":\nWalls's strength: " + walls + "/100\nArmy's size: "+ army + "/100\nFood amount: "+ food + "/100\nWater amount: "+ water + "/100\nGold amount: "+ gold + "/100\nWeapon quality: "+ weapon + "/100\n\nSpecial:\nCulture progress: " + culture + "/100\nEconomy level: " + economyLevel + "/100");
        if(this.isRuined()){
            System.out.println("This castle is ruined");
        }
    }

    @Override
    void Special() {
        System.out.println("\nThis special function can balance the indicators. Continue?\n1.Yes\n2.No");
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        choice = sc.nextLine();
        switch (choice){
            case "1" ->{
                int all = this.culture + this.economyLevel + this.army + this.walls + this.gold + this.food + this.water + this.weapon;
                int oneOf = Math.round(all/8);
                this.culture = oneOf;
                this.economyLevel = oneOf;
                this.army = oneOf;
                this.walls = oneOf;
                this.gold = oneOf;
                this.food = oneOf;
                this.water = oneOf;
                this.weapon = oneOf;
                System.out.println("\nNow all your characteristics are similar and equals " + oneOf);
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
