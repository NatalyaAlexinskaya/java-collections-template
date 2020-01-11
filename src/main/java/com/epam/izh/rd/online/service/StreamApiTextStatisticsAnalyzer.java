package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> list = getWords(text);
        return list.stream().map(String::length).reduce(0, Integer::sum);
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text.split("\\W+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        List<String> list = getWords(text);
        return new HashSet<>(list);
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> list = getWords(text);
        return list.stream().collect(Collectors.toMap(Function.identity(), x -> 1, (a, b) -> a + 1));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);

        if (direction.equals(Direction.ASC)) {
            list = list.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        } else {
            list = list.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        }
        return list;
    }
}
