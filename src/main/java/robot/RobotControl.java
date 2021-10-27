package robot;

import utils.Compass;

import java.util.Scanner;

public class RobotControl {
    private int xPosition;
    private int yPosition;
    private Compass bearing;
    private int maxXSize;
    private int maxYSize;
    private Boolean robotLost;


    public static void main(String[] args) {
        RobotControl robotControl = new RobotControl();
        Scanner input = new Scanner( System.in );
        robotControl.commandRobots(input);

    }

    public String commandRobots(Scanner input) {
        String finalBearings="";



        return finalBearings;
    }



}
