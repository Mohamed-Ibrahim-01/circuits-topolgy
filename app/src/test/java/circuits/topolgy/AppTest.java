package circuits.topolgy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

class ApiTest {

    Path topolgy1Path = Paths.get("./src/main/resources/topology.json");
    Path topolgy2Path = Paths.get("./src/main/resources/topology2.json");
    Path writeTopolgy = Paths.get("./src/main/resources/written.json");

    Api API;

    private enum TestMsg{
        INVALID_SIZE("Invalid number of expected Topolgies"),
        INVALID_COMP_TYPE("Invalid type of component");
        private String val;
        private TestMsg(String msg){
            this.val = msg;
        }
    }

    @BeforeEach void newAPI(){
        API = new Api();
    }

    @Test void readingTopologies() {
        Topolgy top1 = API.readTopolgy(topolgy1Path);
        String top1Id = top1.getId();
        List<Component> top1Components = top1.getComponents();

        assertNotNull(top1, "topolgy1 object not created");
        assertEquals("top1" , top1Id, "Id not matched");

        assertEquals(2, top1Components.size(), "Invalid number of expected Components");

        Resistor res1 = (Resistor)top1Components.get(0);
        assertEquals(ComponentType.RESISTOR, res1.type,  "Invalid Component Type");
        assertEquals("res1" , res1.id, "Invalid Component Id");
        assertEquals(res1.getResistance(), new ResistorProps(100, 10, 1000), "Invalid ResistorProps");
        assertEquals(new ResistorNetlist("vdd", "n1"), res1.getNetlist(),  "Invalid ResistorNetlist");

        Nmos m1 = (Nmos)top1Components.get(1);
        assertEquals(ComponentType.NMOS, m1.type,  "Invalid Component Type");
        assertEquals("m1" , m1.id, "Invalid Component Id");
        assertEquals(new NmosProps(1.5, 1, 2), m1.getNmosProps(), "Invalid NmosProps");
        assertEquals(new NmosNetlist("n1", "vin", "vss"), m1.getNetlist(),  "Invalid NmosNetlist");

        API.deleteTopolgy(top1Id);
    }

    @Test void writingTopologies() throws IOException{
        Path emptyTopPath = Paths.get("./src/test/resources/emptyTestTopology.json");
        Path top1Path = Paths.get("./src/test/resources/top1TestTopology.json");

        Topolgy emptyTop = new Topolgy("test_top1");
        Topolgy top1 = API.readTopolgy(topolgy1Path);

        API.writeTopolgy(emptyTop.getId(), emptyTopPath);
        API.writeTopolgy(top1.getId(), top1Path);

        String emptyTopContent = Files.readString(emptyTopPath);
        List<String> top1Content = Files.readAllLines(top1Path);

        assertEquals("null" ,emptyTopContent, "Invalid write");
        assertEquals("{", top1Content.get(0),"Invalid write");
        assertEquals("      \"type\": \"resistor\",", top1Content.get(4),"Invalid write");
        assertEquals("      \"type\": \"nmos\",", top1Content.get(17), "Invalid write");
        assertEquals("}", top1Content.get(31), "Invalid write" );
    }

    @Test void queryingTopolgies() {
        assertEquals(0, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);
        Topolgy top1 = API.readTopolgy(topolgy1Path);
        assertEquals(1, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);
        Topolgy top2 = API.readTopolgy(topolgy2Path);
        assertEquals(2, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);

        List<Topolgy> topolgies = API.queryTopolgies();
        assertEquals(topolgies.get(0), top1);
        assertEquals(topolgies.get(1), top2);
    }

    @Test void deletingTopologies() {
        assertEquals(0, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);

        Topolgy top1 = API.readTopolgy(topolgy1Path);
        Topolgy top2 = API.readTopolgy(topolgy2Path);

        assertEquals(2, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);

        API.deleteTopolgy(top1.getId());
        API.deleteTopolgy(top2.getId());
    }

    @Test void queryTopologyDevices() {
        assertEquals(0, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);

        Topolgy top2 = API.readTopolgy(topolgy2Path);
        List<Component> top2Components = API.queryDevices(top2.getId());

        assertEquals(ComponentType.RESISTOR, top2Components.get(0).type, TestMsg.INVALID_COMP_TYPE.val);
        assertEquals(ComponentType.NMOS, top2Components.get(1).type, TestMsg.INVALID_COMP_TYPE.val);
        assertEquals(ComponentType.RESISTOR, top2Components.get(2).type, TestMsg.INVALID_COMP_TYPE.val);
    }

    @Test void queryTopologyDevicesNode() {
        assertEquals(0, API.queryTopolgies().size(), TestMsg.INVALID_SIZE.val);

        Topolgy top1 = API.readTopolgy(topolgy1Path);

        List<Component> top1Components = API.queryDevices(top1.getId());
        List<Component> vddNodeComponents = API.queryDevicesWithNode(top1.getId(), "vdd");
        List<Component> n1NodeComponents = API.queryDevicesWithNode(top1.getId(), "n1");
        
        assertEquals(1, vddNodeComponents.size(), "Invalid number of hooked components to node");
        assertEquals(top1Components.get(0), vddNodeComponents.get(0), "Invalid Component in Node");

        assertEquals(2, n1NodeComponents.size(), "Invalid number of hooked components to node");
        assertEquals(top1Components.get(0), n1NodeComponents.get(0), "Invalid Component in Node");
        assertEquals(top1Components.get(1), n1NodeComponents.get(1), "Invalid Component in Node");

        API.deleteTopolgy(top1.getId());
    }

}
