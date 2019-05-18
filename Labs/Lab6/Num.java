public class Num {
    private int n;

    public Num(int n) {
        this.n = n;
    }

    public int getNum() {
        return n;
    }

    public int getLastDigit() {
        return n % 10;
    }

    @Override
    public String toString() {
        return String.format("%d", n);
    }
}

