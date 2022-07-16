package core.Algorithms.Sorting;

import core.Algorithms.Utility.SortBars;
import core.Models.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class QuickSort {

    private AnchorPane grid;
    private Bar[] barsArray;
    private ArrayList<Transition> transitions;
    private SequentialTransition sequentialTransition;
    private int duration;

    public QuickSort(Bar[] _rectArr, AnchorPane _grid) {
        this.grid = _grid;
        this.barsArray = _rectArr;
        this.duration = Bar.getDuration();
        this.sequentialTransition = new SequentialTransition();
        this.transitions = new ArrayList<>();


        if (this.barsArray == null || this.barsArray.length == 0)
            return;

        sort(0, this.barsArray.length-1);
        this.sequentialTransition.getChildren().addAll(this.transitions);
        this.sequentialTransition.play();
    }

    private void sort(int low, int high) {
        int bottom = low;
        int upper = high;

        Bar pivot = this.barsArray[low + (high-low)/2];
        this.transitions.add(SortBars.colorBar(pivot, Color.RED, this.duration));

        while(bottom < upper){

            while (this.barsArray[bottom].getHeight() < pivot.getHeight())
                bottom++;

            while (this.barsArray[upper].getHeight() > pivot.getHeight())
                upper--;

            if (bottom <= upper) {
                this.transitions.add(SortBars.swapBars(this.barsArray, upper, bottom, this.duration));
                bottom++;
                upper--;
            }
        }

        if (low < upper)
            sort(low, upper);

        if (bottom < high)
            sort(bottom, high);
    }
}
