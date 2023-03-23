# Clustering_PerturbedGlyphs

A task consists of clustering the vectors extracted from different perturbed Glyphs of specified character of any Font.

Subtasks involved:
1. Create different perturbed glyphs of a character using FontForge Software.(25-30 glyphs)
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

    * Create around 25-30 perturbed glyphs of any character using FontForge and export it as .svg format in svgFiles Folder  </br>

![Screenshot from 2023-03-22 23-56-16](https://user-images.githubusercontent.com/74758376/227002187-9a8be948-4ce0-4edc-9ad7-444c8c15bf94.png)
![Screenshot from 2023-03-22 23-59-32](https://user-images.githubusercontent.com/74758376/227002837-0ba4f6a4-ca95-4000-9499-652cc3cc72a7.png)

    * Running CSVtoVector.java  will store path data of each glyph in pathData.csv
    
![Screenshot from 2023-03-23 00-07-44](https://user-images.githubusercontent.com/74758376/227004764-dd9daae6-ddb4-4696-923e-accb82bab6f1.png)

    * It further Extract Coordinates from path data and Plot each Glyph 

<img src="https://user-images.githubusercontent.com/74758376/227006268-ec2c483c-8309-4e07-8773-d529a2807da9.png" width="300">
<img src="https://user-images.githubusercontent.com/74758376/227006372-ccceb59a-d12a-480e-b73a-d664226c8f7c.png" width="300">
<img src="https://user-images.githubusercontent.com/74758376/227006447-7b4affff-e40e-4c64-bf57-8afff3a5c520.png" width="300">
<img src="https://user-images.githubusercontent.com/74758376/227006520-bac64d82-c77a-45a3-bf9c-da0b3e7cf51f.png" width="300">



     * Using DBScan Algo w'll get a cluster formed by Dense area on graph, we are mapping that cluster and check for each glyph how many of its coordinates are in cluster. More the coord presents incluster more similar is the glyph.
     
![Screenshot from 2023-03-23 00-52-26](https://user-images.githubusercontent.com/74758376/227014313-a648dc36-170d-4183-9167-ea00ae9c8088.png)
![Screenshot from 2023-03-23 00-52-40](https://user-images.githubusercontent.com/74758376/227014379-4d784a4f-1c9f-404b-8e14-9c66147da3cb.png)
![Screenshot from 2023-03-23 10-01-09](https://user-images.githubusercontent.com/74758376/227104011-e6daee92-0fec-4c7e-95e7-7bfeda0d8adb.png)
![Screenshot from 2023-03-23 10-01-23](https://user-images.githubusercontent.com/74758376/227104063-f424fbe5-d202-457d-aecf-0ad704d57794.png)


    * Using K Means Algo w'll get predefined Numbers of clusters from all the coordinates
    
   ![Screenshot from 2023-03-23 01-02-51](https://user-images.githubusercontent.com/74758376/227016545-1f37e848-96d6-4580-8a99-a457333036ea.png)
![Screenshot from 2023-03-23 10-02-39](https://user-images.githubusercontent.com/74758376/227103956-6f2d6719-8fb5-41b0-a753-f1d79c04c674.png)



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




