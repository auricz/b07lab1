public class Polynomial {
	double[] coeff;
	
	public Polynomial() {
		double[] arr = {0.0}; 
		coeff = arr;
	}
	
	public Polynomial(double[] arr) {
		coeff = arr;
	}
	
	public Polynomial add(Polynomial poly) {
		int cur_poly_len = coeff.length;
		int other_poly_len = poly.coeff.length;
		double[] p1_coeff = coeff;
		double[] p2_coeff = poly.coeff;
		if(cur_poly_len < other_poly_len) {
			double[] new_coeff = new double[other_poly_len];
			for(int i = 0; i < other_poly_len; i++) {
				double cur_coeff = 0;
				if(i < cur_poly_len) {
					cur_coeff = coeff[i];
				}
				new_coeff[i] = cur_coeff;
			}
			p1_coeff = new_coeff;
		}
		if(other_poly_len < cur_poly_len) {
			double[] new_coeff = new double[cur_poly_len];
			for(int i = 0; i < cur_poly_len; i++) {
				double cur_coeff = 0;
				if(i < other_poly_len) {
					cur_coeff = poly.coeff[i];
				}
				new_coeff[i] = cur_coeff;
			}
			p2_coeff = new_coeff;
		}
		double[] new_poly = new double[Math.max(cur_poly_len, other_poly_len)];
		for(int i = 0; i < Math.max(cur_poly_len, other_poly_len); i++) {
			new_poly[i] = p1_coeff[i] + p2_coeff[i];
		}
		return new Polynomial(new_poly);
	}
	
	public double evaluate(double x) {
		double total = 0;
		for(int i = 0; i < coeff.length; i++) {
			total += coeff[i] * Math.pow(x, i);
		}
		return total;
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
	}
}