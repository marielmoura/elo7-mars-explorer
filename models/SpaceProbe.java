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
}
