package com.shpp.p2p.cs.spaukov.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/*
TODO:It is necessary to draw a matrix of black boxes separated by "streets".
 It may seem to you that there are dots at intersections, but they are not there.
 Don't forget to center the picture!
 */
public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public void run() {

        drawSquare();

    }

    private void drawSquare() {
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                GRect box = createBox(i, j); //where i - rows, j - columns
                add(box);
            }
        }
    }

    /**
     * make another method because roman sad it's needed
     * @param i -  rows
     * @param j - columns
     * @return Grect box
     */
    private GRect createBox(int i, int j) {
        GRect box = new GRect(xFirstPosition(i), yFirstPosition(j), BOX_SIZE, BOX_SIZE);
        box.setFilled(true);
        box.setFillColor(Color.BLACK);
        return box;
    }

    private double yFirstPosition(int j) {
        /**
         * to calculate center of all boxes i use screen and take half of it
         * then multiply int from cycle and add box size with spacing
         */
        double yFirstPosition_offset = determineCenterOfScreenY();
        return yFirstPosition_offset + j * (BOX_SIZE + BOX_SPACING);
    }

    private double xFirstPosition(int i) {
        double xFirstPosition_offset = determineCenterOfScreenX();
        return xFirstPosition_offset + i * (BOX_SIZE + BOX_SPACING);
    }


    private double determineCenterOfScreenY() {
        /***
         * i will use Center screen to know how to make picture in center
         * example we have one box his dimension 100x100
         * half of his dimension its 50
         * and half of a screen for example 150
         * to place this box in center i subtract half of a screen from half of the box.
         * @param CenterScreen - getWidth()/2
         * @param CenterOfBoxes- this is all boxes but in one(for example 8 boxes multiply to each other)
         * @return Center of screen
         */
        double CenterScreen = getHeight() / 2.0;
        double CenterOfBoxes = ((BOX_SIZE + BOX_SPACING) * NUM_ROWS - BOX_SPACING) / 2; //subtract BOX_SPACING to resolve invisible last line
        return CenterScreen - CenterOfBoxes;
    }

    private double determineCenterOfScreenX() {
        /**
         * same as did before but in vertical dimension
         */
        double CenterScreen = getWidth() / 2.0;
        double CenterOfBoxes = ((BOX_SIZE + BOX_SPACING) * NUM_COLS - BOX_SPACING) / 2;
        return CenterScreen - CenterOfBoxes;
    }
}
