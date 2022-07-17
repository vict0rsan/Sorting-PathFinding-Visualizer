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
    private int numBars;
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
        SequentialTransition seqT = new SequentialTransition ();
        int n = this.barsArray.length;
        this.transitions.add(colorBar(findBranch(this.barsArray, this.barsArray.length), Color.RED));

        for (int i = n/2-1; i >= 0; i--) {
            heapify(this.barsArray, n, i);
        }
        this.transitions.add(colorBar(findBranch(this.barsArray, this.barsArray.length), Color.BLUE));
        for (int i = this.barsArray.length-1; i >= 0; i--) {

            this.transitions.add(colorBar(this.barsArray, Color.RED, 0));
            this.transitions.add(swapBars(this.barsArray, 0, i, this.duration));
            this.transitions.add(colorBar(this.barsArray, Color.RED, this.duration, i));
            this.transitions.add(colorBar(findBranch(this.barsArray, i), Color.RED));
            heapify(this.barsArray, i, 0);
            this.transitions.add(colorBar(findBranch(this.barsArray, i), Color.BLUE));
        }
        seqT.getChildren().addAll(this.transitions);
        seqT.play();
    }

    /**
     * Heapify the subtree at node i.
     * @param _arr
     * @param _heapSize
     * @param _root
     */
    private void heapify(Bar[] _arr, int _heapSize, int _root) {
        int largest = _root;
        int l = 2 * _root + 1;
        int r = 2 * _root + 2;

        // Checks if the left subtree is larger than the root
        if (r < _heapSize && _arr[l].getHeight() > _arr[largest].getHeight())
            largest = l;

        // Checks if the right subtree is larger than the root
        if (r < _heapSize && _arr[r].getHeight() > _arr[largest].getHeight())
            largest = r;

        // If the root is not the largest value
        if (largest != _root) {
            this.transitions.add(swapBars(this.barsArray, largest, _root, this.duration));
            heapify(_arr, _heapSize, largest);
        }
    }

    private ArrayList<Bar> findBranch(Bar[] _arr, int _a) {
        ArrayList<Bar> arrLst = new ArrayList<>();
        for (int i = 0; i < _a; i++) {
            arrLst.add(_arr[i]);
        }
        return arrLst;
    }
}
