package lab3.methods;

import lab3.ExecuteException;

public abstract class AbstractAppend extends AbstractMethodsOwnStringBuilder {

    private final int sequenceLength;

    AbstractAppend(StringBuilder stringBuilder, int sequenceLength) {
        super(stringBuilder);
        if (sequenceLength < 0) {
            throw new IllegalArgumentException("sequence length < 0");
        }
        this.sequenceLength = sequenceLength;
    }

    @Override
    public void undo() throws ExecuteException {
        super.undo();
        stringBuilder.delete(
            stringBuilder.length() - sequenceLength,
            stringBuilder.length()
        );
        super.setStatusIsUnExecute();
    }
}
