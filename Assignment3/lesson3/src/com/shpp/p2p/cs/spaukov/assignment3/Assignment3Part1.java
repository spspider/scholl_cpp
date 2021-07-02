package com.shpp.p2p.cs.spaukov.assignment3;

import com.shpp.cs.a.console.TextProgram;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.sql.Array;

/**
 * TODO:Doctors recommend 30 hours to take aerobics five days per day
 *   (here it says: from Mon to Fri, altogether my plan has failed)
 *   for the purpose of developing my cardiovascular health. Moreover,
 *   I would like to have 40 khvili three times a day to get a low blood pressure.
 *   Your manager: write a program, I will feed the corystuvach a quantity of chilin,
 *   spent the last seven days on the right, and obviously, report on the next:
 *   it’s enough for an hour to be right for cardiovascular health;
 *   chi bulo enough right to reduce the blood pressure and cholesterol, and, as a matter of fact, to vivodit: some days koristuvach did not reach 40 khvili per day for busyness.
 */

public class Assignment3Part1 extends TextProgram {
    private static final int DAYS_TO_CALCULATE = 7;
    private static final int MINUTES_FOR_AEROBICS = 30;
    private static final int MINUTES_FOR_LOW_BLOOD_PRESSURE = 40;
    private static final int HOW_MANY_DAYS_FOR_BLOODY = 3;
    private static final int HOW_MANY_DAYS_FOR_CARDIO = 5;

    /**
     * @example How many minutes did you do on day 1? 40
     */
    public void run() {
        /*
        write inputs
         */
        int[] inputs = new int[DAYS_TO_CALCULATE];
        for (int i = 0; i < DAYS_TO_CALCULATE; i++) {
            inputs[i] = readInt("How many minutes did you do on day " + (i + 1));

        }
        /*
        use this inputs for check
         */
        //inputs = new int[]{40, 40, 40, 40, 40, 40, 40}; //Great job! You've done enough exercise for cardiovascular health.
        //inputs = new int[]{30, 30, 30, 30, 30, 30, 30}; //Great job! You've done enough exercise for cardiovascular health.
        //inputs = new int[]{30, 30, 30, 30, 30, 40, 40}; //Great job! You've done enough exercise for cardiovascular health.
        //inputs = new int[]{10, 10, 10, 30, 30, 40, 40}; //Great job! You've done enough exercise for cardiovascular health./
        //inputs = new int[]{15, 60, 15, 60, 15, 60, 15}; //Great job! You've done enough exercise for cardiovascular health.
        //inputs = new int[]{40, 30, 30, 30, 11, 40, 30}; //Great job! You've done enough exercise for cardiovascular health.
        calculateLogic(inputs);
    }

    /**
     * 40 minutes in a week, at least 3 times in a week
     * if there no 3 times in a week
     * -> no: more than 30 min in a week
     * How mach days needed for 40 min exercises
     */
    private void calculateLogic(int[] inputs) {
        int daysWithBloodyMinutes = 0;
        int daysWithAerobics = 0;
        for (int i = 0; i < DAYS_TO_CALCULATE; i++) {
            if (inputs[i] >= MINUTES_FOR_LOW_BLOOD_PRESSURE) {
                daysWithBloodyMinutes++;
            }
            if (inputs[i] >= MINUTES_FOR_AEROBICS) {
                daysWithAerobics++;
            }
        }


        showDaysWithAerobics(daysWithAerobics);
        showDaysWithBloodPressure(daysWithBloodyMinutes);
    }

    private void showDaysWithBloodPressure(int daysWithBloodyMinutes) {
        println("Blood pressure:");
        if (daysWithBloodyMinutes >= HOW_MANY_DAYS_FOR_BLOODY) {
            /*
            low pressure blood -> ok
             */
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        } else {
            /*
               how many time is left?
             */
            println("\tYou needed to train hard for at least "+(HOW_MANY_DAYS_FOR_BLOODY - daysWithBloodyMinutes)+" more day(s) a week!");
        }

    }

    private void showDaysWithAerobics(int daysWithAerobics) {
        println("Cardiovascular health:");//aerobics
        if (daysWithAerobics >= HOW_MANY_DAYS_FOR_CARDIO) {
            /*
            aerobics -> ok
             */
            print('\t');
            println("Great job! You've done enough exercise for cardiovascular health.");
        } else {
            println("\tYou needed to train hard for at least " + (HOW_MANY_DAYS_FOR_CARDIO - daysWithAerobics) + " more day(s) a week!");
        }
    }

}
