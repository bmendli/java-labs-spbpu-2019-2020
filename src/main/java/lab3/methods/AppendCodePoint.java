package lab3.methods;

import lab3.ExecuteException;

public class AppendCodePoint extends AbstractAppend {

    private final int codePoint;

    public AppendCodePoint(StringBuilder stringBuilder, int codePoint) {
        super(stringBuilder, 1);
        this.codePoint = codePoint;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.appendCodePoint(codePoint);
        super.setStatusIsExecute();
    }
}
