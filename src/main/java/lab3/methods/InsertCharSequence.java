package lab3.methods;

import lab3.ExecuteException;

public class InsertCharSequence extends AbstractInsert {

    private final CharSequence charSequence;
    private final int start;
    private final int end;

    public InsertCharSequence(StringBuilder stringBuilder, int dstOffset, CharSequence s, int start, int end) {
        super(stringBuilder, dstOffset, end - start);
        if (start > end) {
            throw new IndexOutOfBoundsException("start > end");
        }
        this.charSequence = s;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.insert(offset, charSequence, start, end);
        super.setStatusIsExecute();
    }
}
