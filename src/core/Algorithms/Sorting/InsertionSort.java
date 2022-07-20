package core.Algorithms.Sorting;

import core.Models.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

import static core.Algorithms.Utility.SortBars.swapBars;

public class InsertionSort {

    private AnchorPane grid;
    private int numBars;
    private Bar[] barsArray;
    private ArrayList<Transition> transitions;
    private int duration;

    public InsertionSort(Bar[] barsArray, AnchorPane grid) {
        this.grid = grid;
        this.duration = Bar.getDuration();
        this.barsArray = barsArray;
        this.transitions = new ArrayList<>();
        sort();
    }

    private void sort() {
        SequentialTransition sequentialTransition = new SequentialTransition ();
        int barsNumber = this.barsArray.length;

        for (int i = 0; i < barsNumber; i++) {
            Bar key = this.barsArray[i];
            int j = i - 1;

            while (j >= 0 && this.barsArray[j].getHeight() > key.getHeight()) {
                this.transitions.add(swapBars(this.barsArray, j, j+1, this.duration));
                j -= 1;
            }

            this.transitions.add(swapBars(this.barsArray, j+1, i, this.duration));
        }

        sequentialTransition.getChildren().addAll(this.transitions);
        sequentialTransition.play();
    }

}
