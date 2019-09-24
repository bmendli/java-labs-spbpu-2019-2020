package lab3.methods;

public class Reverse extends AbstractMethodsOwnStringBuilder {
    public Reverse(StringBuilder stringBuilder) {
        super(stringBuilder);
    }

    @Override
    public void execute() {
        super.execute();
        stringBuilder.reverse();
        super.setStatusIsExecute();
    }

    @Override
    public void undo() {
        super.undo();
        stringBuilder.reverse();
        super.setStatusIsUnExecute();
    }
}
