package com.w3epic;

public class Main {

	public static void main(String[] args) {
		Matrix a = new Matrix(2, 3);
		a.setMatrix(new double[][] {{1, 2, 3}, {4, 5, 6}});
		Matrix b = new Matrix(3, 2);
		b.setMatrix(new double[][] {{1, 2}, {3, 4}, {5, 6}});
		
		
		System.out.println(a);
		System.out.println(b);
		System.out.println("multiply: \n" + a.multiply(b));
		System.out.println("transpose: \n" + a.transposed());
		
		Matrix x = new Matrix(5, 3);
		x.setMatrix(new double[][] {
			{1, 5, 7}, 
			{1, 6, 6}, 
			{1, 7, 4}, 
			{1, 8, 5}, 
			{1, 9, 6}
		});
		
		Matrix d = new Matrix(3, 3);
		d.setMatrix(new double[][] {
			{2, 6, 1}, 
			{-15, 15, -7}, 
			{1, 8, -2}
		});
		
		Matrix e = new Matrix(3, 3);
		e.setMatrix(new double[][] {
			{1, 4, 0}, 
			{-1, 2, 2}, 
			{0, 0, 2}
		});

		Matrix f = new Matrix(3, 3);
		f.setMatrix(new double[][] {
			{3, 0, 2}, 
			{2, 0, -2}, 
			{0, 1, 1}
		});
		
		System.out.println(x.transposed().multiply(x));
		System.out.println(x.getMatrixExcept(4, 2));
		System.out.println(d.determinant());
		System.out.println(e.adjointed());
		System.out.println(e.multiply(1/2.0));
		System.out.println(f.inversed());

	}

}
