package ImageXORingTool;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SwingProgressBarExample extends JPanel {

  JProgressBar pbar;
  JLabel label;

  static final int MY_MINIMUM = 0;

  static final int MY_MAXIMUM = 100;

  public SwingProgressBarExample() {
    pbar = new JProgressBar();
    pbar.setMinimum(MY_MINIMUM);
    pbar.setMaximum(MY_MAXIMUM);
    label = new JLabel("________________________");
    add(pbar);
    add(label);
  }

  public void updateBar(int newValue) {
    pbar.setValue(newValue);
  }
  
  public void setText(String t){
	  this.label.setText(t);
  }

}
