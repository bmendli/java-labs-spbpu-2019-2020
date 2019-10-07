package lab3.methods;

public class AppendCharSequence extends AbstractAppend {

    private final CharSequence charSequence;
    private final int start;
    private final int end;

    public AppendCharSequence(StringBuilder stringBuilder, CharSequence s, int start, int end) {
        super(stringBuilder, end - start);
        if (start > end) {
            throw new IndexOutOfBoundsException("start > end");
        }
        this.charSequence = s;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        super.execute();
        stringBuilder.append(charSequence, start, end);
        super.setStatusIsExecute();
    }
}
