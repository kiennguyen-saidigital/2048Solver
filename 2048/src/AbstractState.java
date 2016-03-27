/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public abstract class AbstractState implements State{
    private State parent = null;
    private double distance = 0;
    public AbstractState(){}
    public AbstractState(State parent){
        this.parent = parent;
        this.distance = parent.getDistance() + 1;
    }
    @Override
    public State getParent(){
        return parent;
    }
    
    public void setParent(State parent){
        this.parent = parent;
    }
    
    @Override
    public double getDistance(){
        return distance;
    }
    
}
