import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by vsevolodmolchanov on 14.03.17.
 */

public class PictureForm {
    private JPanel mainPanel;

    public PictureForm() {

        //for a
        aSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int a = (int) aSpinner.getValue();
                canvasPanel.setCatheterA(a);
            }
        });
        aSpinner.setValue(15);

        sra.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int ra = (int) sra.getValue();
                canvasPanel.setRA(ra);
            }
        });
        sra.setValue(50);

        sga.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int ga = (int) sga.getValue();
                canvasPanel.setGA(ga);
            }
        });
        sga.setValue(50);

        sba.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int ba = (int) sba.getValue();
                canvasPanel.setBA(ba);
            }
        });
        sba.setValue(50);

        //for b
        bSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int b = (int) bSpinner.getValue();
                canvasPanel.setCatheterB(b);
            }
        });
        bSpinner.setValue(36);

        srb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int rb = (int) srb.getValue();
                canvasPanel.setRB(rb);
            }
        });
        srb.setValue(100);

        sgb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int gb = (int) sgb.getValue();
                canvasPanel.setGB(gb);
            }
        });
        sgb.setValue(100);

        sbb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int bb = (int) sbb.getValue();
                canvasPanel.setBB(bb);
            }
        });
        sbb.setValue(100);

        //for c
        src.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int rc = (int) src.getValue();
                canvasPanel.setRC(rc);
            }
        });
        src.setValue(200);

        sgc.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int gc = (int) sgc.getValue();
                canvasPanel.setGC(gc);
            }
        });
        sgc.setValue(200);

        sbc.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int bc = (int) sbc.getValue();
                canvasPanel.setBC(bc);
            }
        });
        sbc.setValue(200);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pythagorean Pants");
        frame.setContentPane(new PictureForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JLabel a;
    private JSpinner aSpinner;
    private JLabel b;
    private JSpinner bSpinner;
    private CanvasPanel canvasPanel;
    private JLabel aColor;
    private JLabel bColor;
    private JLabel c;
    private JLabel cColor;
    private JLabel ra;
    private JLabel ga;
    private JLabel ba;
    private JLabel rb;
    private JLabel gb;
    private JLabel bb;
    private JLabel rc;
    private JLabel gc;
    private JLabel bc;
    private JSpinner sra;
    private JSpinner srb;
    private JSpinner src;
    private JSpinner sga;
    private JSpinner sgb;
    private JSpinner sgc;
    private JSpinner sba;
    private JSpinner sbb;
    private JSpinner sbc;

}
