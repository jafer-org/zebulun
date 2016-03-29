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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:28 UTC
 */

//----------------------------------------------------------------

package z3950.ESFormat_ExportSpec;
import asn1.*;
import z3950.v3.CompSpec;
import z3950.v3.InternationalString;

//================================================================
/**
 * Class for representing a <code>Destination_other</code> from <code>ESFormat-ExportSpecification</code>
 *
 * <pre>
 * Destination_other ::=
 * SEQUENCE {
 *   vehicle [1] IMPLICIT InternationalString OPTIONAL
 *   destination [2] IMPLICIT InternationalString
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class Destination_other extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a Destination_other.
 */

public
Destination_other()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a Destination_other from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
Destination_other(BEREncoding ber, boolean check_tag)
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
  // Destination_other should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun Destination_other: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;

  // Decoding: vehicle [1] IMPLICIT InternationalString OPTIONAL

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun Destination_other: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 1 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    s_vehicle = new InternationalString(p, false);
    part++;
  }

  // Decoding: destination [2] IMPLICIT InternationalString

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun Destination_other: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 2 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun Destination_other: bad tag in s_destination\n");

  s_destination = new InternationalString(p, false);
  part++;

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun Destination_other: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the Destination_other.
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
 * Returns a BER encoding of Destination_other, implicitly tagged.
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
  if (s_vehicle != null)
    num_fields++;

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;

  // Encoding s_vehicle: InternationalString OPTIONAL

  if (s_vehicle != null) {
    fields[x++] = s_vehicle.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
  }

  // Encoding s_destination: InternationalString 

  fields[x++] = s_destination.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the Destination_other. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  if (s_vehicle != null) {
    str.append("vehicle ");
    str.append(s_vehicle);
    outputted++;
  }

  if (0 < outputted)
    str.append(", ");
  str.append("destination ");
  str.append(s_destination);
  outputted++;

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public InternationalString s_vehicle; // optional
public InternationalString s_destination;

} // Destination_other

//----------------------------------------------------------------
//EOF
