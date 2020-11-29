package supermetrics.client;

public class MonthStat {

    private Double averageMessageLength;
    private String longestPostId;

    public Double getAverageMessageLength() {
        return averageMessageLength;
    }

    public void setAverageMessageLength(Double averageMessageLength) {
        this.averageMessageLength = averageMessageLength;
    }

    public String getLongestPostId() {
        return longestPostId;
    }

    public void setLongestPostId(String longestPostId) {
        this.longestPostId = longestPostId;
    }

    @Override
    public String toString() {
        return "MonthStat{" +
                "averageMessageLength=" + averageMessageLength +
                ", longestPostId='" + longestPostId + '\'' +
                '}';
    }
}
