package semesters.third.lab3.methods;

import semesters.third.lab3.ExecuteException;

public abstract class AbstractMethodsOwnStringBuilder {

    protected StringBuilder stringBuilder;
    private boolean isExecute;

    AbstractMethodsOwnStringBuilder(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            throw new NullPointerException("string builder is null");
        }
        this.stringBuilder = stringBuilder;
        isExecute = false;
    }

    public void execute() throws ExecuteException {
        if (isExecute) {
            throw new ExecuteException("method is executed");
        }
    }

    public void undo() throws ExecuteException {
        if (!isExecute) {
            throw new ExecuteException("method not executed");
        }
    }

    protected void setStatusIsExecute() {
        isExecute = true;
    }

    void setStatusIsUnExecute() {
        isExecute = false;
    }
}
