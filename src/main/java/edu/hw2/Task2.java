package edu.hw2;

public class Task2 {
    public static class Rectangle {
        private final int width;
        private final int height;

        public Rectangle(int width, int height) {
            if (width < 0 || height < 0) {
                this.width = 0;
                this.height = 0;
            } else {
                this.width = width;
                this.height = height;
            }
        }

        public Rectangle() {
            this(0, 0);
        }

        public Rectangle setWidth(int width) {
            if (width < 0) {
                return new Rectangle(this.width, this.height);
            }
            return new Rectangle(width, this.height);
        }

        public Rectangle setHeight(int height) {
            return new Rectangle(this.width, height);
        }

        public double area() {
            return this.width * this.height;
        }
    }

    public static class Square extends Rectangle {
        private final int width;
        private final int height;

        public Square(int width, int height) {
            if (width < 0 || height < 0) {
                this.width = 0;
                this.height = 0;
            } else {
                this.width = width;
                this.height = width;
            }
        }

        public Square(int side) {
            this(side, side);
        }

        public Square() {
            this(0, 0);
        }

        public Rectangle setWidth(int width) {
            if (width < 0) {
                return new Rectangle(this.width, this.height);
            }
            return new Rectangle(width, width);
        }

        public Rectangle setHeight(int height) {
            if (height < 0) {
                return new Rectangle(this.width, this.height);
            }
            return new Rectangle(height, height);
        }
    }
}
