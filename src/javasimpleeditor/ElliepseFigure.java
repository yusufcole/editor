/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.Graphics;
import java.io.Serializable;
/**
 *
 * @author yusufcole
 */
public class ElliepseFigure implements BaseFigure,Serializable{
    private int left;
    private int top;
    private int width;
    private int height;
    private int ID;
    private final String Type;
    
    public ElliepseFigure(int x, int y, int w, int h, int id){
        this.left = x;
        this.top = y;
        this.width = w + 30;
        this.height = h;
        this.Type = "Ellipse";
        this.ID = id;
    }
    @Override
    public void setID(int id) {
        this.ID =id;
    }
    @Override
    public int getFigureID() {
        return ID;
    }
    @Override
    public String getType(){
       return Type;
    }
    @Override
    public boolean containst(int x, int y){
       double ov_X =  width/2.0;
       double ov_Y = height/2.0;
       double center_toX = left + ov_X;
       double center_toY = top + ov_Y;
       return ((ov_Y*(x - center_toX))*(ov_Y* (x - center_toX)) + (ov_X* (y - center_toY))* (ov_X*(y - center_toY))) <= (ov_X*ov_X*ov_Y*ov_Y);
    }
    @Override
    public void draw(Graphics g) {
        //g.setColor(color);
       // g.fillOval(left, top, width, height);
        //g.setColor(Color.black);
        g.drawOval(left, top, width, height);
    }
    
    @Override
    public int getLeft(){
        return left;
    }
    @Override
    public void setLeft(int left){
        this.left = left;
    }
    @Override
    public int getTop(){
        return top;
    }
    @Override
    public void setTop(int top){
        this.top = top;
    }
    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public void setWith(int width){
        this.width = width;
    }
    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public void setHeigth(int height){
        this.height = height;
    }
    
    @Override
    public void accept(IVisitor v) {
        v.visite(this);
    }
    @Override
    public String toString(){
        return Type + " "+ left + " " + top + " " + width + " " + height ;
    }
}
