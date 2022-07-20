package core.Algorithms.Sorting;

import core.Algorithms.Utility.SortBars;
import core.Models.Bar;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class BubbleSort {

    private Bar[] barsArray;
    private AnchorPane grid;
    private ArrayList<Transition> transitions;
    private int duration;

    public BubbleSort(Bar [] barsArray, AnchorPane grid){
        this.barsArray = barsArray;
        this.grid = grid;
        this.duration = Bar.getDuration();
        this.transitions = new ArrayList<>();
        sort();
    }

    public synchronized void sort(){
        SequentialTransition sequentialTransition = new SequentialTransition();
        int barsNumber = this.barsArray.length;

        for(int i = 0; i < barsNumber; i++){
            boolean changed = false;
            System.out.println("Checking element i " + i);

            for(int j = 0; j < barsNumber-1; j++){
                System.out.println("Checking element j" + j);
                if(this.barsArray[j+1].getHeight() < this.barsArray[j].getHeight()){
                    this.transitions.add(SortBars.swapBars(this.barsArray, j, j+1, this.duration));
                    changed = true;
                }
            }
            if(!changed)
                break;
        }
        sequentialTransition.getChildren().addAll(this.transitions);
        sequentialTransition.play();
    }


}
