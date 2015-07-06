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
        int min = 1;
        int max = 30;

        public Strings() {
        }

        public Strings(int min, int max, String pool) {
            this.pool = pool;
            this.min(min);
            this.max(max);
        }

        public Strings min(int min) {
            this.min = min;
            return this;
        }

        public Strings max(int max) {
            this.max = max;
            return this;
        }

        public Strings pool(String pool) {
            this.pool = pool;
            return this;
        }

        public Strings digit() {
            return pool(Chance.digits);
        }

        public Strings symbols() {
            return pool(Chance.symbols);
        }
    }
}
