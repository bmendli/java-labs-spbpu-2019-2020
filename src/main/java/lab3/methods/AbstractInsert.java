package lab3.methods;

import lab3.ExecuteException;

public abstract class AbstractInsert extends AbstractMethodsOwnStringBuilder {

    final int offset;
    final int len;

    AbstractInsert(StringBuilder stringBuilder, int offset, int len) {
        super(stringBuilder);
        if (offset < 0 || len < 0) {
            throw new IllegalArgumentException("offset or len < 0");
        }
        this.offset = offset;
        this.len = len;
    }

    @Override
    public void undo() throws ExecuteException {
        super.undo();
        stringBuilder.delete(
            offset,
            offset + len
        );
        super.setStatusIsUnExecute();
    }
}
