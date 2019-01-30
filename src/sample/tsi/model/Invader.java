package sample.tsi.model;


import javafx.scene.image.Image;

public class Invader extends Entity{
    public int type;
    public double x;
    public double y;
    public double w;
    public double h;

    public Invader(int type, double x, double y, double w, double h){
        this.type = type;
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }

    public double getx(){
        return x; }
    public double gety(){
        return y; }
    public double getw(){
        return w; }
    public double geth(){
        return h; }

    public void setx(double x){
        this.x=x; }
    public void sety(double y){
        this.y=y; }
    public void setw(double w){
        this.w=w; }
    public void seth(double h){
        this.h=h; }

}