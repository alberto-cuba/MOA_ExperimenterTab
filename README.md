# MOA ExperimenterTab
MOA ExperimenterTab is written in Java. It basically has as inputs learning algorithms and benchmark datasets and outputs the typical artifacts needed to compare online learning algorithms: tables summarizing the experiment results, figures plotting the predictive performance of algorithms over time, and analysis from the application of statistical test to experimental results. The proposed tool follows a wellestablished evaluation methodology for comparing online learning algorithms applied to data
stream mining.

## Using MOA

* [Getting Started](http://moa.cms.waikato.ac.nz/getting-started/)
* [Documentation](http://moa.cms.waikato.ac.nz/documentation/)
* [About MOA](http://moa.cms.waikato.ac.nz/details/)

## Build Description

To run the project it is necessary to have installed Java SE Development Kit 8, 
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "moa.2016.04.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

## Run Description

The Experiment tab allows to run several online learning algorithms over multiple datasets
and save the corresponding experiment results. The experiments can also be performed via
the command line.

Basically, the configuration of an experimental setting is performed in the Experiment tab.
For example, considering the following commands:
• ls: names of the algorithms separated by commas.
• lss: id of the algorithms separated by commas.
• ds: names of the streams separated by commas.
• dss: id of the streams separated by commas.
• rf: results folder.
• th: number of threads.
• ts: task
 
 ## Running an Example
 
1. Download the moa-experimenter-jar at https://github.com/alberto-cuba/moa-experimenter-jar
2. Unzip the folder with the data sets. 
3. Run the replication script. When the algorithms are finished, the results will be in the Results folder.
3. To visualize and summarize the results it is necessary to run the software via the GUI: type the following in command line, java -jar "moa.2016.04.jar"  
4. Click on the Experimenter tab. This tab has four tabs (Experiment, Summary,Plot,and Analyze).
-The Experiment tab allows to run several online learning algorithms over multiple datasets
and save the corresponding experiment results (not needed for the example since we execute the experiment via the command line).
-The Summary tab summarizes various performance measurements for different learning algorithms by means of tables in LaTeX and HTML formats. The summaries can be obtained
when the experiments finish or by means of the result folder. 
-The Plot tab generates figures representing the performance measurements of various learning
algorithms over time by means of the result folder.
-The Analyze tab allows to verify significant differences between algorithms with the Friedman test and the Holm, Shaffer and Nemenyi
procedures for the post-hoc analysis.
   
