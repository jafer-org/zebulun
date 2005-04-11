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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:14:28 UTC
 */

//----------------------------------------------------------------

package z3950.v2;
import asn1.*;


//================================================================
/**
 * Class for representing a <code>RPNStructure</code> from <code>IR</code>
 *
 * <pre>
 * RPNStructure ::=
 * CHOICE {
 *   [0] EXPLICIT Operand
 *   [1] IMPLICIT RPNStructure1
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class RPNStructure extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080314Z";

//----------------------------------------------------------------
/**
 * Default constructor for a RPNStructure.
 */

public
RPNStructure()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a RPNStructure from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
RPNStructure(BEREncoding ber, boolean check_tag)
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

  c_1 = null;
  c_2 = null;

  // Try choice number 1
  if (ber.tag_get() == 0 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagwrapper = (BERConstructed) ber;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun RPNStructure: bad BER form\n");
    }
    if (tagwrapper.number_components() != 1)
      throw new ASN1EncodingException
        ("Zebulun RPNStructure: bad BER form\n");
    c_1 = new Operand(tagwrapper.elementAt(0), true);
    return;
  }

  // Try choice number 2
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_2 = new RPNStructure1(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun RPNStructure: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of RPNStructure.
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

  // Encoding choice: c_1
  if (c_1 != null) {
    enc = new BEREncoding[1];
    enc[0] = c_1.ber_encode();
    chosen = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 0, enc);
  }

  // Encoding choice: c_2
  if (c_2 != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_2.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
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

  throw new ASN1EncodingException("Zebulun RPNStructure: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the RPNStructure. 
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

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public Operand c_1;
public RPNStructure1 c_2;

} // RPNStructure

//----------------------------------------------------------------
//EOF
