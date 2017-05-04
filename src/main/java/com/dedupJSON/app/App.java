package com.dedupJSON.app;

import java.util.List;

/**
 * Entry point to the dedupJSON application.
 */
public class App 
{
    /**
     * The application reads the file, filters out duplicate entries, and writes results to specified file.
     * @param args Supports two command-line argument: the input file name (default: leads.json), and
     *             the output file name (default: results.json).
     *             Log data is sent to std.out, and can be redirected to a file with >> log.txt at the command line.
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
        List<Lead> leads;
        FileHandler handler = new FileHandler(inputFileName);
        leads = handler.getLeads();
        leads = Deduplicate.dedup(leads);
        handler.putLeads(outputFileName, leads);
    }
}
