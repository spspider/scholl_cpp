package com.shpp.cs.assignments.arrays.tm;

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

        double[][]currentResultSound = getResultSoundArray(toneMatrix, column, samples, result);
        double[]sumarrySound = getSumarrizedSound(currentResultSound);
        result = getNormalizedSound(sumarrySound);
        return result;
    }

    private static double[][] getResultSoundArray(boolean[][] toneMatrix, int column,
                                                  double[][] samples, double[] result) {
        int matrixRows = toneMatrix.length;
        double[][]currentResultSound = new double[matrixRows][result.length];

        for (int row = 0; row < matrixRows; row++){
            boolean markedMatrixCell = toneMatrix[row][column];
            if(markedMatrixCell){
                double [] note = samples[row];
                currentResultSound[row] = note;
            }
        }
        return currentResultSound;
    }

    private static double[] getSumarrizedSound(double[][] currentResultSound) {
        int rows = currentResultSound.length;
        int columns = currentResultSound[0].length;
        double[] result = new double[columns];
        for (int col = 0; col < columns; col++) {
            double sum = 0;
            for (int row = 0; row < rows; row++) {
                sum += currentResultSound[row][col];
            }
            result[col] = sum;
        }
        return result;
    }

    private static double[] getNormalizedSound(double[] resultSound) {
        double max = getAbsMaximum(resultSound);
        for(int i = 0; i < resultSound.length; i++){
            if(max > 0) {
                resultSound[i] = resultSound[i] / max;
            }else{
                resultSound[i] = 0;
            }
        }
        return resultSound;
    }

    private static double getAbsMaximum(double[] array) {
        int cells = array.length;
        double max = array [0];
        for (int i = 1; i < cells; i++){
            if(Math.abs(max) < Math.abs(array[i])){
                max = array[i];
            }
        }
        return Math.abs(max);
    }
}
