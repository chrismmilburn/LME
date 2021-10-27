package robot;

import utils.Compass;
import utils.RobotCommands;

import java.util.HashSet;
import java.util.Scanner;

public class RobotControl {
    private int robotXPosition;
    private int robotYPosition;
    private Compass robotBearing;
    private int maxXPosition;
    private int maxYPosition;
    private Boolean robotLost;
    private HashSet<Compass>[][] marsWarningGrid;

    public static void main(String[] args) {
        RobotControl robotControl = new RobotControl();
        Scanner input = new Scanner( System.in );
        robotControl.commandRobots(input);

    }

    public String commandRobots(Scanner input) {
        String finalBearings="";
        maxXPosition = input.nextInt();
        maxYPosition = input.nextInt();
        if((maxXPosition > 50 )||( maxYPosition > 50)) {
            return("Maximum coordinates too large (max is 50)");
        }
        marsWarningGrid = new HashSet[maxXPosition+1][maxYPosition+1];

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

        while( (currentCommand < commands.length )&&(!robotLost)) {
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
        return(robotXPosition+" "+robotYPosition+" "+robotBearing+(robotLost?" LOST":"")+" ");
    }

    private void moveForward() {

        switch (robotBearing) {
            case N:
                if(movementIsSafe(robotBearing)) {
                    if (robotYPosition == maxYPosition) {
                        saveWarning(robotBearing);
                    } else {
                        robotYPosition++;
                    }
                }
                break;
            case E:
                if(movementIsSafe(robotBearing)) {
                    if (robotXPosition == maxXPosition) {
                        saveWarning(robotBearing);
                    } else {
                        robotXPosition++;
                    }
                }
                break;
            case S:
                if(movementIsSafe(robotBearing)) {
                    if (robotYPosition == 0) {
                        saveWarning(robotBearing);
                    } else {
                        robotYPosition--;
                    }
                }
                break;
            case W:
                if(movementIsSafe(robotBearing)) {
                    if (robotXPosition == 0) {
                        saveWarning(robotBearing);
                    } else {
                        robotXPosition--;
                    }
                }
                break;
        }
    }

    // Leave a warning for other robots to avoid this maneuver
    private void saveWarning(Compass robotBearing) {
        robotLost = true;
        if( marsWarningGrid[robotXPosition][robotYPosition] == null) {
            marsWarningGrid[robotXPosition][robotYPosition] = new HashSet<>();
        }
        marsWarningGrid[robotXPosition][robotYPosition].add(robotBearing);
    }

    // Check if a warning has been left by previous lost robots.
    private Boolean movementIsSafe(Compass robotBearing) {
        if(marsWarningGrid[robotXPosition][robotYPosition] == null) {
            return true;
        } else {
            return !marsWarningGrid[robotXPosition][robotYPosition].contains(robotBearing);
        }
    }
}
