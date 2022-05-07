# CitationCountPrediction

The citation count is an important factor to estimate the relevance and significance of academic publications. However, it is not possible to use this measure for papers which are too new. A solution to this problem is to estimate the future citation counts. There are existing works, which point out that graph mining techniques lead to the best results. We aim at improving the prediction of future citation counts by introducing a new feature. This feature is based on frequent graph pattern mining in the so-called citation network constructed on the basis of a dataset of scientific publications.

Due to the drastic growth of the amount of scientific publications each year, it is a major challenge in academia to identify important literature among recent publications. The problem is not only how to navigate through a huge corpus of data, but also what search criteria to use. While the Impact Factor and the h-index measure the significance of a particular venue or a particular author, the citation count aims at estimating the impact of a particular paper. An accurate estimation of the future citation count can be used to facilitate the search for promising publications.

## Project Prerequisites:
* Java 11
* Postman(For Testing)
* Gradle 7.4.2
* MongoDB 5.0.6
* Python 3
* Anaconda with Jupyter Notebook installed

### Dataset Reffered: https://snap.stanford.edu/data/cit-HepTh.html

We have built a Spring Boot API to read text files of the dataset, clean the data, and store the cleaned data in a readable format as MongoDB collections. The API then can be used to fetch the data from the MongoDB collections, clearing out the hassle to load all files in memory. The API can be configured to give out the data in the required format.

After the Spring Boot API, we have built a jupyter notebook to use the data stored in MongoDB to build citation graph and work over them.

## Steps to run the project
1. clone the project on the system.
2. Install all prerequisite softwares.
3. cd into data-fetch-api/ folder of the project.
4. run the command "./gradlew build"
5. run the command "./gradlew bootRun"
6. cd back to the project root using "cd .." command
7. cd into jupyter-notebook/ folder
8. open the .pynb file in anaconda or vscode with jupyter plugin.
9. Run the file commands one by one in series.

#### Note: We have added the dataset files to the project for easy retrieval.

## Screenshots for reference:
![image](https://user-images.githubusercontent.com/31500801/167258966-fcaaec7a-824a-4c2a-a966-d1884e5fc2fc.png)
Reference Graph Creation Data. Every Paper is given a unique id as fromNode, and every paper reffered by it is listed in toNode Array.

![image](https://user-images.githubusercontent.com/31500801/167258985-b92b0d24-57f1-400f-98f3-429edd01ce74.png)
Details of each research paper with nodeId.

![image](https://user-images.githubusercontent.com/31500801/167259039-e03a5de2-8640-4e40-9106-ffae9f520df5.png)
Dates of paper published, along with information, if the paper is Cross-Listed in multiple domains or publications.

![image](https://user-images.githubusercontent.com/31500801/167259305-51543a51-5fa8-4ad6-8d9b-7320319f5217.png)
API response in Postman

![graph](https://user-images.githubusercontent.com/31500801/167259358-3bcff24a-3401-48d5-8d13-f311e8aab99a.png)
Visualization of graph built 
