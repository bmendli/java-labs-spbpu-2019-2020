package lab3.methods;

public class Append<E> extends AbstractAppend {

    private final E appendSequence;

    public Append(StringBuilder stringBuilder, E appendSequence) {
        super(stringBuilder, appendSequence.toString().length());
        this.appendSequence = appendSequence;
    }

    @Override
    public void execute() {
        super.execute();
        stringBuilder.append(appendSequence);
        super.setStatusIsExecute();
    }
}
