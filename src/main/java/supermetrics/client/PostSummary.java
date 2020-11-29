package supermetrics.client;

import java.util.Map;

/**
 * Class to summarise metrics and display as String.
 */
public class PostSummary {

    private Map<String, MonthStat> monthStat;
    private Map<Integer, Long> countOfPostsByWeek;
    private Map<String, Long> usersAvgPostsByMonth;

    public Map<String, MonthStat> getMonthStat() {
        return monthStat;
    }

    public void setMonthStat(Map<String, MonthStat> monthStat) {
        this.monthStat = monthStat;
    }

    public Map<Integer, Long> getCountOfPostsByWeek() {
        return countOfPostsByWeek;
    }

    public void setCountOfPostsByWeek(Map<Integer, Long> countOfPostsByWeek) {
        this.countOfPostsByWeek = countOfPostsByWeek;
    }

    public Map<String, Long> getUsersAvgPostsByMonth() {
        return usersAvgPostsByMonth;
    }

    public void setUsersAvgPostsByMonth(Map<String, Long> usersAvgPostsByMonth) {
        this.usersAvgPostsByMonth = usersAvgPostsByMonth;
    }

    @Override
    public String toString() {
        return "PostSummary{" +
                "monthStat=" + monthStat +
                ", countOfPostsByWeek=" + countOfPostsByWeek +
                ", usersAvgPostsByMonth=" + usersAvgPostsByMonth +
                '}';
    }
}
