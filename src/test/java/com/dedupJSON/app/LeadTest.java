package com.dedupJSON.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Created by sfabini on 5/3/17.
 */
public class LeadTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LeadTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(LeadTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }


    public void testIdEquality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj22", "fun@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Map<String, Lead> idMap= new HashMap<>();
        idMap.put(firstLead.getId(), firstLead);
        idMap.put(secondLead.getId(), secondLead);
        Collection<Lead> results = idMap.values();
        Lead [] result = (Lead []) results.toArray();
        assertTrue(firstLead.equals(secondLead));
    }

    public void testEmailEquality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj23", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:33:20+00:00");
        assertTrue(firstLead.equals(secondLead));
    }

    public void testBothEquality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:33:20+00:00");
        assertTrue(firstLead.equals(secondLead));
    }

    public void testInequality() {
        Lead firstLead = new Lead("jkj238238jdsnfsj22", "foo@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:32:20+00:00");
        Lead secondLead = new Lead("jkj238238jdsnfsj23", "fun@bar.com", "John", "Smith", "123 Street St", "2014-05-07T17:33:20+00:00");
        assertFalse(firstLead.equals(secondLead));
    }
}

