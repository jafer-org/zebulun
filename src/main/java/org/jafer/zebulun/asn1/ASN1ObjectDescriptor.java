/*
 * $Id: ASN1ObjectDescriptor.java,v 1.3 1999/03/17 05:45:37 hoylen Exp $
 *
 * Copyright (C) 1996, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */
package org.jafer.zebulun.asn1;

//----------------------------------------------------------------
/**
 * Representation for an ASN.1 OBJECT DESCRIPTOR.
 *
 * The ASN.1 OBJECT DESCRIPTOR consists of a human-readable text which serves to
 * describe an information object.
 *
 * According to clause 35.3 of the standard, ObjectDescriptor ::= [UNIVERSAL 7]
 * IMPLICIT GraphicString
 *
 * @version	$Release$ $Date: 1999/03/17 05:45:37 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1ObjectDescriptor extends ASN1GraphicString {

  //----------------------------------------------------------------
  /**
   * This constant is the UNIVERSAL tag value for ObjectDescriptor.
   */

  public final static int TAG = 0x07;

  /**
   * Constructor for an ObjectDescriptor object. It sets the tag to the default
   * value of UNIVERSAL 7, and the descriptor to the given value.
   *
   * @param descriptor value
   */
  public ASN1ObjectDescriptor(String descriptor) {
    super(descriptor);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for a ObjectDescriptor object from a primitive BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception if the BER encoding is incorrect.
   */
  public ASN1ObjectDescriptor(BEREncoding ber, boolean check_tag)
          throws ASN1Exception {
    super(ber, false);

    if (check_tag) {
      if (ber.tag_get() != TAG
              || ber.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 ObjectDescriptor: bad BER: tag=" + ber.tag_get()
                + " expected " + TAG + "\n");
      }
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding with no implicit tag.
   *
   * @return	The BER encoding
   * @exception	ASN1Exception when the object is invalid and cannot be encoded.
   */
  @Override
  public BEREncoding
          ber_encode()
          throws ASN1Exception {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  /* ber_encode()
   * ber_decode()
   * set()
   * get() 
   * toString()
   * are inherited
   */
  //================================================================
  // XER (XML Encoding Rules) code
  public static class XER_Parser_Proxy
          extends ASN1OctetString.XER_Parser_Proxy {

    public XER_Parser_Proxy() {
      super("ObjectDescriptor");
    }

    public XER_Parser_Proxy(String overriding_xer_tag) {
      super(overriding_xer_tag);
    }

    @Override
    public void endElement(XERsaxHandler handler,
            String name)
            throws org.xml.sax.SAXException {
      handler.member_got(new ASN1ObjectDescriptor(proxy_value));
    }
  }

} // ASN1ObjectDescriptor

//----------------------------------------------------------------
/*
  $Log: ASN1ObjectDescriptor.java,v $
  Revision 1.3  1999/03/17 05:45:37  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.2  1999/03/15 07:35:00  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:41  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
