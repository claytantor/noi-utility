package com.noi.utility.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

import javax.mail.MessagingException;

public class Base64 {

    private static final char encodeTable[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    static int decodeTable [] = new int[0x100];
    static int lineLength = 72;

    public static int getLineLength() {
        return lineLength;
    }

    public static void setLineLength(int lineLength) {
        Base64.lineLength = lineLength;
    }


    static {
        for (int i = 0; i < decodeTable.length; i++) {
            decodeTable[i] = -1;
        }
        for (int j = 0; j < encodeTable.length; j++) {
            decodeTable[encodeTable[j]] = j;
        }
    }

    private static final char [] crlf = "\n".toCharArray();

    /**
     * base 64 encode
     * @param b data to encode
     * @return base 64 encoded String
     * @throws IOException
     */
    public static String base64Encode(byte[] b) throws IOException {
        BitInputStream bis = new BitInputStream(new ByteArrayInputStream(b));
        bis.setBitsToRead(6);
        StringBuffer sb = new StringBuffer();
        int cnt = 0;
        while (true) {
            int c = bis.read();
            //EOF reached
            if (c == -1) {
                break;
            }
            sb.append(encodeTable[c]);
            if(++cnt == lineLength) {
                sb.append("\n");
                cnt = 0;
            }
        }
        while ((sb.length() % 4) != 0) {
            //padding
            sb.append('=');
        }
        return sb.toString();
    }

    /**
     * base64 encode data from InputStream and write ict to given character stream
     * @param in InputStream
     * @param out Writer
     * @throws IOException
     */
    public static void base64Encode(InputStream in, Writer out) throws IOException {
        BitInputStream bis = new BitInputStream(in);
        char[] buffer = new char[4000];
        bis.setBitsToRead(6);
        int cnt = 0;
        int bufferPtr = 0;
        while (true) {
            int c = bis.read();
            //EOF reached
            if (c == -1) {
                break;
            }
            buffer[bufferPtr++] = encodeTable[c];
            if(++cnt == lineLength) {
                if(bufferPtr + crlf.length >= buffer.length) {
                    out.write(buffer, 0, bufferPtr);
                    bufferPtr = 0;
                }
                for (int i = 0; i < crlf.length; i++) {
                   buffer[bufferPtr++] = crlf[i];
                }
                cnt = 0;
            }
            if (bufferPtr == buffer.length) {
                out.write(buffer);
                bufferPtr = 0;
            }
        }
        while ((bufferPtr % 4) != 0) {
            //padding
            buffer[bufferPtr++] ='=';
        }
        if (bufferPtr > 0) {
            out.write(buffer, 0, bufferPtr);
        }
    }

    /**
     * decode base 64 encoded string
     * @param s String to decode
     * @return byte array
     * @throws IOException
     */
    public static byte[] base64Decode(String s) throws IOException {
        char[] chrs = s.toCharArray();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        BitOutputStream bos = new BitOutputStream((OutputStream)bout);
        bos.setBitsToWrite(6);

        boolean flush = true;

        for (int i = 0; i < chrs.length; i++) {
            char c = chrs[i];
            //start of padding
            if (c == '=') {
                flush = false;
                break;
            }
            int c0 = decodeTable[c];
            if(c0 != -1) {
                bos.write(c0);
            }
        }
        if (flush) {
            bos.flush();
        }
        return bout.toByteArray();
    }


    /**
     * decode base64 encoded character stream and write it to given OutputStream
     * @param in Reader character stream
     * @param out OutputStream
     * @throws IOException
     */
    public static void base64Decode(Reader in, OutputStream out) throws IOException {
        BitOutputStream bos = new BitOutputStream(out);
        bos.setBitsToWrite(6);

        boolean flush = true;

        while (true) {
            int c = in.read();
            if (c == -1) {
                break;
            }
            //start of padding
            if (c == '=') {
                flush = false;
                break;
            }
            final int b = decodeTable[c];
            if(b != -1) {
                bos.write(b);
            }
        }
        if (flush) {
            bos.flush();
        }
    }
    

    
    
    
}