package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

    @Test
    void testLeftTurn() {
        Compass newBearing = Compass.turnLeft(Compass.N);
        Assertions.assertEquals(Compass.W, newBearing);
    }

    @Test
    void testRightTurn() {
        Compass newBearing = Compass.turnRight(Compass.N);
        Assertions.assertEquals(Compass.E, newBearing);
    }
}