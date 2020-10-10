import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class guiController {
    @FXML private Label ArticuloCompra1, ArticuloCompra2, ArticuloCompra3, ArticuloCompra4, ArticuloCompra5, ArticuloCompra6, ArticuloCompra7, ArticuloCompra8;
    @FXML private Label ArticuloCompra9, ArticuloCompra10, ArticuloCompra11, ArticuloCompra12, ArticuloCompra13;

    @FXML private void initialize() {
        Arma miArma = new Arma("Excalibur");

        ArticuloCompra1.setText(miArma.nombre);
        ArticuloCompra2.setText(miArma.nombre);
        ArticuloCompra3.setText(miArma.nombre);
        ArticuloCompra4.setText(miArma.nombre);
        ArticuloCompra5.setText(miArma.nombre);
        ArticuloCompra6.setText(miArma.nombre);
        ArticuloCompra7.setText(miArma.nombre);
        ArticuloCompra8.setText(miArma.nombre);
        ArticuloCompra9.setText(miArma.nombre);
        ArticuloCompra10.setText(miArma.nombre);
        ArticuloCompra11.setText(miArma.nombre);
        ArticuloCompra12.setText(miArma.nombre);
        ArticuloCompra13.setText(miArma.nombre);
    }

    public void onClickItemCompra(MouseEvent mouseEvent) {
    }
}
