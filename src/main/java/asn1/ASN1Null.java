/*
 * $Id: ASN1Null.java,v 1.5 1999/04/13 07:23:07 hoylen Exp $
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
 * Representation of an ASN.1 NULL.
 *
 * This class represents a null value. A NULL is used
 * when only the tag is of interest, and not any value.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:07 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */

//----------------------------------------------------------------

public final class ASN1Null extends ASN1Any
{
  /**
   * This constant is the ASN.1 UNIVERSAL tag value for NULL.
   */

public final static int TAG = 0x05;

  //----------------------------------------------------------------
  /**
   * Default constructor for an ASN.1 NULL object. The tag is set
   * to the default tag of UNIVERSAL 5. A NULL has no value.
   */

public 
ASN1Null()
  {
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 NULL object from a primitive BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */

public
ASN1Null(BEREncoding ber, boolean check_tag)
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
      ("ASN.1 NULL: bad BER: tag=" + ber_enc.tag_get() +
       " expected " + TAG + "\n");
      }
    }

    if (ber_enc instanceof BERPrimitive) {
      // We do not check the contents at all since we are not interested
      // in it at all.
    } else {
      throw new ASN1EncodingException("ASN.1 NULL: bad form, constructed");
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the NULL.
   *
   * @return	The BER encoding of the NULL
   * @exception	ASN1Exception when the NULL is invalid
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
   * Returns a BER encoding of the NULL.
   *
   * @return	The BER encoding of the NULL
   * @exception	ASN1Exception when the NULL is invalid
   *		and cannot be encoded.
   */

public BEREncoding
ber_encode(int tag_type, int tag)
       throws ASN1Exception
  {
    int[] encoding = new int[0]; // trivial encoding
    return new BERPrimitive(tag_type, tag, encoding);
  }

  //----------------------------------------------------------------
  /**
   * Returns a new String object representing this ASN.1 object's value. 
   */

public String
toString()
  {
    return "null";
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
    // do nothing
  }

  //----------------------------------------------------------------
  // Nested inner-class for parsing XER.

  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private final static int STATE_INIT = 0;
    private final static int STATE_START_GOT = 1;
    private final static int STATE_TERM = 2;

    private int state;

    public XER_Parser_Proxy()
    {
      super("NULL");
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
	  state == STATE_START_GOT) {
	// Create new NULL object
	handler.member_got(new ASN1Null());
	state = STATE_TERM;

      } else {
	handler.throw_end_unexpected(xer_tag, name);
      }
    }

    //----------------
    // no characters expected
    // uses characters() from XERsaxHandler.XER_Proxy_Parser

  } // nested inner-class: XER_Parser_Proxy

} // class: ASN1Null

//----------------------------------------------------------------
/*
  $Log: ASN1Null.java,v $
  Revision 1.5  1999/04/13 07:23:07  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:37  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:17  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:34:59  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:41  hoylen
  Imported sources

  */
//----------------------------------------------------------------
//EOF
