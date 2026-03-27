package ru.courses.project;

public class UserAgent {
    private final String os;
    private final String browser;

    public UserAgent(String userAgentStr) {
        if (userAgentStr.contains("Windows")) {
            os = "Windows";
        } else if (userAgentStr.contains("macOS")) {
            os = "macOS";
        } else if (userAgentStr.contains("Linux")) {
            os = "Linux";
        } else {
            os = "Other";
        }

        if (userAgentStr.contains("Edge")) {
            browser = "Edge";
        } else if (userAgentStr.contains("Firefox")) {
            browser = "Firefox";
        } else if (userAgentStr.contains("Opera")) {
            browser = "Opera";
        } else if (userAgentStr.contains("Chrome")) {
            browser = "Chrome";
        } else {
            browser = "Other";
        }
    }

    public String getOs() {
        return os;
    }

    public String getBrowser() {
        return browser;
    }

    public boolean isBot(){
        return browser.toLowerCase().contains("bot");
    }
}
