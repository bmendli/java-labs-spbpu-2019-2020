package semesters.third.lab3.methods;

import semesters.third.lab3.ExecuteException;

public class Insert<E> extends AbstractInsert {

    private final E insertSequence;

    public Insert(StringBuilder stringBuilder, int offset, E insertSequence) {
        super(stringBuilder, offset, insertSequence.toString().length());
        this.insertSequence = insertSequence;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.insert(offset, insertSequence);
        super.setStatusIsExecute();
    }
}
