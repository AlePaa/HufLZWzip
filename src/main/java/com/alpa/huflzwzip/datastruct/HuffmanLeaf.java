/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpa.huflzwzip.datastruct;

/**
 *
 * @author alpa
 */
public class HuffmanLeaf extends HuffmanTree {

    public final char symbol;

    public HuffmanLeaf(Character s, int f) {
        symbol = s;
        freq = f;
    }
}
