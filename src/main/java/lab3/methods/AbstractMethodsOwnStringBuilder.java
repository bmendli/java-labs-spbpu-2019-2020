package lab3.methods;

import lab3.ExecuteException;

import javax.validation.constraints.NotNull;

public abstract class AbstractMethodsOwnStringBuilder {

    protected StringBuilder stringBuilder;
    private boolean isExecute;

    protected AbstractMethodsOwnStringBuilder(@NotNull StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        isExecute = false;
    }

    public void execute() {
        if (isExecute) {
            throw new ExecuteException("method is executed");
        }
    }

    public void undo() {
        if (!isExecute) {
            throw new ExecuteException("method not executed");
        }
    }

    protected void setStatusIsExecute() {
        isExecute = true;
    }

    protected void setStatusIsUnExecute() {
        isExecute = false;
    }
}
