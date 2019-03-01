package ui;

import core.Function;
import core.Integral;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.concurrent.CountDownLatch;

public class Controller {
    @FXML
    private Label resultId;
    @FXML
    private TextField txtFieldId;
    @FXML
    private TextField aFieldId;
    @FXML
    private TextField bFieldId;
    @FXML
    private Label labelWaitId;

    private double returnValue;
    private Integral integral;
    private Function function;
    public int a;
    public int b;

    public Controller(){
        super();
    }


    @FXML
    public void setResult() throws UnparsableExpressionException, UnknownFunctionException {
        function = new Function(txtFieldId.getText().trim());
        integral = new Integral();
        a = Integer.parseInt(aFieldId.getText().trim());
        b = Integer.parseInt(bFieldId.getText().trim());
        function.setA(a);
        function.setB(b);
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Background work
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                returnValue = integral.IntegralReturnValue(a,b,function);
                                labelWaitId.setText("Wait while we calculate your expression");
                                labelWaitId.setVisible(true);
                            } catch (UnparsableExpressionException | UnknownFunctionException e) {
                                e.printStackTrace();
                            } finally {
                                labelWaitId.setText("");
                                labelWaitId.setVisible(false);
                                resultId.setText(String.valueOf(returnValue));
                            }
                        });
                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        service.start();
    }
    @FXML
    public void pythonPlot(){
        function.runPython();
    }
}