/*
 * SWE619 
 * ASSIGNMENT 10
 * NAN LI
 * Point2
 * 
 */

package assignment10;

public class Point2 {
	//Overview: represent points in two-space
	//the x coordinate
	private int x; 
	//the y coordinate
	private int y;
	//the constructor
	public Point2(int x, int y){
		this.x = x;
		this.y = y;
	}
	//Effects: return true if a object p is equal to this
	//else return false
	public boolean equals(Object p){
		if(!(p instanceof Point2))
			return false;
		return equals((Point2) p);
	}
	//Effects: return true if a Point2 type p is equal to this
	//else return false
	public boolean equals(Point2 p){
		if(!(p instanceof Point2))
			return false;
		return x == p.x && y == p.y;
	}
	//Effects: return a hash code for this
	public int hashCode(){
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}
}
