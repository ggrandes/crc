package org.javastack.crc;

/**
 * Generic CRC calculated just-in-time, without lookup-tables, is slow, but works.
 * 
 * @see Preset
 * @see Params
 * @see CRC
 */
public class GenericCalculateCRC extends CRC {
	private final Params p;
	private long crc;

	public GenericCalculateCRC(final Params p) {
		this.p = p;
		this.reset();
	}

	@Override
	public void reset() {
		crc = p.init;
	}

	@Override
	public void update(int b) {
		if (p.refin) {
			b = reverse(b, 8);
		}
		for (int i = 0x80; i != 0; i >>= 1) {
			long bit = crc & p.msb;
			crc <<= 1;
			if ((b & i) != 0) {
				bit ^= p.msb;
			}
			if (bit != 0) {
				crc ^= p.poly;
			}
		}
	}

	@Override
	public long getValue() {
		return (((p.refout ? reverse(crc, p.width) : crc) ^ p.xorout) & p.mask);
	}

	@Override
	public Params getParams() {
		return p;
	}

	/**
	 * Simple Test
	 * 
	 * @param args ignored
	 */
	public static void main(final String[] args) {
		for (final Preset preset : Preset.values()) {
			System.out.println("Testing: " + preset.name());
			preset.params.test();
		}
		System.out.println("DONE: All right!");
	}
}
