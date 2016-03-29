/*
 * $Id$
 *
 * Copyright (C) 1996, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */

package asn1;

//----------------------------------------------------------------
/**
 * Representation for ASN.1 PrintableString.
 *
 * The <code>PrintableString<code> type denotes an arbitary string
 * of printable characters from the following character set:
 *
 * <table>
 * <tr><td>Capital letters<td>A, B, ... , Z
 * <tr><td>Small letters<td>a, b, ..., z
 * <tr><td>Digits<td>0, 1, ..., 9
 * <tr><td>Space<td>
 * <tr><td>Apostrophe<td>'
 * <tr><td>Left partnthesis<td>(
 * <tr><td>Right partnthesis<td>)
 * <tr><td>Plus<td>+
 * <tr><td>comma<td>,
 * <tr><td>hyphen<td>-
 * <tr><td>Full stop<td>.
 * <tr><td>Solidus<td>/
 * <tr><td>Colon<td>:
 * <tr><td>Equal sign<td>=
 * <tr><td>Question mark<td>?
 * </table>

 * This type is a string type.
 *
 * @version	$Release$ $Date$
 * @author	Hoylen Sue <h.sue@ieee.org>
 */

//----------------------------------------------------------------

public final class ASN1PrintableString extends ASN1OctetString
{
  /**
   * This constant is the ASN.1 UNIVERSAL tag value for PrintableString.
   */

public static final int TAG = 0x13;

  //----------------------------------------------------------------
  /**
   * Constructor for a PrintableString object. It sets the tag to the
   * default value of UNIVERSAL 19 (0x13). */

public 
ASN1PrintableString(String text)
  {
    super(text);
  }

  //----------------------------------------------------------------
  /**
   * Constructor for a PrintableString object from a primitive BER encoding.
   *
   * @param ber The BER encoding to use.
   * @param check_tag If true, it checks the tag. Use false if is implicitly tagged.
   * @exception	ASN1Exception If the BER encoding is incorrect.
   */

public
ASN1PrintableString(BEREncoding ber, boolean check_tag)
       throws ASN1Exception
  {
    super(ber, false);

    if (check_tag) {
      if (ber.tag_get() != TAG || 
	  ber.tag_type_get() != BEREncoding.UNIVERSAL_TAG)
	throw new ASN1EncodingException
	  ("ASN.1 PrintableString: bad BER: tag=" + ber.tag_get() + 
	   " expected " + TAG + "\n");
    }
  }

  /*
   * ber_decode()
   * set()
   * get() 
   * toString()
   * are inherited from base class
   */

} // ASN1PrintableString

//----------------------------------------------------------------
/*
  $Log$
  */
//----------------------------------------------------------------
//EOF
