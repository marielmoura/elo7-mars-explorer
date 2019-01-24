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
}