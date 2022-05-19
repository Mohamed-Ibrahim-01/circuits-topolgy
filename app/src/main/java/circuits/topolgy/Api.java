package circuits.topolgy;

import java.nio.file.Path;

public class Api {
    public static Topolgy readTopolgy(Path topolgyPath){
        return JsonIo.deserializeTopolgy(topolgyPath);
    }

    public static String writeTopolgy(Topolgy topolgy, Path writePath){
        return JsonIo.serializeTopolgy(topolgy, writePath);
    }
}

