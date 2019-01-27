package helpers;

import models.AxisPosition;
import models.CardinalDirection;
import models.Planet;
import models.SpaceProbe;

import java.util.List;

public class Helpers {

    public static AxisPosition getNewPosition(Integer keyCode, AxisPosition position, CardinalDirection direction, Planet planet) {
        try {
            Integer newPosX = position.getPosX();
            Integer newPosY = position.getPosY();

            if (keyCode.equals(38)) {

                switch (direction) {
                    case N:
                        newPosY = newPosY > 0 ? newPosY - 1 : 0;
                        break;
                    case E:
                        newPosX++;
                        break;
                    case S:
                        newPosY++;
                        break;
                    case W:
                        newPosX = newPosX > 0 ? newPosX - 1 : 0;
                        break;
                }

                System.out.println("Move probe... PosX: " + newPosX + " PosY: " + newPosY);
            }

            Integer planetSizeX = planet.getSurfaceSizeX() - 1;

            if (newPosX > planetSizeX) {
                System.out.println("[WARNING] AxisX out of range coordinates... PosX: " + newPosX + " PosY: " + newPosY);
                newPosX = planetSizeX;
            }

            Integer planetSizeY = planet.getSurfaceSizeY() - 1;

            if (newPosY > planetSizeY) {
                System.out.println("[WARNING] AxisY out of range coordinates... PosX: " + newPosX + " PosY: " + newPosY);
                newPosY = planetSizeY;
            }

            AxisPosition newPosition = new AxisPosition(newPosX, newPosY);

            return newPosition;

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void clearScreen() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public static CardinalDirection getNewDirection(Integer keyCode, CardinalDirection currentDirection) {
        try {
            CardinalDirection newDirection = currentDirection;

            if (keyCode.equals(37)) {

                switch (currentDirection) {
                    case N:
                        newDirection = CardinalDirection.W;
                        break;
                    case E:
                        newDirection = CardinalDirection.N;
                        break;
                    case S:
                        newDirection = CardinalDirection.E;
                        break;
                    case W:
                        newDirection = CardinalDirection.S;
                        break;
                }

                System.out.println("Turn probe left... current currentDirection: " + currentDirection);
            }

            if (keyCode.equals(39)) {

                switch (currentDirection) {
                    case N:
                        newDirection = CardinalDirection.E;
                        break;
                    case E:
                        newDirection = CardinalDirection.S;
                        break;
                    case S:
                        newDirection = CardinalDirection.W;
                        break;
                    case W:
                        newDirection = CardinalDirection.N;
                        break;
                }

                System.out.println("Turn probe right... current currentDirection: " + currentDirection);
            }

            return newDirection;

        } catch (Exception ex) {
            throw ex;
        }

    }

    public static void drawPlanetSurface(SpaceProbe spaceProbe, Planet planet, List<AxisPosition> mappedPositions) {
        try {
            System.out.println("Reading mars surface... AreaX: " + planet.getSurfaceSizeX() + " AreaY: " + planet.getSurfaceSizeY());
            System.out.println("Sending coordinates... PosX: " + spaceProbe.getPosition().getPosX() + " PosY: " + spaceProbe.getPosition().getPosY() + " Direction: " + spaceProbe.getDirection());

            for (Integer posY = 0; posY < planet.getSurfaceSizeY(); posY++) {

                StringBuilder marsSurfaceLine = new StringBuilder();

                for (Integer posX = 0; posX < planet.getSurfaceSizeX(); posX++) {

                    AxisPosition drawingPosition = new AxisPosition(posX, posY);

                    if (drawingPosition.equals(spaceProbe.getPosition())) {
                        marsSurfaceLine.append("[" + spaceProbe.getDirection().toString() + "]");
                    } else {

                        String planetPosition = mappedPositions.contains(drawingPosition) ? "XXX" : "===";
                        marsSurfaceLine.append(planetPosition);
                    }
                }

                System.out.println("Y" + posY + " " + marsSurfaceLine);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

}
