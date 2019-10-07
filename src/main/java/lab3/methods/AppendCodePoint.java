package lab3.methods;

public class AppendCodePoint extends AbstractAppend {

    private final int codePoint;

    public AppendCodePoint(StringBuilder stringBuilder, int codePoint) {
        super(stringBuilder, 1);
        this.codePoint = codePoint;
    }

    @Override
    public void execute() {
        super.execute();
        stringBuilder.appendCodePoint(codePoint);
        super.setStatusIsExecute();
    }
}
