/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    public LogAnalyzer(String logName) {
        hourCounts = new int[24];
        reader = new LogfileReader(logName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    /**
     * Print the number of Accesses of a log file
     */
    public void numberOfAccesses() {
        int hour = 0;
        int totalAccesses = 0;
        while(hour < hourCounts.length) {
            totalAccesses += hourCounts[hour];
            hour++;
        }
        if (totalAccesses != 0) {
            System.out.println("Number of accesses: " + totalAccesses);
        }
        else {
            System.out.println("Error");
        }
    }

    /**
     * returns the hour with more number of accesses
     */
    public void busiestHour() {
        int hour = 0;
        int busiestHour = 0;
        String theBusiestHour = "";
        while(hour < hourCounts.length) {
            if (hourCounts[hour] > busiestHour) {
                busiestHour = hourCounts[hour];
                theBusiestHour = hour + " : " + busiestHour;
            }
            hour++;
        }
        System.out.println(theBusiestHour);
    }

    /**
     * returns the hour with less number of accesses
     */
    public void quietestHour() {
        int hour = 0;
        int quietestHour = hourCounts[hour];
        String theQuietestHour = "";
        while(hour < hourCounts.length) {
            if (hourCounts[hour] < quietestHour) {
                quietestHour = hourCounts[hour];
                theQuietestHour = hour + " : " + quietestHour;
            }
            hour++;
        }
        System.out.println(theQuietestHour);
    }

    /**
     * 
     */
    public void busiestTwoHours() {
        int hour = 0;
        int hour2 = 1;
        int busiestHours = 0;
        int twoHoursAccesses = (hourCounts[hour]) + (hourCounts[hour2]);
        String theBusiestHours = ""; 
        while(hour2 < hourCounts.length) {
            twoHoursAccesses = (hourCounts[hour]) + (hourCounts[hour2]);
            if (twoHoursAccesses > busiestHours) {
                busiestHours = twoHoursAccesses;
                theBusiestHours = hour + "/" + hour2;
            }
            hour++;
            hour2++;
        }
        System.out.println(theBusiestHours);
    }

    
}
