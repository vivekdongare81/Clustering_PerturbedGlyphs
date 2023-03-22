# Clustering_PerturbedGlyphs

A task consists of clustering the vectors extracted from different perturbed Glyphs of a specific character of any Font.

Subtasks involved:
1. Create different perturbed Glyphs of a character using FontForge Software.(25-30 glyphs)
2. Extract path data from glyphs of character(.svg file) and record in CSV (pathData.csv)
3. Evaluate coordinates from the path data,so that to be used for clustering and Plotting of a Glyph on Graph.
4. Apply different Clustering Algorithm on extracted coordinates.


Tools/Libraries used - FontForge, Batik Lib, jfree Lib.

## Prerequisite 

- FontForge Software, JDK SE
 

## Installation to Run Locally

 -  Step 1 - Clone the project and navigate to the project directory
  
```bash
git init 
git clone < repo link >

```

  -  Step 2 - Add path of Folder containing perturbed glyphs and csv in SVGtoVector.java  </br>
(considering perturbed glyphs are already created and stored in folder svgFiles using FontForge Software )

  -  Step 3 - Add following Maven dependencies in pom.xml
```bash
<dependencies>

 <dependency>
    <groupId>org.jfree</groupId>
    <artifactId>jfreechart</artifactId>
    <version>1.5.3</version>
</dependency>

<dependency>
    <groupId>org.apache.xmlgraphics</groupId>
    <artifactId>batik-svg-dom</artifactId>
    <version>1.7</version>
</dependency>

  </dependencies>

```
  -  Step 4 - Run SVGtoVector.java
```bash
>> SVGtoVector.java
```



## Demo

- Create and store perturbed glyphs of any character using FontForge and store it in svgFiles Folder </br>
![Screenshot from 2023-03-22 23-56-16](https://user-images.githubusercontent.com/74758376/227002187-9a8be948-4ce0-4edc-9ad7-444c8c15bf94.png)

![Screenshot from 2023-03-22 23-56-16](https://user-images.githubusercontent.com/74758376/227002242-907e3239-5ccd-4858-b0cd-be18f9765a07.png)

![Screenshot from 2023-03-22 23-46-23](https://user-images.githubusercontent.com/74758376/227001645-0ed8438a-efd9-42b7-866f-90cf6889561b.png)

        * Browse to given URL to record the tools-browser which gets detected.
       
![image](https://user-images.githubusercontent.com/74758376/170940621-4bc505c5-cf1c-4bf2-99ee-a3814f4abb67.png)

      * Take a Screenshot of pages on GET URL using tool, parallelly save the results in Database to analyze the data. 

![image](https://user-images.githubusercontent.com/74758376/170940348-101414d9-555f-4a76-be98-61daf28ef342.png)

     * Analyze Results according to date performed in Admin Panel.
![image](https://user-images.githubusercontent.com/74758376/170940792-655e3c53-6bc7-4854-935e-fba8a5fc063f.png)


- All Combinations of Tool-Browser with their test Status will be recorded in results.json

![image](https://user-images.githubusercontent.com/74758376/170771018-da3193f9-9623-47a5-a2d1-848b0d1b853c.png)
file:///home/vivek-pt7175/Pictures/Screenshots/Screenshot%20from%202023-03-22%2023-46-23.png



## Required outsources

| Tools/Libraries             | Download Link                                                                |
| ----------------- | ------------------------------------------------------------------ |
| FontForge| [https://fontforge.org/en-US/downloads/](https://fontforge.org/en-US/downloads/)|
| Batik |https://javadoc.io/doc/org.apache.xmlgraphics/batik-parser/latest/index.html|
| Jfree |[http://www.java2s.com/Code/Jar/j/Downloadjfreechartjar.html](http://www.java2s.com/Code/Jar/j/Downloadjfreechartjar.html)| |



## Reference 

- [Batik](https://xmlgraphics.apache.org/batik/)
- [DBScan](https://www.geeksforgeeks.org/dbscan-clustering-in-ml-density-based-clustering/)
- [KMeans](https://www.javatpoint.com/k-means-clustering-algorithm-in-machine-learning)




