package com.shpp.p2p.cs.spaukov.namesurfer.assigment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import static com.shpp.p2p.cs.spaukov.namesurfer.assigment7.NameSurferDataBase.addNewEntryToArrayList;
import static com.shpp.p2p.cs.spaukov.namesurfer.assigment7.NameSurferDataBase.colorSeq;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        clear();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        removeAll();
        addTable();
        addLabels();
        addLines();

        //maybe i'm not completely understand if the color may change after clear or not?
        // if you want to cintinue change color, please remove this
        colorSeq=0;
        //or just comment it

        addNewEntryToArrayList = new ArrayList<>();
    }



    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {

        removeAll();
        addTable();
        addLabels();
        addLines();
        // i place redraw method becouse, after creating the scene it complitely breaks everything
        redrawLinesWithNames();
    }


    //only add points, and that all
    private void redrawLinesWithNames() {

            for (NameSurferEntry nameSurferEntry : addNewEntryToArrayList) {
                //System.out.println(addNewEntryToArrayList.size());
                addPoints(nameSurferEntry);
            }

    }

    //add up and down lines
    private void addLines() {
        addSimpleLine(0, GRAPH_MARGIN_SIZE - 1, getWidth(), GRAPH_MARGIN_SIZE - 1);
        addSimpleLine(0, getHeight() - GRAPH_MARGIN_SIZE + 1, getWidth(), getHeight() - GRAPH_MARGIN_SIZE + 1);

    }

    //this method draw 1900 years
    private void addLabels() {
        int yearsNumber = START_DECADE;
        for (int i = 0; i < NDECADES; i++) {
            GLabel years = new GLabel(String.valueOf(yearsNumber));
            yearsNumber += 10;
            add(years);
            int x = (int) (i * getWidth() / (NDECADES * 1.0) + years.getWidth() / 2);
            years.setLocation(x, getHeight());
            years.setFont("Monospaced-20");
        }
    }

    //create only vertical lines
    private void addTable() {
        for (int i = 0; i < NDECADES; i++) {
            double x = i * getWidth()*1.0 / NDECADES;
            double y = 0;//just right?
            double x2 = i * getWidth()*1.0 / NDECADES;
            double y2 = getHeight();
            addSimpleLine(x, y, x2, y2);
        }
    }

    private GLine addSimpleLine(double x1, double y1, double x2, double y2) {
        GLine line = new GLine(x1, y1, x2, y2);
        add(line);
        return line;
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }


    public void addPoints(NameSurferEntry entry) {
        Color lineColor = entry.getColor();
        for (int i = 0; i < NDECADES - 1; i++) {
            double x1 = i * (getWidth()*1.0 / NDECADES);
            double y1 = GRAPH_MARGIN_SIZE + entry.getRank(i)*1.0 * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK;
            if (entry.getRank(i) == 0) {
                y1 = getHeight() - GRAPH_MARGIN_SIZE;//maybe i'm not right, but why 0 pointers drawed in task 7 in the bottom?
            }
            double x2 = (getWidth()*1.0 / (NDECADES)) * (i + 1);
            double y2 = GRAPH_MARGIN_SIZE + entry.getRank(i + 1)*1.0 * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK;
            if (entry.getRank(i + 1) == 0) {
                y2 = getHeight() - GRAPH_MARGIN_SIZE;
            }
            GLine line = addSimpleLine(x1, y1, x2, y2);
            line.setColor(lineColor);
            addDescription(entry, lineColor, x1, y1, i);

            if (i == 10) {
                addDescription(entry, lineColor, x2, y2, 11);
            }
        }


    }

    /**
     * for greater clarity,
     * graphics should be drawn in different colors.
     * The graph of the
     * first record should be blue,
     * second - red,
     * third - magenta (magenta),
     * fourth - black. After that, the colors are repeated in the same sequence;
     */
    /* Method: findEntry(name) */
    public static Color getColorBySeq() {
        Color color = null;
        switch (colorSeq) {
            case 0 -> color = Color.BLUE;
            case 1 -> color = Color.RED;
            case 3 -> color = Color.magenta;
            case 4 -> color = Color.BLACK;
            case 5 -> {
                colorSeq = -1;
                color = Color.BLUE;
            }
        }


        return color;
    }
    //and add a description Samatha 90
    private void addDescription(NameSurferEntry entry, Color color, double x, double y, int i) {
        String number = String.valueOf(entry.getRank(i) == 0 ? "*" : entry.getRank(i));
        String descr = entry.getName().concat(number);
        GLabel name = new GLabel(descr);
        name.setColor(color);
        add(name, x, y);
    }
}
