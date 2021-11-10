package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;

import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;


public class MyDrawingPanel extends JPanel {
  // Takes in a list of IShapes
  List<IShape> shapesToDraw;

  // The constructor for MyDrawingPanel.
  protected MyDrawingPanel() {
    super();
    shapesToDraw = new ArrayList<>();
  }

  /**
   * The method drawShapes takes in a list of shapes to draw and repaints it.
   *
   * @param shapesToDraw A list of IShapes
   */
  public void drawShapes(List<IShape> shapesToDraw) {
    this.shapesToDraw = shapesToDraw;
    repaint();
  }

  /**
   * The method paintComponent takes in the graphics and paints the shape you want it to show.
   *
   * @param g The graphics that is going to be painted.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (shapesToDraw != null) {
      for (IShape shape : shapesToDraw) {
        Color myColor = new Color((int) (shape.getColorR() * 255),
                (int) (shape.getColorG() * 255), (int) (shape.getColorB() * 255));
        g.setColor(myColor);
        if (shape instanceof Rectangle) {
          g.fillRect((int) shape.getPositionX(), (int) shape.getPositionY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        }
        if (shape instanceof Oval) {
          g.fillOval((int) shape.getPositionX(), (int) shape.getPositionY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        }
      }
    }
  }
}


