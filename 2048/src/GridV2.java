/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nk
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
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
//chỉ quản lý bằng các Set các Cell ko dùng int matrix
public class GridV2 extends AbstractState {

    //to print in path
    private String move;

    public String getMove() {
        return this.move;
    }

    public void setMove(String move) {
        this.move = move;
    }
    private List<Cell> allCells;

    public GridV2() {
        allCells = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                allCells.add(new Cell(i, j, 0));
            }
        }
    }

    //tao grid moi voi sate giống parent (2 object riêng)
    public GridV2(GridV2 parent) {
        this();
        Iterable<Cell> cells = parent.getAllCells();
        for (Cell cell : cells) {
            this.getCell(cell.getX(), cell.getY()).setValue(cell.getValue());
        }
        this.setParent(parent);
    }

    public Cell getCell(int x, int y) {
        for (Cell cell : allCells) {
            if ((cell.getX() == x) && (cell.getY() == y)) {
                return cell;
            }
        }
        return null;
    }

    public int getCellValue(int x, int y) {
        return getCell(x, y).getValue();
    }

    //normal order
    public List<Cell> getAllCells() {
        return this.allCells;
    }

    public List<Cell> getAllEmptyCell() {
        List<Cell> list = new LinkedList<>();
        for (Cell cell : this.getAllCells()) {
            if (cell.getValue() == 0) {
                list.add(cell);
            }
        }
        return list;
    }

    //for hill-climbing
    //better mean state co it hon hoac bang so o trong
    public List<Cell> getAroundCell(Cell c) {
        List<Cell> list = new LinkedList<>();
        //4 o giua
        if (getLeftCell(c) != null) {
            list.add(getLeftCell(c));
        }
        if (getAboveCell(c) != null) {
            list.add(getAboveCell(c));
        }
        if (getRightCell(c) != null) {
            list.add(getRightCell(c));
        }
        if (getBelowCell(c) != null) {
            list.add(getBelowCell(c));
        }
        return list;
    }

    public Cell getMaxCell(){
        Cell maxCell = this.getCell(0, 0);
        for(Cell cell: this.getAllCells()){
            if(cell.getValue() > maxCell.getValue()){
                maxCell = cell;
            }
        }
        return maxCell;
    }
    //s better than -> true
    public boolean worseThan(GridV2 s) {
        List emptyOnThis = this.getAllEmptyCell();
        List emptyOnS = s.getAllEmptyCell();

        if ((emptyOnS.size() >= emptyOnThis.size()) || this.getMaxCell().getX() == 0) {
           return true;
        }
        return false;
    }
    /*
     if ((emptyOnS.size() >= emptyOnThis.size()) || cell.getValue() >= s.getBelowCell(cell).getValue()) {
     return true;
     }
     */

    /*
     List emptyOnThis = this.getAllEmptyCell();
     List emptyOnS = s.getAllEmptyCell();
     for (Cell c : s.getAroundCell(cell)) {
     if (emptyOnS.size() > emptyOnThis.size()|| c.getValue() == cell.getValue()) {
     return true;
     }
     }
     */
    //order bottom to up
    public List<Cell> getAllCellsBottomToUp() {
        List<Cell> listBottomToUp = new LinkedList<>();
        List<Cell> allCellNormal = this.getAllCells();
        for (int i = 12; i < 16; i++) {
            listBottomToUp.add(allCellNormal.get(i));
        }
        for (int i = 8; i < 12; i++) {
            listBottomToUp.add(allCellNormal.get(i));
        }
        for (int i = 4; i < 8; i++) {
            listBottomToUp.add(allCellNormal.get(i));
        }
        for (int i = 0; i < 4; i++) {
            listBottomToUp.add(allCellNormal.get(i));
        }
        return listBottomToUp;
    }

    public List<Cell> getAllCellsLeftToRight() {
        List<Cell> listLeftToRight = new LinkedList<>();
        List<Cell> allCellNormal = this.getAllCells();
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0) {
                listLeftToRight.add(allCellNormal.get(i));
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 1) {
                listLeftToRight.add(allCellNormal.get(i));
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 2) {
                listLeftToRight.add(allCellNormal.get(i));
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 3) {
                listLeftToRight.add(allCellNormal.get(i));
            }
        }
        return listLeftToRight;
    }

    public List<Cell> getAllCellRightToLeft() {
        List<Cell> listRightToLeft = new LinkedList<>();
        List<Cell> allCellNormal = this.getAllCells();
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 3) {
                listRightToLeft.add(allCellNormal.get(i));
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 2) {
                listRightToLeft.add(allCellNormal.get(i));
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 1) {
                listRightToLeft.add(allCellNormal.get(i));
            }
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0) {
                listRightToLeft.add(allCellNormal.get(i));
            }
        }
        return listRightToLeft;
    }

    @Override
    public Iterable<State> getPossibleMoves() {
        /*
         1.initial state
         2.insert random number
         3. can move?
         ok: move + gộp value
         return new state
         quay lại step 2
         not ok: -> game over
         */
        this.setRandomNumberToRandomCell();
        Set<State> moves = new HashSet<>();

        if (canMoveUp()) {
            //tạo ra state mới và add vào moves
            State grid = new GridV2(this);
            grid = moveUp();
            moves.add(grid);
        }
        if (canMoveDown()) {
            State grid = new GridV2(this);
            grid = moveDown();
            moves.add(grid);
        }
        if (canMoveLeft()) {
            State grid = new GridV2(this);
            grid = moveLeft();
            moves.add(grid);
        }
        if (canMoveRight()) {
            State grid = new GridV2(this);
            grid = moveRight();
            moves.add(grid);
        }

        return moves;
    }

    public Cell getLeftCell(Cell c) {
        for (Cell cell : this.getAllCells()) {
            if ((cell.getX() == c.getX() && (cell.getY() == (c.getY() - 1)))) {
                return cell;
            }
        }
        return null;
    }

    public Cell getRightCell(Cell c) {
        for (Cell cell : this.getAllCells()) {
            if ((cell.getX() == c.getX() && (cell.getY() == (c.getY() + 1)))) {
                return cell;
            }
        }
        return null;
    }

    public Cell getAboveCell(Cell c) {
        for (Cell cell : this.getAllCells()) {
            if ((cell.getY() == c.getY() && (cell.getX() == (c.getX() - 1)))) {
                return cell;
            }
        }
        return null;
    }

    public Cell getBelowCell(Cell c) {
        for (Cell cell : this.getAllCells()) {
            if ((cell.getY() == c.getY() && (cell.getX() == (c.getX() + 1)))) {
                return cell;
            }
        }
        return null;
    }

    //return cell dau tien ben trai khac 0
    //neu ko co thi return null
    public Cell getLeftMostCell(Cell c) {
        Cell result = null;
        while (getLeftCell(c).getValue() == 0) {
            c = getLeftCell(c);
            if (c.getY() == 0) {
                return null;
            }
        }
        return getLeftCell(c);
    }

    public Cell getRightMostCell(Cell c) {
        Cell result = null;
        while (getRightCell(c).getValue() == 0) {
            c = getRightCell(c);
            if (c.getY() == 3) {
                return null;
            }
        }
        return getRightCell(c);
    }

    public Cell getAboveMostCell(Cell c) {
        Cell result = null;
        while (getAboveCell(c).getValue() == 0) {
            c = getAboveCell(c);
            if (c.getX() == 0) {
                return null;
            }
        }
        return getAboveCell(c);
    }

    public Cell getBelowMostCell(Cell c) {
        Cell result = null;
        while (getBelowCell(c).getValue() == 0) {
            c = getBelowCell(c);
            if (c.getX() == 3) {
                return null;
            }
        }
        return getBelowCell(c);
    }

    public boolean canMoveUp() {
        boolean canMove = false;
        Iterable<Cell> cells = this.getAllCells();
        for (Cell cell : cells) {
            if ((cell.getValue() != 0) && getAboveCell(cell) != null && (getAboveCell(cell).getValue() == 0)) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public boolean canMoveDown() {
        boolean canMove = false;
        Iterable<Cell> cells = this.getAllCells();
        for (Cell cell : cells) {
            if ((cell.getValue() != 0) && getBelowCell(cell) != null && (getBelowCell(cell).getValue() == 0)) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public boolean canMoveLeft() {
        boolean canMove = false;
        Iterable<Cell> cells = this.getAllCells();
        for (Cell cell : cells) {
            if ((cell.getValue() != 0) && getLeftCell(cell) != null && (getLeftCell(cell).getValue() == 0)) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public boolean canMoveRight() {
        boolean canMove = false;
        Iterable<Cell> cells = this.getAllCells();
        for (Cell cell : cells) {
            if ((cell.getValue() != 0) && getRightCell(cell) != null && (getRightCell(cell).getValue() == 0)) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    public List<Cell> getAllCellAbove(Cell cell) {
        List list = new LinkedList<>();
        int x = cell.getX();
        while (x > 0) {
            list.add(getCell(x - 1, cell.getY()));
            x = x - 1;
        }
        return list;
    }

    public List<Cell> getAllCellBelow(Cell cell) {
        List list = new LinkedList<>();
        int x = cell.getX();
        while (x < 3) {
            list.add(getCell(x + 1, cell.getY()));
            x = x + 1;
        }
        return list;
    }

    public List<Cell> getAllCellLeft(Cell cell) {
        List list = new LinkedList<>();
        int y = cell.getY();
        while (y > 0) {
            list.add(getCell(cell.getX(), y - 1));
            y = y - 1;
        }
        return list;
    }

    public List<Cell> getAllCellRight(Cell cell) {
        List list = new LinkedList<>();
        int y = cell.getY();
        while (y < 3) {
            list.add(getCell(cell.getX(), y + 1));
            y = y + 1;
        }
        return list;
    }

    public Cell getNAbove(Cell cell, int n) {
        return getCell(cell.getX() - n, cell.getY());
    }

    public Cell getNBelow(Cell cell, int n) {
        return getCell(cell.getX() + n, cell.getY());
    }

    public Cell getNLeft(Cell cell, int n) {
        return getCell(cell.getX(), cell.getY() - n);
    }

    public Cell getNRight(Cell cell, int n) {
        return getCell(cell.getX(), cell.getY() + n);
    }

    public List<Cell> getRow(int row) {
        List<Cell> list = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            list.add(getCell(row, i));
        }
        return list;
    }

    public List<Cell> getColumn(int column) {
        List<Cell> list = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            list.add(getCell(i, column));
        }
        return list;
    }

    public State moveUp() {
        //quet: ben tren cell co n o trong -> di chuyen len n
        //gop hang 0 + hang 1 (neu giong nhau)
        //quet them 1 lan nua, tren cell n o trong -> di chuyen len n
        //gop hang 1 hang 2
        //quet de move them 1 lan nua
        //gop hang 2 hang 3
        GridV2 child = new GridV2(this);

        //quet lan 1
        List<Cell> list;
        for (Cell cell : child.getAllCells()) {
            list = child.getAllCellAbove(cell);
            int count = 0;
            for (Cell c : list) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNAbove(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop hang 0 + 1 cho các số giống nhau
        List<Cell> row0 = child.getRow(0);
        for (Cell cell : row0) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX() + 1, cell.getY()).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX() + 1, cell.getY()).setValue(0);
            }
        }
        //quet lan 2
        List<Cell> list2;
        for (Cell cell : child.getAllCells()) {
            list2 = child.getAllCellAbove(cell);
            int count = 0;
            for (Cell c : list2) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNAbove(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 2
        List<Cell> row1 = child.getRow(1);
        for (Cell cell : row1) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX() + 1, cell.getY()).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX() + 1, cell.getY()).setValue(0);
            }
        }

        //quet lan 3
        List<Cell> list3;
        for (Cell cell : child.getAllCells()) {
            list3 = child.getAllCellAbove(cell);
            int count = 0;
            for (Cell c : list3) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNAbove(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 2 + 3
        List<Cell> row2 = child.getRow(2);
        for (Cell cell : row2) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX() + 1, cell.getY()).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX() + 1, cell.getY()).setValue(0);
            }
        }
        child.setMove("move up + generate random number");
        return child;
    }

    public State moveDown() {
        GridV2 child = new GridV2(this);
        //quet lan 1
        List<Cell> list;
        for (Cell cell : child.getAllCellsBottomToUp()) {
            list = child.getAllCellBelow(cell);
            int count = 0;
            for (Cell c : list) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell duoi nó count ô
            if (count != 0) {
                child.getNBelow(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop hang 3 + 2 cho các số giống nhau
        List<Cell> row3 = child.getRow(3);
        for (Cell cell : row3) {
            //so sánh value nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX() - 1, cell.getY()).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX() - 1, cell.getY()).setValue(0);
            }
        }
        //quet lan 2
        List<Cell> list2;
        for (Cell cell : child.getAllCellsBottomToUp()) {
            list2 = child.getAllCellBelow(cell);
            int count = 0;
            for (Cell c : list2) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNBelow(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 2
        List<Cell> row2 = child.getRow(2);
        for (Cell cell : row2) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX() - 1, cell.getY()).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX() - 1, cell.getY()).setValue(0);
            }
        }

        //quet lan 3
        List<Cell> list3;
        for (Cell cell : child.getAllCellsBottomToUp()) {
            list3 = child.getAllCellBelow(cell);
            int count = 0;
            for (Cell c : list3) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNBelow(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 0
        List<Cell> row1 = child.getRow(1);
        for (Cell cell : row1) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX() - 1, cell.getY()).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX() - 1, cell.getY()).setValue(0);
            }
        }
        child.setMove("move down + generate random number");
        return child;
    }

    public State moveLeft() {
        GridV2 child = new GridV2(this);
        //quet lan 1
        List<Cell> list;
        for (Cell cell : child.getAllCellsLeftToRight()) {
            list = child.getAllCellLeft(cell);
            int count = 0;
            for (Cell c : list) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell duoi nó count ô
            if (count != 0) {
                child.getNLeft(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop col 0 va 1
        List<Cell> col0 = child.getColumn(0);
        for (Cell cell : col0) {
            //so sánh value nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX(), cell.getY() + 1).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX(), cell.getY() + 1).setValue(0);
            }
        }
        //quet lan 2
        List<Cell> list2;
        for (Cell cell : child.getAllCellsLeftToRight()) {
            list2 = child.getAllCellLeft(cell);
            int count = 0;
            for (Cell c : list2) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNLeft(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 2
        List<Cell> col1 = child.getColumn(1);
        for (Cell cell : col1) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX(), cell.getY() + 1).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX(), cell.getY() + 1).setValue(0);
            }
        }

        //quet lan 3
        List<Cell> list3;
        for (Cell cell : child.getAllCellsLeftToRight()) {
            list3 = child.getAllCellLeft(cell);
            int count = 0;
            for (Cell c : list3) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNLeft(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 0
        List<Cell> col2 = child.getColumn(2);
        for (Cell cell : col2) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX(), cell.getY() + 1).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX(), cell.getY() + 1).setValue(0);
            }
        }
        child.setMove("move left + generate random number");
        return child;
    }

    public State moveRight() {
        GridV2 child = new GridV2(this);
        //quet lan 1
        List<Cell> list;
        for (Cell cell : child.getAllCellRightToLeft()) {
            list = child.getAllCellRight(cell);
            int count = 0;
            for (Cell c : list) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            if (count != 0) {
                child.getNRight(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop hang 3 + 2 cho các số giống nhau
        List<Cell> col3 = child.getColumn(3);
        for (Cell cell : col3) {
            //so sánh value nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX(), cell.getY() - 1).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX(), cell.getY() - 1).setValue(0);
            }
        }
        //quet lan 2
        List<Cell> list2;
        for (Cell cell : child.getAllCellRightToLeft()) {
            list2 = child.getAllCellRight(cell);
            int count = 0;
            for (Cell c : list2) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNRight(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 2
        List<Cell> col2 = child.getColumn(2);
        for (Cell cell : col2) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX(), cell.getY() - 1).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX(), cell.getY() - 1).setValue(0);
            }
        }

        //quet lan 3
        List<Cell> list3;
        for (Cell cell : child.getAllCellRightToLeft()) {
            list3 = child.getAllCellRight(cell);
            int count = 0;
            for (Cell c : list3) {
                if (c.getValue() == 0) {
                    count++;
                }
            }
            //set value cua cell dang run cho cell tren nó count ô
            if (count != 0) {
                child.getNRight(cell, count).setValue(cell.getValue());
                cell.setValue(0);
            }
        }

        //gop dong 1 + 0
        List<Cell> col1 = child.getColumn(1);
        for (Cell cell : col1) {
            //so sánh value dòng 1 và dòng 2, nếu bằng thì gộp lại
            if (cell.getValue() == child.getCell(cell.getX(), cell.getY() - 1).getValue()) {
                cell.setValue(2 * cell.getValue());
                child.getCell(cell.getX(), cell.getY() - 1).setValue(0);
            }
        }
        child.setMove("move right + generate random number");
        return child;
    }

    //chọn các ô = 0 in Set of cells
    //lấy 1 ô để set value random
    //insert vào thì tạo thêm cell
    //chưa hiệu quả lắm - nếu rảnh implement lại sau
    //set lai state cua g, ko tao state moi
    public State setRandomNumberToRandomCell() {
        List<Cell> emptyCells = new ArrayList<>();
        for (Cell cell : this.getAllCells()) {
            if (cell.getValue() == 0) {
                emptyCells.add(cell);
            }
        }
        Random rd = new Random();
        if (emptyCells.size() != 0) {
            Cell choosen = emptyCells.get(rd.nextInt(emptyCells.size()));
            choosen.setValue(generateRandomNumber());
        }
        return this;
    }

    //tạo số 2 hoặc 4 và insert vào grid mỗi lần move
    public int generateRandomNumber() {
        int randomNumber;
        double xacSuat = Math.random();
        if (xacSuat >= 0.25) {
            randomNumber = 2;
        } else {
            randomNumber = 4;
        }
        return randomNumber;
    }

    @Override
    public boolean isSolution() {
        for (Cell cell : this.getAllCells()) {
            if (cell.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        GridV2 g;
        if (!(o instanceof GridV2)) {
            return false;
        } else {
            g = (GridV2) o;
            return this.getAllCells().equals(g.getAllCells());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.allCells);
        return hash;
    }

    @Override
    public String toString() {
        String result = "";
        result += this.getMove();
        result += "\n";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (getCellValue(i, j) < 10) {
                    result += getCellValue(i, j) + "    ";
                } else if (getCellValue(i, j) < 100) {
                    result += getCellValue(i, j) + "   ";
                } else if (getCellValue(i, j) < 1000) {
                    result += getCellValue(i, j) + "  ";
                } else {
                    result += getCellValue(i, j) + " ";
                }
            }
            result += "\n";
        }
        return result;
    }
}
