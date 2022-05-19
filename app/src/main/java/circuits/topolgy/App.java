package circuits.topolgy;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        
        Path topolgy1Path = Paths.get("./src/main/resources/topology.json");
        Path writeTopolgy = Paths.get("./src/main/resources/written.json");

        Api API = new Api();

        Topolgy top1 = API.readTopolgy(topolgy1Path);
        String writtenTop1 = API.writeTopolgy(top1.getId(), writeTopolgy);

        System.out.println(writtenTop1);
    }
}


