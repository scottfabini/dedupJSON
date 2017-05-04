package com.dedupJSON.app;

import javax.json.*;
import java.io.*;

/**
 * Handler for reading and writing to a JSON file.
 */
public class FileHandler {
    private JsonObject empObj; // The JsonObject read from the file.

    /**
     *
     * @param filename The input file name
     */
    public FileHandler(String filename) {
        File jsonInputFile;
        InputStream is;
        JsonReader reader;

        jsonInputFile = new File(filename);
        try {
            is = new FileInputStream(jsonInputFile);
            reader = Json.createReader(is);
            this.empObj = reader.readObject();
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read the Leads from the JsonObject.
     * @return The array of Leads
     */
    public Lead [] getLeads() {
        JsonArray arrayOfLeads = empObj.getJsonArray("leads");
        JsonObject leadObject;
        String id;
        String email;
        String firstName;
        String lastName;
        String address;
        String entryDate;
        Lead lead;
        Lead [] leads;
        int size = arrayOfLeads.size();
        leads = new Lead[size];
        for (int i = 0; i < size; i++) {
            leadObject = arrayOfLeads.getJsonObject(i);
            id = leadObject.getString("_id");
            email = leadObject.getString("email");
            firstName = leadObject.getString("firstName");
            lastName = leadObject.getString("lastName");
            address = leadObject.getString("address");
            entryDate = leadObject.getString("entryDate");

            lead = new Lead(id, email, firstName, lastName, address, entryDate);
            leads[i] = lead;
        }
        return leads;
    }

    /**
     * Write the array of Leads to the specified file.
     * @param fileName Output file name
     * @param leads The array of Leads
     */
    public void putLeads(String fileName, Lead [] leads) {
        PrintWriter out = null;
        int length;

        if (fileName == null || leads == null) {
            return;
        }
        try {
            out = new PrintWriter(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        out.write("{\"leads\": [\n");

        length = leads.length;
        for (int i = 0; i < length - 1; ++i) {
            out.write(leads[i].toString() + ",\n");
        }
        out.write(leads[length - 1].toString());

        out.write("]\n}");

        if (out != null) {
            out.close();
        }
    }
}
