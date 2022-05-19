package circuits.topolgy;

import com.google.gson.annotations.SerializedName;

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
}

class NmosNetlist{
    String drain;
    String gate;
    String source;

    public NmosNetlist(String drain, String gate, String source){
        this.drain = drain;
        this.gate = gate;
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

    public NmosProps getResistance(){
        return props;
    }

    public NmosNetlist getNetlist(){
        return netlist;
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
