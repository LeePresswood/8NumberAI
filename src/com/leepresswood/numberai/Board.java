package com.leepresswood.numberai;

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
		if(Math.abs(position.x - blank_position.x) == 1 || Math.abs(position.y - blank_position.y) == 1){
			//Shift can work. Put the selected position's value in the blank position and put a blank in this position.
			numbers[blank_position.y][blank_position.x] = numbers[position.y][position.x];
			numbers[position.y][position.x] = 0;
			return true;
		}

		return false;
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
	}

	public enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
}