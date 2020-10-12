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
    @FXML private Label ArticuloCompra9, ArticuloCompra10, ArticuloCompra11, ArticuloCompra12, ArticuloCompra13;
    @FXML private Label hpLabel, mpLabel, atkLabel, defLabel, critLabel, mpLabel2, atkLabel2, critLabel2, hpLabel2, defLabel2;
    @FXML private ImageView mpFlecha, atkFlecha, critFlecha, hpFlecha, defFlecha;
    @FXML private AnchorPane equiparMenu;
    @FXML private Button comprarButton1, siEquipar, noEquipar;
    private String ultimaCompra = "";
    private Arma ultimaArma = new Arma("");

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
        actualizarStats();
        actualizarPrecios();
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
        if (button.getId().equals(siEquipar.getId())) {
            personaje.equiparArma(ultimaArma);
            actualizarStats();
            equiparMenu.setVisible(false);
            personaje.imprimirPersonaje();
        }
        else {
            personaje.imprimirPersonaje();
            equiparMenu.setVisible(false);
        }
    }

    public void onClickComprar(MouseEvent mouseEvent) {
        final Button button = (Button)mouseEvent.getSource();
        switch (button.getId()) {
            case("comprarButton1"): {
                equiparMenu.setVisible(true);
                ultimaArma = Arma1;
                break;
            }
        }
    }

    public void onClickItemCompra(MouseEvent mouseEvent) {
        atkFlecha.setVisible(false);atkLabel2.setText("");
        mpFlecha.setVisible(false);mpLabel2.setText("");
        critFlecha.setVisible(false);critLabel2.setText("");
        hpFlecha.setVisible(false);hpLabel2.setText("");
        defFlecha.setVisible(false);defLabel2.setText("");
        
        final Label label = (Label)mouseEvent.getSource();
        switch (label.getId()) {
            case("ArticuloCompra1"): {
                atkLabel2.setText(Integer.toString(personaje.stats.ATK + Arma1.atkStat));
                critLabel2.setText(Integer.toString(personaje.stats.CRIT + Arma1.critStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Arma1.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra2"): {
                atkLabel2.setText(Integer.toString(personaje.stats.ATK + Arma2.atkStat));
                critLabel2.setText(Integer.toString(personaje.stats.CRIT + Arma2.critStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Arma2.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra3"): {
                atkLabel2.setText(Integer.toString(personaje.stats.ATK + Arma3.atkStat));
                critLabel2.setText(Integer.toString(personaje.stats.CRIT + Arma3.critStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Arma3.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra4"): {
                atkLabel2.setText(Integer.toString(personaje.stats.ATK + Arma4.atkStat));
                critLabel2.setText(Integer.toString(personaje.stats.CRIT + Arma4.critStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Arma4.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra5"): {
                atkLabel2.setText(Integer.toString(personaje.stats.ATK + Arma5.atkStat));
                critLabel2.setText(Integer.toString(personaje.stats.CRIT + Arma5.critStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Arma5.mpStat));
                atkFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                critFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra6"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Armadura1.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Armadura1.mpStat));
                defLabel2.setText(Integer.toString(personaje.stats.DEF + Armadura1.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra7"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Armadura2.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Armadura2.mpStat));
                defLabel2.setText(Integer.toString(personaje.stats.DEF + Armadura2.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra8"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Armadura3.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Armadura3.mpStat));
                defLabel2.setText(Integer.toString(personaje.stats.DEF + Armadura3.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra9"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Armadura4.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Armadura4.mpStat));
                defLabel2.setText(Integer.toString(personaje.stats.DEF + Armadura4.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra10"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Armadura5.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Armadura5.mpStat));
                defLabel2.setText(Integer.toString(personaje.stats.DEF + Armadura5.defStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                defFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra11"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Consumible1.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Consumible1.mpStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra12"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Consumible2.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Consumible2.mpStat));
                hpFlecha.setVisible(true);
                mpFlecha.setVisible(true);
                break;
            }
            case("ArticuloCompra13"): {
                hpLabel2.setText(Integer.toString(personaje.stats.HP + Consumible3.hpStat));
                mpLabel2.setText(Integer.toString(personaje.stats.MP + Consumible3.mpStat));
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
    }
}
