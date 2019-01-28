package models;

public class SpaceProbe {

    private String code;
    private CardinalDirection direction;
    private AxisPosition position;

    public SpaceProbe(String code, CardinalDirection direction, AxisPosition position) {
        this.code = code;
        this.direction = direction;
        this.position = position;
    }

    public CardinalDirection getDirection() {
        return direction;
    }

    public AxisPosition getPosition() {
        return position;
    }

    public String getCode() {
        return code;
    }

    //    public CardinalDirection getDirection() {
//        return direction;
//    }
//
//    public void setDirection(CardinalDirection direction) {
//        direction = direction;
//    }
//
//    public Integer getPosX() {
//        return posX;
//    }
//
//    public void setPosX(Integer posX) {
//        posX = posX;
//    }
//
//    public Integer getPosY() {
//        return posY;
//    }
//
//    public void setPosY(Integer posY) {
//        posY = posY;
//    }
}
