/*
 * $Id: ASN1ObjectIdentifier.java,v 1.5 1999/04/13 07:23:07 hoylen Exp $
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
 * Representation of an ASN.1 OBJECT IDENTIFIER.
 *
 * The <code>OBJECT IDENTIFIER</code> type denotes an object identifier, which
 * is a sequence of integer components. An OBJECT IDENTIFIER can have any number
 * of components, whch are generally non-negative. This type is a non-string
 * type.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:07 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1ObjectIdentifier extends ASN1Any {

  /**
   * This constant is the ASN.1 UNIVERSAL tag value for OBJECT IDENTIFIER.
   */

  public final static int TAG = 0x06;

  //----------------------------------------------------------------
  /**
   * The components of the OBJECT IDENTIFER are stored in this variable as an
   * array of integers.
   */
  private int[] oid;

  //================================================================
  /**
   * Constructor for an ASN.1 OBJECT IDENTIFER object. The tag is set to the
   * default tag of UNIVERSAL 6, and the given OID value.
   *
   * @param oid_value	value
   */
  public ASN1ObjectIdentifier(int[] oid_value) {
    oid = oid_value;
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 OBJECT IDENTIFIER object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */
  public ASN1ObjectIdentifier(BEREncoding ber, boolean check_tag)
          throws ASN1Exception {
    super(ber, check_tag); // superclass will call ber_decode
  }

  //----------------------------------------------------------------
  /**
   * Method for initializing the object from a BER encoding.
   *
   * @param ber_enc The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1EncodingException If the BER encoding cannot be decoded.
   */
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1EncodingException {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG
              || ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 OBJECT IDENTIFIER: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + TAG + "\n");
      }
    }

    if (!(ber_enc instanceof BERPrimitive)) {
      throw new ASN1EncodingException("ASN.1 OBJECT IDENTIFIER: bad form, constructed");
    }

    BERPrimitive ber = (BERPrimitive) ber_enc;

    int[] encoding = ber.peek();

    if (encoding.length < 2) {
      throw new ASN1EncodingException("ASN1 OBJECT IDENTIFER: invalid encoding, length = "
              + encoding.length);
    }

    // Calculate total number of components
    int num_components = 2;
    for (int byte_index = 1; byte_index < encoding.length; byte_index++) {
      // Each value is in base 128, with bit-8 set except in last byte

      if ((encoding[byte_index] & 0x80) == 0) {
        num_components++;
      }
    }

    // Allocate space to store it
    oid = new int[num_components];

    // Decode
    oid[0] = encoding[0] / 40; // first octet encodes first two values
    oid[1] = encoding[0] % 40;

    int index = 1;
    for (int component = 2; component < num_components; component++) {
      // Rest of values in base 128, with bit-8 set except for in last byte

      oid[component] = 0;

      int octet;
      do {
        octet = encoding[index++];

        oid[component] <<= 7;
        oid[component] |= (octet & 0x7f);
      } while ((octet & 0x80) != 0);
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the OBJECT IDENTIFIER. The current implementation
   * rejects negative OID components (should it?)
   *
   * @return	The BER encoding of the OBJECT IDENTIFIER
   * @exception	ASN1Exception when the OBJECT IDENTIFIER is invalid and cannot
   * be encoded. According to X.208, an OBJECT IDENTIFIER must have at least two
   * components, the first has values of (0, 1, or 2) and the second between 0
   * and 39 inclusive.
   */
  public BEREncoding
          ber_encode()
          throws ASN1Exception {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the OBJECT IDENTIFIER. The current implementation
   * rejects negative OID components (should it?)
   *
   * @return	The BER encoding of the OBJECT IDENTIFIER
   * @exception	ASN1Exception when the OBJECT IDENTIFIER is invalid and cannot
   * be encoded. According to X.208, an OBJECT IDENTIFIER must have at least two
   * components, the first has values of (0, 1, or 2) and the second between 0
   * and 39 inclusive.
   */
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    // Validity checking

    if (oid.length < 2) {
      throw new ASN1Exception("OBJECT IDENTIFIER: less than 2 components, violates X.208");
    }
    if (oid[0] < 0 || 2 < oid[0]) {
      throw new ASN1Exception("OBJECT IDENTIFIER: First component invalid, value = " + oid[0]);
    }
    if (oid[1] < 0 || 39 < oid[1]) {
      throw new ASN1Exception("OBJECT IDENTIFIER: Second component invalid, value = " + oid[1]);
    }

    // Calculate total length of encoding
    int num_bytes = 1;
    for (int index = 2; index < oid.length; index++) {
      // Each value in base 128, with bit-8 set except for in last byte

      int tmp_value = oid[index];

      if (tmp_value < 0) {
        throw new ASN1Exception("OBJECT IDENTIFIER: component " + (index + 1)
                + " is negative, value = " + tmp_value);
      }

      do {
        num_bytes++;
        tmp_value >>= 7;
      } while (tmp_value != 0);
    }

    // Encode
    int[] octets = new int[num_bytes];

    octets[0] = (40 * oid[0]) + oid[1]; // first octet encodes first two values

    int bcount = 0;
    for (int index = 2; index < oid.length; index++) {
      // Rest of values in base 128, with bit-8 set except for in last byte

      int number_bytes = 0;
      int tmp_value = oid[index];
      do {
        number_bytes++;
        tmp_value >>= 7;
      } while (tmp_value != 0);

      tmp_value = oid[index];

      for (int digit = number_bytes - 1; 0 <= digit; digit--) {
        octets[++bcount] = (tmp_value >> (digit * 7)) & 0x7f;
        if (digit != 0) {
          octets[bcount] |= 0x80; // bit-8 set in last byte
        }
      }
    }

    return new BERPrimitive(tag_type, tag, octets);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the OBJECT IDENTIFIER's value.
   *
   * @param new_val the value to set the OBJECT IDENTIFIER to.
   * @return OBJECT IDENTIFIER
   *
   */
  public ASN1ObjectIdentifier
          set(int[] new_val) {
    oid = new_val;
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the OBJECT IDENTIFIER's value. The returned value should not
   * be modified in any way.
   *
   * @return	the OBJECT IDENTIFIER's current value.
   */
  public int[]
          get() {
    return oid;
  }

  //----------------------------------------------------------------
  /**
   * Returns a new String object representing this ASN.1 object's value.
   */
  public String
          toString() {
    StringBuffer str = new StringBuffer();

    for (int index = 0; index < oid.length; index++) {
      if (index != 0) {
        str.append('.');
      }

      str.append(String.valueOf(oid[index]));
    }

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
  public void
          xer_encode(java.io.PrintWriter dest)
          throws ASN1Exception {
    for (int index = 0; index < oid.length; index++) {
      if (index != 0) {
        dest.print('.');
      }

      dest.print(String.valueOf(oid[index]));
    }
  }

  //================================================================
  // Nested inner-class for parsing XER.
  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private final static int STATE_INIT = 0;
    private final static int STATE_START_GOT = 1;
    private final static int STATE_VALUE_GOT = 2;
    private final static int STATE_TERM = 3;

    private int state;

    private int[] proxy_value;

    //----------------
    public XER_Parser_Proxy() {
      super("OBJECT_IDENTIFIER");
      state = STATE_INIT;
    }

    public XER_Parser_Proxy(String overriding_xer_tag) {
      super(overriding_xer_tag);
      state = STATE_INIT;
    }

    //----------------
    public void startElement(XERsaxHandler handler,
            String name,
            org.xml.sax.AttributeList atts)
            throws org.xml.sax.SAXException {
      if (name.equals(xer_tag)
              && state == STATE_INIT) {
        state = STATE_START_GOT;

      } else {
        handler.throw_start_unexpected(xer_tag, name);
      }
    }

    //----------------
    public void endElement(XERsaxHandler handler,
            String name)
            throws org.xml.sax.SAXException {
      if (name.equals(xer_tag)
              && state == STATE_VALUE_GOT) {
        // Create new OBJECT IDENTIFIER object
        handler.member_got(new ASN1ObjectIdentifier(proxy_value));
        state = STATE_TERM;

      } else {
        handler.throw_end_unexpected(xer_tag, name);
      }
    }

    //----------------
    public void characters(XERsaxHandler handler,
            char[] ch,
            int start,
            int length)
            throws org.xml.sax.SAXException {
      int begin = start;

      if (state == STATE_START_GOT) {
        int end = begin + length;

        while (begin < end && Character.isWhitespace(ch[begin])) {
          begin++;
        }

        if (begin < end) {
          // Found some non-whitespace characters

          int nws_end = begin + 1;
          while (nws_end < end && !Character.isWhitespace(ch[nws_end])) {
            nws_end++;
          }

          String str = new String(ch, begin, nws_end - begin);

          java.util.Vector vect = new java.util.Vector();

          java.util.StringTokenizer st = new java.util.StringTokenizer(str, ".");
          while (st.hasMoreTokens()) {
            try {
              int component = java.lang.Integer.parseInt(st.nextToken());
              if (component < 0) {
                handler.throw_characters_unexpected(xer_tag);
              }

              vect.addElement(new java.lang.Integer(component));
            } catch (java.lang.NumberFormatException ex) {
              handler.throw_characters_unexpected(xer_tag);
            }
          }

          if (vect.isEmpty()) {
            // An object identifier must contain at least one element
            handler.throw_characters_unexpected(xer_tag);
          }
          int size = vect.size();
          proxy_value = new int[size];
          for (int x = 0; x < size; x++) {
            proxy_value[x] = ((java.lang.Integer) vect.elementAt(x)).intValue();
          }

          // Check that remaining characters are all whitespace
          while (nws_end < end) {
            if (!Character.isWhitespace(ch[nws_end])) {
              handler.throw_characters_unexpected(xer_tag);
            }
            nws_end++;
          }

          state = STATE_VALUE_GOT;

        } else {
          // All whitespace: ignore
        }

      } else {
        handler.throw_characters_unexpected(xer_tag);
      }
    }

  } // nested inner-class: XER_Parser_Proxy

} // ASN1ObjectIdentifier

//----------------------------------------------------------------
/*
  $Log: ASN1ObjectIdentifier.java,v $
  Revision 1.5  1999/04/13 07:23:07  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:38  hoylen
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
