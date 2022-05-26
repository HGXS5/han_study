package cn.han.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

public class NamespaceFilter extends XMLFilterImpl {

	public NamespaceFilter() throws SAXException {
		super(XMLReaderFactory.createXMLReader());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		super.startElement("", localName, qName, atts);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement("", localName, localName);
	}

	@Override
	public void startPrefixMapping(String prefix, String url) throws SAXException {
		
	}

}
