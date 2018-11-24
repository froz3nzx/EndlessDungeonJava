package simpler;

//work in progress
public final class Stats {
    public static int damageDealt = 0;
    public static int damageSuffered = 0;
    public static int hPPotsDrank = 0;
    public static int attacksDone = 0;
    public static int expGained = 0;

    private Stats(){}

    public static void displayStats(){
        System.out.println("***************Player Stats***************");
        System.out.println("Damage Dealt: " + damageDealt);
        System.out.println("Damage Suffered: " + damageSuffered);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Hp Pots Drank: " + hPPotsDrank);
        System.out.println("Attacks done: " + attacksDone);
        System.out.println("Exp Gained: : " + expGained);
        System.out.println("***************Player Stats***************");
    }
}
