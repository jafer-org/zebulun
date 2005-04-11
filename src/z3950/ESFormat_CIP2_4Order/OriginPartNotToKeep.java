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

package z3950.ESFormat_CIP2_4Order;
import asn1.*;
import z3950.v3.InternationalString;
import z3950.v3.OtherInformation;

//================================================================
/**
 * Class for representing a <code>OriginPartNotToKeep</code> from <code>CIP-Order-ES</code>
 *
 * <pre>
 * OriginPartNotToKeep ::=
 * SEQUENCE {
 *   orderId [1] EXPLICIT InternationalString OPTIONAL
 *   orderSpecification [2] EXPLICIT OrderSpecification OPTIONAL
 *   userInformation [3] EXPLICIT UserInformation OPTIONAL
 *   otherInfo [4] EXPLICIT OtherInformation OPTIONAL
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class OriginPartNotToKeep extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080320Z";

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

  s_orderId = null;
  s_orderSpecification = null;
  s_userInformation = null;
  s_otherInfo = null;

  // Decoding: orderId [1] EXPLICIT InternationalString OPTIONAL

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
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_orderId tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_orderId tag bad\n");
    }

    s_orderId = new InternationalString(tagged.elementAt(0), true);
    part++;
  }

  // Decoding: orderSpecification [2] EXPLICIT OrderSpecification OPTIONAL

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
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_orderSpecification tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_orderSpecification tag bad\n");
    }

    s_orderSpecification = new OrderSpecification(tagged.elementAt(0), true);
    part++;
  }

  // Decoding: userInformation [3] EXPLICIT UserInformation OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 3 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagged = (BERConstructed) p;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_userInformation tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_userInformation tag bad\n");
    }

    s_userInformation = new UserInformation(tagged.elementAt(0), true);
    part++;
  }

  // Decoding: otherInfo [4] EXPLICIT OtherInformation OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 4 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagged = (BERConstructed) p;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_otherInfo tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun OriginPartNotToKeep: bad BER encoding: s_otherInfo tag bad\n");
    }

    s_otherInfo = new OtherInformation(tagged.elementAt(0), true);
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
  if (s_orderId != null)
    num_fields++;
  if (s_orderSpecification != null)
    num_fields++;
  if (s_userInformation != null)
    num_fields++;
  if (s_otherInfo != null)
    num_fields++;

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding enc[];

  // Encoding s_orderId: InternationalString OPTIONAL

  if (s_orderId != null) {
    enc = new BEREncoding[1];
    enc[0] = s_orderId.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 1, enc);
  }

  // Encoding s_orderSpecification: OrderSpecification OPTIONAL

  if (s_orderSpecification != null) {
    enc = new BEREncoding[1];
    enc[0] = s_orderSpecification.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 2, enc);
  }

  // Encoding s_userInformation: UserInformation OPTIONAL

  if (s_userInformation != null) {
    enc = new BEREncoding[1];
    enc[0] = s_userInformation.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 3, enc);
  }

  // Encoding s_otherInfo: OtherInformation OPTIONAL

  if (s_otherInfo != null) {
    enc = new BEREncoding[1];
    enc[0] = s_otherInfo.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 4, enc);
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

  if (s_orderId != null) {
    str.append("orderId ");
    str.append(s_orderId);
    outputted++;
  }

  if (s_orderSpecification != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("orderSpecification ");
    str.append(s_orderSpecification);
    outputted++;
  }

  if (s_userInformation != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("userInformation ");
    str.append(s_userInformation);
    outputted++;
  }

  if (s_otherInfo != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("otherInfo ");
    str.append(s_otherInfo);
    outputted++;
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public InternationalString s_orderId; // optional
public OrderSpecification s_orderSpecification; // optional
public UserInformation s_userInformation; // optional
public OtherInformation s_otherInfo; // optional

} // OriginPartNotToKeep

//----------------------------------------------------------------
//EOF