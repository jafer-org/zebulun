/*
 * $Id: ASN1Enumerated.java,v 1.4 1999/04/13 07:23:05 hoylen Exp $
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
 * Representation of an ASN.1 ENUMERATED.
 *
 * The <code>ENUMERATED</code> type denotes an integer from a selected set.
 * ASN.1 ENUMERATED values can be positive, negative, or zero; and can have any
 * magnitude.
 * <p>
 *
 * The current implementation limits the values of ENUMERATED to 32-bit two's
 * complement values.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:05 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1Enumerated extends ASN1Any {

  /**
   * This constant is the ASN.1 UNIVERSAL tag value for ENUMERATED.
   */

  public final static int TAG = 0x0A;

  //----------------------------------------------------------------
  /**
   * The value of the ENUMERATED is stored in this variable. This is private for
   * good information hiding, so that we are able to change its representation
   * (e.g. to a long) at a later date without affecting the interface.
   */
  private int value;

  //================================================================
  /**
   * Constructor for an ASN.1 ENUMERATED object. The tag is set to the default
   * tag of UNIVERSAL 2, and the value to the given number.
   *
   * @param	number the value of the ENUMERATED.
   */
  public ASN1Enumerated(int number) {
    value = number;
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 ENUMERATED object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception if the BER encoding is incorrect.
   */
  public ASN1Enumerated(BEREncoding ber, boolean check_tag)
          throws ASN1Exception {
    super(ber, check_tag); // superclass will call ber_decode
  }

  //----------------------------------------------------------------
  /**
   * Method for initializing the object from a BER encoding.
   *
   * @exception	ASN1EncodingException if the BER encoding is incorrect.
   */
  @Override
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1EncodingException {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG
              || ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 ENUMERATED: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + TAG + "\n");
      }
    }

    if (!(ber_enc instanceof BERPrimitive)) {
      throw new ASN1EncodingException("ASN.1 ENUMERATED: bad form, constructed");
    }

    BERPrimitive ber = (BERPrimitive) ber_enc;

    int[] encoding = ber.peek();

    if (encoding.length < 1) {
      throw new ASN1EncodingException("ASN.1 ENUMERATED: invalid encoding, length = " + encoding.length);
    }

    value = (byte) encoding[0]; // to ensure sign extension

    for (int x = 1; x < encoding.length; x++) {
      value <<= 8;
      value |= (encoding[x] & 0xff);
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the ENUMERATED.
   *
   * @return	The BER encoding of the ENUMERATED
   * @exception	ASN1Exception when the ENUMERATED is invalid and cannot be
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
   * Returns a BER encoding of the ENUMERATED. Explicitly tagged with the
   * supplied tag.
   *
   * @return	The BER encoding of the ENUMERATED
   * @exception	ASN1Exception when the ENUMERATED is invalid and cannot be
   * encoded.
   */
  @Override
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    // Calculate length of encoding

    int length = 0;

    int shifted = value;
    if (value < 0) {
      shifted = ~value;
    }

    boolean need_pad; // when MSB confuses sign (causes 1st 0x00 or 0xFF digit)

    do {
      need_pad = ((shifted & 0x80) == 0x80);

      shifted >>= 8;
      length++;
    } while (shifted != 0);

    if (need_pad) {
      length++;
    }

    // Generate BER encoding of the integer (base 256 encoding)
    int[] encoding = new int[length];

    int index = 0;
    while (0 < length) {
      encoding[index++] = ((value >> (8 * (length - 1))) & 0xff);
      length--;
    }

    return new BERPrimitive(tag_type, tag, encoding);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the integer's value.
   *
   * @param new_val the value to set the ENUMERATED to.
   * @return	the object.
   */
  public ASN1Enumerated
          set(int new_val) {
    value = new_val;
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the integer's value.
   *
   * @return	the ENUMERATED's current value.
   */
  public int
          get() {
    return value;
  }

  //----------------------------------------------------------------
  /**
   * @return a new String object representing this ASN.1 object's value.
   */
  @Override
  public String
          toString() {
    return String.valueOf(value);
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
    dest.print(String.valueOf(value));
  }

  //================================================================
  // Nested inner-class for parsing XER.
  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private final static int STATE_INIT = 0;
    private final static int STATE_START_GOT = 1;
    private final static int STATE_VALUE_GOT = 2;
    private final static int STATE_TERM = 3;

    private int state;

    private int proxy_value;

    //----------------
    public XER_Parser_Proxy() {
      super("ENUMERATED");
      state = STATE_INIT;
    }

    public XER_Parser_Proxy(String overriding_xer_tag) {
      super(overriding_xer_tag);
      state = STATE_INIT;
    }

    //----------------
    @Override
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
    @Override
    public void endElement(XERsaxHandler handler,
            String name)
            throws org.xml.sax.SAXException {
      if (name.equals(xer_tag)
              && state == STATE_VALUE_GOT) {
        // Create new ENUMERATED object
        handler.member_got(new ASN1Enumerated(proxy_value));
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
          try {
            proxy_value = java.lang.Integer.parseInt(str);
          } catch (java.lang.NumberFormatException ex) {
            handler.throw_characters_unexpected(xer_tag);
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

  } // inner class XER_Parser_Proxy

} // ASN1Enumerated

//----------------------------------------------------------------
/*
  $Log: ASN1Enumerated.java,v $
  Revision 1.4  1999/04/13 07:23:05  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.3  1999/03/17 05:45:35  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.2  1999/03/17 00:32:15  hoylen
  Added ZSQL RS

  Revision 1.1.1.1  1998/12/29 00:19:41  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
