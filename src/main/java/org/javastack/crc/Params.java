package org.javastack.crc;

import java.security.InvalidParameterException;

/**
 * Parameters of a CRC Algorithm.
 */
public class Params implements Comparable<Params> {
	/**
	 * Principal algorithm name
	 */
	public final String name;
	/**
	 * width bits of the CRC (8, 16, 32, 64, etc)
	 */
	public final int width;
	/**
	 * Polynomial representation
	 */
	public final long poly;
	/**
	 * The field contains the checksum obtained when the ASCII string "123456789" is calculated
	 * 
	 * @see CRC#TEST_VALUE
	 * @see Checksum#getValue()
	 */
	public final long check;
	/**
	 * Initial CRC value
	 */
	public final long init;
	/**
	 * If true the input bytes are reflected before processed.
	 */
	public final boolean refin;
	/**
	 * If true the final register value is reflected before xorout.
	 */
	public final boolean refout;
	/**
	 * It is XORed to the final register value (after refout)
	 */
	public final long xorout;

	/**
	 * Mask derived from crc-width
	 */
	final long mask;
	/**
	 * The most significant bit=1
	 */
	final long msb;

	public Params(final String name, final int width, //
			final long poly, final long check, final long init, //
			final boolean refin, final boolean refout, final long xorout) {
		this.mask = (~0L >>> (64 - width));
		this.msb = 1L << (width - 1);
		//
		this.name = name;
		this.width = width;
		this.poly = poly & mask;
		this.check = check & mask;
		this.init = init & mask;
		this.refin = refin;
		this.refout = refout;
		this.xorout = xorout & mask;
	}

	/**
	 * Perform a simple test validation.
	 * 
	 * @throws InvalidParameterException if something is wrong.
	 * @see #check
	 * @see CRC#TEST_VALUE
	 */
	public void test() throws InvalidParameterException {
		final CRC crc = new GenericCalculateCRC(this);
		crc.update(CRC.TEST_VALUE.getBytes());
		final long v = crc.getValue();
		if (this.check != v) {
			throw new InvalidParameterException("Invalid check(" + this.name + ")" //
					+ " expected: " + Long.toHexString(this.check) //
					+ " calculated: " + Long.toHexString(v));
		}
	}

	@Override
	public int compareTo(final Params o) {
		// width, poly, init, xorout, check, refin, refout, name
		int c;
		c = Long.compare(this.width, o.width);
		if (c != 0) {
			return c;
		}
		c = Long.compare(this.poly, o.poly);
		if (c != 0) {
			return c;
		}
		c = Long.compare(this.init, o.init);
		if (c != 0) {
			return c;
		}
		c = Long.compare(this.xorout, o.xorout);
		if (c != 0) {
			return c;
		}
		c = Long.compare(this.check, o.check);
		if (c != 0) {
			return c;
		}
		c = Boolean.compare(this.refin, o.refin);
		if (c != 0) {
			return c;
		}
		c = Boolean.compare(this.refout, o.refout);
		if (c != 0) {
			return c;
		}
		return name.compareTo(o.name);
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(width) //
				^ Long.hashCode(poly) //
				^ Long.hashCode(init) //
				^ Long.hashCode(xorout) //
				^ Long.hashCode(check) //
				^ Boolean.hashCode(refin) //
				^ Boolean.hashCode(refout) //
				^ name.hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof Params)) {
			return false;
		}
		if (this == o) {
			return true;
		}
		return (this.compareTo((Params) o) == 0);
	}
}
