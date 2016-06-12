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
public class HuffmanBranch extends HuffmanTree {

    public final HuffmanTree leftChild, rightChild;

    // Branch constructor
    public HuffmanBranch(HuffmanTree l, HuffmanTree r) {

        leftChild = l;
        rightChild = r;
        freq = leftChild.freq + rightChild.freq;
    }
}
