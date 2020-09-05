package com.spring.kafka.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;


public class JsonUtil {
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private JsonUtil() {
		super();
	}

	public static String toJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.registerModule(new JavaTimeModule());

		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException ex) {
			logger.info("Erro de conversão de objeto", ex);
		}
		return null;
	}

	public static <T> T toObject(String json, Class<T> c) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.registerModule(new JavaTimeModule());
		if (null != json) {
			try {
				return mapper.readValue(json, c);
			} catch (JsonProcessingException ex) {
				logger.info("Erro de conversão de objeto", ex);
			}
		}
		return null;
	}
}
