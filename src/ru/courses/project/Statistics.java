package ru.courses.project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

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
