import helpers.Helpers;
import models.AxisPosition;
import models.CardinalDirection;
import models.Planet;
import models.SpaceProbe;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MappingMars extends JFrame {

    static AxisPosition probePosition = new AxisPosition(0, 0);
    static SpaceProbe currentSpaceProbe = new SpaceProbe(CardinalDirection.N, probePosition);
    static Planet mars = new Planet(20);

    MappingMars() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    Integer keyCode = e.getKeyCode();

                    CardinalDirection currentProbeDirection = currentSpaceProbe.getDirection();
                    AxisPosition currentProbePosition = currentSpaceProbe.getPosition();

                    AxisPosition newPosition = Helpers.getNewPosition(keyCode, currentProbePosition, currentProbeDirection, mars);
                    CardinalDirection newDirection = Helpers.getNewDirection(keyCode, currentProbeDirection);

                    currentSpaceProbe = new SpaceProbe(newDirection, newPosition);

                    Helpers.drawPlanetSurface(currentSpaceProbe, mars);

                } catch (Exception ex) {
                    throw ex;
                }
            }
        });
    }

    public static void main(String[] args) {
        try {

            Helpers.drawPlanetSurface(currentSpaceProbe, mars);
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
