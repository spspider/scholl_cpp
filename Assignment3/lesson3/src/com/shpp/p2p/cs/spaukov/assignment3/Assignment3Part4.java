package com.shpp.p2p.cs.spaukov.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


/**
 * TODO:Douglas Hofstadter's book "Gödel, Escher, Bach" (Pulitzer Prize-winning) mentions
 * various interesting tasks that can, to some extent, be viewed through the lens of programming.
 * Here is one of them:
 */

public class Assignment3Part4 extends WindowProgram {

    private static final double BRICK_WIDTH = 50;
    private static final double BRICK_HEIGHT = 50;

    int numberBricks = 0;

    /**
     * calculate number of bricks, and make overall width less then one
     */
    public void run() {
        numberBricks = (int) (getWidth() / BRICK_WIDTH - 1);
        drawPyramid();

    }

    private void drawPyramid() {
        drawPyramidLines();
    }

    private double calculatePositionY(int i) {
        if (i == 0) {
            return getHeight() - BRICK_HEIGHT;
        } else {
            //(getHeight() - BRICK_HEIGHT)-4*BRICK_HEIGHT)
            return (getHeight() - BRICK_HEIGHT)-i*BRICK_HEIGHT;
        }

    }

    /**
     * |  [_] [_] [_] [_] [_] [_] [_] [_] |
     * getWidth()/BRICK_WIDTH? number of bricks - 1
     * centering of window = getWidth / 2
     * centering of bricks = AllWithBricks / 2
     * 300 / 2 = 150
     * 30 * 7 = 210 / 2 = 105
     * 150 - 105 = 45 (from left)
     *
     * @param row
     * @return left offset
     */

    private double calculatePositionX(int row) {
        if (row == 0) {//first row
            return getWidth() / 2.0 - numberBricks * BRICK_WIDTH / 2;
        } else {
            return ((numberBricks - row + 1) * BRICK_WIDTH) / 2.0 - ((numberBricks - row) * BRICK_WIDTH / 2) + calculatePositionX(row - 1);
        }

    }

    /**
     * draw one line of pyramid
     */
    private void drawPyramidLines() {
        double positionX = calculatePositionX(0);
        double positionY = calculatePositionY(0);
        //draw a line of pyramid
        //first line is did:
        drawOneLineOfBricks(numberBricks, positionX, positionY);
        //second line will be the same but higher and on 1 brick less
        //x will be displaced
        //new position will be getWidth()/2 + numberBricks*BRICK_WIDTH / 2;
        //positionX = getWidth() / 2.0 - (numberBricks - 1) * BRICK_WIDTH / 2;
        //otherwise i can calculate new position of bricks relatively to previos briks
        //
        //positionX = (numberBricks * BRICK_WIDTH) / 2.0 - ((numberBricks - 1) * BRICK_WIDTH / 2);
        //and plus first offset
        //positionX = (numberBricks * BRICK_WIDTH) / 2.0 - ((numberBricks - 1) * BRICK_WIDTH / 2) + (getWidth() / 2.0 - numberBricks * BRICK_WIDTH / 2);
        //positionX = calculatePositionX(1);
        //to simplify this
        //drawOneLineOfBricks(numberBricks - 1, positionX, calculatePositionY(1));
        //positionX = calculatePositionX(2);
        //to simplify this
        //positionX = ((numberBricks - 1) * BRICK_WIDTH) / 2.0 - ((numberBricks - 2) * BRICK_WIDTH / 2) + (getWidth() / 2.0 - numberBricks * BRICK_WIDTH / 2);
        //positionX = calculatePositionX(2);
        //drawOneLineOfBricks(numberBricks - 2, positionX, calculatePositionY(2));
        //so now we can write loop with everything upper

        int step=0;
        while (step<numberBricks) {
            drawOneLineOfBricks(numberBricks - step, calculatePositionX(step), calculatePositionY(step));
            step++;
        }
        //now we need to write it in loop


    }

    private void drawOneLineOfBricks(int numberBricks, double positionX, double positionY) {
        for (int i = 0; i < numberBricks; i++) {
            add(drawBrick(positionX + i * BRICK_WIDTH, positionY));
        }
    }

    private GRect drawBrick(double positionX, double positionY) {
        GRect oneBrick = new GRect(positionX, positionY, BRICK_WIDTH, BRICK_HEIGHT);
        oneBrick.setFilled(true);
        oneBrick.setColor(Color.BLACK);
        oneBrick.setFillColor(Color.RED);
        return oneBrick;
    }


}
