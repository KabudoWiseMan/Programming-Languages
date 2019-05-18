public class Marks {
    private int math, comp, rus;

    public Marks(int math, int copm, int rus) {
        this.math = math;
        this.comp = copm;
        this.rus = rus;
    }

    public int sum() {
        return math + comp + rus;
    }

    public int getMath() {
        return math;
    }

    public int getComp() {
        return comp;
    }

    public int getRus() {
        return rus;
    }

    public boolean equals(Marks m) {
        if(this.math == m.math &&
                this.comp == m.comp &&
                this.rus == m.rus) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d", math, comp, rus);
    }
}
