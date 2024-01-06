package org.javastack.crc;

/**
 * CRC8-DVB-S2 Checksum
 * poly=0xd5 init=0x00 refin=false refout=false xorout=0x00 check=0xbc
 * <p>
 * Alias: CRC-8/DVB-S2
 * 
 * <pre>
 * The polynomial code used is 0xD5 (CRC8)
 * x8+x7+x6+x4+x2+1
 * </pre>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Cyclic_redundancy_check">CRC-8 DVB-S2</a>
 * @see <a href="https://reveng.sourceforge.io/crc-catalogue/16.htm">crc-catalogue</a>
 * @see <a href="https://en.wikipedia.org/wiki/Mathematics_of_cyclic_redundancy_checks">Mathematics of CRC</a>
 */
public class CRC8_DVB_S2 extends CRC {
	private static final int poly = 0x0D5;

	private int crc = 0;

	@Override
	public void reset() {
		crc = 0;
	}

	@Override
	public void update(final int b) {
		crc ^= b;
		for (int j = 0; j < 8; j++) {
			if ((crc & 0x80) != 0) {
				crc = ((crc << 1) ^ poly);
			} else {
				crc <<= 1;
			}
		}
		crc &= 0xFF;
	}

	@Override
	public long getValue() {
		return (crc & 0xFF);
	}

	@Override
	public Params getParams() {
		return Preset.CRC_8_DVB_S2.params;
	}

	/**
	 * Simple Test
	 * 
	 * @param args ignored
	 */
	public static void main(final String[] args) {
		final CRC crc = new CRC8_DVB_S2();
		crc.reset();
		crc.update(CRC.TEST_VALUE.getBytes());
		System.out.println("bc=" + Long.toHexString(crc.getValue()));
	}
}
