package org.javastack.crc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Predefined CRC Algorithms and associated Parameters.
 */
public enum Preset {
	// @formatter:off
	/* name, width, poly, check, init, refin, refout, xorout */
	CRC_3_GSM("CRC-3/GSM", 3, 0x3, 0x4, 0, false, false, 0x7), //
	CRC_3_ROHC("CRC-3/ROHC", 3, 0x3, 0x6, 0x7, true, true, 0), //
	CRC_4_G_704("CRC-4/G-704", 4, 0x3, 0x7, 0, true, true, 0), //
	CRC_4_INTERLAKEN("CRC-4/INTERLAKEN", 4, 0x3, 0xb, ~0, false, false, ~0), //
	CRC_5_USB("CRC-5/USB", 5, 0x05, 0x19, 0x1f, true, true, 0x1f), //
	CRC_5_EPC_C1G2("CRC-5/EPC-C1G2", 5, 0x09, 0, 0x09, false, false, 0), //
	CRC_5_G_704("CRC-5/G-704", 5, 0x15, 0x07, 0, true, true, 0), //
	CRC_6_G_704("CRC-6/G-704", 6, 0x03, 0x06, 0, true, true, 0), //
	CRC_6_CDMA2000_A("CRC-6/CDMA2000-A", 6, 0x27, 0x0d, 0x3f, false, false, 0), //
	CRC_6_CDMA2000_B("CRC-6/CDMA2000-B", 6, 0x07, 0x3b, 0x3f, false, false, 0), //
	CRC_6_DARC("CRC-6/DARC", 6, 0x19, 0x26, 0, true, true, 0), //
	CRC_6_GSM("CRC-6/GSM", 6, 0x2f, 0x13, 0, false, false, 0x3f), //
	CRC_7_MMC("CRC-7/MMC", 7, 0x09, 0x75, 0, false, false, 0), //
	CRC_7_UMTS("CRC-7/UMTS", 7, 0x45, 0x61, 0, false, false, 0), //
	CRC_7_ROHC("CRC-7/ROHC", 7, 0x4f, 0x53, 0x7f, true, true, 0), //
	CRC_8_SMBUS("CRC-8/SMBUS", 8, 0x07, 0xf4, 0, false, false, 0), //
	CRC_8_I_432_1("CRC-8/I-432-1", 8, 0x07, 0xa1, 0, false, false, 0x55), //
	CRC_8_ROHC("CRC-8/ROHC", 8, 0x07, 0xd0, ~0, true, true, 0), //
	CRC_8_GSM_A("CRC-8/GSM-A", 8, 0x1d, 0x37, 0, false, false, 0), //
	CRC_8_MIFARE_MAD("CRC-8/MIFARE-MAD", 8, 0x1d, 0x99, 0xc7, false, false, 0), //
	CRC_8_I_CODE("CRC-8/I-CODE", 8, 0x1d, 0x7e, 0xfd, false, false, 0), //
	CRC_8_HITAG("CRC-8/HITAG", 8, 0x1d, 0xb4, ~0, false, false, 0), //
	CRC_8_SAE_J1850("CRC-8/SAE-J1850", 8, 0x1d, 0x4b, ~0, false, false, ~0), //
	CRC_8_TECH_3250("CRC-8/TECH-3250", 8, 0x1d, 0x97, ~0, true, true, 0), //
	CRC_8_OPENSAFETY("CRC-8/OPENSAFETY", 8, 0x2f, 0x3e, 0, false, false, 0), //
	CRC_8_AUTOSAR("CRC-8/AUTOSAR", 8, 0x2f, 0xdf, ~0, false, false, ~0), //
	CRC_8_MAXIM_DOW("CRC-8/MAXIM-DOW", 8, 0x31, 0xa1, 0, true, true, 0), //
	CRC_8_NRSC_5("CRC-8/NRSC-5", 8, 0x31, 0xf7, ~0, false, false, 0), //
	CRC_8_DARC("CRC-8/DARC", 8, 0x39, 0x15, 0, true, true, 0), //
	CRC_8_GSM_B("CRC-8/GSM-B", 8, 0x49, 0x94, 0, false, false, ~0), //
	CRC_8_LTE("CRC-8/LTE", 8, 0x9b, 0xea, 0, false, false, 0), //
	CRC_8_WCDMA("CRC-8/WCDMA", 8, 0x9b, 0x25, 0, true, true, 0), //
	CRC_8_CDMA2000("CRC-8/CDMA2000", 8, 0x9b, 0xda, ~0, false, false, 0), //
	CRC_8_BLUETOOTH("CRC-8/BLUETOOTH", 8, 0xa7, 0x26, 0, true, true, 0), //
	CRC_8_DVB_S2("CRC-8/DVB-S2", 8, 0xd5, 0xbc, 0, false, false, 0), //
	CRC_10_GSM("CRC-10/GSM", 10, 0x175, 0x12a, 0, false, false, 0x3ff), //
	CRC_10_ATM("CRC-10/ATM", 10, 0x233, 0x199, 0, false, false, 0), //
	CRC_10_CDMA2000("CRC-10/CDMA2000", 10, 0x3d9, 0x233, 0x3ff, false, false, 0), //
	CRC_11_UMTS("CRC-11/UMTS", 11, 0x307, 0x061, 0, false, false, 0), //
	CRC_11_FLEXRAY("CRC-11/FLEXRAY", 11, 0x385, 0x5a3, 0x01a, false, false, 0), //
	CRC_12_DECT("CRC-12/DECT", 12, 0x80f, 0xf5b, 0, false, false, 0), //
	CRC_12_UMTS("CRC-12/UMTS", 12, 0x80f, 0xdaf, 0, false, true, 0), //
	CRC_12_GSM("CRC-12/GSM", 12, 0xd31, 0xb34, 0, false, false, ~0), //
	CRC_12_CDMA2000("CRC-12/CDMA2000", 12, 0xf13, 0xd4d, ~0, false, false, 0), //
	CRC_13_BBC("CRC-13/BBC", 13, 0x1cf5, 0x04fa, 0, false, false, 0), //
	CRC_14_DARC("CRC-14/DARC", 14, 0x0805, 0x082d, 0, true, true, 0), //
	CRC_14_GSM("CRC-14/GSM", 14, 0x202d, 0x30ae, 0, false, false, 0x3fff), //
	CRC_15_CAN("CRC-15/CAN", 15, 0x4599, 0x059e, 0, false, false, 0), //
	CRC_15_MPT1327("CRC-15/MPT1327", 15, 0x6815, 0x2566, 0, false, false, 0x0001), //
	CRC_16_DECT_X("CRC-16/DECT-X", 16, 0x0589, 0x007f, 0, false, false, 0), //
	CRC_16_DECT_R("CRC-16/DECT-R", 16, 0x0589, 0x007e, 0, false, false, 0x0001), //
	CRC_16_NRSC_5("CRC-16/NRSC-5", 16, 0x080b, 0xa066, ~0, true, true, 0), //
	CRC_16_XMODEM("CRC-16/XMODEM", 16, 0x1021, 0x31c3, 0, false, false, 0), //
	CRC_16_GSM("CRC-16/GSM", 16, 0x1021, 0xce3c, 0, false, false, ~0), //
	CRC_16_KERMIT("CRC-16/KERMIT", 16, 0x1021, 0x2189, 0, true, true, 0), //
	CRC_16_SPI_FUJITSU("CRC-16/SPI-FUJITSU", 16, 0x1021, 0xe5cc, 0x1d0f, false, false, 0), //
	CRC_16_TMS37157("CRC-16/TMS37157", 16, 0x1021, 0x26b1, 0x89ec, true, true, 0), //
	CRC_16_RIELLO("CRC-16/RIELLO", 16, 0x1021, 0x63d0, 0xb2aa, true, true, 0), //
	CRC_16_ISO_IEC_14443_3_A("CRC-16/ISO-IEC-14443-3-A", 16, 0x1021, 0xbf05, 0xc6c6, true, true, 0), //
	CRC_16_IBM_3740("CRC-16/IBM-3740", 16, 0x1021, 0x29b1, ~0, false, false, 0), //
	CRC_16_GENIBUS("CRC-16/GENIBUS", 16, 0x1021, 0xd64e, ~0, false, false, ~0), //
	CRC_16_MCRF4XX("CRC-16/MCRF4XX", 16, 0x1021, 0x6f91, ~0, true, true, 0), //
	CRC_16_IBM_SDLC("CRC-16/IBM-SDLC", 16, 0x1021, 0x906e, ~0, true, true, ~0), //
	CRC_16_PROFIBUS("CRC-16/PROFIBUS", 16, 0x1dcf, 0xa819, ~0, false, false, ~0), //
	CRC_16_EN_13757("CRC-16/EN-13757", 16, 0x3d65, 0xc2b7, 0, false, false, ~0), //
	CRC_16_DNP("CRC-16/DNP", 16, 0x3d65, 0xea82, 0, true, true, ~0), //
	CRC_16_OPENSAFETY_A("CRC-16/OPENSAFETY-A", 16, 0x5935, 0x5d38, 0, false, false, 0), //
	CRC_16_OPENSAFETY_B("CRC-16/OPENSAFETY-B", 16, 0x755b, 0x20fe, 0, false, false, 0), //
	CRC_16_M17("CRC-16/M17", 16, 0x5935, 0x772b, ~0, false, false, 0), //
	CRC_16_LJ1200("CRC-16/LJ1200", 16, 0x6f63, 0xbdf4, 0, false, false, 0), //
	CRC_16_UMTS("CRC-16/UMTS", 16, 0x8005, 0xfee8, 0, false, false, 0), //
	CRC_16_ARC("CRC-16/ARC", 16, 0x8005, 0xbb3d, 0, true, true, 0), //
	CRC_16_MAXIM_DOW("CRC-16/MAXIM-DOW", 16, 0x8005, 0x44c2, 0, true, true, ~0), //
	CRC_16_DDS_110("CRC-16/DDS-110", 16, 0x8005, 0x9ecf, 0x800d, false, false, 0), //
	CRC_16_CMS("CRC-16/CMS", 16, 0x8005, 0xaee7, ~0, false, false, 0), //
	CRC_16_MODBUS("CRC-16/MODBUS", 16, 0x8005, 0x4b37, ~0, true, true, 0), //
	CRC_16_USB("CRC-16/USB", 16, 0x8005, 0xb4c8, ~0, true, true, ~0), //
	CRC_16_T10_DIF("CRC-16/T10-DIF", 16, 0x8bb7, 0xd0db, 0, false, false, 0), //
	CRC_16_TELEDISK("CRC-16/TELEDISK", 16, 0xa097, 0x0fb3, 0, false, false, 0), //
	CRC_16_CDMA2000("CRC-16/CDMA2000", 16, 0xc867, 0x4c06, ~0, false, false, 0), //
	CRC_17_CAN_FD("CRC-17/CAN-FD", 17, 0x1685b, 0x04f03, 0, false, false, 0), //
	CRC_21_CAN_FD("CRC-21/CAN-FD", 21, 0x102899, 0x0ed841, 0, false, false, 0), //
	CRC_24_BLE("CRC-24/BLE", 24, 0x00065b, 0xc25a56, 0x555555, true, true, 0), //
	CRC_24_INTERLAKEN("CRC-24/INTERLAKEN", 24, 0x328b63, 0xb4f3e6, ~0, false, false, ~0), //
	CRC_24_FLEXRAY_A("CRC-24/FLEXRAY-A", 24, 0x5d6dcb, 0x7979bd, 0xfedcba, false, false, 0), //
	CRC_24_FLEXRAY_B("CRC-24/FLEXRAY-B", 24, 0x5d6dcb, 0x1f23b8, 0xabcdef, false, false, 0), //
	CRC_24_LTE_B("CRC-24/LTE-B", 24, 0x800063, 0x23ef52, 0, false, false, 0), //
	CRC_24_OS_9("CRC-24/OS-9", 24, 0x800063, 0x200fa5, ~0, false, false, ~0), //
	CRC_24_LTE_A("CRC-24/LTE-A", 24, 0x864cfb, 0xcde703, 0, false, false, 0), //
	CRC_24_OPENPGP("CRC-24/OPENPGP", 24, 0x864cfb, 0x21cf02, 0xb704ce, false, false, 0), //
	CRC_30_CDMA("CRC-30/CDMA", 30, 0x2030b9c7, 0x04c34abf, 0x3fffffff, false, false, 0x3fffffff), //
	CRC_31_PHILIPS("CRC-31/PHILIPS", 31, 0x04c11db7, 0x0ce9e46c, 0x7fffffff, false, false, 0x7fffffff), //
	CRC_32_XFER("CRC-32/XFER", 32, 0x000000af, 0xbd0be338, 0, false, false, 0), //
	CRC_32_CKSUM("CRC-32/CKSUM", 32, 0x04c11db7, 0x765e7680, 0, false, false, ~0), //
	CRC_32_MPEG_2("CRC-32/MPEG-2", 32, 0x04c11db7, 0x0376e6e7, ~0, false, false, 0), //
	CRC_32_BZIP2("CRC-32/BZIP2", 32, 0x04c11db7, 0xfc891918, ~0, false, false, ~0), //
	CRC_32_JAMCRC("CRC-32/JAMCRC", 32, 0x04c11db7, 0x340bc6d9, ~0, true, true, 0), //
	CRC_32_ISO_HDLC("CRC-32/ISO-HDLC", 32, 0x04c11db7, 0xcbf43926, ~0, true, true, ~0), //
	CRC_32_ISCSI("CRC-32/ISCSI", 32, 0x1edc6f41, 0xe3069283, ~0, true, true, ~0), //
	CRC_32_MEF("CRC-32/MEF", 32, 0x741b8cd7, 0xd2c22f51, ~0, true, true, 0), //
	CRC_32_CD_ROM_EDC("CRC-32/CD-ROM-EDC", 32, 0x8001801b, 0x6ec2edc4, 0, true, true, 0), //
	CRC_32_AIXM("CRC-32/AIXM", 32, 0x814141ab, 0x3010bf7f, 0, false, false, 0), //
	CRC_32_BASE91_D("CRC-32/BASE91-D", 32, 0xa833982b, 0x87315576, ~0, true, true, ~0), //
	CRC_32_AUTOSAR("CRC-32/AUTOSAR", 32, 0xf4acfb13, 0x1697d06a, ~0, true, true, ~0), //
	CRC_40_GSM("CRC-40/GSM", 40, 0x0004820009L, 0xd4164fc646L, 0L, false, false, ~0L), //
	CRC_64_GO_ISO("CRC-64/GO-ISO", 64, 0x000000000000001bL, 0xb90956c775a41001L, ~0L, true, true, ~0L), //
	CRC_64_MS("CRC-64/MS", 64, 0x259c84cba6426349L, 0x75d4b74f024eceeaL, ~0L, true, true, 0L), //
	CRC_64_ECMA_182("CRC-64/ECMA-182", 64, 0x42f0e1eba9ea3693L, 0x6c40df5f0b497347L, 0L, false, false, 0L), //
	CRC_64_WE("CRC-64/WE", 64, 0x42f0e1eba9ea3693L, 0x62ec59e3f1a4f00aL, ~0L, false, false, ~0L), //
	CRC_64_XZ("CRC-64/XZ", 64, 0x42f0e1eba9ea3693L, 0x995dc9bbdf1939faL, ~0L, true, true, ~0L), //
	CRC_64_REDIS("CRC-64/REDIS", 64, 0xad93d23594c935a9L, 0xe9c6d914c4b8d9caL, 0L, true, true, 0L), //
	; // END
	// @formatter:on

	/**
	 * Associated {@link Params} with this {@link Preset}
	 */
	public final Params params;

	Preset(final String name, final int width, //
			final long poly, final long check, final long init, //
			final boolean refin, final boolean refout, final long xorout) {
		this.params = new Params(name, width, poly, check, init, refin, refout, xorout);
	}

	/**
	 * Find first {@link Preset} occurence with specified parameters.
	 * 
	 * @param width in bits of CRC
	 * @param poly is the normal representacion of the Polynomical
	 * @param check is the computed value of reference test string {@link CRC#TEST_VALUE}
	 * @return first found {@link Preset}
	 * @throws FileNotFoundException if no {@link Preset} is found
	 */
	public static Preset findBy(final int width, //
			final long poly, final long check) throws FileNotFoundException {
		for (final Preset preset : Preset.values()) {
			if ((preset.params.width == width) //
					&& (preset.params.poly == poly) //
					&& (preset.params.check == check)) {
				return preset;
			}
		}
		throw new FileNotFoundException("wanted width=" + width //
				+ " poly=" + Long.toHexString(poly) //
				+ " check=" + Long.toHexString(check));
	}

	/**
	 * Return a CRC instance associated with this {@link Preset}
	 * 
	 * @param tableLookup true if {@link GenericLookupTableCRC}, false to {@link GenericCalculateCRC}
	 * @return crc instance
	 */
	public CRC getInstance(final boolean tableLookup) {
		if (tableLookup) {
			return new GenericLookupTableCRC(this.params);
		} else {
			return new GenericCalculateCRC(this.params);
		}
	}

	/**
	 * This method call getInstance with a default value of true.
	 * 
	 * @return crc instance
	 * @see #getInstance(boolean)
	 */
	public CRC getInstance() {
		return this.getInstance(true);
	}

	/**
	 * Print a list with supported Presets in Markdown format (table)
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		final Preset[] presets = Preset.values();
		// Order by crc-width and name
		Arrays.sort(presets, new Comparator<Preset>() {
			@Override
			public int compare(final Preset a, final Preset b) {
				if (a.params.width < b.params.width) {
					return -1;
				}
				if (a.params.width > b.params.width) {
					return 1;
				}
				return a.name().compareTo(b.name());
			}
		});
		// Markdown Format
		System.out.println("| name | poly | check | init | refin | refout | xorout |");
		System.out.println("| :--- | :--- | :--- | :--- | :--- | :--- | :--- |");
		final ArrayList<String> row = new ArrayList<String>();
		for (final Preset preset : presets) {
			final Params p = preset.params;
			row.add(preset.name());
			row.add("0x" + Long.toHexString(p.poly));
			row.add("0x" + Long.toHexString(p.check));
			row.add("0x" + Long.toHexString(p.init));
			row.add(String.valueOf(p.refin));
			row.add(String.valueOf(p.refout));
			row.add("0x" + Long.toHexString(p.xorout));
			System.out.println("| " + String.join(" | ", row) + " |");
			row.clear();
		}
	}
}
