package com.leepresswood.numberai;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by leepresswood on 12/17/15.
 */
public class Board{
	public byte[][] numbers;

	public Board(){
		initializeBoard();
	}

	public Board(Board to_copy){
		this.numbers = Arrays.copyOf(to_copy.numbers.clone(), to_copy.numbers.length);
	}

	public void initializeBoard(){
		numbers = new byte[3][3];
		Random random = new Random();

		//Init all with -1.
		for(byte j = 0; j < 3; j++){
			for(byte i = 0; i < 3; i++){
				numbers[j][i] = -1;
			}
		}

		//Set all numbers.
		byte counter = 0;
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

	public Position getPositionOf(byte position){
		for(byte j = 0; j < 3; j++){
			for(byte i = 0; i < 3; i++){
				if(numbers[j][i] == position){
					return new Position(i, j);
				}
			}
		}

		return null;
	}

	public void shift(Direction direction){

	}

	@Override
	public String toString(){
		String string = "";

		for(byte j = 0; j < 3; j++){
			for(byte i = 0; i < 3; i++){
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
		public byte x, y;
		public Position(byte x, byte y){
			this.x = x;
			this.y = y;
		}
	}

	public enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
}