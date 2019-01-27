import helpers.Helpers;
import javafx.scene.chart.Axis;
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

    static final AxisPosition probePosition = new AxisPosition(0, 0);
    static SpaceProbe currentSpaceProbe = new SpaceProbe(CardinalDirection.N, probePosition);
    static List<AxisPosition> mappedPlanetPositions = new ArrayList<>();

    static final Planet mars = new Planet(60);

    MappingMars() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                try {

                    Helpers.clearScreen();
                    Integer keyCode = e.getKeyCode();

                    CardinalDirection currentProbeDirection = currentSpaceProbe.getDirection();
                    AxisPosition currentProbePosition = currentSpaceProbe.getPosition();

                    if (!mappedPlanetPositions.contains(currentProbePosition)) {
                        mappedPlanetPositions.add(currentProbePosition);
                    }

                    AxisPosition newPosition = Helpers.getNewPosition(keyCode, currentProbePosition, currentProbeDirection, mars);
                    CardinalDirection newDirection = Helpers.getNewDirection(keyCode, currentProbeDirection);

                    currentSpaceProbe = new SpaceProbe(newDirection, newPosition);

                    Helpers.drawPlanetSurface(currentSpaceProbe, mars, mappedPlanetPositions);

                } catch (Exception ex) {
                    throw ex;
                }
            }
        });
    }

    public static void main(String[] args) {
        try {

            Helpers.drawPlanetSurface(currentSpaceProbe, mars, mappedPlanetPositions);
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
