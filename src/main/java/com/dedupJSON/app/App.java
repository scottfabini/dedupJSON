package com.dedupJSON.app;

/**
 * Entry point to the dedupJSON application.
 */
public class App 
{
    /**
     * The application reads the file, filters out duplicate entries, and writes results to "result.json" file.
     * @param args Supports one command-line argument: the input file name.
     */
    public static void main( String[] args )
    {
        String inputFileName;
        String outputFileName;
        if (args == null || args.length == 0) {
            inputFileName = "leads.json";
            outputFileName = "result.json";
        } else if (args.length == 1) {
            inputFileName = args[0];
            outputFileName = "result.json";
        } else {
            inputFileName = args[0];
            outputFileName = args[1];
        }
        Lead [] leads;
        FileHandler handler = new FileHandler(inputFileName);
        leads = handler.getLeads();
        leads = Deduplicate.dedup(leads);
        handler.putLeads(outputFileName, leads);
    }
}
