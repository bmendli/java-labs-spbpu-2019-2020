package semesters.third.lab3.methods;

import semesters.third.lab3.ExecuteException;

public class Append<E> extends AbstractAppend {

    private final E appendSequence;

    public Append(StringBuilder stringBuilder, E appendSequence) {
        super(stringBuilder, appendSequence.toString().length());
        this.appendSequence = appendSequence;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.append(appendSequence);
        super.setStatusIsExecute();
    }
}
