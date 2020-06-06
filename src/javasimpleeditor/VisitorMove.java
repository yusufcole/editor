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
class VisitorMove implements IVisitor{
    private final int x;
    private final int y;
    public VisitorMove(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void visite(ElliepseFigure o) {
        o.setLeft(x);
        o.setTop(y);
    }

    @Override
    public void visite(RectangleFigure r) {
        r.setLeft(x);
        r.setTop(y);
    }

    @Override
    public void visite(GroupFigure g) {
        g.setLeft(x);
        g.setTop(y);
    }
    
}
