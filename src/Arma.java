public class Arma extends Item {
    protected int atkStat;
    protected int critStat;
    protected int mpStat;

    public Arma(String nombreArma) {
        super(nombreArma);
    }

    public void setAtkStat(int valorAtk) {
        atkStat = valorAtk;
    }
    public void setCritStat(int valorCrit) {
        critStat = valorCrit;
    }
    public void setMpStat(int valorMp) {
        mpStat = valorMp;
    }
}