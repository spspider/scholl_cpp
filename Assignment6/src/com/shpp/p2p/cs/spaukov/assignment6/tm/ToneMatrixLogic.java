package com.shpp.p2p.cs.spaukov.assignment6.tm;
public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        /* main idea that is take Matrix ----> then this matrix calculate into array
        * but!
        * there is some another problem!
        * all sounds should be normalized
        * therefore i implement method - normalize matrix*/
        double[][]matrixArraySound = takeMatrixArraySound(toneMatrix, column, samples, result);
        double[]sum = getSum(matrixArraySound);
        result = getNormalWave(sum);
        return result;
    }

    private static double[][] takeMatrixArraySound(boolean[][] toneMatrix, int column,
                                                   double[][] samples, double[] result) {
        /*get length of matrix*/
        int allRows = toneMatrix.length;
        /*write this into double[][]*/
        double[][]resultMatrix = new double[allRows][result.length];

        for (int row = 0; row < allRows; row++){
            /*if there is matrix pinned(marked)*/
            if(toneMatrix[row][column]){
                resultMatrix[row] = samples[row];//write all marked into result matrix
            }
        }
        return resultMatrix;
    }

    private static double[] getSum(double[][] currentResultSound) {
        int rows = currentResultSound.length;
        //get length of summarized sound
        int columns = currentResultSound[0].length;
        double[] resultSound = new double[columns];
        for (int col = 0; col < columns; col++) {
            double summarize = 0;
            for (int row = 0; row < rows; row++) {
                summarize += currentResultSound[row][col];
            }
            resultSound[col] = summarize;
        }
        return resultSound;
    }

    private static double[] getNormalWave(double[] resultSound) {
        double maximum = getAbsMaximum(resultSound);
        for(int i = 0; i < resultSound.length; i++){
            if (maximum!=0) {
                resultSound[i] = resultSound[i] / maximum;
            }else{
                return resultSound;
            }
        }
        return resultSound;
    }

    private static double getAbsMaximum(double[] array) {
        int arrayLength = array.length;
        double maximum = array [0];
        for (int i = 1; i < arrayLength; i++){
            if(Math.abs(maximum) < Math.abs(array[i])){
                maximum = array[i];
            }
        }
        //return Math.abs(maximum);
        return maximum;
    }
}