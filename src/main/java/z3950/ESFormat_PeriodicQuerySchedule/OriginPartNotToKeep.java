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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:26 UTC
 */

//----------------------------------------------------------------

package z3950.ESFormat_PeriodicQuerySchedule;
import asn1.*;
import z3950.ESFormat_ExportSpec.Destination;
import z3950.ESFormat_ExportSpec.ExportSpecification;
import z3950.v3.IntUnit;
import z3950.v3.InternationalString;
import z3950.v3.Query;

//================================================================
/**
 * Class for representing a <code>OriginPartNotToKeep</code> from <code>ESFormat-PeriodicQuerySchedule</code>
 *
 * <pre>
 * OriginPartNotToKeep ::=
 * SEQUENCE {
 *   querySpec [1] EXPLICIT OriginPartNotToKeep_querySpec OPTIONAL
 *   originSuggestedPeriod [2] EXPLICIT Period OPTIONAL
 *   expiration [3] IMPLICIT GeneralizedTime OPTIONAL
 *   resultSetPackage [4] IMPLICIT InternationalString OPTIONAL
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class OriginPartNotToKeep extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a OriginPartNotToKeep.
 */

public
OriginPartNotToKeep()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a OriginPartNotToKeep from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
OriginPartNotToKeep(BEREncoding ber, boolean check_tag)
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
  // OriginPartNotToKeep should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun OriginPartNotToKeep: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;
  BERConstructed tagged;

  // Remaining elements are optional, set variables
  // to null (not present) so can return at end of BER

  s_querySpec = null;
  s_originSuggestedPeriod = null;
  s_expiration = null;
  s_resultSetPackage = null;

  // Decoding: querySpec [1] EXPLICIT OriginPartNotToKeep_querySpec OPTIONAL

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
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_querySpec tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_querySpec tag bad\n");
    }

    s_querySpec = new OriginPartNotToKeep_querySpec(tagged.elementAt(0), true);
    part++;
  }

  // Decoding: originSuggestedPeriod [2] EXPLICIT Period OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 2 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagged = (BERConstructed) p;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_originSuggestedPeriod tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_originSuggestedPeriod tag bad\n");
    }

    s_originSuggestedPeriod = new Period(tagged.elementAt(0), true);
    part++;
  }

  // Decoding: expiration [3] IMPLICIT GeneralizedTime OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 3 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    s_expiration = new ASN1GeneralizedTime(p, false);
    part++;
  }

  // Decoding: resultSetPackage [4] IMPLICIT InternationalString OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 4 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    s_resultSetPackage = new InternationalString(p, false);
    part++;
  }

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun OriginPartNotToKeep: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the OriginPartNotToKeep.
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
 * Returns a BER encoding of OriginPartNotToKeep, implicitly tagged.
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

  int num_fields = 0; // number of mandatories
  if (s_querySpec != null)
    num_fields++;
  if (s_originSuggestedPeriod != null)
    num_fields++;
  if (s_expiration != null)
    num_fields++;
  if (s_resultSetPackage != null)
    num_fields++;

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding enc[];

  // Encoding s_querySpec: OriginPartNotToKeep_querySpec OPTIONAL

  if (s_querySpec != null) {
    enc = new BEREncoding[1];
    enc[0] = s_querySpec.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 1, enc);
  }

  // Encoding s_originSuggestedPeriod: Period OPTIONAL

  if (s_originSuggestedPeriod != null) {
    enc = new BEREncoding[1];
    enc[0] = s_originSuggestedPeriod.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 2, enc);
  }

  // Encoding s_expiration: GeneralizedTime OPTIONAL

  if (s_expiration != null) {
    fields[x++] = s_expiration.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 3);
  }

  // Encoding s_resultSetPackage: InternationalString OPTIONAL

  if (s_resultSetPackage != null) {
    fields[x++] = s_resultSetPackage.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 4);
  }

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the OriginPartNotToKeep. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  if (s_querySpec != null) {
    str.append("querySpec ");
    str.append(s_querySpec);
    outputted++;
  }

  if (s_originSuggestedPeriod != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("originSuggestedPeriod ");
    str.append(s_originSuggestedPeriod);
    outputted++;
  }

  if (s_expiration != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("expiration ");
    str.append(s_expiration);
    outputted++;
  }

  if (s_resultSetPackage != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("resultSetPackage ");
    str.append(s_resultSetPackage);
    outputted++;
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public OriginPartNotToKeep_querySpec s_querySpec; // optional
public Period s_originSuggestedPeriod; // optional
public ASN1GeneralizedTime s_expiration; // optional
public InternationalString s_resultSetPackage; // optional

} // OriginPartNotToKeep

//----------------------------------------------------------------
//EOF
