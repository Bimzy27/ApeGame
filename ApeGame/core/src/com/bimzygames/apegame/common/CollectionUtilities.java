package com.bimzygames.apegame.common;

import java.util.Arrays;

public class CollectionUtilities
{
    public static <T> T[] CombineArrays(T[]... arrays) {
        return Arrays.stream(arrays)
                .flatMap(Arrays::stream)
                .toArray(size -> Arrays.copyOf(arrays[0], size));
    }
}
