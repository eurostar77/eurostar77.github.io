package com.bueffeltier.data.jdbc;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeSerializerForGson
		implements JsonSerializer<LocalDateTime>
{
	@Override
	public JsonElement serialize(
			LocalDateTime src,
			Type typeOfSrc,
			JsonSerializationContext context
	)
	{
		return new JsonPrimitive(src.toString());
	}
}