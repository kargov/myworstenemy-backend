package com.ned.admitone.config.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.util.TimeZone;

public class BackendObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public BackendObjectMapper() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        setVisibility(new VisibilityChecker.Std(JsonAutoDetect.Visibility.NONE));
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setTimeZone(TimeZone.getDefault());
        registerModule(new BackendJsonModule());
        registerModule(new JodaModule());
    }
}

