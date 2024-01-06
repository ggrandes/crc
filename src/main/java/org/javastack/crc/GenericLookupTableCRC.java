package org.javastack.crc;

import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * Generic CRC based on lookup tables for speed-up.
 * 
 * @see Preset
 * @see Params
 * @see CRC
 */
public class GenericLookupTableCRC extends CRC {
	private static final HashMap<Params, long[]> CACHE = new HashMap<Params, long[]>();
	private final Params p;
	private final long[] table;
	private final long init;
	private long crc;

	public GenericLookupTableCRC(final Params p) {
		this.p = p;
		this.init = (p.refin) ? reverse(p.init, p.width) : p.init;
		this.table = initTable(p);
		this.reset();
	}

	@Override
	public void reset() {
		crc = init;
	}

	private static final long[] initTable(final Params p) {
		long[] table = null;
		synchronized (CACHE) {
			table = CACHE.get(p);
			if (table != null) {
				return table;
			}
			final Params tableCalcParams = new Params( //
					p.name, p.width, //
					p.poly, p.check, 0, //
					p.refin, p.refin, 0);
			final CRC helper = new GenericCalculateCRC(tableCalcParams);
			table = new long[256];
			for (int i = 0; i < table.length; i++) {
				helper.reset();
				helper.update(i);
				table[i] = helper.getValue();
			}
			CACHE.put(p, table);
		}
		return table;
	}

	@Override
	public void update(final int b) {
		if (p.refin) {
			crc = ((crc >>> 8) ^ table[(b ^ (int) crc) & 0xFF]);
		} else if (p.width < 8) {
			crc = table[(b ^ (((int) crc) << (8 - p.width))) & 0xFF];
		} else {
			crc = table[(b ^ ((int) (crc >>> (p.width - 8)))) & 0xFF] ^ (crc << 8);
		}
	}

	@Override
	public long getValue() {
		return (((p.refout != p.refin) ? reverse(crc, p.width) : crc) ^ p.xorout) & p.mask;
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
			final CRC crc = new GenericLookupTableCRC(preset.params);
			crc.update(CRC.TEST_VALUE.getBytes());
			final long v = crc.getValue();
			if (preset.params.check != v) {
				throw new InvalidParameterException("Invalid check(" + preset.name() + ")" //
						+ " expected: " + Long.toHexString(preset.params.check) //
						+ " calculated: " + Long.toHexString(v));
			}
		}
		System.out.println("DONE: All right!");
	}
}
