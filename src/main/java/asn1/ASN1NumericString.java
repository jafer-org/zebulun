/*
 * $Id: ASN1NumericString.java,v 1.5 1999/04/13 07:23:07 hoylen Exp $
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
 * Representation for an ASN.1 NumericString.
 *
 * The <code>NumericString<code> type denotes an arbitary string
 * of Numeric characters (digits and space).
 * This type is a string type.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:07 $
 * @author	Hoylen Sue <h.sue@ieee.org>
 */

//----------------------------------------------------------------

public class ASN1NumericString extends ASN1OctetString
{
  /**
   * This constant is the ASN.1 UNIVERSAL tag value for NumericString.
   */

public final static int TAG = 0x12;

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 NumericString object. It sets the tag to the
   * default value of UNIVERSAL 18 (0x12). */

public 
ASN1NumericString(String value)
  {
    super(value);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for a NumericString object from a primitive BER encoding.
   *
   * @param ber_enc The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */

public
ASN1NumericString(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
  {
    super(ber, false);

    if (check_tag) {
      if (ber.tag_get() != TAG || 
	  ber.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
	throw new ASN1EncodingException
	  ("ASN.1 NumericString: bad BER: tag=" + ber.tag_get() + 
	   " expected " + TAG + "\n");
      }
    }
  }

  //----------------------------------------------------------------
  /**
   * Encodes
   *
   * @exception	ASN1Exception if the BER encoding cannot be formed.
   */

public BEREncoding
ber_encode()
       throws ASN1Exception
  {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  /*
   * ber_encode()
   * ber_decode()
   * set()
   * get() 
   * toString()
   * are inherited from base class
   */
  
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
    super.xer_encode(dest);
  }

  //================================================================
  // Nested inner-class for parsing XER.

  public static class XER_Parser_Proxy
    extends ASN1OctetString.XER_Parser_Proxy {

    public XER_Parser_Proxy()
    {
      super("NumericString");
    }

    public XER_Parser_Proxy(String overriding_xer_tag)
    {
      super(overriding_xer_tag);
    }

    public void endElement(XERsaxHandler handler,
			   String name)
      throws org.xml.sax.SAXException
    {
      handler.member_got(new ASN1NumericString(proxy_value));
    }

  } // nested inner-class: XER_Parser_Proxy

} // ASN1NumericString

//----------------------------------------------------------------
/*
  $Log: ASN1NumericString.java,v $
  Revision 1.5  1999/04/13 07:23:07  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:37  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:17  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:35:00  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:41  hoylen
  Imported sources

  */
//----------------------------------------------------------------
//EOF
