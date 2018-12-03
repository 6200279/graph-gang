package GraphColoring;
//couldn't seem to figure out what our version of the ColEdge class was, or if we used that (in case we CAN use it, then it's fine for this class as well! 
//It should work at least, but it does have to be connected to the gui and there have to be some things done
//such as: set a warning when the vertex can't be coloured a certain colour
//when the time is up, end the game
//clear all the colours in the game as well, when this one is cleared
//I hope this class is useful, at least!
//pretty similar to the first one, has some different implementations as well, i.e. calculate the score (which is still basic) and count the number of colours

public class GameModeTwo { //upper bound in a fixed amount of time, so the one that reaches the chromatic number
	private int n; //will be the number of vertices
	private ColEdge[] e; //will be the one that exists when running Steven's readgraph one, didn't know how we called it
	private int[] colours;;

		public GameModeTwo(int numberOfVertices, int numberOfEdges, ColEdge[] e){//the same as the one in gamemodeone
				n = numberOfVertices;
				this.e = e; //the one you read in from the file (ReadGraph, Steven's, I don't know which one this is in our file, can be modified later)
				colours = new int[n];
		}
					public boolean checkIfPossible(int nrOfVertix, int colourChosen){
						boolean possible = true;
							for (int i = 0; i < e.length; i++) {//check whether there's a vertex with the same colour connected to this vertex
								if (e[i].u == nrOfVertix) {
									int checking = e[i].v;
									if (colours[checking] == colourChosen) {
										possible = false;
										break;
									}
								}
								if (e[i].v == nrOfVertix) {
									int checking = e[i].u;
									if (colours[checking] == colourChosen) {
										possible = false;
										break;
									}
								}
							}
							if(possible) {
							colours[nrOfVertix] = colourChosen; //add the colour at the right index to the colours array
							}
					return possible;//if it's false, it should give an error (in the interface)
					}
					
		public void clearOne(int numberOfVertix) {//to clear a vertex at a certain index of the array
			colours[numberOfVertix] =0;
		}
					
		public void clearAll() { //to clear the whole array
			for (int i = 0; i < colours.length; i++) {
				colours[i] = 0;
			}
		}
		
		public int coloursUsed() {
			int coloursUsed = 0;
			for (int i = 1; i < colours.length; i++) {//max number of colours is the length of the colours array
				for (int j = 0; j < colours.length; j++) {//checks if a certain colour is used
					if (colours[j] ==i){
						coloursUsed++;
						break;
					}
					else if (colours[j] ==0) {
						coloursUsed++;//every vertex that is not coloured will count as an extra colour
						colours[j] = -1;//will not be registered as a one, so it won't be added multiple times
					}
				}
			}
			return coloursUsed;
		}
		
		public int score(int coloursUsed, int timesHintPressed) {
			int score = 1000 * (n^2); //if there are more vertices, the total score can be higher, because there are more colours as well, make it even bigger
			score = score / (coloursUsed*timesHintPressed); // a simple method to calculate someone's score
			return score;
		}
}