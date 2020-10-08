public class Consumible extends Item {
    protected int hpStat;
    protected int mpStat;

    public Consumible(String nombreConsumible) {
        super(nombreConsumible);
    }

    public void setHpStat(int valorHp) {
        hpStat = valorHp;
    }
    public void setMpStat(int valorMp) {
        mpStat = valorMp;
    }
}
