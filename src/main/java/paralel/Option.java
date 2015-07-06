package paralel;


public interface Option {

    class Ints {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        boolean unsigned = false;

        public Ints min(int min) {
            this.min = min;
            return this;
        }

        public Ints max(int max) {
            this.max = max;
            return this;
        }

        public Ints unsigned(boolean unsigned) {
            this.unsigned = unsigned;
            return this;
        }

    }

    final class Floatings {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        int fixed = 4;

        public Floatings min(double min) {
            this.min = min;
            return this;
        }

        public Floatings max(double max) {
            this.max = max;
            return this;
        }

        public Floatings fixed(int fixed) {
            this.fixed = fixed;
            return this;
        }
    }

    final class Strings extends Ints {
        String pool;
        boolean unsigned = true;

        public Strings pool(String pool) {
            this.pool = pool;
            return this;
        }
    }
}
