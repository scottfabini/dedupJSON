package com.dedupJSON.app;

import java.util.*;
import java.util.stream.Stream;

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
     * Lead is added to the emailMap HashMap.
     * If a duplicate occurs and the entryDates are also identical, then the Lead that comes later in the array
     * replaces the earlier Lead.
     * TODO: Consider converting to a Java 8 Streams API using a map().filter() function. See skeleton code below.
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

    /**
     * TODO: Functional alternative approach. Not yet implemented.
     * A shot at filtering using functional paradigm. map/filter take a List and a function/predicate as arguments
     * and returns a list. But first need to pair every permutation so these pairs can be compared within the map/filter.
     * A zip function might help, but there doesn't appear to be a zip function in Java 8.
     * @param leads The array of leads
     * @return An array of Leads with duplicates (having same email or id) removed
     */
    /*
    public Lead [] dedup(Lead [] leads) {
        List<Lead> list = Arrays.asList(leads);
        Stream<Lead> stream = list.stream();
        stream.map(lead1 -> new Pair<Lead, Lead>(lead1, "Stuck here. Not sure how to pair with all the other leads"))
        .map((lead1, lead2) -> {
            if (lead1.getEmail().equals(lead2.getEmail()) && lead1.getDate().compareTo(lead2.getDate()) >= 0) { return lead1; }
            else { return lead2;}
        });
        return stream.toArray(Lead[]::new);
    }
    private class Pair<First, Second> {
        First first;
        Second second;

        Pair(First first, Second second) {
            this.first = first;
            this.second = second;
        }
    }
    */
}
