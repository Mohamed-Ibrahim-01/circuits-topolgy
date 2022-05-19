package circuits.topolgy;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;

import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;


public class Topolgy {
    protected String id;
    protected HashMap<String,Component> components;

    public Topolgy(String id){
        this.id = id;
    }

    public Topolgy(String id, HashMap<String, Component> components){
        this.id = id;
        this.components = components;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("(id=%s, source=%s)", id, components);
    }
    
}

class TopolgySerializer implements JsonSerializer<Topolgy> {
    @Override
    public JsonElement serialize(Topolgy src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject topolgy = new JsonObject();
        ArrayList<Component> componentsList = new ArrayList<Component>(src.components.values());

        topolgy.add("id", new JsonPrimitive(src.id));
        topolgy.add("components", context.serialize(componentsList));

        return topolgy;
    }
}
