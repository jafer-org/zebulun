/*
 * $Id: ASN1T61String.java,v 1.5 1999/04/13 07:23:08 hoylen Exp $
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
 * Representation for ASN.1 T61String.
 *
 * The <code>T61String</code> type denotes an arbitrary string
 * of T.61 characters, or TeletextString. T.61 is an eight-bit extension
 * to the ASCII character set. Special escape sequences specify the
 * interpretation of subsequent character values (e.g. as
 * Japanese rather than Latin). A T61String value can be of 
 * any length, including zero. This type is a string type.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:08 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */

//----------------------------------------------------------------

public final class ASN1T61String extends ASN1OctetString
{
  /**
   * This constant is the UNIVERSAL tag value for T61String.
   */

public final static int TAG = 0x14;

  //----------------------------------------------------------------
  /**
   * Constructor for an T61String object. It sets the tag to the
   * default value of UNIVERSAL 20 (0x14).
   *
   * @param value The string value.
   */

public 
ASN1T61String(String value)
  {
    super(value);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for a T61String object from a primitive BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */

public
ASN1T61String(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
  {
    super(ber, false);

    if (check_tag) {
      if (ber.tag_get() != TAG || 
	  ber.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
	throw new ASN1EncodingException
	  ("ASN.1 T61String: bad BER: tag=" + ber.tag_get() + 
	   " expected " + TAG + "\n");
      }
    }
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
      super("T61String");
    }

    public XER_Parser_Proxy(String overriding_xer_tag)
    {
      super(overriding_xer_tag);
    }

    public void endElement(XERsaxHandler handler,
			   String name)
      throws org.xml.sax.SAXException
    {
      handler.member_got(new ASN1T61String(proxy_value));
    }

  } // nested inner-class: XER_Parser_Proxy

} // class: ASN1T61String

//----------------------------------------------------------------
/*
  $Log: ASN1T61String.java,v $
  Revision 1.5  1999/04/13 07:23:08  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:39  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:18  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:35:01  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

  */
//----------------------------------------------------------------
//EOF
