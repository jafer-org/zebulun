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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:23 UTC
 */

//----------------------------------------------------------------

package z3950.RS_generic;
import asn1.*;
import z3950.v3.IntUnit;
import z3950.v3.InternationalString;
import z3950.v3.StringOrNumeric;
import z3950.v3.Term;
import z3950.v3.Unit;

//================================================================
/**
 * Class for representing a <code>Variant_value</code> from <code>RecordSyntax-generic</code>
 *
 * <pre>
 * Variant_value ::=
 * CHOICE {
 *   INTEGER
 *   InternationalString
 *   OCTET STRING
 *   OBJECT IDENTIFIER
 *   BOOLEAN
 *   NULL
 *   unit [1] IMPLICIT Unit
 *   valueAndUnit [2] IMPLICIT IntUnit
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class Variant_value extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a Variant_value.
 */

public
Variant_value()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a Variant_value from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
Variant_value(BEREncoding ber, boolean check_tag)
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
  // Null out all choices

  c_1 = null;
  c_2 = null;
  c_3 = null;
  c_4 = null;
  c_5 = null;
  c_6 = null;
  c_unit = null;
  c_valueAndUnit = null;

  // Try choice number 1
  try {
    c_1 = new ASN1Integer(ber, check_tag);
    return;
  } catch (ASN1Exception e) {
    // failed to decode, continue on
  }

  // Try choice number 2
  try {
    c_2 = new InternationalString(ber, check_tag);
    return;
  } catch (ASN1Exception e) {
    // failed to decode, continue on
  }

  // Try choice number 3
  try {
    c_3 = new ASN1OctetString(ber, check_tag);
    return;
  } catch (ASN1Exception e) {
    // failed to decode, continue on
  }

  // Try choice number 4
  try {
    c_4 = new ASN1ObjectIdentifier(ber, check_tag);
    return;
  } catch (ASN1Exception e) {
    // failed to decode, continue on
  }

  // Try choice number 5
  try {
    c_5 = new ASN1Boolean(ber, check_tag);
    return;
  } catch (ASN1Exception e) {
    // failed to decode, continue on
  }

  // Try choice number 6
  try {
    c_6 = new ASN1Null(ber, check_tag);
    return;
  } catch (ASN1Exception e) {
    // failed to decode, continue on
  }

  // Try choice unit
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_unit = new Unit(ber, false);
    return;
  }

  // Try choice valueAndUnit
  if (ber.tag_get() == 2 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_valueAndUnit = new IntUnit(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun Variant_value: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of Variant_value.
 *
 * @return	The BER encoding.
 * @exception	ASN1Exception Invalid or cannot be encoded.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  BEREncoding chosen = null;

  // Encoding choice: c_1
  if (c_1 != null) {
  chosen = c_1.ber_encode();
  }

  // Encoding choice: c_2
  if (c_2 != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
  chosen = c_2.ber_encode();
  }

  // Encoding choice: c_3
  if (c_3 != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
  chosen = c_3.ber_encode();
  }

  // Encoding choice: c_4
  if (c_4 != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
  chosen = c_4.ber_encode();
  }

  // Encoding choice: c_5
  if (c_5 != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
  chosen = c_5.ber_encode();
  }

  // Encoding choice: c_6
  if (c_6 != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
  chosen = c_6.ber_encode();
  }

  // Encoding choice: c_unit
  if (c_unit != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_unit.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
  }

  // Encoding choice: c_valueAndUnit
  if (c_valueAndUnit != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_valueAndUnit.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);
  }

  // Check for error of having none of the choices set
  if (chosen == null)
    throw new ASN1Exception("CHOICE not set");

  return chosen;
}

//----------------------------------------------------------------

/**
 * Generating a BER encoding of the object
 * and implicitly tagging it.
 * <p>
 * This method is for internal use only. You should use
 * the ber_encode method that does not take a parameter.
 * <p>
 * This function should never be used, because this
 * production is a CHOICE.
 * It must never have an implicit tag.
 * <p>
 * An exception will be thrown if it is called.
 *
 * @param tag_type the type of the tag.
 * @param tag the tag.
 * @exception ASN1Exception if it cannot be BER encoded.
 */

public BEREncoding
ber_encode(int tag_type, int tag)
       throws ASN1Exception
{
  // This method must not be called!

  // Method is not available because this is a basic CHOICE
  // which does not have an explicit tag on it. So it is not
  // permitted to allow something else to apply an implicit
  // tag on it, otherwise the tag identifying which CHOICE
  // it is will be overwritten and lost.

  throw new ASN1EncodingException("Zebulun Variant_value: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the Variant_value. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_1 != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE> ");
    found = true;
  str.append(c_1);
  }

  if (c_2 != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE> ");
    found = true;
  str.append(c_2);
  }

  if (c_3 != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE> ");
    found = true;
  str.append(c_3);
  }

  if (c_4 != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE> ");
    found = true;
  str.append(c_4);
  }

  if (c_5 != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE> ");
    found = true;
  str.append(c_5);
  }

  if (c_6 != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE> ");
    found = true;
  str.append(c_6);
  }

  if (c_unit != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: unit> ");
    found = true;
    str.append("unit ");
  str.append(c_unit);
  }

  if (c_valueAndUnit != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: valueAndUnit> ");
    found = true;
    str.append("valueAndUnit ");
  str.append(c_valueAndUnit);
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public ASN1Integer c_1;
public InternationalString c_2;
public ASN1OctetString c_3;
public ASN1ObjectIdentifier c_4;
public ASN1Boolean c_5;
public ASN1Null c_6;
public Unit c_unit;
public IntUnit c_valueAndUnit;

} // Variant_value

//----------------------------------------------------------------
//EOF
