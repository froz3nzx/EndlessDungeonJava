package simpler;

import java.util.concurrent.ThreadLocalRandom;

public class Player extends Entity {
    private int[] nextLevelUp = {PlayerLevels.LVL2, PlayerLevels.LVL3, PlayerLevels.LVL4, PlayerLevels.LVL5};
    private int currLvlCounter = 0;

    Player(String name) {
        super(name);
        setHealth(100);
        setAttack(getLevel() + 5);
    }

    @Override
    public void attack(Entity player, Entity monster) {
        super.attack(player, monster);
        //checks if the victim (monster) is died and assigns exp to the player
        if (!monster.isAlive(monster)) {
            System.out.println("<<" + player.getName() + " gains " + monster.getExperience() + " EXP.>>");
            player.addExperience(monster.getExperience());
            System.out.println("Player exp: " + player.getExperience());
            canLevelUp((Player) player);
        }
    }

    //increases the player's level and increases randomly the attack
    private void levelUp() {
        int rand = ThreadLocalRandom.current().nextInt(1, 5);
        setLevel(getLevel() + 1);
        setAttack(getAttack() + rand);

        System.out.println(getName() + " has levelled up! +" + rand + " att.");
    }

    //checks if player can level up and calls levelUP()
    public void canLevelUp(Player p) {
        int currentExp = getExperience();
        if (currLvlCounter == nextLevelUp.length - 1) {
            System.out.println("**You've reached the max level. How far will you resist?**");
        } else if (currentExp >= nextLevelUp[currLvlCounter]) {
            levelUp();
            currLvlCounter++;
        }
    }

    //displays the player's statistics
    public void displayStats() {
        System.out.println("**********Player stats**********");
        System.out.println("PlayerLevels: " + getLevel());
        System.out.println("Exp: " + getExperience());
        System.out.println("Attack: " + getAttack());
        System.out.println("Total damage dealt: " + Stats.damageDealt);
        System.out.println("******************************");
    }
}
