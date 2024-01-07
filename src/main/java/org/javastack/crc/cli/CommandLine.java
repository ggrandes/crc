package org.javastack.crc.cli;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import org.javastack.crc.CRC;
import org.javastack.crc.Preset;

public class CommandLine {
	private static void usage() {
		out.println(CommandLine.class.getCanonicalName() + " <command> [parameters]");
		out.println("List of commands:");
		out.println("\t" + "help" + "\t" + ": this help");
		out.println("\t" + "list" + "\t" + ": list supported CRCs");
		out.println("\t" + "search <CRC-NAME>" + "\t" + ": search CRCs by partial name");
		out.println("\t" + "string <CRC-NAME> <--hex|--ascii|--latin1|--utf8> <INPUT-STRING>" + "\t"
				+ ": compute CRC from string");
		out.println("\t" + "file <CRC-NAME> <FILE-NAME>" + "\t" + ": compute CRC of a file");
		out.println();
	}

	private static void computeFile(final String crcName, //
			final String fileName) {
		final Preset ps;
		try {
			ps = Preset.valueOf(crcName);
		} catch (Exception e) {
			err.println("ERROR: " + crcName + " not found");
			return;
		}
		out.println("Computing crcName=" + crcName //
				+ " inputFileName=" + fileName);
		final CRC crc = ps.getInstance(false);
		try (FileInputStream fis = new FileInputStream(fileName)) {
			while (true) {
				final byte[] buf = new byte[4096];
				final int len = fis.read(buf, 0, buf.length);
				if (len < 0) {
					break;
				} else if (len > 0) {
					crc.update(buf, 0, len);
				}
			}
		} catch (Exception e) {
			err.println("ERROR: " + String.valueOf(e));
			return;
		}
		out.println("crc(hex)=" + Long.toHexString(crc.getValue()));
	}

	private static void computeString(final String crcName, //
			final String coding, //
			final String input) {
		final Preset ps;
		try {
			ps = Preset.valueOf(crcName);
		} catch (Exception e) {
			err.println("ERROR: " + crcName + " not found");
			return;
		}
		final byte[] inputBuffer;
		switch (coding) {
			case "--hex":
				inputBuffer = Utils.fromHex(input);
				break;
			case "--ascii":
				inputBuffer = input.getBytes(StandardCharsets.US_ASCII);
				break;
			case "--latin1":
				inputBuffer = input.getBytes(StandardCharsets.ISO_8859_1);
				break;
			case "--utf8":
				inputBuffer = input.getBytes(StandardCharsets.UTF_8);
				break;
			default:
				err.println("ERROR: " + coding + " unknown");
				return;
		}
		out.println("Computing crcName=" + crcName //
				+ " inputCoding=" + coding.replace("-", "") //
				+ " inputString=" + input);
		final CRC crc = ps.getInstance(false);
		crc.update(inputBuffer);
		out.println("crc(hex)=" + Long.toHexString(crc.getValue()));
	}

	private static void searchPreset(String likeName) {
		// Sanitize name
		likeName = likeName.trim();
		likeName = likeName.toUpperCase();
		likeName = likeName.replaceAll("[^A-Z0-9_]", "_");
		out.println("Searching likeName=" + likeName);
		for (Preset ps : Preset.values()) {
			if (ps.name().contains(likeName)) {
				out.println(ps.name());
			}
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			err.println("ERROR: not enough parameters");
			usage();
			return;
		}
		int argP = 0;
		final String mainCommand = args[argP++];
		switch (mainCommand) {
			case "string": {
				if (args.length < 4) {
					err.println("ERROR: not enough parameters");
					usage();
					return;
				}
				final String crcName = args[argP++], //
						coding = args[argP++], //
						input = args[argP++];
				computeString(crcName, coding, input);
				break;
			}
			case "file": {
				if (args.length < 3) {
					err.println("ERROR: not enough parameters");
					usage();
					return;
				}
				final String crcName = args[argP++], //
						fileName = args[argP++];
				computeFile(crcName, fileName);
				break;
			}
			case "search": {
				if (args.length < 2) {
					err.println("ERROR: not enough parameters");
					usage();
					return;
				}
				final String likeName = args[argP++];
				searchPreset(likeName);
				break;
			}
			case "list":
				Preset.main(null);
				break;
			case "help":
			default:
				usage();
				break;
		}
	}
}
