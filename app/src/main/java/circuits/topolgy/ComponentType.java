package circuits.topolgy;

import com.google.gson.annotations.SerializedName;

enum ComponentType{
    @SerializedName("resistor")
    RESISTOR("resistor"),

    @SerializedName("nmos")
    NMOS("nmos");

    private final String value;
    private ComponentType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return this.getValue();
    }
}
