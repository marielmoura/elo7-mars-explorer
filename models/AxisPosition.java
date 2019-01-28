package models;

public class AxisPosition {

    private Integer posX;
    private Integer posY;
    private Boolean isMapped;

    public AxisPosition(Integer posX, Integer posY, boolean isMapped) {
        this.posX = posX;
        this.posY = posY;
        this.isMapped = isMapped;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public Boolean getMapped() {
        return isMapped;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.posX.equals(((AxisPosition) obj).posX)
                && this.posY.equals(((AxisPosition) obj).posY)
                && this.isMapped.equals(((AxisPosition) obj).isMapped));
    }

}