import java.awt.*;
import java.util.Random;

public class Circle implements Runnable {
    private boolean threadStarted = false;
    private MyPanel drawPanel;
    private int radius = 0;
    private int xCoords;
    private int yCoords;
    private int xVelocity = 1;
    private int yVelocity = 0;
    private Random random;

    public Circle(MyPanel drawPanel) {
        this.drawPanel = drawPanel;
        random = new Random();
        radius = 100;
        xCoords = random.nextInt(400);
        yCoords = random.nextInt(300);
    }

    @Override
    public void run() {
        threadStarted = true;
        System.out.println("thread started");
        while (threadStarted) {
            drawPanel.repaint();
            if(radius >= 600){
                threadStarted = false;
            }
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.move();
        }
    }

    public boolean isThreadStarted() {
        return threadStarted;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getxCoords() {
        return xCoords;
    }

    public int getyCoords() {
        return yCoords;
    }

    public void setThreadStarted(boolean threadStarted) {
        this.threadStarted = threadStarted;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    private void move() {
        if (xVelocity == 1 && xCoords + radius > drawPanel.getWidth()) {
            this.xVelocity = -1;
            this.yVelocity = 0;
        } else if (xVelocity == -1 && xCoords < 0) {
            this.xVelocity = 1;
            this.yVelocity = 0;
        } else if (yVelocity == -1 && yCoords < 0) {
            this.xVelocity = 0;
            this.yVelocity = 1;
        } else if (yVelocity == 1 && yCoords + radius > drawPanel.getHeight()) {
            this.xVelocity = 0;
            this.yVelocity = -1;
        }
        xCoords += this.xVelocity;
        yCoords += this.yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval(xCoords, yCoords, radius, radius);
    }
    public void stop() {
        threadStarted = false;
    }
}
