
public class BetterProgrammerTask {

    public static void main(String[] args) {
        //System.out.println(BetterProgrammerTask.getCorrectChange(313));
        //System.out.println(131 % 125);
        int amount = 9;
        int dollar = 0, quarters = 0, dimes = 0, nickels = 0, cents_1 = 0;
        dollar = amount / 100;
        quarters = (amount % 100) / 25;
        dimes = (amount % 100) % 25 / 10;
        nickels = (amount % 100) % 25 % 10 / 5;
        cents_1 = (amount % 100) % 25 % 10 % 5;
        System.out.println(new Change(dollar, quarters, dimes, nickels, cents_1)
        );

    }

    public static Change getCorrectChange(int cents) {
        /*
          Please implement this method to
          take cents as a parameter
          and return an equal amount in dollars and coins using the minimum number of
          coins possible.
          For example: 164 cents = 1 dollar, 2 quarters, 1 dime and 4 cents.
          Return null if the parameter is negative.

         */
        int dollar = 0, quarters = 0, dimes = 0, nickels = 0, cents_1 = 0;
        int amount = cents;
        dollar = amount / 100;
        quarters = (amount % 100) / 25;
        dimes = (amount % 100) % 25 / 10;
        nickels = (amount % 100) % 25 % 10 / 5;
        cents_1 = (amount % 100) % 25 % 10 % 5;
        return new Change(dollar, quarters, dimes, nickels, cents_1);
    }

    // Please do not change this class
    static class Change {

        private final int _dollars;
        private final int _quarters; //25 cents
        private final int _dimes; // 10 cents
        private final int _nickels; // 5 cents
        private final int _cents; // 1 cent

        public Change(int dollars, int quarters, int dimes, int nickels, int cents) {
            _dollars = dollars;
            _quarters = quarters;
            _dimes = dimes;
            _nickels = nickels;
            _cents = cents;
        }

        @Override
        public String toString() {
            return "Change{" + "_dollars=" + _dollars + ", _quarters=" + _quarters + ", _dimes=" + _dimes + ", _nickels=" + _nickels + ", _cents=" + _cents + '}';
        }

        public int getDollars() {
            return _dollars;
        }

        public int getQuarters() {
            return _quarters;
        }

        public int getDimes() {
            return _dimes;
        }

        public int getNickels() {
            return _nickels;
        }

        public int getCents() {
            return _cents;
        }
    }
}
