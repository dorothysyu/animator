package cs3500.animator;
import org.junit.*;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.VisualView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import javax.swing.JOptionPane;

import util.AnimationFileReader;

import static cs3500.animator.EasyAnimator.*;

public class debugTest {

  @Test
  public void debug() {
    String[] args = {
            "java",
            "-jar",
            "Assignment6.jar",
            "-if",
            "big-bang-big-crunch.txt",
            "-iv",
            "text",
            "-o",
            "out",
            "-speed",
            "50"};
    if (throwsException(args)) {
      JOptionPane.showMessageDialog(null, "Check that you entered the arguments correctly and try" +
              " again.");
    }
    try {
      AnimatorModel model = buildModelFromFile(args);
      IView view = getView(args);
      int speed = getSpeed(args);
      Appendable a = getAppendable(args);
      view.run(model, speed, a);
    } catch (FileNotFoundException | IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    }
  }
}