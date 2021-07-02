package com.shpp.p2p.cs.spaukov.assignment3;

import com.shpp.cs.a.console.TextProgram;


/**
 * TODO:Douglas Hofstadter's book "Gödel, Escher, Bach" (Pulitzer Prize-winning) mentions
 *      various interesting tasks that can, to some extent, be viewed through the lens of programming.
 *      Here is one of them:
 */

public class Assignment3Part3 extends TextProgram {

    public void run() {
        double a = 10;//for debug purposes
        int b = 0;//for debug purposes
        println(raiseToPower(readInt("Enter base "), readInt("Enter exponent ")));
        //println(raiseToPower(a, b));
        //this is only for testing purpose, you can uncomment this for check
        //println("right:" + Math.pow(a, b));

    }

    /**
     * this is predefined void with may return right answer
     * @param base first, example 2
     * @param exponent in > how much
     * @return answer
     */
    private double raiseToPower(double base, int exponent) {


        //2 in 3 that is 2 * 2 * 2 = 8
        //3 in 3 that is 3 * 3 * 3 = 27
        double base1 = base;
        boolean minus = false;
        if (exponent < 0) { //if our exponent < 0, then make variable minus - true
            exponent *= -1;
            minus = true;
        }
        if (exponent == 0) {//exclude from calculation
            base = 1;
        }
        for (int i = 1; i < exponent; i++) {
            base *= base1;//multiply base
        }
        if (minus) {
            base = 1.0 / base;//dont forget if this exponent with minus, 1 must divide to this exponent
        }
        return base;
    }

}
