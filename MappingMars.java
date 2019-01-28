import helpers.Helpers;
import models.AxisPosition;
import models.CardinalDirection;
import models.Planet;
import models.SpaceProbe;

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

                    //SELECT SPACE PROBE
                    if (probesOnMars.size() > 0) {
                        probesOnMars.get(probesOnMars.size() - 1);
                        Integer probeCodeToSelect = keyCode - 48;
                        Optional<SpaceProbe> selectedSpaceProbe = probesOnMars.stream().filter(spaceProbe -> spaceProbe.getCode().contains("P" + probeCodeToSelect)).findFirst();
                        if (selectedSpaceProbe.isPresent()) {
                            currentSpaceProbe = selectedSpaceProbe.get();

                        }
                    }

                    //LAND NEW SPACE PROBE
                    if (keyCode.equals(67)) {

                        if (newProbeCode < 9) {
                            newProbeCode++;
                            Helpers.addSpaceProbeToPlanet("P" + newProbeCode, mars, probesOnMars);
                            currentSpaceProbe = probesOnMars.get(probesOnMars.size() - 1);
                        }

                    }

                    //SEND COMMAND TO SPACE PROBE
                    if (probesOnMars.size() > 0 && currentSpaceProbe != null) {

                        System.out.println("Space Probe selected: " + currentSpaceProbe.getCode());

                        probesOnMars.remove(currentSpaceProbe);

                        CardinalDirection currentProbeDirection = currentSpaceProbe.getDirection();
                        AxisPosition currentProbePosition = currentSpaceProbe.getPosition();

                        AxisPosition newPosition = currentProbePosition;
                        CardinalDirection newDirection = currentProbeDirection;

                        if (keyCode.equals(38)) {
                            newPosition = Helpers.getNewPosition(currentProbePosition, currentProbeDirection, mars);
                        }

                        if (keyCode.equals(37)) {
                            newDirection = Helpers.getNewLeftDirection(currentProbeDirection);
                        }

                        if (keyCode.equals(39)) {
                            newDirection = Helpers.getNewRightDirection(currentProbeDirection);
                        }

                        currentSpaceProbe = new SpaceProbe(currentSpaceProbe.getCode(), newDirection, newPosition);

                        probesOnMars.add(currentSpaceProbe);

                        Helpers.drawPlanet(mars, probesOnMars);
                    }

                } catch (Exception ex) {
                    throw ex;
                }
            }
        });
    }

    public static void main(String[] args) {
        try {

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
}
