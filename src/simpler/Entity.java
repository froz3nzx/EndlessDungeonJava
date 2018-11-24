package simpler;

import java.util.concurrent.ThreadLocalRandom;

//represents a basic entity
public abstract class Entity {
    private String name;
    private int health;
    private int level;
    private int attack;
    private int experience;

    Entity(String name) {
        this.name = name;
        level = 1;
    }

    public void addExperience(int exp) {
        experience += exp;
    }

    public int getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void restoreHealth(int amount){
        health += amount;
        if(health > 100)
            health = 100;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

//	public abstract void attack(Entity attacker, Entity victim);
//	}

    public void attack(Entity attacker, Entity victim) {
        int damage = 0;
        int rand = ThreadLocalRandom.current().nextInt(1, attacker.getLevel() + 5);
        damage += (attack + rand);
        Stats.damageDealt += damage;
        victim.setHealth(victim.getHealth() - damage);
        System.out.println(attacker.getName() + " did " + damage + " damage to " + victim.getName() + " lvl " + victim.getLevel());


    }

    public boolean isAlive(Entity e) {
        if (e.getHealth() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        return "<- " + getName() + " | LVL: " + getLevel() + " | HP: " + getHealth() + " ->";
    }
}
