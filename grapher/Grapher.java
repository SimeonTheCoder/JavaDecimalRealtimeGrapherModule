package grapher;

import grapher.DataList;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Grapher extends JPanel {
    private JFrame frame;
    private DataList list;

    // Controls the background color of the graph
    public Color BG_COLOR;

    // Controls the color of the line
    public Color GRAPH_COLOR;

    // Controls the color of the average
    public Color MEAN_COLOR;

    // Controls the speed of the graph, in milliseconds
    public long DISPLAY_RATE;

    public void setup(String title, int sizeX, int sizeY) {
        frame = new JFrame(title);

        frame.setSize(sizeX, sizeY);

        frame.add(this);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public Grapher(String title, int sizeX, int sizeY) {
        setup(title, sizeX, sizeY);

        BG_COLOR = Color.WHITE;
        GRAPH_COLOR = Color.GRAY;
        MEAN_COLOR = Color.GREEN;

        this.list = new DataList();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        int startOffset = Math.max(0, list.getData().size() - frame.getWidth() / 5);

        int dataCount = 0;
        double average = 0.0;

        g.setColor(GRAPH_COLOR);

        for(int i = startOffset; i < list.getData().size() - 1; i ++) {
            double curr = list.getData().get(i);
            double next = list.getData().get(i + 1);

            dataCount ++;
            average += curr;

            g.drawLine((i - startOffset) * 5, (int) curr, (i - startOffset) * 5 + 5, (int) next);
        }

        average /= dataCount;

        g.setColor(MEAN_COLOR);

        g.drawString("Average: " + String.format("%.2f", average), 20, 20);

        g.drawLine(0, (int) average, frame.getWidth(), (int) average);

        try {
            TimeUnit.MILLISECONDS.sleep(DISPLAY_RATE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        repaint();
    }

    //Used to input data into the grapher
    public void feedData(double data) {
        this.list.add(data);
    }
}
