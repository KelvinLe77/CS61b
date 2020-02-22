public class summing implements IntUnaryFunction {
    private int sum = 0;
    public summing (int num) {
        this.sum = num;
    }
    @Override
    public int apply(int x) {
        this.sum += x;
        return x;
    }
    public int result() {
        return this.sum;
    }
}
