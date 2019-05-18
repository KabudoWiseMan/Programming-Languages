import javax.swing.*;
import java.awt.*;

/**
 * Created by vsevolodmolchanov on 14.03.17.
 */
public class CanvasPanel extends JPanel {
    private int a = 15;
    private int ra = 50;
    private int ga = 50;
    private int ba = 50;

    private int b = 36;
    private int rb = 100;
    private int gb = 100;
    private int bb = 100;

    private int rc = 200;
    private int gc = 200;
    private int bc = 200;

    //for a
    public void setCatheterA(int a) {
        this.a = a;
        repaint();
    }

    public void setRA(int ra) {
        this.ra = ra;
        repaint();
    }
    public void setGA(int ga) {
        this.ga = ga;
        repaint();
    }
    public void setBA(int ba) {
        this.ba = ba;
        repaint();
    }

    //for b
    public void setCatheterB(int b) {
        this.b = b;
        repaint();
    }

    public void setRB(int rb) {
        this.rb = rb;
        repaint();
    }
    public void setGB(int gb) {
        this.gb = gb;
        repaint();
    }
    public void setBB(int bb) {
        this.bb = bb;
        repaint();
    }

    //for c

    public void setRC(int rc) {
        this.rc = rc;
        repaint();
    }
    public void setGC(int gc) {
        this.gc = gc;
        repaint();
    }
    public void setBC(int bc) {
        this.bc = bc;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillPolygon(new int[] {100, 100, 100 + b}, new int[] {100, 100 + a, 100}, 3);
        g.setColor(new Color(ra, ga, ba));
        g.fillRect(100 - a, 100, a, a);
        g.setColor(new Color(rb, gb, bb));
        g.fillRect(100, 100 - b, b, b);
        g.setColor(new Color(rc, gc, bc));
        g.fillPolygon(new int[] {100, 100 + a, 100 + a + b, 100 + b}, new int[] {100 + a, 100 + a + b, 100 + b, 100}, 4);
    }
}
