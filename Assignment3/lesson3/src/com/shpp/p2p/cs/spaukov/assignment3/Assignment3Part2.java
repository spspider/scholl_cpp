package com.shpp.p2p.cs.spaukov.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.InputMismatchException;


/**
 * TODO:Douglas Hofstadter's book "Gödel, Escher, Bach" (Pulitzer Prize-winning) mentions
 * various interesting tasks that can, to some extent, be viewed through the lens of programming.
 * Here is one of them:
 */

public class Assignment3Part2 extends TextProgram {

    public void run() {
        /*
        if Number is %2==0 then / 2
        else{
        Number * 3 + 1
        }
        then check if it %2 and do it again
         */

        checkIfTheNumberPaired(Double.parseDouble(readLine("Enter a number: ")));

    }

    /**
     * in this void i will check if it paired number
     *
     * @param magicNumber number for check
     */
    private void checkIfTheNumberPaired(double magicNumber) {
        if (magicNumber == 0) {
            println("Unable to calculate");
            return;//if this condition are not comply
        }
        if (magicNumber < 0) {
            print("You mean:");
            magicNumber = magicNumber * -1;
            println(magicNumber);
        }
        if (magicNumber - Math.abs(magicNumber) > 0)
            return;//even this condition are not comply
        while (magicNumber != 1) {
            print((int) magicNumber);//print all number and cast to int
            if (magicNumber % 2 == 0) {//this number can divide on 2
                // / 2
                magicNumber = magicNumber / 2.0;//so do it
                println(" - even, then you need to divide by 2, we get " + (int) magicNumber);//even, then you need to divide by 2, we get
            } else {
                // Assignment3Part2* 3 + 1
                magicNumber = magicNumber * 3.0 + 1; //then do calculations with my instructions
                println(" - odd, so you need to multiply by 3 and add 1, we get " + (int) magicNumber);//odd, so you need to multiply by 3 and add 1, we get
            }
            //do it again
            //so do it in loop
            //until what?
            //until magicNumber != 1
            //oh my god
        }
        println("End");
    }
}
