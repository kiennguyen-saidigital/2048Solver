
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public class BreadthFirstSolver extends AbstractSolver {

    private Queue<State> queue = new LinkedList<>();
    
    @Override
    protected boolean hasElements() {
        return !queue.isEmpty();
    }

    @Override
    protected State nextState() {
        return queue.remove();
    }

    @Override
    protected void addState(State s) {
        if(!queue.contains(s)){
            queue.offer(s);
        }
    }

    @Override
    protected void clearOpen() {
        queue.clear();
    }
    
}
