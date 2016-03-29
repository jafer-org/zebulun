/*
 * $Source$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 1998, Hoylen Sue.  All Rights Reserved.
 * (h.sue@ieee.org)
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 *
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:20:29 UTC
 */

//----------------------------------------------------------------

package z3950.CIP2_4SpecificInfo;

import asn1.*;

import z3950.v3.IntUnit;
import z3950.v3.InternationalString;
import z3950.v3.Unit;
import z3950.v3.ResourceReport;

//================================================================
/**
 * Class for representing a <code>WRSGRSSpatialCoverage</code> from <code>CIP2-4-Release-B-APDU</code>
 *
 * <pre>
 * WRSGRSSpatialCoverage ::=
 * SEQUENCE {
 *   track [1] IMPLICIT INTEGER
 *   frames [2] EXPLICIT SEQUENCE OF INTEGER
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class WRSGRSSpatialCoverage extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080320Z";

//----------------------------------------------------------------
/**
 * Default constructor for a WRSGRSSpatialCoverage.
 */

public
WRSGRSSpatialCoverage()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a WRSGRSSpatialCoverage from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
WRSGRSSpatialCoverage(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
{
  super(ber, check_tag);
}

//----------------------------------------------------------------
/**
 * Initializing object from a BER encoding.
 * This method is for internal use only. You should use
 * the constructor that takes a BEREncoding.
 *
 * @param ber the BER to decode.
 * @param check_tag if the tag should be checked.
 * @exception ASN1Exception if the BER encoding is bad.
 */

public void
ber_decode(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
{
  // WRSGRSSpatialCoverage should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun WRSGRSSpatialCoverage: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;
  BERConstructed tagged;

  // Decoding: track [1] IMPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun WRSGRSSpatialCoverage: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 1 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun WRSGRSSpatialCoverage: bad tag in s_track\n");

  s_track = new ASN1Integer(p, false);
  part++;

  // Decoding: frames [2] EXPLICIT SEQUENCE OF INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun WRSGRSSpatialCoverage: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 2 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun WRSGRSSpatialCoverage: bad tag in s_frames\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun WRSGRSSpatialCoverage: bad BER encoding: s_frames tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun WRSGRSSpatialCoverage: bad BER encoding: s_frames tag bad\n");
  }

  try {
    BERConstructed cons = (BERConstructed) tagged.elementAt(0);
    int parts = cons.number_components();
    s_frames = new ASN1Integer[parts];
    int n;
    for (n = 0; n < parts; n++) {
      s_frames[n] = new ASN1Integer(cons.elementAt(n), true);
    }
  } catch (ClassCastException e) {
    throw new ASN1EncodingException("Bad BER");
  }
  part++;

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun WRSGRSSpatialCoverage: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the WRSGRSSpatialCoverage.
 *
 * @exception	ASN1Exception Invalid or cannot be encoded.
 * @return	The BER encoding.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  return ber_encode(BEREncoding.UNIVERSAL_TAG, ASN1Sequence.TAG);
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of WRSGRSSpatialCoverage, implicitly tagged.
 *
 * @param tag_type	The type of the implicit tag.
 * @param tag	The implicit tag.
 * @return	The BER encoding of the object.
 * @exception	ASN1Exception When invalid or cannot be encoded.
 * @see asn1.BEREncoding#UNIVERSAL_TAG
 * @see asn1.BEREncoding#APPLICATION_TAG
 * @see asn1.BEREncoding#CONTEXT_SPECIFIC_TAG
 * @see asn1.BEREncoding#PRIVATE_TAG
 */

public BEREncoding
ber_encode(int tag_type, int tag)
       throws ASN1Exception
{
  // Calculate the number of fields in the encoding

  int num_fields = 2; // number of mandatories

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding f2[];
  int p;
  BEREncoding enc[];

  // Encoding s_track: INTEGER 

  fields[x++] = s_track.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);

  // Encoding s_frames: SEQUENCE OF 

  enc = new BEREncoding[1];
  f2 = new BEREncoding[s_frames.length];

  for (p = 0; p < s_frames.length; p++) {
    f2[p] = s_frames[p].ber_encode();
  }

  enc[0] = new BERConstructed(BEREncoding.UNIVERSAL_TAG, ASN1Sequence.TAG, f2);
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 2, enc);

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the WRSGRSSpatialCoverage. 
 */

public String
toString()
{
  int p;
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  str.append("track ");
  str.append(s_track);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("frames ");
  str.append("{");
  for (p = 0; p < s_frames.length; p++) {
    if (p != 0)
      str.append(", ");
    str.append(s_frames[p]);
  }
  str.append("}");
  outputted++;

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public ASN1Integer s_track;
public ASN1Integer s_frames[];

} // WRSGRSSpatialCoverage

//----------------------------------------------------------------
//EOF
