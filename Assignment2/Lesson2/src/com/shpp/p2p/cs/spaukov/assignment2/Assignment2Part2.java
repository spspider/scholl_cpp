package com.shpp.p2p.cs.spaukov.assignment2;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * TODO: You may know a similar optical illusion: * Your task is to create the following:
 *  White rectangle superimposed on four circles.
 *  The program should be flexible and we recommend using constants for easy adjustment of the size of the balls.
 */

public class Assignment2Part2 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;
    public static final int OVAL_HEIGHT = 100;
    public static final int OVAL_WIDTH = 100;

    public void run() {
        /*
          make four Cycles and then Square inside it
         */
        createFourCycles();
        add(createSquareInCycles());
    }

    /**
     * calculate position of corners in square,
     * for the upper-left it's
     *      OVAL_WIDTH/2;
     *for the bottom-right it's:
     *      OVAL_HEIGHT/2;
     *width and height
     *      getWidth()-OVAL_WIDTH; - getWidth () - to know what window Width is
     * @return rect
     */
    private GRect createSquareInCycles() {

        int rect_x=OVAL_WIDTH/2;
        int rect_y=OVAL_HEIGHT/2;
        int rect_width=getWidth()-OVAL_WIDTH;
        int rect_height=getHeight()-OVAL_HEIGHT;
        GRect rect = new GRect(rect_x,rect_y,rect_width,rect_height);
        rect.setFilled(true);
        rect.setFillColor(Color.WHITE);
        rect.setColor(Color.WHITE);
        return rect;

    }


    /**
     * inside this void i create cycles first of all i need to calculate their position,
     * left upper cycle and left bottom cycle - 0
     * right upper cycle and bottom - getWidth() - OVAL_WIDTH
     * in the Y coordinates:
     *  1. same as in X coordinates - 0
     *  2. getHeight()-OVAL_HEIGHT for example height 100, oval height is 10,so coordinate Y is 100-10 = 90
     */
    private void createFourCycles() {
        for (int i = 0; i < 2; i++) {//in horizontal (ROWS)
            for(int j=0;j<2;j++) {//in vertical (COLUMNS)
               GOval oval= createCycle(i,j);
               add(oval);
            }
        }
    }

    /**
     * i made rows and columns separately, because roman sad it is needed
     * @param row number of rows
     * @param col number of columns
     * @return
     */
    private GOval createCycle(int row, int col) {
        int xPosition = getWidth() * row - row * OVAL_WIDTH;//0;getWidth()-100; - for the first parametr
        int yPosition = col*getHeight()-col*OVAL_HEIGHT;//0; - for the first parametr
        GOval oval = new GOval(xPosition, yPosition, OVAL_WIDTH, OVAL_HEIGHT);
        oval.setColor(Color.BLACK);
        oval.setFilled(true);
        oval.setFillColor(Color.BLACK);
        return oval;
    }
}

