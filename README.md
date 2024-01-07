# crc

A cyclic redundancy check (CRC) is an error-detecting code commonly used to detect accidental changes to digital data. Code writen in pure Java. Open Source project under Apache License v2.0

### Current Stable Version is [1.0.0](https://search.maven.org/#search|ga|1|g%3Aorg.javastack%20a%3Acrc)

---

## Requirements, Installation and Running (for Command Line Interface)

* Java Runtime (8 o newer): https://jdk.java.net/java-se-ri/17
* This software run in [Portable Mode](https://en.wikipedia.org/wiki/Portable_application), you only need the JAR.
* Execute with `java -jar crc-x.x.x.jar help`

---

## DOC

* All CRCs implements [java.util.zip.Checksum](https://docs.oracle.com/javase/8/docs/api/java/util/zip/Checksum.html) inteface.

#### Usage Example

```java
import org.javastack.crc.*;

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
```

* More examples in [Example package](https://github.com/ggrandes/crc/tree/master/src/main/java/org/javastack/crc/example/)


#### Supported CRCs (implemented as classes, for educational and didactic purposes)

| class-name | type | speed | poly | check | init | refin | refout | xorout |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| CRC8_DVB_S2 | calculated | slow | 0xd5 | 0xbc | 0x0 | false | false | 0x0 |
| CRC16_XMODEM | calculated | slow | 0x1021 | 0x31c3 | 0x0 | false | false | 0x0 |
| CRC32 (ISO/HDLC) | lookup-table | fast | 0x04c11db7 | 0xcbf43926 | 0xffffffff | true | true | 0xffffffff |
| CRC32C (ISCSI) | lookup-table | fast | 0x1edc6f41 | 0xe3069283 | 0xffffffff | true | true | 0xffffffff |
| CRC64 (ECMA-182) | lookup-table | fast | 0x42f0e1eba9ea3693 | 0x6c40df5f0b497347 | 0x0 | false | false | 0x0 |

#### Supported CRCs (generic Preset; lookup-table (fast) and calculated (slow))

| preset-name | poly | check | init | refin | refout | xorout |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| CRC_3_GSM | 0x3 | 0x4 | 0x0 | false | false | 0x7 |
| CRC_3_ROHC | 0x3 | 0x6 | 0x7 | true | true | 0x0 |
| CRC_4_G_704 | 0x3 | 0x7 | 0x0 | true | true | 0x0 |
| CRC_4_INTERLAKEN | 0x3 | 0xb | 0xf | false | false | 0xf |
| CRC_5_EPC_C1G2 | 0x9 | 0x0 | 0x9 | false | false | 0x0 |
| CRC_5_G_704 | 0x15 | 0x7 | 0x0 | true | true | 0x0 |
| CRC_5_USB | 0x5 | 0x19 | 0x1f | true | true | 0x1f |
| CRC_6_CDMA2000_A | 0x27 | 0xd | 0x3f | false | false | 0x0 |
| CRC_6_CDMA2000_B | 0x7 | 0x3b | 0x3f | false | false | 0x0 |
| CRC_6_DARC | 0x19 | 0x26 | 0x0 | true | true | 0x0 |
| CRC_6_GSM | 0x2f | 0x13 | 0x0 | false | false | 0x3f |
| CRC_6_G_704 | 0x3 | 0x6 | 0x0 | true | true | 0x0 |
| CRC_7_MMC | 0x9 | 0x75 | 0x0 | false | false | 0x0 |
| CRC_7_ROHC | 0x4f | 0x53 | 0x7f | true | true | 0x0 |
| CRC_7_UMTS | 0x45 | 0x61 | 0x0 | false | false | 0x0 |
| CRC_8_AUTOSAR | 0x2f | 0xdf | 0xff | false | false | 0xff |
| CRC_8_BLUETOOTH | 0xa7 | 0x26 | 0x0 | true | true | 0x0 |
| CRC_8_CDMA2000 | 0x9b | 0xda | 0xff | false | false | 0x0 |
| CRC_8_DARC | 0x39 | 0x15 | 0x0 | true | true | 0x0 |
| CRC_8_DVB_S2 | 0xd5 | 0xbc | 0x0 | false | false | 0x0 |
| CRC_8_GSM_A | 0x1d | 0x37 | 0x0 | false | false | 0x0 |
| CRC_8_GSM_B | 0x49 | 0x94 | 0x0 | false | false | 0xff |
| CRC_8_HITAG | 0x1d | 0xb4 | 0xff | false | false | 0x0 |
| CRC_8_I_432_1 | 0x7 | 0xa1 | 0x0 | false | false | 0x55 |
| CRC_8_I_CODE | 0x1d | 0x7e | 0xfd | false | false | 0x0 |
| CRC_8_LTE | 0x9b | 0xea | 0x0 | false | false | 0x0 |
| CRC_8_MAXIM_DOW | 0x31 | 0xa1 | 0x0 | true | true | 0x0 |
| CRC_8_MIFARE_MAD | 0x1d | 0x99 | 0xc7 | false | false | 0x0 |
| CRC_8_NRSC_5 | 0x31 | 0xf7 | 0xff | false | false | 0x0 |
| CRC_8_OPENSAFETY | 0x2f | 0x3e | 0x0 | false | false | 0x0 |
| CRC_8_ROHC | 0x7 | 0xd0 | 0xff | true | true | 0x0 |
| CRC_8_SAE_J1850 | 0x1d | 0x4b | 0xff | false | false | 0xff |
| CRC_8_SMBUS | 0x7 | 0xf4 | 0x0 | false | false | 0x0 |
| CRC_8_TECH_3250 | 0x1d | 0x97 | 0xff | true | true | 0x0 |
| CRC_8_WCDMA | 0x9b | 0x25 | 0x0 | true | true | 0x0 |
| CRC_10_ATM | 0x233 | 0x199 | 0x0 | false | false | 0x0 |
| CRC_10_CDMA2000 | 0x3d9 | 0x233 | 0x3ff | false | false | 0x0 |
| CRC_10_GSM | 0x175 | 0x12a | 0x0 | false | false | 0x3ff |
| CRC_11_FLEXRAY | 0x385 | 0x5a3 | 0x1a | false | false | 0x0 |
| CRC_11_UMTS | 0x307 | 0x61 | 0x0 | false | false | 0x0 |
| CRC_12_CDMA2000 | 0xf13 | 0xd4d | 0xfff | false | false | 0x0 |
| CRC_12_DECT | 0x80f | 0xf5b | 0x0 | false | false | 0x0 |
| CRC_12_GSM | 0xd31 | 0xb34 | 0x0 | false | false | 0xfff |
| CRC_12_UMTS | 0x80f | 0xdaf | 0x0 | false | true | 0x0 |
| CRC_13_BBC | 0x1cf5 | 0x4fa | 0x0 | false | false | 0x0 |
| CRC_14_DARC | 0x805 | 0x82d | 0x0 | true | true | 0x0 |
| CRC_14_GSM | 0x202d | 0x30ae | 0x0 | false | false | 0x3fff |
| CRC_15_CAN | 0x4599 | 0x59e | 0x0 | false | false | 0x0 |
| CRC_15_MPT1327 | 0x6815 | 0x2566 | 0x0 | false | false | 0x1 |
| CRC_16_ARC | 0x8005 | 0xbb3d | 0x0 | true | true | 0x0 |
| CRC_16_CDMA2000 | 0xc867 | 0x4c06 | 0xffff | false | false | 0x0 |
| CRC_16_CMS | 0x8005 | 0xaee7 | 0xffff | false | false | 0x0 |
| CRC_16_DDS_110 | 0x8005 | 0x9ecf | 0x800d | false | false | 0x0 |
| CRC_16_DECT_R | 0x589 | 0x7e | 0x0 | false | false | 0x1 |
| CRC_16_DECT_X | 0x589 | 0x7f | 0x0 | false | false | 0x0 |
| CRC_16_DNP | 0x3d65 | 0xea82 | 0x0 | true | true | 0xffff |
| CRC_16_EN_13757 | 0x3d65 | 0xc2b7 | 0x0 | false | false | 0xffff |
| CRC_16_GENIBUS | 0x1021 | 0xd64e | 0xffff | false | false | 0xffff |
| CRC_16_GSM | 0x1021 | 0xce3c | 0x0 | false | false | 0xffff |
| CRC_16_IBM_3740 | 0x1021 | 0x29b1 | 0xffff | false | false | 0x0 |
| CRC_16_IBM_SDLC | 0x1021 | 0x906e | 0xffff | true | true | 0xffff |
| CRC_16_ISO_IEC_14443_3_A | 0x1021 | 0xbf05 | 0xc6c6 | true | true | 0x0 |
| CRC_16_KERMIT | 0x1021 | 0x2189 | 0x0 | true | true | 0x0 |
| CRC_16_LJ1200 | 0x6f63 | 0xbdf4 | 0x0 | false | false | 0x0 |
| CRC_16_M17 | 0x5935 | 0x772b | 0xffff | false | false | 0x0 |
| CRC_16_MAXIM_DOW | 0x8005 | 0x44c2 | 0x0 | true | true | 0xffff |
| CRC_16_MCRF4XX | 0x1021 | 0x6f91 | 0xffff | true | true | 0x0 |
| CRC_16_MODBUS | 0x8005 | 0x4b37 | 0xffff | true | true | 0x0 |
| CRC_16_NRSC_5 | 0x80b | 0xa066 | 0xffff | true | true | 0x0 |
| CRC_16_OPENSAFETY_A | 0x5935 | 0x5d38 | 0x0 | false | false | 0x0 |
| CRC_16_OPENSAFETY_B | 0x755b | 0x20fe | 0x0 | false | false | 0x0 |
| CRC_16_PROFIBUS | 0x1dcf | 0xa819 | 0xffff | false | false | 0xffff |
| CRC_16_RIELLO | 0x1021 | 0x63d0 | 0xb2aa | true | true | 0x0 |
| CRC_16_SPI_FUJITSU | 0x1021 | 0xe5cc | 0x1d0f | false | false | 0x0 |
| CRC_16_T10_DIF | 0x8bb7 | 0xd0db | 0x0 | false | false | 0x0 |
| CRC_16_TELEDISK | 0xa097 | 0xfb3 | 0x0 | false | false | 0x0 |
| CRC_16_TMS37157 | 0x1021 | 0x26b1 | 0x89ec | true | true | 0x0 |
| CRC_16_UMTS | 0x8005 | 0xfee8 | 0x0 | false | false | 0x0 |
| CRC_16_USB | 0x8005 | 0xb4c8 | 0xffff | true | true | 0xffff |
| CRC_16_XMODEM | 0x1021 | 0x31c3 | 0x0 | false | false | 0x0 |
| CRC_17_CAN_FD | 0x1685b | 0x4f03 | 0x0 | false | false | 0x0 |
| CRC_21_CAN_FD | 0x102899 | 0xed841 | 0x0 | false | false | 0x0 |
| CRC_24_BLE | 0x65b | 0xc25a56 | 0x555555 | true | true | 0x0 |
| CRC_24_FLEXRAY_A | 0x5d6dcb | 0x7979bd | 0xfedcba | false | false | 0x0 |
| CRC_24_FLEXRAY_B | 0x5d6dcb | 0x1f23b8 | 0xabcdef | false | false | 0x0 |
| CRC_24_INTERLAKEN | 0x328b63 | 0xb4f3e6 | 0xffffff | false | false | 0xffffff |
| CRC_24_LTE_A | 0x864cfb | 0xcde703 | 0x0 | false | false | 0x0 |
| CRC_24_LTE_B | 0x800063 | 0x23ef52 | 0x0 | false | false | 0x0 |
| CRC_24_OPENPGP | 0x864cfb | 0x21cf02 | 0xb704ce | false | false | 0x0 |
| CRC_24_OS_9 | 0x800063 | 0x200fa5 | 0xffffff | false | false | 0xffffff |
| CRC_30_CDMA | 0x2030b9c7 | 0x4c34abf | 0x3fffffff | false | false | 0x3fffffff |
| CRC_31_PHILIPS | 0x4c11db7 | 0xce9e46c | 0x7fffffff | false | false | 0x7fffffff |
| CRC_32_AIXM | 0x814141ab | 0x3010bf7f | 0x0 | false | false | 0x0 |
| CRC_32_AUTOSAR | 0xf4acfb13 | 0x1697d06a | 0xffffffff | true | true | 0xffffffff |
| CRC_32_BASE91_D | 0xa833982b | 0x87315576 | 0xffffffff | true | true | 0xffffffff |
| CRC_32_BZIP2 | 0x4c11db7 | 0xfc891918 | 0xffffffff | false | false | 0xffffffff |
| CRC_32_CD_ROM_EDC | 0x8001801b | 0x6ec2edc4 | 0x0 | true | true | 0x0 |
| CRC_32_CKSUM | 0x4c11db7 | 0x765e7680 | 0x0 | false | false | 0xffffffff |
| CRC_32_ISCSI | 0x1edc6f41 | 0xe3069283 | 0xffffffff | true | true | 0xffffffff |
| CRC_32_ISO_HDLC | 0x4c11db7 | 0xcbf43926 | 0xffffffff | true | true | 0xffffffff |
| CRC_32_JAMCRC | 0x4c11db7 | 0x340bc6d9 | 0xffffffff | true | true | 0x0 |
| CRC_32_MEF | 0x741b8cd7 | 0xd2c22f51 | 0xffffffff | true | true | 0x0 |
| CRC_32_MPEG_2 | 0x4c11db7 | 0x376e6e7 | 0xffffffff | false | false | 0x0 |
| CRC_32_XFER | 0xaf | 0xbd0be338 | 0x0 | false | false | 0x0 |
| CRC_40_GSM | 0x4820009 | 0xd4164fc646 | 0x0 | false | false | 0xffffffffff |
| CRC_64_ECMA_182 | 0x42f0e1eba9ea3693 | 0x6c40df5f0b497347 | 0x0 | false | false | 0x0 |
| CRC_64_GO_ISO | 0x1b | 0xb90956c775a41001 | 0xffffffffffffffff | true | true | 0xffffffffffffffff |
| CRC_64_MS | 0x259c84cba6426349 | 0x75d4b74f024eceea | 0xffffffffffffffff | true | true | 0x0 |
| CRC_64_REDIS | 0xad93d23594c935a9 | 0xe9c6d914c4b8d9ca | 0x0 | true | true | 0x0 |
| CRC_64_WE | 0x42f0e1eba9ea3693 | 0x62ec59e3f1a4f00a | 0xffffffffffffffff | false | false | 0xffffffffffffffff |
| CRC_64_XZ | 0x42f0e1eba9ea3693 | 0x995dc9bbdf1939fa | 0xffffffffffffffff | true | true | 0xffffffffffffffff |

---

## MAVEN

Add the dependency to your pom.xml:

    <dependency>
        <groupId>org.javastack</groupId>
        <artifactId>crc</artifactId>
        <version>1.0.0</version>
    </dependency>

---
Inspired in [Painless Guide to CRC Error Detection Algorithms](https://www.zlib.net/crc_v3.txt) and [CRC RevEng](https://reveng.sourceforge.io/crc-catalogue/), this code is Java-minimalistic version.
