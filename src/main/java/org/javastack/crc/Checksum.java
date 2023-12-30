package org.javastack.crc;

public interface Checksum extends java.util.zip.Checksum {
	/**
	 * Updates the current checksum with the specified byte.
	 *
	 * @param b the byte to update the checksum with
	 */
	public void update(final byte b);

	/**
	 * Updates the current checksum with the specified array of bytes.
	 * 
	 * @param buffer the byte array to update the checksum with
	 */
	public void update(final byte[] buffer);
}
