/**
 * ** CATERPILLAR ***
 * o    o
 * \__/
 * /oo\
 * \()/
 * |~~|
 * |~~|
 * |~~|               /\
 * \~~\              /\/
 * \~~\____________/\/
 * \/ | | | | | | \/
 * ~~~~~~~~~~~~~~~
 * picture taken from
 * @url https://www.asciiart.eu/animals/insects/caterpillars
 */


package com.shpp.p2p.cs.spaukov.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/*
TODO:Use GOval and draw the most enchanting caterpillar in the world.
 The ovals must be superimposed on each other in the correct order,
 otherwise there will be porridge.In this task, you should not try to make the caterpillar
 be centered in the window or occupy all its space. On the other hand, you have to think about the constants yourself.
 And don't forget to comment on their purpose! The color of the mug should be different from the color of the border of this mug.
 It should be possible to easily change the number of caterpillar segments.Well, yes - free creativity!
 */
public class Assignment2Part6 extends WindowProgram {

    private static final double CATERPILLAR_HEIGHT = 50;
    private static final double CATERPILLAR_WIDTH = 50;
    private static final double START_CATERPILLAR_X = 0;
    private static final double START_CATERPILLAR_Y = 0;
    private static final int CATERPILLAR_SECTION = 11;
    private static final boolean PLACE_CATERPILLAR_IN_THE_CENTER_OF_SCREEN = false;
    private static final int CATERPILLAR_DIVIDER = 8;
    private static final Color CATERPILLAR_INSIDE_COLOR = Color.GREEN;
    private static final Color CATERPILLAR_BORDER_COLOR = Color.RED;


    public void run() {

        drawCaterpillar();
        drawCaterpillarFace();
    }

    private void drawCaterpillarFace() {
        double positionX = calculateStartX(CATERPILLAR_SECTION);
        double positionY = calculateStartY(CATERPILLAR_SECTION) + CATERPILLAR_HEIGHT;
        GLabel face = new GLabel("ツ");
        face.setFont("Monospaced-20");
        face.setLocation(positionX - face.getWidth() / 2, positionY - face.getHeight() / 2);
        add(face);
    }

    private void drawCaterpillar() {
        for (int i = 0; i < CATERPILLAR_SECTION; i++) {
            GOval caterpillarOval = drawCaterpillarOval(i);
            add(caterpillarOval);
        }
    }

    /**
     *  draw caterpillar for main task
     * i use one oval for draw another ovals
     *
     * @param i - numbers of ovals
     * @return
     */
    private GOval drawCaterpillarOval(int i) {
        GOval caterpillarOval = new GOval(calculateStartX(i), calculateStartY(i), CATERPILLAR_WIDTH, CATERPILLAR_HEIGHT);
        caterpillarOval.setFillColor(CATERPILLAR_INSIDE_COLOR);
        caterpillarOval.setFilled(true);
        caterpillarOval.setColor(CATERPILLAR_BORDER_COLOR);
        return caterpillarOval;
    }


    private double calculateStartY(int i) {
        double circlePosition;
        int divider = CATERPILLAR_DIVIDER;
        /**
         * first caterpillar circle = i;
         * next circle will be upper or lower, for example  even will be upper
         * next circle should be little higher from centering
         * @param CATERPILLAR_HEIGHT - all high, center:CATERPILLAR_HEIGHT/2
         * @param START_CATERPILLAR_Y - start position (offset)
         * @param divider - which movements from center lie caterpillar
         *
         */
        //try to find Even i
        double caterpillarCenterY = 0;
        if (PLACE_CATERPILLAR_IN_THE_CENTER_OF_SCREEN) {
            caterpillarCenterY = getHeight() / 2.0 - (CATERPILLAR_HEIGHT / 2.0);//2.0 - because i need centering  this object
        } else {
            caterpillarCenterY = CATERPILLAR_HEIGHT / divider;
        }
        if (i % 2 == 0) {
            circlePosition = (START_CATERPILLAR_Y + CATERPILLAR_HEIGHT / divider);
        } else {//odd
            circlePosition = (START_CATERPILLAR_Y - CATERPILLAR_HEIGHT / divider);
        }
        return caterpillarCenterY + circlePosition;
    }

    private double calculateStartX(int i) {
        /**
         * first caterpillar circle = i;
         * next circle = i * half size of width
         */
        double caterpillarCenterX = 0;

        if (PLACE_CATERPILLAR_IN_THE_CENTER_OF_SCREEN) {
        /*
        calculate width of caterpillar CATERPILLAR_SECTION*CATERPILLAR_WIDTH
         */
            caterpillarCenterX = getWidth() / 2.0 - (CATERPILLAR_SECTION * CATERPILLAR_WIDTH) / (CATERPILLAR_DIVIDER / 2.0);

        }
        return caterpillarCenterX + START_CATERPILLAR_X + i * (CATERPILLAR_WIDTH / 1);
    }

}