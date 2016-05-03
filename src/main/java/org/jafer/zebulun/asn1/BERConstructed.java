/*
 * $Id: BERConstructed.java,v 1.6 1999/04/07 01:23:47 hoylen Exp $
 *
 * Copyright (C) 1996, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */
package org.jafer.zebulun.asn1;

import java.io.OutputStream;
import java.util.Arrays;

//----------------------------------------------------------------
/**
 * BERConstructed
 *
 * This class represents a BER encoded ASN.1 object which is constructed from
 * component BER encodings.
 * <p>
 *
 * Generally it is used to store the BER encoding of constructed types (i.e.
 * SEQUENCE, SEQUENCE OF, SET, and SET OF) The end-of-content octets, if
 * required, must be added to the end of the elements by the creator.
 *
 * @see org.jafer.zebulun.asn1.BEREncoding
 *
 * @version	$Release$ $Date: 1999/04/07 01:23:47 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public class BERConstructed extends BEREncoding {

  //----------------------------------------------------------------
  /**
   * Constructor for a non-primitive BEREncoding.
   *
   * @param asn1_class The tag type.
   * @param tag The tag number.
   * @param elements The components making up the constructed BER.
   * @exception ASN1Exception If tag or tag type is invalid
   * @see org.jafer.zebulun.asn1.BEREncoding#UNIVERSAL_TAG
   * @see org.jafer.zebulun.asn1.BEREncoding#APPLICATION_TAG
   * @see org.jafer.zebulun.asn1.BEREncoding#CONTEXT_SPECIFIC_TAG
   * @see org.jafer.zebulun.asn1.BEREncoding#PRIVATE_TAG
   */

  public BERConstructed(int asn1_class, int tag, BEREncoding[] elements)
          throws ASN1Exception {
    int content_length = 0;
    for (BEREncoding element : elements) {
      content_length += element.i_total_length;
    }

    init(asn1_class, /* constructed */ true, tag, content_length);
    content_elements = Arrays.copyOf(elements, elements.length);
  }

  //----------------------------------------------------------------
  /*
   * This method outputs the encoded octets for this object
   * to the output stream.
   *
   * Note: the output is not flushed, so you <strong>must</strong>  explicitly
   * flush the output stream after calling this method to ensure that
   * the data has been written out.
   *
   * @param	dest - OutputStream to write encoding to.
   */
  @Override
  public void
          output(OutputStream dest)
          throws java.io.IOException {
    output_head(dest);

    for (BEREncoding content_element : content_elements) {
      content_element.output(dest);
    }
  }

  //----------------------------------------------------------------
  /**
   * This method returns the number of BER encoded elements that this object is
   * made up of to be returned.
   *
   * @return number of BER encoded elements
   */
  public int
          number_components() {
    return content_elements.length;
  }

  //----------------------------------------------------------------
  /**
   * This method allows the elements of the BER encoding to be examined.
   *
   * @param	index - the index of the BER object required, it must be in the
   * range, [0, number_components() - 1]
   *
   * @return BER Encoding
   */
  public BEREncoding
          elementAt(int index) {
    return content_elements[index];
  }

  //----------------------------------------------------------------
  /**
   * @return a new String object representing this ASN.1 object's value.
   */
  @Override
  public String
          toString() {
    StringBuffer str = new StringBuffer("[");
    switch (i_tag_type) {
      case BEREncoding.UNIVERSAL_TAG:
        str.append("UNIVERSAL ");
        break;
      case BEREncoding.APPLICATION_TAG:
        str.append("APPLICATION ");
        break;
      case BEREncoding.CONTEXT_SPECIFIC_TAG:
        str.append("CONTEXT SPECIFIC ");
        break;
      case BEREncoding.PRIVATE_TAG:
        str.append("PRIVATE ");
        break;
      default:
        break;
    }
    str.append(String.valueOf(i_tag)).append("]{");

    for (int x = 0; x < content_elements.length; x++) {
      if (x != 0) {
        str.append(',');
      }

      str.append(content_elements[x].toString());
    }

    str.append('}');

    return new String(str);
  }

  //----------------------------------------------------------------
  /**
   * This protected method is used to implement the "get_encoding" method.
   * @param offset initial offset
   * @param data  data
   * @return calculated offset
   */
  @Override
  protected int
          i_encoding_get(int offset, byte[] data) {
    int result = i_get_head(offset, data);

    for (BEREncoding content_element : content_elements) {
      result = content_element.i_encoding_get(result, data);
    }

    return result;
  }

  //----------------------------------------------------------------
  /**
   * This variable stores the BER encoding elements which make up this
   * constucted BER encoding (in order).
   */
  private BEREncoding[] content_elements;

//  //================================================================
//  /**
//   * Produces the XER encoding equivalent of this BER encoding. 
//   *
//   * @param	dest the destination XER encoding is written to
//   * @exception ASN1Exception if data is invalid.
//   */
//
//  public void
//    ZZZxer_encode(java.io.PrintWriter dest)
//    throws ASN1Exception
//  {
//    String title = "xer:BER";
//
//    dest.print("<" + title + " ");
//
//    switch (i_tag_type) {
//    case BEREncoding.UNIVERSAL_TAG:
//	dest.print("UNIVERSAL=");
//	break;
//    case BEREncoding.APPLICATION_TAG:
//	dest.print("APPLICATION=");
//	break;
//    case BEREncoding.CONTEXT_SPECIFIC_TAG:
//	dest.print("CONTEXTSPECIFIC=");
//	break;
//    case BEREncoding.PRIVATE_TAG:
//	dest.print("PRIVATE=");
//	break;
//    }
//    dest.print('"');
//    dest.print(String.valueOf(i_tag));
//    dest.print('"');
//
//    dest.print(">");
//
//    for (int x = 0; x < content_elements.length; x++) {
//	content_elements[x].xer_encode(dest);
//    }
//    
//    dest.print("</" + title + ">");
//  }
}

//----------------------------------------------------------------
/*
  $Log: BERConstructed.java,v $
  Revision 1.6  1999/04/07 01:23:47  hoylen
  Fixed XER encoding of ANY to be a single BER rather than as individual parts

  Revision 1.5  1999/03/17 05:45:40  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.4  1999/03/17 00:32:18  hoylen
  Added ZSQL RS

  Revision 1.3  1999/03/15 07:35:01  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.2  1998/12/29 02:24:59  hoylen
  Fixed new directory structure

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
