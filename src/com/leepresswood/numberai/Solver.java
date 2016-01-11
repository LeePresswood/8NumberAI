package com.leepresswood.numberai;

/**
 * Created by leepresswood on 12/17/15.
 */
public class Solver{
	/**
	 * Solve the passed board using lookahead by max_depth states.
	 * @param board The board to solve.
	 * @param max_depth The depth to look ahead.
	 * @return
	 */
	public static int solve(Board board, int max_depth){
		int moves = 0;
		while(!board.isSolved() && moves < 100){
			int neighbor_to_shift = getMax(board, board.getNeighborsOfBlank(), 0, max_depth);
			board.shift(board.getPositionOf(board.getNeighborsOfBlank()[neighbor_to_shift]));
			moves++;
		}

		if(moves < 100){
			return moves;
		}
		return -1;
	}

	/**
	 * Compare the neighbors' states. Find the most fit (the lowest score).
	 * @param board Current state.
	 * @param neighbors List of neighbors
	 * @param current_depth How deep we are.
	 * @param max_depth How deep we can go.
	 * @return The index of the best neighbor.
	 */
	private static int getMax(Board board, int[] neighbors, int current_depth, int max_depth){
		//Finding a score of 0 means we found the best possible solution.
		if(board.isSolved()){
		//	System.out.println("Solved at depth " + current_depth + ".");
			return 0;
		}

		//Hitting the max depth means we should just return the score.
		if(current_depth >= max_depth){
		//	System.out.println("Max depth reached. Current score: " + board.getHeuristicScore() + ".");
			return board.getHeuristicScore();
		}

		//Otherwise, we need to go deeper on all these neighbors.
		int[] scores = new int[neighbors.length];
		for(int i = 0; i < neighbors.length; i++){
			Board this_board = new Board(board);
			this_board.shift(this_board.getPositionOf(neighbors[i]));
			int[] this_neighbors = this_board.getNeighborsOfBlank();

			scores[i] = getMax(this_board, this_neighbors, current_depth + 1, max_depth);

			//If we found a winning state, return immediately. This is a speedup.
			if(scores[i] == 0){
				//System.out.println("Solved. Current depth: " + current_depth + ".");
				return i;
			}
		}
		System.out.println(board);
		int smallest_index = 0;
		for(int i = 1; i < neighbors.length; i++){
			System.out.println(i + " -> " + scores[i]);
			if(scores[i] < scores[smallest_index]){
				smallest_index = i;
			}
		}

		//System.out.println("Smallest index: " + smallest_index + " with a score of " + scores[smallest_index] + " found.");
		return smallest_index;
	}
}
