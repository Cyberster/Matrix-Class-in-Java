package com.w3epic;

public class Matrix {
	private double[][] matrix;

	public Matrix(int row, int column) {
		matrix = new double[row][column];
	}
	
	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public double[][] toArray() {
		return this.matrix;
	}
	
	public double[] getRow(int x) {
		return matrix[x];
	}
	
	public double get(int x, int y) {
		return matrix[x][y];
	}
	
	public Matrix getMatrixExcept(int x, int y) {
		Matrix result = new Matrix(this.rowCount() - 1, this.colCount() - 1);
		
		int m = 0;
		
		for (int i = 0; i < this.rowCount(); i++) {
			if (i == x) continue;
			int n = 0;
			
			for (int j = 0; j < this.colCount(); j++) {
				if (j == y) continue;
				result.set(m, n, this.get(i, j));
				n++;
			}
			m++;
		}
		
		return result;
	}
	
	public void set(int x, int y, double value) {
		matrix[x][y] = value;
	}
	
	public int rowCount() {
		return matrix.length;
	}
	
	public int colCount() {
		return matrix[0].length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < this.rowCount(); i++) {
			for (int j = 0; j < this.colCount(); j++) {
				sb.append(String.format("%.3f\t", matrix[i][j]));
				//System.out.print(matrix[i][j] + "\t");
			}
			sb.append("\n");
			//System.out.println();
		}
		
		return sb.toString();
	}
	
	public Matrix multiply(Matrix that) {
		Matrix result = null;
		
		if (this.colCount() != that.rowCount())
			return result;
		
		result = new Matrix(this.rowCount(), that.colCount());
		
		for (int i = 0; i < this.rowCount(); i++) {
			for (int j = 0; j < that.colCount(); j++) {
				double sum = 0;
				for (int k = 0; k < that.rowCount(); k++) {
					sum += this.get(i, k) * that.get(k, j);
				}
				result.set(i, j, sum);
			}
		}
		
		return result;
	}
	
	public Matrix multiply(double value) {
		Matrix result = new Matrix(this.rowCount(), this.colCount());
		
		for (int i = 0; i < this.rowCount(); i++) {
			for (int j = 0; j < this.colCount(); j++) {
				result.set(i, j, this.get(i, j) * value);
			}
		}
		
		return result;
	}
	
	public Matrix transposed() {
		Matrix result = new Matrix(this.colCount(), this.rowCount());
		
		for (int i = 0; i < this.rowCount(); i++) {
			for (int j = 0; j < this.colCount(); j++) {
				result.set(j, i, this.get(i, j));
			}
		}
		
		return result;
	}
	
	public double determinant() {
		if (this.rowCount() != this.colCount()) {
			System.out.println("Should be a square matrix.");
			return 0;
		}
		
		if (this.rowCount() == 2 && this.colCount() == 2) {
			return this.get(0, 0) * this.get(1, 1) -  this.get(0, 1) * this.get(1, 0);
		}
		
		double sum = 0;
		int sign = 1; // 1=+ve, -1=-ve
		
		for (int i = 0; i < this.colCount(); i++) {
			sum += this.get(0, i) * this.getMatrixExcept(0, i).determinant() * sign;
			sign *= -1;
		}
		
		return sum;
	}
	
	public Matrix adjointed() {
		Matrix result = null;
		
		if (this.rowCount() != this.colCount()) {
			System.out.println("Should be a square matrix.");
			return result;
		}
		
		result = new Matrix(this.colCount(), this.rowCount());
		
		int sign_i = 1;
		
		for (int i = 0; i < this.rowCount(); i++) {
			int sign_j = sign_i;
			
			for (int j = 0; j < this.colCount(); j++) {
				// note: i-j is swapped for transpose at once
				//result.set(i,  j,  this.getMatrixExcept(j, i).determinant() * sign_j);
				// or apply transpose before return
				result.set(i,  j,  this.getMatrixExcept(i, j).determinant() * sign_j);
				sign_j *= -1;
			}
			sign_i *= -1;
		}
		
		result = result.transposed();
		
		return result;
	}
	
	public Matrix inversed() {
		Matrix result = null;
		
		if (this.rowCount() != this.colCount()) {
			System.out.println("Should be a square matrix.");
			return result;
		}
		
		double detA = this.determinant();
		Matrix adjA = this.adjointed();
		
		result = adjA.multiply(1 / detA);
		
		return result;
	}

}
