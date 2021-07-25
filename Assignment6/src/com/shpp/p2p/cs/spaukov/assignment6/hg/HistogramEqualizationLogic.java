package com.shpp.p2p.cs.spaukov.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        /*they want us to return histogram of luminances
        * This image is a set of pixels of the same brightness.
        * An image histogram is a distribution of brightness across an image.
        *  More precisely, it is an array of 256 integer numbers,
        * one for each possible degree of brightness,
        * where each element of the array is the number of pixels that have the corresponding brightness.
        * For example, the zero element of the array is the number of pixels with a brightness of 0,
        * the first - with a brightness of 1, etc. Looking at the histogram, you can say a
        * lot about the brightness distribution in the image. For example, here is the original
        * picture of the landscape in the form of a histogram.
        * */
        int[] result = new int[MAX_LUMINANCE + 1];
        for (int row = 0; row < luminances.length; ++row)
            for (int col = 0; col < luminances[row].length; col++) {
                //create some result source
                     result[luminances[row][col]]++;//add linu source to result
            }
        return result;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int histogrPlusPlus = 0;//create variable for change inside loop
        int[] cumulativeFreqArray = new int[histogram.length];
        /*create cycle for hystogram*/
        for (int i = 0; i < histogram.length; ++i) {//we start from 0 to his length
            histogrPlusPlus += histogram[i];//add this histogram
            cumulativeFreqArray[i] = histogrPlusPlus;
        }
        return cumulativeFreqArray;//return array of histogram
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {



        return luminances[0].length * luminances.length;//just multiply heigth to width
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        /*
        so i need to find histogram
        and i have just only test cases,
        looking throw it i found what i need to do,
                if (this.confirmResult(result, pixels)) {
            for (int row = 0; row < result.length; ++row) {
                for (int col = 0; col < result[row].length; ++col) {
                    int intensity = result[row][col];
                    result[row][col] = GImage.createRGBPixel(intensity, intensity, intensity);
                }
            }
            this is the codem which drawing image after my correction,
            nothing special, so i must to go throw histogram visualization to better understand what i should do
        * */
        int[][] lumiOfImage = new int[luminances.length][luminances[0].length];
        int[] cumulativeSum_ = cumulativeSumFor(histogramFor(luminances));
        //create two for and make him work in [][] selection
        for (int row = 0; row < luminances.length; ++row) {
            //for the each colomn
            for (int col = 0; col < luminances[0].length; col++) {
                /*this taken from our course*/
                lumiOfImage[row][col] = MAX_LUMINANCE * cumulativeSum_[luminances[row][col]]
                        / totalPixelsIn(luminances);// normally if we have for example 100*summ(500)/10231(pixels)
            }
        }
        return lumiOfImage;//return luminicence of the image
    }
}

