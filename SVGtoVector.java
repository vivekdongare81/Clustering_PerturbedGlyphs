package com.pack;

import java.awt.geom.PathIterator;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.batik.parser.AWTPathProducer;
import org.apache.batik.parser.ParseException;
import org.apache.batik.parser.PathParser;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGPathElement;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;

public class SVGtoVector {
	static int cnt=0;
    static ArrayList<ArrayList<Double>> xyCoordinates = new ArrayList<ArrayList<Double>>();
	
	static int sizeOfxy=0;
    public static void main(String[] args) {
    	
    	File folder = new File("C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\svgFiles\\o_Glyphs\\");
        File[] listOfFiles = folder.listFiles();
        int FileInx=0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
    
    	
        String svgPathFolder = "C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\svgFiles\\o_Glyphs\\";
      
        // String svgPath="C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\svgFiles\\others\\o_NotoSans-Regular.svg";
        String svgPath=svgPathFolder+file.getName();
       
        String csvPath="C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\pathData_.csv";
        
        try {
        	 byte[] svgBytes = Files.readAllBytes(Paths.get(svgPath));
        	    String svgContent = new String(svgBytes);
        	    SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
        	    SVGDocument document = factory.createSVGDocument("", new ByteArrayInputStream(svgBytes));



            SVGPathElement pathElement = (SVGPathElement) document.getElementsByTagName("path").item(0);
            String pathData = pathElement.getAttribute("d");
            System.out.println(FileInx+ " For "+file.getName());
            System.out.println(pathData);
            
        
            List<String[]> coordinates =parsePathDataInCSV(pathData,FileInx);
          
            for(int i=0;i<coordinates.size();i++) {
            	System.out.println( Arrays.toString( coordinates.get(i)));
            }
            //Saving PathData in csv
            saveAsCsv( csvPath,FileInx+1, file.getName() , coordinates);
            
            //Adding coordinates into Global array
            parsePathDataInCoordinates(pathData,FileInx);
            
            System.out.println("In File "+xyCoordinates.size()+" coord "+xyCoordinates.get(FileInx).size()+" total coor "+sizeOfxy);
            //PLOTTING each Glyph
            
            double[][] dataset=new double[sizeOfxy/2][2];
            int dInx=0;
            for(int i=0;i<xyCoordinates.size();i++) {
                
                for(int j=0;j<xyCoordinates.get(i).size();j++) {
                	

                	dataset[dInx]=new double[2];
                	dataset[dInx][0]= xyCoordinates.get(i).get(j);
                	dataset[dInx][1]= xyCoordinates.get(i).get(j+1);
                	dInx++;
                	j++;
                	
                }
                
            }
           //PLOTTING each Glyph in Folder
             DataPlotter.mainn(dataset,"blue");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
    
            }
           
        	FileInx++;
	    //  if(FileInx==3) {break; }
        }
       
        
        // CLUSTERING
        double[][] dataset=new double[sizeOfxy/2][2];
        int dInx=0;
        for(int i=0;i<xyCoordinates.size();i++) {
            
            for(int j=0;j<xyCoordinates.get(i).size();j++) {
            	

            	dataset[dInx]=new double[2];
            	dataset[dInx][0]= xyCoordinates.get(i).get(j);
            	dataset[dInx][1]= xyCoordinates.get(i).get(j+1);
            	dInx++;
            	j++;
            	
            }
            
        }
           
             DBScan.mainDBScan(dataset);
            // KMeans.mainnKMeans(dataset);
        
    }

    private static void parsePathDataInCoordinates(String pathData,int FileInx) {
        PathParser parser = new PathParser();
        AWTPathProducer producer = new AWTPathProducer();
        parser.setPathHandler(producer);
        try {
            parser.parse(pathData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Get the path iterator for the shape
        PathIterator pathIterator = producer.getShape().getPathIterator(null);

        // Extract and process the path elements
        double[] coords = new double[6];
      
        
        List<Double> xCoords = new ArrayList<>();
        List<Double> yCoords = new ArrayList<>();
        
        xyCoordinates.add(new ArrayList<Double>());
        int segType;
        while (!pathIterator.isDone()) {
            segType = pathIterator.currentSegment(coords);
            switch (segType) {
                case PathIterator.SEG_MOVETO:
                    xCoords.add(coords[0]);
                    yCoords.add(coords[1]);
                    xyCoordinates.get(FileInx).add(coords[0]);
                    xyCoordinates.get(FileInx).add(coords[1]);
                   
                    
                    break;
                case PathIterator.SEG_LINETO:
                    xCoords.add(coords[0]);
                    yCoords.add(coords[1]);
                  
                    xyCoordinates.get(FileInx).add(coords[0]);
                    xyCoordinates.get(FileInx).add(coords[1]);
                    
                    break;
                case PathIterator.SEG_QUADTO:
                    xCoords.add(coords[0]);
                    yCoords.add(coords[1]);
                  
                    xyCoordinates.get(FileInx).add(coords[0]);
                    xyCoordinates.get(FileInx).add(coords[1]);
                   
                    xCoords.add(coords[2]);
                    yCoords.add(coords[3]);
                    
                    xyCoordinates.get(FileInx).add(coords[2]);
                    xyCoordinates.get(FileInx).add(coords[3]);
                    
                    break;
                case PathIterator.SEG_CUBICTO:
                    xCoords.add(coords[0]);
                    yCoords.add(coords[1]);
                    
                    xyCoordinates.get(FileInx).add(coords[0]);
                    xyCoordinates.get(FileInx).add(coords[1]);
                    
                    xCoords.add(coords[2]);
                    yCoords.add(coords[3]);
                  
                    xyCoordinates.get(FileInx).add(coords[2]);
                    xyCoordinates.get(FileInx).add(coords[3]);
                    
                    xCoords.add(coords[4]);
                    yCoords.add(coords[5]);
                  
                    xyCoordinates.get(FileInx).add(coords[4]);
                    xyCoordinates.get(FileInx).add(coords[5]);
                    
                    break;
                case PathIterator.SEG_CLOSE:
                    break;
                default:
       
                    break;
            }
            pathIterator.next();
        }
        
        System.out.println("xCoords: " + xCoords);
        System.out.println("yCoords: " + yCoords);
     
        sizeOfxy+=xyCoordinates.get(FileInx).size();
    }

    
        private static List<String[]> parsePathDataInCSV(String pathData ,int FileInx ) {
      	
          List<String[]> coordinatePairs = new ArrayList<>();
          System.out.println(":in"); 
          String temp=" ";
          for(int i=0;i<pathData.length();i++) {
          	 
          	if(Character.isAlphabetic(pathData.charAt(i) ) ){
          		 String[] coordinates = temp.split("[^\\d.-]+");
          		 String[] toAddCorr = new String[coordinates.length+1];
          		
          		 toAddCorr[0]=""+temp.charAt(0);
          		
              	 for(int j=0;j<coordinates.length;j++) {
            
                 	  toAddCorr[j+1]=coordinates[j];
                 	  
              	 }
              	 coordinatePairs.add(toAddCorr);
          	      temp="";
          	      temp+=pathData.charAt(i);
          		
          	}else {
          		temp+=pathData.charAt(i);
          		//System.out.println(pathData.charAt(i));
          	}
    
          }
          
          return coordinatePairs;
        }
        
        

        private static void saveAsCsv(String filepath,int FileInx, String fileNAME, List<String[]> data) throws IOException {
      	  cnt++;
      	  List<String[]>  records = new ArrayList<>();
      	  String earlierData="";
      	  
      	  File f = new File(filepath);
      	  if(f.exists() && !f.isDirectory()) { 
      	      
      	  }else {
      		  FileWriter writer = new FileWriter(filepath);
      		  writer.write(" ");
      	  }
      	  
      	  try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
      	      String line;
      	      while ((line = br.readLine()) != null) {
      	        
      	          earlierData+=line+"\n";
      	      }
      	  }
      	  //System.out.println("earrrrrrr"+earlierData);
          FileWriter writer = new FileWriter(filepath); 
            //writer.write(earlierData);
            
          String presentData= " "+FileInx+" "+ fileNAME+"\n";
          for(int i=0;i<data.size();i++) {
          	String towrite="";
          	for(int j=0;j<data.get(i).length;j++) {
          		towrite+=data.get(i)[j]+",";
          	} towrite+="\n";
          	
          	presentData+=towrite;
          }
          writer.write(earlierData+"\n"+presentData);
          	 
          writer.close();
        }
        
        
}
