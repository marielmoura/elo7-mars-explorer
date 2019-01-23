import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        try {

            Integer marsSurfaceSizeX = 20;
            Integer marsSurfaceSizeY = marsSurfaceSizeX / 2;

            Integer landingPosX = ThreadLocalRandom.current().nextInt(marsSurfaceSizeX - 1);
            Integer landingPosY = ThreadLocalRandom.current().nextInt(marsSurfaceSizeY - 1);

            System.out.println("Reading mars surface... AreaX: " + marsSurfaceSizeX + " AreaY: " + marsSurfaceSizeY);
            System.out.println("Sending coordenates to landing... PosX: " + landingPosX + " PosY: " + landingPosY);

            for (Integer posY = 0; posY < marsSurfaceSizeY; posY++) {

                StringBuilder marsSurface_X = new StringBuilder();

                for (Integer posX = 0; posX < marsSurfaceSizeX; posX++) {

                    if (posX.equals(landingPosX) && posY.equals(landingPosY)) {
                        marsSurface_X.append("XX");
                    } else {
                        marsSurface_X.append("==");
                    }

                }

                System.out.println("Y" + posY + " " + marsSurface_X);
            }

        } catch (Exception ex) {

            throw ex;

        }
    }
}
