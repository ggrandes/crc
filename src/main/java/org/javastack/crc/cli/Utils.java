package org.javastack.crc.cli;

public class Utils {
	// Original Source:
	// https://github.com/ggrandes/packer/blob/master/src/main/java/org/javastack/packer/Packer.java
	// org.javastack.packer.Packer#fromHex(String)
	static final byte[] fromHex(final String hex) throws RuntimeException {
		final int len = hex.length();
		final byte[] out = new byte[len / 2];
		for (int i = 0, j = 0; i < len; i++) {
			char c = hex.charAt(i);
			int v = 0;
			if ((c >= '0') && (c <= '9')) {
				v = (c - '0');
			} else if ((c >= 'A') && (c <= 'F')) {
				v = (c - 'A') + 0xA;
			} else if ((c >= 'a') && (c <= 'f')) {
				v = (c - 'a') + 0xA;
			} else {
				throw new RuntimeException("Invalid char: " + j);
			}
			if ((i & 1) == 0) {
				out[j] |= (v << 4);
			} else {
				out[j++] |= v;
			}
		}
		return out;
	}
}
