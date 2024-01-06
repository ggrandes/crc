package org.javastack.crc;

/**
 * CRC16-XMODEM Checksum
 * poly=0x1021 init=0x0 refin=false refout=false xorout=0x0 check=0x31c3
 * <p>
 * Alias: CRC-16/ACORN, CRC-16/LTE, CRC-16/V-41-MSB, XMODEM, ZMODEM
 * 
 * <pre>
 * The polynomial code used is 0x1021 (CRC16)
 * x16+x12+x5+1
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Cyclic_redundancy_check">CRC-16-CCITT</a>
 * @see <a href="https://reveng.sourceforge.io/crc-catalogue/16.htm">crc-catalogue</a>
 * @see <a href="https://en.wikipedia.org/wiki/Mathematics_of_cyclic_redundancy_checks">Mathematics of CRC</a>
 */
public class CRC16_XMODEM extends CRC {
	private static final int poly = 0x1021;

	private int crc = 0;

	@Override
	public void reset() {
		crc = 0;
	}

	@Override
	public void update(final int b) {
		for (int j = 0; j < 8; j++) {
			final boolean bit = ((b >> (7 - j) & 1) == 1);
			final boolean c15 = ((crc >> 15 & 1) == 1);
			crc <<= 1;
			if (c15 ^ bit) {
				crc ^= poly;
			}
		}
		crc &= 0xFFFF;
	}

	@Override
	public long getValue() {
		return ((long) (crc ^ 0x0) & 0xFFFFL);
	}

	@Override
	public Params getParams() {
		return Preset.CRC_16_XMODEM.params;
	}

	/**
	 * Simple Test
	 * 
	 * @param args ignored
	 */
	public static void main(final String[] args) {
		final CRC crc = new CRC16_XMODEM();
		crc.reset();
		crc.update(CRC.TEST_VALUE.getBytes());
		System.out.println("31c3=" + Long.toHexString(crc.getValue()));
	}
}
