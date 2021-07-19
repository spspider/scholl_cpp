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
        char[] syllabl = {'e', 'a', 'o', 'y', 'u', 'i'};
        char[] currentWord = word.toCharArray();
        int numThisWords;
        numThisWords = goToFindSomeWords(syllabl, currentWord);
        if ((word.length() > 2 && word.endsWith("e")) || word.endsWith("ee") || word.endsWith("le")) {
            numThisWords--;
        }
        return numThisWords;
    }

    /**
     * In English it is quite difficult to determine the number of
     * syllabl in a word by ear. For example, in the word are only one syllable,
     * and in almost consonant area - 3.Therefore, we do not ask you to count the specific number of syllabl,
     * instead approximate the number of syllabl using the following analysis -
     * count the number of vowels in the word (including y),
     *
     * @param syllabl     - count of syllabl
     * @param currentWord - take this word
     * @return - numbers of this
     */
    private static int goToFindSomeWords(char[] syllabl, char[] currentWord) {
        boolean lastSyllable=false;
        int numThisWords = 0;
        for (char wordCombination : currentWord) {//for each letter in word
            boolean found = false; //if we dound him
            for (char currentLetter : syllabl) {
                if ((currentLetter == wordCombination) && lastSyllable) {
                    found = true;
                    lastSyllable = true;//so next word should ignore
                    break;
                } else if (currentLetter == wordCombination) {
                    numThisWords++; //add 1 value
                    found = true;
                    lastSyllable = true; //next value should ignore
                    break;
                }
            }

            if (!found)
                lastSyllable = false;
        }
        return numThisWords;
    }
}
