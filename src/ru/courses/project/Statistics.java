package ru.courses.project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    private Set<String> pages = new HashSet<>();
    private Map<String, Integer> osCount = new HashMap<>();

    public Statistics() {
        totalTraffic = 0;
        minTime = null;
        maxTime = null;
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

        String os = entry.getAgent().getOs();

        if (!osCount.containsKey(os)){
            osCount.put(os, 1);
        } else {
            osCount.put(os, osCount.get(os) + 1);
        }

    }

    public Set<String> getPages(){
        return pages;
    }

    public Map<String, Double> getOsStatistics(){
        Map<String, Double> result = new HashMap<>();

        int total = 0;

        for (int count : osCount.values()){
            total += count;
        }

        for(Map.Entry<String, Integer> entry : osCount.entrySet()){
            String os = entry.getKey();
            int count = entry.getValue();

            double share = (double) count / total;
            result.put(os, share);
        }
        return result;
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
