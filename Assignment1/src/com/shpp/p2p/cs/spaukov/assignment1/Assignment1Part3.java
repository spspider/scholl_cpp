package com.shpp.p2p.cs.spaukov.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {
    /*
     *
     * TODO: main concept of the finding middle point to throw the beepers, and bouncing from it
     *
     *
     */
    public void run() throws Exception {
        //lets check if we are looking to the East
        /**
         * 1. check if the Karel looking to the East, because it is a requirement of the program
         * 2. put Beeper
         * 3. go the the end of the world(to another wall)
         */
        //now place beepers in the end of the world
        placeBeepersToAllSides();
        //if Karel meet beepers,it turns around and place another
        bouncingFromBeepersAndStartToLookMiddlePoint();
        //cleaning the world:
        cleanTheWorldFromBeepers();


    }

    private void cleanTheWorldFromBeepers() throws Exception {
        moveUntilWallAndRemoveBeepers();//all beepers should be removed
        turnAround();
        while (frontIsClear()) {
            if (beepersPresent()) {
                pickBeeper();
            }
            moveIfFrontClear();
        }
        if (beepersPresent()) {
            pickBeeper();
        }
    }

    private void bouncingFromBeepersAndStartToLookMiddlePoint() throws Exception {
        while (!beepersPresent()) {
            while (!beepersPresent()) {
                moveIfFrontClear();
            }
            turnAround();
            moveIfFrontClear();
            putBeeper();
            moveIfFrontClear();
            /**
             * i will check if the beepers placed before
             * if Karel facing with it, that is founding of middle point
             */
            if (beepersPresent()) {
                //middle point Founded!!!
                turnAround();
                moveIfFrontClear();
                putBeeper();
            }
        }
    }

    /**
     * 1. check if the Karel looking to the East, because it is a requirement of the program
     * 2. put Beeper
     * 3. go the the end of the world(to another wall)
     */
    private void placeBeepersToAllSides() throws Exception {
        turnHeIsLookEast();
        putBeeper();
        moveUntilWallAndRemoveBeepers();
        putBeeper();
        turnAround();
        if (!beepersPresent()) {
            putBeeper();
        }
        moveIfFrontClear();

    }


    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }


    public void moveUntilWallAndRemoveBeepers() throws Exception {

        while (frontIsClear()) {
            move();
            while (beepersPresent()) {
                //removing all beepers,were was facing
                pickBeeper();
            }
        }

    }

    //is it look to East?no? turn it!
    public void turnHeIsLookEast() throws Exception {
        while (notFacingEast()) {
            turnLeft();
        }
    }

    //move Karel if the any place to go
    public void moveIfFrontClear() throws Exception {
        if (frontIsClear()) {
            move();
        }
    }
}
