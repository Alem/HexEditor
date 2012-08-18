HexEditor - A Simple HexEditor Class for Java
===============================================
(c) Alem - Alemcode.com


DESCRIPTION
---------------
HexEditor is a crude, dead-simple HexEditing utility class for Java.  
Open a binary file and replace hexadecimal strings or characters.

*Caveat:* This class should not be used for large binary files as it simply 
loads binary content into memory.

USAGE
---------------

To open a binary file:

    HexEditor HexEditor = new HexEditor( "/path/to/binary" );

For simple string replacements:

    HexEditor.replace( "4a", "5b");

For regex replacements:

    HexEditor.regexReplace( "5b", "6c");

For positional replacements:

    HexEditor.replacePosition( 2, 'f');

Saving:

    HexEditor.save();
    HexEditor.saveAs( "/path/to/newbinary" );

Deletion:

    HexEditor.delete();
