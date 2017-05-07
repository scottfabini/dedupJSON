package com.dedupJSON.app;

import java.util.*;

/**
 * Wrapper class for a single static method, dedup(List) which takes a List of Leads and returns a deduplicated
 * List of Leads.
 */
public class Deduplicate {

    /**
     * Deduplicate the list.
     * If a duplicate _id or email is detected, the lead with the least recent entryDate is replaced.
     * If a duplicate occurs and the entryDates are also identical, then the Lead that comes later in the List
     * replaces the earlier Lead.
     * @param leads A List of Leads
     * @return A List of Leads with duplicates (having same email or id) removed
     */
    public static List<Lead> dedup(List<Lead> leads) {
        // long startTime = System.nanoTime();
        // long endTime;
        Map<String, Lead> idMap = new HashMap<>();    // HashMap with id as key
        Map<String, Lead> emailMap = new HashMap<>(); // HashMap with email as key
        Lead leadFromIdMap;                           // Detects duplicate id in idMap
        Lead leadFromEmailMap;                        // Detects duplicate email in emailMap

        for (Lead lead : leads) {
            leadFromIdMap = idMap.get(lead.getId());
            leadFromEmailMap = emailMap.get(lead.getEmail());

            if (leadFromIdMap == null && leadFromEmailMap == null) {
                idMap.put(lead.getId(), lead);
                emailMap.put(lead.getEmail(), lead);
            }
            else if (leadFromIdMap != null
                    && lead.getId().equals(leadFromIdMap.getId())
                    && lead.getDate().compareTo(leadFromIdMap.getDate()) >= 0) {
                idMap.put(lead.getId(), lead);
                emailMap.remove(leadFromIdMap.getEmail());
                emailMap.put(lead.getEmail(), lead);
                leadFromIdMap.logChange(lead);
            } else if (leadFromEmailMap != null
                    && lead.getEmail().equals(leadFromEmailMap.getEmail())
                    && lead.getDate().compareTo(leadFromEmailMap.getDate()) >= 0) {
                emailMap.put(lead.getEmail(), lead);
                idMap.remove(leadFromEmailMap.getId());
                idMap.put(lead.getId(), lead);
                leadFromEmailMap.logChange(lead);
            }
            // The Lead values in the key-value pair of both maps must always have the same id && email
            assert(idMap.get(lead.getId()).getId() == emailMap.get(lead.getEmail()).getId());
            assert(emailMap.get(lead.getEmail()).getEmail() == emailMap.get(lead.getId()).getEmail());
        }
        // endTime = System.nanoTime();
        // System.out.println("Time: " + (endTime - startTime));
        return new ArrayList<>(idMap.values());
    }


    /*
    public static List<Lead> dedup(List<Lead> leads) {
        // long startTime = System.nanoTime();
        // long endTime;
        for (int i = 0; i < leads.size(); i++) {
            Lead lead1 = leads.get(i);
            for (int j = i + 1; j < leads.size(); j++) {
                Lead lead2 = leads.get(j);
                if (lead1.getId().equals(lead2.getId()) || lead1.getEmail().equals(lead2.getEmail())) {
                    if (lead1.getDate().compareTo(lead2.getDate()) > 0) {
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
        // endTime = System.nanoTime();
        // System.out.println("Time: " + (endTime - startTime));
        return leads;
    }
    */
}
