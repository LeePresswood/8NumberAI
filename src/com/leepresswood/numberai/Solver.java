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
	public static int solve(Board board, int moves_previous, int last_moved, int max_depth){
		//Branch was a failure.
		if(moves_previous >= max_depth){
			return -1;
		}

		//Branch was a success.
		if(board.isSolved()){
			return moves_previous;
		}

		//Getting here means that we need to go deeper. Recursively check best moves.
		int[] neighbors = board.getNeighborsOfBlank();
		int[] this_boards_moves = new int[neighbors.length];
		Board[] neighbor_boards = new Board[neighbors.length];
		int best_moves = 9999;
		for(int i = 0; i < neighbors.length; i++){
			if(neighbors[i] == last_moved){
				continue;
			}
			//System.out.println(moves_previous + " : " + board.getHeuristicScore() + " : " + neighbors[i]);

			neighbor_boards[i] = new Board(board);
			neighbor_boards[i].shift(neighbor_boards[i].getPositionOf(neighbors[i]));
			this_boards_moves[i] = solve(neighbor_boards[i], moves_previous + 1, neighbors[i], max_depth);

			if(this_boards_moves[i] != -1 && this_boards_moves[i] < best_moves){
				best_moves = this_boards_moves[i];
			}
		}

		return (best_moves == 9999 ? -1 : best_moves);
	}
}
