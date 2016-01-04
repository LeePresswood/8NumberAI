package com.leepresswood.numberai;

/**
 * Created by leepresswood on 12/17/15.
 */
public class Solver{
	/**
	 * Solves the passed board.
	 * @param board The board to work on.
	 * @return Number of moves.
	 */
	public static int solve(Board board){
		int moves = 0;

		//Get the current score of the board.
		int current_score = board.getHeuristicScore();
		while(current_score != 0){
			//Every neighbor should be checked.
			int neighbor = getBestNeighbor(board);

			//Use this above neighbor to swap on the main board.
			board.shift(board.getPositionOf(neighbor));

			//Get ready for the next move.
			moves++;
			current_score = board.getHeuristicScore();
		}

		return moves;
	}

	private static int getBestNeighbor(Board board){
		Integer[] neighbors = board.getNeighborsOfBlank();
		int best = -1;

		//Make clones of the board for each neighbor.
		Board[] boards = new Board[neighbors.length];
		for(int i = 0; i < boards.length; i++){
			boards[i] = new Board(board);

			//Shift this copy.
			boards[i].shift(boards[i].getPositionOf(neighbors[i]));

			//Get the score and compare.
			if(best == -1 || boards[best].getHeuristicScore() > boards[i].getHeuristicScore()){
				best = i;
			}
		}

		return best;
	}
}
