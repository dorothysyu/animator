package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a visual view of the model. It extends the JFrame class because
 * it appears as a pop-up window.
 */
public class VisualView extends JFrame implements IView {
  MyDrawingPanel drawingPanel;
  Timer timer;
  double frameCount;

  /**
   * This constructs this JFrame view. It adds a panel and a scrollbar and sets it visible.
   */
  public VisualView() {
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    drawingPanel = new MyDrawingPanel();
    drawingPanel.setPreferredSize(new Dimension(800, 800));
    JScrollPane scrollPane = new JScrollPane(drawingPanel);
    scrollPane.setPreferredSize(new Dimension(800, 800));
    this.add(scrollPane);
    this.setVisible(true);
  }

  /**
   * This runs the animation.
   *
   * @param model          The model that the animation will run from.
   * @param ticksPerSecond the tempo at which the animation will run
   * @param a              ignores this appendable because it doesn't need it.
   */
  public void run(AnimatorModel model, int ticksPerSecond, Appendable a) {
    frameCount = 0;
    timer = new Timer(1000 / ticksPerSecond, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        drawingPanel.drawShapes(model.getShapesAtFrame(frameCount++));
        if (frameCount > model.getMaxDisappearTime()) {
          timer.stop();
        }
      }
    });
    timer.start();
  }
}