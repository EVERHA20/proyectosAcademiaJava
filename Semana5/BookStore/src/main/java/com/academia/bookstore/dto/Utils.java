package com.academia.bookstore.dto;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String convertLongListToCommaSeparatedString(List<Long> longList) {
        return longList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}