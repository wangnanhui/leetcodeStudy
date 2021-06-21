package com.java.classloder;

import sun.misc.Unsafe;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TestClassLoder {
    public static void main(String[] args) {
        List<ByteBuffer>  bytes =  new ArrayList<>();
        while(true){
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024 * 10);
            bytes.add(buffer);
        }


    }




}
