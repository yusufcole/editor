/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author yusufcole
 */
 public class GroupFigure implements BaseFigure,Serializable{ 
    private int left;
    private int top;
    private int width;
    private int height;
    private int ID;
    private final String Type;
    private final LinkedList<BaseFigure> group;
    public GroupFigure(int x, int y, int w, int h, int id) {
        this.left = x;
        this.top = y;
        this.width = w + 80;
        this.height = h + 80;
        this.ID = id;
        Type = "group";
        group = new LinkedList<>();
    } 

    /**
     *
     * @param id
     */
    @Override
    public void setID(int id) {
        this.ID =id;
    }

    /**
     *
     * @return
     */
    @Override
    public int getFigureID() {
        return ID;
    }

    /**
     *
     * @return
     */
    @Override
    public String getType(){
       return Type;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLeft(){
        return left;
    }

    /**
     *
     * @param left
     */
    @Override
    public void setLeft(int left){
        this.left = left;
    }

    /**
     *
     * @return
     */
    @Override
    public int getTop(){
        return top;
    }

    /**
     *
     * @param top
     */
    @Override
    public void setTop(int top){
        this.top = top;
    }

    /**
     *
     * @return
     */
    @Override
    public int getWidth(){
        return width;
    }

    /**
     *
     * set Figure Width
     * @param width
     */
    @Override
    public void setWith(int width){
        this.width = width;
    }

    /**
     *
     * @return height
     */
    @Override
    public int getHeight(){
        return height;
    }

    /**
     *
     * @param height
     */
    @Override
    public void setHeigth(int height){
        this.height = height;
    }
    
    /**
     *
     * add group to list
     * add Figure to list
     * @param shapes
     */
    public void addToGroup(BaseFigure shapes){
        GroupFigure GroupShapes;
        if(!"group".equals(shapes.getType())){
            group.addLast(shapes);
        }else{
            GroupShapes = (GroupFigure)shapes;
            group.addLast(GroupShapes);
            System.out.println("GroupFigure.addToGroup()!!!" + GroupShapes.group.size());
            System.out.println("javasimpleeditor.GroupFigure.addToGroup()" + group.size());
        }
    //group.addLast(shapes);
        System.out.println("GroupFigure.addToGroup()!!!" + shapes);
    }
    
    /**
     *delete shape from list
     * @param shapes
     */
    public void removeFromGroup(BaseFigure shapes){
        GroupFigure GroupShapes;
        if("group".equals(shapes.getType())){
            GroupShapes = (GroupFigure)shapes;
            GroupShapes.group.forEach((BaseFigure figure)->{
                boolean remove = GroupShapes.group.remove(figure);
            });
        }else{
            boolean remove = group.remove(shapes);
        }
    }
    
    /**
     *
     * @return group 
     */
    public LinkedList<BaseFigure> getGroup(){
        return group;
    }
    
    /**
     *
     * @param index
     * @return  shape at index in list
     */
    public BaseFigure getShape(int index){
        GroupFigure groupfig;
        for(BaseFigure baseFigure : group) {
            if(!baseFigure.getType().equals("group")){
                return group.get(index);
            }else{
                groupfig = (GroupFigure)baseFigure;
                return groupfig.getShape(index);
            }
        }
        return group.get(index);
    }
    
    /**
     *
     * @param g
     * g for group
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(left, top, width, height);
    }
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean containst(int x, int y){
       double ov_X =  width/2.0;
       double ov_Y = height/2.0;
       double center_toX = left + ov_X;
       double center_toY = top + ov_Y;
       return ((ov_Y*(x - center_toX))*(ov_Y* (x - center_toX)) + (ov_X* (y - center_toY))* (ov_X*(y - center_toY))) <= (ov_X*ov_X*ov_Y*ov_Y);
    }
     
    @Override
    public String toString(){
        return Type + " " + group.size();
    }

    @Override
    public void accept(IVisitor v) {
        group.forEach((baseFigure) -> {
            baseFigure.accept(v);
        });
        v.visite(this);
    }
    
}
