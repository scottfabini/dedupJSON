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
        if (args == null) {
            System.err.println("Usage: dedupJSON <input file name>");
        }
        Lead [] leads;
        FileHandler handler = new FileHandler(args[0]);
        leads = handler.getLeads();
        leads = Deduplicate.dedup(leads);
        handler.putLeads("result.json", leads);
    }
}
