public class Personaje {
    private String nombre;
    private Stats stats = new Stats();

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
        incATK(arma.atkStat);
        incCRIT(arma.critStat);
        incMP(arma.mpStat);
    }
    public void equiparArmadura(Armadura armadura) {
        incDEF(armadura.defStat);
        incHP(armadura.hpStat);
        incMP(armadura.mpStat);
    }
    public void equiparConsumible(Consumible consumible) {
        incHP(consumible.hpStat);
        incMP(consumible.mpStat);
    }
    public  void desequiparArma(Arma arma) {
        incATK(-arma.atkStat);
        incCRIT(-arma.critStat);
        incMP(-arma.mpStat);
    }
    public void desequiparArmadura(Armadura armadura) {
        incDEF(-armadura.defStat);
        incHP(-armadura.hpStat);
        incMP(-armadura.mpStat);
    }

    public void imprimirPersonaje() {
        System.out.println(nombre);
        stats.imprimirStats();
    }
}