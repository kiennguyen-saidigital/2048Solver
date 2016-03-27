
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public interface Solver {
    //print: có in ra các steps ko
    public List<State> solve(State initialState);
}
