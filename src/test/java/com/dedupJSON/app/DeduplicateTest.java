package com.dedupJSON.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Created by sfabini on 5/3/17.
 */
public class DeduplicateTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DeduplicateTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DeduplicateTest.class);
    }

    public void testIdEquality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:30+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj22", "fun@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        List<Lead> leads = new ArrayList<Lead>();
        leads.add(firstLead);
        leads.add(secondLead);
        leads = Deduplicate.dedup(leads);
        assertEquals(leads.size(), 1);
        assertEquals(leads.get(0).getEmail(), firstLead.getEmail());
    }

    public void testIdEqualityAndEntryDateEquality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj22", "fun@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        List<Lead> leads = new ArrayList<>();
        leads.add(firstLead);
        leads.add(secondLead);
        leads = Deduplicate.dedup(leads);
        assertEquals(leads.size(), 1);
        assertEquals(leads.get(0).getEmail(), secondLead.getEmail());
    }

    public void testEmailEquality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:30+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj23", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        List<Lead> leads = new ArrayList<>();
        leads.add(firstLead);
        leads.add(secondLead);
        leads = Deduplicate.dedup(leads);
        assertEquals(leads.size(), 1);
        assertEquals(leads.get(0).getId(), firstLead.getId());
    }

    public void testInequality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj23", "fun@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        List<Lead> leads = new ArrayList<>();
        leads.add(firstLead);
        leads.add(secondLead);
        assertEquals(leads.size(), 2);
        assertEquals(leads.get(0).getEmail(), firstLead.getEmail());
        assertEquals(leads.get(1).getEmail(), secondLead.getEmail());
    }


}


