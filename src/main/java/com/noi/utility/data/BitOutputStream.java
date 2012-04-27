package com.noi.utility.data;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream extends FilterOutputStream {

    protected static final int[] mask = new int[33];

    static {
        for (int i = 0; i < mask.length; i++) {
            mask[i] =  (1 << (i + 1)) - 1;
        }
    }

    protected int bitbuf;
    protected int vbits;

    private int bitsToWrite = 8;

    protected byte [] flipTable = BitInputStream.getFlipTable();

    protected boolean invertBitOrder;

    protected int fillByte = 0;

    public BitOutputStream(OutputStream out) {
        super(out);
    }

    public int getBitsToWrite() {
        return bitsToWrite;
    }

    /**
     * set how much bits should be written to stream every write() call
     * @param bitsToWrite
     */
    public void setBitsToWrite(int bitsToWrite) {
        this.bitsToWrite = bitsToWrite;
    }

    public boolean isInvertBitOrder() {
        return invertBitOrder;
    }

    public void setInvertBitOrder(boolean invertBitOrder) {
        this.invertBitOrder = invertBitOrder;
    }

    /**
     * Writes some bits from the specified int to stream.
     * @param b int which should be written
     * @throws IOException if an I/O error occurs
     * @see #setBitsToWrite
     * @see #getBitsToWrite
     */
    public void write(int b) throws IOException {
        write(b, bitsToWrite);
    }

    /**
     * Writes some bits from the specified int to stream.
     * @param b int which should be written
     * @param nbits bit count to write
     * @throws IOException if an I/O error occurs
     */
    public void write(int b, int nbits) throws IOException {
        if (nbits == 0) {
            return;
        }
        final int k = b & mask[nbits];
        bitbuf = (bitbuf << nbits) | k;
        vbits += nbits;

        write8();
    }

    protected void write8() throws IOException {
        while (vbits > 8) {
            int c = (int) (bitbuf << (32 - vbits) >>> 24);
            vbits -= 8;
            if(invertBitOrder) {
                c = flipTable[c] & 0xFF;
            }
            out.write(c);
        }
    }

    /**
     * get fill byte used to adjust stream to byte boundary.
     * @return int
     */
    public int getFillByte() {
        return fillByte;
    }

    /**
     * set fill byte used to adjust stream to byte boundary
     * @param fillByte int
     */
    public void setFillByte(int fillByte) {
        this.fillByte = fillByte & 0xFF;
    }

    /**
     * writes bits from buffer to output stream
     * @throws IOException if I/O error occurs
     */
    public void flush() throws IOException {
        write8();   //rather not needed, just to ensure
        if(vbits > 0) {
            write(fillByte, 8);
        }
        vbits = 0;
        bitbuf = 0;
        out.flush();
    }
}