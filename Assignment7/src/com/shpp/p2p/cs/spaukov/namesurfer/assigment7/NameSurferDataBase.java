package com.shpp.p2p.cs.spaukov.namesurfer.assigment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class NameSurferDataBase implements NameSurferConstants {

    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */


    //everything putted in Singleton
    //i create array of lists
    public static ArrayList<NameSurferEntry> nameSurferEntries = new ArrayList<>();

    public NameSurferDataBase(String filename) {
        ArrayList<String> readToString = readToString(filename);// i take this from my previous assigments

        if (readToString==null){
            System.out.println("sorry,cant find file: " + NAMES_DATA_FILE);
            return;
        }
        for (int i = 0; i < readToString.size() - 1; i++) {
            if (readToString.get(i) != null) {
                String line = readToString.get(i);//just for better view
                NameSurferEntry nameSurferEntry = new NameSurferEntry(line);

                nameSurferEntries.add(nameSurferEntry);
            }

        }


    }


    private ArrayList<String> readToString(String s) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            /* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(s));
            /* Read the file in triples, plotting a dot for each city. */
            while (true) {
                String line = br.readLine();
                lines.add(line);
                /* If *any* of the above three variables is null, then the longitude
                 * will be null because its call to readLine won't have any data left.
                 */
                if (line == null) break;
            }

            br.close();
        } catch (IOException e) {
            return null;
        }
        return lines;
    }


    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    static ArrayList<NameSurferEntry> addNewEntryToArrayList = new ArrayList<>();
    public static int colorSeq;

    public static NameSurferEntry findInSinglton(String name) {
        int i = 0;
        for (NameSurferEntry key : nameSurferEntries) {
            if (key.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase())) {
                if (!addNewEntryToArrayList.contains(key)) {

                    Color color = NameSurferGraph.getColorBySeq();
                    colorSeq++;
                    key.setColor(color);//also i create this variable stored in array NameSerfEntry
                    addNewEntryToArrayList.add(key);

                    //add to another list, where user entered names.
                    //i know, i know, i should use variable to shorter the RAM
                    //but whois care about it? if you run program IDEA you should have more than 4GB
                } else {
                    System.out.println("this name already printed, isn't it? check please:" + key.getName() + " i know:" + addNewEntryToArrayList.size() + " names");
                }
                return key;
            }
        }
        return null;

    }


}

