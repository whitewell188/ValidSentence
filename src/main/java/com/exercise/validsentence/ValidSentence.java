/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercise.validsentence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.cli.*;

/**
 *
 * @author nward
 */
public class ValidSentence {
    
    
    public static void main(String[] args) {
        
        ArrayList<String> lines = new ArrayList<String>();
        
        Options options = new Options();

        Option filename = new Option("f", "file", true, "path and filename containing a batch of sentences to parse");
        filename.setRequired(false);
        options.addOption(filename);

        Option sentence = new Option("s", "sentence", true, "individual sentence to parse");
        sentence.setRequired(false);
        options.addOption(sentence);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        String inputFilePath = null;
        String inputSentence = null;
        
        try {
            CommandLine cmd = parser.parse(options, args);
            inputFilePath = cmd.getOptionValue("file");
            inputSentence = cmd.getOptionValue("sentence");
            
            if ( inputFilePath != null ){
                ReadFromFile(inputFilePath, lines);
            } else if ( inputSentence != null) {
                lines.add(inputSentence);
            } else {
               formatter.printHelp("utility-name", options);
               System.exit(1);
            }
            
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
      
        ArrayList<ValidationPlugin> plugin_list = new ArrayList<ValidationPlugin>();
        plugin_list.add(new ValidateCapitalLetter());
        plugin_list.add(new ValidateFullStops());
        plugin_list.add(new ValidateQuotationMarks());
        plugin_list.add(new ValidateNumberUsage());
        
        long start = System.nanoTime();
        
        int num_valid_strings = lines.size();
        for (int i = 0; i < lines.size(); i++) {
            if ( lines.get(i).length() == 0){
                continue;
            }
            ArrayList<String> error_strings = new ArrayList<String>();
            for (int j = 0; j < plugin_list.size(); j++){
                if ( plugin_list.get(j).validate(lines.get(i)) != true ){
                    error_strings.add(plugin_list.get(j).getResult());
                }
            }
            System.out.println("Input  = [" + lines.get(i) + "]");
            System.out.print("Result = [");
            if ( error_strings.size() == 0 ){
                System.out.print("Valid sentence");
            } else {
                num_valid_strings--;
                System.out.print("Invalid sentence: ");
                System.out.print(error_strings.toString());
            }
            System.out.println("]");
            error_strings.clear();
        }
        
        long end = System.nanoTime();
        long microseconds = (end - start) / 1000;
		
        System.out.println("\nProcessed " + lines.size() + 
                " sentences in " + microseconds +
                " microseconds - " +
                + num_valid_strings + " valid, " +
                + (lines.size()-num_valid_strings) + " invalid");
    }
    
    public static void ReadFromFile( String filename, ArrayList<String> lines ) {
        
        try {
            File file = new File(filename.toString());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            fileReader.close();
            
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
