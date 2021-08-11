package com.shpp.p2p.cs.spaukov.namesurfer.assigment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.shpp.p2p.cs.spaukov.namesurfer.assigment7.NameSurferDataBase.findInSinglton;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {


        /*
         * o my GOD! i need to write really big program for the short period of time
         * */
        createButtons();


    }

    NameSurferGraph tableGraph = new NameSurferGraph();

    private final JButton graphButton = new JButton("Graph");
    private final JButton clearButton = new JButton("Clear");
    private final JTextField nameNameTextField = new JTextField(MAX_NAME_FIELD);
    private final NameSurferDataBase nameData = new NameSurferDataBase(NAMES_DATA_FILE);

    private void createButtons() {
        //lets begin
        //i will start from write TEXT field
        JLabel labelNameLabel = new JLabel("Name:");
        add(labelNameLabel, NORTH);//-taken from docs
        /*
         *add some menu
         * */
        add(nameNameTextField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);

        nameNameTextField.setActionCommand("EnterPressed");//im not 100% that is works,but Google sad that one of type EnterPressed
        nameNameTextField.addActionListener(action);//this should work
        //and action listener
        addActionListeners();
        add(tableGraph);
    }

    //this action oly neded for "Enter" command
    //this is magic of java
    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            actionPerf(e);// i write another method, becouse i have two action listeners
        }
    };

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        actionPerf(e);
    }

    private void actionPerf(ActionEvent e) {
        Object sourse = e.getSource();//take sourse

        if ((sourse != null)) {// if source not null
            if (sourse.equals(clearButton)) {
                //this should be first, otherwise clear button dont work
                tableGraph.clear();
                return;
            } else if ((nameNameTextField.getText().isEmpty())) {
                //check if it empty, if no - BOOM
                System.out.println("just text something ;)");
                return;
            }
            NameSurferEntry nameSurferEntry = findInSinglton(nameNameTextField.getText());
            if (nameSurferEntry != null) {//also it's neded by our course
                if (sourse.equals(graphButton) || sourse.equals(nameNameTextField)) {
                    System.out.println(nameSurferEntry.toString());
                    tableGraph.update();
                }

            } else {
                System.out.println("returned null Sorry,didn't find, maybe you know another name?");
            }
        }

    }
}

