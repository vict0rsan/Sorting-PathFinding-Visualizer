package core.Algorithms.Sorting;

import core.Algorithms.Utility.SortBars;
import core.Models.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static core.Algorithms.Utility.SortBars.colorBar;

public class CoctailSort {

    private Bar[] barsArray;
    private int duration;
    private AnchorPane grid;
    private ArrayList<Transition> transitions;

    public CoctailSort(Bar[] barsArray, AnchorPane grid) {
        this.barsArray = barsArray;
        this.duration = Bar.getDuration();
        this.transitions = new ArrayList<>();
        this.grid = grid;
        sort();
    }

    private void sort() {
        SequentialTransition sequentialTransition = new SequentialTransition ();
        boolean swapped = true;
        int start = 0;
        int end = this.barsArray.length;

        while (swapped) {
            swapped = false;
            for (int i = start; i < end-1; ++i) {
                this.transitions.add(colorBar(this.barsArray[i], Color.RED, this.duration));
                this.transitions.add(colorBar(this.barsArray[i+1], Color.RED, this.duration));
                if (this.barsArray[i].getHeight() > this.barsArray[i+1].getHeight()) {
                    this.transitions.add(SortBars.swapBars(this.barsArray, i, i+1, this.duration));
                    swapped = true;
                }
                this.transitions.add(colorBar(this.barsArray[i], Color.BLUE, this.duration));
                this.transitions.add(colorBar(this.barsArray[i+1], Color.BLUE, this.duration));
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            end = end-1;

            for (int i = end-1; i >= start; i--) {
                this.transitions.add(colorBar(this.barsArray[i], Color.RED, this.duration));
                this.transitions.add(colorBar(this.barsArray[i+1], Color.RED, this.duration));
                if (this.barsArray[i].getHeight() > this.barsArray[i+1].getHeight()) {
                    this.transitions.add(SortBars.swapBars(this.barsArray,i,  i+1, this.duration));
                    swapped = true;
                }
                this.transitions.add(colorBar(this.barsArray[i], Color.BLUE, this.duration));
                this.transitions.add(colorBar(this.barsArray[i+1], Color.BLUE, this.duration));
            }
            start += 1;
        }
        sequentialTransition.getChildren().addAll(this.transitions);
        sequentialTransition.play();
    }
}
