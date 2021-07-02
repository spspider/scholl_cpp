package com.shpp.p2p.cs.spaukov.assignment2;

import com.shpp.cs.a.console.TextProgram;

/*
TODO: write a console program that will take 3 numbers of type double (a, b, c) as input, and output the roots of a quadratic equation.
    1. we need create inputs
    2. show result
 */

/**
 * The square equation, or the equation of the second degree, is an algebraic equation of general form
 * <p>
 * ax ^ {2} + bx + c = 0,
 * where the square trinomial [1], or second degree trinomial, is represented, ax ^ {2} + bx + c, where  x is unknown,  a} a,  b} b,  c} c are coefficients, with  a \ neq 0.}  a \ neq 0.}
 * <p>
 * The root of the equation ax ^ {2} + bx + c = 0} ax ^ {2} + bx + c = 0 is the value of the variable  x, which makes the quadratic trinomial zero and the quadratic equation true numerical equality. This value is also called the root of the polynomial itself  ax ^ {2} + bx + c.}  ax ^ {2} + bx + c.}
 * <p>
 * Elements of the quadratic equation have their own names [1]:
 * <p>
 * a a is called the first or highest odds,
 * b b is called the second, mean, or coefficient of   x
 * c c is called the intercept.
 * Reduced is a quadratic equation in which the leading coefficient is equal to one [1]. Such an equation can be obtained by dividing the entire expression by the leading factor  a} a:
 */
public class Assignment2Part1 extends TextProgram {
    public void run() {
        //input number
        double a = Double.parseDouble(readLine("Please enter a:")); //readLine("Please enter a:");
        double b = Double.parseDouble(readLine("Please enter b:"));
        double c = Double.parseDouble(readLine("Please enter c:"));

        //a*(x^2) + b*x + c = 0

        //we need to find discriminant
        char Discriminant = FindDiscriminant(a, b, c);


        double X1 = 0, X2 = 0;
        switch (Discriminant) {//switch by discriminant
            case 0 -> println("There are no real roots");
            case 1 -> {
                X1 = calculateSquare(a, b, c, false);

                if (Double.isNaN(X1)) {
                    println("No any results");
                } else {
                    println("There are one root: " + X1);
                }
            }
            //
            case 2 -> {
                X1 = calculateSquare(a, b, c, false);
                X2 = calculateSquare(a, b, c, true);
                println("There are two roots: " + X1 + " and: " + X2);
            }
        }


    }

    /**
     * main formula is:
     * x=(-b±√b²-4ac)/2a
     */
    private double calculateSquare(double a, double b, double c, boolean second_root) {

        int sec_root_minus = 1;
        if (second_root) {
            sec_root_minus = -1;
        }
        return ((-b) + Math.sqrt((b * b - (4 * a * c))) * sec_root_minus) / (2 * a);//x=(-b±√b²-4ac)/2a
    }


    /**
     *
     * @param a first known value a
     * @param b second known value b
     * @param c third known value c
     * @return char discriminant, char have only 8 bits, it's takes less memory
     */
    private char FindDiscriminant(double a, double b, double c) {
        char D;
        //D=b^{2}-4ac.
        double Discriminant = (b * b) - (4 * a * c);
        if (Discriminant < 0) {//no roots
            return 0;//no roots
        } else if (Discriminant > 0) {//two roots
            return 2;//two roots
        } else if (Discriminant == 0) {//one root
            return 1;//one root
        } else {
            System.out.println("Discriminant find ERROR");
        }

        return 3;//Error (really this return are not possible)
    }
}
