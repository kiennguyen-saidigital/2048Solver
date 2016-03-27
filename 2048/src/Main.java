
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nk
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("-------Chuong trinh choi game 2048---------");
        System.out.println("Game khoi dong voi 1 o co gia tri 2");
        System.out.println("Sau moi lan move 1 so random (2 hoac 4) se duoc tao ra va insert vao 1 o bat ki");
        System.out.println("Tro choi ket thuc khi co 1 o dat gia tri 2048");
        System.out.print("Chon input - nhap so bat ki tu 1 -> 10: ");

        Scanner sc1 = new Scanner(System.in);
        int input = sc1.nextInt();
        GridV2 g = new GridV2();
        g.setMove("root");

        if (input == 1) {
            g.getCell(0, 0).setValue(2);
            g.getCell(1, 0).setValue(8);
            g.getCell(1, 2).setValue(4);
            g.getCell(2, 0).setValue(64);
        } else if (input == 2) {
            g.getCell(0, 1).setValue(4);
            g.getCell(0, 3).setValue(16);
            g.getCell(1, 0).setValue(2);
            g.getCell(1, 2).setValue(64);
            g.getCell(2, 0).setValue(2);
            g.getCell(2, 3).setValue(8);
            g.getCell(3, 1).setValue(256);
        } else if (input == 3) {
            g.getCell(0, 0).setValue(4);
            g.getCell(1, 1).setValue(8);
            g.getCell(0, 2).setValue(2);
            g.getCell(2, 2).setValue(4);
            g.getCell(2, 1).setValue(128);
            g.getCell(3, 3).setValue(2);
        } else if (input == 4) {
            g.getCell(0, 0).setValue(128);
            g.getCell(0, 1).setValue(8);
            g.getCell(0, 3).setValue(4);
            g.getCell(1, 2).setValue(64);
            g.getCell(1, 3).setValue(32);
            g.getCell(2, 1).setValue(4);
            g.getCell(2, 2).setValue(8);
            g.getCell(2, 3).setValue(2);
            g.getCell(3, 1).setValue(8);
            g.getCell(3, 2).setValue(2);
        } else if (input == 5) {
            g.getCell(0, 3).setValue(128);
            g.getCell(0, 2).setValue(2);
            g.getCell(1, 2).setValue(32);
            g.getCell(2, 0).setValue(1024);
        } else if (input == 6) {
            g.getCell(0, 0).setValue(2);
            g.getCell(1, 0).setValue(32);
            g.getCell(2, 2).setValue(4);
            g.getCell(3, 0).setValue(128);
            g.getCell(3, 1).setValue(2);
            g.getCell(3, 2).setValue(8);
            g.getCell(3, 3).setValue(256);
        } else if (input == 7) {
            g.getCell(0, 0).setValue(2);
            g.getCell(0, 1).setValue(8);
            g.getCell(0, 2).setValue(4);
            g.getCell(0, 3).setValue(2);
            g.getCell(1, 2).setValue(2);
            g.getCell(1, 3).setValue(4);
            g.getCell(2, 1).setValue(32);
            g.getCell(2, 2).setValue(2);
            g.getCell(3, 0).setValue(8);
            g.getCell(3, 1).setValue(128);
            g.getCell(3, 2).setValue(8);
            g.getCell(3, 3).setValue(256);
        } else if (input == 8) {
            g.getCell(0, 1).setValue(2);
            g.getCell(0, 2).setValue(8);
            g.getCell(0, 3).setValue(4);
            g.getCell(1, 1).setValue(16);
            g.getCell(1, 2).setValue(32);
            g.getCell(2, 0).setValue(8);
            g.getCell(2, 1).setValue(32);
            g.getCell(2, 2).setValue(1024);
            g.getCell(2, 3).setValue(8);
            g.getCell(3, 1).setValue(128);
            g.getCell(3, 2).setValue(8);
            g.getCell(3, 3).setValue(1024);
        } else if (input == 9) {
            g.getCell(0, 0).setValue(2);
            g.getCell(0, 1).setValue(32);
            g.getCell(0, 2).setValue(16);
            g.getCell(0, 3).setValue(64);
            g.getCell(1, 1).setValue(4);
            g.getCell(1, 3).setValue(2);
            g.getCell(2, 1).setValue(16);
            g.getCell(2, 2).setValue(2);
            g.getCell(3, 0).setValue(128);
            g.getCell(3, 1).setValue(4);
            g.getCell(3, 2).setValue(2);
            g.getCell(3, 3).setValue(1024);

            //game over  
        } else {
            g.getCell(0, 0).setValue(8);
            g.getCell(0, 1).setValue(128);
            g.getCell(0, 2).setValue(8);
            g.getCell(0, 3).setValue(2);
            g.getCell(1, 0).setValue(4);
            g.getCell(1, 1).setValue(32);
            g.getCell(1, 2).setValue(64);
            g.getCell(1, 3).setValue(8);
            g.getCell(2, 0).setValue(8);
            g.getCell(2, 1).setValue(16);
            g.getCell(2, 2).setValue(8);
            g.getCell(2, 3).setValue(2);
            g.getCell(3, 0).setValue(2);
            g.getCell(3, 1).setValue(2);
            g.getCell(3, 2).setValue(4);
            g.getCell(3, 3).setValue(2);
        }

        System.out.println(g);
        System.out.println(g.getMaxCell());

        System.out.println();
        System.out.println("-Original State-");
        System.out.println();
        System.out.println(g);
        System.out.println("Chon phuong phap giai: ");
        System.out.println("\t1. Using BreadFistSearch");
        System.out.println("\t2. Using DepthFistSearch");
        System.out.println("\t3. Using Simple Hill climbing");
        System.out.print("Chon phuong phap so: ");
        Scanner sc = new Scanner(System.in);
        Solver solver = null;
        int choose = sc.nextInt();
        if (choose == 1) {
            solver = new BreadthFirstSolver();
        } else if (choose == 2) {
            solver = new DepthFistSolver();
        } else if (choose == 3) {
            solver = new HillClimbingSolver();
        } else {
            System.out.println("Input khong hop le");
            System.exit(1);
        }

        boolean print = false;
        g.setMove("root + generate random number");
        long start = System.currentTimeMillis();
        List path = solver.solve(g);
        long end = System.currentTimeMillis();
        System.out.println("Path");
        if (path != null) {
            Iterator it = path.iterator();

            while (it.hasNext()) {
                System.out.println(it.next());
                System.out.println();
            }
            System.out.println("path to solution size: " + path.size());
            System.out.println("Time: " + (end - start) + " milliseconds");
        }

    }
}
