package lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lab5.FilePropertiesWorker;
import lab7.Alertable;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ResourceBundle;


public class FifthLabController implements Initializable, Alertable {

    public class KeyValue {
        private String key;
        private String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    @FXML
    public TableView<KeyValue> tableKeyValue;

    @FXML
    public TableColumn<String, String> keyColumn;

    @FXML
    public TableColumn<String, String> valueColumn;

    @FXML
    public TextField file;

    private FilePropertiesWorker filePropertiesWorker;

    public void onInputFileAction(ActionEvent actionEvent) {
        try {
            filePropertiesWorker.setFile(Paths.get(file.getText()));
            filePropertiesWorker.parse();
            for (Map.Entry<String, String> set : filePropertiesWorker.getProperties().entrySet()) {
                tableKeyValue.getItems().add(new KeyValue(set.getKey(), set.getValue()));
            }
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "FILE ERROR", "File not founded", "No such file");
        } finally {
            file.clear();
        }
    }

    public void onClearButtonClicked(ActionEvent actionEvent) {
        tableKeyValue.getItems().clear();
        file.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filePropertiesWorker = new FilePropertiesWorker();
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    @Override
    public void alert(Alert.AlertType alertType, String tittle, String header, String context) {
        Alert alert = new Alert(alertType);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
