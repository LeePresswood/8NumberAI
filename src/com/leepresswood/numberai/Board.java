package com.leepresswood.numberai;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by leepresswood on 12/17/15.
 */
public class Board{
	public int[][] numbers;

	public Board(){
		initializeBoard();
	}

	public Board(Board to_copy){
		this.numbers = Arrays.copyOf(to_copy.numbers.clone(), to_copy.numbers.length);
	}

	public void initializeBoard(){
		numbers = new int[3][3];
		Random random = new Random();

		//Init all with -1.
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				numbers[j][i] = -1;
			}
		}

		//Set all numbers.
		int counter = 0;
		while(counter < 9){
			//Choose a position at random.
			int x = random.nextInt(3);
			int y = random.nextInt(3);

			//If this is free, place and move on.
			if(numbers[y][x] == -1){
				numbers[y][x] = counter++;
			}
		}
	}

	public Position getPositionOf(int position){
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				if(numbers[j][i] == position){
					return new Position(i, j);
				}
			}
		}
		return null;
	}

	public boolean shift(Position position){
		//Can only shift numbers. Not blanks.
		if(numbers[position.y][position.x] == 0){
			return false;
		}

		//Find the blank location.
		Position blank_position = getPositionOf(0);

		//Blank position must be within 1 of the requested shift position.
		if(Math.abs(position.x - blank_position.x) == 1 && Math.abs(position.y - blank_position.y) == 0 || Math.abs(position.x - blank_position.x) == 0 && Math.abs(position.y - blank_position.y) == 1){
			//Shift can work. Put the selected position's value in the blank position and put a blank in this position.
			numbers[blank_position.y][blank_position.x] = numbers[position.y][position.x];
			numbers[position.y][position.x] = 0;
			return true;
		}

		return false;
	}

	public int[] getNeighborsOfBlank(){
		Position blank_position = getPositionOf(0);
		ArrayList<Integer> neighbors = new ArrayList<>();
		for(int i = 1; i < 9; i++){
			Position position = getPositionOf(i);
			if(Math.abs(position.x - blank_position.x) == 1 && Math.abs(position.y - blank_position.y) == 0 || Math.abs(position.x - blank_position.x) == 0 && Math.abs(position.y - blank_position.y) == 1){
				neighbors.add(i);
			}
		}

		int[] as_int_array = new int[neighbors.size()];
		for(int i = 0; i < neighbors.size(); i++){
			as_int_array[i] = neighbors.get(i).intValue();
		}

		return as_int_array;
	}

	public boolean isSolved(){
		return getHeuristicScore() == 0;
	}

	/**
	 * Score the board based upon the Manhattan distance. Lower is better.
	 * @return Sum of all the positions' Manhattan distances.
	 */
	public int getHeuristicScore(){
		int score = 0;
		for(int i = 1; i < 9; i++){
			score += getManhattanDistance(i);
		}

		return score;
	}

	/**
	 * Get the distance from the actual position.
	 * @param value Value we're researching.
	 * @return The Manhattan Distance.
	 */
	private int getManhattanDistance(int value){
		Position current_position = getPositionOf(value);
		int val_position_x = (value - 1) % 3;
		int val_position_y = (value - 1) / 3;

		return Math.abs(current_position.x - val_position_x) + Math.abs(current_position.y - val_position_y);
	}

	@Override
	public String toString(){
		String string = "";

		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				if(numbers[j][i] == 0){
					string += " _ ";
				}
				else{
					string += " " + numbers[j][i] + " ";
				}
			}
			string += "\n";
		}

		return string;
	}

	public class Position{
		public int x, y;
		public Position(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString(){
			return "X: " + x + " : Y: " + y;
		}
	}

	public enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
}