package safe.domain;

import java.time.LocalDateTime;

public class SafeNote {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // optional
    private String tags;
    private String type;
    private boolean isEncrypted;

    private Integer userId;

    public SafeNote() {
    }

    public SafeNote(String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String tags, String type, boolean isEncrypted, Integer userId) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
        this.type = type;
        this.isEncrypted = isEncrypted;
        this.userId = userId;
    }

    public SafeNote(String title, String content, String tags, String type, boolean isEncrypted) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.type = type;
        this.isEncrypted = isEncrypted;
    }

    public SafeNote(Integer id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String tags, String type, boolean isEncrypted, Integer userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
        this.type = type;
        this.isEncrypted = isEncrypted;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SafeNote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                ", isEncrypted=" + isEncrypted +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
