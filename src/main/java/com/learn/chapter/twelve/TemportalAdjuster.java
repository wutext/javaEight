package com.learn.chapter.twelve;

import java.time.temporal.Temporal;

@FunctionalInterface
public interface TemportalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
