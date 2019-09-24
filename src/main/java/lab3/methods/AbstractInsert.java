package lab3.methods;

public class AbstractInsert extends AbstractMethodsOwnStringBuilder {

    protected final int offset;
    protected final int len;

    protected AbstractInsert(StringBuilder stringBuilder, int offset, int len) {
        super(stringBuilder);
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
