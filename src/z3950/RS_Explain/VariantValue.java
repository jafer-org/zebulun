/*
 * $Source$
 * $Date$
 * $Revision$
 *
 * Copyright (C) 1998, Hoylen Sue.  All Rights Reserved.
 * <h.sue@ieee.org>
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 *
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:22 UTC
 */

//----------------------------------------------------------------

package z3950.RS_Explain;
import asn1.*;
import z3950.v3.AttributeElement;
import z3950.v3.AttributeList;
import z3950.v3.AttributeSetId;
import z3950.v3.DatabaseName;
import z3950.v3.ElementSetName;
import z3950.v3.IntUnit;
import z3950.v3.InternationalString;
import z3950.v3.OtherInformation;
import z3950.v3.Specification;
import z3950.v3.StringOrNumeric;
import z3950.v3.Term;
import z3950.v3.Unit;

//================================================================
/**
 * Class for representing a <code>VariantValue</code> from <code>RecordSyntax-explain</code>
 *
 * <pre>
 * VariantValue ::=
 * SEQUENCE {
 *   dataType [0] EXPLICIT PrimitiveDataType
 *   values [1] EXPLICIT ValueSet OPTIONAL
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class VariantValue extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a VariantValue.
 */

public
VariantValue()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a VariantValue from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
VariantValue(BEREncoding ber, boolean check_tag)
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
  // VariantValue should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun VariantValue: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;
  BERConstructed tagged;

  // Decoding: dataType [0] EXPLICIT PrimitiveDataType

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun VariantValue: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 0 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun VariantValue: bad tag in s_dataType\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun VariantValue: bad BER encoding: s_dataType tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun VariantValue: bad BER encoding: s_dataType tag bad\n");
  }

  s_dataType = new PrimitiveDataType(tagged.elementAt(0), true);
  part++;

  // Remaining elements are optional, set variables
  // to null (not present) so can return at end of BER

  s_values = null;

  // Decoding: values [1] EXPLICIT ValueSet OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 1 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagged = (BERConstructed) p;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun VariantValue: bad BER encoding: s_values tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun VariantValue: bad BER encoding: s_values tag bad\n");
    }

    s_values = new ValueSet(tagged.elementAt(0), true);
    part++;
  }

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun VariantValue: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the VariantValue.
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
 * Returns a BER encoding of VariantValue, implicitly tagged.
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

  int num_fields = 1; // number of mandatories
  if (s_values != null)
    num_fields++;

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding enc[];

  // Encoding s_dataType: PrimitiveDataType 

  enc = new BEREncoding[1];
  enc[0] = s_dataType.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 0, enc);

  // Encoding s_values: ValueSet OPTIONAL

  if (s_values != null) {
    enc = new BEREncoding[1];
    enc[0] = s_values.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 1, enc);
  }

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the VariantValue. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  str.append("dataType ");
  str.append(s_dataType);
  outputted++;

  if (s_values != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("values ");
    str.append(s_values);
    outputted++;
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public PrimitiveDataType s_dataType;
public ValueSet s_values; // optional

} // VariantValue

//----------------------------------------------------------------
//EOF
