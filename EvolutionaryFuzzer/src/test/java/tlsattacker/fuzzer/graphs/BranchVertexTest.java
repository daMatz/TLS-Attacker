/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2016 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package tlsattacker.fuzzer.graphs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * 
 * @author Robert Merget - robert.merget@rub.de
 */
public class BranchVertexTest {

    @Test
    public void testBranchVertex() {
        BranchVertex vertex = new BranchVertex(1, Blocktype.RET);
        assertTrue("Failure: ProbeID in Constructor does not Equal returned ProbeID", vertex.getProbeID() == 1);
        assertTrue("Failure: Type in Constructor does not Equal returend Type", vertex.getType().equals(Blocktype.RET));
        vertex = new BranchVertex(1, Blocktype.RET, "label");
        assertTrue("Failure: ProbeID in Constructor does not Equal returned ProbeID", vertex.getProbeID() == 1);
        assertTrue("Failure: Type in Constructor does not Equal returend Type", vertex.getType().equals(Blocktype.RET));
        assertTrue("Failure: Label in Constructor does not Equal returned Label", vertex.getLabel().equals("label"));
        BranchVertex vertex2 = new BranchVertex(1, Blocktype.RET, "label");
        assertTrue("Failure: Two Vertices with same Parameters should be equal", vertex.equals(vertex2));
        BranchVertex vertex3 = new BranchVertex(1, Blocktype.RET, "uncoollabel");
        assertFalse("Failure: Two Vertices with different Label should be unequal", vertex.equals(vertex3));
        vertex3 = new BranchVertex(1, Blocktype.CNDJUMP, "label");
        assertFalse("Failure: Two Vertices with different Type should be unequal", vertex.equals(vertex3));
        vertex3 = new BranchVertex(2, Blocktype.RET, "label");
        assertFalse("Failure: Two Vertices with different ProbeID should be unequal", vertex.equals(vertex3));

    }

}