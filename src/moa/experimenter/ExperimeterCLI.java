/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.experimenter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 *
 * @author Alberto
 */
public class ExperimeterCLI {

    private String algorithms[];
    private String algorithmsID[];
    private String streams[];
    private String streamsID[];
    private String task;
    private String resultsFolder;
    private String saveExperimentsPath;
    private String args[];
    private int threads = 1;

    public int[] measures = null;
    public String[] types = null;
    Options options = new Options();
    Options optionsm = new Options();

    public ExperimeterCLI(String[] args) {
        this.args = args;
        //Config the options

        options.addOption("ls", true, "The names of the algorithms separated by commas");
        options.addOption("lss", true, "ID of the algorithms separated by commas");
        options.addOption("ds", true, "The names of the streams separated by commas");
        options.addOption("dss", true, "ID of the streams separated by commas");
        options.addOption("rf", true, "Results folder");
        options.addOption("th", true, "Number of threads");
        options.addOption("ts", true, "Task");
        options.addOption("h", "help", false, "Prints the help message");

        optionsm.addOption("h", "help", false, "Prints the help message");
        optionsm.addOption("m", true, "The number of measures separated by commas");
        optionsm.addOption("tm", true, "The types of measures separated by commas, the types are Mean and Last");
    }

    public boolean summary1CMD(String[] args) {

        CommandLineParser parser = null;
        CommandLine cmdLine = null;

        try {
            parser = new BasicParser();
            cmdLine = parser.parse(optionsm, args);
            if (cmdLine.hasOption("h")) {
                new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), optionsm);
                return false;
            }
            String measure = cmdLine.getOptionValue("m");
            if (measure == null) {
                System.out.println("The measures are required");
                return false;
            }
            if (measure.contains(",")) {
                String[] m = measure.split(",");
                measures = new int[m.length];
                for (int i = 0; i < m.length; i++) {
                    measures[i] = Integer.parseInt(m[i]);
                }
            } else {
                measures = new int[1];
                measures[0] = Integer.parseInt(measure);
            }

            if (cmdLine.hasOption("tm")) {
                String type = cmdLine.getOptionValue("tm");
                if (type.contains(",")) {
                    types = type.split(",");
                } else {
                    types = new String[1];
                    types[0] = type;
                }
            } else {
                types = new String[measures.length];
                for (int i = 0; i < types.length; i++) {
                    types[i] = "Mean";
                }
            }
        } catch (org.apache.commons.cli.ParseException ex) {
            System.out.println(ex.getMessage());

            new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), optionsm);    // Error, imprimimos la ayuda  
        } catch (java.lang.NumberFormatException ex) {
            new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), optionsm);    // Error, imprimimos la ayuda  
        }
        return true;
    }

    public void proccesCMD() {
        int threads = 1;
        String algNames = null;
        String algShortNames = null;
        String streamNames = null;
        String streamShortNames = null;
        String task = null;
        String resultsFolder = null;

        CommandLineParser parser = null;
        CommandLine cmdLine = null;

        try {
            //Parse the input with the set configuration
            parser = new BasicParser();
            cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption("h")) {
                new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);
                return;
            }

            if (cmdLine.hasOption("th")) {
                threads = Integer.parseInt(cmdLine.getOptionValue("th"));
                this.setThreads(threads);
            }

            task = cmdLine.getOptionValue("ts");
            if (task == null) {
                throw new org.apache.commons.cli.ParseException("The task is required");
            }
            this.setTask(task);
            //Algorithms names
            algNames = cmdLine.getOptionValue("ls");

            if (algNames == null) {
                throw new org.apache.commons.cli.ParseException("The name of the algorithms are required");
            }

            try {

                this.setAlgorithms(algNames.split(","));

            } catch (Exception e) {
                System.out.println("Problems with algortihms ls options");
                new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);
            }

            //Agorithms ID 
            if (cmdLine.hasOption("lss")) {
                try {
                    algShortNames = cmdLine.getOptionValue("lss");
                    this.setAlgorithmsID(algShortNames.split(","));

                } catch (Exception e) {
                    System.out.println("Problems with algortihms ID options");
                    new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);
                }
            } else {
                this.setAlgorithmsID(algNames.split(","));
            }
            //Streams names
            streamNames = cmdLine.getOptionValue("ds");

            if (streamNames == null) {
                throw new org.apache.commons.cli.ParseException("The name of the streams are required");
            }
            try {

                this.setStreams(streamNames.split(","));

            } catch (Exception e) {
                System.out.println("Problems with streams ds options");
                new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);
            }
            //stream ID 
            if (cmdLine.hasOption("dss")) {
                try {
                    streamShortNames = cmdLine.getOptionValue("dss");
                    this.setStreamsID(streamShortNames.split(","));

                } catch (Exception e) {
                    System.out.println("Problems with streams ID options");
                    new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);
                }
            } else {
                this.setStreamsID(streamNames.split(","));
            }
            //Results folder
            resultsFolder = cmdLine.getOptionValue("rf");

            if (streamNames == null) {
                throw new org.apache.commons.cli.ParseException("The resuts folder are required");
            }
            this.setResultsFolder(resultsFolder);
            // System.out.println("OK");  
            // System.out.println(task);
            // System.out.println(algNames);
            // System.out.println(streamNames);

        } catch (org.apache.commons.cli.ParseException ex) {
            System.out.println(ex.getMessage());

            new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);    // Error, imprimimos la ayuda  
        } catch (java.lang.NumberFormatException ex) {
            new HelpFormatter().printHelp(ExperimeterCLI.class.getCanonicalName(), options);    // Error, imprimimos la ayuda  
        }
    }

    public String[] getAlgorithms() {
        return algorithms;
    }

    public String[] getAlgorithmsID() {
        return algorithmsID;
    }

    public String[] getArgs() {
        return args;
    }

    public String getResultsFolder() {
        return resultsFolder;
    }

    public String getSaveExperimentsPath() {
        return saveExperimentsPath;
    }

    public String[] getStreams() {
        return streams;
    }

    public String[] getStreamsID() {
        return streamsID;
    }

    public String getTask() {
        return task;
    }

    public int getThreads() {
        return threads;
    }

    public void setAlgorithms(String[] algorithms) {
        this.algorithms = algorithms;
    }

    public void setAlgorithmsID(String[] algorithmsID) {
        this.algorithmsID = algorithmsID;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void setResultsFolder(String resultsFolder) {
        this.resultsFolder = resultsFolder;
    }

    public void setSaveExperimentsPath(String saveExperimentsPath) {
        this.saveExperimentsPath = saveExperimentsPath;
    }

    public void setStreams(String[] streams) {
        this.streams = streams;
    }

    public void setStreamsID(String[] streamsID) {
        this.streamsID = streamsID;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

}
