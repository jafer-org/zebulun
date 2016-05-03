/*
 * $Id: BERPrimitive.java,v 1.6 1999/04/07 01:23:47 hoylen Exp $
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

//----------------------------------------------------------------
/**
 * This class represents a primitive ASN.1 object encoded according to the Basic
 * Encoding Rules.
 * <p>
 *
 * <em>Information technology - Open Systems Interconnection - Specification of
 * basic encoding rules for Abstract Syntax Notation One (ASN.1)</em>
 * AS 3626-1991 ISO/IEC 8825:1990
 *
 * @see org.jafer.zebulun.asn1.BEREncoding
 *
 * @version	$Release$ $Date: 1999/04/07 01:23:47 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public class BERPrimitive extends BEREncoding {

  //----------------------------------------------------------------
  /**
   * Constructor. Note that the contents is int[] because this is the internal
   * representation, which can only be used by the ASN.1 standard object
   * classes. It is not intended that higher level classes create BERPrimitives
   * directly.
   *
   * @see asn1.BEREncoding#UNIVERSAL_TAG
   * @see asn1.BEREncoding#APPLICATION_TAG
   * @see asn1.BEREncoding#CONTEXT_SPECIFIC_TAG
   * @see asn1.BEREncoding#PRIVATE_TAG
   */

  BERPrimitive(int asn1_class, int tag, int[] contents)
          throws ASN1Exception {
    init(asn1_class, /* constructed */ false, tag, contents.length);
    contents_octets = contents;
  }

  //----------------------------------------------------------------
  /**
   * This method allows the content octets to be examined. Once again, only the
   * ASN.1 standard objects should be using this.
   */
  int[]
          peek() {
    return contents_octets;
  }

  //----------------------------------------------------------------
  /**
   * This method outputs the encoded octets to the destination OutputStream.
   *
   * Note: the output is not flushed, so you <strong>must</strong> explicitly
   * flush the output stream after calling this method to ensure that the data
   * has been written out.
   *
   * @param	dest - OutputStream to write encoding to.
   * @throws java.io.IOException
   */
  @Override
  public void
          output(OutputStream dest)
          throws java.io.IOException {
    output_head(dest);
    output_bytes(contents_octets, dest);
  }

  //----------------------------------------------------------------
  /**
   * @return a new String object representing this BER encoded ASN.1 object's
   * value.
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
    str.append(String.valueOf(i_tag)).append("] '");

    for (int x = 0; x < contents_octets.length; x++) {
      // Dump each octet in hex

      int octet = contents_octets[x];
      char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f'};

      str.append(hex[((octet >> 4) & 0x0f)]);
      str.append(hex[(octet & 0x0f)]);
    }

    str.append("'H");

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

    for (int n = 0; n < contents_octets.length; n++) {
      data[result++] = (byte) contents_octets[n];
    }

    return result;
  }

  //----------------------------------------------------------------
  /**
   * The octets of the encoding are stored in this array. They are internally
   * stored as int[] for efficiency over byte[].
   */
  private int[] contents_octets;

//  //================================================================
//  /**
//   * Produces the XER encoding equivalent of this BER encoding. 
//   *
//   * @param	dest the destination XER encoding is written to
//   * @exception ASN1Exception if data is invalid.
//   */
//
//public void
//ZZZxer_encode(java.io.PrintWriter dest)
//  throws ASN1Exception
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
//    dest.print(" xer:enc=\"hex\">");
//
//    for (int x = 0; x < contents_octets.length; x++) {
//	// Dump each octet in hex
//
//	int octet = contents_octets[x];
//	char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//		       'a', 'b', 'c', 'd', 'e', 'f' };
//
//	dest.print(hex[((octet >> 4) & 0x0f)]);
//	dest.print(hex[(octet & 0x0f)]);
//    }
//    
//    dest.print("</" + title + ">");
//  }
} // BERPrimitive

//----------------------------------------------------------------
/*
  $Log: BERPrimitive.java,v $
  Revision 1.6  1999/04/07 01:23:47  hoylen
  Fixed XER encoding of ANY to be a single BER rather than as individual parts

  Revision 1.5  1999/03/17 05:45:41  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.4  1999/03/17 00:32:19  hoylen
  Added ZSQL RS

  Revision 1.3  1999/03/15 07:35:02  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.2  1998/12/29 02:24:59  hoylen
  Fixed new directory structure

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
