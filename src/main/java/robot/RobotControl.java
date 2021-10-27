package robot;

import utils.Compass;
import utils.RobotCommands;
import java.util.Scanner;

public class RobotControl {
    private int robotXPosition;
    private int robotYPosition;
    private Compass robotBearing;
    private int maxXPosition;
    private int maxYPosition;
    private Boolean robotLost;


    public static void main(String[] args) {
        RobotControl robotControl = new RobotControl();
        Scanner input = new Scanner( System.in );
        robotControl.commandRobots(input);

    }

    public String commandRobots(Scanner input) {
        String finalBearings="";

        maxXPosition = input.nextInt();
        maxYPosition = input.nextInt();

        while(input.hasNext()) {

            robotXPosition = input.nextInt();
            robotYPosition = input.nextInt();
            robotLost = false;
            String startingOrientation = input.next();
            robotBearing = Compass.valueOf(startingOrientation);

            String commands = input.next();
            finalBearings += processCommands( commands );

            System.out.println(finalBearings);
        }

        return finalBearings;
    }

    private String processCommands(String commandString){

        char[] commands = commandString.toCharArray();
        int currentCommand = 0;
        char command;

        while( (currentCommand < commands.length )&&(robotLost != true)) {
            command = commands[currentCommand++];
            switch (RobotCommands.valueOf(String.valueOf(command))) {
                case L:
                    robotBearing = Compass.turnLeft(robotBearing);
                    break;
                case R:
                    robotBearing = Compass.turnRight(robotBearing);
                    break;
                case F:
                    moveForward();
                    break;
                default:
                    System.out.println("Unknown command "+command);
            }
        }
        String resultLocation = robotXPosition+" "+robotYPosition+" "+robotBearing+(robotLost?" LOST":"");

        return resultLocation;
    }

    private void moveForward() {

        switch (robotBearing) {
            case N:
                if(robotYPosition == maxYPosition) {
                      robotLost = true;
                } else {
                    robotYPosition++;
                }
                break;
            case E:
                if(robotXPosition == maxXPosition) {
                    robotLost = true;
                } else {
                    robotXPosition++;
                }
                break;
            case S:
                if(robotYPosition == 0) {
                    robotLost = true;
                } else {
                    robotYPosition--;
                }
                break;
            case W:
                if(robotXPosition == 0) {
                    robotLost = true;
                } else {
                    robotXPosition--;
                }
                break;
        }
    }

}
