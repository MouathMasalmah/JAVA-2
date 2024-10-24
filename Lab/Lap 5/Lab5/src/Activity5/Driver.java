package Activity5;

import java.io.IOException;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws IOException {
		BitOutputStream BIT = new BitOutputStream();
		BIT.writeBit('0');
		BIT.writeBit("1010011");
	}
}