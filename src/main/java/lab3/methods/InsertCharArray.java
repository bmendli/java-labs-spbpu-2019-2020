package lab3.methods;

import lab3.ExecuteException;

public class InsertCharArray extends AbstractInsert {

    private final int index;
    private final char[] str;

    public InsertCharArray(StringBuilder stringBuilder, int index, char[] str, int offset, int len) {
        super(stringBuilder, offset, len);
        this.index = index;
        this.str = str;
    }

    @Override
    public void execute() throws ExecuteException {
        super.execute();
        stringBuilder.insert(index, str, offset, len);
        super.setStatusIsExecute();
    }
}
