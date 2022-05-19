package circuits.topolgy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class JsonIo{
    private static Gson gson = gsonConfig();

    public static Topolgy deserializeTopolgy(Path filePath){
        Topolgy topolgy;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))){
            JsonObject jsonTopolgy = JsonParser.parseReader(br).getAsJsonObject();

            String topolgyId = jsonTopolgy.get("id").getAsString();

            JsonArray components = jsonTopolgy.getAsJsonArray("components");
            HashMap<String, Component> topologyComponents = parseTopologyComponents(components);

            topolgy = new Topolgy(topolgyId, topologyComponents);

        }catch(Exception e){
                topolgy = new Topolgy("id");
                e.printStackTrace();
        }
        return topolgy;
    }

    public static String serializeTopolgy(Topolgy topolgy, Path writePath){
        String writtenTopolgy = gson.toJson(topolgy);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(writePath.toFile()))){
            writtenTopolgy = gson.toJson(topolgy);
            gson.toJson(topolgy, bw);
        }
        catch(Exception e){
            writtenTopolgy = "";
            e.printStackTrace();
        }
        return writtenTopolgy;
    }

    private static HashMap<String, Component> parseTopologyComponents(JsonArray components){
        HashMap<String, Component> topologyComponents = new HashMap<String, Component>();

        for(JsonElement jsonComponent : components){
            ComponentType type = ComponentType.valueOf(
                    jsonComponent.getAsJsonObject().get("type").getAsString().toUpperCase());

            String componentId = jsonComponent.getAsJsonObject().get("id").getAsString();
            Component component = gson.fromJson(jsonComponent,Utils.ComponentFromType(type));

            topologyComponents.put(componentId, component);
        }
        return topologyComponents; 
    }

    private static Gson gsonConfig(){
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Topolgy.class, new TopolgySerializer());

        gsonBuilder.registerTypeAdapter(Resistor.class, new ComponentSerializer());
        gsonBuilder.registerTypeAdapter(Nmos.class, new ComponentSerializer());
        gsonBuilder.registerTypeAdapter(ComponentType.class, new ComponentTypeDeserializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        return gson;
    }
}
