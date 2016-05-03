/*
 * $Id: ASN1BitString.java,v 1.5 1999/04/13 07:23:05 hoylen Exp $
 *
 * Copyright (C) 1996, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */

package asn1;

//----------------------------------------------------------------
/**
 * Representation of an ASN.1 <code>BIT STRING</code>
 *
 * The BIT STRING type denotes an arbitary string of bits (ones and zeros).
 * A BIT STRING value can have any length, including zero. The type is a
 * string type.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:05 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */

public final class ASN1BitString extends ASN1Any
{
  /**
   * This constant is the ASN.1 UNIVERSAL tag value for BIT STRING.
   */

public final static int TAG = 0x03;

  //================================================================
  /**
   * The values of the BIT STRING are stored in this array of boolean
   * values.
   */
  
private boolean[] bits;

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 BIT STRING object. It sets the tag
   * to the default value of UNIVERSAL 3, and the bits to the
   * given bit_values.
   *
   * @param	bit_values - array of booleans representing the bit string.
   */

public 
ASN1BitString(boolean[] bit_values)
  {
    bits = bit_values;
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 BIT STRING object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */

public
ASN1BitString(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
  {
    super(ber, check_tag); // superclass will call ber_decode
  }

  //----------------------------------------------------------------
  /**
   * Method for initializing the object from a BER encoding.
   *
   * @param ber_enc The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1EncodingException If the BER encoding is incorrect.
   */

public void
ber_decode(BEREncoding ber_enc, boolean check_tag)
       throws ASN1EncodingException
  {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG || 
	  ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
	throw new ASN1EncodingException
	  ("ASN.1 BIT STRING: bad BER: tag=" + ber_enc.tag_get() + 
	   " expected " + TAG + "\n");
      }
    }

    if (ber_enc instanceof BERPrimitive) {
      BERPrimitive ber = (BERPrimitive) ber_enc;

      int[] encoding = ber.peek();
  
      if (encoding.length < 1) {
	throw new ASN1EncodingException
	  ("ASN1 BIT STRING: invalid encoding, length = " + encoding.length);
      }

      int unused_bits = (encoding[0] & 0x07);
      int num_bits = (encoding.length - 1) * 8 - unused_bits;
    
      bits = new boolean[num_bits];
      for (int bit = 0; bit < num_bits; bit++) {
	int octet = encoding[(bit / 8) + 1];
	octet <<= (bit % 8);
	if ((octet & 0x80) == 0) {
	  bits[bit] = false;
	} else {
	  bits[bit] = true;
	}
      }
    } else {
      // BERConstructed ber = (BERConstructed) ber_enc;

      throw new ASN1EncodingException
	("ASN.1 BIT STRING: decoding constructed NOT IMPLEMENTED YET");
      // ??? extract all the bits then concatenate into one
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the BIT STRING.
   * Bit strings can have a primitive encoding and a constructed
   * encoding. This method performs the primitive encoding (which
   * is the one specified for DER encoding).
   *
   * @return	The BER encoding of the BIT STRING
   * @exception	ASN1Exception when the BIT STRING is invalid
   *		and cannot be encoded.
   */

public BEREncoding
ber_encode()
       throws ASN1Exception
  {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the BIT STRING.
   * Bit strings can have a primitive encoding and a constructed
   * encoding. This method performs the primitive encoding (which
   * is the one specified for DER encoding).
   *
   * @return	The BER encoding of the BIT STRING
   * @exception	ASN1Exception when the BIT STRING is invalid
   *		and cannot be encoded.
   */

public BEREncoding
ber_encode(int tag_type, int tag)
       throws ASN1Exception
  {
    int num_octets = (bits.length + 7) / 8;

    int[] encoding = new int[num_octets + 1];

    // Generate BER encoding of the BitString

    // First octet gives the number of unused bits.
    encoding[0] = (num_octets * 8) - bits.length;

    for (int count = 1; count <= num_octets; count++) {
      encoding[count] = 0x00;

      int bit_base_index = (count - 1) * 8;
      for (int bit_index = 0; bit_index < 8; bit_index++) {
	int n = bit_base_index + bit_index;

	encoding[count] <<= 1;
	if (n < bits.length && bits[n]) {
	  encoding[count] |= 0x01;
	}
      }
    }

    return new BERPrimitive(tag_type, tag, encoding);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the bit string's value.
   *
   * @param	new_bits the value to set the BIT STRING to.
   * @return	the object.
   */

public ASN1BitString
set(boolean[] new_bits)
  {
    bits = new_bits;
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the bit string's value.
   *
   * @return	the BIT STRING's current value.
   */

public boolean[]
get()
  {
    return bits;
  }

  //----------------------------------------------------------------
  /**
   * Returns a new String object representing this ASN.1 object's value. 
   *
   * @return	A text string representation of the BitString.
   */

public String
toString()
  {
    StringBuffer str = new StringBuffer();

    str.append('\'');
    for (int x = 0; x < bits.length; x++) {
      str.append(bits[x] ? '1' : '0');
    }
    str.append("'B");
    
    return str.toString();
  }

  //================================================================
  // XER (XML Encoding Rules) code

  //----------------------------------------------------------------
  /**
   * Produces the XER encoding of the object.
   *
   * @param	dest the destination XER encoding is written to
   * @exception ASN1Exception if data is invalid.
   */

  public void
    xer_encode(java.io.PrintWriter dest)
    throws ASN1Exception
  {
    for (int x = 0; x < bits.length; x++) {
      dest.print(bits[x] ? '1' : '0');
    }
  }

  //================================================================
  // Nested inner-class for parsing XER.

  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private final static int STATE_INIT = 0;
    private final static int STATE_START_GOT = 1;
    private final static int STATE_VALUE_GOT = 2;
    private final static int STATE_TERM = 3;

    private int state;

    private java.util.Vector proxy_value;

    //----------------

    public XER_Parser_Proxy()
    {
      super("BIT_STRING");
      state = STATE_INIT;
    }

    public XER_Parser_Proxy(String overriding_xer_tag)
    {
      super(overriding_xer_tag);
      state = STATE_INIT;
    }

    //----------------

    public void startElement(XERsaxHandler handler,
			     String name,
			     org.xml.sax.AttributeList atts)
      throws org.xml.sax.SAXException
    {
      if (name.equals(xer_tag) &&
	  state == STATE_INIT) {
	proxy_value = new java.util.Vector();
	state = STATE_START_GOT;

      } else {
	handler.throw_start_unexpected(xer_tag, name);
      }
    }

    //----------------

    public void endElement(XERsaxHandler handler,
			   String name)
      throws org.xml.sax.SAXException
    {
      if (name.equals(xer_tag) &&
	  state == STATE_VALUE_GOT) {
	// Create new BIT STRING object

        int size = proxy_value.size();
	boolean[] a_value = new boolean[size];
	for (int x = 0; x < size; x++) {
	  a_value[x] = ((java.lang.Boolean)
			proxy_value.elementAt(x)).booleanValue();
	}
	handler.member_got(new ASN1BitString(a_value));

	state = STATE_TERM;

      } else {
	handler.throw_end_unexpected(xer_tag, name);
      }
    }

    //----------------

    public void characters(XERsaxHandler handler,
			   char[] ch,
			   int start,
			   int length)
      throws org.xml.sax.SAXException
    {
      int begin = start;

      if (state == STATE_START_GOT) {
	int end = begin + length;

	while (begin < end) {
	  char character = ch[begin];
	  if (character == '0') {
	    proxy_value.addElement(new java.lang.Boolean(false));
	    state = STATE_VALUE_GOT;

	  } else if (character == '1') {
	    proxy_value.addElement(new java.lang.Boolean(true));
	    state = STATE_VALUE_GOT;

	  } else if (Character.isWhitespace(character)) {
	    // ignore whitespace

	  } else {
	    // die
	  }

	  begin++;
	}

      } else {
	handler.throw_characters_unexpected(xer_tag);
      }
    }

  } // Nested inner-class XER_Parser_Proxy

} // ASN1BitString

//----------------------------------------------------------------
/*
  $Log: ASN1BitString.java,v $
  Revision 1.5  1999/04/13 07:23:05  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:34  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:14  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:34:58  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

  */
//----------------------------------------------------------------
//EOF
