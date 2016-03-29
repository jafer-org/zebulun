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
 * Generated by Zebulun ASN1tojava: 1998-09-08 03:14:28 UTC
 */

//----------------------------------------------------------------

package z3950.v2;
import asn1.*;


//================================================================
/**
 * Class for representing a <code>ResourceReportResponse</code> from <code>IR</code>
 *
 * <pre>
 * ResourceReportResponse ::=
 * SEQUENCE {
 *   referenceId ReferenceId OPTIONAL
 *   resourceReportStatus [50] IMPLICIT INTEGER
 *   resourceReport [51] EXPLICIT ResourceReport OPTIONAL
 * }
 * </pre>
 *
 * @version	$Release$ $Date$
 */

//----------------------------------------------------------------

public final class ResourceReportResponse extends ASN1Any
{

  public final static String VERSION = "Copyright (C) Hoylen Sue, 1998. 199809080314Z";

//----------------------------------------------------------------
/**
 * Default constructor for a ResourceReportResponse.
 */

public
ResourceReportResponse()
{
}

//----------------------------------------------------------------
/**
 * Constructor for a ResourceReportResponse from a BER encoding.
 * <p>
 *
 * @param ber the BER encoding.
 * @param check_tag will check tag if true, use false
 *         if the BER has been implicitly tagged. You should
 *         usually be passing true.
 * @exception	ASN1Exception if the BER encoding is bad.
 */

public
ResourceReportResponse(BEREncoding ber, boolean check_tag)
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
  // ResourceReportResponse should be encoded by a constructed BER

  BERConstructed ber_cons;
  try {
    ber_cons = (BERConstructed) ber;
  } catch (ClassCastException e) {
    throw new ASN1EncodingException
      ("Zebulun ResourceReportResponse: bad BER form\n");
  }

  // Prepare to decode the components

  int num_parts = ber_cons.number_components();
  int part = 0;
  BEREncoding p;
  BERConstructed tagged;

  // Decoding: referenceId ReferenceId OPTIONAL

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun ResourceReportResponse: incomplete");
  }
  p = ber_cons.elementAt(part);

  try {
    s_referenceId = new ReferenceId(p, true);
    part++; // yes, consumed
  } catch (ASN1Exception e) {
    s_referenceId = null; // no, not present
  }

  // Decoding: resourceReportStatus [50] IMPLICIT INTEGER

  if (num_parts <= part) {
    // End of record, but still more elements to get
    throw new ASN1Exception("Zebulun ResourceReportResponse: incomplete");
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() != 50 ||
      p.tag_type_get() != BEREncoding.CONTEXT_SPECIFIC_TAG)
    throw new ASN1EncodingException
      ("Zebulun ResourceReportResponse: bad tag in s_resourceReportStatus\n");

  s_resourceReportStatus = new ASN1Integer(p, false);
  part++;

  // Remaining elements are optional, set variables
  // to null (not present) so can return at end of BER

  s_resourceReport = null;

  // Decoding: resourceReport [51] EXPLICIT ResourceReport OPTIONAL

  if (num_parts <= part) {
    return; // no more data, but ok (rest is optional)
  }
  p = ber_cons.elementAt(part);

  if (p.tag_get() == 51 &&
      p.tag_type_get() == BEREncoding.CONTEXT_SPECIFIC_TAG) {
    try {
      tagged = (BERConstructed) p;
    } catch (ClassCastException e) {
      throw new ASN1EncodingException
        ("Zebulun ResourceReportResponse: bad BER encoding: s_resourceReport tag bad\n");
    }
    if (tagged.number_components() != 1) {
      throw new ASN1EncodingException
        ("Zebulun ResourceReportResponse: bad BER encoding: s_resourceReport tag bad\n");
    }

    s_resourceReport = new ResourceReport(tagged.elementAt(0), true);
    part++;
  }

  // Should not be any more parts

  if (part < num_parts) {
    throw new ASN1Exception("Zebulun ResourceReportResponse: bad BER: extra data " + part + "/" + num_parts + " processed");
  }
}

//----------------------------------------------------------------
/**
 * Returns a BER encoding of the ResourceReportResponse.
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
 * Returns a BER encoding of ResourceReportResponse, implicitly tagged.
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
  if (s_referenceId != null)
    num_fields++;
  if (s_resourceReport != null)
    num_fields++;

  // Encode it

  BEREncoding fields[] = new BEREncoding[num_fields];
  int x = 0;
  BEREncoding enc[];

  // Encoding s_referenceId: ReferenceId OPTIONAL

  if (s_referenceId != null) {
    fields[x++] = s_referenceId.ber_encode();
  }

  // Encoding s_resourceReportStatus: INTEGER 

  fields[x++] = s_resourceReportStatus.ber_encode(BEREncoding.CONTEXT_SPECIFIC_TAG, 50);

  // Encoding s_resourceReport: ResourceReport OPTIONAL

  if (s_resourceReport != null) {
    enc = new BEREncoding[1];
    enc[0] = s_resourceReport.ber_encode();
    fields[x++] = new BERConstructed(BEREncoding.CONTEXT_SPECIFIC_TAG, 51, enc);
  }

  return new BERConstructed(tag_type, tag, fields);
}

//----------------------------------------------------------------
/**
 * Returns a new String object containing a text representing
 * of the ResourceReportResponse. 
 */

public String
toString()
{
  StringBuffer str = new StringBuffer("{");
  int outputted = 0;

  if (s_referenceId != null) {
    str.append("referenceId ");
    str.append(s_referenceId);
    outputted++;
  }

  if (0 < outputted)
    str.append(", ");
  str.append("resourceReportStatus ");
  str.append(s_resourceReportStatus);
  outputted++;

  if (s_resourceReport != null) {
    if (0 < outputted)
    str.append(", ");
    str.append("resourceReport ");
    str.append(s_resourceReport);
    outputted++;
  }

  str.append("}");

  return str.toString();
}

//----------------------------------------------------------------
/*
 * Internal variables for class.
 */

public ReferenceId s_referenceId; // optional
public ASN1Integer s_resourceReportStatus;
public ResourceReport s_resourceReport; // optional

//----------------------------------------------------------------
/*
 * Enumerated constants for class.
 */

// Enumerated constants for resourceReportStatus
public static final int E_success = 0;
public static final int E_partial = 1;
public static final int E_failure_1 = 2;
public static final int E_failure_2 = 3;
public static final int E_failure_3 = 4;
public static final int E_failure_4 = 5;

} // ResourceReportResponse

//----------------------------------------------------------------
//EOF
