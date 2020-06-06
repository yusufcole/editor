/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpleeditor;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author yusufcole
 */
public class CommandHandler {
    
    private IMyCommand comand;
    private int pos, count;
    private LinkedList <IMyCommand> undocommands = new LinkedList<>();
    private LinkedList <IMyCommand>docommands = new LinkedList<>(); 
    @SuppressWarnings("NonPublicExported")
    public void addComand(IMyCommand command){
        this.comand = command;
        docommands.addLast(comand);
        command.execute();
        undocommands.clear();
        pos++;
        count++;
        System.out.println("add" + docommands + " " + pos + " " + count+ " " + undocommands);
    } 
    public boolean canundo(){
        return docommands.size() >= 0;
    }
    public void undo(){
        if(canundo()){
            try {
                docommands.getLast().undo();
                undocommands.addLast(docommands.removeLast());
                //docommands.getLast().execute();
                docommands.forEach((next) -> {
                    next.execute();
                });
                --pos;
                System.out.println("undo()" + docommands + " " + pos + " " + count);     
            } catch (Exception e) {
                e.getMessage();
                System.out.println(".undo() list isemty! ");
            }
        }      
    }
    public void redo(){
        try {
            if(undocommands.size() >= 0){
             docommands.addLast(undocommands.removeLast());
             docommands.getLast().execute();
            pos++;
            System.out.println(".redo()" + docommands + " " + pos + " " + count); 
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
            System.out.println("CommandHandler.redo() cant redo");
        }
    }
    
//    public void execute(){
////         Iterator it = docommands.iterator();
////            while (it.hasNext())
////            ((IMyCommand) it.next()).execute();
//        //docommands.forEach(IMyCommand::execute);
//        //docommands.clear();
//    }
    
 }
