/*
 * $Id: ASN1IA5String.java,v 1.5 1999/04/13 07:23:06 hoylen Exp $
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
 * Representation for ASN.1 IA5String.
 *
 * The <code>IA5String</code> type denotes an arbitrary string of IA5
 * characters. IA5 stands for International Alphabet 5, which is
 * the same as ASCII. The character set includes non-printing control
 * characters. An IA5String can be of any length, including zero.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:06 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */

public final class ASN1IA5String extends ASN1OctetString
{
  /**
   * This constant is the ASN.1 UNIVERSAL tag value for IA5String.
   */

public final static int TAG = 0x16;

  //----------------------------------------------------------------
  /**
   * Constructor for a IA5String object. It sets the tag to the
   * default value of UNIVERSAL 22 (0x16).
   * 
   * @param value value
   */

public 
ASN1IA5String(String value)
  {
    super(value);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for a IA5String object from a primitive BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */

public
ASN1IA5String(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
  {
    super(ber, false);

    if (check_tag) {
      if (ber.tag_get() != TAG || 
	  ber.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
	throw new ASN1EncodingException
	  ("ASN.1 IA5String: bad BER: tag=" + ber.tag_get() + 
	   " expected " + TAG + "\n");
      }
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding with no implicit tag.
   *
   * @return	The BER encoding
   * @exception	ASN1Exception when the object is invalid
   *		and cannot be encoded.
   */

public BEREncoding
ber_encode()
       throws ASN1Exception
  {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  /*
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
      super("IA5String");
    }

    public XER_Parser_Proxy(String overriding_xer_tag)
    {
      super(overriding_xer_tag);
    }

    public void endElement(XERsaxHandler handler,
			   String name)
      throws org.xml.sax.SAXException
    {
      handler.member_got(new ASN1IA5String(proxy_value));
    }

  } // nested inner-class: XER_Parser_Proxy

} // ASN1IA5String

//----------------------------------------------------------------
/*
  $Log: ASN1IA5String.java,v $
  Revision 1.5  1999/04/13 07:23:06  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:37  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:16  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:34:59  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:41  hoylen
  Imported sources

  */
//----------------------------------------------------------------
//EOF
