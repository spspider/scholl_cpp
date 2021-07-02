package com.shpp.p2p.cs.spaukov.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * main concept:
 * TODO: go to the end of the world, and put beepers in odd and even, i will use snake movements,
 * when he is go to the end of the row,Kaerl will turn 180degree and goes back with one step upper
 * no variables are used
 */
public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {
        doWorld();
    }

    private void doWorld() throws Exception {
        /**
         * I will use one variable,hidden from me, its check if Karel looking to south,
         * i spend a lot of time to rewrite my code,and make him workable, without global changes
         */
        while (notFacingSouth()) {
            if (!beepersPresent()) {
                putBeeper();
            }
            /**
             * now we need to remove another beepers from the world, then if possible move toward
             * i use facing North, to make another step in up direction
             * @param putTheBeeperAndMoveToward remove another beepers from the world, then if possible move toward
             */
            putTheBeeperAndMoveToward();
            putTheBeeperAndMoveToward();
        }
        if (!frontIsClear()) {
            //we will turn to North, to go to another step upper
            turnHeIsLookNorth();
            if (frontIsClear()) {
                move();
            } else {
                //turn to South, if there no way to go, and stop cycle
                turnHeIsLookSouth();
            }
        }
    }

    /**
     *
     * @throws Exception
     * check if there beepers, and go to the next step,otherwise cancel the cycle(with walls toward)
     */
    private void putTheBeeperAndMoveToward() throws Exception {
        if (frontIsClear()) {
            move();
            while (beepersPresent()) {
                //remove another beepers from world.
                pickBeeper();
            }
        } else {
            turnHeIsLookNorth();
            if (frontIsClear()) {
                move();
                makeAroundTurns();
            } else {
                turnHeIsLookSouth();
            }

        }
    }

    /**
     * i need to make around turns, to go from line to line
     * it very useful code, because my Karel turns Around when he is face looks to wall,in the end of the row
     */

    private void makeAroundTurns() throws Exception {
        //if the left Blocked?
        if (leftIsBlocked()) {
            //we turn him to Look East
            turnHeSsLookEast();
        }
        if (rightIsBlocked()) {
            //we turn him to Look West
            turnHeIsLookWest();
        }
    }
    //is it look to North?no? turn it!
    public void turnHeIsLookNorth() throws Exception {
        while (notFacingNorth()) {
            turnLeft();
        }
    }

    //is it look to East?no? turn it!
    public void turnHeSsLookEast() throws Exception {
        while (notFacingEast()) {
            turnLeft();
        }
    }

    //is it look to West?no? turn it!
    public void turnHeIsLookWest() throws Exception {
        while (notFacingWest()) {
            turnLeft();
        }
    }

    //is it look to South?no? turn it!
    public void turnHeIsLookSouth() throws Exception {
        while (notFacingSouth()) {
            turnLeft();
        }
    }

}
