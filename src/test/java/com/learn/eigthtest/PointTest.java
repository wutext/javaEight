package com.learn.eigthtest;

import com.learn.test.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointTest {

    @Test
    public void testPoint() {
        Point p1 = new Point(5,6);
        Point p2 = p1.moveRightBy(10);

        Assert.assertEquals(10,p2.getX());
        Assert.assertEquals(10,p2.getY());
    }
}
