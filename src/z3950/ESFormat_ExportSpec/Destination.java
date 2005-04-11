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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:28 UTC
 */

//----------------------------------------------------------------

package z3950.ESFormat_ExportSpec;
import asn1.*;
import z3950.v3.CompSpec;
import z3950.v3.InternationalString;

//================================================================
/**
 * Class for representing a <code>Destination</code> from <code>ESFormat-ExportSpecification</code>
 *
 * <pre>
 * Destination ::=
 * CHOICE {
 *   phoneNumber [1] IMPLICIT InternationalString
 *   faxNumber [2] IMPLICIT InternationalString
 *   x400address [3] IMPLICIT InternationalString
 *   emailAddress [4] IMPLICIT InternationalString
 *   pagerNumber [5] IMPLICIT InternationalString
 *   ftpAddress [6] IMPLICIT InternationalString
 *   ftamAddress [7] IMPLICIT InternationalString
 *   printerAddress [8] IMPLICIT InternationalString
 *   other [100] IMPLICIT Destination_other
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class Destination extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a Destination.
 */

public
Destination()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a Destination from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
Destination(BEREncoding ber, boolean check_tag)
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

  c_phoneNumber = null;
  c_faxNumber = null;
  c_x400address = null;
  c_emailAddress = null;
  c_pagerNumber = null;
  c_ftpAddress = null;
  c_ftamAddress = null;
  c_printerAddress = null;
  c_other = null;

  // Try choice phoneNumber
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_phoneNumber = new InternationalString(ber, false);
    return;
  }

  // Try choice faxNumber
  if (ber.tag_get() == 2 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_faxNumber = new InternationalString(ber, false);
    return;
  }

  // Try choice x400address
  if (ber.tag_get() == 3 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_x400address = new InternationalString(ber, false);
    return;
  }

  // Try choice emailAddress
  if (ber.tag_get() == 4 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_emailAddress = new InternationalString(ber, false);
    return;
  }

  // Try choice pagerNumber
  if (ber.tag_get() == 5 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_pagerNumber = new InternationalString(ber, false);
    return;
  }

  // Try choice ftpAddress
  if (ber.tag_get() == 6 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_ftpAddress = new InternationalString(ber, false);
    return;
  }

  // Try choice ftamAddress
  if (ber.tag_get() == 7 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_ftamAddress = new InternationalString(ber, false);
    return;
  }

  // Try choice printerAddress
  if (ber.tag_get() == 8 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_printerAddress = new InternationalString(ber, false);
    return;
  }

  // Try choice other
  if (ber.tag_get() == 100 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_other = new Destination_other(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun Destination: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of Destination.
 *
 * @return	The BER encoding.
 * @exception	ASN1Exception Invalid or cannot be encoded.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  BEREncoding chosen = null;

  // Encoding choice: c_phoneNumber
  if (c_phoneNumber != null) {
    chosen = c_phoneNumber.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
  }

  // Encoding choice: c_faxNumber
  if (c_faxNumber != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_faxNumber.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);
  }

  // Encoding choice: c_x400address
  if (c_x400address != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_x400address.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 3);
  }

  // Encoding choice: c_emailAddress
  if (c_emailAddress != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_emailAddress.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 4);
  }

  // Encoding choice: c_pagerNumber
  if (c_pagerNumber != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_pagerNumber.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 5);
  }

  // Encoding choice: c_ftpAddress
  if (c_ftpAddress != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_ftpAddress.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 6);
  }

  // Encoding choice: c_ftamAddress
  if (c_ftamAddress != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_ftamAddress.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 7);
  }

  // Encoding choice: c_printerAddress
  if (c_printerAddress != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_printerAddress.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 8);
  }

  // Encoding choice: c_other
  if (c_other != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_other.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 100);
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

  throw new ASN1EncodingException("Zebulun Destination: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the Destination. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_phoneNumber != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: phoneNumber> ");
    found = true;
    str.append("phoneNumber ");
  str.append(c_phoneNumber);
  }

  if (c_faxNumber != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: faxNumber> ");
    found = true;
    str.append("faxNumber ");
  str.append(c_faxNumber);
  }

  if (c_x400address != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: x400address> ");
    found = true;
    str.append("x400address ");
  str.append(c_x400address);
  }

  if (c_emailAddress != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: emailAddress> ");
    found = true;
    str.append("emailAddress ");
  str.append(c_emailAddress);
  }

  if (c_pagerNumber != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: pagerNumber> ");
    found = true;
    str.append("pagerNumber ");
  str.append(c_pagerNumber);
  }

  if (c_ftpAddress != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: ftpAddress> ");
    found = true;
    str.append("ftpAddress ");
  str.append(c_ftpAddress);
  }

  if (c_ftamAddress != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: ftamAddress> ");
    found = true;
    str.append("ftamAddress ");
  str.append(c_ftamAddress);
  }

  if (c_printerAddress != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: printerAddress> ");
    found = true;
    str.append("printerAddress ");
  str.append(c_printerAddress);
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

public InternationalString c_phoneNumber;
public InternationalString c_faxNumber;
public InternationalString c_x400address;
public InternationalString c_emailAddress;
public InternationalString c_pagerNumber;
public InternationalString c_ftpAddress;
public InternationalString c_ftamAddress;
public InternationalString c_printerAddress;
public Destination_other c_other;

} // Destination

//----------------------------------------------------------------
//EOF
