package ru.courses.project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    private final Set<String> pages = new HashSet<>();
    private final Map<String, Integer> osCount = new HashMap<>();

    private final Set<String> notFoundPages = new HashSet<>();
    private final Map<String, Integer> browserCount = new HashMap<>();

    public Statistics() {
    }

    public void addEntry(LogEntry entry){

        totalTraffic += entry.getResponseSize();

        if(minTime == null || entry.getTime().isBefore(minTime)){
            minTime = entry.getTime();
        }

        if (maxTime == null || entry.getTime().isAfter(maxTime)){
            maxTime = entry.getTime();
        }

        if(entry.getResponseCode() == 200) {
            pages.add(entry.getPath());
        }

        if(entry.getResponseCode() == 404){
            notFoundPages.add(entry.getPath());
        }

        String os = entry.getAgent().getOs();

        if (!osCount.containsKey(os)){
            osCount.put(os, 1);
        } else {
            osCount.put(os, osCount.get(os) + 1);
        }

        String browser = entry.getAgent().getBrowser();

        if(!browserCount.containsKey(browser)){
            browserCount.put(browser, 1);
        } else {
            browserCount.put(browser, browserCount.get(browser) + 1);
        }

    }

    public Set<String> getPages(){
        return pages;
    }

    public Set<String> getNotFoundPages(){
        return notFoundPages;
    }

    public Map<String, Double> getBrowserStatistics(){
        Map<String, Double> resultBrowser = new HashMap<>();

        int total = 0;

        for (int count : browserCount.values()){
            total += count;
        }

        for (Map.Entry<String, Integer> entry : browserCount.entrySet()){
            String browser = entry.getKey();
            int count = entry.getValue();

            double share = (double) count / total;
            resultBrowser.put(browser, share);
        }
        return resultBrowser;
    }

    public Map<String, Double> getOsStatistics(){
        Map<String, Double> resultOs = new HashMap<>();

        int total = 0;

        for (int count : osCount.values()){
            total += count;
        }

        for(Map.Entry<String, Integer> entry : osCount.entrySet()){
            String os = entry.getKey();
            int count = entry.getValue();

            double share = (double) count / total;
            resultOs.put(os, share);
        }
        return resultOs;
    }

    public double getTrafficRate(){
        if(minTime == null || maxTime == null || maxTime == minTime){
            return 0.0;
        }

        long hours = Math.abs(ChronoUnit.HOURS.between(maxTime, minTime));

        if(hours == 0){
            return 0.0;
        }

        return (double) totalTraffic/hours;
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }
    public LocalDateTime getMaxTime() {
        return maxTime;
    }
}
