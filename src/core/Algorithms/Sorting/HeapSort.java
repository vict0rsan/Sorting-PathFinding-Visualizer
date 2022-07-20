package core.Algorithms.Sorting;

import core.Algorithms.Utility.SortBars;
import core.Models.Bar;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static core.Algorithms.Utility.SortBars.*;

public class HeapSort {

    private AnchorPane grid;
    private Bar[] barsArray;
    private ArrayList<Transition> transitions;
    private int duration;

    public HeapSort(Bar[] barsArray, AnchorPane grid) {
        this.transitions = new ArrayList<>();
        this.grid = grid;
        this.barsArray = barsArray;
        this.duration = Bar.getDuration();
        sort();
    }

    public void sort() {
        SequentialTransition sequentialTransition = new SequentialTransition ();
        int barsNumber = this.barsArray.length;
        this.transitions.add(colorBar(findBranch(this.barsArray, this.barsArray.length), Color.BLUE));

        for (int i = barsNumber/2 - 1; i >= 0; i--) {
            heapify(this.barsArray, barsNumber, i);
        }

        this.transitions.add(colorBar(findBranch(this.barsArray, this.barsArray.length), Color.RED));

        for (int i = barsNumber - 1; i >= 0; i--) {
            this.transitions.add(colorBar(this.barsArray, Color.BLUE, 0));
            this.transitions.add(swapBars(this.barsArray, 0, i, this.duration));
            this.transitions.add(colorBar(this.barsArray, Color.BLUE, this.duration, i));
            this.transitions.add(colorBar(findBranch(this.barsArray, i), Color.RED));
            heapify(this.barsArray, i, 0);
            this.transitions.add(colorBar(findBranch(this.barsArray, i), Color.RED));
        }

        sequentialTransition.getChildren().addAll(this.transitions);
        sequentialTransition.play();
    }

    /**
     * Heapify the subtree at node i.
     * @param barsArray
     * @param heapSize
     * @param root
     */
    private void heapify(Bar[] barsArray, int heapSize, int root) {
        int largest = root;
        int left = 2*root + 1;
        int right = 2*root + 2;

        // Checks if the left subtree is larger than the root
        if (right < heapSize && barsArray[left].getHeight() > barsArray[largest].getHeight())
            largest = left;

        // Checks if the right subtree is larger than the root
        if (right < heapSize && barsArray[right].getHeight() > barsArray[largest].getHeight())
            largest = right;

        // If the root is not the largest value
        if (largest != root) {
            this.transitions.add(swapBars(this.barsArray, largest, root, this.duration));
            heapify(barsArray, heapSize, largest);
        }
    }

    private ArrayList<Bar> findBranch(Bar[] barsArray, int a) {
        ArrayList<Bar> bars = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            bars.add(barsArray[i]);
        }
        return bars;
    }
}
