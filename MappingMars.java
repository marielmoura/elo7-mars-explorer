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

public class MappingMars extends JFrame {

    static List<SpaceProbe> probesOnMars = new ArrayList<>();
    static Planet mars = new Planet(20);
    static Integer newProbeCode = 0;

    MappingMars() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                try {

                    Helpers.clearScreen();
                    Integer keyCode = e.getKeyCode();

                    if (keyCode.equals(67)) {

                        newProbeCode++;
                        Helpers.addSpaceProbeToPlanet("P" + newProbeCode, mars, probesOnMars);

                    }

                    if (probesOnMars.size() > 0) {

                        SpaceProbe lastProbeLanded = probesOnMars.get(probesOnMars.size() - 1);

                        probesOnMars.remove(lastProbeLanded);

                        CardinalDirection currentProbeDirection = lastProbeLanded.getDirection();
                        AxisPosition currentProbePosition = lastProbeLanded.getPosition();

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

                        lastProbeLanded = new SpaceProbe(lastProbeLanded.getCode(), newDirection, newPosition);

                        probesOnMars.add(lastProbeLanded);
                    }

                    Helpers.drawPlanet(mars, probesOnMars);

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
