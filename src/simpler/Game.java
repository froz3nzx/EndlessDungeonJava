package simpler;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static ArrayList<DungeonRoom> rooms;
    private static Player player;
    private static Scanner sc = new Scanner(System.in);
    private static Enemy enemy;

    public static void main(String[] args) {
        System.out.println("<Dungeon Slayer v0.0.1>");
        play();
        System.out.println("****Thanks for playing****");
    }

    //instantiates the entities
    private static void init(String playerName) {
        player = new Player(playerName);
        rooms = new ArrayList<>();
    }

    //create a room and return the current floor
    private static int nextRoom() {
        DungeonRoom dg = new DungeonRoom();
        rooms.add(dg);
        return dg.getCurrentFloor();
    }

    //the core loop of the game
    private static void play() {
        System.out.println("*******************************");
        System.out.println(">Input the name of the player:");
        init(sc.nextLine());

        boolean isPlaying = true;
        do {
            switch (gameMenu(sc)) {
                case 1:
                    isPlaying = combatMenu(sc);
                    break;
                case 2:
                    inventoryMenu();
                    break;
                case 3:
                    player.displayStats();
                    break;
                case 0:
                    isPlaying = false;
                    break;
            }
        } while (isPlaying);


    }

    private static void inventoryMenu() {
        boolean exit = true;
        //shows the inventory and asks if to drink a potion
        do {
            System.out.println("***Your inventory***");
            System.out.println("-" + Consumables.getHpQty() + " HP Potions left.");
            System.out.println("-" + Consumables.getAttQty() + " Attack Potions left.");
            System.out.println(">Drink a potion? [y]/[n]");
            String choice = sc.nextLine().toLowerCase();
            do {
                if (!choice.contains("y") && !choice.contains("n")) {
                    System.out.println("Enter a valid choice!");
                    choice = sc.nextLine().toLowerCase();
                } else break;
            } while (true);

            //select what to drink
            if (choice.contains("y")) {
                int potToDrink;
                exit = false;
                System.out.println(">Which potions do you want to drink? ([0] go back)");
                System.out.println("[1]HP [2]Attack");
                potToDrink = Integer.parseInt(sc.nextLine());

                for(;potToDrink < 0 || potToDrink > 2;){
                    System.out.println("Select a valid potion!");
                    potToDrink = Integer.parseInt(sc.nextLine());
                }


                switch (potToDrink) {
                    case 1:
                        player.restoreHealth(Consumables.drinkHP_Pot());
                        break;
                    case 2:
                        player.setAttack(player.getAttack() + Consumables.drinkAttPot());
                        break;
                }
            } else
                exit = true;

        } while (!exit);
    }

    static boolean combatMenu(Scanner sc) {
        int currentFloor = nextRoom() - 1;
        System.out.println("**You're at floor " + (currentFloor + 1) + "**");
        System.out.print("You encountered: ");
        enemy = rooms.get(currentFloor).getEnemy();

        System.out.println(enemy.toString());
        int choice;
        boolean escape = false;
        //show combatmenu till someone dies
        do {
            //combat or run
            do {
                System.out.println(">What do you do?");
                System.out.println("[1]Combat [2]Run away");
                choice = Integer.parseInt(sc.nextLine());
                if (choice < 0 || choice > 2) {
                    System.out.println("Incorrect choice!");
                    choice = Integer.parseInt(sc.nextLine());
                }

                if (choice == 1) {
                    player.attack(player, enemy);
                    if (!enemy.isAlive(enemy))
                        break;
                    enemy.attack(enemy, player);
                } else if (choice == 2) {
                    escape = true;
                    break;
                }
            } while (choice < 0 || choice > 2);

            if (escape) {
                System.out.println("<<You escaped the battle.>>");
                return true;
            }

            if (!enemy.isAlive(enemy)) {
                break;
            }

            System.out.println(player.toString());
            System.out.println(enemy.toString());
        } while (player.isAlive(player));

        if (!player.isAlive(player)) {
            System.out.println(enemy.getName() + " strikes a fatal attack, you die.");
            System.out.println("--Game Over--");
            return false; //end the game
        } else {
            enemy.dropPotion(enemy);
            System.out.println("<<You defeated the enemy, you can continue to the next room.>>");
            return true;
        }
    }

    static int gameMenu(Scanner sc) {
        System.out.println("\n*******************************");
        System.out.println(player.toString());
        System.out.println("*******************************");
        System.out.println(">What do you want to do?");
        System.out.println("[1]Proceed to the next room");
        System.out.println("[2]Inventory");
        System.out.println("[3]Player stats");

        System.out.println("[0]Exit");
        int choice = -1;
        String in;

        do {
            in = sc.nextLine();
            if (!in.isEmpty()) {
                choice = Integer.parseInt(in);
                if (choice < 0 || choice > 3) {
                    System.out.println("Invalid choice!");
                    choice = Integer.parseInt(in);
                }
            } else {
                System.out.println("Invalid choice!");
            }
        } while (choice < 0 || choice > 3);

        return choice;
    }

}
