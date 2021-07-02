package com.shpp.p2p.cs.spaukov.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
TODO: we are going to collect newspaper
1. go to newspaper
2. pick it
3. return to home
 */
public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        moveToBeeper();//move to beeper
        pickThisBeeper();//pick newspaper
        returnToStart(); //return to home
    }

    private void returnToStart() throws Exception {
        turnAround(); // turn around
        moveUntilWall();//move until wall is present
        turnRight(); //turn right
        moveUntilWall();//move until wall is present
        turnRight(); //turn right
    }

    private void pickThisBeeper() throws Exception {
        pickBeeper();
    }

    private void moveToBeeper() throws Exception {
        moveUntilWall();//move until wall is present
        //turn 3 times for turn right
        turnRight();
        move();
        turnLeft();
        moveUntilBeepersPresent();//move to beeper in loop
    }


    public void moveUntilBeepersPresent() throws Exception {
        //if the no any beepers ahead - move to it
        while (!beepersPresent()) {
            move();
        }
    }

    //turn Around
    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
    //turn Right
    public void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }



    public void moveUntilWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }
}

