// filepath: SimpleGraphics/src/SimpleGraphics.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class SimpleGraphics extends JPanel implements ActionListener, MouseListener {

    int x = 0, y = 0;
    int dx = 3, dy = 3; 
    Timer timer;
    ImageIcon icon;
    Image backgroundImage;

    public SimpleGraphics() {
        try {
            icon = new ImageIcon(new URL("https://i.ibb.co/bYJDS72/Pixel-Gif-Png.gif"));
            backgroundImage = new ImageIcon(new URL("https://i.ibb.co/hFHv59C6/Pngtree-backyard-garden-trees-terrace-with-15872713-1.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        timer = new Timer(10, this);
        timer.start();
        addMouseListener(this);
        setBorder(BorderFactory.createLineBorder(Color.pink, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        icon.paintIcon(this, g, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        if (x < 0 || x + icon.getIconWidth() > getWidth()) {
            dx = -dx;
        }
        if (y < 0 || y + icon.getIconHeight() > getHeight()) {
            dy = -dy;
        }

        repaint(); // ask panel to redraw
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("Mouse clicked at: " + mouseX + ", " + mouseY);

        // Check if click is inside the image bounds
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();

        if (mouseX >= x && mouseX <= x + iconWidth &&
            mouseY >= y && mouseY <= y + iconHeight) {

            if (timer.isRunning()) {
                timer.stop();
                System.out.println("Image clicked! Animation Stopped.");
            } else {
                timer.start();
                System.out.println("Image clicked! Animation Resumed.");
            }
        }
    }

    // Unused mouse events
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Animated Image");
        SimpleGraphics panel = new SimpleGraphics();
        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
