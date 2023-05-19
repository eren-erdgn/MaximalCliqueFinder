import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

public class MaximalClique {
	
	public static List<List<Integer>> findMaximalCliques(List<Integer> vertices, List<int[]> edges) {
	    List<List<Integer>> maximalCliques = new ArrayList<>();
	    List<Integer> remainingVertices = new ArrayList<>(vertices);

	    
	    while (!remainingVertices.isEmpty()) {
	      
	      int vertex = remainingVertices.get(0);

	      List<Integer> clique = new ArrayList<>();
	      clique.add(vertex);

	      remainingVertices.remove(0);

	      for (int i = 0; i < remainingVertices.size(); i++) {
	        int remainingVertex = remainingVertices.get(i);
	        
	        if (isValidToAdd(remainingVertex, clique, edges)) {
	          clique.add(remainingVertex);
	          remainingVertices.remove(i);
	          i--; 
	        }
	      }

	      maximalCliques.add(clique);
	    }

	    return maximalCliques;
	  }
	

	  private static boolean isValidToAdd(int vertex, List<Integer> clique, List<int[]> edges) {
	    for (int cliqueVertex : clique) {
	      boolean isConnected = false;
	      for (int[] edge : edges) {
	        if ( (edge[0] == cliqueVertex && edge[1] == vertex)) {
	          isConnected = true;
	          break;
	        }
	      }
	      if (!isConnected) {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  
  public static void main(String[] args) {
	  
	  Scanner sc =  new Scanner(System.in);
	  System.out.println("Please Enter N:");
	  int size = sc.nextInt();
	  System.out.println("Please Enter D:");
	  int minDistance = sc.nextInt();
	  
	  if(size <= minDistance)
	  {
		  while(size < minDistance)
		  {
			  System.out.println("D can not be larger than N!");
			  System.out.println("Please Enter N:");
			   size = sc.nextInt();
			  System.out.println("Please Enter D:");
			   minDistance = sc.nextInt();
		  }
	  }

	  int[][] binaryNumbers = new int[(int) Math.pow(2, size)][size];

	  for (int i = 0; i < Math.pow(2, size); i++) {
	    for (int j = size - 1; j >= 0; j--) {
	      binaryNumbers[i][j] = i >> j & 1;
	    }
	  }
	  System.out.println("Binary Strings: ");
	  for (int[] row : binaryNumbers) {
	    System.out.println(Arrays.toString(row));
	  }

	  List<Integer> vertices = new ArrayList<>();
	  List<int[]> edges = new ArrayList<>();
	  for (int i = 0; i < binaryNumbers.length; i++) {
	    vertices.add(i);
	  }

	  
	  for (int i = 0; i < binaryNumbers.length; i++) {
	    for (int j = i + 1; j < binaryNumbers.length; j++) {
	      int distance = 0;
	      for (int k = 0; k < size; k++) {
	        if (binaryNumbers[i][k] != binaryNumbers[j][k]) {
	          distance++;
	        }
	      }
	      if (distance >= minDistance) {
	        edges.add(new int[] {i,j});
	      }
	    }
	  }
	  System.out.println("Edges:");
	  for (int[] edge : edges) {
          System.out.println(Arrays.toString(edge));
        }
	  
	  List<List<Integer>> maximalCliques = findMaximalCliques(vertices, edges);
	  for (List<Integer> clique : maximalCliques) {
		    System.out.println("Maximal clique: " + clique);
		  }
	  System.out.println("Maximal clique's size : " +  maximalCliques.get(0).size());
	  
	  
	    GraphPanel panel = new GraphPanel(vertices, edges, maximalCliques);
	    JFrame frame = new JFrame();
	    frame.setContentPane(panel);
	    frame.setSize(600, 600);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  
	  
	}
	  
	  
  }