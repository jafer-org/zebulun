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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:15:12 UTC
 */

//----------------------------------------------------------------

package z3950.DiagFormat;
import asn1.*;
import z3950.v3.AttributeList;
import z3950.v3.DatabaseName;
import z3950.v3.DefaultDiagFormat;
import z3950.v3.InternationalString;
import z3950.v3.SortElement;
import z3950.v3.Specification;
import z3950.v3.Term;

//================================================================
/**
 * Class for representing a <code>DiagFormat_segmentation</code> from <code>DiagnosticFormatDiag1</code>
 *
 * <pre>
 * DiagFormat_segmentation ::=
 * CHOICE {
 *   segmentCount [0] IMPLICIT NULL
 *   segmentSize [1] IMPLICIT INTEGER
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class DiagFormat_segmentation extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080315Z";

//----------------------------------------------------------------
/**
 * Default constructor for a DiagFormat_segmentation.
 */

public
DiagFormat_segmentation()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a DiagFormat_segmentation from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
DiagFormat_segmentation(BEREncoding ber, boolean check_tag)
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

  c_segmentCount = null;
  c_segmentSize = null;

  // Try choice segmentCount
  if (ber.tag_get() == 0 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_segmentCount = new ASN1Null(ber, false);
    return;
  }

  // Try choice segmentSize
  if (ber.tag_get() == 1 &&
      ber.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    c_segmentSize = new ASN1Integer(ber, false);
    return;
  }

  throw new ASN1Exception("Zebulun DiagFormat_segmentation: bad BER encoding: choice not matched");
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of DiagFormat_segmentation.
 *
 * @return	The BER encoding.
 * @exception	ASN1Exception Invalid or cannot be encoded.
 */

public BEREncoding
ber_encode()
       throws ASN1Exception
{
  BEREncoding chosen = null;

  // Encoding choice: c_segmentCount
  if (c_segmentCount != null) {
    chosen = c_segmentCount.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 0);
  }

  // Encoding choice: c_segmentSize
  if (c_segmentSize != null) {
    if (chosen != null)
      throw new ASN1Exception("CHOICE multiply set");
    chosen = c_segmentSize.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 1);
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

  throw new ASN1EncodingException("Zebulun DiagFormat_segmentation: cannot implicitly tag");
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the DiagFormat_segmentation. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");

  boolean found = false;

  if (c_segmentCount != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: segmentCount> ");
    found = true;
    str.append("segmentCount ");
  str.append(c_segmentCount);
  }

  if (c_segmentSize != null) {
    if (found)
      str.append("<ERROR: multiple CHOICE: segmentSize> ");
    found = true;
    str.append("segmentSize ");
  str.append(c_segmentSize);
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public ASN1Null c_segmentCount;
public ASN1Integer c_segmentSize;

} // DiagFormat_segmentation

//----------------------------------------------------------------
//EOF