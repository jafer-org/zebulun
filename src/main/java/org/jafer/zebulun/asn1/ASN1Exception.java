/*
 * $Id: ASN1Exception.java,v 1.1.1.1 1998/12/29 00:19:40 hoylen Exp $
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
 * ASN1Exception
 *
 * @version	$Release$ $Date: 1998/12/29 00:19:40 $
 * @author	Hoylen Sue (h.sue@ieee.org)
 */
//----------------------------------------------------------------
public class ASN1Exception extends Exception {

  public ASN1Exception(String message, Throwable cause) {
    super(message, cause);
  }

  public ASN1Exception(String message) {
    super(message);
  }

  public ASN1Exception(Throwable cause) {
   this("ASN.1 exception", cause);
  }
  
  public ASN1Exception() {
    this("ASN.1 exception");
  }
}

//----------------------------------------------------------------
/*
  $Log: ASN1Exception.java,v $
  Revision 1.1.1.1  1998/12/29 00:19:40  hoylen
  Imported sources

 */
//----------------------------------------------------------------
//EOF
