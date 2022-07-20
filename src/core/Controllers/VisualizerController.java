package core.Controllers;

import core.Algorithms.Sorting.BubbleSort;
import core.Algorithms.Sorting.HeapSort;
import core.Algorithms.Sorting.InsertionSort;
import core.Algorithms.Sorting.QuickSort;
import core.Algorithms.Utility.SortBars;
import core.Models.Bar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class VisualizerController implements Initializable {

        @FXML
        private AnchorPane sortGraph;

        @FXML
        private Slider scrollBar;

        @FXML
        private TextField numBars;
        @FXML
        private ComboBox comboBox;
        @FXML
        private TextArea textArea;

        @FXML
        private ComboBox leftDropDown;


        public TextField numColumns;
        private BubbleSort bubbleSort;
        private QuickSort quickSort;
        private HeapSort heapSort;
        private InsertionSort insertionSort;
        private Bar[] bars;
        private String selectedSort;
        private String selectedGridSort;
        private ObservableMap<String, String> observableMap;
        private ObservableMap<String, String> observableGridMap;
        private int cols, rows;

        /**
         * Clear all anchor panes
         * @param event
         */
        public void handleClear(ActionEvent event) {
           System.out.println("Clearing stuff");
        }

        /**
         * Handle sort button for sort graph. Uses sorting algorithms.
         * @param event
         */
        public void handleSort(ActionEvent event) {
            if (null != this.selectedSort) {
                if (null == this.bars) {
                    this.bars = new SortBars(this.sortGraph).getRectArr();
                }
                switch (this.selectedSort) {
                    case "Bubble Sort":
                        this.textArea.setText(this.observableMap.get("Bubble Sort"));
                        this.bubbleSort = new BubbleSort(this.bars, this.sortGraph);
                        break;
                    case "Quick Sort":
                        this.textArea.setText(this.observableMap.get("Quick Sort"));
                        this.quickSort = new QuickSort(this.bars, this.sortGraph);
                        break;
                    case "Heap Sort":
                        this.textArea.setText(this.observableMap.get("Heap Sort"));
                        this.heapSort = new HeapSort(this.bars, this.sortGraph);
                    case "Insertion Sort":
                        this.textArea.setText(this.observableMap.get("Insertion Sort"));
                        this.heapSort = new HeapSort(this.bars, this.sortGraph);
                    default:
                        System.out.println("No sorting algorithm selected");
                        break;
                }
            }
        }

        /**
         * Create bars used in sorting algorithms.
         * @param event
         */
        public void handleMakeBars(ActionEvent event) {
            makeBars();
        }

        private void makeBars() {
            if (!this.sortGraph.getChildren().isEmpty()) {
                this.sortGraph.getChildren().clear();
            }
            if (!this.numBars.getText().isEmpty()) {
                this.bars = new SortBars(Integer.parseInt(this.numBars.getText()), this.sortGraph).getRectArr();
                this.sortGraph.getChildren().addAll(this.bars);
            } else {
                this.bars = new SortBars(this.sortGraph).getRectArr();
                this.sortGraph.getChildren().addAll(this.bars);
            }
        }


        public void handleComboBox(ActionEvent event) {
            System.out.println("cbox");
        }

        private void comboAction() {
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedSort = comboBox.getValue().toString();
                    System.out.println(selectedSort);
                }
            };
            EventHandler<ActionEvent> gridEvent = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedGridSort = leftDropDown.getValue().toString();
                    System.out.println(selectedGridSort);
//                makeGrid();
                }
            };
            this.comboBox.setOnAction(event);
            this.leftDropDown.setOnAction(gridEvent);
        }

        private void fillDescriptionMap() {
            this.observableMap = FXCollections.observableHashMap();
            this.observableMap.put("Bubble Sort", "Bubble Sort has an average of O(n^2) time complexity");
            this.observableMap.put("Quick Sort", "Quick Sort has average time complexity of O(n*log(n))");
            this.observableMap.put("Heap Sort", "Heap Sort has an overall time complexity of O(n*log(n). Heapify is O(log(n)) and build heap is O(n)");
            this.observableMap.put("Insertion Sort", "Insertion sort has an average of O(n^2) time complexity");

        }

    private void scrollListener() {
        this.scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                if (null != bars) {
                    if (new_val.intValue() < 50) {
                        Bar.setDuration(500 / (new_val.intValue()+1));

                    } else {
                        Bar.setDuration(100 / new_val.intValue());

                    }
                    System.out.println(Bar.getDuration());
                }
            }
        });
        if (Objects.nonNull(bars)) {
            for (Bar bar : this.bars) {
                bar.setDuration(Bar.getDuration());
            }
        }
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            this.scrollBar.setBlockIncrement(10);
            this.cols = 0;
            scrollListener();
            fillDescriptionMap();
            this.comboBox.getItems().addAll(this.observableMap.keySet());
            //this.leftDropDown.getItems().addAll(this.observableGridMap.keySet());
            comboAction();
        }
}

