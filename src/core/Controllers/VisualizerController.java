package core.Controllers;

import core.Algorithms.Sorting.BubbleSort;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class VisualizerController implements Initializable {

        @FXML
        private AnchorPane graph;
        @FXML
        private AnchorPane sortGraph;
        @FXML
        private AnchorPane grid;
        @FXML
        private Slider scrollBar;
        @FXML
        private Label numLabel;
        @FXML
        private TextField numBars;
        @FXML
        private ComboBox comboBox;
        @FXML
        private TextArea textArea;
        @FXML
        private TabPane tabPane;
        @FXML
        private Tab graphTab;
        @FXML
        private Tab sortTab;
        @FXML
        private Tab gridTab;
        @FXML
        private VBox sideVBox;
        @FXML
        private ComboBox leftDropDown;
        @FXML
        private CheckBox allowHorizontals;

        public TextField numColumns;
        private BubbleSort bsort;
        private Bar[] bars;
        private int rightClickCount;
        private String selectedSort;
        private String selectedGridSort;
        private boolean isGridActive;
        private ObservableMap<String, String> observableMap;
        private ObservableMap<String, String> observableGridMap;
        private int cols, rows;

        /**
         * Clear all anchor panes
         * @param event
         */
        public void handleClear(ActionEvent event) {
            if (!this.graph.getChildren().isEmpty()) {
                this.graph.getChildren().clear();
            }
            if (!this.sortGraph.getChildren().isEmpty()) {
                this.sortGraph.getChildren().clear();
            }
            if (!this.grid.getChildren().isEmpty()) {
                this.grid.getChildren().clear();
            }
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
                        this.bsort = new BubbleSort(this.bars, this.sortGraph);
                        this.bsort.sort();
                        break;
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
            this.observableMap.put("Bubble Sort", "Bubble Sort has an average of O(n**2) time complexity");

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

