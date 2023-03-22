package com.pack;

import java.util.*;

public class KMeans {
    private List<Point> points;
    private int k;

    public KMeans(List<Point> points, int k) {
        this.points = points;
        this.k = k;
    }

    public Map<Point, List<Point>> run() {
        List<Point> centroids = initializeCentroids();
        Map<Point, List<Point>> clusters = null;
        do {
            clusters = assignPointsToClusters(centroids);
            centroids = calculateCentroids(clusters);
        } while (!areCentroidsEqual(centroids, clusters.keySet()));
        return clusters;
    }


    private List<Point> initializeCentroids() {
        List<Point> centroids = new ArrayList<>();
        Collections.shuffle(points);
        for (int i = 0; i < k; i++) {
            centroids.add(points.get(i));
        }
        return centroids;
    }

    private Map<Point, List<Point>> assignPointsToClusters(List<Point> centroids) {
        Map<Point, List<Point>> clusters = new HashMap<>();
        for (Point centroid : centroids) {
            clusters.put(centroid, new ArrayList<>());
        }
        for (Point point : points) {
            Point closestCentroid = getClosestCentroid(point, centroids);
            clusters.get(closestCentroid).add(point);
        }
        return clusters;
    }

    private Point getClosestCentroid(Point point, List<Point> centroids) {
        double minDistance = Double.MAX_VALUE;
        Point closestCentroid = null;
        for (Point centroid : centroids) {
            double distance = Math.sqrt(Math.pow(point.x - centroid.x, 2) + Math.pow(point.y - centroid.y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                closestCentroid = centroid;
            }
        }
        return closestCentroid;
    }

    private List<Point> calculateCentroids(Map<Point, List<Point>> clusters) {
        List<Point> centroids = new ArrayList<>();
        for (Map.Entry<Point, List<Point>> entry : clusters.entrySet()) {
            Point centroid = calculateCentroid(entry.getValue());
            centroids.add(centroid);
        }
        return centroids;
    }

    private Point calculateCentroid(List<Point> points) {
        double sumX = 0;
        double sumY = 0;
        for (Point point : points) {
            sumX += point.x;
            sumY += point.y;
        }
        double meanX = sumX / points.size();
        double meanY = sumY / points.size();
        return new Point(meanX, meanY);
    }

    private boolean areCentroidsEqual(List<Point> centroids1, Set<Point> centroids2) {
        for (Point centroid : centroids1) {
            if (!centroids2.contains(centroid)) {
                return false;
            }
        }
        return true;
    }

    public static void mainnKMeans(double [][]dataset ) {
    	
    	
   	 List<Point> points = new ArrayList<KMeans.Point>();

    	 for(int i=0;i<dataset.length;i++) {
     		points.add(new Point(dataset[i][0], dataset[i][1]));
     	}
    	 
    	KMeans kMeans = new KMeans(points, 2);
    	Map<Point, List<Point>> clusters = kMeans.run();
    	System.out.println("Final clusters:");
    	for (Map.Entry<Point, List<Point>> entry : clusters.entrySet()) {
    	    System.out.println("Centroid: " + entry.getKey());
    	    System.out.println("Points: " + entry.getValue());
    	}

       
      
    }
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Point point = (Point) obj;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
