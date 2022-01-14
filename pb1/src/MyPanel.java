import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    Circle circle;
    RandomCircle randomCircle;
    public MyPanel() {

    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public void setRandomCircle(RandomCircle randomCircle) {
        this.randomCircle = randomCircle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        circle.draw(g);
        randomCircle.draw(g);
    }
}
