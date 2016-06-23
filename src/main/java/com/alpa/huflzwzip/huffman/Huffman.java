package com.alpa.huflzwzip.huffman;

import com.alpa.huflzwzip.huffman.coding.CodeTree;
import com.alpa.huflzwzip.huffman.coding.FrequencyTable;
import com.alpa.huflzwzip.huffman.coding.CanonicalCode;
import com.alpa.huflzwzip.huffman.coding.HuffmanEncoder;
import com.alpa.huflzwzip.huffman.coding.HuffmanDecoder;
import com.alpa.huflzwzip.huffman.io.BitOutputStream;
import com.alpa.huflzwzip.huffman.io.BitInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Huffman {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: java Huffman +/- InputFile OutputFile");
            System.exit(1);
            return;
        }
        File inputFile = new File(args[1]);
        File outputFile = new File(args[2]);

        if (null != args[0]) {
            switch (args[0]) {
                case "+": {
                    FrequencyTable freqs = getFrequencies(inputFile);
                    freqs.increment(256);
                    CodeTree code = freqs.buildCodeTree();
                    System.out.println("Creating new canon code");
                    CanonicalCode dictionary = new CanonicalCode(code, 257);
                    System.out.println("Converting to canon code tree");
                    code = dictionary.toCodeTree();

                    InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
                    BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
                    try {
                        writeDictionary(out, dictionary);
                        compress(code, in, out);
                    } finally {
                        out.close();
                        in.close();
                    }
                    break;
                }
                case "-": {
                    BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
                    try {
                        CanonicalCode canonCode = readDictionary(in);
                        CodeTree code = canonCode.toCodeTree();
                        decompress(code, in, out);
                    } finally {
                        out.close();
                        in.close();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    static CanonicalCode readDictionary(BitInputStream in) throws IOException {
        int[] codeLengths = new int[257];
        for (int i = 0; i < codeLengths.length; i++) {
            int val = 0;
            for (int j = 0; j < 8; j++) {
                val = (val << 1) | in.readNoEof();
            }
            System.out.println("Writing to codeLengths: " + val);
            codeLengths[i] = val;
        }
        return new CanonicalCode(codeLengths);
    }

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

    private static FrequencyTable getFrequencies(File file) throws IOException {
        FrequencyTable freqs = new FrequencyTable(new int[257]);
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            while (true) {
                int b = input.read();
                if (b == -1) {
                    break;
                }
                freqs.increment(b);
            }
        }
        return freqs;
    }

    static void writeDictionary(BitOutputStream out, CanonicalCode canonCode) throws IOException {
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
