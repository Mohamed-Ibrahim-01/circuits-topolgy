package circuits.topolgy;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

abstract class Component {
    protected ComponentType type;
    protected String id;

    protected Component(ComponentType type, String id){
        this.type = type;
        this.id = id;
    }
}

class ComponentSerializer implements JsonSerializer<Component> {
    @Override
    public JsonElement serialize(Component src, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject component = new JsonObject();
        JsonObject unorderedComponenet = new Gson().toJsonTree(src).getAsJsonObject();

        JsonElement id = unorderedComponenet.remove("id");
        JsonElement type = unorderedComponenet.remove("type");

        component.add("type", type);
        component.add("id", id);

        for(String prop : unorderedComponenet.keySet()){
            component.add(prop, unorderedComponenet.get(prop));
        }

        return component;
    }
}

class ComponentTypeDeserializer implements JsonDeserializer <ComponentType> {
    @Override
    public ComponentType deserialize(JsonElement json, Type typeOfSrc,
            JsonDeserializationContext context){
        return ComponentType.valueOf(json.getAsString().toUpperCase());
    }
}
