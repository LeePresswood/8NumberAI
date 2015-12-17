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

		byte counter = 0;
		while(counter < 9){
			if(b.getPositionOf(counter++) == null){
				result = false;
			}
		}

		assertTrue(result);
	}

	public void testGetPositionOf() throws Exception{
		byte counter = -5;
		while(counter < 15){
			if(counter < 0 || counter > 9){
				if(b.getPositionOf(counter++) != null){
					assert false;
				}
			}
			else{
				if(!(b.getPositionOf(counter++) instanceof Board.Position)){
					assert false;
				}
			}
		}
		assert true;
	}

	public void testCanShift() throws Exception{

	}
}