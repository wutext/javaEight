package com.learn.javaEight;

import com.learn.test.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaEightApplicationTests {

	@Test
	public void contextLoads() {
		Point p1 = new Point(5,6);
		Point p2 = p1.moveRightBy(10);

		Assert.assertEquals(15,p2.getX());
		Assert.assertEquals(6,p2.getY());
	}
}
