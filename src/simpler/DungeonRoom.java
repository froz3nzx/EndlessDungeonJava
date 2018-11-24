package simpler;

import java.util.concurrent.ThreadLocalRandom;

//contains a list of the monsters name and each new dungeonRoom contains (creates) a new random enemy
public class DungeonRoom {
    private static final String[] enemies = {"Skeleton Warrior", "Mighty Knight",
            "Undead Wizard", "Zombie", "Evil Priest",
            "Slime Warrior", "Baby Dragon", "Flying Golem"};
    private static final String bossSuffix = " Boss";

    private static int floor = 0;
    private int currentFloor = 0;
    private int enemyLevel;
    private Enemy e;

    //for each new room creates an enemy
    DungeonRoom() {
        //goes to next floor
        floor++;
        currentFloor = floor;
        //if current floor is a multiple of 5 generate a boss enemy
        if (currentFloor > 0 && currentFloor % 5 == 0) {
            int rand = ThreadLocalRandom.current().nextInt(0, enemies.length);
            //generate a stronger enemy, that is the boss of every 5th room
            enemyLevel = ThreadLocalRandom.current().nextInt(currentFloor + 2, currentFloor + 5);
            e = new Enemy(enemies[rand] + bossSuffix, enemyLevel);
        } else {
            //else generate a normal enemy
            int rand = ThreadLocalRandom.current().nextInt(0, enemies.length);
            //currentfloor (enemyLVL) ==> 1 (1-3) 2 (2-5) 3 (3-6) etc..
            enemyLevel = ThreadLocalRandom.current().nextInt(currentFloor, currentFloor + 3);
            e = new Enemy(enemies[rand], enemyLevel);
        }
    }

    public void setFloor(int floor) {
        currentFloor = floor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Enemy getEnemy() {
        if (e != null)
            return e;
        else {
            System.out.println("NULL");
            return null;
        }
    }
}
