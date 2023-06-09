package com.pack;
import java.util.*;
import com.pack.DataPlotter;


public class DBScan {

    public static void mainDBScan(double[][]dataset) {
     
        double eps = 20;
        int minPts = 3;
       
        // Run DBSCAN algorithm
        ArrayList<ArrayList<Double>> clusters = dbscan(dataset, eps, minPts);
 
        HashMap<Double, Double> hm =new HashMap<Double, Double>();
        
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster "+ ": " + clusters.get(i));
            
        }
        for (int i = 0; i < clusters.size();i++) {
          ArrayList<Double> temp = clusters.get(i);
          for(int j=0;j<temp.size();j++) {
        	  hm.put(temp.get(j),temp.get(j+1));
        	  j++;
          }
          
        }
        System.out.println(hm);
        
        System.out.println(SVGtoVector.xyCoordinates.size());
     
        for(int i=0;i<SVGtoVector.xyCoordinates.size();i++) {
        	int cnt=0; int total=0;
        	
        	for(int j=0;j<SVGtoVector.xyCoordinates.get(i).size();j++) {
        		total++;
             if(hm.containsKey(SVGtoVector.xyCoordinates.get(i).get(j))){
            	// System.out.println();
        		   if(Double.compare(hm.get(SVGtoVector.xyCoordinates.get(i).get(j)),SVGtoVector.xyCoordinates.get(i).get(j+1)) == 0) {
        			   
        			   cnt++;
        			   }	
        		}else {
 				  // System.out.println();
 			   }
             j++;
        	}
        	System.out.println("for File- "+(i+1)+" "+SVGtoVector.fileNames.get(i)+" total co- "+total+" incluster co- "+cnt);
        }
        
    }

    public static ArrayList<ArrayList<Double>> dbscan(double[][] dataset, double eps, int minPts) {
        ArrayList<ArrayList<Double>> clusters = new ArrayList<ArrayList<Double>>();
        HashSet<double[]> visited = new HashSet<double[]>();

        for (double[] point : dataset) {
            if (!visited.contains(point)) {
                visited.add(point);

                ArrayList<double[]> neighbors = regionQuery(dataset, point, eps);

                if (neighbors.size() < minPts) {
                    continue;
                }

                ArrayList<Double> cluster = new ArrayList<Double>();
                clusters.add(cluster);

                expandCluster(dataset, point, neighbors, cluster, visited, eps, minPts,clusters);
            }
        }

        return clusters;
    }

    public static void expandCluster(double[][] dataset, double[] point, ArrayList<double[]> neighbors, ArrayList<Double> cluster, HashSet<double[]> visited, double eps, int minPts, ArrayList<ArrayList<Double>> clusters) {
        cluster.add(point[0]);
        cluster.add(point[1]);

        for (int i = 0; i < neighbors.size(); i++) {
            double[] neighbor = neighbors.get(i);

            if (!visited.contains(neighbor)) {
                visited.add(neighbor);

                ArrayList<double[]> subNeighbors = regionQuery(dataset, neighbor, eps);

                if (subNeighbors.size() >= minPts) {
                    neighbors.addAll(subNeighbors);
                }
            }

            boolean inCluster = false;

            for (ArrayList<Double> c : clusters) {
                if (c.contains(neighbor[0]) && c.contains(neighbor[1])) {
                    inCluster = true;
                    break;
                }
            }

            if (!inCluster) {
                cluster.add(neighbor[0]);
                cluster.add(neighbor[1]);
            }
        }
    }

    public static ArrayList<double[]> regionQuery(double[][] dataset, double[] point, double eps) {
        ArrayList<double[]> neighbors = new ArrayList<double[]>();

        for (double[] other : dataset) {
            if (distance(point, other) <= eps) {
                neighbors.add(other);
            }
        }

        return neighbors;
    }

    public static double distance(double[] a, double[] b) {
        double xDiff = a[0] - b[0];
        double yDiff = a[1] - b[1];

        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
}

