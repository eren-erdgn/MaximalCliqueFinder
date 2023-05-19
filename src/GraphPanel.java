import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
  private List<Integer> vertices;
  private List<int[]> edges;
  private List<List<Integer>> maximalClique;
  private static final Color[] CLIQUE_COLORS = {
		  Color.RED,
		  Color.BLUE,
		  Color.GREEN,
		  Color.YELLOW,
		  Color.CYAN,
		  Color.MAGENTA,
		  Color.ORANGE,
		  Color.PINK
		};
  private int cliqueColorCounter = 0;
  
  


  //Constructor
  public GraphPanel(List<Integer> vertices, List<int[]> edges, List<List<Integer>> maximalClique) {
    this.vertices = vertices;
    this.edges = edges;
    this.maximalClique = maximalClique;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int radius = Math.min(getWidth(), getHeight()) / 2 - 50;
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;
    

    
    for (int i = 0; i < vertices.size(); i++) {
      double angle = 2 * Math.PI * i / vertices.size();
      int x = (int) (centerX + radius * Math.cos(angle));
      int y = (int) (centerY + radius * Math.sin(angle));
      g.drawString(vertices.get(i).toString(), x, y);
    }

    
    for (int[] edge : edges) {
      double angle1 = 2 * Math.PI * edge[0] / vertices.size();
      int x1 = (int) (centerX + radius * Math.cos(angle1));
      int y1 = (int) (centerY + radius * Math.sin(angle1));
      double angle2 = 2 * Math.PI * edge[1] / vertices.size();
      int x2 = (int) (centerX + radius * Math.cos(angle2));
      int y2 = (int) (centerY + radius * Math.sin(angle2));
      g.drawLine(x1, y1, x2, y2);
    }
    
    for (List<Integer> clique : maximalClique) {
      Color cliqueColor = CLIQUE_COLORS[cliqueColorCounter % CLIQUE_COLORS.length];
      cliqueColorCounter++;
      for (int i = 0; i < clique.size(); i++) {
        for (int j = i + 1; j < clique.size(); j++) {
          double angle1 = 2 * Math.PI * clique.get(i) / vertices.size();
          int x1 = (int) (centerX + radius * Math.cos(angle1));
          int y1 = (int) (centerY + radius * Math.sin(angle1));
          double angle2 = 2 * Math.PI * clique.get(j) / vertices.size();
          int x2 = (int) (centerX + radius * Math.cos(angle2));
          int y2 = (int) (centerY + radius * Math.sin(angle2));
          g.setColor(cliqueColor);
          g.drawLine(x1, y1, x2, y2);
        }
      }
    }
  }
}