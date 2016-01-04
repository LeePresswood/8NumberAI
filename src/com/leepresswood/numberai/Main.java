package com.leepresswood.numberai;

public class Main {

    public static void main(String[] args) {
        int moves = Solver.solve(new Board(), 0, 100);

        if(moves != -1){
            System.out.println(moves);
        }
        else{
            System.out.println("Failure.");
        }
    }
}
