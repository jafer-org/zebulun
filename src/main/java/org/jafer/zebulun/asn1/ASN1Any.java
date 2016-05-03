/*
 * $Id: ASN1Any.java,v 1.5 1999/04/13 07:23:05 hoylen Exp $
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
 * ASN1 ANY
 * <p>
 *
 * The ANY type denotes an arbitary value of an arbitary type. This class also
 * serves as the base class for all ASN.1 classes.
 * <p>
 *
 * The ASN.1 syntax is defined in
 * <em>Information Technology - Open Systems Interconnection - Specification of
 * Abstract Syntax Notation One (ASN.1)</em>
 * AS 3625-1991 ISO/IEC 8824:1990
 * <p>
 *
 * The current implementation assumes values are limited to 32-bit signed
 * integers for tags, lengths, etc.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:05 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
public class ASN1Any {

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 ANY object.
   */

  public ASN1Any() {
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 ANY object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Does nothing for ASN1Any.
   * @exception	ASN1Exception if the BER encoding is incorrect.
   */
  public ASN1Any(BEREncoding ber, boolean check_tag)
          throws ASN1Exception {
    // tag type and number will be set by ber_decode, which is a
    // virtual method.

    ber_decode(ber, check_tag);
  }

  //----------------------------------------------------------------
  /**
   * Method for initializing the object from a BER encoding. All classes derived
   * from this one must implement a version of this.
   *
   * This method will be overridden by derived types.
   *
   * @param ber_enc The BER encoding to use.
   * @param check_tag If true, it checks the tag. Does nothing for ASN1Any.
   * @exception	ASN1Exception If the BER encoding is incorrect. Never occurs for
   * ASN1Any.
   */
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1Exception {
    asn1any_ber = ber_enc;
  }

  //----------------------------------------------------------------
  /**
   * Constructs a BER encoding for this ASN.1 object. This method is usually
   * overridden by a subclass method.
   *
   * @exception	ASN1Exception If the object cannot be BER encoded.
   * @return BER encoding
   */
  public BEREncoding
          ber_encode()
          throws ASN1Exception {
    if (asn1any_ber == null) {
      throw new ASN1EncodingException("ASN.1 ANY: uninitialised");
    }

    return asn1any_ber;
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of ASN1Any, implicitly tagged.
   *
   * @param	tag_type The type of the implicit tag.
   * @param	tag The implicit tag number.
   * @return	The BER encoding of the object.
   * @exception	ASN1Exception when invalid or cannot be encoded.
   */
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    if (asn1any_ber == null) {
      throw new ASN1EncodingException("ASN.1 ANY: uninitialised");
    }

    // Can't really do it, this method is really for overriding
    // in the subclasses.
    throw new ASN1EncodingException("ASN.1 ANY: cannot implicitly tag");
  }

  //----------------------------------------------------------------
  /**
   * Returns a new String object representing this ASN.1 object's value.
   *
   * @return A text string representation.
   */
  @Override
  public String
          toString() {
    if (asn1any_ber == null) {
      return "<empty ASN.1 ANY>";
    }

    return asn1any_ber.toString();
  }

  //================================================================
  /* Hack to support creation of ASN1 ANY types from a BER and have
     it behave normally.  This is not used by any other ASN.1 subclasses.
     It is a waste of space in that respect. */
  private BEREncoding asn1any_ber;

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
          throws ASN1Exception {
    if (asn1any_ber != null) {
      asn1any_ber.xer_encode(dest);

    } else {
      throw new ASN1Exception("XER encoding not available for "
              + this.getClass().getName());
    }
  }

} // ASN1Any

//----------------------------------------------------------------
/*
  $Log: ASN1Any.java,v $
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
