package circuits.topolgy;

import com.google.gson.annotations.SerializedName;
import java.util.LinkedList;

class NmosProps{
    @SerializedName("default")
    double defaultValue;
    double min;
    double max;

    public NmosProps(double defaultValue, double min, double max){
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean equals(Object obj){
        final NmosProps other = (NmosProps) obj;
        if(other.min == this.min && other.max == this.max
                && other.defaultValue == this.defaultValue){
            return true;
        }
        return false;
    }
}

class NmosNetlist{
    String drain;
    String gate;
    String source;

    public NmosNetlist(String drain, String gate, String source){
        this.drain = drain;
        this.gate = gate;
        this.source = source;
    }

    @Override
    public boolean equals(Object obj){
        final NmosNetlist other = (NmosNetlist)obj;
        if(other.drain.equals(this.drain) && other.gate.equals(this.gate)
                && other.source.equals(this.source)){
            return true;
        }
        return false;
    }
}

class Nmos extends Component{
    @SerializedName("m(l)")
    private NmosProps props;
    private NmosNetlist netlist;

    public Nmos(String id, NmosProps props, NmosNetlist netlist){
        super(ComponentType.NMOS, id);
        this.props = props;
        this.netlist = netlist;
    }

    public NmosProps getNmosProps(){
        return props;
    }

    public NmosNetlist getNetlist(){
        return netlist;
    }

    public LinkedList<String> getNodesIds(){
        LinkedList<String> nodesIds = new LinkedList<String>();
        nodesIds.add(this.netlist.drain);
        nodesIds.add(this.netlist.gate);
        nodesIds.add(this.netlist.source);
        return nodesIds;
    }

    public NmosProps setResistance(NmosProps props){
        this.props = props;
        return this.props;
    }

    public NmosNetlist setNetlist(NmosNetlist netlist){
        this.netlist = netlist;
        return this.netlist;
    }
}
