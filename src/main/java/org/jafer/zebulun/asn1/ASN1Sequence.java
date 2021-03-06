/*
 * $Id: ASN1Sequence.java,v 1.4 1999/04/13 07:23:08 hoylen Exp $
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

import java.util.Arrays;

/**
 * Representation of an ASN.1 SEQUENCE.
 * <p>
 *
 * The <code>SEQUENCE</code> type denotes an ordered collection of one or more
 * types. The SEQUENCE OF type denotes an ordered collection of zero or more
 * occurances of a given type.
 * <p>
 *
 * This class is available for the generic handling of ASN.1 definitions.
 * However, specialised ASN.1 productions will usually use their own encoding
 * for SEQUENCES directly.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:08 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1Sequence extends ASN1Any {

  /**
   * This constant tag value is the ASN.1 UNIVERSAL tag value for a SEQUENCE or
   * a SEQUENCE OF type.
   */

  public static final int TAG = 0x10;

  //----------------------------------------------------------------
  /**
   * The values of the SEQUENCE are stored in this array.
   */
  private ASN1Any[] elements;

  //================================================================
  /**
   * Default constructor for an ASN.1 SEQUENCE object. The tag is set to the
   * default value.
   *
   * @param element_array the ASN.1 objects that make up the sequence.
   */
  public ASN1Sequence(ASN1Any[] element_array) {
    elements = Arrays.copyOf(element_array, element_array.length);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 SEQUENCE object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */
  public ASN1Sequence(BEREncoding ber, boolean check_tag)
          throws ASN1Exception {
    super(ber, check_tag);
  }

  //----------------------------------------------------------------
  /**
   * Method for initializing the object from a BER encoding.
   *
   * @param ber_enc The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */
  @Override
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1Exception {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG
              || ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 SEQUENCE: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + TAG + "\n");
      }
    }

    if (ber_enc instanceof BERPrimitive) {
      throw new ASN1EncodingException("ASN.1 SEQUENCE: bad form, primitive");
    }

    if (!(ber_enc instanceof BERConstructed)) {
      throw new ASN1EncodingException("ASN.1 EXTERNAL: incorrect form, not BERConstructed");      
    }

    BERConstructed ber = (BERConstructed) ber_enc;

    int len = ber.number_components();

    elements = new ASN1Any[len];

    for (int x = 0; x < len; x++) {
      elements[x] = ASN1Decoder.toASN1(ber.elementAt(x));
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

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the SEQUENCE implcitly tagged.
   *
   * @param tag_type The type of the implcit tag
   * @param tag The implicit tag number
   * @return	The BER encoding of the SEQUENCE
   * @exception	ASN1Exception when the SEQUENCE is invalid and cannot be
   * encoded.
   */
  @Override
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    int len = elements.length;
    BEREncoding[] encodings = new BEREncoding[len];

    for (int index = 0; index < len; index++) {
      encodings[index] = elements[index].ber_encode();
    }

    return new BERConstructed(tag_type, tag, encodings);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the SEQUENCE's elements.
   *
   * @param element_array an array of ASN.1 object.
   * @return SEQUENCE
   */
  public ASN1Sequence
          set(ASN1Any[] element_array) {
    elements = Arrays.copyOf(element_array, element_array.length);
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the elements of the SEQUENCE.
   *
   * @return	an array containing the SEQUENCE's elements.
   */
  public ASN1Any[]
          get() {
    return Arrays.copyOf(elements, elements.length);
  }

  //----------------------------------------------------------------
  /**
   * @return a new String object representing this ASN.1 object's value.
   */
  @Override
  public String
          toString() {
    StringBuilder str = new StringBuilder("{");

    for (int index = 0; index < elements.length; index++) {
      if (index != 0) {
        str.append(", ");
      }

      str.append(elements[index].toString());
    }

    str.append('}');

    return new String(str);
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
  @Override
  public void
          xer_encode(java.io.PrintWriter dest)
          throws ASN1Exception {
    for (ASN1Any element : elements) {
      element.xer_encode(dest);
    }
  }

} // ASN1Sequence

//----------------------------------------------------------------
/*
  $Log: ASN1Sequence.java,v $
  Revision 1.4  1999/04/13 07:23:08  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.3  1999/04/06 07:07:43  hoylen
  Added XML encoding (not decoding) routine.

  Revision 1.2  1999/03/17 05:45:38  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
