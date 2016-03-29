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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:20:30 UTC
 */

//----------------------------------------------------------------

package z3950.CIP2_4FormattedSceneSelectionOptions;
import asn1.*;
import z3950.v3.InternationalString;
import z3950.v3.IntUnit;

//================================================================
/**
 * Class for representing a <code>RectangleSceneSelection</code> from <code>CIP2-4-Order-ES</code>
 *
 * <pre>
 * RectangleSceneSelection ::=
 * SEQUENCE {
 *   acrossGrid [1] EXPLICIT INTEGER
 *   alongGridUnitType [2] IMPLICIT INTEGER
 *   alongGrid [3] EXPLICIT INTEGER
 *   acrossSize [4] EXPLICIT INTEGER
 *   alongSize [5] EXPLICIT INTEGER
 *   acrossCenter [6] EXPLICIT INTEGER
 *   alongCenter [7] EXPLICIT INTEGER
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class RectangleSceneSelection extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080320Z";

//----------------------------------------------------------------
/**
 * Default constructor for a RectangleSceneSelection.
 */

public
RectangleSceneSelection()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a RectangleSceneSelection from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
RectangleSceneSelection(BEREncoding ber, boolean check_tag)
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
  // RectangleSceneSelection should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;
  BERConstructed tagged;

  // Decoding: acrossGrid [1] EXPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 1 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_acrossGrid\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_acrossGrid tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_acrossGrid tag bad\n");
  }

  s_acrossGrid = new ASN1Integer(tagged.elementAt(0), true);
  part++;

  // Decoding: alongGridUnitType [2] IMPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 2 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_alongGridUnitType\n");

  s_alongGridUnitType = new ASN1Integer(p, false);
  part++;

  // Decoding: alongGrid [3] EXPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 3 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_alongGrid\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_alongGrid tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_alongGrid tag bad\n");
  }

  s_alongGrid = new ASN1Integer(tagged.elementAt(0), true);
  part++;

  // Decoding: acrossSize [4] EXPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 4 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_acrossSize\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_acrossSize tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_acrossSize tag bad\n");
  }

  s_acrossSize = new ASN1Integer(tagged.elementAt(0), true);
  part++;

  // Decoding: alongSize [5] EXPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 5 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_alongSize\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_alongSize tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_alongSize tag bad\n");
  }

  s_alongSize = new ASN1Integer(tagged.elementAt(0), true);
  part++;

  // Decoding: acrossCenter [6] EXPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 6 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_acrossCenter\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_acrossCenter tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_acrossCenter tag bad\n");
  }

  s_acrossCenter = new ASN1Integer(tagged.elementAt(0), true);
  part++;

  // Decoding: alongCenter [7] EXPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun RectangleSceneSelection: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 7 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad tag in s_alongCenter\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_alongCenter tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun RectangleSceneSelection: bad BER encoding: s_alongCenter tag bad\n");
  }

  s_alongCenter = new ASN1Integer(tagged.elementAt(0), true);
  part++;

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun RectangleSceneSelection: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the RectangleSceneSelection.
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
 * Returns a BER encoding of RectangleSceneSelection, implicitly tagged.
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

  int num_fields = 7; // number of mandatories

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding enc[];

  // Encoding s_acrossGrid: INTEGER
 
  enc = new BEREncoding[1];
  enc[0] = s_acrossGrid.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 1, enc);

  // Encoding s_alongGridUnitType: INTEGER 

  fields[x++] = s_alongGridUnitType.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);

  // Encoding s_alongGrid: INTEGER 

  enc = new BEREncoding[1];
  enc[0] = s_alongGrid.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 3, enc);

  // Encoding s_acrossSize: INTEGER 

  enc = new BEREncoding[1];
  enc[0] = s_acrossSize.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 4, enc);

  // Encoding s_alongSize: INTEGER 

  enc = new BEREncoding[1];
  enc[0] = s_alongSize.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 5, enc);

  // Encoding s_acrossCenter: INTEGER 

  enc = new BEREncoding[1];
  enc[0] = s_acrossCenter.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 6, enc);

  // Encoding s_alongCenter: INTEGER 

  enc = new BEREncoding[1];
  enc[0] = s_alongCenter.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 7, enc);

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the RectangleSceneSelection. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  str.append("acrossGrid ");
  str.append(s_acrossGrid);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("alongGridUnitType ");
  str.append(s_alongGridUnitType);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("alongGrid ");
  str.append(s_alongGrid);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("acrossSize ");
  str.append(s_acrossSize);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("alongSize ");
  str.append(s_alongSize);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("acrossCenter");
  str.append(s_acrossCenter);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("alongCenter");
  str.append(s_alongCenter);
  outputted++;

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public ASN1Integer s_acrossGrid;
public ASN1Integer s_alongGridUnitType;
public ASN1Integer s_alongGrid;
public ASN1Integer s_acrossSize;
public ASN1Integer s_alongSize;
public ASN1Integer s_acrossCenter;
public ASN1Integer s_alongCenter;

//----------------------------------------------------------------
/*
 * Enumerated constants for class.
 */

// Enumerated constants for transferDirection
public static final int E_relative = 0;
public static final int E_timeBased = 1;

} // RectangleSceneSelection

//----------------------------------------------------------------
//EOF
