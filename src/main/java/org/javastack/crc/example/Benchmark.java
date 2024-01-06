package org.javastack.crc.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.javastack.crc.CRC;
import org.javastack.crc.CRC32;
import org.javastack.crc.CRC32C;
import org.javastack.crc.CRC64;
import org.javastack.crc.Params;
import org.javastack.crc.Preset;

public class Benchmark {
	protected static final long ITER = (long) 1e4;
	protected static final long LOOPS = 10;
	protected static final boolean useDirectCalc = true;
	protected static final boolean useTableCalc = true;
	protected static final boolean shortReport = true;

	protected static final byte[] testBytes = CRC.TEST_VALUE.getBytes();

	public static void main(final String[] args) throws Throwable {
		final List<CRC> refs = Arrays.asList( //
				new CRC64(), //
				new CRC32(), //
				new CRC32C() //
		);
		System.out.println("iter=" + ITER);
		System.out.println("loops=" + LOOPS);
		System.out.println("speed=CRCs/ms (high better)");
		System.out.println("--- Init ---");
		{
			final long begin = System.currentTimeMillis();
			for (final CRC crc : refs) {
				check(crc);
			}
			for (final Preset ps : Preset.values()) {
				check(ps.getInstance(false));
				check(ps.getInstance(true));
			}
			long end = System.currentTimeMillis();
			System.out.println("time=" + (end - begin) + "ms");
		}
		System.out.println("--- Begin ---");
		for (final CRC ref : refs) {
			final Params p = ref.getParams();
			System.out.print(ref.getClass().getSimpleName() + " (class-table) ");
			doTest(ref);
			if (useDirectCalc) {
				final Preset ps = Preset.findBy(p.width, p.poly, p.check);
				System.out.print(p.name + " (gen-calc) ");
				doTest(ps.getInstance(false));
			}
			if (useTableCalc) {
				final Preset ps = Preset.findBy(p.width, p.poly, p.check);
				System.out.print(p.name + " (gen-table) ");
				doTest(ps.getInstance(true));
			}
		}
		System.out.println("--- End ---");
	}

	public static void check(final CRC crc) {
		crc.reset();
		crc.update(testBytes);
		if (crc.getValue() != crc.getParams().check) {
			throw new RuntimeException("FAIL: " + crc);
		}
	}

	private static final int poolSize = 0x3FFFF + 1;
	private static final ArrayList<byte[]> bb = new ArrayList<byte[]>(poolSize + 8) {
		private static final long serialVersionUID = 42L;
		{
			final long begin = System.currentTimeMillis();
			final Random r = new Random();
			for (int i = 0; i < poolSize; i++) {
				final byte[] b = new byte[1024];
				r.nextBytes(b);
				add(b);
			}
			long end = System.currentTimeMillis();
			System.out.println("random buffers (" + size() + "): done - time=" + (end - begin) + "ms");
		}
	};

	public static void doTest(final CRC crc) throws Throwable {
		long avg = 0;
		for (int j = 0; j < LOOPS; j++) {
			final long begin = System.currentTimeMillis();
			int c = 0;
			for (int i = 0; i < ITER; i++) {
				crc.reset();
				crc.update(bb.get(i % bb.size()));
				final long value = crc.getValue();
				if ((value ^ value) != 0L) {
					throw new RuntimeException("FAIL");
				}
				c++;
			}
			long end = System.currentTimeMillis();
			final long speed = (c / Math.max(end - begin, 1));
			avg += speed;
			if (shortReport) {
				System.out.print(speed + " ");
			} else {
				System.out.println("time=" + (end - begin) + " count=" + c + " CRCs/ms=" + speed);
			}
		}
		if (shortReport) {
			System.out.print("| " + avg / LOOPS);
			System.out.println();
		}
		System.out.flush();
	}
}
