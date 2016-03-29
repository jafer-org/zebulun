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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:31 UTC
 */

//----------------------------------------------------------------

package z3950.ElementSpec;
import asn1.*;
import z3950.RS_generic.Variant;
import z3950.v3.InternationalString;
import z3950.v3.StringOrNumeric;

//================================================================
/**
 * Class for representing a <code>SimpleElement</code> from <code>ElementSpecificationFormat-eSpec-1</code>
 *
 * <pre>
 * SimpleElement ::=
 * SEQUENCE {
 *   path [1] IMPLICIT TagPath
 *   variantRequest [2] IMPLICIT Variant OPTIONAL
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class SimpleElement extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a SimpleElement.
 */

public
SimpleElement()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a SimpleElement from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
SimpleElement(BEREncoding ber, boolean check_tag)
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
  // SimpleElement should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun SimpleElement: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;

  // Decoding: path [1] IMPLICIT TagPath

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun SimpleElement: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 1 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun SimpleElement: bad tag in s_path\n");

  s_path = new TagPath(p, false);
  part++;

  // Remaining elements are optional, set variables
  // to null (not present) so can return at end of BER

  s_variantRequest = null;

  // Decoding: variantRequest [2] IMPLICIT Variant OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 2 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    s_variantRequest = new Variant(p, false);
    part++;
  }

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun SimpleElement: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the SimpleElement.
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
 * Returns a BER encoding of SimpleElement, implicitly tagged.
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
  if (s_variantRequest != null)
    num_fields++;

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;

  // Encoding s_path: TagPath 

  fields[x++] = s_path.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);

  // Encoding s_variantRequest: Variant OPTIONAL

  if (s_variantRequest != null) {
    fields[x++] = s_variantRequest.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);
  }

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the SimpleElement. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  str.append("path ");
  str.append(s_path);
  outputted++;

  if (s_variantRequest != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("variantRequest ");
    str.append(s_variantRequest);
    outputted++;
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public TagPath s_path;
public Variant s_variantRequest; // optional

} // SimpleElement

//----------------------------------------------------------------
//EOF
