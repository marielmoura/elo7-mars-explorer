package models;

public enum KeyCommand {
    SpinLeft(37), SpinRight(39), Move(38), LandProbe(67);

    private final int code;

    KeyCommand(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}