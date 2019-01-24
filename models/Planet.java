package models;

public class Planet {

    private Integer surfaceSizeX;
    private Integer surfaceSizeY;

    public Planet(Integer surfaceSizeX){
        this.surfaceSizeX = 20;
        this.surfaceSizeY = this.surfaceSizeX / 2;
    }

    public Integer getSurfaceSizeX() {
        return surfaceSizeX;
    }

    public Integer getSurfaceSizeY() {
        return surfaceSizeY;
    }
}
