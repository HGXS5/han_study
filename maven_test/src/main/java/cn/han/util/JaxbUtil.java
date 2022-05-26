package cn.han.util;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

public class JaxbUtil {

	public static Optional<String> toXml(Object obj) {
		return toXml(obj, "UTF-8");
	}

	public static Optional<String> toXml(Object obj, String encoding) {
		String result = null;
		try (StringWriter writer = new StringWriter()) {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
		}

		return Optional.ofNullable(result);
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<T> fromXml(String xml, Class<T> c) {
		T result = null;
		try (StringReader sr = new StringReader(xml)) {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			SAXSource source = new SAXSource(new NamespaceFilter(), new InputSource(sr));
			result = (T) unmarshaller.unmarshal(source);
		} catch (Exception e) {
		}

		return Optional.ofNullable(result);
	}

}