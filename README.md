HexEditor - A crude HexEditor object for Java
===============================================
(c) Alem - Alemcode.com


DESCRIPTION
---------------
HexEditor is a crude, dead-simple HexEditing utility class for Java.  
Open a binary file and replace hexadecimal strings or characters.   
That's it.

*Caveat:* This class should not be used for large binary files as it  
simply loads binary content into memory.

USAGE
---------------


Opening file

    HexEditor HexEditor = new HexEditor( "/path/to/binary" );

For a simple string replace

    HexEditor.replace( "4a", "5b");

For regex replacements

    HexEditor.regexReplace( "5b", "6c");

For positional replacements

    HexEditor.replacePosition( 2, 'f');


*Note:* All alphabetical characters in the hexadecimal character set 
must be in their lowercase form. ( Crude, remember? )

Saving

    HexEditor.save();
    HexEditor.saveAs( "/path/to/newbinary" );

Deletion

    HexEditor.delete();
