package circuits.topolgy;

import com.google.gson.annotations.SerializedName;

class Resistor extends Component{
    private ResistorProps resistance;
    private ResistorNetlist netlist;

    public Resistor(String id, ResistorProps resistance, ResistorNetlist netlist){
        super(ComponentType.RESISTOR, id);
        this.resistance = resistance;
        this.netlist = netlist;
    }

    public ResistorProps getResistance(){
        return resistance;
    }

    public ResistorNetlist getNetlist(){
        return netlist;
    }

    public ResistorProps setResistance(ResistorProps resistance){
        this.resistance = resistance;
        return this.resistance;
    }

    public ResistorNetlist setNetlist(ResistorNetlist netlist){
        this.netlist = netlist;
        return this.netlist;
    }

}

class ResistorProps {
    @SerializedName("default")
    double defaultValue;
    double min;
    double max;

    public ResistorProps(double defaultValue, double min, double max){
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
    }
}

class ResistorNetlist {
    String t1;
    String t2;

    public ResistorNetlist(String t1, String t2){
        this.t1 = t1;
        this.t2 = t2;
    }
}
