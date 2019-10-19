package lab6;

import java.util.logging.Level;

public class OwnLogger {

    private StringBuilder stringBuilder;

    public OwnLogger(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public void log(Level level, String message) {
            stringBuilder.append(level.getName()).append(" : ").append(message);
    }
}
