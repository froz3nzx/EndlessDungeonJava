package simpler;

public class Consumables {
    private static final int HP_POT_VALUE = 100;
    private static final int ATTACK_POT_VALUE = 3;
    private static int hpQty = 3;
    private static int attQty = 0;

    public static int getHpQty() {
        return hpQty;
    }

    public static int getAttQty() {
        return attQty;
    }


//	private static HashMap<HashMap<String, Integer>, Integer> consumables;
//	private static HashMap<String, Integer> hp;
//	
//	Consumables(){
//		consumables = new HashMap<>();
//		hp = new HashMap<>();
//		hp.put("HP Potion", HP_POT);
//		consumables.put(hp, 3); // (type, qty)
//	}


    public static void addHpPot() {
        hpQty++;
    }

    //returns the hp amount to drink
    public static int drinkHP_Pot() {
        if (hpQty > 0) {
            System.out.println("<<You drank an HP Potion.>>");
            hpQty--;
            return HP_POT_VALUE;
        } else {
            System.out.println("<<No more HP Potions left!>>");
            return 0;
        }
    }

    public static void addAttPot() {
        attQty++;
    }

    public static int drinkAttPot() {
        if (attQty > 0) {
            System.out.println("<<You drank an Attack Potion.>>");
            attQty--;
            return ATTACK_POT_VALUE;
        } else {
            System.out.println("<<No more Attack Potions left!>>");
            return 0;
        }
    }

}
