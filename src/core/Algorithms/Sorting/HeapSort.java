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
    private Bar[] rectArr;
    private ArrayList<Transition> transitions;
    private ArrayList<Transition> fadeTransitions;
    private int duration;

    public HeapSort(Bar[] _rectArr, AnchorPane _grid) {
        this.transitions = new ArrayList<>();
        this.grid = _grid;
//        this.numBars = _numBars;
        this.rectArr = _rectArr;
        this.duration = Bar.getDuration();
    }

    public void sort() {
        SequentialTransition seqT = new SequentialTransition ();
        int n = this.rectArr.length;
        this.transitions.add(colorBar(findBranch(this.rectArr, this.rectArr.length), Color.RED, this.duration));

        for (int i = n/2-1; i >= 0; i--) {
            heapify(this.rectArr, n, i);
        }
        this.transitions.add(colorBar(findBranch(this.rectArr, this.rectArr.length), Color.BLUE, this.duration));
        for (int i = this.rectArr.length-1; i >= 0; i--) {

            this.transitions.add(colorBar(this.rectArr, Color.RED, 0));
            this.transitions.add(swapBars(this.rectArr, 0, i, this.duration));
            this.transitions.add(colorBar(this.rectArr, Color.RED, this.duration, i));
            this.transitions.add(colorBar(findBranch(this.rectArr, i), Color.RED, this.duration));
            heapify(this.rectArr, i, 0);
            this.transitions.add(colorBar(findBranch(this.rectArr, i), Color.BLUE, this.duration));
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
        if (r < _heapSize && _arr[l].getHeight() > _arr[largest].getHeight()) {
            largest = l;
        }

        // Checks if the right subtree is larger than the root
        if (r < _heapSize && _arr[r].getHeight() > _arr[largest].getHeight()) {
            largest = r;
        }

        // If the root is not the largest value
        if (largest != _root) {
            this.transitions.add(swapBars(this.rectArr, largest, _root, this.duration));
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
