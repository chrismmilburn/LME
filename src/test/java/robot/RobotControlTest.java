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
        Assertions.assertEquals("1 1 E ",result);
    }

    @Test
    void turnRobot() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n R".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("1 1 S ",result);
    }

    @Test
    void moveRobotOneSquare() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("2 1 E ",result);
    }

    @Test
    void moveRobotOffGrid() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 0 1 W\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("0 1 W LOST ",result);
    }

    @Test
    void moveTwoRobots() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n RFRFRFRF\n 3 2 N\n FRRFLLFFRRFLL".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("1 1 E 3 3 N LOST ",result);
    }

    @Test
    void testUsingLostFlag() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 3 \n 1 1 E\n RFRFRFRF\n 3 2 N\n FRRFLLFFRRFLL\n 0 3 W\n LLFFFLFLFL".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("1 1 E3 3 N LOST2 3 S ", result);
    }

    @Test
    void testCornerCase() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("10 10 \n 0 10 N\n F\n 0 10 N\n LF\n 0 10 N\n FLF".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("0 10 N LOST0 10 W LOST0 10 W ", result);
    }

    @Test
    void testDropOutBottom() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("10 10 \n 0 0 S\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("0 0 S LOST ",result);
    }

    @Test
    void testComeOutTop() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("10 10 \n 10 10 N\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("10 10 N LOST ",result);
    }
    @Test
    void testDropOutLeftEdge() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("10 10 \n 0 0 W\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("0 0 W LOST ",result);
    }

    @Test
    void testDropOutRightEdge() {
        RobotControl underTest = new RobotControl();
        InputStream testIn = new ByteArrayInputStream("5 5 \n 5 0 E\n F".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(testIn);
        String result = underTest.commandRobots(scanner);
        Assertions.assertEquals("5 0 E LOST ",result);
    }
}