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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:20:30 UTC
 */

//----------------------------------------------------------------

package z3950.ESFormat_CIPOrder;
import asn1.*;
import z3950.v3.InternationalString;
import z3950.v3.OtherInformation;

//================================================================
/**
 * Class for representing a <code>DeliveryMethod</code> from <code>CIP-Order-ES</code>
 *
 * <pre>
 * DeliveryMethod ::=
 * CHOICE {
 *   eMail [1] EXPLICIT InternationalString
 *   ftp [2] EXPLICIT FTPDelivery
 *   mail [3] EXPLICIT PostalAddress
 *   otherInfo [4] EXPLICIT OtherInformation
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class DeliveryMethod extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080320Z";

//----------------------------------------------------------------
/**
 * Default constructor for a DeliveryMethod.
 */

public
DeliveryMethod()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a DeliveryMethod from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
DeliveryMethod(BEREncoding ber, boolean check_tag)
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
  BERConstructed tagwrapper;

  // Null out all choices

  c_eMail = null;
  c_ftp = null;
  c_mail = null;
  c_otherInfo = null;

  // Try choice eMail
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagwrapper = (BERConstructed) ber;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    }
    if (tagwrapper.number_components() != 1)
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    c_eMail = new InternationalString(tagwrapper.elementAt(0), true);
    return;
  }

  // Try choice ftp
  if (ber.tag_get() == 2 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagwrapper = (BERConstructed) ber;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    }
    if (tagwrapper.number_components() != 1)
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    c_ftp = new FTPDelivery(tagwrapper.elementAt(0), true);
    return;
  }

  // Try choice mail
  if (ber.tag_get() == 3 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagwrapper = (BERConstructed) ber;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    }
    if (tagwrapper.number_components() != 1)
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    c_mail = new PostalAddress(tagwrapper.elementAt(0), true);
    return;
  }

  // Try choice otherInfo
  if (ber.tag_get() == 4 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagwrapper = (BERConstructed) ber;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    }
    if (tagwrapper.number_components() != 1)
      throw new ASN1EncodingException
        ("Zebulun DeliveryMethod: bad BER form\n");
    c_otherInfo = new OtherInformation(tagwrapper.elementAt(0), true);
    return;
  }

  throw new ASN1Exception("Zebulun DeliveryMethod: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of DeliveryMethod.
 *
 * @return	The BER encoding.
 * @exception	ASN1Exception Invalid or cannot be encoded.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  BEREncoding chosen = null;

  BEREncoding enc[];

  // Encoding choice: c_eMail
  if (c_eMail != null) {
    enc = new BEREncoding[1];
    enc[0] = c_eMail.ber_encode();
    chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 1, enc);
  }

  // Encoding choice: c_ftp
  if (c_ftp != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    enc = new BEREncoding[1];
    enc[0] = c_ftp.ber_encode();
    chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 2, enc);
  }

  // Encoding choice: c_mail
  if (c_mail != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    enc = new BEREncoding[1];
    enc[0] = c_mail.ber_encode();
    chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 3, enc);
  }

  // Encoding choice: c_otherInfo
  if (c_otherInfo != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    enc = new BEREncoding[1];
    enc[0] = c_otherInfo.ber_encode();
    chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 4, enc);
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

  throw new ASN1EncodingException("Zebulun DeliveryMethod: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the DeliveryMethod. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_eMail != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: eMail> ");
    found = true;
    str.append("eMail ");
  str.append(c_eMail);
  }

  if (c_ftp != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: ftp> ");
    found = true;
    str.append("ftp ");
  str.append(c_ftp);
  }

  if (c_mail != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: mail> ");
    found = true;
    str.append("mail ");
  str.append(c_mail);
  }

  if (c_otherInfo != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: otherInfo> ");
    found = true;
    str.append("otherInfo ");
  str.append(c_otherInfo);
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public InternationalString c_eMail;
public FTPDelivery c_ftp;
public PostalAddress c_mail;
public OtherInformation c_otherInfo;

} // DeliveryMethod

//----------------------------------------------------------------
//EOF
