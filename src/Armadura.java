public class Armadura extends Item {
    protected int defStat;
    protected int hpStat;
    protected int mpStat;

    public Armadura(String nombreArmadura) {
        super(nombreArmadura);
    }

    public void setDefStat(int valorDef) {
        defStat = valorDef;
    }
    public void setHpStat(int valorHp) {
        hpStat = valorHp;
    }
    public void setMpStat(int valorMp) {
        mpStat = valorMp;
    }
}
