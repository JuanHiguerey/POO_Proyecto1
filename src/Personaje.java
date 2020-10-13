public class Personaje {
    protected String nombre;
    protected Stats stats = new Stats();
    protected Stats baseStats = new Stats();
    protected int dinero = 3_000;
    protected Inventario inventario = new Inventario();
    protected Arma armaEquipada;
    protected Armadura armaduraEquipada;

    public Personaje(String nombrePersonaje) {
        nombre = nombrePersonaje;
    }

    public void incHP(int HP) {
        stats.setHP(stats.HP + HP);
    }
    public void incMP(int MP) {
        stats.setMP(stats.MP + MP);
    }
    public void incATK(int ATK) {
        stats.setATK(stats.ATK + ATK);
    }
    public void incDEF(int DEF) {
        stats.setDEF(stats.DEF + DEF);
    }
    public void incCRIT(int CRIT) {
        stats.setCRIT(stats.CRIT + CRIT);
    }

    public void equiparArma(Arma arma) {
        if(armaEquipada != null) { desequiparArma(); }
        incATK(arma.atkStat);
        incCRIT(arma.critStat);
        incMP(arma.mpStat);
        armaEquipada = arma;
    }
    public void equiparArmadura(Armadura armadura) {
        if(armaduraEquipada != null) { desequiparArmadura(); }
        incDEF(armadura.defStat);
        incHP(armadura.hpStat);
        incMP(armadura.mpStat);
        armaduraEquipada = armadura;
    }
    public void equiparConsumible(Consumible consumible) {
        incHP(consumible.hpStat);
        incMP(consumible.mpStat);
        baseStats.setHP(baseStats.HP + consumible.hpStat);
        baseStats.setMP(baseStats.MP + consumible.mpStat);
    }
    public  void desequiparArma() {
        if(armaEquipada != null) {
            incATK(-armaEquipada.atkStat);
            incCRIT(-armaEquipada.critStat);
            incMP(-armaEquipada.mpStat);
            armaEquipada = null;
        }
    }
    public void desequiparArmadura() {
        if(armaduraEquipada != null) {
            incDEF(-armaduraEquipada.defStat);
            incHP(-armaduraEquipada.hpStat);
            incMP(-armaduraEquipada.mpStat);
            armaduraEquipada = null;
        }
    }

    public void imprimirPersonaje() {
        System.out.println(nombre);
        stats.imprimirStats();
    }

    public void setDinero(int Dinero) {
        dinero += Dinero;
    }
}