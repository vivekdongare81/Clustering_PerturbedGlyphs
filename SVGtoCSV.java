package com.pack;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SVGtoCSV {
  static int cnt=0;
  static ArrayList<Double> allCoordinates = new ArrayList<Double>();
  static ArrayList<ArrayList<Double>> allCoordinateArray =  new  ArrayList<ArrayList<Double>>();
  
  public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
	  

  // String svgFilepath = "C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\svgFiles\\a_NotoSans-Regular.svg";
    File folder = new File("C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\svgFiles\\o_Glyphs");
    File[] listOfFiles = folder.listFiles();
    int FileInx=0;
    for (File file : listOfFiles) {
        if (file.isFile()) {
        	   String svgFilepathh = "C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\svgFiles\\o_Glyphs\\";
        	   
            System.out.println(svgFilepathh+file.getName());
            String csvFilepath = "C:\\Users\\donga\\eclipse-workspace\\FontCode\\src\\com\\pack\\a_.csv";
            
            String pathData = extractPathData(svgFilepathh+file.getName());
            // String pathData = extractPathData(svgFilepathh+"o_NotoSans-Regular.svg");
            System.out.println("data heree "+pathData);
            
            List<String[]> coordinatePairs = parsePathData(pathData,FileInx);
            FileInx++;
        
            for (Iterator iterator = coordinatePairs.iterator(); iterator.hasNext();) {
        		String[] strings = (String[]) iterator.next();
        		for(String s:strings) {
        			System.out.print(s+" ");
        		} System.out.println();
        	}
            
            System.out.println("cooooo");
            System.out.println(allCoordinates);
            saveAsCsv(csvFilepath,FileInx, file.getName(),coordinatePairs);
            System.out.println(cnt+" coordinates size- "+allCoordinates.size()+" svg data no - "+allCoordinateArray.size());
          
            double dataset [][]= new double[allCoordinates.size()/2][2];
            int inx=0;
            for(int i=0;i<allCoordinates.size();i++,i++) {
            	
         	double []temp = new double[2];
         	temp[0]= allCoordinates.get(i);
         	temp[1]= allCoordinates.get(i+1);
         	

         	//System.out.println(temp[0]+"  get  "+temp[1]);
         	dataset[inx]=temp;  inx++;
         }
            DataPlotter.mainn(dataset,"black");
        }
    } 
    double dataset [][]= new double[allCoordinates.size()/2][2];
    int inx=0;
    for(int i=0;i<allCoordinates.size();i++,i++) {
    	
 	double []temp = new double[2];
 	temp[0]= allCoordinates.get(i);
 	temp[1]= allCoordinates.get(i+1);
 	
 	//System.out.println(temp[0]+"  get  "+temp[1]);
 	dataset[inx]=temp;  inx++;
 }
 DBScan.mainDBScan(dataset);
    
  }
  
  private static String extractPathData(String filepath) throws IOException, SAXException, ParserConfigurationException {
    List<String> pathData = new ArrayList<>();
    
    File file = new File(filepath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    // Extract path data from XML
    NodeList glyphList = doc.getElementsByTagName("svg");
    Element glyph = (Element) glyphList.item(0);
    String pathDataa = glyph.getElementsByTagName("path").item(0).getAttributes().getNamedItem("d").getNodeValue();
 //System.out.println(pathDataa);
pathData.add(pathDataa);
    
    return pathDataa;
  }
  
  private static List<String[]> parsePathData(String pathData ,int FileInx ) {
	  allCoordinateArray.add (new ArrayList<Double>());
	  
    List<String[]> coordinatePairs = new ArrayList<>();
    System.out.println(":in"); 
    String temp=" ";
    for(int i=0;i<pathData.length();i++) {
    	 
    	if(Character.isAlphabetic(pathData.charAt(i) ) ){
    		 String[] coordinates = temp.split("[^\\d.-]+");
    		 String[] toAddCorr = new String[coordinates.length+1];
    		
    		 toAddCorr[0]=""+temp.charAt(0);
    		
        	 for(int j=0;j<coordinates.length;j++) {
           	//  System.out.println(coordinates.length);
        		 if(j!=0) {
        			 System.out.println("at "+temp.charAt(0)+" addding  "+coordinates[j]); // 173 800 
        			 if(temp.charAt(0)=='v') {
        				 allCoordinateArray.get(FileInx).add( allCoordinates.get( allCoordinates.size()-2));
                           allCoordinates.add( allCoordinates.get( allCoordinates.size()-2)); 
                           if(allCoordinates.get(j)<0) {
                        	   allCoordinateArray.get(FileInx).add(allCoordinates.get( allCoordinates.size()-2)- Double.parseDouble(coordinates[j]));
                        	   allCoordinates.add(allCoordinates.get( allCoordinates.size()-2)- Double.parseDouble(coordinates[j]));
                       	}else {
                        	 allCoordinateArray.get(FileInx).add(allCoordinates.get( allCoordinates.size()-2) +Double.parseDouble(coordinates[j]));
                       		allCoordinates.add(allCoordinates.get( allCoordinates.size()-2) +Double.parseDouble(coordinates[j]));
                       	}
        			 }
        			 else if(temp.charAt(0)=='h') {
        				 if(allCoordinates.get(j)<0) {
        					 allCoordinateArray.get(FileInx).add(allCoordinates.get( allCoordinates.size()-2) -Double.parseDouble(coordinates[j]));
                      	   allCoordinates.add(allCoordinates.get( allCoordinates.size()-2) -Double.parseDouble(coordinates[j]));
                     	}else {
                     		 allCoordinateArray.get(FileInx).add(allCoordinates.get( allCoordinates.size()-2)+Double.parseDouble(coordinates[j]));
                     		allCoordinates.add(allCoordinates.get( allCoordinates.size()-2)+Double.parseDouble(coordinates[j]));
                     	}
        				 allCoordinateArray.get(FileInx).add(allCoordinates.get( allCoordinates.size()-2));
                         allCoordinates.add( allCoordinates.get( allCoordinates.size()-2)); 
                         
      			 }   else if(temp.charAt(0)=='q') {
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      				  toAddCorr[j+1]=coordinates[j];
      				  j++;
      				 allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
      				 allCoordinates.add(Double.parseDouble(coordinates[j]));
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      				 allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
      				 allCoordinates.add(Double.parseDouble(coordinates[j]));
      				 
      			 }   else if(temp.charAt(0)=='c') {
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      				allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
      				 allCoordinates.add(Double.parseDouble(coordinates[j]));
      				  toAddCorr[j+1]=coordinates[j];
      				 j++;
      			    	allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
      				 allCoordinates.add(Double.parseDouble(coordinates[j]));
      				 
      			 }  else if(temp.charAt(0)=='t') {
      				 double[] prevCoord = {allCoordinates.get(allCoordinates.size()-2),allCoordinates.get(allCoordinates.size()-1)};
     			      double [] prevControl = {allCoordinates.get(allCoordinates.size()-4),allCoordinates.get(allCoordinates.size()-3)};
     			      double reflectX = 2 * prevCoord[0] - prevControl[0];
     			      double reflectY = 2 * prevCoord[1] - prevControl[1];
     			   // allCoordinates.add(reflectX);
     			    //allCoordinates.add(reflectY);
     			     allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
     			    allCoordinates.add(Double.parseDouble(coordinates[j]));
     			   toAddCorr[j+1]=coordinates[j];
     			    j++;
     			   allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
     			    allCoordinates.add(Double.parseDouble(coordinates[j]));
     				 
     			 } else{
     				allCoordinateArray.get(FileInx).add(Double.parseDouble(coordinates[j]));
      				 allCoordinates.add(Double.parseDouble(coordinates[j]));
        			 
      			 }
        			 
        		 }
 
           	  toAddCorr[j+1]=coordinates[j];
             }
//        	 for(int j=0;j<toAddCorr.length;j++) {
//              	  System.out.print ("string "+j+" "+toAddCorr[j]+" ");
//              	  
//                }
        	 coordinatePairs.add(toAddCorr);
    	      temp="";
    	      temp+=pathData.charAt(i);
    		
    		 // int count = getCoordinateCount(pathData.charAt(i));
    		  
    		//System.out.println(pathData.charAt(i));
    	}else {
    		temp+=pathData.charAt(i);
    		//System.out.println(pathData.charAt(i));
    	}
    	
    	
    }
    
    
   /* for (String path : pathData) { 
      String[] commands = path.split("[AaCcHhLlMmQqSsTtVvZz]");
      String[] coordinates = path.split("[^\\d.-]+");
      for(int i=0;i<commands.length;i++) {
    	  System.out.println(commands[i]);
      }
      System.out.println("co");
      for(int i=0;i<coordinates.length;i++) {
    	  System.out.println(coordinates[i]);
      }
      int i = 0;
      for (String command : commands) {
        if (command.isEmpty()) {
          continue;
        }
        
        int count = getCoordinateCount(command.charAt(0));
        for (int j = 0; j < count; j++) {
          String[] pair = new String[2];
          pair[0] = coordinates[i];
          pair[1] = coordinates[i + 1];
          coordinatePairs.add(pair);
          i += 2;
        }
      }
    }
    */
    return coordinatePairs;
  }
   
  
  private static List<String[]> parsePathDataa(String pathData) {
	    List<String[]> segments = new ArrayList<String[]>();
	    Pattern pattern = Pattern.compile("[a-zA-Z]");
	    java.util.regex.Matcher matcher = pattern.matcher(pathData);
	    int start = 0;
	    while (matcher.find()) {
	        int end = matcher.start();
	        String segment = pathData.substring(start, end).trim();
	        if (segment.length() > 0) {
	            String[] coords = segment.split("[ ,\\s]+");
	            String command = matcher.group();
	            int coordCount = getCoordinateCount(command.charAt(0));
	            if (coords.length >= coordCount) {
	                String[] segmentArr = new String[coordCount + 1];
	                segmentArr[0] = command;
	                System.arraycopy(coords, 0, segmentArr, 1, coordCount);
	                segments.add(segmentArr);
	                start = end;
	            }
	        }
	    }
	    String segment = pathData.substring(start).trim();
	    if (segment.length() > 0) {
	        String[] coords = segment.split("[,\\s]+");
	        String command = pathData.substring(pathData.length() - 1);
	        int coordCount = getCoordinateCount(command.charAt(0));
	       
	        if (coords.length >= coordCount) {
	            String[] segmentArr = new String[coordCount + 1];
	            segmentArr[0] = command;
	            System.arraycopy(coords, 0, segmentArr, 1, coordCount);
	            segments.add(segmentArr); 
	        }
	    }
	    return segments;
	}  
  
  private static int getCoordinateCount(char command) { 
    switch (command) {
      case 'A': case 'a': return 7;
      case 'C': case 'c': return 6;
      case 'H': case 'h': return 1;
      case 'L': case 'l': return 2;
      case 'M': case 'm': return 2;
      case 'Q': case 'q': return 4;
      case 'S': case 's': return 4;
      case 'T': case 't': return 2;
      case 'V': case 'v': return 1;
      case 'Z': case 'z': return 0;
      default: throw new IllegalArgumentException("Invalid path command: " + command);
    }
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
