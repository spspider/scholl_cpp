package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * TODO: Your task is to write a method:
 * <p>
 * private int syllablesInWord (String word)
 * … Which takes the word parameter and returns the number of syllables in it. Of course, you can write such a method for the Ukrainian language, but it will be too easy. Therefore, we suggest you write a method for calculating syllables in English, it's all a little more interesting.
 * <p>
 * In English it is quite difficult to determine the number of syllables in a word by ear. For example, in the word are only one syllable, and in almost consonant area - 3.
 * Therefore, we do not ask you to count the specific number of syllables, instead approximate the number of syllables using the following analysis - count the number of vowels in a word (including y), except:
 */
public class Assignment5Part4 extends TextProgram {
    /**
     * resolve this final attributes for change csv file parsing
     */
    private static final char DELIMITER = ',';
    private static final char BRACKETS = '\"';
    private static final char TRUNCATE = ' ';

    public void run() {
        String filename = "sources/csv.csv";
        int columnIndex = 1;
        String column = extractColumn(filename, columnIndex).toString();
        System.out.println(column);
    }

    /**
     * this method used for return array list
     *
     * @param filename    - name of filename
     * @param columnIndex - index of column
     * @return string with returned value
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> returnString = new ArrayList<>();
        ArrayList<String> readToString = readToString(filename);
        for (String s : readToString) {
            try {
                String calculations = getTextInThatIndex(columnIndex, s);
                if (calculations == null)
                    continue;
                returnString.add("\"" + calculations + "\"");
            } catch (NullPointerException e) {
                System.out.println("sorry" + e);
            }

        }
        return returnString;
    }

    /**
     * Optionally, you can enclose the contents of the cells, even if they do not contain a comma.
     * which opens a CSV file named filename,
     * finds the column and assigns it an index
     * (0 for the first column, 1 for the second, and so on),
     * then returns an ArrayList that contains information from that column.
     */
    private String getTextInThatIndex(int number, String s) {
        String found = null;
        for (int i = 0; i < number + 1; i++) {
            if (s == null)
                return null;
            //if there is lenght == 0 return null
            if (s.length() == 0) return null;
            //look for brackets
            s = sTurncate(s);
            if ((s.charAt(0) == BRACKETS)) {//found brackets
                //substring this brackets so we have "
                s = s.substring(1);
                //where is the end of this message
                int nextBrakets = s.indexOf(BRACKETS);
                int nextDecimal = s.indexOf(DELIMITER);
                if (nextDecimal > nextBrakets) {//double brackets
                    nextBrakets = nextDecimal;
                }
                if (s.indexOf(BRACKETS) == -1) {
                    return null;
                }
                found = s.substring(0, nextBrakets);
                if (s.indexOf(BRACKETS) != -1)
                    s = s.substring(s.indexOf(BRACKETS));
                //this needed for avoid null pointer Exception
                if ((s.indexOf(DELIMITER) != -1) && (s.length() > 1))
                    s = s.substring(s.indexOf(DELIMITER) + 1);

            } else {
                s = sTurncate(s);
                int index = s.indexOf(DELIMITER);
                int add = 1;//if next is not null
                if (index == -1) {
                    add = 0;
                    index = s.length();
                }
                found = s.substring(0, index);
                s = s.substring(index + add);
            }
        }
        return found;

    }


    private String sTurncate(String s) {
        while (s.charAt(0) == TRUNCATE) {//if there is non special symbol, truncate it
            s = s.substring(1);
        }
        return s;
    }

    /**
     * method read all lines from a file to stream and populates lazily as the stream is consumed.
     * Bytes from the file are decoded into characters using the specified charset.
     *
     * @param s - string
     * @return ArrayList of Strings
     */
    private ArrayList<String> readToString(String s) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            /* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(s));
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
