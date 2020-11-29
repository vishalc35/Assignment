package supermetrics.client.model;

/**
 * Response class to get data from API
 * @param <T>
 */
public class Response<T> {

    private Meta meta;
    private T data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
