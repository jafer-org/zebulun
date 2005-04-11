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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:14:26 UTC
 */

//----------------------------------------------------------------

package z3950.v2;
import asn1.*;


//================================================================
/**
 * Class for representing a <code>AttributesPlusTerm</code> from <code>IR</code>
 *
 * <pre>
 * AttributesPlusTerm ::=
 * [102] IMPLICIT SEQUENCE {
 *   AttributeList
 *   Term
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class AttributesPlusTerm extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080314Z";

//----------------------------------------------------------------
/**
 * Default constructor for a AttributesPlusTerm.
 */

public
AttributesPlusTerm()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a AttributesPlusTerm from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
AttributesPlusTerm(BEREncoding ber, boolean check_tag)
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
  // Check tag matches

  if (check_tag) {
    if (ber.tag_get() != 102 ||
        ber.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
      throw new ASN1EncodingException
        ("Zebulun: AttributesPlusTerm: bad BER: tag=" + ber.tag_get() + " expected 102\n");
  }

  // AttributesPlusTerm should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun AttributesPlusTerm: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;

  // Decoding: AttributeList

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun AttributesPlusTerm: incomplete");
  }
  p = ber_cons.elementAt(part);

  s_1 = new AttributeList(p, true);
  part++;

  // Decoding: Term

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun AttributesPlusTerm: incomplete");
  }
  p = ber_cons.elementAt(part);

  s_2 = new Term(p, true);
  part++;

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun AttributesPlusTerm: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the AttributesPlusTerm.
 *
 * @exception	ASN1Exception Invalid or cannot be encoded.
 * @return	The BER encoding.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  return ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 102);
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of AttributesPlusTerm, implicitly tagged.
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

  // Encoding s_1: AttributeList 

  fields[x++] = s_1.ber_encode();

  // Encoding s_2: Term 

  fields[x++] = s_2.ber_encode();

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the AttributesPlusTerm. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  str.append(s_1);
  outputted++;

  if (0 < outputted)
    str.append(", ");
  str.append(s_2);
  outputted++;

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public AttributeList s_1;
public Term s_2;

} // AttributesPlusTerm

//----------------------------------------------------------------
//EOF
