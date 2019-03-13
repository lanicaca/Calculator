package core;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Plot extends Pane {

    private Integral integral;

    public Plot(Integral integral) {
        this.integral = integral;
    }

    public void draw(Axes axes){
        Path path = new Path();
        path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(2);
        path.setFillRule(FillRule.EVEN_ODD);
        path.setClip(
                new Rectangle(
                        0, 0,
                        axes.getPrefWidth(),
                        axes.getPrefHeight()
                )
        );
        int index = 0;
        int length = integral.getFunction().getX_coords().size();
        System.out.println(length);
        double xMax = integral.getFunction().getX_coords().get(length - 1);
        double x = integral.getFunction().getX_coords().get(index);
        double y = integral.getFunction().getY_coords().get(index);
        boolean firstDotPlotted = false;


        while (x <= xMax && index < length - 1) {
            try {
                double mx = mapX(x, axes);
                double my = mapY(y, axes);

                if (firstDotPlotted) {
                    path.getElements().add(
                            new LineTo(
                                    mx, my
                            )
                    );
                } else {
                    path.getElements().add(
                            new MoveTo(
                                    mx, my
                            )
                    );
                    firstDotPlotted=true;
                }

            } catch (Exception e) {
                // not in domain of definition
            } finally {
                index++;
                x = integral.getFunction().getX_coords().get(index);
                y = integral.getFunction().getY_coords().get(index);
            }
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        getChildren().setAll(axes, path);
    }

    private double mapX(double x, Axes axes) {
        double tx = axes.getPrefWidth() / 2;
        double sx = axes.getPrefWidth()
                / (axes.getXAxis().getUpperBound()
                - axes.getXAxis().getLowerBound());

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight() / 2;
        double sy = axes.getPrefHeight()
                / (axes.getYAxis().getUpperBound()
                - axes.getYAxis().getLowerBound());
        return -y * sy + ty;
    }

}
