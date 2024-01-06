package org.javastack.crc.example;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import org.javastack.crc.CRC;
import org.javastack.crc.Preset;

public class TestCases {
	protected static final byte[] testBytes = CRC.TEST_VALUE.getBytes();

	public static void main(String[] args) {
		boolean testCalc = true;
		boolean testTable = true;
		final List<Preset> pp = Arrays.asList(Preset.values());
		//
		if (testCalc) {
			for (Preset p : pp) {
				final CRC crc = p.getInstance(false);
				System.out.println("check (calc): " + p.params.name);
				crc.update(testBytes, 0, testBytes.length);
				final long v = crc.getValue();
				if (p.params.check != v) {
					throw new InvalidParameterException("Invalid check(" + p.params.name + ")" //
							+ " expected: " + Long.toHexString(p.params.check) //
							+ " calculated: " + Long.toHexString(v));
				}
			}
		}
		//
		if (testTable) {
			for (Preset p : pp) {
				final CRC crc = p.getInstance(true);
				System.out.println("check (table): " + p.params.name);
				crc.update(testBytes, 0, testBytes.length);
				final long v = crc.getValue();
				if (p.params.check != v) {
					throw new InvalidParameterException("Invalid check(" + p.params.name + ")" //
							+ " expected: " + Long.toHexString(p.params.check) //
							+ " calculated: " + Long.toHexString(v));
				}
			}
		}
	}
}
