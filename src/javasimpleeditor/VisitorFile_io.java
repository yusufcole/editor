/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

/**
 *
 * @author yusufcole
 */
class VisitorFile_io implements IVisitor{
    BaseFigure fig;
    int left,top,width,height, ID;
    String type;
    public VisitorFile_io(String type, int x, int y, int w, int h, int id){
        this.left = x;
        this.top = y;
        this.width = w;
        this.height = h;
        this.ID = id;
        this.type = type;
    }
    /**
     *
     * @param oval
     */
    @Override
    public void visite(ElliepseFigure oval) {
        oval.setLeft(left); //= left;
        oval.setTop(top);//= top;
        oval.setWith(width);// = width;
        oval.setHeigth(height);// = height;
        oval.getType();// = type;
    }

    /**
     *
     * @param rectangel
     */
    @Override
    public void visite(RectangleFigure rectangel) {
        rectangel.setLeft(left);
        rectangel.setTop(top);
        rectangel.setWith(width);
        rectangel.setHeigth(height);
        rectangel.getType();
    }

    /**
     *
     * @param group
     */
    @Override
    public void visite(GroupFigure group) {
        group.setLeft(left);
        group.setTop(top);
        group.setWith(width);
        group.setHeigth(height);
        group.getType();
        group.getGroup();
    }
    
}
