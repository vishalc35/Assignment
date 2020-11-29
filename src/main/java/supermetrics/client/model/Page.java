package supermetrics.client.model;

import java.util.List;

/**
 * Page class to fetch posts.
 */
public class Page {

    private Integer page;
    private List<Post> posts;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
