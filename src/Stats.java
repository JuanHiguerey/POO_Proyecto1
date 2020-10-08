public class Stats {
    protected int HP;
    protected int MP;
    protected int ATK;
    protected int DEF;
    protected int CRIT;

    public Stats() {
        HP = 100;
        MP = 20;
        ATK = 12;
        DEF = 10;
        CRIT = 1;
    }

    public void setHP(int nuevoHP) {
            HP = nuevoHP;
    }
    public void setMP(int nuevoMP) {
            MP = nuevoMP;
    }
    public void setATK(int nuevoATK) {
            ATK = nuevoATK;
    }
    public void setDEF(int nuevoDEF) {
            DEF = nuevoDEF;
    }
    public void setCRIT(int nuevoCRIT) {
            CRIT = nuevoCRIT;
    }

    public void imprimirStats() {
        System.out.print("HP: ");
        System.out.println(HP);
        System.out.print("MP: ");
        System.out.println(MP);
        System.out.print("ATK: ");
        System.out.println(ATK);
        System.out.print("DEF: ");
        System.out.println(DEF);
        System.out.print("CRIT: ");
        System.out.println(CRIT);
    }
}

