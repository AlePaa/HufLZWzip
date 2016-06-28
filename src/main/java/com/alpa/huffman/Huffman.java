package com.alpa.huffman;

import com.alpa.huffman.coding.HuffmanCoder;
import java.io.File;
import java.io.IOException;

/**
 * The main executable of the Huffman coding algorithm
 *
 */
public final class Huffman {

    /**
     *
     * @param args command line parameters Parameter 1 is + or - for compress
     * and expand
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: java Huffman +/- InputFile OutputFile");
            System.exit(1);
            return;
        }
        File inputFile = new File(args[1]);
        File outputFile = new File(args[2]);
        Diagnostics diag = new Diagnostics();

        diag.setOriginalFileSize(inputFile);
        diag.startTimer();
        if (null != args[0]) {
            switch (args[0]) {
                case "+": {
                    HuffmanCoder.compressFile(inputFile, outputFile);
                    break;
                }
                case "-": {
                    HuffmanCoder.expandFile(inputFile, outputFile);
                    break;
                }
                default:
                    System.err.println("First argument needs to be + or -");
                    System.exit(1);
                    return;
            }
        }
        diag.stopTimer();
        diag.compareFileSizes(outputFile);
    }

}
