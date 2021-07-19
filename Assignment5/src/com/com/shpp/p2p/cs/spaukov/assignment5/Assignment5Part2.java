package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

    private static final char ZERO = 48;


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
        //firstly i get where is bigger value
        if (firstString.length() > secondString.length()){
            String bufferString = firstString;
            firstString = secondString;
            secondString = bufferString;
        }



        int firstLength = firstString.length(), index2 = secondString.length();//takes length of two strings

        //this string Builder reversing all strings
        firstString=new StringBuilder(firstString).reverse().toString();
        secondString=new StringBuilder(secondString).reverse().toString();

        //create some int
        int countPlus10 = 0;
        StringBuilder newStrBuilder = new StringBuilder();//final string


        for (int i = 0; i < firstLength; i++)
        {
            //let's do some calculations
            //i try to add one value to another within a 10 - digit value
            int summa = ((firstString.charAt(i) -ZERO) +(secondString.charAt(i)-ZERO) + countPlus10);
            //then append to string, but firstly divide it on 10
            char sumDivider = (char) (summa % 10 + ZERO);
            //append to previous string
            newStrBuilder.append(sumDivider);
            countPlus10 = summa / 10;
        }

        StringBuilder strBuilder1 = new StringBuilder(newStrBuilder.toString());//reformat thisStrBuilder
        for (int i = firstLength; i < index2; i++)
        {
            int summa = ((secondString.charAt(i) - ZERO) + countPlus10);
            //take only one char
            strBuilder1.append((char) (summa % 10 + ZERO));
            //make / 10 for counting our result
            countPlus10 = summa / 10;
        }
        //we need to make string from String buffer
        String stringBuffer= strBuilder1.toString();

        //check if /10 > 0  and add previous calculation
        if (countPlus10 > 0)
            stringBuffer += (char)(countPlus10 + ZERO);

        //now er need only to reverse all strings
        stringBuffer = new StringBuilder(stringBuffer).reverse().toString();

        return stringBuffer;
    }

}
