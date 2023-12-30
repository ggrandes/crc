package org.javastack.crc;

public abstract class CRC implements Checksum {
	@Override
	public void update(final byte[] buffer, final int offset, int length) {
		for (int i = offset; length > 0; length--) {
			final int b = buffer[i++];
			update(b);
		}
	}

	@Override
	public void update(final byte[] buffer) {
		for (int i = 0; i < buffer.length; i++) {
			final int b = buffer[i];
			update(b);
		}
	}

	@Override
	public void update(final byte b) {
		update((int) b);
	}
}
