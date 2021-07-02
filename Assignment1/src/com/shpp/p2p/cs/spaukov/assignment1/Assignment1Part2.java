package com.shpp.p2p.cs.spaukov.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * TODO:this is the code for Karel's algorithm for stones, witch are not completed
 */
public class Assignment1Part2 extends KarelTheRobot {
    public void run() throws Exception {
        //create useful copy of helpful class
        /**
         * make decomposition for better understanding
         */
        goToTheWallAndPutSomeBeepersThenReturn();
        moveItToNorthAndReturn();
    }

    /**
     * check he is the turned North?,if the is no to go, the program will be completed
     * we know that we need to go through 4 steps, to avoid repeats, mean avoid possible stones
     * @throws Exception
     */
    private void goToTheWallAndPutSomeBeepersThenReturn() throws Exception {
        while (frontIsClear()) {//infinite loop
            //check he is the turned North?
            moveItToNorthAndReturn();
            turnHeIsLookEast();
            //if the is no to go, the program will be completed
            //we know that we need to go through 4 steps, to avoid repeats, mean
            for (int i = 0; i < 4; i++) {
                if (frontIsClear()) {//avoid possible stones
                    move();
                }
            }


        }
    }

    private void moveItToNorthAndReturn() throws Exception {
        turnHeIsLookNorth();
        //go to wall and add beepers,if there
        putIfThereNoBeeperAndStopOnTheWall();
        /*
        now we need to turn Karel face to South and move to the edge of the world
         */
        turnHeIsLookSouth();
        moveUntilWall();
    }

    //we need to put beepers and stop if there wall ahead

    /**
     *the problem is:
     * after move, and if there front in not clear, Karel ignore putting beeper
     * to do this we need to put it again
     * @throws Exception
     */
    private void putIfThereNoBeeperAndStopOnTheWall() throws Exception {
        while (frontIsClear()) {
            ifBeepersPresentVoid();//check if there under robot is beeper
            move();//move
        }
        // after move, and if there front in not clear, Karel ignore putting beeper
        //to do this we need to put it again
        ifBeepersPresentVoid();

    }

    /**
     * i use turn to any sides of the world, instead simple turns, to know we now is Karel, and put him in right direction
     * @throws Exception
     */

    //if there beeper, put it
    public void ifBeepersPresentVoid() throws Exception {
        if (!beepersPresent()) {
            putBeeper();
        }
    }


    //if there no walls, go ahead!!!
    public void moveUntilWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    //is it look to North?no? turn it!
    public void turnHeIsLookNorth() throws Exception {
        while (!facingNorth()) {
            turnLeft();
        }
    }

    //is it look to East?no? turn it!
    public void turnHeIsLookEast() throws Exception {
        while (!facingEast()) {
            turnLeft();
        }
    }

    //is it look to South?no? turn it!
    public void turnHeIsLookSouth() throws Exception {
        while (!facingSouth()) {
            turnLeft();
        }
    }

}
