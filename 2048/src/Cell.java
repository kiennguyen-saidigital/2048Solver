/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
public class Cell {
    private int x;
    private int y;
    private int value;
    
    public Cell(){
    }
    
    public Cell(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
    
    public Cell(int value){
        this.value = value;
    }
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value){
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        hash = 37 * hash + this.value;
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        Cell cell;
        if(!(o instanceof Cell)){
            return false;
        }
        else{
            cell = (Cell)o;
            return (this.getX() == cell.getX()) &&(this.getY()==cell.getY()) 
                    &&(this.getValue() == cell.getValue());
        }
    }
    //for test
    @Override
    public String toString(){
        return (this.getX() +"," +this.getY() +": " +this.getValue());
    }
}
