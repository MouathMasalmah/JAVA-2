import java.io.*;

import java.util.*;

public class Activity4 {
	

	public static void main(String[] args) throws IOException {
		int sum=0;
		try (FileInputStream fileInputStream = new FileInputStream("Activity33.dat");
				DataInputStream D = new DataInputStream(fileInputStream);) {
			while (D.available()>0){
				int S = D.readInt();
				if (S < 0) {
					break;
				}
				System.out.println(S);
				sum+=S;
			}
		}
		System.out.println(sum);
	}

}
