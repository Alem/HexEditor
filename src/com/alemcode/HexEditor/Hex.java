/**
 * Copyright 2012, Z.Alem
 * License: http://opensource.org/licenses/bsd-license.php The BSD License
 */
package com.alemcode.HexEditor;

/**
 * Converts representation of binary data between 
 * byte arrays and hexadecimal char arrays.
 *
 * @author Z. Alem <info@alemcode.com>
 * {@link http://alemcode.com}
 * @since  HexEditor1.0
 */
public class Hex
{
	/** Stores charaters of the hexadecimal numeral system */
	private final static char[] hex_digits = {
		'0', '1', '2', '3', '4', '5', '6', '7', 
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};

	/**
	 * Returns hexdecimal equivalent of byte array
	 *
	 * Creates a char array with 2:1 ratio to byte array ( 2 hex digits : 1 byte )
	 * Masks (and shifts) to get value of left and right bit quartet in byte
	 *
	 * @param 	byte[]	bytes
	 * @return 	char[]	Hexadecimal char array
	 */
	public static char[] bytesToHex( byte[] bytes )
	{
		int bytes_length = bytes.length;
		char[] hex_form = new char[bytes_length*2];

		for( int i = 0, j = 0; i<bytes_length; i++, j=i*2 )
		{
			hex_form[j] 	= hex_digits[ ( bytes[i] & 0xf0 ) >>> 4 ];
			hex_form[j+1]  	= hex_digits[ ( bytes[i] & 0x0f ) ];
		}

		return hex_form;
	}


	/**
	 * Returns index of a char in a char array
	 *
	 * Constructs String out of char array to make use of String's indexOf method
	 *
	 * @param char 	needle 		The char to find
	 * @param char[] haystack 	The char array to search through
	 * @return The index of the needle
	 */
	public static int indexOfChar( char needle, char[] haystack )
	{
		return( new String( haystack ).indexOf( needle ) );
	}

	/**
	 * Turns a char array containing hex data into a byte array
	 *
	 * Calculates hexadecimal pair's integer value (base 10),
	 * and type casts to byte
	 * 
	 * @param 	char[]	hex_chars
	 * @return 	byte[]	Hexadecimal char array
	 */
	public static byte[] hexToBytes( char[] hex_chars )
	{
		int hex_length = hex_chars.length;
		int byte_length = hex_length/2;
		byte[] byte_form = new byte[byte_length];

		for( int i = 0, j = 0; i<byte_length; i++, j=i*2 )
		{
			byte_form[i] = (byte)( 
					indexOfChar( hex_chars[j], hex_digits )*16
					+ indexOfChar(hex_chars[j+1], hex_digits )
					);
		}
		return byte_form;

	}

}
