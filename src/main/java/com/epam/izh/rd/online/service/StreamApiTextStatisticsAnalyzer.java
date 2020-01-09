package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> list = getWords(text);
        int count = list.stream().map(x -> x.length()).reduce(0, (acc, x) -> acc + x);
        return count;
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        List<String> list = getWords(text);
        Set<String> set = list.stream().collect(Collectors.toSet());
        return (int) set.stream().count();
    }

    @Override
    public List<String> getWords(String text) {
        List<String> list = Stream.of(text.split("\\W+")).collect(Collectors.toList());
        return list;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        List<String> list = getWords(text);
        Set<String> set = list.stream().collect(Collectors.toSet());
        return set;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> list = getWords(text);
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Function.identity(), x -> 1, (a, b) -> a + 1));
        return map;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);

        switch (direction) {
            case ASC:
                list = list.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
                break;
            case DESC:
                list = list.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
                break;
        }
        return list;
    }
}
