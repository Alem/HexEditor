/**
 * Copyright 2012, Z.Alem
 * License: http://opensource.org/licenses/bsd-license.php The BSD License
 */
package com.alemcode.HexEditor;

import junit.framework.TestCase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HexTest extends TestCase
{

	public static byte[] test_bytes = { 
		(byte) 0x01, 
		(byte) 0xa0, 
		(byte) 0x1a 
	};

	/**
	 * @Test
	 */
	public static void bytesToHexTest()
	{
		char[] hex_form = Hex.bytesToHex( test_bytes );

		assertEquals( hex_form[0], '0' );
		assertEquals( hex_form[1], '1' );
		assertEquals( hex_form[2], 'a' );
		assertEquals( hex_form[3], '0' );
		assertEquals( hex_form[4], '1' );
		assertEquals( hex_form[5], 'a' );
	}

	/**
	 * @Test
	 */
	public static void hexToBytesTest()
	{
		char[] hex = { 
			'0','1', 
			'a','0',
			'1','a'
		};

		byte[] byte_form = Hex.hexToBytes( hex );

		assertEquals( byte_form[0], (byte)0x01 );
		assertEquals( byte_form[1], (byte)0xa0 );
		assertEquals( byte_form[2], (byte)0x1a );
	}


	/**
	 * @Test
	 */
	public static void HexEditorTest()
	{
		String filepath = "binary_test";

		try
		{
			FileOutputStream out = new FileOutputStream( filepath );
			out.write( test_bytes );
			out.close();
			System.out.println("Created binary_test with byte content: 01a01a ");

			HexEditor binary_file = new HexEditor( filepath );

			System.out.println( 
					"Opened binary_test and found:" +
					binary_file.file_hex_string 
					);

			assertEquals( binary_file.file_hex_string, "01a01a" );

			replacementTest( binary_file );

			// test saveAs different file 
			binary_file.saveAs( "binary_test2");
			HexEditor binary_file2 = new HexEditor( "binary_test2" );
			System.out.println( 
					"Saved as binary_test2 with content:" 
					+ binary_file2.file_hex_string 
					);
			assertEquals( binary_file2.file_hex_string, "7ca4ca" );

			// Verify actual content of binary remained unchanged
			HexEditor binary_file_old = new HexEditor( "binary_test" );
			System.out.println( 
					"Re-opened binary_test and found:" +
					binary_file_old.file_hex_string 
			);
			assertEquals( binary_file_old.file_hex_string, "01a01a" );

			// Write buffer to currently opened binary file
			binary_file.save();
			HexEditor binary_file_new = new HexEditor( "binary_test" );
			System.out.println( 
					"Re-opened binary_test after save() and found:"
				       	+ binary_file_new.file_hex_string 
					);
			assertEquals( binary_file_new.file_hex_string, "7ca4ca" );

			// cleanup
			binary_file.delete();
			binary_file2.delete();

			File binary_file_check = new File( "binary_test" );
			File binary_file2_check = new File( "binary_test2" );
			assertFalse( binary_file_check.exists() );
			assertFalse( binary_file2_check.exists() );

			System.out.println( "Successfully deleted binary_test files" );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	/**
	 * @Test
	 */
	public static void replacementTest( HexEditor binary_file )
	{
		binary_file.replace( "01", "6B" );
		assertEquals( binary_file.file_hex_string, "6Ba6Ba" );
		System.out.println( "Replace 01 for 6B: " + binary_file.file_hex_string );

		binary_file.regexReplace( "6B", "4c" );
		assertEquals( binary_file.file_hex_string, "4ca4ca" );
		System.out.println( "Replace 6B for 4c: " + binary_file.file_hex_string );

		binary_file.replacePosition( 0, '7' );
		assertEquals( binary_file.file_hex_string, "7ca4ca" );
		System.out.println( "Replace pos 0 with 7: " + binary_file.file_hex_string );
	}

	/**
	 * @Test
	 */
	public static void main( String args[] )
	{
		System.out.println("Testing Hex.bytesToHex: ");
		bytesToHexTest();

		System.out.println("Testing Hex.hexToBytes: ");
		hexToBytesTest();

		System.out.println("Testing HexEditor: ");
		HexEditorTest();
	}
}
