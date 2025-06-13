package com.home.authentication;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaTest {

  public static void main(String[] args) {
    //0,1,1,2,3,5,8,13,12,7,10,8,9,17,17,16,15...
    System.out.println(solution(10000000));
  }


//  public static int solution(int[] A) {
//    // write your code in Java SE 8
//    int begin = Arrays.stream(A).boxed().sorted().findFirst().get();
//    int end = Arrays.stream(A).boxed().sorted().reduce((a,b) -> b).get();
//
//    Stream<Integer> compare = IntStream.rangeClosed(begin, end).boxed();
//    Stream<Integer> ordered = Arrays.stream(A).boxed().sorted();
//
//    return compare.sorted().filter(t -> {
//      return !ordered.collect(Collectors.toList()).contains(t);
//    }).findFirst().get();
//  }



  public static Integer solution(Integer N) {
    return Stream
      .iterate(new Integer[]{0, 1}, t -> {
        Integer sum1 = Arrays
          .stream(String.valueOf(t[0]).split(""))
          .reduce(0,
            (a,b) -> Integer.valueOf(a) + Integer.parseInt(b),
            Integer::sum);
        Integer sum2 = Arrays
          .stream(String.valueOf(t[1]).split(""))
          .reduce(0,
            (a,b) -> Integer.valueOf(a) + Integer.parseInt(b),
            Integer::sum);
        return new Integer[]{t[1], sum1 + sum2};
      })
      .limit(N)
      .map(t -> t[0])
      .skip(Math.max(0, N - 2))
      .mapToInt(Integer::intValue)
      .sum();
  }

  public static Integer fibonacci2(Integer limit) {
    return Stream
      .iterate(new Integer[]{0, 1}, t -> new Integer[]{t[1], t[0] + t[1]})
      .limit(limit)
      .map(t -> t[0])
      .mapToInt(Integer::intValue)
      .findFirst().getAsInt();
  }

//  public static Boolean isPrime(Integer n) {
//    if(n <= 0){
//      throw new RuntimeException("number must be natural");
//    }
//
//    if(n == 1) {
//      return true;
//    } else {
//      return Stream
//        .iterate(2, i -> i + 1)
//        .limit(n - 2)
//        .filter(t -> n % t == 0)
//        .count() <= 0;
//    }
//    Arrays.asList(1,2,3,4).si
//  }
//
//  public static Boolean isPalindromo(String s) {
//    val s1 = new StringBuilder(s).reverse().toString();
//    return s.equals(s1);
//  }
//
//  public static Integer reverse(Integer number) {
//    return Integer.valueOf(new StringBuffer(number).reverse().toString());
//  }
}
