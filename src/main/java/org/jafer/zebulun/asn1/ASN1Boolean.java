/*
 * $Id: ASN1Boolean.java,v 1.5 1999/04/13 07:23:05 hoylen Exp $
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
 * Representation of an ASN.1 <code>BOOLEAN</code>.
 * <p>
 *
 * The BOOLEAN type denotes a Boolean value: either true or false.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:05 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1Boolean extends ASN1Any {

  /**
   * This constant is the ASN.1 UNIVERSAL tag value for BOOLEAN.
   */

  public static final int TAG = 0x01;

  //----------------------------------------------------------------
  /**
   * The value of the BOOLEAN is stored in this variable.
   */
  private boolean value;

  //================================================================
  /**
   * Default constructor for an ASN.1 BOOLEAN object. It sets the tag to the
   * default of UNIVERSAL 1, and the value to bool.
   *
   * @param	bool the value of the BOOLEAN.
   */
  public ASN1Boolean(boolean bool) {
    value = bool;
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 BOOLEAN object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception if the BER encoding is incorrect.
   */
  public ASN1Boolean(BEREncoding ber, boolean check_tag)
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
   * @exception	ASN1EncodingException if the BER encoding is incorrect.
   */
  @Override
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1EncodingException {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG
              || ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 BOOLEAN: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + "TAG\n");
      }
    }

    if (ber_enc instanceof BERPrimitive) {
      BERPrimitive ber = (BERPrimitive) ber_enc;

      int[] encoding = ber.peek();

      if (encoding.length != 1) {
        throw new ASN1EncodingException("ASN.1 BOOLEAN: invalid encoding, length = " + encoding.length);
      }

      if (encoding[0] == 0) {
        value = false;
      } else {
        value = true;
      }
    } else {
      throw new ASN1EncodingException("ASN.1 BOOLEAN: bad BER: decoding constructed NOT IMPLEMENTED YET");
      //???
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the BOOLEAN.
   *
   * @return	The BER encoding of the BOOLEAN
   * @exception	ASN1Exception when the BOOLEAN is invalid and cannot be encoded.
   */
  @Override
  public BEREncoding
          ber_encode()
          throws ASN1Exception {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the BOOLEAN. Implicitly tagged.
   *
   * @return	The BER encoding of the BOOLEAN
   * @exception	ASN1Exception when the BOOLEAN is invalid and cannot be encoded.
   */
  @Override
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    // Generate BER encoding of the Boolean

    int[] encoding = new int[1];

    if (value) {
      encoding[0] = 0xff; // TRUE (in fact, any non-zero will do)
    } else {
      encoding[0] = 0x00; // FALSE
    }

    return new BERPrimitive(tag_type, tag, encoding);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the boolean's value.
   *
   * @param new_val the value to set the BOOLEAN to.
   * @return BOOLEAN
   */
  public ASN1Boolean
          set(boolean new_val) {
    value = new_val;
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the boolean's value.
   *
   * @return	the BOOLEAN's current value.
   */
  public boolean
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
    return ((value) ? "true" : "false");
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
    dest.print((value) ? "true" : "false");
  }

  //================================================================
  // Nested inner-class for parsing XER.
  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private static final int STATE_INIT = 0;
    private static final int STATE_START_GOT = 1;
    private static final int STATE_VALUE_GOT = 2;
    private static final int STATE_TERM = 3;

    private int state;

    private boolean proxy_value;

    //----------------
    public XER_Parser_Proxy() {
      super("BOOLEAN");
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
        // Create new Boolean object
        handler.member_got(new ASN1Boolean(proxy_value));
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
          if (str.equals("false")) {
            proxy_value = false;
          } else if (str.equals("true")) {
            proxy_value = true;
          } else {
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

} // ASN1Boolean

//----------------------------------------------------------------
/*
  $Log: ASN1Boolean.java,v $
  Revision 1.5  1999/04/13 07:23:05  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.4  1999/03/17 05:45:35  hoylen
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
