package com.learn.chapter.nine;

import java.util.Collection;
import java.util.stream.Collectors;

public class DefaultMethod implements Sized{

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int returnSum() {
        return 0;
    }
}
