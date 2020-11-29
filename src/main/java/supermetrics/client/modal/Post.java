package supermetrics.client.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Post {

    private String id;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("from_id")
    private String fromId;
    private String message;
    private String type;
    @JsonProperty("created_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssxxx")
    private LocalDateTime createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMonth() {
        return createdTime.getMonth().getValue() + "/" + createdTime.getYear();
    }

    public Integer getWeek() {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return createdTime.get(weekFields.weekOfWeekBasedYear());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", fromName='" + fromName + '\'' +
                ", fromId='" + fromId + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
