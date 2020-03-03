package org.combinations;

import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Basic tests for the Main cli app
 */
public class MainTests {

    @Test
    public void testSolve3() {

        String outStr = runTest("-n", "6", "-3");

        assertTrue(outStr.contains("Solutions: 2"));

        outStr = runTest("-n", "6");

        assertTrue(outStr.contains("Solutions: 4"));
    }

    @Test
    public void testSolveNoPrint() {

        String outStr = runTest("-n", "4");

        assertFalse(outStr.contains(
                "- - Q - \n" +
                        "Q - - - \n" +
                        "- - - Q \n" +
                        "- Q - - "));

        assertTrue(outStr.contains("Solutions: 2"));
    }

    @Test
    public void testSolveAndPrint() {

        String outStr = runTest("-n", "4", "-p");

        assertTrue(outStr.contains(
                        "- - Q - \n" +
                        "Q - - - \n" +
                        "- - - Q \n" +
                        "- Q - - "));

        assertTrue(outStr.contains("Solutions: 2"));
    }

    private String runTest(String... args) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PrintStream currentOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            Main.main(args);
        } finally {
            System.setOut(currentOut);
        }

        String outStr = new String(out.toByteArray());

        System.out.println(outStr);
        return outStr;
    }
}
