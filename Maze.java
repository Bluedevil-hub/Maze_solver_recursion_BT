
//My name is Harshil Mehta

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/

import java.util.Stack;
import java.util.ArrayList;



public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        
        int col_num = maze.getNCols();
        int row_num = maze.getNRows();
        

        //check for x znd y are 0
        if(x < 0 || y < 0 ){
            return false;}

        //check for x y are greater than matrix size
        else if(x >= col_num || y >= row_num){
            return false;}

        //Check for box in path
        else if(maze.getColor(x, y) != NON_BACKGROUND ) {
    		return false;}
        //Check for exit cell
        else if(x == col_num -1 && y == row_num -1) {
            maze.recolor(x, y, PATH);
            return true;}
        
        else {
            maze.recolor(x, y, PATH);
            if(findMazePath(x-1, y) || findMazePath(x+1, y) || findMazePath(x, y-1) ||findMazePath(x, y+1)) {
                    return true;
                } 
            else {maze.recolor(x , y, TEMPORARY);
                    return false;}
        }


        
    
    }

    // ADD METHOD FOR PROBLEM 2 HERE

    public ArrayList <ArrayList<PairInt>>findAllMazePaths( int  x ,  int  y )  {
    	ArrayList <ArrayList<PairInt>>  result  =  new  ArrayList <>();  
    	Stack <PairInt>  trace  =  new Stack <>();  
    	findMazePathStackBased (0 ,0 , result , trace ); 
    	return result ;
    } 

    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result,Stack<PairInt> trace){
    	int col_num = maze.getNCols(); 
    	int rows_num = maze.getNRows();

        if (x < 0 || y < 0 || x > col_num - 1 || y > rows_num - 1 || 
				(!maze.getColor(x, y).equals(NON_BACKGROUND))){
			return;}
        
        else if(x > col_num- 1 || y > rows_num - 1){
            return;
        }
        else if((!maze.getColor(x, y).equals(NON_BACKGROUND))){
            return;
        }
        else if (x == col_num - 1 && y == rows_num - 1) {
			
			PairInt lastnode = new PairInt(x,y);
			trace.push(lastnode);
			ArrayList<PairInt> allElements_list = new ArrayList<>(); 
			allElements_list.addAll(trace);
			result.add(allElements_list);
			trace.pop(); 
			maze.recolor(x, y, NON_BACKGROUND); 
			return;
		} 
		else {

			PairInt lastnode = new PairInt(x,y);
			trace.push(lastnode);
			maze.recolor(x, y, PATH);
			findMazePathStackBased(x-1, y, result, trace);
			findMazePathStackBased(x+1, y, result, trace);
			findMazePathStackBased(x, y-1, result, trace);
			findMazePathStackBased(x, y+1, result, trace);
			maze.recolor(x, y, NON_BACKGROUND);
			trace.pop();
			return;}
    }


    // ADD METHOD FOR PROBLEM 3 HERE

    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	
        int min_val = Integer.MAX_VALUE;
        int index = 0;
        
        ArrayList<ArrayList<PairInt>> totalpaths_value = new ArrayList<>();
        totalpaths_value = findAllMazePaths(x, y);

        //looping all paths
        for (int j = 0; j < totalpaths_value.size(); j++) {
        	int temp = min_val;
        	min_val = Math.min(min_val, totalpaths_value.get(j).size());
        	if(temp != min_val) {
        		index = j;
        	}
        }

        //if no elements then return empty
        if(totalpaths_value.size() == 0) return new ArrayList<PairInt>();
        return totalpaths_value.get(index);
    }




    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
