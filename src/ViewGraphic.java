
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * Bresenham's Line Algorithm test
 * 
 * @author leo
 */
public class ViewGraphic extends JPanel {
    
    private double angle;
    private final Color[] colors = { Color.BLACK, Color.WHITE, Color.RED };
    
    public void startMainLoop() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                repaint();
            }
        }, 100, 1000 / 30);
    }
    
    private void update() {
        BresenhamLine.clearScreen();
        for (int a = 0; a < 360; a += 15) {
            int x = (int) (50 * Math.cos(Math.toRadians(a + angle)));
            int y = (int) (50 * Math.sin(Math.toRadians(a + angle)));
            angle += 0.03;
            BresenhamLine.drawLine(80, 55, 80 + x, 55 + y);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        renderScreen(g);
    }
    
    private void renderScreen(Graphics g) {
        for (int y = 0; y < BresenhamLine.screen.length; y++) {
            for (int x = 0; x < BresenhamLine.screen[0].length; x++) {
                g.setColor(colors[BresenhamLine.screen[y][x]]);
                g.fillRect(x * 5, y * 5, 5, 5);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewGraphic view = new ViewGraphic();
            JFrame frame = new JFrame();
            frame.setTitle("Bresenham's Line Algorithm Test");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(view);
            frame.setVisible(true);
            view.requestFocus();
            view.startMainLoop();
        });
    }
    
}
