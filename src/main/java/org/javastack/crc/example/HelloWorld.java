package org.javastack.crc.example;

import org.javastack.crc.CRC;
import org.javastack.crc.CRC32C;
import org.javastack.crc.GenericCalculateCRC;
import org.javastack.crc.Params;
import org.javastack.crc.Preset;

public class HelloWorld {
	public static void main(final String[] args) {
		CRC crc = null;

		// Using class
		crc = new CRC32C();
		crc.reset();
		crc.update(CRC.TEST_VALUE.getBytes());
		System.out.println("e3069283=" + Long.toHexString(crc.getValue()));

		// Using a Preset
		crc = Preset.CRC_64_ECMA_182.getInstance(true); // use Lookup-table
		crc.reset();
		crc.update(CRC.TEST_VALUE.getBytes());
		System.out.println("6c40df5f0b497347=" + Long.toHexString(crc.getValue()));

		// Using Custom Algorithm/Params
		Params custom = new Params("CRC-32/SATA", 32, // name, bits
				0x04c11db7, 0xcf72afe8, 0x52325032, // poly, check, init
				false, false, 0x0); // refin, refout, xorout
		crc = new GenericCalculateCRC(custom); // no lookup-table (slow)
		crc.reset();
		crc.update(CRC.TEST_VALUE.getBytes());
		System.out.println("calc=" + Long.toHexString(crc.getValue()));
		System.out.println("want=" + Long.toHexString(crc.getParams().check));
	}
}
