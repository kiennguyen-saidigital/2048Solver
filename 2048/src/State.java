/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public interface State {
    //print? co print ra ngoai man hinh cac step hay ko?
    public Iterable<State> getPossibleMoves();
    public boolean isSolution();
    public double getDistance();
    public State getParent();
}
