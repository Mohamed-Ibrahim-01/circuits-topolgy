package circuits.topolgy;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.reflect.Type;

import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;


/**
* Topolgy is the data structure representation of the circut and the main class that the api deals with.
* 
* @author Mohamed Ibraim
* 
*/
public class Topolgy {
    protected String id;
    protected HashMap<String, Component> components;
    protected HashMap<String, List<Component>> nodes;

    public Topolgy(String id){
        this.id = id;
    }

    public Topolgy(String id, HashMap<String, Component> components){
        this.id = id;
        this.components = components;
        this.nodes = hookComponentsNodes();
    }

    public String getId(){
        return this.id;
    }

    public List<Component> getComponents(){
        return new LinkedList<Component>(this.components.values());
    }

    public void setComponents(HashMap<String, Component> components){
        this.components = components;
        this.nodes = hookComponentsNodes();
    }

    private HashMap<String, List<Component>> hookComponentsNodes(){
        HashMap<String, List<Component>> nodes = new HashMap<>();
        for(Component component: this.components.values()){
            List<String> nodesIds = component.getNodesIds();
            for(String nodeId : nodesIds){
                if(!nodes.containsKey(nodeId)){
                    nodes.put(nodeId, new LinkedList<Component>());
                }
                nodes.get(nodeId).add(component);
            }
        }
        return nodes;
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
