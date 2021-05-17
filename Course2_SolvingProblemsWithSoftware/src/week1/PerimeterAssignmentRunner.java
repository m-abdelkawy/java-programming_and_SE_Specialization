package week1;

import edu.duke.*;
import java.io.File;
import java.util.ArrayList;

public class PerimeterAssignmentRunner {
	public double getPerimeter(Shape s) {
		// Start with totalPerim = 0
		double totalPerim = 0.0;
		// Start wth prevPt = the last point
		Point prevPt = s.getLastPoint();
		// For each point currPt in the shape,
		for (Point currPt : s.getPoints()) {
			// Find distance from prevPt point to currPt
			double currDist = prevPt.distance(currPt);
			// Update totalPerim by currDist
			totalPerim = totalPerim + currDist;
			// Update prevPt to be currPt
			prevPt = currPt;
		}
		// totalPerim is the answer
		return totalPerim;
	}

	public int getNumPoints(Shape s) {
		Iterable<Point> itr = s.getPoints();
		int i = 0;
		for (Point point : itr) {
			i++;
		}
		return i;
	}

	public double getAverageLength(Shape s) {
		double totalPerim = 0;
		double avg = 0;
		int distCount = 0;
		Point prevPt = s.getLastPoint();
		for (Point currPt : s.getPoints()) {
			double currDist = prevPt.distance(currPt);
			distCount++;
			totalPerim += currDist;
			prevPt = currPt;
		}
		avg = totalPerim / distCount;
		return avg;
	}

	public double getLargestSide(Shape s) {
		double largestSide = 0;
		Point prevPt = s.getLastPoint();
		for (Point currPt : s.getPoints()) {
			double currDist = prevPt.distance(currPt);
			if (currDist > largestSide)
				largestSide = currDist;
			prevPt = currPt;
		}
		return largestSide;
	}

	public double getLargestX(Shape s) {
		double largestX = Double.MIN_VALUE;
		for (Point currPt : s.getPoints()) {
			double currX = currPt.getX();
			if (currX > largestX)
				largestX = currX;
		}
		return largestX;
	}

	public double getLargestPerimeterMultipleFiles() {
		double largestPerim = 0;
		DirectoryResource dr = new DirectoryResource();
		FileResource fr = null;
		for (File f : dr.selectedFiles()) {
			fr = new FileResource(f);
			Shape s = new Shape(fr);
			double currPerim = getPerimeter(s);
			
			//for testing - to Delete later
			System.out.println(String.format("File: %s  Perimeter: %f" , f.getName(), currPerim));
			
			if (currPerim > largestPerim)
				largestPerim = currPerim;
		}
		return largestPerim;
	}

	public String getFileWithLargestPerimeter() {
		double largestPerim = 0;
		File fileWithLargestPerim = null;

		DirectoryResource dr = new DirectoryResource();
		FileResource fr = null;
		for (File f : dr.selectedFiles()) {
			fr = new FileResource(f);
			Shape s = new Shape(fr);
			double currPerim = getPerimeter(s);
			if (currPerim > largestPerim) {
				largestPerim = currPerim;
				fileWithLargestPerim = f;
			}
		}
		return fileWithLargestPerim.getName();
	}

	public void testPerimeter() {
		FileResource fr = new FileResource();
		Shape s = new Shape(fr);

		// 02. print number of points in the file
		System.out.println("Number of points: " + getNumPoints(s));

		// 04. print average length
		System.out.println("Average side length: " + getAverageLength(s));

		// 06. print largest side
		System.out.println("Longest side: " + getLargestSide(s));

		// 08. Print largest X-Coordinate in the file
		System.out.println("Largest X corrdinate: " + getLargestX(s));

		double length = getPerimeter(s);
		System.out.println("perimeter = " + length);
	}

	public void testPerimeterMultipleFiles() {
		// 02-02 print largest perimeter
		System.out.println("Largest Perimeter: " + getLargestPerimeterMultipleFiles());
		
		//02-4 print name of the file with the largest perimeter
		System.out.println("File with the largest perimeter: " + getFileWithLargestPerimeter());
	}

	public void testFileWithLargestPerimeter() {
		// Put code here
	}

	// This method creates a triangle that you can use to test your other methods
	public void triangle() {
		Shape triangle = new Shape();
		triangle.addPoint(new Point(0, 0));
		triangle.addPoint(new Point(6, 0));
		triangle.addPoint(new Point(3, 6));
		for (Point p : triangle.getPoints()) {
			System.out.println(p);
		}
		double peri = getPerimeter(triangle);
		System.out.println("perimeter = " + peri);
	}

	// This method prints names of all files in a chosen folder that you can use to
	// test your other methods
	public void printFileNames() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			System.out.println(f);
		}
	}

	public static void main(String[] args) {
		PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
		//pr.testPerimeter();
		pr.testPerimeterMultipleFiles();
	}
}
