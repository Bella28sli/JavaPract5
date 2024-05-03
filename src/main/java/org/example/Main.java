package org.example;
import java.security.PublicKey;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static North NorthCastle = new North("North Castle", 100, 80, 60, 80, 80, 80 , 100, 100);
    static West WestCastle = new West("West Castle",  80, 60, 60, 80, 90, 100, 100, 100);
    static East EastCastle = new East("East Castle",  80, 100, 70, 70, 70, 90, 100, 100);
    static South SouthCastle = new South("South Castle", 80, 90, 100, 60, 60, 70, 100, 100);
    static public ArrayList<Castle> castles = new ArrayList<>() {{
        add(NorthCastle);
        add(WestCastle);
        add(EastCastle);
        add(SouthCastle);
    }};

    public static void main(String[] args) {
        ChooseCastle();
    }

    static void ChooseCastle(){
        System.out.println("\nMy compliments, Your Majesty. Choose your castle, please.\nYou can change it later");
        System.out.println("""
                
                0. Check detailed description about castles\s
                1. North Castle\s
                2. South Castle\s
                3. West Castle\s
                4. East Castle""");
        String choice;
        Castle currentCastle = null;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        choice = sc.nextLine();
        switch (choice) {
            case "0" -> {
                for(Castle castle : castles){
                    castle.CheckInformation();
                }
                ChooseCastle();
            }
            case "1" -> { currentCastle = NorthCastle;
            }
            case "2" -> { currentCastle = SouthCastle;
            }
            case "3" -> { currentCastle = WestCastle;
            }
            case "4" -> { currentCastle = EastCastle;
            }
            case null, default -> {
                System.out.println("This castle is not exists\n");
                ChooseCastle();
            }
        }
        assert currentCastle != null;
        System.out.println("\nNow " + currentCastle.name + " is your castle!\nHave a good game\n");
        Game(currentCastle);
    }
    static void  NewGame(){
        NorthCastle = new North("North Castle", 100, 80, 60, 80, 80, 80 , 100, 100);
        WestCastle = new West("West Castle",  80, 60, 60, 80, 90, 100, 100, 100);
        EastCastle = new East("East Castle",  80, 100, 70, 70, 70, 90, 100, 100);
        SouthCastle = new South("South Castle", 80, 90, 100, 60, 60, 70, 100, 100);
        castles.clear();
        castles = new ArrayList<>() {{
            add(NorthCastle);
            add(WestCastle);
            add(EastCastle);
            add(SouthCastle);
        }};
        for (Castle castle : castles){
                castle.wars.clear();
        }
        ChooseCastle();
    }
    static void Game(Castle castle){
        Main main = new Main();
        System.out.println(""" 
                
                What do You want to do now, Your Majesty?
                
                0. Check the condition of the castle\s
                1. Harvest\s
                2. Check the amount of food\s
                3. Repair walls\s
                4. Buy weapon\s
                5. Find the weakest castle\s
                6. Check strength of the castle\s
                7. Check all enemies of the castle\s
                8. Declare war to castle\s
                9. Declare war to all castles!\s
                10. End the war\s
                
                11. Special function\s
                
                12. Change current castle and continue (all castles will RETAIN their current characteristics)\s
                13. Start new game (all castles will be repaired and RETURN TO DEFAULT characteristics)""");
        String choice;
        Scanner sc = new Scanner(System.in); // подключаем сканер
        choice = sc.nextLine();
        switch (choice){
            case "0" -> castle.CheckInformation();
            case "1" -> {
                try {
                    System.out.println("\nEnter the number of fields from which you want to harvest");
                    int amount = sc.nextInt(); sc.nextLine();
                    System.out.println("Your amount of food is " + castle.harvest(amount) + " now");
                }
                catch (InputMismatchException e) {
                    System.out.println("This isn't a number");
                }
            }
            case "2" -> { System.out.println("\nYour amount of food is " + castle.getFood() + " now");}
            case "3" -> { castle.repairWalls(); System.out.println("\nCheck condition of the castle to see the changes");}
            case "4" -> { castle.buyWeapon(); System.out.println("\nCheck condition of the castle to see the changes");}
            case "5" -> {System.out.println("\nThe weakest castle is " + findWeakCastle(castles));}
            case "6" -> { System.out.println("\nYour strength is " + castle.getStrength() + " now");}
            case "7" -> castle.allMyEnemies();
            case "8" -> {
                System.out.println("\nEnter the name of the castle");
                choice = sc.nextLine();
                if(!Objects.equals(choice, castle.name)){
                    main.declareWar(choice, castle);
                }
                else {
                    System.out.println("You can't declare war to yourself");
                }
            }
            case "9" -> {
                main.declareWarToAll(castle);
                System.out.println("You have declared war to all castles!");
            }
            case "10" ->{
                System.out.println("Those are your enemies:");
                castle.allMyEnemies();
                if (!(castle.wars.isEmpty())){
                    System.out.println("With which enemy you want to reconcile? Enter the name of the castle");
                    String enemy = sc.nextLine();
                    for (War war : castle.wars){
                        if(war.IsActive && (!Objects.equals(enemy, castle.name) &&
                                (Objects.equals(enemy, war.opponent2.name) || (Objects.equals(enemy, war.opponent1.name))
                        ))) war.EndTheWar();
                    }
                }
                castle.CheckInformation();
            }
            case "11"->{
                castle.Special();
            }
            case "12" -> {
                ChooseCastle();
            }
            case "13" -> {
                System.out.println("Are you sure?\n1. No\n2. Yes");
                choice = sc.nextLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("It's ok! Let's continue");
                    }
                    case "2" -> {
                        System.out.println("It was good game. This is your results:");
                        for(Castle oneOfcastle : castles){
                            oneOfcastle.CheckInformation();
                        }
                        NewGame();
                    }
                    case null, default -> {
                        System.out.println("There is no such option");
                    }
                }
            }
            case null, default -> {System.out.println("There is no such option");}
        }
        if (castle != null){
            Game(castle);
        }
    }


    public static String findWeakCastle(ArrayList<Castle> castles) {
        int weakest = 101;
        Castle weakCastle = castles.getFirst();
        for (Castle castle : castles) {
            if (castle.army < weakest) {
                weakCastle = castle;
                weakest = castle.army; //добавлено по договорённости
            }
        }
        return weakCastle.name;
    }
    public void declareWar(String to, Castle thisCastle) { //подстроено под новый класс -> сильно изменена логика
        for (Castle castle : castles){
            if (Objects.equals(castle.name, to)) {
                for (War war : thisCastle.wars){
                    if(Objects.equals(war.opponent1.name, castle.name) || Objects.equals(war.opponent2.name, castle.name)){
                        war.EndTheWar();
                    }
                }
                War newWar = new War();
                newWar.opponent1 = thisCastle;
                newWar.opponent2 = castle;
                newWar.IsActive = true;
                castle.wars.add(newWar);
                thisCastle.wars.add(newWar);
                System.out.printf("War is declare to %s\n", to);
                return;
            }
        }
        System.out.println("This castle is not exists");
    }
    public void declareWarToAll(Castle thisCastle) { //подстроено под новый класс -> сильно изменена логика
        for (War war : thisCastle.wars){
            war.EndTheWar();
        }
        for (Castle castle : castles) {
            if (castle != thisCastle) {
                War newWar = new War();
                newWar.opponent1 = thisCastle;
                newWar.opponent2 = castle;
                newWar.IsActive = true;
                thisCastle.wars.add(newWar);
                castle.wars.add(newWar);
            }
        }
    }
}
