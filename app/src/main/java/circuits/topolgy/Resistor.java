package circuits.topolgy;

import java.util.LinkedList;

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

    public LinkedList<String> getNodesIds(){
        LinkedList<String> nodesIds = new LinkedList<String>();
        nodesIds.add(this.netlist.t1);
        nodesIds.add(this.netlist.t2);
        return nodesIds;
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

    @Override
    public boolean equals(Object obj){
        final ResistorProps other = (ResistorProps) obj;
        if(other.min == this.min && other.max == this.max
                && other.defaultValue == this.defaultValue){
            return true;
        }
        return false;
    }
}

class ResistorNetlist {
    String t1;
    String t2;

    public ResistorNetlist(String t1, String t2){
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean equals(Object obj){
        final ResistorNetlist other = (ResistorNetlist) obj;
        if(other.t1.equals(this.t1)&& other.t2.equals(this.t2)){
            return true;
        }
        return false;
    }
}
