package lab3.methods;

public class AppendCharArray extends AbstractAppend {

    private final char[] str;
    private final int offset;
    private final int len;

    public AppendCharArray(StringBuilder stringBuilder, char[] str, int offset, int len) {
        super(stringBuilder, len);
        this.str = str;
        this.len = len;
        this.offset = offset;
    }

    @Override
    public void execute() {
        super.execute();
        stringBuilder.append(str, offset, len);
        super.setStatusIsExecute();
    }
}
