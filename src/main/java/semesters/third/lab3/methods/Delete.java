package semesters.third.lab3.methods;

import semesters.third.lab3.ExecuteException;

public class Delete extends AbstractMethodsOwnStringBuilder {

    private final int start;
    private final int end;
    private String deletedStr;

    public Delete(StringBuilder stringBuilder, int start, int end) {
        super(stringBuilder);
        if (start > end) {
            throw new IndexOutOfBoundsException("start > end");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        deletedStr = stringBuilder.substring(start, end);
        if (start - end != 1) {
            stringBuilder.delete(start, end);
        } else {
            stringBuilder.deleteCharAt(start);
        }
        super.setStatusIsExecute();
    }

    @Override
    public void undo() throws ExecuteException {
        super.undo();
        stringBuilder.insert(start, deletedStr);
        super.setStatusIsUnExecute();
    }
}
