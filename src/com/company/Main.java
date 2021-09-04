package com.company;

import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Comparator;


public class Main {

    public static Optional<Integer> calculateHIndex1(List<Integer> input) {
        Map<Integer, List<Integer>> inputMap = input.stream().flatMap(x -> IntStream.rangeClosed(1, x).mapToObj(y -> y))
                .collect(Collectors.groupingBy(Function.identity()));

        Optional<Integer> hIndex = inputMap.entrySet().stream()
                .filter(x -> x.getValue().size() >= x.getKey())
                .map(x -> x.getKey()).sorted()
                .reduce((first, second) -> second);

        return hIndex;
    }

    public static Optional<Integer> calculateHIndex2(List<Integer> input) {
        Map<Integer, List<Integer>> inputMap = input.stream().flatMap(x -> IntStream.rangeClosed(1, x).mapToObj(y -> y))
                .collect(Collectors.groupingBy(Function.identity()));

        Optional<Integer> hIndex = inputMap.entrySet().stream()
                .filter(x -> x.getValue().size() >= x.getKey())
                .map(x -> x.getKey())
                .max(Comparator.naturalOrder());

        return hIndex;
    }

    public static Optional<Integer> calculateHIndex3(List<Integer> input) {
        Collections.sort(input);
        Optional<Integer> hIndex = Optional.empty();
        for (int i = input.size() - 1, j = 1; i >= 0; i--, j++) {
            Integer currentValue = input.get(i);
            if (j >= currentValue) {
                hIndex = Optional.of(currentValue);
                break;
            }
        }
        return hIndex;
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 4, 1, 4, 2, 1, 3, 5, 6);
        System.out.println(calculateHIndex1(input));
        System.out.println(calculateHIndex2(input));
        System.out.println(calculateHIndex3(input));
	// write your code here
    }
}
