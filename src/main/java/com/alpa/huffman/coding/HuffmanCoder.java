package com.alpa.huffman.coding;

import com.alpa.huffman.io.BitInputStream;
import com.alpa.huffman.io.BitOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helper class for the main class. Handles the actual compression and
 * expansion.
 */
public class HuffmanCoder {

    /**
     * Encodes the input file and writes it into output
     *
     * @param inputFile An uncompressed input file
     * @param outputFile The compressed output file
     * @throws IOException
     */
    public static void compressFile(File inputFile, File outputFile) throws IOException {
        // Count the symbol weights in the input file
        FrequencyTable freqs = getFrequencies(inputFile);
        freqs.increment(256);
        // Build a Huffman tree from the frequencies
        CodeTree code = freqs.buildCodeTree();
        // Build the canonical code from the code tree
        CanonicalCode canonical = new CanonicalCode(code, 257);
        // Rebuild the code tree after canonical coding
        code = canonical.toCodeTree();

        InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
        BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        try {
            // Write the canonical coding to output
            writeCanonical(out, canonical);
            // Compress the input file and write to output
            compress(code, in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Performs a file decoding on the input file and writes it in the output
     *
     * @param inputFile A compressed input file
     * @param outputFile The uncompressed output file
     * @throws IOException
     */
    public static void expandFile(File inputFile, File outputFile) throws IOException {
        BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
        try {
            CanonicalCode canonCode = readCanonicalFromFile(in);
            CodeTree code = canonCode.toCodeTree();
            decompress(code, in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Counts the frequencies for all symbols in the input file
     *
     * @param file the input file to count symbol frequencies from
     * @return a table of frequencies for the file's symbols
     * @throws IOException
     */
    private static FrequencyTable getFrequencies(File file) throws IOException {
        FrequencyTable freqs = new FrequencyTable(new int[257]);
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            while (true) {
                int b = input.read();
                // EOF
                if (b == -1) {
                    break;
                }
                freqs.increment(b);
            }
        }
        return freqs;
    }

    /**
     * Reads the prepended canonical code lengths from the input
     * @param in the input file to read from
     * @return the canonical code for the input file
     * @throws IOException
     */
    static CanonicalCode readCanonicalFromFile(BitInputStream in) throws IOException {
        int[] codeLengths = new int[257];
        for (int i = 0; i < codeLengths.length; i++) {
            int val = 0;
            for (int j = 0; j < 8; j++) {
                val = (val << 1) | in.readNoEof();
            }
            codeLengths[i] = val;
        }
        return new CanonicalCode(codeLengths);
    }

    /**
     * Performs decompression on the input file
     * 
     * @param code the code tree to use in decoding
     * @param in the compressed input file
     * @param out the file to write to
     * @throws IOException 
     */
    static void decompress(CodeTree code, BitInputStream in, OutputStream out) throws IOException {
        HuffmanDecoder dec = new HuffmanDecoder(in);
        dec.codeTree = code;
        while (true) {
            int symbol = dec.read();
            if (symbol == 256) {
                break;
            }
            out.write(symbol);
        }
    }

    /**
     * Writes the canonical code to output
     * @param out the output file to write in
     * @param canonCode the canonical code to write
     * @throws IOException 
     */
    static void writeCanonical(BitOutputStream out, CanonicalCode canonCode) throws IOException {
        for (int i = 0; i < canonCode.getSymbolLimit(); i++) {
            int val = canonCode.getCodeLength(i);

            if (val >= 256) {
                throw new RuntimeException("Symbol code is too long");
            }

            for (int j = 7; j >= 0; j--) {
                out.write((val >>> j) & 1);
            }
        }
    }

    /**
     * Performs the file compression
     * @param code codebook to use in encoding
     * @param in the file to compress
     * @param out the output file to write in
     * @throws IOException 
     */
    static void compress(CodeTree code, InputStream in, BitOutputStream out) throws IOException {
        HuffmanEncoder encoder = new HuffmanEncoder(out);
        encoder.codeTree = code;
        while (true) {
            int b = in.read();
            if (b == -1) {
                break;
            }
            encoder.write(b);
        }
        encoder.write(256);
    }
}
