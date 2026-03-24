package ru.courses.project;

import ru.stepup.exceptioncast.CastomException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethod method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent agent;

    public LogEntry(String logLine) throws CastomException {
        if (logLine.length() > 1024) {
            throw new CastomException("Кол-во символов в строке не может быть больше 1024 символов");
        }

        String[] parts = logLine.split("\"");

        String[] ipAndTime = parts[0].split(" ");
        ipAddr = ipAndTime[0];
        time = parseDateTime(parts[0].substring(parts[0].indexOf("[")+1, parts[0].indexOf("]")));

        String[] methodAndPath = parts[1].split(" ");
        method = HttpMethod.valueOf(methodAndPath[0]);
        path = methodAndPath[1];

        String[] respCodeAndSize = parts[2].split(" ");
        responseCode = Integer.parseInt(respCodeAndSize[1]);
        responseSize = Integer.parseInt(respCodeAndSize[2]);

        referer= parts[3];

        agent = new UserAgent(parts[5]);

    }

    public LocalDateTime parseDateTime(String dateTimeStr){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return LocalDateTime.parse(dateTimeStr, dateFormatter);
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getAgent() {
        return agent;
    }
}

enum HttpMethod {
    GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS
}
