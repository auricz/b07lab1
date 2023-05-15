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
		int longer = Math.max(coeff.length, poly.coeff.length);
		double[] new_poly = new double[longer];
		for(int i = 0; i < longer; i++) {
			double p1_coeff = 0;
			double p2_coeff = 0;
			if(i < coeff.length) {
				p1_coeff = coeff[i];
			}
			if(i < poly.coeff.length) {
				p2_coeff = poly.coeff[i];
			}
			new_poly[i] = p1_coeff + p2_coeff;
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