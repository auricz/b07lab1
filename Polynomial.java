import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Polynomial {
	double[] coeff;
	int[] power;
	
	public Polynomial() {
		this.coeff = new double[1];
		this.power = new int[1];
	}
	
	public Polynomial(double[] coeff, int[] power) {
		this.coeff = coeff;
		this.power = power;
	}

	public Polynomial(File file) {
		Polynomial temp = new Polynomial();
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String[] poly_split = buff.readLine().split("\\+");
			int term_num = 1;
			for(String segment : poly_split) {
				String[] terms = segment.split("-");
				int terms_idx = 0;
				int boost = 0;
				if(terms[0] == "") {
					String[] first_term = terms[1].split("x");
					double fir_coeff = Double.parseDouble(first_term[0]) * -1;
					int fir_pow = 0;
					if(first_term.length > 1) fir_pow = Integer.parseInt(first_term[1]);
					temp.coeff[0] = fir_coeff;
					temp.power[0] = fir_pow;
					boost = 2;
					term_num++;
				}
				for(terms_idx = boost; terms_idx < terms.length; terms_idx++) {
					String[] cur_term = terms[terms_idx].split("x");
					double cur_coeff = Double.parseDouble(cur_term[0]);
					if(terms_idx > 0) cur_coeff *= -1; 
					int cur_pow = 0;
					if(cur_term.length > 1) cur_pow = Integer.parseInt(cur_term[1]);
					if(term_num == 1) {
						temp.coeff[0] = cur_coeff;
						temp.power[0] = cur_pow;
					}
					else {
						temp.append_to_coeff(cur_coeff);
						temp.append_to_pow(cur_pow);
					}
					term_num++;
				}
			}
			buff.close();
			this.coeff = temp.coeff;
			this.power = temp.power;
		}
		catch(FileNotFoundException e) {
			System.out.println("Invalid file. Cannot instantiate new Polynomial.\n");
		}
		catch(IOException i) {
			System.out.println("If you see this, somethin ain't right.\n");
		}
	}
	
	public Polynomial add(Polynomial poly) {
		Polynomial total = new Polynomial(this.coeff, this.power);
		for(int pow : poly.power) {
			if(total.get_idx_of_pow(pow) == -1) {
				total.append_to_pow(pow);
				total.append_to_coeff(0.0);
			}
		}
		for(int poly_i = 0; poly_i < poly.power.length; poly_i++) {
			for(int total_i = 0; total_i < total.power.length; total_i++) {
				if(poly.power[poly_i] == total.power[total_i]) {
					total.coeff[total_i] += poly.coeff[poly_i];
					break;
				}
			}
		}
		total.reorder();
		return total;
	}
	
	public Polynomial multiply(Polynomial poly) {
	    Polynomial total = new Polynomial(); 
	    for(int cur_poly_i = 0; cur_poly_i < power.length; cur_poly_i++) {
	        for(int other_poly_i = 0; other_poly_i < poly.power.length; other_poly_i++) {
	            int mul_power = power[cur_poly_i] + poly.power[other_poly_i];
	            double mul_coeff = coeff[cur_poly_i] * poly.coeff[other_poly_i];
	            int total_pow_idx = total.get_idx_of_pow(mul_power);
				if(total_pow_idx == -1) {
					total.append_to_pow(mul_power);
					total.append_to_coeff(mul_coeff);
				}
				else {
					total.coeff[total_pow_idx] += mul_coeff;
				}
	        }
	    }
		total.reorder();
	    return total;
	}
	
	public double evaluate(double x) {
		double total = 0;
		for(int i = 0; i < coeff.length; i++) {
			total += coeff[i] * Math.pow(x, power[i]);
		}
		return total;
	}
	
	public boolean hasRoot(double x) {
		return (evaluate(x) == 0);
	}
	
	public void saveToFile(String filename) {
		try {
			PrintStream ps = new PrintStream(filename);
			String poly = "";
			if(coeff.length > 0) {
				poly += coeff[0];
				if(power[0] > 0) poly += "x" + power[0];
			}
			for(int i = 1; i < power.length; i++) {
				if(coeff[i] >= 0) poly += "+";
				poly += coeff[i];
				if(power[i] > 0) poly += "x" + power[i];
			}
			ps.print(poly);
			ps.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Invalid filename. Cannot save to that file.\n");
		}
	}

	private int get_idx_of_pow(int pow) {
	    int i = 0;
	    while(i < power.length) {
	        if(power[i] == pow) return i;
	        i++;
	    }
	    return -1;
	}
	
	private void append_to_pow(int x) {
		int[] longer = new int[power.length + 1];
		for(int i = 0; i < power.length; i++) {
			longer[i] = power[i];
		}
		longer[power.length] = x;
		power = longer;
	}
	
	private void append_to_coeff(double x) {
		double[] longer = new double[coeff.length + 1];
		for(int i = 0; i < coeff.length; i++) {
			longer[i] = coeff[i];
		}
		longer[coeff.length] = x;
		coeff = longer;
	}
	
	private void reorder() {
	    boolean is_sorted = false;
	    while(!is_sorted) {
	        is_sorted = true;
	        for(int i = 0; i < power.length - 1; i++) {
	            if(power[i] > power[i + 1]) {
	                is_sorted = false;
	                int left_pow = power[i];
	                double left_c = coeff[i];
	                power[i] = power[i + 1];
	                coeff[i] = coeff[i + 1];
	                power[i + 1] = left_pow;
	                coeff[i + 1] = left_c;
	            }
	        }
	    }
	}
}