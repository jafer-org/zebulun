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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:24 UTC
 */

//----------------------------------------------------------------

package z3950.AccessCtrl_prompt;
import asn1.*;
import z3950.v3.DiagRec;
import z3950.v3.InternationalString;

//================================================================
/**
 * Class for representing a <code>Response_promptResponse</code> from <code>AccessControlFormat-prompt-1</code>
 *
 * <pre>
 * Response_promptResponse ::=
 * CHOICE {
 *   string [1] IMPLICIT InternationalString
 *   accept [2] IMPLICIT BOOLEAN
 *   acknowledge [3] IMPLICIT NULL
 *   diagnostic [4] EXPLICIT DiagRec
 *   encrypted [5] IMPLICIT Encryption
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class Response_promptResponse extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a Response_promptResponse.
 */

public
Response_promptResponse()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a Response_promptResponse from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
Response_promptResponse(BEREncoding ber, boolean check_tag)
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

  c_string = null;
  c_accept = null;
  c_acknowledge = null;
  c_diagnostic = null;
  c_encrypted = null;

  // Try choice string
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_string = new InternationalString(ber, false);
    return;
  }

  // Try choice accept
  if (ber.tag_get() == 2 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_accept = new ASN1Boolean(ber, false);
    return;
  }

  // Try choice acknowledge
  if (ber.tag_get() == 3 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_acknowledge = new ASN1Null(ber, false);
    return;
  }

  // Try choice diagnostic
  if (ber.tag_get() == 4 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagwrapper = (BERConstructed) ber;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun Response_promptResponse: bad BER form\n");
    }
    if (tagwrapper.number_components() != 1)
      throw new ASN1EncodingException
        ("Zebulun Response_promptResponse: bad BER form\n");
    c_diagnostic = new DiagRec(tagwrapper.elementAt(0), true);
    return;
  }

  // Try choice encrypted
  if (ber.tag_get() == 5 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_encrypted = new Encryption(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun Response_promptResponse: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of Response_promptResponse.
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

  // Encoding choice: c_string
  if (c_string != null) {
    chosen = c_string.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
  }

  // Encoding choice: c_accept
  if (c_accept != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_accept.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 2);
  }

  // Encoding choice: c_acknowledge
  if (c_acknowledge != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_acknowledge.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 3);
  }

  // Encoding choice: c_diagnostic
  if (c_diagnostic != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    enc = new BEREncoding[1];
    enc[0] = c_diagnostic.ber_encode();
    chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 4, enc);
  }

  // Encoding choice: c_encrypted
  if (c_encrypted != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_encrypted.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 5);
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

  throw new ASN1EncodingException("Zebulun Response_promptResponse: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the Response_promptResponse. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_string != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: string> ");
    found = true;
    str.append("string ");
  str.append(c_string);
  }

  if (c_accept != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: accept> ");
    found = true;
    str.append("accept ");
  str.append(c_accept);
  }

  if (c_acknowledge != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: acknowledge> ");
    found = true;
    str.append("acknowledge ");
  str.append(c_acknowledge);
  }

  if (c_diagnostic != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: diagnostic> ");
    found = true;
    str.append("diagnostic ");
  str.append(c_diagnostic);
  }

  if (c_encrypted != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: encrypted> ");
    found = true;
    str.append("encrypted ");
  str.append(c_encrypted);
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public InternationalString c_string;
public ASN1Boolean c_accept;
public ASN1Null c_acknowledge;
public DiagRec c_diagnostic;
public Encryption c_encrypted;

} // Response_promptResponse

//----------------------------------------------------------------
//EOF
