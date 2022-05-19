package circuits.topolgy;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        
        Path topolgy1Path = Paths.get("./src/main/resources/topology.json");
        Path topolgy2Path = Paths.get("./src/main/resources/topology2.json");
        Path writeTopolgy = Paths.get("./src/main/resources/written.json");

        Api API = new Api();

        Topolgy top1 = API.readTopolgy(topolgy1Path);
        Topolgy top2 = API.readTopolgy(topolgy2Path);
        for(Topolgy t : API.queryTopolgies()){
            System.out.println(t);
        }

        String json =  API.writeTopolgy(top1.getId(), writeTopolgy);
        System.out.println(json);
        API.deleteTopolgy(top1.getId());
        for(Topolgy t : API.queryTopolgies()){
            System.out.println(t);
        }
        for(Component c : API.queryDevices(top2.getId())){
            System.out.println(c);
        }

        for(Component c : API.queryDevicesWithNode(top2.getId(), "vdd")){
            System.out.println(c);
        }
    }
}


