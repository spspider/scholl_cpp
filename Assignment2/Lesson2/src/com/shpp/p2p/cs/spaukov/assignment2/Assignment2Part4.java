package com.shpp.p2p.cs.spaukov.assignment2;

import acm.graphics.GDimension;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;


import java.awt.*;

/*
 * TODO:Many countries have such flags that simply consist of three colored stripes of the same size (horizontal or vertical).
 *   Among them: Armenia, Austria, Belgium, Bulgaria, Chad, France, Gabon, Germany, Guinea, Ireland, Italy, Lithuania, Luxembourg,
 *   Mali, the Netherlands, Nigeria, Peru, Romania, Russia, Yemen, well, and Ukraine, of course… I'm kidding 😃 * … Plus a few others,
 *   but in general, it doesn't matter!*  * Your task: to write a program that draws such flags.
 *   The size of the flag is regulated by constants, it must be centered in the middle of the window. The inscription
 *   “Flag of“ ”should also be at the bottom right. In Art and Science of Java, you'll probably find tips on how
 *   to calculate text size to position GLabel correctly.
 */

public class Assignment2Part4 extends WindowProgram {
    /**
     * useful links:
     * http://mathcenter.oxford.emory.edu/site/cs170/jars/breadboards_doc/breadboards/package-summary.html
     *
     */


    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 800;

    private static final String TEXT_OF_FLAG = "Flag of ";
    private static final boolean isHorizontal = false;
    /**
     * FIRST_COLOR - first color of flag
     * SECOND_COLOR - second color of flag
     * THIRD_COLOR - third color of flag
     * COUNTRY - country, you can change it)
     * TEXT_OFFSET - little offset from edges
     */
    private static final Color FIRST_COLOR = Color.BLACK;
    private static final Color SECOND_COLOR = Color.YELLOW;
    private static final Color THIRD_COLOR = Color.RED;
    private static final String COUNTRY = "Belgium";
    private static final int TEXT_OFFSET = 5;

    public void run() {

        drawFlag(isHorizontal);
        drawText(TEXT_OF_FLAG + COUNTRY);

    }
    /**
     * documentation says that i can use text Width, to know where i will place the text
     * and
     * with all text inherited GDimension (its special square were text placed)
     */
    private void drawText(String textOfFlag) {
        double positionX, positionY;

        GLabel text = new GLabel(textOfFlag);
        text.setColor(Color.BLACK);
        text.setFont("Monospaced-12");
        GDimension size = text.getSize();
        positionX = getWidth() - size.getWidth() - TEXT_OFFSET;
        positionY = getHeight() - TEXT_OFFSET;
        text.setLocation(positionX, positionY);
        add(text);

    }

    /**
     *@param isHorizontal - is the Horizontal flag? or vertical?
     * loop for drawing three flags
     */

    private void drawFlag(boolean isHorizontal) {
        for (int i = 0; i < 3; i++) {
            GRect rect = drawHorizontalOrVerticalFlag(i, isHorizontal);
            add(rect);
        }

    }

    private GRect drawHorizontalOrVerticalFlag(int i, boolean isHorizontal) {
        // make variables for different purpuses
        double rect_x, rect_y, rect_width, rect_height;

        if (isHorizontal) {
            /**
             * @param rect_x - position of start rect, if it horizontal position should be in 1.2 less than window
             * 80% OF screen it s a flag
             * @param rect_width all of screen minus half of starting position
             * @param rect_height we know that only three colors for that height should be divided to 3+1
             * @param rect_y try to make height less than window and centering all flags
             *               left side it's - subtraction value
             *               right side - positive value
             */
            rect_x = getWidth() - getWidth() / 1.2;//80% screen
            rect_width = getWidth() - rect_x * 2;
            rect_height = getHeight() / 4.0;
            rect_y = getHeight() / 2.0 - rect_height / 2 - ((i - 1) * rect_height);
        } else {
            /**
             * @param rect_width - we know that only three colors for that height should be divided to 3+1
             * @param rect_x try to make height less than window and centering all flags
             *              *               left side it's - subtraction value
             *              *               right side - positive value
             *               if this is 1t parameter
             *               rect_x = getWidth()/2(to found a centering) then subtract center of flag
             *               -rect_width/2
             *               and subtract a first flag
             *               -rect_width
             *               for second this is:
             *               0
             *               for third this is:
             *               +rect_width
             *               therefore in loop this subtraction generates by it's position
             * @param rect_y position of start rect, if it vertical position should be in 1.2 less than window
             *              * 80% OF screen it s a flag
             * @param rect_height all of screen minus half of starting position
             */
            rect_width = getWidth() / 4.0;
            rect_x = getWidth() / 2.0 - rect_width / 2 - ((i - 1) * rect_width);
            rect_y = getHeight() - getHeight() / 1.2;
            rect_height = getHeight() - rect_y * 2;
        }
        /*
         * and now we can draw all of  us flags with their position
         */
        GRect firstRect = new GRect(rect_x, rect_y, rect_width, rect_height);
        firstRect.setFilled(true);
        firstRect.setFillColor(getColor(i));

        return firstRect;
    }

    /**
     * i take flag color from predefined values
     * @param i - inside loop we need to take our flag position
     * @return flag color
     */
    private Color getColor(int i) {
        return switch (i) {
            case 0 -> FIRST_COLOR;
            case 1 -> SECOND_COLOR;
            case 2 -> THIRD_COLOR;
            default -> Color.BLACK;
        };
    }


}
