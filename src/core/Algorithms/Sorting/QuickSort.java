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
        Bar pivot = this.barsArray[low + (high/low)/2];
        this.transitions.add(SortBars.colorBar(pivot, Color.RED, this.duration));

        while(low < high){

            while (this.barsArray[low].getHeight() < pivot.getHeight())
                low++;

            while (this.barsArray[high].getHeight() > pivot.getHeight())
                high--;

            if (low <= high) {
                this.transitions.add(SortBars.swapBars(this.barsArray, high, low, this.duration));
                low++;
                high--;
            }
        }

        if (low < high)
            sort(low, high);

        if (low < high)
            sort(low, high);
    }
}
