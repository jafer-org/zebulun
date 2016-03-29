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
 * Class for representing a <code>Period</code> from <code>ESFormat-PeriodicQuerySchedule</code>
 *
 * <pre>
 * Period ::=
 * CHOICE {
 *   unit [1] IMPLICIT IntUnit
 *   businessDaily [2] IMPLICIT NULL
 *   continuous [3] IMPLICIT NULL
 *   other [4] IMPLICIT InternationalString
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class Period extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a Period.
 */

public
Period()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a Period from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
Period(BEREncoding ber, boolean check_tag)
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

  c_unit = null;
  c_businessDaily = null;
  c_continuous = null;
  c_other = null;

  // Try choice unit
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_unit = new IntUnit(ber, false);
    return;
  }

  // Try choice businessDaily
  if (ber.tag_get() == 2 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_businessDaily = new ASN1Null(ber, false);
    return;
  }

  // Try choice continuous
  if (ber.tag_get() == 3 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_continuous = new ASN1Null(ber, false);
    return;
  }

  // Try choice other
  if (ber.tag_get() == 4 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_other = new InternationalString(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun Period: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of Period.
 *
 * @return	The BER encoding.
 * @exception	ASN1Exception Invalid or cannot be encoded.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  BEREncoding chosen = null;

  // Encoding choice: c_unit
  if (c_unit != null) {
    chosen = c_unit.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
  }

  // Encoding choice: c_businessDaily
  if (c_businessDaily != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_businessDaily.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);
  }

  // Encoding choice: c_continuous
  if (c_continuous != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_continuous.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 3);
  }

  // Encoding choice: c_other
  if (c_other != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_other.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 4);
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

  throw new ASN1EncodingException("Zebulun Period: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the Period. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_unit != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: unit> ");
    found = true;
    str.append("unit ");
  str.append(c_unit);
  }

  if (c_businessDaily != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: businessDaily> ");
    found = true;
    str.append("businessDaily ");
  str.append(c_businessDaily);
  }

  if (c_continuous != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: continuous> ");
    found = true;
    str.append("continuous ");
  str.append(c_continuous);
  }

  if (c_other != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: other> ");
    found = true;
    str.append("other ");
  str.append(c_other);
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public IntUnit c_unit;
public ASN1Null c_businessDaily;
public ASN1Null c_continuous;
public InternationalString c_other;

} // Period

//----------------------------------------------------------------
//EOF
