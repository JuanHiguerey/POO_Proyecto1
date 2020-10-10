import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent guiRoot = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Proyecto 1 - Tienda RPG");
        Scene gui = new Scene(guiRoot);
        primaryStage.setScene(gui);
        primaryStage.setResizable(false);

        /*Personaje miPersonaje = new Personaje("Juhi");
        Arma miArma = new Arma("Excalibur");
        Armadura miArmadura = new Armadura("Steel Platebody");
        miArmadura.setDefStat(80);
        miArmadura.setHpStat(1000);
        miArma.setAtkStat(123);
        miArma.setCritStat(99);
        miPersonaje.equiparArma(miArma);
        miPersonaje.equiparArmadura(miArmadura);
        miPersonaje.imprimirPersonaje();*/

        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}