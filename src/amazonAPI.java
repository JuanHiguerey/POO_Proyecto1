import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class amazonAPI {
    private JSONObject jsonObject;

    public amazonAPI() {

    }

    public void request() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://amazon-product-reviews-keywords.p.rapidapi.com/product/search?category=aps&country=US&keyword=drink")
                .get()
                .addHeader("X-RapidAPI-Host", "amazon-product-reviews-keywords.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "93819bf9bfmsh116bc9de9bb031ep153bb5jsn29c4af34d50b")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            generarJSON(response.body().string());
            FileWriter archivoJSON = new FileWriter("C:\\Users\\hhjua\\Desktop\\POO\\POO_Proyecto1\\src\\consumibles.json");
            archivoJSON.write(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generarJSON(String stringJSON) {
        JSONParser parse = new JSONParser();
        try {
            jsonObject = (JSONObject)parse.parse(stringJSON);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
