
package com.alpa.huflzwzip.algo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public interface Compressor {

    public void encode(BufferedInputStream input) throws IOException;

    public void decode(BufferedInputStream input);
}
