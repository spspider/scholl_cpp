package com.shpp.p2p.cs.spaukov.assignment2;

import acm.graphics.GDimension;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


/* TODO: General idea - you need to fill in the empty drawPawprint method (double x, double y)
    The steepness of the code is that it has a lot of constants, and you should not forget to use them all 😉
 *
 */

public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     *
     * (Yes, I know that actual pawprints have four toes.
     * Just pretend it's a cartoon animal. ^_^)
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {
        // here is your turn to type some code!!

        //setApplicationWidthHeight
        /**
         * i will decompose my code to separate parts,
         * in the first i draw Heel in coordinates, which given t me from lesson
         */

        drawHeel(x, y);
        drawToes(x, y,FIRST_TOE_OFFSET_X,FIRST_TOE_OFFSET_Y);
        drawToes(x, y,SECOND_TOE_OFFSET_X,SECOND_TOE_OFFSET_Y);
        drawToes(x,y,THIRD_TOE_OFFSET_X,THIRD_TOE_OFFSET_Y);

    }


    /**
     * everything is simple - draw Toe in their position,
     * @param x - position in X coordinates (position +offset)
     * @param y - position in Y coordinates (position +offset)
     *
     */
    private void drawToes(double x, double y,double offsetX,double offsetY) {

            double positionX, positionY, ovalWidth, ovalHeight;
            positionX = x +  offsetX;
            positionY = y + offsetY;
            ovalWidth = TOE_WIDTH;
            ovalHeight = TOE_HEIGHT;
            GOval toe = new GOval(positionX, positionY, ovalWidth, ovalHeight);
            toe.setFilled(true);
            toe.setFillColor(Color.BLACK);
            add(toe);

    }
    /**
     * lets draw a heel
     * @param x - position of Heel relative to coordinates in window in horizontal
     * @param y - position of Heel relative to coordinates in window on vertical
     */
    private void drawHeel(double x, double y) {
        /**
         * @param ovalWidth - width of oval given in instruction
         * @param ovalHeight - height of oval given in instruction
         */
        double positionX, positionY, ovalWidth, ovalHeight;
        positionX = x+HEEL_OFFSET_X;
        positionY = y + HEEL_OFFSET_Y;
        ovalWidth = HEEL_WIDTH;
        ovalHeight = HEEL_HEIGHT;
        GOval paw = new GOval(positionX, positionY, ovalWidth, ovalHeight);
        paw.setFilled(true);
        paw.setFillColor(Color.BLACK);
        add(paw);
    }



}