/**
 * ----------------------------------------------------------------
 * $Source: /projects/rdu/cvsroot/projects/zebulun/au/edu/dstc/zebulun/asn1/XERsaxHandler.java,v $
 * $Date: 1999/03/17 05:45:41 $
 * $Revision: 1.3 $
 * $Name:  $
 *
 * Copyright (C) 1996, 1999, Hoylen Sue.  All Rights Reserved.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Refer to
 * the supplied license for more details.
 * //----------------------------------------------------------------
 *
 */
package org.jafer.zebulun.asn1;

import java.util.Hashtable;
import org.xml.sax.HandlerBase;
import org.xml.sax.AttributeList;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

//----------------------------------------------------------------
/**
 * This class is an event handler for SAX. This allows XER encodings to be
 * parsed using any SAX compliant XML parser.
 * <p>
 * The way to use this class to parse XER using a SAX parser is:
 *
 * <pre>
 * XERsaxHandler handler = new XERsaxHandler();
 * handler.member_expect(new TARGET_CLASS_BEING_PARSED_FOR.XER_Parser_Proxy());
 * sax_parser.setDocumentHandler(handler);
 *
 * sax_parser.parse(input_source);
 *
 * ASN1Any result = handler.get();
 * if (result == null) {
 *
 * }
 * </pre>
 *
 * Refer to the SAX XML parser documentation for more details on how to use SAX
 * parsers.
 */
public class XERsaxHandler extends HandlerBase {

  // Zebulun's proxy interface
  //
  // This is implemented by classes designed to handle the parsing of
  // each type (standard ASN.1 types and types generated by Zebulun).
  // Nested top-level class
  public abstract static class XER_Parser_Proxy {

    protected String xer_tag; // expected XML tag name

    //----------------
    protected XER_Parser_Proxy(String expected_tag_title) {
      xer_tag = expected_tag_title;
    }

    //----------------
    public abstract void startElement(XERsaxHandler h,
            String name,
            AttributeList atts)
            throws SAXException;

    //----------------
    public abstract void endElement(XERsaxHandler h,
            String name)
            throws SAXException;

    //----------------
    public void characters(XERsaxHandler handler,
            char[] ch,
            int start,
            int length)
            throws SAXException {
      // All characters must be whitespace

      int begin = start;
      int end = begin + length;

      while (begin < end && Character.isWhitespace(ch[begin])) {
        begin++;
      }

      if (begin < end) {
        handler.throw_characters_unexpected(xer_tag);
      }
    }

    //----------------
    public void member(XERsaxHandler handler,
            ASN1Any result)
            throws SAXException {
      handler.throw_member_unexpected(xer_tag);
    }

  }

  //================================================================
  public XERsaxHandler() {
    proxy_stack = new java.util.Stack();

    namespaces = new Hashtable();

    result = null;
  }

  //----------------------------------------------------------------
  /**
   * Begin parsing for a specific production.
   *
   * Indicates that the XER parser should now begin to parse for the given type.
   * Call this before parsing begins.
   *
   * @param p XML parsing proxy object for the class being parsed for.
   */
  public void member_expect(XER_Parser_Proxy p) {
    if (proxy_stack.empty()) {
      result = null; // reset result
    }

    // Push it on
    proxy_stack.push(p);
  }

  //----------------------------------------------------------------
  /**
   * Successful parse of a production.
   *
   * You should not need to call this method at all. This is used internally to
   * indicate that the parsing for a production was successful and to return the
   * generated object to the previous parsing level.
   *
   * @param parsed_result the parsed object.
   * @exception SAXException on error.
   */
  public void member_got(ASN1Any parsed_result)
          throws SAXException {
    // Pop it off

    proxy_stack.pop();

    if (proxy_stack.empty()) {
      // Done
      result = parsed_result;

    } else {
      // Inform the previous proxy

      XER_Parser_Proxy current_proxy = (XER_Parser_Proxy) proxy_stack.peek();
      current_proxy.member(this, parsed_result);
    }
  }

  //----------------------------------------------------------------
  /**
   * Get the result of the parsing operation.
   *
   * This should be called after a successful parse has been performed.
   *
   * @return the ASN.1 structure, or null on error.
   */
  public ASN1Any get() // Returns the parsed result, or null on error.
  {
    if (proxy_stack.empty() && result != null) {
      return result;
    }

    return null; // no result
  }

  //================================================================
  /**
   * SAX callback method.
   *
   * Called by the SAX parser, it then redirect the event to the current proxy
   * object
   *
   * @param qualified_name XML tag name (including namespace prefix).
   * @param atts attribute list.
   * @exception SAXException on error.
   */
  public void startElement(String qualified_name, AttributeList atts)
          throws SAXException {
    //System.out.println("DEBUG: <" + qualified_name + ">");

    // Identify namespaces
    String localpart = name_localpart(qualified_name);

    // Get current proxy object for parsing
    if (proxy_stack.empty()) {
      throw_start_unexpected(null, localpart);
    }

    XER_Parser_Proxy current_proxy = (XER_Parser_Proxy) proxy_stack.peek();

    // Use current proxy object to parse it
    current_proxy.startElement(this, localpart, atts);
  }

  //----------------------------------------------------------------
  /**
   * SAX callback method.
   *
   * Called by the SAX parser, it then redirect the event to the current proxy
   * object
   *
   * @param qualified_name XML tag name (including namespace prefix).
   * @exception SAXException on error.
   */
  public void endElement(String qualified_name)
          throws SAXException {
    //System.out.println("DEBUG: </" + qualified_name + ">");

    // Namespaces
    String localpart = name_localpart(qualified_name);

    // Get current proxy object
    if (proxy_stack.empty()) {
      throw_end_unexpected(null, localpart);
    }

    XER_Parser_Proxy current_proxy = (XER_Parser_Proxy) proxy_stack.peek();

    // call proxy object
    current_proxy.endElement(this, localpart);
  }

  //----------------------------------------------------------------
  /**
   * SAX callback method.
   *
   * Called by the SAX parser, it then redirect the event to the current proxy
   * object
   *
   * @param ch characters received
   * @param start offset into character array for beginning of data.
   * @param length length of data in character array.
   * @exception SAXException on error.
   */
  public void characters(char[] ch, int start, int length)
          throws SAXException {
    //System.out.println("DEBUG: #PCDATA: " + length);

    int begin = start;

    if (!proxy_stack.empty()) {
      XER_Parser_Proxy current_proxy = (XER_Parser_Proxy) proxy_stack.peek();
      current_proxy.characters(this, ch, begin, length);

    } else {
      // No proxy on stack: all characters must be whitespace

      int end = begin + length;
      while (begin < end) {
        if (!Character.isWhitespace(ch[begin])) {
          this.throw_characters_unexpected(null);
        }

        begin++;
      }
    }
  }

  //----------------------------------------------------------------
  /**
   * SAX callback method.
   *
   * Called by the SAX parser when a locator is available. This is saved for use
   * when generating exceptions.
   *
   * @param locator the locator provided by the SAX parser.
   */
  public void setDocumentLocator(Locator locator) {
    // Save locator for use in exception messages, called by the SAX parser.
    sax_locator = locator;
  }

  //----------------------------------------------------------------
  // Routines for generating exceptions
  /**
   * Internal exception generator for an unexpected begin XML tag. This method
   * will always throw an exception.
   *
   * @param xer_tag the XML tag being parsed for.
   * @param name the XML tag that was found.
   * @exception org.xml.sax.SAXException the exception thrown.
   */
  public void throw_start_unexpected(String xer_tag, String name)
          throws org.xml.sax.SAXException {
    throw new org.xml.sax.SAXException(message(xer_tag)
            + "unexpected start tag: " + name);
  }

  /**
   * Internal exception generator for an unexpected end XML tag. This method
   * will always throw an exception.
   *
   * @param xer_tag the XML tag being parsed for.
   * @param name the XML tag that was found.
   * @exception org.xml.sax.SAXException the exception thrown.
   */
  public void throw_end_unexpected(String xer_tag, String name)
          throws org.xml.sax.SAXException {
    throw new org.xml.sax.SAXException(message(xer_tag)
            + "unexpected end tag: " + name);
  }

  /**
   * Internal exception generator for unexpected character data. This method
   * will always throw an exception.
   *
   * @param xer_tag the XML tag being parsed for.
   * @exception org.xml.sax.SAXException the exception thrown.
   */
  public void throw_characters_unexpected(String xer_tag)
          throws org.xml.sax.SAXException {
    throw new org.xml.sax.SAXException(message(xer_tag) + "unexpected PCDATA");
  }

  /**
   * Internal exception generator for an unexpected member. This method will
   * always throw an exception.
   *
   * @param xer_tag the XML tag being parsed for.
   * @exception org.xml.sax.SAXException the exception thrown.
   */
  public void throw_member_unexpected(String xer_tag)
          throws org.xml.sax.SAXException {
    throw new org.xml.sax.SAXException(message(xer_tag) + "unexpected member");
  }

  //----------------------------------------------------------------
  /**
   * Internal error message generator. Constructs a string with file and line
   * number details (when available) and the currently processing production.
   *
   * @param xer_tag the tag being parsed for (or null if none)
   */
  private String message(String xer_tag) {
    String msg = "XER Parser: ";

    if (sax_locator != null) {
      String id = sax_locator.getSystemId();
      if (id != null) {
        msg += id + ": ";
      }

      int line_number = sax_locator.getLineNumber();
      if (0 < line_number) {
        msg += "line " + line_number + ": ";
      }
    }

    if (xer_tag != null) {
      msg += "parsing " + xer_tag + ": ";
    } else {
      msg += "parsing completed: ";
    }

    return msg;
  }

  //----------------------------------------------------------------
  // Takes a string like "xer:BOOLEAN" and returns namespace, "BOOLEAN"
  private String name_localpart(String tag_name) {
    int index = tag_name.lastIndexOf(':');
    if (index < 0) {
      return tag_name; // no prefix, return qualified name as the local part
    } else {
      return tag_name.substring(index + 1); // return local part
    }
  }

  // xmlns:xer="http:/...."
  // Takes a string like "xer:BOOLEAN" and returns namespace, "http:/....."
  private String name_namespace(String tag_name) {
    int index = tag_name.lastIndexOf(':');
    if (index < 0) {
      // No prefix, return default namespace
      return "default";
    } else {
      // Extract prefix from qualified name
      String ns_full = (String) namespaces.get(tag_name.substring(0, index));
      if (ns_full == null) {
        // error
      }

      return ns_full;
    }
  }

  //================================================================
  /**
   * A reference to the locator provided by the SAX parser, or null if there is
   * none.
   */
  private Locator sax_locator; // default = null

  /**
   * Stack of XER parsing proxy objects. The top element being the one currently
   * being parsed for.
   */
  private java.util.Stack proxy_stack;

  // Not used yet. ???
  private Hashtable namespaces;

  /**
   * Holder of the top level parse result when it is successfully parsed. This
   * is then returned to the user when the get() function is called. Null if no
   * result.
   */
  private ASN1Any result;
}

//----------------------------------------------------------------
//EOF
