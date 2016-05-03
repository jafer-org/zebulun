/*
 * $Id: ASN1External.java,v 1.6 1999/04/13 07:23:06 hoylen Exp $
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
 * ASN1 EXTERNAL
 *
 * The <code>EXTERNAL</code> type represents an external object.
 *
 * According to clause 34.4 of the ASN.1 standard, the EXTERNAL type can be
 * defined as:
 *
 * <pre>
 * EXTERNAL := [UNIVERSAL 8] IMPLICIT SEQUENCE {
 *               direct-reference OBJECT IDENTIFIER OPTIONAL,
 *               indirect-reference INTEGER OPTIONAL,
 *               data-value-descriptor ObjectDescriptor OPTIONAL,
 *               encoding CHOICE {
 *                 single-ASN1-type [0] ANY,
 *                 octet-aligned    [1] IMPLICIT OCTET STRING,
 *                 arbitrary        [2] IMPLICIT BIT STRING
 *               }
 *             }
 * </pre>
 *
 * This construct has been represented by a class with six variables:
 * s_direct_reference, s_indirect_reference, s_data_value_descriptor,
 * c_single_ASN1_type, c_octet_Aligned, c_arbitrary. The first three should be
 * set to point to the appropriate object if present, or null if not. One of the
 * last three variables should be set to non-null (the choice) and the rest to
 * null.
 *
 * @version	$Release$ $Date: 1999/04/13 07:23:06 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public final class ASN1External extends ASN1Any {

  /**
   * This constant is the ASN.1 UNIVERSAL tag value for an EXTERNAL.
   */

  public final static int TAG = 0x08;

  //----------------------------------------------------------------
  /*
   * The values are stored in these variables.
   */
  public ASN1ObjectIdentifier s_direct_reference;
  public ASN1Integer s_indirect_reference;
  public ASN1ObjectDescriptor s_data_value_descriptor;

  public ASN1Any c_singleASN1type;
  public ASN1OctetString c_octetAligned;
  public ASN1BitString c_arbitrary;

  //================================================================
  /**
   * Constructor for an ASN.1 EXTERNAL object. It sets the tag to the default
   * value of UNIVERSAL 8.
   */
  public ASN1External() {
    // All members automatically set to null
  }

  //----------------------------------------------------------------
  /**
   * Constructor for an ASN.1 EXTERNAL object from a BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly
   * tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */
  public ASN1External(BEREncoding ber, boolean check_tag)
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
        throw new ASN1EncodingException("ASN.1 EXTERNAL: bad BER: tag=" + ber_enc.tag_get()
                + " expected " + TAG + "\n");
      }
    }

    if (ber_enc instanceof BERPrimitive) {
      throw new ASN1EncodingException("ASN.1 EXTERNAL: incorrect form, primitive encoding");
    }

    BERConstructed ber = (BERConstructed) ber_enc;

    s_direct_reference = null;
    s_indirect_reference = null;
    s_data_value_descriptor = null;
    c_singleASN1type = null;
    c_octetAligned = null;
    c_arbitrary = null;

    int num_parts = ber.number_components();
    if (num_parts < 1) {
      throw new ASN1EncodingException("ASN.1 EXTERNAL: incomplete");
    }

    int part = 0;

    BEREncoding p = ber.elementAt(part);

    if (p.tag_get() == ASN1ObjectIdentifier.TAG
            && p.tag_type_get() == BEREncoding.UNIVERSAL_TAG) {
      s_direct_reference = new ASN1ObjectIdentifier(p, true);

      if (num_parts <= ++part) {
        throw new ASN1EncodingException("ASN.1 EXTERNAL: incomplete");
      }
      p = ber.elementAt(part);
    }

    if (p.tag_get() == ASN1Integer.TAG
            && p.tag_type_get() == BEREncoding.UNIVERSAL_TAG) {
      s_indirect_reference = new ASN1Integer(p, true);

      if (num_parts <= ++part) {
        throw new ASN1EncodingException("ASN.1 EXTERNAL: incomplete");
      }
      p = ber.elementAt(part);
    }

    if (p.tag_get() == ASN1ObjectDescriptor.TAG
            && p.tag_type_get() == BEREncoding.UNIVERSAL_TAG) {
      s_data_value_descriptor = new ASN1ObjectDescriptor(p, true);

      if (num_parts <= ++part) {
        throw new ASN1EncodingException("ASN.1 EXTERNAL: incomplete");
      }
      p = ber.elementAt(part);
    }

    // decoding IMPLICIT
    switch (p.tag_get()) {
      case 0:
        // single_ASN1_type [0] ANY,

//???
// Isite sample zserver seems to have an error and send UNIVERSAL tag
//
//      if (p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
//        throw new ASN1EncodingException
//	  ("ASN.1 EXTERNAL: encoding: bad tag type " + p);
        if (!(p instanceof BERConstructed)) {
          throw new ASN1EncodingException("ASN.1 EXTERNAL: singleASN1type: bad form, primitive");
        }

        if (((BERConstructed) p).number_components() != 1) {
          throw new ASN1EncodingException("ASN.1 EXTERNAL: singleASN1type: bad form, no explicit tag");
        }

        c_singleASN1type = ASN1Decoder.toASN1(((BERConstructed) p).elementAt(0));
        break;

      case 1:
        // octet_Aligned [1] IMPLICIT OCTET STRING

        if (p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG) {
          throw new ASN1EncodingException("ASN.1 EXTERNAL: encoding: bad tag type " + p);
        }

        c_octetAligned = new ASN1OctetString(p, false);
        break;

      case 2:
        // arbitrary [2] IMPLICIT BIT STRING

        if (p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG) {
          throw new ASN1EncodingException("ASN.1 EXTERNAL: encoding: bad tag type " + p);
        }

        c_arbitrary = new ASN1BitString(p, false);
        break;

      default:
        throw new ASN1EncodingException("ASN.1 EXTERNAL: encoding: tag = " + p.tag_get());
    }

    if (part != (num_parts - 1)) {
      throw new ASN1Exception("ASN.1 EXTERNAL: extra element(s)");
    }
  }

  //----------------------------------------------------------------
  /**
   * Returns a BER encoding of the EXTERNAL.
   *
   * @return	The BER encoding of the EXTERNAL
   * @exception	ASN1Exception when the EXTERNAL is invalid and cannot be
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
   * Returns a BER encoding of the EXTERNAL.
   *
   * @return	The BER encoding of the EXTERNAL
   * @exception	ASN1Exception when the EXTERNAL is invalid and cannot be
   * encoded.
   */
  @Override
  public BEREncoding
          ber_encode(int tag_type, int tag)
          throws ASN1Exception {
    // Calculate length of encoding

    int num_parts = 0;

    if (c_singleASN1type != null) {
      num_parts++;
    }
    if (c_octetAligned != null) {
      num_parts++;
    }
    if (c_arbitrary != null) {
      num_parts++;
    }

    if (num_parts < 1) {
      throw new ASN1Exception("ASN1 EXTERNAL: no encoding has been set");
    }
    if (1 < num_parts) {
      throw new ASN1Exception("ASN1 EXTERNAL: more than one encoding set");
    }

    if (s_direct_reference != null) {
      num_parts++;
    }
    if (s_indirect_reference != null) {
      num_parts++;
    }
    if (s_data_value_descriptor != null) {
      num_parts++;
    }

    // Encode it
    BEREncoding[] parts = new BEREncoding[num_parts];

    int part = 0;

    if (s_direct_reference != null) {
      parts[part++] = s_direct_reference.ber_encode();
    }
    if (s_indirect_reference != null) {
      parts[part++] = s_indirect_reference.ber_encode();
    }
    if (s_data_value_descriptor != null) {
      parts[part++] = s_data_value_descriptor.ber_encode();
    }

    // Encode the choice
    if (c_singleASN1type != null) {
      // explicit tag
      BEREncoding[] contents = new BEREncoding[1];
      contents[0] = c_singleASN1type.ber_encode();
      parts[part] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 0,
              contents);

    } else if (c_octetAligned != null) {
      parts[part] = c_octetAligned.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG,
              1);

    } else if (c_arbitrary != null) {
      parts[part] = c_arbitrary.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG,
              2);
    }

    return new BERConstructed(tag_type, tag, parts);
  }

//----------------------------------------------------------------
  /**
   * @return a new String object representing this ASN.1 object's value.
   */
  @Override
  public String
          toString() {
    StringBuilder str = new StringBuilder("{");
    boolean has_element = false;

    if (s_direct_reference != null) {
      str.append("directReference ");
      str.append(s_direct_reference);
      has_element = true;
    }

    if (s_indirect_reference != null) {
      if (has_element) {
        str.append(", ");
      }
      str.append("indirectReference ");
      str.append(s_indirect_reference);
      has_element = true;
    }

    if (s_data_value_descriptor != null) {
      if (has_element) {
        str.append(", ");
      }
      str.append("dataValueDescriptor ");
      str.append(s_data_value_descriptor);
      has_element = true;
    }

    if (has_element) {
      str.append(", ");
    }
    str.append("encoding {");

    if (c_singleASN1type != null) {
      str.append("singleASN1type ");
      str.append(c_singleASN1type);
    }

    if (c_octetAligned != null) {
      str.append("octetAligned ");
      str.append(c_octetAligned);
    }

    if (c_arbitrary != null) {
      str.append("arbitrary ");
      str.append(c_arbitrary);
    }

    str.append("}}");

    return str.toString();
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
    if (s_direct_reference != null) {
      dest.print("<xer:direct-reference>");
      s_direct_reference.xer_encode(dest);
      dest.print("</xer:direct-reference>");
    }

    if (s_indirect_reference != null) {
      dest.print("<xer:indirect-reference>");
      s_indirect_reference.xer_encode(dest);
      dest.print("</xer:indirect-reference>");
    }

    if (s_data_value_descriptor != null) {
      dest.print("<xer:data-value-descriptor>");
      s_data_value_descriptor.xer_encode(dest);
      dest.print("</xer:data-value-descriptor>");
    }

    dest.print("<xer:encoding>");

    if (c_singleASN1type != null) {
      dest.print("<xer:single-ASN1-type>");
      c_singleASN1type.ber_encode().xer_encode(dest);
      dest.print("</xer:single-ASN1-type>");
    }

    if (c_octetAligned != null) {
      dest.print("<xer:octet-aligned>");
      c_octetAligned.xer_encode(dest);
      dest.print("</xer:octet-aligned>");
    }

    if (c_arbitrary != null) {
      dest.print("<xer:arbitrary>");
      c_arbitrary.xer_encode(dest);
      dest.print("</xer:arbitrary>");
    }

    dest.print("</xer:encoding>");
  }

  //================================================================
  // Nested inner-class for parsing XER.
  public static class XER_Parser_Proxy extends XERsaxHandler.XER_Parser_Proxy {

    private final static int STATE_INIT = 0;
    private final static int STATE_EXTERNAL_GETTING = 1;
    private final static int STATE_DIRECT_REFERENCE_GETTING = 2;
    private final static int STATE_DIRECT_REFERENCE_GOT = 3;
    private final static int STATE_INDIRECT_REFERENCE_GETTING = 4;
    private final static int STATE_INDIRECT_REFERENCE_GOT = 5;
    private final static int STATE_DATA_VALUE_DESCRIPTOR_GETTING = 6;
    private final static int STATE_DATA_VALUE_DESCRIPTOR_GOT = 7;
    private final static int STATE_ENCODING_GETTING = 8;
    private final static int STATE_SINGLE_ASN1_TYPE_GETTING = 9;
    private final static int STATE_OCTET_ALIGNED_GETTING = 10;
    private final static int STATE_ARBITRARY_GETTING = 11;
    private final static int STATE_ENCODING_GOT = 12;
    private final static int STATE_TERM = 13;

    private int istate;
    private ASN1External proxy_value;

    //----------------
    public XER_Parser_Proxy() {
      super("EXTERNAL");
      proxy_value = new ASN1External();
      istate = STATE_INIT;
    }

    public XER_Parser_Proxy(String overriding_xer_tag) {
      super(overriding_xer_tag);
      proxy_value = new ASN1External();
      istate = STATE_INIT;
    }

    //----------------
    @Override
    public void startElement(XERsaxHandler handler,
            String name,
            org.xml.sax.AttributeList atts)
            throws org.xml.sax.SAXException {
      if (name.equals(xer_tag)
              && istate == STATE_INIT) {
        istate = STATE_EXTERNAL_GETTING;

      } else if (name.equals("direct-reference")
              && istate == STATE_EXTERNAL_GETTING) {
        istate = STATE_DIRECT_REFERENCE_GETTING;
        handler.member_expect(new ASN1ObjectIdentifier.XER_Parser_Proxy("direct-reference"));
        handler.startElement(name, atts);

      } else if (name.equals("indirect-reference")
              && (istate == STATE_EXTERNAL_GETTING
              || istate == STATE_DIRECT_REFERENCE_GOT)) {
        istate = STATE_INDIRECT_REFERENCE_GETTING;
        handler.member_expect(new ASN1Integer.XER_Parser_Proxy("indirect-reference"));
        handler.startElement(name, atts);

      } else if (name.equals("data-value-descriptor")
              && (istate == STATE_EXTERNAL_GETTING
              || istate == STATE_DIRECT_REFERENCE_GOT
              || istate == STATE_INDIRECT_REFERENCE_GOT)) {
        istate = STATE_DATA_VALUE_DESCRIPTOR_GETTING;
        handler.member_expect(new ASN1ObjectDescriptor.XER_Parser_Proxy("data-value-descriptor"));
        handler.startElement(name, atts);

      } else if (name.equals("encoding")
              && (istate == STATE_EXTERNAL_GETTING
              || istate == STATE_DIRECT_REFERENCE_GOT
              || istate == STATE_INDIRECT_REFERENCE_GOT
              || istate == STATE_DATA_VALUE_DESCRIPTOR_GOT)) {
        istate = STATE_ENCODING_GETTING;

      } else if (name.equals("single-ASN1-type")
              && istate == STATE_ENCODING_GETTING) {
        istate = STATE_SINGLE_ASN1_TYPE_GETTING;
        throw new org.xml.sax.SAXException("XER Parser: "
                + "EXTERNAL single-ASN1-type: "
                + "not implemented yet"); //???

      } else if (name.equals("octet-aligned")
              && istate == STATE_ENCODING_GETTING) {
        istate = STATE_OCTET_ALIGNED_GETTING;
        handler.member_expect(new ASN1OctetString.XER_Parser_Proxy("octet-aligned"));
        handler.startElement(name, atts);

      } else if (name.equals("arbitrary")
              && istate == STATE_ENCODING_GETTING) {
        istate = STATE_ARBITRARY_GETTING;
        handler.member_expect(new ASN1BitString.XER_Parser_Proxy("arbitrary"));
        handler.startElement(name, atts);

      } else {
        handler.throw_start_unexpected(xer_tag, name);
      }
    }

    //----------------
    @Override
    public void endElement(XERsaxHandler handler,
            String name)
            throws org.xml.sax.SAXException {
      if (name.equals("encoding")
              && istate == STATE_ENCODING_GOT) {
        istate = STATE_ENCODING_GOT;

      } else if (name.equals(xer_tag)
              && istate == STATE_ENCODING_GOT) {
        istate = STATE_TERM;
        handler.member_got(proxy_value);

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
      // All characters must be whitespace

      int begin = start;
      int end = begin + length;

      while (begin < end && Character.isWhitespace(ch[begin])) {
        begin++;
      }

      if (begin < end) {
        handler.throw_characters_unexpected(xer_tag);
      }
    }

    //----------------
    @Override
    public void member(XERsaxHandler handler, ASN1Any result)
            throws org.xml.sax.SAXException {
      if (istate == STATE_DIRECT_REFERENCE_GETTING) {
        proxy_value.s_direct_reference = (ASN1ObjectIdentifier) result;
        istate = STATE_DIRECT_REFERENCE_GOT;

      } else if (istate == STATE_INDIRECT_REFERENCE_GETTING) {
        proxy_value.s_indirect_reference = (ASN1Integer) result;
        istate = STATE_INDIRECT_REFERENCE_GOT;

      } else if (istate == STATE_DATA_VALUE_DESCRIPTOR_GETTING) {
        proxy_value.s_data_value_descriptor = (ASN1ObjectDescriptor) result;
        istate = STATE_DATA_VALUE_DESCRIPTOR_GOT;

      } else if (istate == STATE_SINGLE_ASN1_TYPE_GETTING) {
        proxy_value.c_singleASN1type = result;
        istate = STATE_ENCODING_GOT;

      } else if (istate == STATE_OCTET_ALIGNED_GETTING) {
        proxy_value.c_octetAligned = (ASN1OctetString) result;
        istate = STATE_ENCODING_GOT;

      } else if (istate == STATE_ARBITRARY_GETTING) {
        proxy_value.c_arbitrary = (ASN1BitString) result;
        istate = STATE_ENCODING_GOT;

      } else {
        handler.throw_member_unexpected(xer_tag);
      }

    }

  } // nexted inner-class: XER_Parser_Proxy

} // class: ASN1External

//----------------------------------------------------------------
/*
  $Log: ASN1External.java,v $
  Revision 1.6  1999/04/13 07:23:06  hoylen
  Updated encoding code to latest XER encoding rules

  Revision 1.5  1999/04/07 01:23:46  hoylen
  Fixed XER encoding of ANY to be a single BER rather than as individual parts

  Revision 1.4  1999/03/17 05:45:36  hoylen
  Tidied up for metamata audit code checking software

  Revision 1.3  1999/03/17 00:32:15  hoylen
  Added ZSQL RS

  Revision 1.2  1999/03/15 07:34:58  hoylen
  Implemented experimental XER encoding and decoding

  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
