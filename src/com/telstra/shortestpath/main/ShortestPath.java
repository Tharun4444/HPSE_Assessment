package com.telstra.shortestpath.main;

import java.io.IOException;

/**
 * There are 2 possible cases in each iteration:
 *
 *
 * A) If the list of vertices is empty, return the distance between starting point and vertex.
 * B) If the list of vertices is not empty, lets decrease our problem space:
 *
 *      1) Consider each vertex in vertices as a starting point ("initial")
 *      2) As "initial" is the starting point, we have to remove it from the list of vertices
 *      3) Calculate the distance of visiting "initial" and distance of visiting the rest from it
 *      4) Return the minimum result from step 3
 */

public class ShortestPath {

	private static int minimumDistance = Integer.MAX_VALUE;
	private static String bestPath = "";


	public static void main(String args[]) throws IOException{

		int[][] distances=new int[][]{{0, 12, 10, 19, 8}, 
			{12, 0, 3, 7, 2}, 
			{10, 3, 0, 6, 20}, 
			{19, 7, 6, 0, 4}, 
			{8, 2, 20, 4, 0} 
		};

		int size = 5;

		// Initial variables to start the algorithm
		String path = "";
		int[] vertices = new int[size - 1];

		// Filling the initial vertices array with the proper values
		for (int i = 1; i < size; i++) {
			vertices[i - 1] = i;
		}

		// FIRST CALL TO THE RECURSIVE FUNCTION
		bestRoute(0, vertices, path, 0,distances);

		System.out.print("Path: " + bestPath + ". Distance = " + minimumDistance);
	}




	private static int bestRoute(int initial, int vertices[], String path, int distanceUntilHere, int[][] distances) {

		// We concatenate the current path and the vertex taken as initial
		path = path + Integer.toString(initial) + " - ";
		int length = vertices.length;
		int newDistance;


		// Exit case, if there are no more options to evaluate (last node)
		if (length == 0) {
			newDistance = distanceUntilHere + distances[initial][0];

			// If its cost is lower than the stored one
			if (newDistance < minimumDistance){
				minimumDistance = newDistance;
				bestPath = path + "0";
			}

			return (distances[initial][0]);
		}


		// If the current branch has higher cost than the stored one: stop traversing
		else if (distanceUntilHere > minimumDistance){
			return 0;
		}


		// Common case, when there are several nodes in the list
		else {

			int[][] newVertices = new int[length][(length - 1)];
			int currentDistance, childDistance;
			int bestDistance = Integer.MAX_VALUE;

			// For each of the nodes of the list
			for (int i = 0; i < length; i++) {

				// Each recursion new vertices list is constructed
				for (int j = 0, k = 0; j < length; j++, k++) {

					// The current child is not stored in the new vertices array
					if (j == i) {
						k--;
						continue;
					}
					newVertices[i][k] = vertices[j];
				}

				// Cost of arriving the current node from its parent
				currentDistance = distances[initial][vertices[i]];

				// Here the cost to be passed to the recursive function is computed
				newDistance = currentDistance + distanceUntilHere;

				// RECURSIVE CALLS TO THE FUNCTION IN ORDER TO COMPUTE THE COSTS
				childDistance = bestRoute(vertices[i], newVertices[i], path, newDistance,distances);

				// The cost of every child + the current node cost is computed
				int totalDistance = childDistance + currentDistance;

				// Finally we select from the minimum from all possible children costs
				if (totalDistance < bestDistance) {
					bestDistance = totalDistance;
				}
			}

			return (bestDistance);
		}
	}
}