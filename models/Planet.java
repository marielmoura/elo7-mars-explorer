package models;

import java.util.ArrayList;
import java.util.List;

public class Planet {

    private Integer surfaceSizeX;
    private Integer surfaceSizeY;
    private List<AxisPosition> positions;

    public Planet(Integer surfaceSizeX) {
        this.surfaceSizeX = 20;
        this.surfaceSizeY = this.surfaceSizeX / 2;
        this.positions = new ArrayList<>();

        for (int posY = 0; posY < surfaceSizeY; posY++) {
            for (int posX = 0; posX < surfaceSizeX; posX++) {
                AxisPosition planetPosition = new AxisPosition(posX, posY, false);
                positions.add(planetPosition);
            }
        }
    }

    public List<AxisPosition> getPositions() {
        return positions;
    }

    public Integer getSurfaceSizeX() {
        return surfaceSizeX;
    }

    public Integer getSurfaceSizeY() {
        return surfaceSizeY;
    }

    public void setPositions(List<AxisPosition> positions) {
        this.positions = positions;
    }
}
