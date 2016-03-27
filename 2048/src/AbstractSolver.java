
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public abstract class AbstractSolver implements Solver {
    //state da visited
    private Set<State> closed = new HashSet<>();
    @Override
    public List<State> solve(State initialState){
        //Set lưu các State đã visit
        closed.clear();
        //clear running stack/queue
        clearOpen();
        addState(initialState);
        while(hasElements()){
            State s = nextState();
            if(s.isSolution()){
                System.out.println("Solution is found\n");
                return findPath(s);
            }
            closed.add(s);
            //System.out.println(getVisitedStateCount());
            //get possible moves từ 1 sate để add vào trong queue/stack
            //để vòng lặp tiếp theo pop ra thao tác tiếp
            Iterable<State> moves = s.getPossibleMoves();
            for(State move: moves){
                if(!closed.contains(move)){
                    addState(move);
                }
            }
        }
        System.out.println("Game over. Can't find a solution");
        return null;
    }
    
    public int getVisitedStateCount(){
        return closed.size();
    }
    public List<State> findPath(State solution){
        LinkedList<State> path = new LinkedList<>();
        while(solution!=null){
            path.addFirst(solution);
            solution = solution.getParent();
        }
        return path;
    }
    protected abstract boolean hasElements();
    protected abstract State nextState();
    protected abstract void addState(State s);
    protected abstract void clearOpen();
}
