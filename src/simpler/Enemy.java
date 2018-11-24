package simpler;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Entity implements Droppable {

    public Enemy(String name) {
        super(name);
    }

    //constructor for creating always a different monster from the precedent
    public Enemy(String name, int level) {
        super(name);
        setLevel(level);
        //calculations for generating different monsters
        addExperience((level * ThreadLocalRandom.current().nextInt(level, level + 3)));
        setHealth((level * 5) + (ThreadLocalRandom.current().nextInt(1, level + 2) * 3));
        setAttack(getLevel() + 3);
    }


    @Override
    public void attack(Entity monster, Entity player) {
        // TODO Auto-generated method stub
        super.attack(monster, player);

    }


    @Override
    public void dropPotion(Enemy e) {
        //calculates random chance to drop a potion
        int chance = ThreadLocalRandom.current().nextInt(0, 200);
        if ((chance <= 30) && (chance > 10)) { //chance to drop hp pot
            System.out.println("<<" + e.getName() + " drops an HP Potion>>");
            Consumables.addHpPot();
        }
        if (chance <= 10) { //smaller chance to drop att potion
            System.out.println("<<" + e.getName() + " drops an Attack Potion>>");
            Consumables.addAttPot();

        }

    }
}
