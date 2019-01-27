package models;

public class AxisPosition {

    private Integer posX;
    private Integer posY;

    public AxisPosition(Integer posX, Integer posY){
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.posX.equals(((AxisPosition) obj).posX)
                && this.posY.equals(((AxisPosition) obj).posY));
    }

}