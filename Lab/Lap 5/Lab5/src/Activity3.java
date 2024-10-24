import java.io.*;

import java.util.*;

import javax.imageio.stream.FileImageOutputStream;

public class Activity3 {

	public static void main(String[] args) throws IOException {
		Random R = new Random();
		FileOutputStream F = new FileOutputStream("Activity33.dat");   
		DataOutputStream p = new DataOutputStream(F);
		for (int i = 0; i < 100; i++) {
			int RandNum = R.nextInt(101);
			p.writeInt(RandNum);
			System.out.println(RandNum);

		}
		p.close();
	}
}
