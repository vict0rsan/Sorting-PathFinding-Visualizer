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

    public HeapSort(Bar[] _rectArr, AnchorPane _grid) {
        this.transitions = new ArrayList<>();
        this.grid = _grid;
        this.barsArray = _rectArr;
        this.duration = Bar.getDuration();

    }

    public void sort() {
        SequentialTransition seqT = new SequentialTransition ();
        int n = this.barsArray.length;
        this.transitions.add(colorBar(findBranch(this.barsArray, this.barsArray.length), Color.RED, this.duration));

        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(this.barsArray, n, i);
        }
        this.transitions.add(colorBar(findBranch(this.barsArray, this.barsArray.length), Color.BLUE, this.duration));
        for (int i = this.barsArray.length-1; i >= 0; i--) {

            this.transitions.add(colorBar(this.barsArray, Color.RED, 0));
            this.transitions.add(swapBars(this.barsArray, 0, i, this.duration));
            this.transitions.add(colorBar(this.barsArray, Color.RED, this.duration, i));
            this.transitions.add(colorBar(findBranch(this.barsArray, i), Color.RED, this.duration));
            heapify(this.barsArray, i, 0);
            this.transitions.add(colorBar(findBranch(this.barsArray, i), Color.BLUE, this.duration));
        }
        seqT.getChildren().addAll(this.transitions);
        seqT.play();
    }

    /**
     * Heapify the subtree at node i.
     * @param _arr
     * @param _heapSize
     * @param root
     */
    private void heapify(Bar[] _arr, int _heapSize, int root) {
        int largest = root;
        int l = 2*root + 1;
        int r = 2*root + 2;

        // Checks if the left subtree is larger than the root
        if (r < _heapSize && _arr[l].getHeight() > _arr[largest].getHeight()) {
            largest = l;
        }

        // Checks if the right subtree is larger than the root
        if (r < _heapSize && _arr[r].getHeight() > _arr[largest].getHeight()) {
            largest = r;
        }

        // If the root is not the largest value
        if (largest != root) {
            this.transitions.add(swapBars(this.barsArray, largest, root, this.duration));
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
