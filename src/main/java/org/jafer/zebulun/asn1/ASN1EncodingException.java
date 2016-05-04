/*
 * $Id: ASN1EncodingException.java,v 1.1.1.1 1998/12/29 00:19:41 hoylen Exp $
 *
 * Copyright (C) 1996, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 */
package org.jafer.zebulun.asn1;

//----------------------------------------------------------------
/**
 * ASN1EncodingException
 *
 * @version	$Release$ $Date: 1998/12/29 00:19:41 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public class ASN1EncodingException extends ASN1Exception {

  public ASN1EncodingException(String message, Throwable cause) {
    super(message, cause);
  }

  public ASN1EncodingException(String message) {
    super(message);
  }

  public ASN1EncodingException(Throwable cause) {
    this("ASN.1 encoding exception", cause);
  }

  public ASN1EncodingException() {
    this("ASN.1 encoding exception");
  }
}

//----------------------------------------------------------------
/*
  $Log: ASN1EncodingException.java,v $
  Revision 1.1.1.1  1998/12/29 00:19:41  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
