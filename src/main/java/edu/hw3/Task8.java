package edu.hw3;

public class Task8 {
    public class BackwardIterator<T> {
        private T[] elements;
        private int currentIndex;

        public BackwardIterator(T[] elements) {
            this.elements = elements;
            currentIndex = elements.length - 1;
        }

        public boolean hasNext() {
            return currentIndex > 0;
        }

        public T next() {
            return elements[currentIndex--];
        }
    }
}
