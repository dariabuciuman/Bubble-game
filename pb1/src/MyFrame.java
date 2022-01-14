import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;

public class MyFrame extends JFrame {
    private JFrame frame;
    private MyPanel drawPanel;
    private Circle circle;
    private RandomCircle randomCircle;
    private Random random = new Random();
    private Timer timer = new Timer();
    private int score = 0;
    private JLabel jLabel;
    public MyFrame() {
        frame = new JFrame("Joc Cerc");
        drawPanel = new MyPanel();
        circle = new Circle(drawPanel);
        drawPanel.setCircle(circle);

        frame.setSize(600, 500);
        drawPanel.setPreferredSize(new Dimension(600, 500));
        frame.add(drawPanel, BorderLayout.CENTER);
        drawPanel.setFocusable(true);
        frame.setVisible(true);
        drawPanel.setBackground(Color.BLACK);
        jLabel = new JLabel();
        jLabel.setSize(300, 300);
        jLabel.setForeground(Color.ORANGE);
        drawPanel.add(jLabel, BorderLayout.CENTER);

        randomCircle = new RandomCircle(drawPanel);
        drawPanel.setRandomCircle(randomCircle);

        addKeyListener();
        drawPanel.requestFocus();
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },2*60*1000);*/
        Thread thread = new Thread(circle);
        thread.start();
        Thread randomThread = new Thread(randomCircle);
        randomThread.start();

        while (thread.isAlive() && checkCircle(circle)==1) {
            if (checkCoords(circle, randomCircle) >= 0) {
                if (checkCoords(circle, randomCircle) == 0) {
                    circle.setRadius(circle.getRadius() + 10);
                    score +=1;
                    jLabel.setText("Your score is " + score);
                    System.out.println("Score is ..." + score);
                }
                else {
                    score -= 1;
                    jLabel.setText("Your score is " + score);
                    System.out.println("Score is ..." + score);
                    circle.setRadius(circle.getRadius() - 10);
                }
                random = new Random();
                randomCircle.setxCoords(random.nextInt(500));
                randomCircle.setyCoords(random.nextInt(400));
                randomCircle.setRadius(50);
                randomCircle.setColors(random.nextInt(4));
            } else if (randomCircle.getRadius() == 0) {
                random = new Random();
                randomCircle.setxCoords(random.nextInt(500));
                randomCircle.setyCoords(random.nextInt(400));
                randomCircle.setRadius(50);
                randomCircle.setColors(random.nextInt(4));
            }
        }
        if(checkCircle(circle) == -1) {
            System.out.println("You lost!");
            jLabel.setFont(new Font("Serif", Font.BOLD, 20));
            jLabel.setText("You lost! :(");
        } else if(checkCircle(circle) == 0) {
            jLabel.setFont(new Font("Serif", Font.BOLD, 20));
            jLabel.setText("You won! :)");
        }
        drawPanel.requestFocus();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private int checkCoords(Circle circle, RandomCircle randomCircle) {
        int xMax = circle.getxCoords() + circle.getRadius();
        int xMin = circle.getxCoords() - circle.getRadius();
        int yMax = circle.getyCoords() + circle.getRadius();
        int yMin = circle.getyCoords() - circle.getRadius();
        int xRMax = randomCircle.getxCoords() + randomCircle.getRadius();
        int xRMin = randomCircle.getxCoords() - randomCircle.getRadius();
        int yRMax = randomCircle.getyCoords() + randomCircle.getRadius();
        int yRMin = randomCircle.getyCoords() - randomCircle.getRadius();
        if (xRMin > xMin && xRMax < xMax && yRMin > yMin && yRMax < yMax) {
            if (randomCircle.getColor() == Color.RED)
                return 0;
            else return 1;
        }
        return -1;
    }

    private int checkCircle(Circle circle) {
        if(circle.getRadius() == 0)
            return -1;
        if(circle.getRadius() > 600) {
            return 0;
        }
        return  1;
    }

    private void addKeyListener() {
        //System.out.println("key listener...");
        drawPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int key = e.getKeyCode();
                //System.out.println("key pressed..." + key);
                if (key == 37) {
                    circle.setxVelocity(-1);
                    circle.setyVelocity(0);
                    drawPanel.repaint();
                }
                if (key == 38) {
                    circle.setxVelocity(0);
                    circle.setyVelocity(-1);
                    drawPanel.repaint();
                }
                if (key == 39) {
                    circle.setxVelocity(1);
                    circle.setyVelocity(0);
                    drawPanel.repaint();
                }
                if (key == 40) {
                    circle.setxVelocity(0);
                    circle.setyVelocity(1);
                    drawPanel.repaint();
                }
            }
        });
    }
}
