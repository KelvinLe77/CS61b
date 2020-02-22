public class Empty extends WeirdList {
    public Empty(int head, WeirdList tail) {
        super (head, tail);
    }
    @Override
    public int length() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public WeirdList map(IntUnaryFunction func) {
        return this;
    }
}
