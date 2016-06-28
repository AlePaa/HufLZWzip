package com.alpa.huffman;

import java.io.File;

/**
 * Class for benchmarking the time taken and the compression ratio of the
 * algorithm
 */
public class Diagnostics {

    private Long startTime;
    private Long originalFileSize;

    public Diagnostics() {
        startTime = new Long(0);
        originalFileSize = new Long(0);
    }

    /**
     * @param f the input file
     */
    public void setOriginalFileSize(File f) {
        originalFileSize = f.length();
    }

    public void compareFileSizes(File f) {
        long finalFileSize = f.length();
        System.out.println("Original file size: " + originalFileSize);
        System.out.println("Final file size: " + finalFileSize + " bytes");
        System.out.println("(" + calcCompressionEfficiency(finalFileSize) + " compression ratio)");
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    /**
     * Stops the timer and prints out the time taken
     *
     */
    public void stopTimer() {
        long stopTime = System.nanoTime();
        System.out.println("Elapsed time: " + calcElapsedTime(stopTime) + " milliseconds");
    }

    /**
     *
     * @param stopTime the time taken after the end of the compression
     * @return the time taken in seconds
     */
    private Long calcElapsedTime(Long stopTime) {
        return ((stopTime - startTime) / 1000000);
    }

    /**
     *
     * @param finalSize the size of the compressed output file
     * @return the ratio of the compressed file size compared to the original
     */
    private Double calcCompressionEfficiency(Long finalSize) {
        if (originalFileSize != 0) {
            return finalSize.doubleValue() / originalFileSize.doubleValue();
        } else {
            return new Double(0);
        }
    }
}
