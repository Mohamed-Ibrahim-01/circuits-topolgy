package circuits.topolgy;

import java.util.HashMap;
class Utils{
    public static Class<? extends Component> ComponentFromType(ComponentType type){
        HashMap<ComponentType, Class<? extends Component>> components = 
            new HashMap<ComponentType, Class<? extends Component>>();

        components.put(ComponentType.RESISTOR, Resistor.class);
        components.put(ComponentType.NMOS, Nmos.class);

        return components.get(type);
    }
}
