package supermetrics.client.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request Meta class
 */
public class Meta {

    @JsonProperty("request_id")
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "requestId='" + requestId + '\'' +
                '}';
    }
}
