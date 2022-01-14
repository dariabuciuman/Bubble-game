import java.awt.*;
import java.util.Random;

public class RandomCircle implements Runnable{
    private Random random;
    private int xCoords;
    private int yCoords;
    private int radius;
    private int colors;
    private Color color;
    private boolean threadStarted = false;
    private MyPanel panel;

    public RandomCircle(MyPanel panel) {
        random = new Random();
        this.panel = panel;
        xCoords = random.nextInt(500);
        yCoords = random.nextInt(400);
        radius = 50;
        colors = random.nextInt(4);
    }

    public int getxCoords() {
        return xCoords;
    }

    public int getyCoords() {
        return yCoords;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public void setxCoords(int xCoords) {
        this.xCoords = xCoords;
    }

    public void setyCoords(int yCoords) {
        this.yCoords = yCoords;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    @Override
    public void run() {
        threadStarted = true;
        while (threadStarted) {
            panel.repaint();
            try {
                radius -= 1;

                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isThreadStarted() {
        return threadStarted;
    }

    public void draw(Graphics g) {
        switch (colors) {
            case 0 -> color = Color.YELLOW;
            case 1 -> color = Color.RED;
            case 2 -> color = Color.BLUE;
            default -> color = Color.GREEN;
        }
        g.setColor(color);
        g.drawOval(xCoords, yCoords, radius, radius);
    }
    public void stop() {
        threadStarted = false;
    }
}
