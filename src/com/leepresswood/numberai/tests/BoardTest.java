package com.leepresswood.numberai.tests;

import com.leepresswood.numberai.Board;
import junit.framework.TestCase;

/**
 * Created by leepresswood on 12/17/15.
 */
public class BoardTest extends TestCase{
	public void testInitializeBoard() throws Exception{
		Board b = new Board();
		byte counter = 0;
		while(counter != 10){
			if(b.getPositionOf(counter++) == null){
				assert false;
			}
		}

		assert true;
	}

	public void testGetPositionOf() throws Exception{
		Board b = new Board();
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

		for(byte j = 0; j < 3; j++){
			for(byte i = 0; i < 3; i++){

			}
		}
	}

	public void testCanShift() throws Exception{

	}
}