/**
 * Copyright 2016 Massimo Zaniboni <massimo.zaniboni@docmelody.com>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.docmelody.svg_to_elm;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;

public class SvgToElm extends DefaultHandler {

    int _indentLevel = 0;
    boolean _isFirstElement;

    public void startDocument() throws SAXException {
        this._indentLevel = 0;
        this._isFirstElement = true;
    }

    protected void printIndent(boolean isFirstElement) {
        System.out.print("\n");
        for (int i = 0; i < this._indentLevel; i++) {
            System.out.print("  ");
        }

        if (isFirstElement) {
            System.out.print("  ");
        } else {
            System.out.print(", ");
        }
    }

    @Override
    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts)
            throws SAXException {

        this.printIndent(this._isFirstElement);

        System.out.print(this.fromSvgElementToElm(localName));
        System.out.print(" [");

        // NOTE: inside a new element/node, we start with no child nodes.
        this._isFirstElement = true;
        this._indentLevel++;

        boolean isFirstAttr = true;
        for (int i = 0; i < atts.getLength(); i++) {

            String attrName = atts.getLocalName(i);
            String attrValue = atts.getValue(i);

            this.printIndent(isFirstAttr);
            System.out.print(this.fromSvgAttrToElm(attrName));
            System.out.print(" \"");
            System.out.print(attrValue.toString());
            System.out.print("\"");

            isFirstAttr = false;
        }

        System.out.print("] [");
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.print("]");
        this._isFirstElement = false;
        this._indentLevel--;
    }

    protected static final Map<String, String> _fromSvgElementToElm;
    protected static final Map<String, String> _fromSvgAttributeToElm;

    static {
        Map<String, String> me = new HashMap<String, String>();
        me.put("text", "text'");
        me.put("path", "Svg.path");
        me.put("style", "Svg.style");
        _fromSvgElementToElm = me;

        Map<String, String> ma = new HashMap<String, String>();
        ma.put("text", "text");
        ma.put("path", "Svg.Attributes.path");
        ma.put("style", "Svg.Attributes.style");
        _fromSvgAttributeToElm = ma;
    }

    public String fromSvgElementToElm(String svgName) {
        String r = this._fromSvgElementToElm.get(svgName);
        if (r == null) {
            return svgName;
        } else {
            return r;
        }
    }

    public String fromSvgAttrToElm(String svgName) {
        String r = this._fromSvgAttributeToElm.get(svgName);
        if (r == null) {
            return svgName;
        } else {
            return r;
        }
    }
}
