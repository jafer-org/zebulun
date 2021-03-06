/*
 * $Id: ASN1OctetString.java,v 1.7 1999/04/13 07:23:07 hoylen Exp $
 *
 * Copyright (C) 1996, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */
package org.jafer.zebulun.asn1;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

//----------------------------------------------------------------
/**
 * Representation of an ASN.1 OCTET STRING.
 *
 * This class is used to store an ASN.1 OCTET STRING which is an arbitary string
 * of octets (eight-bit values). An OCTET STRING can have any length including
 * zero. The type is a string type.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:07 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public class ASN1OctetString extends ASN1Any {

  /**
   * This constant is the ASN.1 UNIVERSAL tag value for OCTET STRING.
   */

  public static final int TAG = 0x04;

  //----------------------------------------------------------------
  /**
   * Constructor for an OCTET STRING object. The tag is set to the default of
   * UNIVERSAL 4, and its value to the given bytes.
   *
   * @param data	value
   */
  public ASN1OctetString(byte[] data) {
    octets = new byte[data.length];
    System.arraycopy(data, 0, octets, 0, data.length);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an OCTET STRING object. The tag is set to the default of
   * UNIVERSAL 4, and its value to the lower bytes of the characters of the
   * given string.
   *
   * @param str	value
   */
  public ASN1OctetString(String str) {
    try {
      octets = str.getBytes("ISO-8859-1");
    } catch (UnsupportedEncodingException ex) {
      octets = str.getBytes();
    }
  }

  //----------------------------------------------------------------
  /**
   * Constructor for a OCTET STRING object from a primitive BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */
  public ASN1OctetString(BEREncoding ber, boolean check_tag)
          throws ASN1Exception {
    super(ber, check_tag); // superclass will call ber_decode
  }

  //----------------------------------------------------------------
  /**
   * Method for initializing the object from a BER encoding.
   *
   * @param ber_enc The BER encoding to use.
   * @param check_tag If true, it checks the tag. Does nothing for ASN1Any.
   * @exception	ASN1EncodingException If the BER cannot be decoded.
   */
  @Override
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1EncodingException {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG
              || ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 OCTET STRING: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + TAG + "\n");
      }
    }

    if (ber_enc instanceof BERPrimitive) {
      BERPrimitive ber = (BERPrimitive) ber_enc;

      int[] encoding = ber.peek();

      StringBuilder buf = new StringBuilder(encoding.length);

      for (int x = 0; x < encoding.length; x++) {
        buf.append((char) (encoding[x] & 0x00ff));
      }

      try {
        octets = new String(buf).getBytes("ISO-8859-1");
      } catch (UnsupportedEncodingException ex) {
        octets = new String(buf).getBytes();
      }

    } else {
      // not implemented yet ???

      throw new ASN1EncodingException("ASN.1 OCTET STRING: decode from constructed NOT IMPLEMENTED YET");
    }
  }

  //----------------------------------------------------------------
  /**
   * Makes a BER encoding of the OCTET STRING.
   *
   * OCTET STRINGs can have a primitive encoding and a constructed encoding.
   * This implemented performs the primitive encoding (which is the DER form).
   *
   * @return	The BER encoding of the OCTET STRING
   * @exception	ASN1Exception when the OCTET STRING is invalid and cannot be
   * encoded.
   */
  @Override
  public BEREncoding
          ber_encode()
          throws ASN1Exception {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  //----------------------------------------------------------------
  /**
   * Makes a BER encoding of the OCTET STRING.
   *
   * OCTET STRINGs can have a primitive encoding and a constructed encoding.
   * This implemented performs the primitive encoding (which is the DER form).
   *
   * @return	The BER encoding of the OCTET STRING
   * @exception	ASN1Exception when the OCTET STRING is invalid and cannot be
   * encoded.
   */
  @Override
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    int size = octets.length;
    int[] encoding = new int[size];

    // Generate BER encoding of the Octet String
    for (int index = 0; index < size; index++) {
      encoding[index] = octets[index] & 0x00ff;
    }

    return new BERPrimitive(tag_type, tag, encoding);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the OCTET STRING's value.
   *
   * @param octet_array the value to set the OCTET STRING to.
   * @return	the object.
   */
  public ASN1OctetString
          set(byte[] octet_array) {

    octets = new byte[octet_array.length];
    System.arraycopy(octet_array, 0, octets, 0, octet_array.length);
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to set the OCTET STRING's value.
   *
   * @param	str the value to set the OCTET STRING to.
   * @return	the object.
   */
  public ASN1OctetString
          set(String str) {
    try {
      octets = str.getBytes("ISO-8859-1");
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(ASN1OctetString.class.getName()).log(Level.WARNING, "ISO-8859-1 character encoding not supported.", ex);
      octets = str.getBytes();
    }
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the OCTET STRING's value as a String.
   *
   * @return	the OCTET STRING's current value.
   */
  public String
          get() {
    try {
      return new String(octets, "ISO-8859-1");
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(ASN1OctetString.class.getName()).log(Level.WARNING, "ISO-8859-1 character encoding not supported.", ex);
      return new String(octets);
    }
  }

  //----------------------------------------------------------------
  /**
   * Method to get the OCTET STRING's value as an array of bytes.
   *
   * @return	the OCTET STRING's current value.
   */
  public byte[]
          get_bytes() {
    return Arrays.copyOf(octets, octets.length);
  }

  //----------------------------------------------------------------
  private static final char[] OCT = {
    '0', '1', '2', '3', '4', '5', '6', '7'
  };

  private static final char[] HEX = {
    '0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
  };

  /**
   * @return a new String object representing this ASN.1 object's value.
   */
  @Override
  public String
          toString() {
    int size = octets.length;
    // Buffer: make it big just in case everything needs to be encoded
    StringBuilder buf = new StringBuilder(32 + (size * 4));

    // Determine whether to use hexadecimal form or text form
    int printable = 0;
    int binary = 0;

    for (int x = 0; x < size; x++) {
      char octet = (char) octets[x];

      if ((' ' <= octet && octet <= '~')
              || octet == '\n') {
        printable++;
      } else {
        binary++;
      }
    }

    if (binary <= printable) {
      // Display as a printable string

      buf.append('"');

      for (int x = 0; x < size; x++) {
        char octet = (char) octets[x];

        if (' ' <= octet && octet <= '~') {
          // Printable character

          if (octet == '\\' || octet == '"' || octet == '\'') {
            buf.append('\\'); // escape character
          }

          buf.append(octet);

        } else if (octet == '\n') {
          buf.append("\\n");
        } else if (octet == '\t') {
          buf.append("\\t");
        } else if (octet == '\r') {
          buf.append("\\r");
        } else if (octet == '\b') {
          buf.append("\\b");
        } else if (octet == '\f') {
          buf.append("\\f");

        } else {
          // Unprintable characters, use octal escape

          buf.append('\\');

          buf.append(OCT[((octet >> 6) & 0x07)]);
          buf.append(OCT[((octet >> 3) & 0x07)]);
          buf.append(OCT[(octet & 0x07)]);
        }
      }
      buf.append('"');

    } else {
      // Binary string, display as hex

      buf.append('\'');

      for (int x = 0; x < size; x++) {
        char octet = (char) octets[x];

        buf.append(HEX[((octet >> 4) & 0x0f)]);
        buf.append(HEX[(octet & 0x0f)]);
      }

      buf.append("'H");
    }

    return new String(buf);
  }

  //================================================================
  /**
   * The values of the OCTET STRING are stored in this string. Only the lower
   * bytes are valid.
   */
  private byte[] octets;

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
    int size = octets.length;

    // Determine whether to use hexadecimal form or text form
    int printable = 0;
    int nonprintable = 0;

    for (int x = 0; x < size; x++) {
      char octet = (char) octets[x];

      if ((' ' <= octet && octet <= '~'
              && octet != '&'
              && octet != '<'
              && octet != '>')
              || octet == '\n') {
        printable++;
      } else {
        nonprintable++;
      }
    }

    if (nonprintable == 0) {
      // Display as a printable string

      for (int x = 0; x < size; x++) {
        dest.print((char) octets[x]);
      }

    } else {
      // Binary string, display as hex

      dest.print("<xer:Hex>");

      for (int x = 0; x < size; x++) {
        char octet = (char) octets[x];

        dest.print(HEX[((octet >> 4) & 0x0f)]);
        dest.print(HEX[(octet & 0x0f)]);
      }

      dest.print("</xer:Hex>");
    }
  }

  //================================================================
  // Nested inner-class for parsing XER.
  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private static final int STATE_INIT = 0;
    private static final int STATE_START_GOT = 1;
    private static final int STATE_VALUE_GOT = 2;
    private static final int STATE_TERM = 3;

    private int state;

    protected String proxy_value;

    //----------------
    public XER_Parser_Proxy() {
      super("OCTET_STRING");
      state = STATE_INIT;
    }

    public XER_Parser_Proxy(String overriding_xer_tag) {
      super(overriding_xer_tag);
      state = STATE_INIT;
    }

    //----------------
    @Override
    public void startElement(XERsaxHandler handler,
            String uri,
            String localName,
            String qName,
            org.xml.sax.Attributes atts)
            throws org.xml.sax.SAXException {
      if (localName.equals(xer_tag)
              && state == STATE_INIT) {
        proxy_value = ""; // empty string
        state = STATE_START_GOT;

      } else {
        handler.throw_start_unexpected(xer_tag, localName);
      }

    }

    //----------------
    @Override
    public void endElement(XERsaxHandler handler,
            String name)
            throws org.xml.sax.SAXException {
      if (name.equals(xer_tag)
              && state == STATE_VALUE_GOT) {
        // Create new OCTET STRING object

        handler.member_got(new ASN1OctetString(proxy_value));

        state = STATE_TERM;

      } else {
        handler.throw_end_unexpected(xer_tag, name);
      }
    }

    //----------------
    @Override
    public void characters(XERsaxHandler handler,
            char[] ch,
            int start,
            int length)
            throws org.xml.sax.SAXException {
      int begin = start;

      if (state == STATE_START_GOT) {
        int end = begin + length;

        // Skip leading whitespace characters
        while (begin < end && Character.isWhitespace(ch[begin])) {
          begin++;
        }

        // Skip trailing whitespace characters
        while (begin < end && Character.isWhitespace(ch[end - 1])) {
          end--;
        }

        // Get the value
        proxy_value = new String(ch, begin, end - begin);
        state = STATE_VALUE_GOT;

      } else {
        handler.throw_characters_unexpected(xer_tag);
      }
    }

  } // inner class XER_Parser_Proxy

} // ASN1OctetString

//----------------------------------------------------------------
/*
  $Log: ASN1OctetString.java,v $
  Revision 1.7  1999/04/13 07:23:07  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.6  1999/04/07 01:23:47  hoylen
  Fixed XER encoding of ANY to be a single BER rather than as individual parts

  Revision 1.5  1999/04/06 07:07:23  hoylen
  Added hack to not use entities for binary data because the non-printable
  ASCII characters are invalid XML characters. Hex is used instead.

  Revision 1.4  1999/03/17 05:45:38  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:17  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:35:00  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
