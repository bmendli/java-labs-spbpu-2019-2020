package lab3.methods;

public class Replace extends AbstractMethodsOwnStringBuilder {

    private final int start;
    private final int end;
    private final String str;
    private String replaceStr;

    public Replace(StringBuilder stringBuilder, int start, int end, String str) {
        super(stringBuilder);
        if (start > end) {
            throw new IndexOutOfBoundsException("start > end");
        }
        this.start = start;
        this.end = end;
        this.str = str;
    }

    @Override
    public void execute() {
        super.execute();
        this.replaceStr = stringBuilder.substring(start, end);
        stringBuilder.replace(start, end, str);
        super.setStatusIsExecute();
    }

    @Override
    public void undo() {
        super.undo();
        stringBuilder.replace(start, start + str.length(), replaceStr);
        super.setStatusIsUnExecute();
    }
}
