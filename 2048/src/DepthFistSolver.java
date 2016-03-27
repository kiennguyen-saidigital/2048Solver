
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public class DepthFistSolver extends AbstractSolver {

    private Stack<State> stack= new Stack<>();
    
    @Override
    protected boolean hasElements() {
        return !stack.empty();
    }

    @Override
    protected State nextState() {
        return stack.pop();
    }

    @Override
    //contains: equals la duoc, ko can trung hashcode
    protected void addState(State s) {
        if(!stack.contains(s)){
            stack.push(s);
        }
    }

    @Override
    protected void clearOpen() {
        stack.clear();
    }
    
}
