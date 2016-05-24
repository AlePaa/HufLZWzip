package com.alpa.huflzwzip.algo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Huffman implements Compressor {

    @Override
    public void encode(FileInputStream input) {
        HashMap<Integer, Integer> frequencies = new HashMap();
        try {
            while (input.read() != -1) {
                int symbol = input.read();

                if (!frequencies.containsKey(symbol)) {
                    frequencies.put(symbol, 1);
                } else {
                    frequencies.put(symbol, frequencies.get(symbol) + 1);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void decode(FileInputStream input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
