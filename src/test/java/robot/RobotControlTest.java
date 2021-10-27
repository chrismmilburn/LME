package robot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RobotControlTest {

    @Test
    void commandRobots() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n RFRFRFRF".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("1 1 E",result);
    }

    @Test
    void turnRobot() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n R".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("1 1 S",result);
    }

    @Test
    void moveRobotOneSquare() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("1 2 E",result);
    }

    @Test
    void moveRobotOffGrid() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 0 1 W\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("0 1 W LOST",result);
    }
}