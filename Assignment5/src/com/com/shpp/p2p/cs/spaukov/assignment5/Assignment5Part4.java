package com.shpp.p2p.cs.spaukov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    public void run() {
        String filename = "sources/csv.csv";
        int columnIndex = 1;
        String column = extractColumn(filename, columnIndex).toString();
        System.out.println(column);
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> returnString = new ArrayList<>();
        ArrayList<String> readToString = readToString(filename);
        for (String s : readToString) {
            String calculations = getTextInThatIndex(columnIndex, s);
            if (calculations == null)
                continue;
            returnString.add("\""+calculations+"\"");
        }
        return returnString;
    }

    private String getTextInThatIndex(int number, String s) {
        String found = null;
        for (int i = 0; i < number + 1; i++) {
            if (s == null)
                return null;
            if (s.length() == 0) return null;
            if ((s.charAt(0) == '\"')) {//found brackets
                s = s.substring(1);

                int nextBrakets = s.indexOf("\"");
                if (s.indexOf('\"') == -1){return null;}
                    found = s.substring(0, nextBrakets);
                if (s.indexOf('\"') != -1)
                    s = s.substring(s.indexOf('\"'));
                if ((s.indexOf(',') != -1) && (s.length() > 1))
                    s = s.substring(s.indexOf(',') + 1);

            } else {
                int index = s.indexOf(',');
                int add = 1;
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
