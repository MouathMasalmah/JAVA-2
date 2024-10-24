package Activity5;

import java.util.*;
import java.io.*;

public class BitOutputStream {
	String B="";

	BitOutputStream() {

	}

	public void writeBit(char bit) throws IOException {
		FileOutputStream F = new FileOutputStream("Activity5.txt");
		DataOutputStream D = new DataOutputStream(F);
		if (bit == '0' || bit == '1') {
			B += bit;
		} else {
			System.out.println("Enter a true value");
		}
		if (B.length() == 8) {
			D.write((Integer.parseInt(B,2)));
			B = "";
			
		}

	}

	public void writeBit(String bit) throws IOException {
		FileOutputStream F = new FileOutputStream("Activity5.txt");
		DataOutputStream D = new DataOutputStream(F);
		for (int i = 0; i < bit.length(); i++) {
			if (bit.charAt(i) == '0' || bit.charAt(i) == '1') {
				B += bit.charAt(i);
				if (B.length() == 8) {
					D.write((Integer.parseInt(B,2)));
					B = "";
					
				}
			} else {
				System.out.println("Enter a true value");
			}
		}
		
		

	}
}
