package com.dedupJSON.app;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for a single static method, dedup(Lead []) which takes an array of Leads and returns a filtered
 * array of Leads.
 */
public class Deduplicate {

    /**
     * Filter using two HashMaps. The first HashMap is keyed using _id from the Lead. If a collision (duplicate)
     * is detected and the Lead being inserted has a more recent entryDate, then the Lead being inserted replaces
     * the current Lead in the idMap HashMap.
     * The second HashMap is keyed using email from the Lead. Again, if a duplicate is detected, the most recent
     * Lead is added to the HashMap.
     * If a duplicate occurs and the entryDates are also identical, then the Lead that comes later in the array
     * replaces the earlier Lead.
     * TODO: Consider converting to a Java 8 Streams API and use a filter function.
     * @param leads An array of Leads
     * @return An array of Leads with duplicates (having same email or id) removed
     */
    public static Lead [] dedup(Lead [] leads) {
        Map<String, Lead> idMap = new HashMap<>();
        Map<String, Lead> emailMap = new HashMap<>();
        Collection<Lead> dedupedById;
        Collection<Lead> fullyDeduped;

        // Filter on id
        for (Lead lead : leads) {
            Lead mapLead = idMap.get(lead.getId());
            if (mapLead == null) {
                idMap.put(lead.getId(), lead);
            }
            else if (lead.getId().equals(mapLead.getId()) && lead.getDate().compareTo(mapLead.getDate()) >= 0) {
                idMap.put(lead.getId(), lead);
                mapLead.logChange(lead);
            }
        }
        dedupedById = idMap.values();

        // Filter on email
        for (Lead lead : dedupedById) {
            Lead mapLead = emailMap.get(lead.getEmail());
            if (mapLead == null) {
                emailMap.put(lead.getEmail(), lead);
            }
            else if (lead.getEmail().equals(mapLead.getEmail()) && lead.getDate().compareTo(mapLead.getDate()) >= 0){
                emailMap.put(lead.getEmail(), lead);
                mapLead.logChange(lead);

            }
        }
        fullyDeduped = emailMap.values();
        return fullyDeduped.toArray(new Lead[fullyDeduped.size()]);
    }
}
