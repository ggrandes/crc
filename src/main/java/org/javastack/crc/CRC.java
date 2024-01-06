package org.javastack.crc;

/**
 * Abstraction of CRC classes
 * 
 * @see Preset
 * @see Params
 * @see GenericCalculateCRC
 * @see GenericLookupTableCRC
 * @see CRC32
 * @see CRC64
 */
public abstract class CRC implements Checksum {
	/**
	 * Reference input value in {@link #update(byte[])} and {@link Params#check}.
	 * Use with {@link String#getBytes()}
	 */
	public static final String TEST_VALUE = "123456789";

	@Override
	public void update(final byte[] buffer, final int offset, int length) {
		for (int i = offset; length > 0; length--) {
			final int b = buffer[i++];
			update(b);
		}
	}

	@Override
	public void update(final byte[] buffer) {
		for (int i = 0; i < buffer.length; i++) {
			final int b = buffer[i];
			update(b);
		}
	}

	@Override
	public void update(final byte b) {
		update((int) b);
	}

	/**
	 * Reverse input as described:
	 * 
	 * <pre>
	 *  Example of irreducible polynomial (CRC32):
	 *     1    + x    + x^2  + x^4  + x^5  + x^7  + x^8  +
	 *     x^10 + x^11 + x^12 + x^16 + x^22 + x^23 + x^26
	 *
	 *      0000 0100 1100 0001 0001 1101 1011 0111
	 *  0x   0    4    C    1    1    D    B    7
	 *
	 *  The reverse of this polynomial is
	 *      0000 0010 0011 1000 1000 1011 1101 1110
	 *       0    2    3    8    8    B    D    E
	 *  And the usable version (return value) is
	 *  0x   E    D    B    8    8    3    2    0
	 * </pre>
	 * 
	 * @param in to reverse
	 * @param width in bits
	 * @return reversed data
	 */
	protected static int reverse(final int in, final int width) {
		final int rshift = (32 - width);
		final int mask = (~0 >>> rshift);
		final int rev = Integer.reverse(in);
		return (rev >>> rshift) & mask;
	}

	/**
	 * Reverse input as described in {@link #reverse(int, int)}
	 * 
	 * @param in to reverse
	 * @param width in bits
	 * @return reversed data
	 */
	protected static long reverse(final long in, final int width) {
		final int rshift = (64 - width);
		final long mask = (~0L >>> rshift);
		final long rev = Long.reverse(in);
		return (rev >>> rshift) & mask;
	}

	/**
	 * Simple Test
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		System.out.println("edb88320=" + Integer.toHexString(reverse(0x04c11db7, 32))); // CRC-32-ISO-HDLC
		System.out.println("c96c5795d7870f42=" + Long.toHexString(reverse(0x42f0e1eba9ea3693L, 64))); // CRC-64-ECMA
	}
}
