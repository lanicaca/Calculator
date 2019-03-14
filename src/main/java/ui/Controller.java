package ui;

import core.Axes;
import core.Function;
import core.Integral;
import core.Plot;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

public class Controller {

    private static final Logger log = Logger.getLogger(Controller.class);

    @FXML
    private Label resultId;
    @FXML
    private TextField txtFieldId;
    @FXML
    private TextField aFieldId;
    @FXML
    private TextField bFieldId;
    @FXML
    private Pane graphPaneId;

    private double returnValue;
    private Integral integral;
    private Function function;
    private int a;
    private int b;
    private Task<String> task;

    public Controller() {
        super();
    }


    @FXML
    private void setResult() throws Exception {
        log.info("in setResult");
        function = new Function(txtFieldId.getText().trim());
        a = Integer.parseInt(aFieldId.getText().trim());
        b = Integer.parseInt(bFieldId.getText().trim());
        integral = new Integral(function, a, b);
        log.info("Before service");

        task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                log.info("Run Later");
                returnValue = integral.IntegralReturnValue(function);
                return String.valueOf(returnValue);
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    @FXML
    public void graphPlot() {
        try {
            setResult();
        } catch (Exception e) {
            throwExceptionDialog("All fields are required");
            return;
        }
        task.setOnSucceeded(event -> {
            resultId.setText(task.getValue());
            double temp;
            if (Math.abs(a) > Math.abs(b)) {
                temp = Math.abs(a);
            } else {
                temp = Math.abs(b);
            }
            Axes axes = new Axes(
                    graphPaneId.getWidth(), graphPaneId.getHeight(),
                    -temp, temp, 1,
                    -temp, temp, 1
            );
            Plot plot = new Plot(integral);
            plot.draw(axes);
            graphPaneId.getChildren().clear();
            graphPaneId.setPadding(new Insets(20, 20, 20, 20));
            graphPaneId.getChildren().add(plot);

        });
        task.setOnFailed(e -> {
            throwExceptionDialog("Wrong expression entered");
        });
    }

    private void throwExceptionDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(message);
        alert.showAndWait();
    }
}