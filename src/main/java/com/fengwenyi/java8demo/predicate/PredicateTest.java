package com.fengwenyi.java8demo.predicate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Predicate 断言
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-04-20
 */
public class PredicateTest {

    private static final List<String> PHONES = Arrays.asList(
            "Apple X",
            "Apple XR",
            "Apple 11",
            "Apple 12",
            "Apple 13",
            "Redmi K40S"
    );

    @Test
    void test() {
        Predicate<String> isApple = s -> s.startsWith("Apple");
        long size = PHONES.stream().filter(isApple).count(); // 5
        assertEquals(5, size);
    }

    // 与，相当于 && 运算
    @Test
    void and() {
        Predicate<String> isApple = s -> s.contains("Apple");
        Predicate<String> is11 = s -> s.contains("11");
        long size = PHONES.stream().filter(isApple.and(is11)).count(); // Apple 11
        assertEquals(1, size);
    }

    // 或，相当于 || 运算
    @Test
    public void or() {
        Predicate<String> isAppleX  = s -> s.equals("Apple X");
        Predicate<String> isApple11  = s -> s.contains("Apple 11");
        long size = PHONES.stream().filter(isAppleX.or(isApple11)).count(); // 2
        assertEquals(2, size);
    }

    // 非，相当于 ! 运算
    @Test
    public void negate() {
        Predicate<String> isApple  = s -> s.startsWith("Apple");
        long size = PHONES.stream().filter(isApple.negate()).count(); // 1
        assertEquals(1, size);
    }

    // 先进行 null 判断，再进行 object的equals() 判断
    @Test
    public void isEqual() {
        long size = PHONES.stream().filter(Predicate.isEqual("Apple 11")).count(); // 1
        assertEquals(1, size);
    }

}
