package com.journaldev.logger;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class VersionConverter extends ClassicConverter {
    public VersionConverter() {
    }

    @Override
    public String convert(ILoggingEvent event) {
        return event.getLoggerContextVO().getPropertyMap().get("build-version");
    }
}