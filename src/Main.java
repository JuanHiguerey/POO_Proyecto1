public class Main {
    public static void main(String[] args){
        Personaje miPersonaje = new Personaje("Juhi");
        Arma miArma = new Arma("Excalibur");
        Armadura miArmadura = new Armadura("Steel Platebody");
        miArmadura.setDefStat(80);
        miArmadura.setHpStat(1000);
        miArma.setAtkStat(123);
        miArma.setCritStat(99);
        miPersonaje.equiparArma(miArma);
        miPersonaje.equiparArmadura(miArmadura);
        miPersonaje.imprimirPersonaje();
    }
}
