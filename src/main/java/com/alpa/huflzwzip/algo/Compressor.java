
package com.alpa.huflzwzip.algo;

import java.io.FileInputStream;

public interface Compressor {

    public void encode(FileInputStream input);

    public void decode(FileInputStream input);
}
