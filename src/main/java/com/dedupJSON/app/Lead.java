package com.dedupJSON.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Lead consists of an _id, email, firstName, lastName, address, and an entryDate in "yyyy-MM-dd'T'HH:mm:ssXXX" format.
 * Supports logging to the console on change.
 */
public class Lead {
    private String _id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String entryDateString;
    private Date entryDate;

    public Lead(String _id, String email, String firstName, String lastName, String address, String entryDateString) {
        this._id = _id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.entryDateString = entryDateString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            this.entryDate = dateFormat.parse(this.entryDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return this._id;
    }
    public String getEmail() {
        return this.email;
    }
    public Date getDate() { return this.entryDate; }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("{\n");
        result.append("\"_id\": \"" + this._id + "\",\n");
        result.append("\"email\": \"" + this.email + "\",\n");
        result.append("\"firstName\": \"" + this.firstName + "\",\n");
        result.append("\"lastName\": \"" + this.lastName + "\",\n");
        result.append("\"address\": \"" + this.address + "\",\n");
        result.append("\"entryDate\": \"" + this.entryDateString + "\"\n");
        result.append("}");
        return result.toString();
    }

    /**
     * Log the change in Lead parameters to System.out. Changed fields are separated by a " -> ".
     * @param changeFrom The Lead that is changing. 'this' is the Lead that it is changing to.
     */
    public void logChange(Lead changeFrom) {
        StringBuffer result = new StringBuffer();

        if (changeFrom == null) {
            return;
        }
        result.append("{\n");
        result.append("\"_id\": \"" + this._id + "\"");
        if (!this._id.equals(changeFrom._id)) {
            result.append(" -> \"" + changeFrom._id + "\"");
        }

        result.append(",\n\"email\": \"" + this.email + "\"");
        if (!this.email.equals(changeFrom.email)) {
            result.append(" -> \"" + changeFrom.email + "\"");
        }

        result.append(",\n\"firstName\": \"" + this.firstName + "\"");
        if (!this.firstName.equals(changeFrom.firstName)) {
            result.append(" -> \"" + changeFrom.firstName + "\"");
        }

        result.append(",\n\"lastName\": \"" + this.lastName + "\"");
        if (!this.lastName.equals(changeFrom.lastName)) {
            result.append(" -> \"" + changeFrom.lastName + "\"");
        }

        result.append(",\n\"address\": \"" + this.address + "\"");
        if (!this.address.equals(changeFrom.address)) {
            result.append(" -> \"" + changeFrom.address + "\"");
        }

        result.append(",\n\"entryDate\": \"" + this.entryDateString + "\"");
        if (!this.entryDateString.equals(changeFrom.entryDateString)) {
            result.append(" -> \"" + changeFrom.entryDateString + "\"");
        }
        result.append("\n}");

        System.out.println(result.toString());
    }
}


