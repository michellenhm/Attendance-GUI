package application;

public class TimeConverter {
    
    public static int getSecond(String timestamp) {
        String[] parts = timestamp.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);
        return hour * 3600 + minute * 60 + second;
    }
}
