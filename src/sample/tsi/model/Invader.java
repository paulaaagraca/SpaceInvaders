package sample.tsi.model;


import javafx.scene.image.Image;

public class Invader extends Entity{
    public int type;
    public double x;
    public double y;
    public Image image;

    public Invader(int type, double x, double y){
        this.type = type;
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
