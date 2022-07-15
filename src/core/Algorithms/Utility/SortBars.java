package core.Algorithms.Utility;

import core.Models.Bar;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;

public class SortBars {

    private static double SPACING;
    private static final int BARS_NUMBER = 35;
    private static final int BASE_HEIGHT = 10;
    private static int RECT_WIDTH = 3;

    private ObservableList<Bar> bars;
    private Bar [] barsArray;
    private AnchorPane grid;
    private int duration;
    private int numBars;

    public SortBars(int numBars, AnchorPane grid) {
        this.bars = FXCollections.observableArrayList();
        this.barsArray = new Bar[numBars];
        this.grid = grid;
        SPACING = this.grid.getWidth() / numBars;
        this.duration = Bar.getDuration();
        createBaseLine(this.grid);

        for (int i = 0; i < numBars; i++) {
            Bar currentBar = createBar(i, Color.BLACK);
            this.bars.add(currentBar);
            this.barsArray[i] = currentBar;
        }
    }

    public SortBars(AnchorPane grid){
        this.bars = FXCollections.observableArrayList();
        this.barsArray = new Bar[BARS_NUMBER];
        this.grid = grid;
        this.duration = Bar.getDuration();
        createBaseLine(this.grid);
        SPACING = (this.grid.getWidth() / 50) + 5;

        for (int i = 0; i < BARS_NUMBER; i++) {
            Bar currentBar = createBar(i, Color.BLACK);
            this.bars.add(currentBar);
            this.barsArray[i] = currentBar;
        }

    }

    private Bar createBar(int num, Color color){
        Bar bar = new Bar();
        bar.setX((SPACING * num) + SPACING);
        bar.setY(BASE_HEIGHT);
        bar.setHeight((Math.random()*(this.grid.getHeight()-20))+BASE_HEIGHT);
        RECT_WIDTH = (int) (SPACING-1);
        bar.setWidth(RECT_WIDTH);
        bar.setFill(color);
        return bar;
    }

    public static void createBaseLine(AnchorPane grid){
        Bar baseLine = new Bar();
        baseLine.setX(0.0);
        baseLine.setY(0.0);
        baseLine.setWidth(grid.getWidth());
        baseLine.setHeight(BASE_HEIGHT);
        grid.getChildren().add(baseLine);
    }

    /**
     * Swap locations of elements in list at indices i and j and return the transition.
     * @param list
     * @param i
     * @param j
     * @return
     */
    public static ParallelTransition swapBars(Bar[] list, int i, int j, int duration) {
        // Add all transitions together.
        ParallelTransition parallelTransition = new ParallelTransition();

        // The distance to move a rectangle is calculated from the distance between their indices.
        int move = j - i;

        // Add the transitions for both rectangles
        parallelTransition.getChildren().addAll(list[i].setToX(move * SPACING), list[j].setToX(-(SPACING * move)));

        // Swap their locations in the array
        Bar tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;

        // Update the color once they have been visited
        parallelTransition.getChildren().addAll(colorRect(list[i], Color.BLUE, duration), colorRect(list[j], Color.BLUE, duration));
        return parallelTransition;
    }

    public static ParallelTransition colorRect(List<Bar> list, Color color) {
        ParallelTransition parallelTransition = new ParallelTransition();
        for (Bar bar : list) {
            FillTransition fillTransition = new FillTransition();
            fillTransition.setShape(bar);
            fillTransition.setToValue(color);
            fillTransition.setDuration(Duration.millis(bar.getDuration()));
            parallelTransition.getChildren().add(fillTransition);
        }
        return parallelTransition;
    }

    public static ParallelTransition colorRect(Bar[] list, Color color, int duration, int...k) {
        ParallelTransition parallelTransition = new ParallelTransition();
        for (int i = 0; i < k.length; i++) {
            FillTransition fillTransition = new FillTransition();
            fillTransition.setShape(list[k[i]]);
            fillTransition.setToValue(color);
            fillTransition.setDuration(Duration.millis(duration)); // 100
            parallelTransition.getChildren().add(fillTransition);
        }
        return parallelTransition;
    }

    public static ParallelTransition colorRect(Bar bar, Color color, int duration) {
        ParallelTransition parallelTransition = new ParallelTransition();
        FillTransition fill = new FillTransition();
        fill.setShape(bar);
        fill.setToValue(color);
        fill.setDuration(Duration.millis(duration));
        parallelTransition.getChildren().add(fill);

        return parallelTransition;
    }

    private void draggable(Bar bar){
        bar.setOnMousePressed(m -> {
            double dragX = m.getSceneX();
            double dragY = m.getSceneY();
            Bar draggedBar = (Bar) (m.getSource());
            bar.toFront();;
        });
    }

    public Bar[] getRectArr() {
        return this.barsArray;
    }

    public ObservableList<Bar> getRectangles() {
        return this.bars;
    }
}
