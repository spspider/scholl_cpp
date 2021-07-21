package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Assignment5Part3 extends TextProgram {

    private static final String ROAD_FILE = "sources/en-dictionary.txt";
    private static final boolean LIMIT_NUMBER = false;

    /**
     * the operation is rather slow, so it is good if it is performed only once.
     */
    public void run() {
        //compareCharsArray("aaz".toCharArray(), "saadfas".toCharArray());
        while (true) {
            String word = readLine("Enter tre letters: ");
            ArrayList<String> dictionary = readToString();
            word = word.toLowerCase();
            char[] currentWord = word.toCharArray();
            if ((LIMIT_NUMBER)&&(word.length() > 3)) {
                System.out.println("Length more than 3");
                continue;
            }
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

    /**
     * In this task, you need to write a program
     * that will ask the user for a string of three letters,
     * and then display the words that can be composed of these letters.
     * The program should be looped, at each iteration to ask the user to
     * enter 3 new letters and to process them (thus letters can be any case).
     *
     * @param currentWord    - this word
     * @param wordDictionary - dictionary from course
     */
    private void compareCharsArray(char[] currentWord, char[] wordDictionary) {
        int[] foundNextIndex0 = new int[currentWord.length];
        boolean notFound = false;
        //this is neded for change length (for default is 3)

            int length = currentWord.length;
            char[] wordDictionaryThis = wordDictionary;
            for (int i = 0; i < length; i++) {
                foundNextIndex0[i] = findNextLetter(currentWord[i],  wordDictionaryThis);
                StringBuilder wordDict = new StringBuilder();
                wordDictionaryThis = wordDict.append(wordDictionaryThis).substring(foundNextIndex0[i] + 1).toCharArray();
                if (foundNextIndex0[i] == -1) {
                    notFound = true;
                    break;
                }
            }




        if (!notFound) {
            System.out.println(wordDictionary);
        }


    }

    /**
     * eat Chars before
     *
     * @param iWhereCharEats      charArray where chars to eat
     * @param inWhichWordCharEats - where is chars eat
     * @return string ready
     */
    private char[] eatCharFrom(int iWhereCharEats, char[] inWhichWordCharEats) {
        char[] chars = new char[inWhichWordCharEats.length];
        if (inWhichWordCharEats.length - iWhereCharEats >= 0)
            System.arraycopy(inWhichWordCharEats, iWhereCharEats, chars, iWhereCharEats, inWhichWordCharEats.length - iWhereCharEats);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inWhichWordCharEats);
        chars = stringBuilder.toString().substring(iWhereCharEats).toCharArray();
        //System.out.println(chars);

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

    /**
     * method read all lines from a file to stream and populates lazily as the stream is consumed.
     * Bytes from the file are decoded into characters using the specified charset.
     *
     * @return ArrayList of Strings
     */
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
