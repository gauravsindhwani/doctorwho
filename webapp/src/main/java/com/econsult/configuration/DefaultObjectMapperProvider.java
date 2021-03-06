package com.econsult.configuration;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

@Provider
public class DefaultObjectMapperProvider implements
ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;

	public DefaultObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
		
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {
			return defaultObjectMapper;
	}

	private static ObjectMapper createCombinedObjectMapper() {
		return new ObjectMapper()
		.configure(SerializationFeature.WRAP_ROOT_VALUE, true)
		.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
		.setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.enable(SerializationFeature.INDENT_OUTPUT);
		result.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		result.registerModule(new Hibernate4Module());
		return result;
	}

	private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {

		final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

		return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
	}
}
