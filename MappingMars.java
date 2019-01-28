import helpers.Helpers;
import models.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MappingMars extends JFrame {

    static List<SpaceProbe> probesOnMars = new ArrayList<>();
    static Planet mars = new Planet(20);
    static Integer newProbeCode = 0;
    static SpaceProbe currentSpaceProbe = null;

    MappingMars() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                try {

                    Helpers.clearScreen();

                    Integer keyCode = e.getKeyCode();

                    selectSpaceProbe(keyCode);

                    landNewSpaceProbe(keyCode, Optional.empty());

                    moveSpaceProbe(keyCode);

                    spinSpaceProbe(keyCode);

                    Helpers.drawPlanet(mars, probesOnMars);

                } catch (Exception ex) {
                    throw ex;
                }
            }
        });
    }

    public static void main(String[] args) {
        try {

            AxisPosition landingPosition = new AxisPosition(1, 2, true);
            landNewSpaceProbe(KeyCommand.LandProbe.getCode(), Optional.of(landingPosition));
//            spinSpaceProbe(KeyCommand.SpinLeft.getCode());
//            moveSpaceProbe(KeyCommand.Move.getCode());
//            spinSpaceProbe(KeyCommand.SpinLeft.getCode());
//            moveSpaceProbe(KeyCommand.Move.getCode());
//            spinSpaceProbe(KeyCommand.SpinLeft.getCode());
//            moveSpaceProbe(KeyCommand.Move.getCode());
//            spinSpaceProbe(KeyCommand.SpinLeft.getCode());
//            moveSpaceProbe(KeyCommand.Move.getCode());
//            moveSpaceProbe(KeyCommand.Move.getCode());
            Helpers.drawPlanet(mars, probesOnMars);

            SwingUtilities.invokeLater(() -> {
                MappingMars f = new MappingMars();
                f.setFocusable(true);
                f.setVisible(true);
            });

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void landNewSpaceProbe(Integer keyCode, Optional<AxisPosition> landingPosition) {

        if (keyCode.equals(KeyCommand.LandProbe.getCode())) {

            if (newProbeCode < 9) {
                newProbeCode++;
                AxisPosition unmappedPosition = Helpers.getPlanetUnmappedPosition(mars);

                if (landingPosition.isPresent()) {
                    unmappedPosition = landingPosition.get();
                }

                Helpers.addSpaceProbeToPlanet("P" + newProbeCode, mars, probesOnMars, unmappedPosition);
                currentSpaceProbe = probesOnMars.get(probesOnMars.size() - 1);
            }

        }

    }

    public static void spinSpaceProbe(Integer keyCode) {

        if (probesOnMars.size() > 0 && currentSpaceProbe != null) {

            System.out.println("Space Probe selected: " + currentSpaceProbe.getCode());

            probesOnMars.remove(currentSpaceProbe);

            CardinalDirection currentProbeDirection = currentSpaceProbe.getDirection();
            CardinalDirection newDirection = currentProbeDirection;

            //SEND COMMAND TO SPIN LEFT
            if (keyCode.equals(KeyCommand.SpinLeft.getCode())) {
                newDirection = Helpers.getNewLeftDirection(currentProbeDirection);
            }

            //SEND COMMAND TO SPIN RIGHT
            if (keyCode.equals(KeyCommand.SpinRight.getCode())) {
                newDirection = Helpers.getNewRightDirection(currentProbeDirection);
            }

            currentSpaceProbe = new SpaceProbe(
                    currentSpaceProbe.getCode(), newDirection, currentSpaceProbe.getPosition());

            probesOnMars.add(currentSpaceProbe);
        }
    }

    public static void moveSpaceProbe(Integer keyCode) {

        if (probesOnMars.size() > 0 && currentSpaceProbe != null) {

            System.out.println("Space Probe selected: " + currentSpaceProbe.getCode());

            probesOnMars.remove(currentSpaceProbe);

            AxisPosition newPosition = currentSpaceProbe.getPosition();

            //SEND COMMAND TO MOVE SPACE
            if (keyCode.equals(KeyCommand.Move.getCode())) {
                newPosition = Helpers.getNewPosition(currentSpaceProbe.getPosition(), currentSpaceProbe.getDirection(), mars);
            }

            currentSpaceProbe = new SpaceProbe(
                    currentSpaceProbe.getCode(), currentSpaceProbe.getDirection(), newPosition);

            probesOnMars.add(currentSpaceProbe);
        }
    }

    public static void selectSpaceProbe(Integer keyCode){

        if (probesOnMars.size() > 0) {
            probesOnMars.get(probesOnMars.size() - 1);
            Integer probeCodeToSelect = keyCode - 48;
            Optional<SpaceProbe> selectedSpaceProbe = probesOnMars.stream().filter(spaceProbe -> spaceProbe.getCode().contains("P" + probeCodeToSelect)).findFirst();
            if (selectedSpaceProbe.isPresent()) {
                currentSpaceProbe = selectedSpaceProbe.get();

            }
        }

    }
}
