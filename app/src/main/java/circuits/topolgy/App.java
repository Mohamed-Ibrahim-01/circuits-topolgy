package circuits.topolgy;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.*;

public class App {
    public static void main(String[] args) {
        Path topolgyPath = Paths.get(
                "/home/mohamed/clones/circuits-topolgy/app/src/main/resources/topology2.json");

        Path writeTopolgy = Paths.get(
                "/home/mohamed/clones/circuits-topolgy/app/src/main/resources/written.json");
        Topolgy top1 = Api.readTopolgy(topolgyPath);
        String json =  Api.writeTopolgy(top1, writeTopolgy);
        System.out.println(json);
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void trial(){
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        Collection collection = new ArrayList();

        collection.add("hello");
        collection.add(5);
        collection.add(new Resistor("resid", new ResistorProps(1,2,3),new ResistorNetlist("t1", "t2")));

        String json = gson.toJson(collection);
        System.out.println("Using Gson.toJson() on a raw collection: " + json);
    /*
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();

        String message = gson.fromJson(array.get(0), String.class);
        int number = gson.fromJson(array.get(1), int.class);
        Resistor resistor = gson.fromJson(array.get(2), Resistor.class);
        Nmos nmos = gson.fromJson(array.get(3), Nmos.class);

    */
    }
}


