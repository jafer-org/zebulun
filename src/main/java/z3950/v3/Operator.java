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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:06 UTC
 */

//----------------------------------------------------------------

package z3950.v3;
import asn1.*;


//================================================================
/**
 * Class for representing a <code>Operator</code> from <code>Z39-50-APDU-1995</code>
 *
 * <pre>
 * Operator ::=
 * [46] EXPLICIT CHOICE {
 *   and [0] IMPLICIT NULL
 *   or [1] IMPLICIT NULL
 *   and-not [2] IMPLICIT NULL
 *   prox [3] IMPLICIT ProximityOperator
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class Operator extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a Operator.
 */

public
Operator()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a Operator from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
Operator(BEREncoding ber, boolean check_tag)
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
    if (ber.tag_get() != 46 ||
        ber.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
      throw new ASN1EncodingException
        ("Zebulun: Operator: bad BER: tag=" + ber.tag_get() + " expected 46\n");
  }

  // Unwrap explicit tag

  BERConstructed tagwrapper;
  try {
    tagwrapper = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun Operator: bad BER tag form\n");
  }
  if (tagwrapper.number_components() != 1) 
    throw new ASN1EncodingException
      ("Zebulun Operator: bad BER tag form\n");
  ber = tagwrapper.elementAt(0);

  // Null out all choices

  c_and = null;
  c_or = null;
  c_and_not = null;
  c_prox = null;

  // Try choice and
  if (ber.tag_get() == 0 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_and = new ASN1Null(ber, false);
    return;
  }

  // Try choice or
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_or = new ASN1Null(ber, false);
    return;
  }

  // Try choice and-not
  if (ber.tag_get() == 2 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_and_not = new ASN1Null(ber, false);
    return;
  }

  // Try choice prox
  if (ber.tag_get() == 3 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_prox = new ProximityOperator(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun Operator: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of Operator.
 *
 * @return	The BER encoding.
 * @exception	ASN1Exception Invalid or cannot be encoded.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  return ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 46);
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of Operator, implicitly tagged.
 *
 * @return	The BER encoding of the object.
 * @exception	ASN1Exception When invalid or cannot be encoded.
 */

public BEREncoding
ber_encode(int tag_type, int tag)
       throws ASN1Exception
{
  BEREncoding chosen = null;

  // Encoding choice: c_and
  if (c_and != null) {
    chosen = c_and.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 0);
  }

  // Encoding choice: c_or
  if (c_or != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_or.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
  }

  // Encoding choice: c_and_not
  if (c_and_not != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_and_not.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);
  }

  // Encoding choice: c_prox
  if (c_prox != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_prox.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 3);
  }

  // Check for error of having none of the choices set
  if (chosen == null)
    throw new ASN1Exception("CHOICE not set");

  // Return chosen element wrapped in its explicit tag

  BEREncoding exp_tag_data[] = new BEREncoding[1];
  exp_tag_data[0] = chosen;
  return new BERConstructed(tag_type, tag, exp_tag_data);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the Operator. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_and != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: and> ");
    found = true;
    str.append("and ");
  str.append(c_and);
  }

  if (c_or != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: or> ");
    found = true;
    str.append("or ");
  str.append(c_or);
  }

  if (c_and_not != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: and-not> ");
    found = true;
    str.append("and-not ");
  str.append(c_and_not);
  }

  if (c_prox != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: prox> ");
    found = true;
    str.append("prox ");
  str.append(c_prox);
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public ASN1Null c_and;
public ASN1Null c_or;
public ASN1Null c_and_not;
public ProximityOperator c_prox;

} // Operator

//----------------------------------------------------------------
//EOF
