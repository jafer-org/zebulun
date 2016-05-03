/*
 * $Id: ASN1Real.java,v 1.3 1999/04/20 01:50:46 hoylen Exp $
 *
 * Copyright (C) 1996, 1999, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */
package org.jafer.zebulun.asn1;

//----------------------------------------------------------------

import org.xml.sax.SAXException;

/**
 * Representation of an ASN.1 REAL.
 * <p>
 * The <code>REAL</code> type denotes a real number.
 * <p>
 * An ASN.1 REAL may take on normal values as well as
 * java.lang.Double.NEGATIVE_INFINITY and java.lang.Double.POSITIVE_INFINITY. It
 * cannot be NaN.
 * <p>
 * <em>
 * Currently, REALs are not supported by Zebulun. This is just a stub.
 * </em>
 * <p>
 *
 *
 * @version	$Release$ $Date: 1999/04/20 01:50:46 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1Real extends ASN1Any {

  /**
   * This constant is the ASN.1 UNIVERSAL tag value for REAL.
   */

  public static final int TAG = 0x09;

  //----------------------------------------------------------------
  /**
   * The value of the REAL is stored in this variable.
   * <p>
   * This is private for good information hiding, so that we are able to change
   * its representation at a later date without affecting the interface.
   */
  private double value;

  //================================================================
  /**
   * Constructor for an ASN.1 REAL object. The tag is set to the default tag of
   * UNIVERSAL 9. The value is invalid.
   */
  public ASN1Real() {
    value = java.lang.Double.NaN;
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 REAL object. The tag is set to the default tag of
   * UNIVERSAL 9, and the value to the given number.
   *
   * @param	number the value of the REAL.
   */
  public ASN1Real(double number) {
    value = number;
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 REAL object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */
  public ASN1Real(BEREncoding ber, boolean check_tag)
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
   * @exception	ASN1EncodingException If the BER encoding is incorrect.
   */
  @Override
  public void
          ber_decode(BEREncoding ber_enc, boolean check_tag)
          throws ASN1EncodingException {
    if (check_tag) {
      if (ber_enc.tag_get() != TAG
              || ber_enc.tag_type_get() != BEREncoding.UNIVERSAL_TAG) {
        throw new ASN1EncodingException("ASN.1 REAL: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + TAG + "\n");
      }
    }

    if (!(ber_enc instanceof BERPrimitive)) {
      throw new ASN1EncodingException("ASN.1 REAL: bad form, constructed");
    }

    BERPrimitive ber = (BERPrimitive) ber_enc;

    int[] encoding = ber.peek();

    if (encoding.length == 0) {
      // No contents: value is zero
      value = 0;

    } else {
      int first_octet = encoding[0];

      if ((first_octet & 0x80) != 0) {
        // Binary encoding

        boolean is_negative = ((first_octet & 0x40) != 0);
        int base_code = (first_octet >> 4) & 0x03;
        int scale_factor = (first_octet >> 2) & 0x03;
        int exp_size_code = first_octet & 0x03;

        // Right now, we only support base-2 encoding.
        if (base_code != 0x00) {
          switch (base_code) {
            case 0x01:
              throw new ASN1EncodingException("ASN.1 REAL: base-8 unsupported");
            case 0x02:
              throw new ASN1EncodingException("ASN.1 REAL: base-16 unsupported");
            default:
              throw new ASN1EncodingException("ASN.1 REAL: base unsupported");
          }
        }

        // Extract the exponent
        int start_of_mantissa;
        long exponent;
        switch (exp_size_code) {
          case 0x00:
            // 1-byte exponent
            
            if (encoding.length < 2) {
              throw new ASN1EncodingException("ASN.1 REAL: unexpected end");
            } exponent = encoding[1];
            if ((exponent & 0x00000080) != 0) {
              exponent |= 0xFFFFFF00; // sign extent one-byte value
            } start_of_mantissa = 2;
            break;
          case 0x01:
            // 2-byte exponent
            
            if (encoding.length < 3) {
              throw new ASN1EncodingException("ASN.1 REAL: unexpected end");
            } exponent = (encoding[1] << 8) | (encoding[2]);
            if ((exponent & 0x00008000) != 0) {
              exponent |= 0xFFFF0000; // sign extent two-byte value
            } start_of_mantissa = 3;
            break;
          case 0x10:
            // 3-byte exponent
            
            if (encoding.length < 3) {
              throw new ASN1EncodingException("ASN.1 REAL: unexpected end");
            } exponent = (encoding[1] << 16) | (encoding[2] << 8) | (encoding[3]);
            if ((exponent & 0x00800000) != 0) {
              exponent |= 0xFF000000; // sign extent three-byte value
            } start_of_mantissa = 4;
            break;
          default:
            // Variable length exponent
            
            if (encoding.length < 3) {
              throw new ASN1EncodingException("ASN.1 REAL: unexpected end");
            } int num_bytes_for_exponent = encoding[1];
            num_bytes_for_exponent &= 0x00FF; // treated as an unsigned byte
            if (encoding.length < num_bytes_for_exponent + 2) {
              throw new ASN1EncodingException("ASN.1 REAL: unexpected end");
            } if ((encoding[2] & 0x80) != 0) {
              exponent = -1; // prepare for sign extention of value
            } else {
              exponent = 0;
            } for (int x = 0; x < num_bytes_for_exponent; x++) {
              exponent <<= 8;
              exponent |= encoding[2 + x];
            } start_of_mantissa = num_bytes_for_exponent + 2;
            break;
        }

        // Extract the mantissa
        if (start_of_mantissa == encoding.length) {
          throw new ASN1EncodingException("ASN.1 REAL: no mantissa");
        }

        long mantissa = 0;

        for (int x = start_of_mantissa; x < encoding.length; x++) {
          if ((mantissa & 0xFF00000000000000L) != 0) {
            throw new ASN1EncodingException("ASN.1 REAL: resolution overflow");
          }

          mantissa = mantissa << 8;
          mantissa |= encoding[x];
        }

        // Convert raw mantissa into IEEE-754 normalized form
        int mantissa_bits_length = 0;
        while ((mantissa >> mantissa_bits_length) != 0) {
          mantissa_bits_length++;
        }
        if (mantissa_bits_length == 0) {
          throw new ASN1EncodingException("ASN.1 REAL: invalid zero mantissa");
        }

        int offset = 53 - mantissa_bits_length;
        if (0 < offset) {
          mantissa <<= offset;
          exponent -= offset;
        } else {
          mantissa >>= -offset;
          exponent += -offset;
        }

        exponent += scale_factor; // incorporate scale factor
        exponent += 1023 + 52; // IEEE-754 offset

        // Set the value
        long ieee754_bits;

        if (2047 <= exponent) {
          // Very big number
          throw new ASN1EncodingException("ASN.1 REAL: value out of range");

        } else if (exponent <= 0) {
          // Very small number: try denormalized form

          // Work out how many places we can reduce it without throwing
          // away any precision.
          int max_shift = 0;
          while ((mantissa & (0x01L << max_shift)) == 0) {
            max_shift++;
          }

          if (0 < exponent + max_shift) {
            // Yes, can store it in denormalized form
            int shift = 1 - (int) exponent;
            mantissa >>= shift;

            if ((mantissa & ~0x0FFFFFFFFFFFFFL) != 0) {
              throw new ASN1EncodingException("ASN.1 REAL: internal error");
            }

            ieee754_bits = mantissa;
            if (is_negative) {
              ieee754_bits |= 0x8000000000000000L;
            }

          } else {
            // No, still too small
            throw new ASN1EncodingException("ASN.1 REAL: value out of range");
          }

        } else {
          // Normalized number
          // (0 < exponent && exponent < 2047)

          mantissa ^= 0x0010000000000000L; // throw away implicit "1"

          if ((mantissa & ~0x0FFFFFFFFFFFFFL) != 0) {
            throw new ASN1EncodingException("ASN.1 REAL: internal error");
          }

          ieee754_bits = ((exponent << 52) | (mantissa));
          if (is_negative) {
            ieee754_bits |= 0x8000000000000000L;
          }
        }

        value = java.lang.Double.longBitsToDouble(ieee754_bits);

      } else if ((first_octet & 0x40) != 0) {
        // Special Real value

        if (encoding.length != 1) {
          throw new ASN1EncodingException("ASN.1 REAL: bad encoding");
        }

        switch (first_octet) {
          case 0x40:
            value = java.lang.Double.POSITIVE_INFINITY;
            break;
          case 0x41:
            value = java.lang.Double.NEGATIVE_INFINITY;
            break;
          default:
            throw new ASN1EncodingException("ASN.1 REAL: bad special value");
        }

      } else {
        // Decimal encoding

        throw new ASN1EncodingException("ASN.1 REAL decimal BER form: "
                + "NOT IMPLEMENTED YET");
      }
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the REAL.
   *
   * @return	The BER encoding of the REAL
   * @exception	ASN1Exception when the REAL is invalid and cannot be encoded.
   */
  @Override
  public BEREncoding
          ber_encode()
          throws ASN1Exception {
    return ber_encode(BEREncoding.UNIVERSAL_TAG, TAG);
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the REAL. Explicitly tagged with the supplied
   * tag.
   *
   * @return	The BER encoding of the REAL
   * @exception	ASN1Exception when the REAL is invalid and cannot be encoded.
   */
  @Override
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    int[] encoding;

    // Isolate bits from the IEEE-754 double encoding
    long ieee754 = java.lang.Double.doubleToLongBits(value);

    boolean negative_sign = ((ieee754 >> 63) & 0x01) == 0x01;
    long exponent = (ieee754 >> 52) & 0x07FF; // isolate 11-bit exponent
    long mantissa = ieee754 & 0x0FFFFFFFFFFFFFL; // isolate 52-bit mantissa

    if (exponent == 2047) {
      // Special number

      if (mantissa == 0) {
        // Infinity (plus or minus), encode as a special REAL value

        if (negative_sign) {
          encoding = new int[1];
          encoding[0] = 0x41; // special real value: MINUS-INFINITY
        } else {
          encoding = new int[1];
          encoding[0] = 0x40; // special real value: PLUS-INFINITY
        }

      } else {
        // NaN, not supported by ASN.1 REALs

        throw new ASN1Exception("ASN.1 REAL: NaN can't be BER encoded"); // NaN
      }

    } else // Ordinary number
    if (exponent == 0 && mantissa == 0) {
      // Zero (plus or minus), encode as ASN.1 REAL zero

      encoding = new int[0]; // positive zero: no content octets

    } else {
      // Non-zero number

      // Convert IEEE-754 encoding to raw exponent and mantissa
      if (exponent == 0) {
        // Denormalized number
        exponent = -1022 - 52;
        // mantissa stays as it is
      } else {
        // Normalized number
        exponent = exponent - 1023 - 52; // compensate for mantissa
        mantissa |= 0x010000000000000L; // add implicit leading "1"
      }

      // Get rid of trailing zeros in mantissa (to make encoding compact)
      while ((mantissa & 0x01) == 0x00) {
        mantissa >>= 1; // reduce mantissa size by increasing exponent
        exponent += 1;
      }

      int mantissa_bits_length = 0;
      while ((mantissa >> mantissa_bits_length) != 0) {
        mantissa_bits_length++;
      }

      int mantissa_length = (mantissa_bits_length + 7) / 8;

      // Increase mantissa to align first bit to be "1"
      int offset = (mantissa_length * 8) - mantissa_bits_length;
      mantissa <<= offset;
      exponent -= offset;

      // Calculate length of exponent encoding
      int exponent_length;
      if (-128 <= exponent && exponent <= 127) {
        exponent_length = 1;
      } else {
        // assert(-32768 <= exponent && exponent <= 32767)
        exponent_length = 2;
      }

      // Make encoding
      encoding = new int[1 + exponent_length + mantissa_length];

      // First octet
      encoding[0] = 0x80; // binary, base-2, scale=0
      if (negative_sign) {
        encoding[0] |= 0x40; // negative flag
      }

      // Encode exponent
      if (exponent_length == 1) {
        encoding[1] = (int) (exponent & 0xFF);

      } else {
        encoding[0] |= 0x01; // two octet exponent

        encoding[1] = (int) ((exponent >> 8) & 0xFF);
        encoding[2] = (int) (exponent & 0xFF);
      }

      // Encode mantissa
      for (int x = 0; x < mantissa_length; x++) {
        encoding[1 + exponent_length + x]
                = (int) (((mantissa >> ((mantissa_length - x - 1) * 8))) & 0xFF);
      }
    }

    return new BERPrimitive(tag_type, tag, encoding);
  }

  //----------------------------------------------------------------
  /**
   * Method to set the REAL's value.
   *
   * @param new_val the value to set the REAL to.
   * @return	the object.
   */
  public ASN1Real
          set(double new_val) {
    value = new_val;
    return this;
  }

  //----------------------------------------------------------------
  /**
   * Method to get the REAL's value.
   *
   * @return	the REAL's current value.
   */
  public double
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
    if (value == java.lang.Double.NEGATIVE_INFINITY) {
      dest.write("MINUS-INFINITY");

    } else if (value == java.lang.Double.POSITIVE_INFINITY) {
      dest.write("PLUS-INFINITY");

    } else if (Double.isNaN(value)) {
      throw new ASN1Exception("ASN.1 REAL: NaN cannot be XER encoded");

    } else {
      dest.write(String.valueOf(value));
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

    private int proxy_value;

    //----------------
    public XER_Parser_Proxy() {
      super("REAL");
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
      throw new org.xml.sax.SAXException("ASN.1 REAL support not implemented yet");
    }

    //----------------

    /**
     *
     * @param handler
     * @param name
     * @throws SAXException
     */
    @Override
    public void endElement(XERsaxHandler handler,
            String name)
            throws org.xml.sax.SAXException {
      throw new org.xml.sax.SAXException("ASN.1 REAL support not implemented yet");
    }

    //----------------
    @Override
    public void characters(XERsaxHandler handler,
            char[] ch,
            int start,
            int length)
            throws org.xml.sax.SAXException {
      throw new org.xml.sax.SAXException("ASN.1 REAL support not implemented yet");
    }

  } // inner class XER_Parser_Proxy

} // ASN1Real

//----------------------------------------------------------------
//EOF
