package edu.hw10.task1;

public class TestClass {
    public int a;

    public TestClass() {

    }

    public TestClass(int a) {
        this.a = a;
    }

    public static TestClass create(@Max(max = 15) int a) {
        return new TestClass(a);
    }
}

