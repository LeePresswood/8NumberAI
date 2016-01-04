package com.leepresswood.numberai.tests;

import com.leepresswood.numberai.Board;
import junit.framework.TestCase;

/**
 * Created by leepresswood on 12/17/15.
 */
public class BoardTest extends TestCase{
	Board b = new Board();

	public void testInitializeBoard() throws Exception{
		boolean result = true;
		int counter = 0;
		while(counter < 9){
			if(b.getPositionOf(counter++) == null){
				result = false;
			}
		}

		assertTrue(result);
	}

	public void testGetPositionOfOutOfBounds() throws Exception{
		boolean result = true;
		int counter = -5;
		while(counter < 15){
			if(counter < 0 || counter > 8){
				if(b.getPositionOf(counter) != null){
					result = false;
				}
			}
			counter++;
		}

		assertTrue(result);
	}

	public void testGetPositionOfInBounds() throws Exception{
		boolean result = true;
		int counter = 0;
		while(counter < 9){
			if(b.getPositionOf(counter) == null){
				result = false;
			}
			counter++;
		}

		assertTrue(result);
	}

	public void testShiftOnZero() throws Exception{
		assertFalse(b.shift(b.getPositionOf(0)));
	}

	public void testShiftOnANumberNotNextToBlank() throws Exception{
		Board.Position position_zero = b.getPositionOf(0);
		int bad_x = position_zero.x - 1;
		if(bad_x < 0){
			bad_x = 2;
		}
		int bad_y = position_zero.y - 1;
		if(bad_y < 0){
			bad_y = 2;
		}

		assertFalse(b.shift(b.getPositionOf(b.numbers[bad_y][bad_x])));
	}

	public void testShiftOnANumberNextToBlank() throws Exception{
		Board.Position position_zero = b.getPositionOf(0);
		int good_x = position_zero.x - 1;
		if(good_x < 0){
			good_x = 1;
		}
		int good_y = position_zero.y;

		assertTrue(b.shift(b.getPositionOf(b.numbers[good_y][good_x])));
	}

	public void testManhattanDistanceOfSamePosition() throws Exception{
		Board.Position position = b.getPositionOf(3);
		assertTrue(b.getManhattanDistanceBetween(position, position) == 0);
	}

	public void testManhattanDistanceOfDifferentPosition() throws Exception{
		Board.Position position1 = b.getPositionOf(3);
		Board.Position position2 = b.getPositionOf(4);
		assertFalse(b.getManhattanDistanceBetween(position1, position2) == 0);
	}
}