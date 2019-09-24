package lab3.methods;

public abstract class AbstractInsert extends AbstractMethodsOwnStringBuilder {

    protected final int offset;
    protected final int len;

    protected AbstractInsert(StringBuilder stringBuilder, int offset, int len) {
        super(stringBuilder);
        if (offset < 0 || len < 0) {
            throw new IllegalArgumentException("offset or len < 0");
        }
        this.offset = offset;
        this.len = len;
    }

    @Override
    public void undo() {
        super.undo();
        stringBuilder.delete(
            offset,
            offset + len
        );
        super.setStatusIsUnExecute();
    }
}
