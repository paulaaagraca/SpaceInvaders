package sample.tsi.model;


import javafx.scene.image.Image;

public class Rocket extends Entity{
    public double x;
    public double y;


    public Rocket(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getx(){
        return x; }
    public double gety(){
        return y; }

    public void setx(double x){
        this.x=x; }
    public void sety(double y){
        this.y=y; }
}