package com.home.banking.transaction.controllers;

import org.junit.Test;

import java.util.Map;

public class MapTest {


    @Test
    public void testOrderedMap() {
        Map<Integer, String> map = Map.of(1, "d", 3, "b", 2, "c", 4, "a");

        System.out.println(map);

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
    }
}
