package circuits.topolgy;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
* Api that uses in memory database managing Topolgies (reading , writing, queries,...)
* 
* @author Mohamed Ibraim
* 
*/
public class Api {
    public HashMap<String, Topolgy> memoryDb = new HashMap<String, Topolgy>();

    /**
     * Reading topoloy from a given path.
     *
     * @param topolgyPath the path for Topolgy to be read.
     * @return {@link Topolgy}
     */
    public Topolgy readTopolgy(Path topolgyPath){
        Topolgy topolgy = JsonIo.deserializeTopolgy(topolgyPath);
        memoryDb.put(topolgy.getId(), topolgy);
        return topolgy;
    }

    /**
     * Writing a selected topoloy from the db to the disk to specific write path as json.
     *
     * @param topolgyId the id for the selected Topolgy
     * @param writePath the path for Topolgy to be written.
     * @return {@link String} the written json.
     */
    public String writeTopolgy(String topolgyId, Path writePath){
        Topolgy topolgy = memoryDb.get(topolgyId);
        return JsonIo.serializeTopolgy(topolgy, writePath);
    }

    /**
     * Getting a list of all the present topologies in the db.
     *
     * @return List of {@link Topolgy}> the written json.
     */
    public List<Topolgy> queryTopolgies(){
        return new ArrayList<Topolgy>(memoryDb.values());
    }

    /**
     * Deleting a Topolgy from the db.
     *
     * @param topolgyId the id for the selected Topolgy to be deleted
     *
     * @return {@link Topolgy} the deleted {@link Topolgy}.
     */
    public Topolgy deleteTopolgy(String topolgyId){
        return memoryDb.remove(topolgyId);
    }

    /**
     * Getting a list of all the components present in a {@link Topolgy}
     *
     * @return List of {@link Component}> all the components in the given topology.
     */
    public List<Component> queryDevices(String topolgyId){
        return new ArrayList<Component>(memoryDb.get(topolgyId).getComponentsList());
    }

    /**
     * Getting a list of all the components present in a {@link Topolgy} that share same node in the topolgy.
     *
     * @return List of {@link Component}> all the components in the given topology.
     */
    public List<Component> queryDevicesWithNode(String topolgyId, String nodeId){
        return memoryDb.get(topolgyId).nodes.get(nodeId);
    }

}

