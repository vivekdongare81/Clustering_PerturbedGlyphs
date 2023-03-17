package com.pack;
import java.util.*;
import com.pack.DataPlotter;
public class DBScan {

    public static void mainDBScan(double[][]dataset) {
        // Initialize dataset and parameters
    	
    	
        //double[][] dataset = {{1,2}, {3,4}, {50,6}, {51,7}, {9,10}};
        double eps = 50;
        int minPts = 0;
        
       // DataPlotter.mainn(dataset,"black");
        // Run DBSCAN algorithm
        ArrayList<ArrayList<Double>> clusters = dbscan(dataset, eps, minPts);
 
        // Print results
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster " + (i+1) + ": " + clusters.get(i));
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

