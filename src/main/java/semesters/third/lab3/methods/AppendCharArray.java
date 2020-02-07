package semesters.third.lab3.methods;

import semesters.third.lab3.ExecuteException;

public class AppendCharArray extends AbstractAppend {

    private final char[] str;
    private final int offset;
    private final int len;

    public AppendCharArray(StringBuilder stringBuilder, char[] str, int offset, int len) {
        super(stringBuilder, len);
        this.str = str;
        this.len = len;
        this.offset = offset;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.append(str, offset, len);
        super.setStatusIsExecute();
    }
}
