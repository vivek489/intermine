package org.flymine.ontology;

/*
 * Copyright (C) 2002-2003 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;

public class Daml2OwlTest extends TestCase{
    public static final String NS = "http://www.flymine.org/namspace";
    public void testProcess() throws Exception {
        String before = "<rdf:RDF"
            + " xmlns:daml=\"http://www.daml.org/2001/03/daml+oil#\""
            + " xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">"
            + "<daml:UniqueProperty rdf:about=\"http://www.flymine.org/namespace#uniqProp\"/>"
            + "</rdf:RDF>";
        String expected = "<rdf:RDF\n"
            + "    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
            + "    xmlns:owl=\"http://www.w3.org/2002/07/owl#\" >\n"
            + "  <rdf:Description rdf:about=\"http://www.flymine.org/namespace#uniqProp\">\n"
            + "    <rdf:type rdf:resource=\"http://www.w3.org/2002/07/owl#FunctionalProperty\"/>\n"
            + "  </rdf:Description>\n"
            + "</rdf:RDF>\n";
        StringWriter after = new StringWriter();
        Daml2Owl.process(new BufferedReader(new StringReader(before))).write(after);
        assertEquals(expected, after.toString());
    }
}
