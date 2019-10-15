package lab7;

import javafx.scene.control.Alert;

public interface Alertable {

    public void alert(Alert.AlertType alertType, String tittle, String header, String context);
}
