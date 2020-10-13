import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class guiController {
    @FXML private Label ArticuloCompra1, ArticuloCompra2, ArticuloCompra3, ArticuloCompra4, ArticuloCompra5, ArticuloCompra6, ArticuloCompra7, ArticuloCompra8;
    @FXML private Label ArticuloCompra9, ArticuloCompra10, ArticuloCompra11, ArticuloCompra12, ArticuloCompra13, espadaLabel, armaduraLabel;
    @FXML private Label hpLabel, mpLabel, atkLabel, defLabel, critLabel, mpLabel2, atkLabel2, critLabel2, hpLabel2, defLabel2, nombreLabel, dineroLabel;
    @FXML private ImageView mpFlecha, atkFlecha, critFlecha, hpFlecha, defFlecha, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13;
    @FXML private AnchorPane equiparMenu;
    @FXML private Button comprarButton1, comprarButton2, comprarButton3, comprarButton4, comprarButton5, comprarButton6, comprarButton7, comprarButton8, comprarButton9,
            comprarButton10, comprarButton11, comprarButton12, comprarButton13, venta1, venta2, venta3, venta4, venta5, venta6, venta7, venta8, venta9, venta10, venta11, venta12, venta13, siEquipar;
    @FXML private Label ArticuloVenta1, ArticuloVenta2, ArticuloVenta3, ArticuloVenta4, ArticuloVenta5, ArticuloVenta6, ArticuloVenta7, ArticuloVenta8, ArticuloVenta9,
            ArticuloVenta10, ArticuloVenta11, ArticuloVenta12, ArticuloVenta13;
    private ImageView[] imgArray = new ImageView[13];
    private Label[] labelArray = new Label[13];
    private Button[] buttonArray = new Button[13];
    private Arma ultimaArma = new Arma("");
    private Armadura ultimaArmadura = new Armadura("");
    private Consumible ultimoConsumible = new Consumible("");
    private String ultimoItem = "";
    private int contCompras = 0;
    Image imageArma = new Image("iconoArma.jpg");
    Image imageArmadura = new Image("iconoArmadura.jpg");
    Image imageConsumible = new Image("iconoConsumible.png");

    Personaje personaje = new Personaje("John");
    Arma Arma1 = generarArmaJson("armas.json", 0);
    Arma Arma2 = generarArmaJson("armas.json", 1);
    Arma Arma3 = generarArmaJson("armas.json", 2);
    Arma Arma4 = generarArmaJson("armas.json", 3);
    Arma Arma5 = generarArmaJson("armas.json", 4);
    Armadura Armadura1 = generarArmaduraJson("armaduras.json", 5);
    Armadura Armadura2 = generarArmaduraJson("armaduras.json", 6);
    Armadura Armadura3 = generarArmaduraJson("armaduras.json", 7);
    Armadura Armadura4 = generarArmaduraJson("armaduras.json", 8);
    Armadura Armadura5 = generarArmaduraJson("armaduras.json", 9);
    Consumible Consumible1 = generarConsumibleJson("consumibles.json", 0);
    Consumible Consumible2 = generarConsumibleJson("consumibles.json", 1);
    Consumible Consumible3 = generarConsumibleJson("consumibles.json", 2);

    @FXML private void initialize() {
        generarArrays();
        actualizarStats();
        actualizarPrecios();
        nombreLabel.setText(personaje.nombre);
        dineroLabel.setText(Integer.toString(personaje.dinero));
        ArticuloCompra1.setText(Arma1.nombre);
        ArticuloCompra2.setText(Arma2.nombre);
        ArticuloCompra3.setText(Arma3.nombre);
        ArticuloCompra4.setText(Arma4.nombre);
        ArticuloCompra5.setText(Arma5.nombre);
        ArticuloCompra6.setText(Armadura1.nombre);
        ArticuloCompra7.setText(Armadura2.nombre);
        ArticuloCompra8.setText(Armadura3.nombre);
        ArticuloCompra9.setText(Armadura4.nombre);
        ArticuloCompra10.setText(Armadura5.nombre);
        ArticuloCompra11.setText(Consumible1.nombre);
        ArticuloCompra12.setText(Consumible2.nombre);
        ArticuloCompra13.setText(Consumible3.nombre);
    }
    public void onClickEquipar(MouseEvent mouseEvent) {
        final  Button button = (Button)mouseEvent.getSource();
        if (ultimoItem == "Arma") {
            if (button.getId().equals(siEquipar.getId())) {
                personaje.equiparArma(ultimaArma);
                espadaLabel.setText(personaje.armaEquipada.nombre);
                actualizarStats();
                equiparMenu.setVisible(false);
                resetPreview();
            }
            else {
                equiparMenu.setVisible(false);
            }
        }
        else if (ultimoItem == "Armadura") {
            if (button.getId().equals(siEquipar.getId())) {
                personaje.equiparArmadura(ultimaArmadura);
                armaduraLabel.setText(personaje.armaduraEquipada.nombre);
                actualizarStats();
                equiparMenu.setVisible(false);
                resetPreview();
            }
            else {
                equiparMenu.setVisible(false);
            }
        }
        else {
            if (button.getId().equals(siEquipar.getId())) {
                personaje.equiparConsumible(ultimoConsumible);
                actualizarStats();
                equiparMenu.setVisible(false);
                resetPreview();
            }
            else {
                equiparMenu.setVisible(false);
                personaje.inventario.ingresarItem(ultimoConsumible, contCompras);
                imgArray[contCompras].setImage(imageConsumible);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
            }
        }
    }

    public void onClickVender(MouseEvent mouseEvent) {
        final Button button = (Button)mouseEvent.getSource();
        switch(button.getId()) {
            case("venta1"): {
                if(personaje.inventario.getItem(0) == null) { break; }
                if(personaje.inventario.getItem(0) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(0) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta1.setText("N/A");
                ArticuloVenta1.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(0).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(0);
                imgArray[0].setImage(null);
                break;
            }
            case("venta2"): {
                if(personaje.inventario.getItem(1) == null) { break; }
                if(personaje.inventario.getItem(1) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(1) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta2.setText("N/A");
                ArticuloVenta2.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(1).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(1);
                imgArray[1].setImage(null);
                break;
            }
            case("venta3"): {
                if(personaje.inventario.getItem(2) == null) { break; }
                if(personaje.inventario.getItem(2) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(2) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta3.setText("N/A");
                ArticuloVenta3.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(2).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(2);
                imgArray[2].setImage(null);
                break;
            }
            case("venta4"): {
                if(personaje.inventario.getItem(3) == null) { break; }
                if(personaje.inventario.getItem(3) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(3) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta4.setText("N/A");
                ArticuloVenta4.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(3).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(3);
                imgArray[3].setImage(null);
                break;
            }
            case("venta5"): {
                if(personaje.inventario.getItem(4) == null) { break; }
                if(personaje.inventario.getItem(4) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");actualizarStats();
                    personaje.desequiparArma();
                }
                if(personaje.inventario.getItem(4) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta5.setText("N/A");
                ArticuloVenta5.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(4).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(4);
                imgArray[4].setImage(null);
                break;
            }
            case("venta6"): {
                if(personaje.inventario.getItem(5) == null) { break; }
                if(personaje.inventario.getItem(5) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(5) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta6.setText("N/A");
                ArticuloVenta6.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(5).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(5);
                imgArray[5].setImage(null);
                break;
            }
            case("venta7"): {
                if(personaje.inventario.getItem(6) == null) { break; }
                if(personaje.inventario.getItem(6) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(6) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta7.setText("N/A");
                ArticuloVenta7.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(6).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(6);
                imgArray[6].setImage(null);
                break;
            }
            case("venta8"): {
                if(personaje.inventario.getItem(7) == null) { break; }
                if(personaje.inventario.getItem(7) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(7) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta8.setText("N/A");
                ArticuloVenta8.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(7).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(7);
                imgArray[7].setImage(null);
                break;
            }
            case("venta9"): {
                if(personaje.inventario.getItem(8) == null) { break; }
                if(personaje.inventario.getItem(8) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(8) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta9.setText("N/A");
                ArticuloVenta9.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(8).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(8);
                imgArray[8].setImage(null);
                break;
            }
            case("venta10"): {
                if(personaje.inventario.getItem(9) == null) { break; }
                if(personaje.inventario.getItem(9) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(9) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta10.setText("N/A");
                ArticuloVenta10.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(9).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(9);
                imgArray[9].setImage(null);
                break;
            }
            case("venta11"): {
                if(personaje.inventario.getItem(10) == null) { break; }
                if(personaje.inventario.getItem(10) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(10) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta11.setText("N/A");
                ArticuloVenta11.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(10).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(10);
                imgArray[10].setImage(null);
                break;
            }
            case("venta12"): {
                if(personaje.inventario.getItem(11) == null) { break; }
                if(personaje.inventario.getItem(11) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();actualizarStats();
                }
                if(personaje.inventario.getItem(11) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();actualizarStats();
                }
                venta12.setText("N/A");
                ArticuloVenta11.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(11).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(11);
                imgArray[11].setImage(null);
                break;
            }
            case("venta13"): {
                if(personaje.inventario.getItem(12) == null) { break; }
                if(personaje.inventario.getItem(12) == personaje.armaEquipada) {
                    espadaLabel.setText("< Ninguna >");
                    personaje.desequiparArma();
                    actualizarStats();
                }
                if(personaje.inventario.getItem(12) == personaje.armaduraEquipada) {
                    armaduraLabel.setText("< Ninguna >");
                    personaje.desequiparArmadura();
                    actualizarStats();
                }
                venta13.setText("N/A");
                ArticuloVenta13.setText("< Vacio >");
                personaje.dinero += personaje.inventario.getItem(12).precioVenta;
                dineroLabel.setText(Integer.toString(personaje.dinero));
                personaje.inventario.borrarItem(12);
                imgArray[12].setImage(null);
                break;
            }
        }
    }

    public void onClickComprar(MouseEvent mouseEvent) {
        final Button button = (Button)mouseEvent.getSource();
        switch (button.getId()) {
            case("comprarButton1"): {
                if(Arma1.precioCompra > personaje.dinero) { break; }
                if(comprarButton1.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArma = Arma1;
                itemComprado(ultimaArma);
                ArticuloCompra1.setText("< Agotado >");
                comprarButton1.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArma, contCompras);
                imgArray[contCompras].setImage(imageArma);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Arma";
                break;
            }
            case("comprarButton2"): {
                if(Arma2.precioCompra > personaje.dinero) { break; }
                if(comprarButton2.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArma = Arma2;
                itemComprado(ultimaArma);
                ArticuloCompra2.setText("< Agotado >");
                comprarButton2.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArma, contCompras);
                imgArray[contCompras].setImage(imageArma);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Arma";
                break;
            }
            case("comprarButton3"): {
                if(Arma3.precioCompra > personaje.dinero) { break; }
                if(comprarButton3.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArma = Arma3;
                itemComprado(ultimaArma);
                ArticuloCompra3.setText("< Agotado >");
                comprarButton3.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArma, contCompras);
                imgArray[contCompras].setImage(imageArma);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Arma";
                break;
            }
            case("comprarButton4"): {
                if(Arma4.precioCompra > personaje.dinero) { break; }
                if(comprarButton4.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArma = Arma4;
                itemComprado(ultimaArma);
                ArticuloCompra4.setText("< Agotado >");
                comprarButton4.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArma, contCompras);
                imgArray[contCompras].setImage(imageArma);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Arma";
                break;
            }
            case("comprarButton5"): {
                if(Arma5.precioCompra > personaje.dinero) { break; }
                if(comprarButton5.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArma = Arma5;
                itemComprado(ultimaArma);
                ArticuloCompra5.setText("< Agotado >");
                comprarButton5.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArma, contCompras);
                imgArray[contCompras].setImage(imageArma);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Arma";
                break;
            }
            case("comprarButton6"): {
                if(Armadura1.precioCompra > personaje.dinero) { break; }
                if(comprarButton6.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArmadura = Armadura1;
                itemComprado(ultimaArmadura);
                ArticuloCompra6.setText("< Agotado >");
                comprarButton6.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArmadura, contCompras);
                imgArray[contCompras].setImage(imageArmadura);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;

                ultimoItem = "Armadura";
                break;
            }
            case("comprarButton7"): {
                if(Armadura2.precioCompra > personaje.dinero) { break; }
                if(comprarButton7.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArmadura = Armadura2;
                itemComprado(ultimaArmadura);
                ArticuloCompra7.setText("< Agotado >");
                comprarButton7.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArmadura, contCompras);
                imgArray[contCompras].setImage(imageArmadura);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Armadura";
                break;
            }
            case("comprarButton8"): {
                if(Armadura3.precioCompra > personaje.dinero) { break; }
                if(comprarButton8.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArmadura = Armadura3;
                itemComprado(ultimaArmadura);
                ArticuloCompra8.setText("< Agotado >");
                comprarButton8.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArmadura, contCompras);
                imgArray[contCompras].setImage(imageArmadura);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Armadura";
                break;
            }
            case("comprarButton9"): {
                if(Armadura4.precioCompra > personaje.dinero) { break; }
                if(comprarButton9.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArmadura = Armadura4;
                itemComprado(ultimaArmadura);
                ArticuloCompra9.setText("< Agotado >");
                comprarButton9.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArmadura, contCompras);
                imgArray[contCompras].setImage(imageArmadura);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Armadura";
                break;
            }
            case("comprarButton10"): {
                if(Armadura5.precioCompra > personaje.dinero) { break; }
                if(comprarButton10.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimaArmadura = Armadura5;
                itemComprado(ultimaArmadura);
                ArticuloCompra10.setText("< Agotado >");
                comprarButton10.setText("N/A");
                personaje.inventario.ingresarItem(ultimaArmadura, contCompras);
                imgArray[contCompras].setImage(imageArmadura);
                labelArray[contCompras].setText(personaje.inventario.getItem(contCompras).nombre);
                buttonArray[contCompras].setText(Integer.toString(personaje.inventario.getItem(contCompras).precioVenta));
                contCompras++;
                ultimoItem = "Armadura";
                break;
            }
            case("comprarButton11"): {
                if(Consumible1.precioCompra > personaje.dinero) { break; }
                if(comprarButton11.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimoConsumible = Consumible1;
                itemComprado(ultimoConsumible);
                ArticuloCompra11.setText("< Agotado >");
                comprarButton11.setText("N/A");
                personaje.inventario.ingresarItem(ultimoConsumible, contCompras);
                ultimoItem = "Consumible";
                break;
            }
            case("comprarButton12"): {
                if(Consumible2.precioCompra > personaje.dinero) { break; }
                if(comprarButton12.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimoConsumible = Consumible2;
                itemComprado(ultimoConsumible);
                ArticuloCompra12.setText("< Agotado >");
                comprarButton12.setText("N/A");
                personaje.inventario.ingresarItem(ultimoConsumible, contCompras);
                ultimoItem = "Consumible";
                break;
            }
            case("comprarButton13"): {
                if(Consumible3.precioCompra > personaje.dinero) { break; }
                if(comprarButton13.getText() == "N/A") { break; }
                equiparMenu.setVisible(true);
                ultimoConsumible = Consumible3;
                itemComprado(ultimoConsumible);
                ArticuloCompra13.setText("< Agotado >");
                comprarButton13.setText("N/A");
                ultimoItem = "Consumible";
                break;
            }
        }
    }

    public void onClickItemCompra(MouseEvent mouseEvent) {
        resetPreview();
        final Label label = (Label)mouseEvent.getSource();
        switch (label.getId()) {
            case("ArticuloCompra1"): {
                if(comprarButton1.getText() == "N/A") { break; }
                atkLabel2.setText(Integer.toString(personaje.baseStats.ATK + Arma1.atkStat));
                critLabel2.setText(Integer.toString(personaje.baseStats.CRIT + Arma1.critStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Arma1.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra2"): {
                if(comprarButton2.getText() == "N/A") { break; }
                atkLabel2.setText(Integer.toString(personaje.baseStats.ATK + Arma2.atkStat));
                critLabel2.setText(Integer.toString(personaje.baseStats.CRIT + Arma2.critStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Arma2.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra3"): {
                if(comprarButton3.getText() == "N/A") { break; }
                atkLabel2.setText(Integer.toString(personaje.baseStats.ATK + Arma3.atkStat));
                critLabel2.setText(Integer.toString(personaje.baseStats.CRIT + Arma3.critStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Arma3.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra4"): {
                if(comprarButton4.getText() == "N/A") { break; }
                atkLabel2.setText(Integer.toString(personaje.baseStats.ATK + Arma4.atkStat));
                critLabel2.setText(Integer.toString(personaje.baseStats.CRIT + Arma4.critStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Arma4.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra5"): {
                if(comprarButton5.getText() == "N/A") { break; }
                atkLabel2.setText(Integer.toString(personaje.baseStats.ATK + Arma5.atkStat));
                critLabel2.setText(Integer.toString(personaje.baseStats.CRIT + Arma5.critStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Arma5.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra6"): {
                if(comprarButton6.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Armadura1.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Armadura1.mpStat));
                defLabel2.setText(Integer.toString(personaje.baseStats.DEF + Armadura1.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra7"): {
                if(comprarButton7.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Armadura2.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Armadura2.mpStat));
                defLabel2.setText(Integer.toString(personaje.baseStats.DEF + Armadura2.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra8"): {
                if(comprarButton8.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Armadura3.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Armadura3.mpStat));
                defLabel2.setText(Integer.toString(personaje.baseStats.DEF + Armadura3.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra9"): {
                if(comprarButton9.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Armadura4.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Armadura4.mpStat));
                defLabel2.setText(Integer.toString(personaje.baseStats.DEF + Armadura4.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra10"): {
                if(comprarButton10.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Armadura5.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Armadura5.mpStat));
                defLabel2.setText(Integer.toString(personaje.baseStats.DEF + Armadura5.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra11"): {
                if(comprarButton11.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Consumible1.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Consumible1.mpStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra12"): {
                if(comprarButton12.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Consumible2.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Consumible2.mpStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra13"): {
                if(comprarButton13.getText() == "N/A") { break; }
                hpLabel2.setText(Integer.toString(personaje.baseStats.HP + Consumible3.hpStat));
                mpLabel2.setText(Integer.toString(personaje.baseStats.MP + Consumible3.mpStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                break;
            }
        }

    }

    public Arma generarArmaJson(String archivoJson, int indice) {
        Arma Arma = new Arma("");
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("C:\\Users\\hhjua\\Desktop\\POO\\POO_Proyecto1\\src\\" + archivoJson);
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray jsonArray = (JSONArray)jsonObject.get("products");
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(indice);
                JSONObject jsonObject3 = (JSONObject) jsonObject2.get("price");
                JSONObject jsonObject4 = (JSONObject) jsonObject2.get("reviews");
                Arma.setNombre(jsonObject2.get("title").toString());
                Arma.setAtkStat(Math.round(Float.parseFloat(jsonObject2.get("score").toString())));
                Arma.setCritStat(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString())));
                Arma.setMpStat(Integer.parseInt(jsonObject4.get("total_reviews").toString()));
                Arma.setPrecioCompra(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString())) + 561);
                Arma.setPrecioVenta(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString()) + 561 * 0.3F));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Arma;
    }

    public Armadura generarArmaduraJson(String archivoJson, int indice) {
        Armadura Armadura = new Armadura("");
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("C:\\Users\\hhjua\\Desktop\\POO\\POO_Proyecto1\\src\\" + archivoJson);
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray jsonArray = (JSONArray)jsonObject.get("products");
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(indice);
                JSONObject jsonObject3 = (JSONObject) jsonObject2.get("price");
                JSONObject jsonObject4 = (JSONObject) jsonObject2.get("reviews");
                Armadura.setNombre(jsonObject2.get("title").toString());
                Armadura.setHpStat(Math.round(Float.parseFloat(jsonObject2.get("score").toString())));
                Armadura.setDefStat(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString())));
                Armadura.setMpStat(Integer.parseInt(jsonObject4.get("total_reviews").toString()));
                Armadura.setPrecioCompra(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString())) + 677);
                Armadura.setPrecioVenta(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString()) + 677 * 0.3F));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Armadura;
    }

    public Consumible generarConsumibleJson(String archivoJson, int indice) {
        Consumible consumible = new Consumible("");
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("C:\\Users\\hhjua\\Desktop\\POO\\POO_Proyecto1\\src\\" + archivoJson);
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray jsonArray = (JSONArray)jsonObject.get("products");
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(indice);
                JSONObject jsonObject3 = (JSONObject) jsonObject2.get("price");
                consumible.setNombre(jsonObject2.get("title").toString());
                consumible.setHpStat(Math.round(Float.parseFloat(jsonObject2.get("score").toString())));
                consumible.setMpStat(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString())));
                consumible.setPrecioCompra(Math.round(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString()))) + 394);
                consumible.setPrecioVenta(Math.round(Float.parseFloat(jsonObject3.get("current_price").toString()) + 394 * 0.3F));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return consumible;
    }

    public Image getImagen(String archivoJson, int indice) {
        JSONParser parser = new JSONParser();
        String res = "";
        try {
            Reader reader = new FileReader("C:\\Users\\hhjua\\Desktop\\POO\\POO_Proyecto1\\src\\" + archivoJson);
            try {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray jsonArray = (JSONArray)jsonObject.get("products");
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(indice);
                res = jsonObject2.get("thumbnail").toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(res);
        return image;
    }

    public void actualizarStats() {
        hpLabel.setText(Integer.toString(personaje.stats.HP));
        mpLabel.setText(Integer.toString(personaje.stats.MP));
        atkLabel.setText(Integer.toString(personaje.stats.ATK));
        defLabel.setText(Integer.toString(personaje.stats.DEF));
        critLabel.setText(Integer.toString(personaje.stats.CRIT));
    }

    public void actualizarPrecios() {
        comprarButton1.setText(Integer.toString(Arma1.precioCompra));
        comprarButton2.setText(Integer.toString(Arma2.precioCompra));
        comprarButton3.setText(Integer.toString(Arma3.precioCompra));
        comprarButton4.setText(Integer.toString(Arma4.precioCompra));
        comprarButton5.setText(Integer.toString(Arma5.precioCompra));
        comprarButton6.setText(Integer.toString(Armadura1.precioCompra));
        comprarButton7.setText(Integer.toString(Armadura2.precioCompra));
        comprarButton8.setText(Integer.toString(Armadura3.precioCompra));
        comprarButton9.setText(Integer.toString(Armadura4.precioCompra));
        comprarButton10.setText(Integer.toString(Armadura5.precioCompra));
        comprarButton11.setText(Integer.toString(Consumible1.precioCompra));
        comprarButton12.setText(Integer.toString(Consumible2.precioCompra));
        comprarButton13.setText(Integer.toString(Consumible3.precioCompra));
    }

    public void resetPreview() {
        atkFlecha.setVisible(false);atkLabel2.setText("");
        mpFlecha.setVisible(false);mpLabel2.setText("");
        critFlecha.setVisible(false);critLabel2.setText("");
        hpFlecha.setVisible(false);hpLabel2.setText("");
        defFlecha.setVisible(false);defLabel2.setText("");
    }

    public void itemComprado(Item item) {
        if(item.getClass().getCanonicalName() == "Arma") {
            personaje.dinero -= ultimaArma.precioCompra;
            dineroLabel.setText(Integer.toString(personaje.dinero));
        }
        else if(item.getClass().getCanonicalName() == "Armadura") {
            personaje.dinero -= ultimaArmadura.precioCompra;
            dineroLabel.setText(Integer.toString(personaje.dinero));
        }
        else{
            personaje.dinero -= ultimoConsumible.precioCompra;
            dineroLabel.setText(Integer.toString(personaje.dinero));
        }
    }

    public void generarArrays() {
        imgArray[0] = img1;
        imgArray[1] = img2;
        imgArray[2] = img3;
        imgArray[3] = img4;
        imgArray[4] = img5;
        imgArray[5] = img6;
        imgArray[6] = img7;
        imgArray[7] = img8;
        imgArray[8] = img9;
        imgArray[9] = img10;
        imgArray[10] = img11;
        imgArray[11] = img12;
        imgArray[12] = img13;

        labelArray[0] = ArticuloVenta1;
        labelArray[1] = ArticuloVenta2;
        labelArray[2] = ArticuloVenta3;
        labelArray[3] = ArticuloVenta4;
        labelArray[4] = ArticuloVenta5;
        labelArray[5] = ArticuloVenta6;
        labelArray[6] = ArticuloVenta7;
        labelArray[7] = ArticuloVenta8;
        labelArray[8] = ArticuloVenta9;
        labelArray[9] = ArticuloVenta10;
        labelArray[10] = ArticuloVenta11;
        labelArray[11] = ArticuloVenta12;
        labelArray[12] = ArticuloVenta13;

        buttonArray[0] = venta1;
        buttonArray[1] = venta2;
        buttonArray[2] = venta3;
        buttonArray[3] = venta4;
        buttonArray[4] = venta5;
        buttonArray[5] = venta6;
        buttonArray[6] = venta7;
        buttonArray[7] = venta8;
        buttonArray[8] = venta9;
        buttonArray[9] = venta10;
        buttonArray[10] = venta11;
        buttonArray[11] = venta12;
        buttonArray[12] = venta13;
    }
}
