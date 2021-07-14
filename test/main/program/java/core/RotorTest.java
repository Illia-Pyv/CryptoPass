package main.program.java.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotorTest {
    private Rotor rotor;

    /**
     * Initial rotor state
     *
     * ABCD
     * CADB
     */
    @BeforeEach
    public void setUp() {
        ArrayList<Character> in = new ArrayList<>();
        ArrayList<Character> out = new ArrayList<>();
        in.add('A');
        in.add('B');
        in.add('C');
        in.add('D');
        out.add('C');
        out.add('A');
        out.add('D');
        out.add('B');
        rotor = new Rotor(in, out);
    }

    @Test
    public void testEncodeChar() {
        rotor.setHead(0);
        // ABCD
        // CADB
        assertEquals(2, rotor.encodeChar(0));
        assertEquals(0, rotor.encodeChar(1));
        assertEquals(3, rotor.encodeChar(2));
        assertEquals(1, rotor.encodeChar(3));
        assertEquals(2, rotor.encodeChar(4));

        rotor.setHead(1);
        // BCDA
        // ADBC
        assertEquals(3, rotor.encodeChar(0));
        assertEquals(2, rotor.encodeChar(1));
        assertEquals(0, rotor.encodeChar(2));
        assertEquals(1, rotor.encodeChar(3));
        assertEquals(3, rotor.encodeChar(4));

        rotor.setHead(2);
        // CDAB
        // DBCA
        assertEquals(1, rotor.encodeChar(0));
        assertEquals(3, rotor.encodeChar(1));
        assertEquals(0, rotor.encodeChar(2));
        assertEquals(2, rotor.encodeChar(3));
        assertEquals(1, rotor.encodeChar(4));

        rotor.setHead(3);
        // DABC
        // BCAD
        assertEquals(2, rotor.encodeChar(0));
        assertEquals(3, rotor.encodeChar(1));
        assertEquals(1, rotor.encodeChar(2));
        assertEquals(0, rotor.encodeChar(3));
        assertEquals(2, rotor.encodeChar(4));
    }

    @Test
    public void testRotate() {
        rotor.setHead(0);
        // ABCD
        // CADB
        assertEquals(2, rotor.encodeChar(0));
        assertEquals(0, rotor.encodeChar(1));
        assertEquals(3, rotor.encodeChar(2));
        assertEquals(1, rotor.encodeChar(3));
        assertEquals(2, rotor.encodeChar(4));

        rotor.rotate();
        // BCDA
        // ADBC
        assertEquals(3, rotor.encodeChar(0));
        assertEquals(2, rotor.encodeChar(1));
        assertEquals(0, rotor.encodeChar(2));
        assertEquals(1, rotor.encodeChar(3));
        assertEquals(3, rotor.encodeChar(4));

        rotor.rotate();
        // CDAB
        // DBCA
        assertEquals(1, rotor.encodeChar(0));
        assertEquals(3, rotor.encodeChar(1));
        assertEquals(0, rotor.encodeChar(2));
        assertEquals(2, rotor.encodeChar(3));
        assertEquals(1, rotor.encodeChar(4));

        rotor.rotate();
        // DABC
        // BCAD
        assertEquals(2, rotor.encodeChar(0));
        assertEquals(3, rotor.encodeChar(1));
        assertEquals(1, rotor.encodeChar(2));
        assertEquals(0, rotor.encodeChar(3));
        assertEquals(2, rotor.encodeChar(4));
    }
}
