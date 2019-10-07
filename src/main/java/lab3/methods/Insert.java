package lab3.methods;

public class Insert<E> extends AbstractInsert {

    private final E insertSequence;

    public Insert(StringBuilder stringBuilder, int offset, E insertSequence) {
        super(stringBuilder, offset, insertSequence.toString().length());
        this.insertSequence = insertSequence;
    }

    @Override
    public void execute() {
        super.execute();
        stringBuilder.insert(offset, insertSequence);
        super.setStatusIsExecute();
    }
}
