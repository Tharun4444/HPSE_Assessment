package com.telstra.shortestpath.test;

import org.junit.Test;

import com.telstra.shortestpath.main.ShortestPath;

import junit.framework.Assert;

public class UnitTest {

	@Test
	public void testAssetThatExamples() {
		int[][] distances=new int[][]{{0, 12, 10, 19, 8}, 
			{12, 0, 3, 7, 2}, 
			{10, 3, 0, 6, 20}, 
			{19, 7, 6, 0, 4}, 
			{8, 2, 20, 4, 0} 
		};

		int size = 5;

	   ShortestPath path=new ShortestPath();
	   Assert.assertEquals("Success",path.shortestDistance(distances, size));
	    
	}

}
