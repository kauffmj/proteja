/*
 * SWE619 
 * ASSIGNMENT 10
 * NAN LI
 * Point3
 * 
 */
package assignment10;

import java.util.HashMap;
import java.util.Map;

public class Point3 extends Point2{
	//	the z coordinate
	private int z;
	//the constructor
	public Point3(int x, int y, int z){
		super(x,y);
		this.z = z;
	}
	//Effects: return true if a object p is equal to this
	//else return false
	public boolean equals(Object p){
		if(p instanceof Point3)
			return equals((Point3) p);
		return super.equals(p);
	}
	//Effects: return true if a Point2 p is equal to this
	//else return false
	public boolean equals(Point2 p){
		if(p instanceof Point3) 
			return equals((Point3) p);
		return super.equals(p);
	}
	//Effects: return true if a Point3 p is equal to this
	//else return false
	public boolean equals(Point3 p){
		if(p == null || z != p.z)
			return false;
		return super.equals(p);
	}
	//Effects: return a hash code for this
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + z;
		return result;
	}
	public static void main(String[] args) {
		Point3 p1 = new Point3(1,2,3);
		Point2 p2 = new Point2(1,2);
		Point3 p3 = new Point3(1,2,4);
		//equals() is broken
		//Test for Transitivity
		//p1 is equal to p2 and p2 is equal to p3 but p1 is not equalt to p3
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
		System.out.println(p1.equals(p3));
		//hashCode() is broken
		//Test for breaking hash code
		//p1 is equal to p2 but they produce different hash code
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
	}
}
