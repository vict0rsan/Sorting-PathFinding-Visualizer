package core.Models;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bar extends Rectangle {

    private static int duration = 600;
    private int currentDuration;

    private double xPos;
    private double yPos;
    private double softX;
    private double softY;
    private double width;
    private double height;
    private double dragX;
    private double dragY;
    private Color color;

    public Bar(){

    }

    public Bar(Double xPos, Double yPos,  Double softX, Double softY, Double width, Double height, Color color) {
        super(xPos, yPos, width, height);
        this.xPos = xPos;
        this.yPos = yPos;
        this.softX = softX;
        this.softY = softY;
        this.width = width;
        this.height = height;
        this.dragX = 0;
        this.dragY = 0;
        this.color = color;
        this.currentDuration = 100;
    }

    public TranslateTransition setToX(Double x){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(this);
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.setByX(x);
        return translateTransition;
    }

    public static ParallelTransition colorRect(Color color, Bar bar) {
        ParallelTransition parallelTransition = new ParallelTransition();
        FillTransition fill = new FillTransition();
        fill.setShape(bar);
        fill.setDuration(Duration.millis(duration));
        fill.setToValue(color);
        parallelTransition.getChildren().add(fill);
        return parallelTransition;
    }

    public static ParallelTransition colorRect(Color _color, Bar[] bars) {
        ParallelTransition parallelTransition = new ParallelTransition();
        for (int i = 0; i < bars.length; i++) {
            FillTransition fill = new FillTransition();
            fill.setShape(bars[i]);
            fill.setDuration(Duration.millis(duration));
            fill.setToValue(_color);
            parallelTransition.getChildren().add(fill);
        }
        return parallelTransition;
    }

    public static int getDuration() {
        return duration;
    }

    public static void setDuration(int duration) {
        Bar.duration = duration;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getSoftX() {
        return softX;
    }

    public void setSoftX(double softX) {
        this.softX = softX;
    }

    public double getSoftY() {
        return softY;
    }

    public void setSoftY(double softY) {
        this.softY = softY;
    }

    public double getDragX() {
        return dragX;
    }

    public void setDragX(double dragX) {
        this.dragX = dragX;
    }

    public double getDragY() {
        return dragY;
    }

    public void setDragY(double dragY) {
        this.dragY = dragY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setFill(color);
    }
}
