package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        // TODO: Replace this comment with your implementation of this method!
        return findSum(n1,n2);
    }

    static String findSum(String firstString, String secondString)
    {

        if (firstString.length() > secondString.length()){
            String bufferString = firstString;
            firstString = secondString;
            secondString = bufferString;
        }

        String stringBuffer;

        int index1 = firstString.length(), index2 = secondString.length();

        firstString=new StringBuilder(firstString).reverse().toString();
        secondString=new StringBuilder(secondString).reverse().toString();

        int toDelimeter = 0;
        StringBuilder newStrBuilder = new StringBuilder();

        for (int i = 0; i < index1; i++)
        {
            int summa = ((firstString.charAt(i) - '0') +(secondString.charAt(i) - '0') + toDelimeter);
            newStrBuilder.append((char) (summa % 10 + '0'));
            toDelimeter = summa / 10;
        }


        StringBuilder strBuilder1 = new StringBuilder(newStrBuilder.toString());
        for (int i = index1; i < index2; i++)
        {
            int summa = ((secondString.charAt(i) - '0') + toDelimeter);
            strBuilder1.append((char) (summa % 10 + '0'));
            toDelimeter = summa / 10;
        }
        stringBuffer = strBuilder1.toString();


        if (toDelimeter > 0)
            stringBuffer += (char)(toDelimeter + '0');


        stringBuffer = new StringBuilder(stringBuffer).reverse().toString();

        return stringBuffer;
    }

}
