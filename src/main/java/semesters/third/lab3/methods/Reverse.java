package semesters.third.lab3.methods;

import semesters.third.lab3.ExecuteException;

public class Reverse extends AbstractMethodsOwnStringBuilder {
    public Reverse(StringBuilder stringBuilder) {
        super(stringBuilder);
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.reverse();
        super.setStatusIsExecute();
    }

    @Override
    public void undo() throws ExecuteException {
        super.undo();
        stringBuilder.reverse();
        super.setStatusIsUnExecute();
    }
}
