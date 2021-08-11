package com.shpp.p2p.cs.spaukov.namesurfer.assigment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.awt.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    /* Constructor: NameSurferEntry(line) */
    private final String name;
    private final int[] numbersName = new int[NDECADES];
    Color color = null;//create variable

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        //parsing lines
        String[] linePopular = line.split(" ");
        //now we have "samantha 0 0 0 0 0 0"
        name = linePopular[0];//only to "samantha"
        for (int i = 1; i < linePopular.length; i++) {
            numbersName[i - 1] = Integer.parseInt(linePopular[i]);//this is 0 0 0 0 0
        }


    }





    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */

    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {

        return numbersName[decade];//return static decade
    }



    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {//why it should be there????? but ok, i place it
        //for me it's better to live this in main method, or method Graph
        String str = name + " " + Arrays.toString(numbersName);
        return str.replaceAll(",", "");
    }


}

