/**
 * Copyright 2012, Z.Alem
 * License: http://opensource.org/licenses/bsd-license.php The BSD License
 */
package com.alemcode.HexEditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.alemcode.HexEditor.Hex;

/**
 * HexEditor provides simple methods for manipulating 
 * file binary data in hexadecimal form.
 *
 * A file is opened, and its contents are loaded into memory,
 * by passing the filepath to the HexEditor object's constructor.
 * Various forms of editing/replacements can then be performed.
 *
 * When satisfied with the results, the loaded content can be saved
 * either to the same file or to a new specified path.
 *
 * <p><blockquote><pre>
 *     HexEditor HexEditor = new HexEditor( "/path/to/binary" );
 *     HexEditor.replace( "4a", "5b");
 *     HexEditor.regexReplace( "5b", "6c");
 *     HexEditor.replacePosition( 2, 'f');
 *     HexEditor.saveAs( "/path/to/newbinary" );
 * </pre></blockquote><p>
 *
 * The HexEditor class relies heavily on the methods supplied by
 * the {@link Hex} class.
 * 
 * @author Z. Alem <info@alemcode.com>
 * @see    com.alemcode.HexEditor.Hex
 * @since  HexEditor1.0
 */
public class HexEditor
{

	/**
	 * Holds representation of a binary file as a
	 * string of hexadecimal characters  
	 */
	public String file_hex_string;

	/**
	 * Holds filepath
	 */
	public String filepath;


	/**
	 * Holds Java's representation of filepath
	 */
	public File file;

	/**
	 * Opens a file during instantiation using the {@link #open( String ) } method
	 * @see #open( String )
	 */
	public HexEditor( String filepath ) throws IOException
	{
		open( filepath );
	}

	/**
	 * Stores a hexadecimal string for given file
	 *
	 * @param string filepath
	 * @return a char[] containing files binary data in hexadecimal
	 * @throws IOException If file, given by filepath, cannot be read.
	 * @see Hex#bytesToHex( byte[] )
	 */
	public void open( String filepath ) throws IOException
	{		
		this.filepath = filepath;

		file 			= new File( filepath );
		byte[] file_bytes	= new byte[ (int)file.length() ];
		FileInputStream fis 	= new FileInputStream( file );

		int  bytes_read = 0;
		do
		{
			bytes_read = fis.read( file_bytes );
		}
		while ( bytes_read != -1 );

		file_hex_string = new String( Hex.bytesToHex( file_bytes ) );

		fis.close();
	}

	/**
	 * Saves the content currently held by the HexEditor object to a file.
	 *
	 * @param  filepath 	
	 * 		The file path to save to
	 * @throws IOException 	
	 * @throws FileNotFoundException 
	 * 		If file cannot be written due to unexpected condition. 
	 */
	public void saveAs( String filepath ) throws IOException, FileNotFoundException
	{
		byte[] file_hex_bytes = Hex.hexToBytes( file_hex_string.toCharArray() );
		FileOutputStream file_save = new FileOutputStream( filepath );
		file_save.write( file_hex_bytes );
	}


	/**
	 * Shorthand for {@link #saveAs( String ) } currently opened file.
	 * @see #saveAs( String )
	 */
	public void save() throws IOException, FileNotFoundException
	{
		saveAs( filepath );
	}

	/** Deletes the currently opened file */
	public void delete()
	{
		file.delete();	
	}

	/**
	 * Allows the search and replacement of hexadecimal strings
	 *
	 * @param match			The hex sequence to match
	 * @param replacement 		The hex sequence to replace with
	 */
	public void replace( CharSequence match, CharSequence replacement )
	{
		file_hex_string = file_hex_string.replace( match, replacement );
	}

	/**
	 * Allows the search and replacement of hexadecimal strings
	 * through Regular Expressions
	 *
	 * @param regex		 	The regex for the hex sequence to match
	 * @param replacement 		The hex sequence to replace with
	 */
	public void regexReplace( String regex, String replacement )
	{
		file_hex_string = file_hex_string.replaceAll( regex, replacement );

	}


	/**
	 * Allows replacement of hexadecimal at specified position
	 *
	 * @param position 		The position of the hexadecimal to replace
	 * @param replacement 		The replacement hexadecimal character
	 */
	public void replacePosition( int position, char replacement )
	{
		file_hex_string = file_hex_string.substring( 0, position ) + replacement + file_hex_string.substring( position + 1 );
	}



}
