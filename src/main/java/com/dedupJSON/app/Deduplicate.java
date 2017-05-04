package com.dedupJSON.app;

import java.util.*;

/**
 * Wrapper class for a single static method, dedup(List) which takes an List of Leads and returns a deduplicated
 * List of Leads.
 */
public class Deduplicate {

    /**
     * Deduplicate the list.
     * If a duplicate _id or email is detected and the lead with the least recent entryDate is removed.
     * If a duplicate occurs and the entryDates are also identical, then the Lead that comes later in the array
     * replaces the earlier Lead.
     * @param leads A List of Leads
     * @return A List of Leads with duplicates (having same email or id) removed
     */
    public static List<Lead> dedup(List<Lead> leads) {
        for (int i = 0; i < leads.size(); i++) {
            Lead lead1 = leads.get(i);
            for (int j = i + 1; j < leads.size(); j++) {
                Lead lead2 = leads.get(j);
                if (lead1.getId().equals(lead2.getId()) || lead1.getEmail().equals(lead2.getEmail())) {
                    if (lead1.getDate().compareTo(lead2.getDate()) >= 0) {
                        leads.remove(lead2);
                        lead2.logChange(lead1);

                    } else {
                        leads.remove(lead1);
                        lead1.logChange(lead2);
                        i--; // The lead1 item is removed, so decrement i to access the next lead1 item.
                        break;
                    }
                }
            }
        }
        return leads;
    }
}
