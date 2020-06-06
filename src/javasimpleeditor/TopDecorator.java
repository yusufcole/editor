/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author yusufcole
 */
public class TopDecorator extends FigureDecorator implements Serializable,ActionListener{

    private JTextField addtext;
    protected JTextArea textArea;
    private final static String newline = "\n";
    private String text = "rondje";
    public TopDecorator(BaseFigure fig) {
        super(fig);
        this.Type ="ornament";
        
    }
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        addtext = new JTextField(20);
       // addtext.getCaret();
        addtext.addActionListener(this);
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        //text = addtext.getText();
        //scan = new Scanner(System.in);
        //text = scan.next();
        //addtext.setText(text);
        g.drawString(text,decoratedFigure.getLeft() + (decoratedFigure.getWidth() - 1)/ 2,
                decoratedFigure.getTop() - 2);

//        g.drawRect(decoratedFigure.getLeft() + (decoratedFigure.getWidth() - 1)/ 2,
//                decoratedFigure.getTop() - 2, 10, 10);//x + (w - 1)/ 2,y - 2
        decoratedFigure.draw(g);
        setAtTop(decoratedFigure);
    }
    public String getText(){
        return text;
    }
    public void setText(JTextField t){
        addtext = t;
    }
    @Override
    public String getType(){
        return Type;
    }
    private String setAtTop(BaseFigure fig){
        return this.Type+" "+fig.getClass().getSimpleName()+"\t"+ text;
    }

    @Override
    public void accept(IVisitor v) {
        decoratedFigure.accept(v);
    }

    @Override
    public boolean containst(int x, int y) {
       return decoratedFigure.containst(x, y);
    }

    @Override
    public int getFigureID() {
        return decoratedFigure.getFigureID();
    }

    @Override
    public void setID(int id) {
        id = decoratedFigure.getFigureID();
    }

    @Override
    public int getLeft() {
        return decoratedFigure.getLeft();
    }

    @Override
    public void setLeft(int left) {
        left = decoratedFigure.getLeft();
    }

    @Override
    public int getTop() {
        return decoratedFigure.getTop();
    }

    @Override
    public void setTop(int top) {
        top = decoratedFigure.getTop();
    }

    @Override
    public int getWidth() {
        return decoratedFigure.getWidth();
    }

    @Override
    public void setWith(int width) {
        width = 10;
    }

    @Override
    public int getHeight() {
        return decoratedFigure.getHeight();
    }

    @Override
    public void setHeigth(int height) {
        height = 10;
    }
        @Override
    public String toString(){
        return setAtTop(decoratedFigure);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        text = addtext.getText();
        textArea.append(text + newline);
        addtext.selectAll();
 
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
