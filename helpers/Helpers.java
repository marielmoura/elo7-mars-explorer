package helpers;

import models.AxisPosition;
import models.CardinalDirection;
import models.Planet;
import models.SpaceProbe;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Helpers {


    public static void showInfos(Planet planet) {

        Integer allPlanetPositions = planet.getPositions().size();
        Integer unmappedPlanetPositions = planet.getPositions().stream().filter(x -> x.getMapped()).collect(Collectors.toList()).size();

        Double planetExploredPercentage = ((double) unmappedPlanetPositions / (double) allPlanetPositions) * 100;
        System.out.println("[SPACE PROBE COMMANDS]");
        System.out.println("[C] key: Land new space probe");
        System.out.println("[Up] key: Move space probe");
        System.out.println("[Right] key: Turn right space probe");
        System.out.println("[Left] key: Turn left space probe");
        System.out.println("[1-9] key: Select space probe");
        System.out.println("Planet explored: " + new DecimalFormat(".##").format(planetExploredPercentage) + " %");

    }

    public static AxisPosition getNewPosition(AxisPosition position, CardinalDirection direction, Planet planet) {
        try {
            Integer newPosX = position.getPosX();
            Integer newPosY = position.getPosY();

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

            System.out.println("Moving probe... PosX: " + newPosX + " PosY: " + newPosY);

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

            AxisPosition newPosition = new AxisPosition(newPosX, newPosY, true);

            setPlanetMappedPosition(newPosition, planet);

            return newPosition;

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void setPlanetMappedPosition(AxisPosition position, Planet planet) {

        List<AxisPosition> planetPositions = planet.getPositions();

        for (AxisPosition planetPosition : planetPositions) {
            if (planetPosition.getPosY() == position.getPosY() && planetPosition.getPosX() == position.getPosX()) {
                planetPositions.remove(planetPosition);
                AxisPosition _planetPosition = new AxisPosition(planetPosition.getPosX(), planetPosition.getPosY(), true);
                planetPositions.add(_planetPosition);
                break;
            }
        }

        planet.setPositions(planetPositions);

    }

    public static AxisPosition getPlanetUnmappedPosition(Planet planet) {

        AxisPosition positionToMap = new AxisPosition(0, 0, true);

        if (planet.getPositions().size() > 0) {
            Optional<AxisPosition> _positionToMap = planet.getPositions().stream().filter(x -> !x.getMapped()).findFirst();

            if (_positionToMap.isPresent()) {
                positionToMap = new AxisPosition(_positionToMap.get().getPosX(), _positionToMap.get().getPosY(), true);
            }
        }

        return positionToMap;
    }

    public static void addSpaceProbeToPlanet(String probeCode, Planet planet, List<SpaceProbe> probesOnSoil, AxisPosition positionToMap) {

        setPlanetMappedPosition(positionToMap, planet);
        SpaceProbe newProbe = new SpaceProbe(probeCode, CardinalDirection.N, positionToMap);
        probesOnSoil.add(newProbe);

    }

    public static void drawPlanet(Planet planet, List<SpaceProbe> probesOnSoil) {

        System.out.println("");
        Helpers.showInfos(planet);
        System.out.println("");

        for (int posY = 0; posY < planet.getSurfaceSizeY(); posY++) {

            StringBuilder marsSurfaceLine = new StringBuilder();

            for (int posX = 0; posX < planet.getSurfaceSizeX(); posX++) {

                String positionDraw = "===";
                AxisPosition currentPosition = new AxisPosition(posX, posY, true);

                if (planet.getPositions().contains(currentPosition)) {
                    positionDraw = "XXX";
                }

                for (SpaceProbe probeOnSoil : probesOnSoil) {
                    if (currentPosition.equals(probeOnSoil.getPosition())) {
                        positionDraw = probeOnSoil.getCode() + probeOnSoil.getDirection();
                        continue;
                    }
                }

                marsSurfaceLine.append(positionDraw);
            }

            System.out.println("Y" + posY + " " + marsSurfaceLine);
        }

        System.out.println("");

    }

    public static void clearScreen() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public static CardinalDirection getNewRightDirection(CardinalDirection currentDirection) {
        try {

            CardinalDirection newRightDirection = currentDirection;

            switch (currentDirection) {
                case N:
                    newRightDirection = CardinalDirection.E;
                    break;
                case E:
                    newRightDirection = CardinalDirection.S;
                    break;
                case S:
                    newRightDirection = CardinalDirection.W;
                    break;
                case W:
                    newRightDirection = CardinalDirection.N;
                    break;
            }

            System.out.println("Turn probe right... current direction: " + currentDirection);

            return newRightDirection;

        } catch (Exception ex) {
            throw ex;
        }

    }

    public static CardinalDirection getNewLeftDirection(CardinalDirection currentDirection) {
        try {

            CardinalDirection newLeftDirection = currentDirection;

            switch (currentDirection) {
                case N:
                    newLeftDirection = CardinalDirection.W;
                    break;
                case E:
                    newLeftDirection = CardinalDirection.N;
                    break;
                case S:
                    newLeftDirection = CardinalDirection.E;
                    break;
                case W:
                    newLeftDirection = CardinalDirection.S;
                    break;
            }

            System.out.println("Turn probe left... current direction: " + currentDirection);

            return newLeftDirection;

        } catch (
                Exception ex) {
            throw ex;
        }

    }

}
