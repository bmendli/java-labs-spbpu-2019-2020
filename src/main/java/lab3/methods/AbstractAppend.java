package lab3.methods;

public abstract class AbstractAppend extends AbstractMethodsOwnStringBuilder {

    protected final int sequenceLength;

    protected AbstractAppend(StringBuilder stringBuilder, int sequenceLength) {
        super(stringBuilder);
        if (sequenceLength < 0) {
            throw new IllegalArgumentException("sequence length < 0");
        }
        this.sequenceLength = sequenceLength;
    }

    @Override
    public void undo() {
        super.undo();
        stringBuilder.delete(
            stringBuilder.length() - sequenceLength,
            stringBuilder.length()
        );
        super.setStatusIsUnExecute();
    }
}
