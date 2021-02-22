import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class PaintPanel extends JPanel {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    private static final Color BACK_COLOR = Color.WHITE;

    private int x1, y1, x2, y2;
    private BufferedImage image;
    private Graphics2D myGraphics;
    protected static Color colorOfLines;

    public PaintPanel() {
        setBackground(BACK_COLOR);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDoubleBuffered(false);

        MyMouseHandler handler = new MyMouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    public void setColorOfLines(Color color) {
        colorOfLines = color;
    }

    protected void paintComponent(Graphics graphics) {
        if (image == null) {
            image = (BufferedImage) createImage(getSize().width, getSize().height);
            myGraphics = (Graphics2D) image.getGraphics();
            myGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        graphics.drawImage(image, 0, 0, null);
    }


    public void clear() {
        myGraphics.setPaint(Color.white);
        myGraphics.fillRect(0, 0, getSize().width, getSize().height);
        myGraphics.setPaint(Color.black);
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(Image im) {
        image = (BufferedImage) im;
        repaint();
    }

    private class MyMouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            myGraphics.setColor(colorOfLines);
        }

        public void mouseDragged(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();

            myGraphics.drawLine(x1, y1, x2, y2);
            repaint();
            x1 = x2;
            y1 = y2;
        }
    }
}