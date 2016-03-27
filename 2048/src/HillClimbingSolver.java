
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
public class HillClimbingSolver implements Solver {

    //do mỗi current state chỉ chọn 1 state tốt hơn đầu tiên -> chỉ cần dùng LinkedList để chứa các state, ko cần backtrack
    LinkedList<State> list = new LinkedList<>();

    @Override
    public List<State> solve(State initialState) {
        list.clear();
        list.add(initialState);
        GridV2 initial = (GridV2)initialState;
        Set<State> moves = null;
        moves = (Set<State>) (Iterable<State>) moves;
        GridV2 nextMove;
        moves = (Set<State>) initial.getPossibleMoves();
        while (!initial.isSolution() && (moves.size() != 0)) {
            for (State move : moves) {
                nextMove = (GridV2)move;
                if (!list.contains(nextMove) && initial.worseThan((GridV2)nextMove)) {
                    list.add(nextMove);
                    System.out.println("a");
                    break;
                }
            }
            initial =(GridV2) list.getLast();
            System.out.println(initial);
            moves = (Set<State>) initial.getPossibleMoves();
        }
        
        if(initial.isSolution()){
            System.out.println("Solution is found");
        }
        else{
            System.out.println("Can't found solution");
        }
        return list;
    }
}
