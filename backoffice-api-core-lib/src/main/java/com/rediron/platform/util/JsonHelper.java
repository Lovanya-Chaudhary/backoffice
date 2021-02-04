package com.rediron.platform.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.MapType;

public class JsonHelper {

	private static ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		ObjectMapper om = getObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (json == null)
			return null;
		try {
			return om.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T fromJson(InputStream stream, Class<T> clazz) {
		ObjectMapper om = getObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		if (stream == null)
			return null;

		try (InputStream is = stream) {
			return om.readValue(is, clazz);
		} catch (IOException e) {
		}
		return null;
	}

	public static Map<String, Object> fromJsonToMap(InputStream stream) {
		return fromJsonToMap(stream, Object.class);
	}

	public static <T> Map<String, T> fromJsonToMap(InputStream stream, Class<T> clazz) {
		ObjectMapper om = getObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MapType mapType = om.getTypeFactory().constructMapType(HashMap.class, String.class, clazz);
		Map<String, T> map = Collections.emptyMap();

		try (InputStream is = stream) {
			if (is != null) {
				map = om.readValue(is, mapType);
			}
		} catch (IOException e) {
		}

		return map;
	}

	public static <T> String toJson(T object) {
		return toJson(object, false);
	}

	public static <T> String toJson(T object, boolean formatted) {
		ObjectMapper om = getObjectMapper();
		om.disable(MapperFeature.AUTO_DETECT_FIELDS, MapperFeature.AUTO_DETECT_GETTERS,
				MapperFeature.AUTO_DETECT_IS_GETTERS);

		if (formatted)
			return toJson(om.writerWithDefaultPrettyPrinter(), object);
		else
			return toJson(om.writer(), object);
	}

	private static <T> String toJson(ObjectWriter ow, T object) {
		try {
			return ow.writeValueAsString(object);
		} catch (JsonProcessingException e) {
		}
		return null;
	}

}
