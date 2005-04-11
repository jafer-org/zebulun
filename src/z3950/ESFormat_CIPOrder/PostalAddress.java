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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:20:30 UTC
 */

//----------------------------------------------------------------

package z3950.ESFormat_CIPOrder;
import asn1.*;
import z3950.v3.InternationalString;
import z3950.v3.OtherInformation;

//================================================================
/**
 * Class for representing a <code>PostalAddress</code> from <code>CIP-Order-ES</code>
 *
 * <pre>
 * PostalAddress ::=
 * SEQUENCE {
 *   streetAddress [1] EXPLICIT InternationalString
 *   city [2] EXPLICIT InternationalString
 *   state [3] EXPLICIT InternationalString
 *   postalCode [4] EXPLICIT InternationalString
 *   country [5] EXPLICIT InternationalString
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class PostalAddress extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080320Z";

//----------------------------------------------------------------
/**
 * Default constructor for a PostalAddress.
 */

public
PostalAddress()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a PostalAddress from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
PostalAddress(BEREncoding ber, boolean check_tag)
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
  // PostalAddress should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;
  BERConstructed tagged;

  // Decoding: streetAddress [1] EXPLICIT InternationalString

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun PostalAddress: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 1 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad tag in s_streetAddress\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_streetAddress tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_streetAddress tag bad\n");
  }

  s_streetAddress = new InternationalString(tagged.elementAt(0), true);
  part++;

  // Decoding: city [2] EXPLICIT InternationalString

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun PostalAddress: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 2 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad tag in s_city\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_city tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_city tag bad\n");
  }

  s_city = new InternationalString(tagged.elementAt(0), true);
  part++;

  // Decoding: state [3] EXPLICIT InternationalString

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun PostalAddress: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 3 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad tag in s_state\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_state tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_state tag bad\n");
  }

  s_state = new InternationalString(tagged.elementAt(0), true);
  part++;

  // Decoding: postalCode [4] EXPLICIT InternationalString

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun PostalAddress: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 4 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad tag in s_postalCode\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_postalCode tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_postalCode tag bad\n");
  }

  s_postalCode = new InternationalString(tagged.elementAt(0), true);
  part++;

  // Decoding: country [5] EXPLICIT InternationalString

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun PostalAddress: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 5 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad tag in s_country\n");

  try {
    tagged = (BERConstructed) p;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_country tag bad\n");
  }
  if (tagged.number_components() != 1) {
    throw new ASN1EncodingException
      ("Zebulun PostalAddress: bad BER encoding: s_country tag bad\n");
  }

  s_country = new InternationalString(tagged.elementAt(0), true);
  part++;

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun PostalAddress: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the PostalAddress.
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
 * Returns a BER encoding of PostalAddress, implicitly tagged.
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

  int num_fields = 5; // number of mandatories

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding enc[];

  // Encoding s_streetAddress: InternationalString 

  enc = new BEREncoding[1];
  enc[0] = s_streetAddress.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 1, enc);

  // Encoding s_city: InternationalString 

  enc = new BEREncoding[1];
  enc[0] = s_city.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 2, enc);

  // Encoding s_state: InternationalString 

  enc = new BEREncoding[1];
  enc[0] = s_state.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 3, enc);

  // Encoding s_postalCode: InternationalString 

  enc = new BEREncoding[1];
  enc[0] = s_postalCode.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 4, enc);

  // Encoding s_country: InternationalString 

  enc = new BEREncoding[1];
  enc[0] = s_country.ber_encode();
  fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 5, enc);

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the PostalAddress. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  str.append("streetAddress ");
  str.append(s_streetAddress);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("city ");
  str.append(s_city);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("state ");
  str.append(s_state);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("postalCode ");
  str.append(s_postalCode);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append("country ");
  str.append(s_country);
  outputted++;

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public InternationalString s_streetAddress;
public InternationalString s_city;
public InternationalString s_state;
public InternationalString s_postalCode;
public InternationalString s_country;

} // PostalAddress

//----------------------------------------------------------------
//EOF
