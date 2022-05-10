package st10068305.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * I learnt how to do text to ascii art from the following website,
 *
 * <a href="https://www.baeldung.com/ascii-art-in-java">ASCII Art in Java | Baeldung</a>
 */
public class TextToAscii {
    private final int WIDTH;
    private final int HEIGHT;
    private final int PADDING;

    public TextToAscii() {
        this.WIDTH = 100;
        this.HEIGHT = 20;
        this.PADDING = 10;
    }

    public TextToAscii(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.PADDING = 10;
    }

    public TextToAscii(int width, int height, int padding) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.PADDING = padding;
    }

    public BufferedImage draw(String text) {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(text, PADDING, PADDING * 5);

        return bufferedImage;
    }

    public void show(BufferedImage bufferedImage) {
        String CHARACTER = "*";

        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder builder = new StringBuilder();

            for (int x = 0; x < WIDTH; x++) {
                builder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : CHARACTER);
            }

            if (builder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(builder);
        }
    }
}
