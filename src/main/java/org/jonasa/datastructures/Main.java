package org.jonasa.datastructures;

import lombok.extern.slf4j.Slf4j;
import org.jonasa.datastructures.hashmap.MyHashMap;

@Slf4j
public class Main {
    private static final MyHashMap<Integer, String> mhm = new MyHashMap<>();

    public static void main(String[] args) {
        mhm.put(1, "one");
        mhm.put(2, "two");
        mhm.put(3, "three");
        mhm.put(3, "three");
        mhm.put(4, "four");
        mhm.put(5, "five");
        mhm.put(6, "six");
        mhm.put(7, "seven");
        mhm.put(8, "eight");
        mhm.put(9, "nine");
        mhm.put(10, "ten");
        mhm.put(11, "eleven");
        mhm.put(12, "twelve");
        mhm.put(13, "thirteen");
        mhm.put(14, "fourteen");


        log.info(mhm.get(3));
        log.info(mhm.get(4));
        log.info(mhm.get(5));
    }
}
