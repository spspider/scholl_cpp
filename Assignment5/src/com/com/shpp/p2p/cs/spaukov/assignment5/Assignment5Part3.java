package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {

    private static final String ROAD_FILE = "sources/en-dictionary.txt";
    private static final boolean LIMIT_NUMBER=true;

    public void run() {
        while (true) {
            String word = readLine("Enter tre letters: ");
            ArrayList<String> dictionary = readToString();
            word = word.toLowerCase();
            char[] currentWord = word.toCharArray();
            String newWord = findWord(currentWord, dictionary);
            System.out.println(newWord);
        }
    }

    private String findWord(char[] currentWord, ArrayList<String> dictionary) {
        String wordNumber = "";
        int size = dictionary.size();
        for (int i = 0; i < size - 1; i++) {
            String word = dictionary.get(i);
            char[] wordDictionary = word.toCharArray();
            compareCharsArray(currentWord, wordDictionary);
        }
        return wordNumber;
    }

    private void compareCharsArray(char[] currentWord, char[] wordDictionary) {
        int foundNextIndex0 = 0;
        boolean notFound = false;
        int length = LIMIT_NUMBER?3:currentWord.length;
        for (int i = 0; i < length; i++) {
            foundNextIndex0 = findNextLetter(currentWord[i], eatCharFrom(foundNextIndex0, wordDictionary));
            if (foundNextIndex0 == -1) {
                notFound = true;
                break;
            }

        }
        if (!notFound) {
            System.out.println(wordDictionary);
        }

    }

    private char[] eatCharFrom(int iWhereCharEats, char[] inWhichWordCharEats) {
        char[] chars = new char[inWhichWordCharEats.length];
        if (inWhichWordCharEats.length - iWhereCharEats >= 0)
            System.arraycopy(inWhichWordCharEats, iWhereCharEats, chars, iWhereCharEats, inWhichWordCharEats.length - iWhereCharEats);
        return chars;
    }

    private int findNextLetter(char c, char[] wordDictionary) {
        for (int i = 0; i < wordDictionary.length; i++) {
            if (c == wordDictionary[i]) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> readToString() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            /* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(Assignment5Part3.ROAD_FILE));

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
            println("Error." + e);
        }
        return lines;
    }


}
