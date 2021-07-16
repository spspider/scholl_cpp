package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        // TODO: Delete this comment and implement this method.
        return countSyllables(word);
    }

    public static int countSyllables(String word) {
        word = word.toLowerCase();
        char[] syllables = {'a', 'e', 'i', 'o', 'u', 'y'};
        char[] currentWord = word.toCharArray();
        int numThisWords;
        boolean lastIsSyllable = false;
        numThisWords = goToFindSomeWords(syllables, currentWord, lastIsSyllable);

        if ((word.length() > 2 && word.endsWith("e")) || word.endsWith("ee") || word.endsWith("le"))
            numThisWords--;
        return numThisWords;
    }

    private static int goToFindSomeWords(char[] syllables, char[] currentWord, boolean lastIsSyl) {
        int numThisWords = 0;
        for (char wc : currentWord) {//for each letter in word
            boolean found = false; //if we dound him
            for (char v : syllables) {
                if ((v == wc) && lastIsSyl) {
                    found = true;
                    lastIsSyl = true;//so next word should ignore
                    break;
                } else if (v == wc) {
                    numThisWords++;
                    found = true;
                    lastIsSyl = true;
                    break;
                }
            }

            if (!found)
                lastIsSyl = false;
        }
        return numThisWords;
    }
}
