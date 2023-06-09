import java.io.File;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) {
		System.out.println("========= Testing add =========");
		double[] c1 = {1,2,3,5};
		int[] pow = {0,1,2,4};
		Polynomial p1 = new Polynomial(c1, pow);
		double[] c2 = {10,4};
		int[] pow2 = {4,3};
		Polynomial p2 = new Polynomial(c2, pow2);
		Polynomial p1_add_p2 = p1.add(p2);
		for(int i = 0; i < p1_add_p2.power.length; i++) {
		    System.out.println("("+p1_add_p2.coeff[i]+")x^"+p1_add_p2.power[i]);
		}
		System.out.println("========= Testing evaluate =========");
		double p1_eval = p1.evaluate(4);
		System.out.println(p1_eval);
		System.out.println("========= Testing hasRoot =========");
		double[] cr = {6,-2,5,-9};
		int[] powr = {0,1,3,4};
		Polynomial pr = new Polynomial(cr, powr);
		System.out.println(pr.hasRoot(1));
		System.out.println("========= Testing multiply =========");
		double[] c3 = {-1, 2, 1};
		int[] pow3 = {0, 1, 2};
		double[] c4 = {6, -3, 2};
		Polynomial p3 = new Polynomial(c3, pow3);
		Polynomial p4 = new Polynomial(c4, pow3);
		Polynomial p3_mul_p4 = p3.multiply(p4);
		for(int i = 0; i < p3_mul_p4.power.length; i++) {
		    System.out.println("("+p3_mul_p4.coeff[i]+")x^"+p3_mul_p4.power[i]);
		}
		// System.out.println("========= Testing file constructor =========");
		// File f = new File("C:\\Users\\Public\\c.txt");
		// Polynomial p5 = new Polynomial(f);
		// for(int i = 0; i < p5.power.length; i++) {
		//     System.out.println("("+p5.coeff[i]+")x^"+p5.power[i]);
		// }
		// System.out.println("========= Testing saveToFile =========");
		// p5.saveToFile("C:\\Users\\Public\\d.txt");
	}
}