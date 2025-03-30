package me.dio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity(name = "tb_news")
@Table(
        indexes = {
                @Index(name = "idx_news_publish_date", columnList = "publish_date"),
                @Index(name = "idx_news_active", columnList = "active")
        }
)
public class News extends BaseItem {

    @NotBlank(message = "Content cannot be blank")
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotNull(message = "Publish date cannot be null")
    @Column(name = "publish_date", nullable = false)
    private LocalDateTime publishDate;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "source", length = 100)
    private String source;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 30, nullable = false)
    private NewsCategory category = NewsCategory.GENERAL;

    @Column(name = "featured", nullable = false)
    private boolean featured = false;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
        this.category = category;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public enum NewsCategory {
        GENERAL, FINANCIAL, TECHNOLOGY, PROMOTION, SECURITY, INVESTMENT
    }

    public String getSummary(int maxLength) {
        if (content == null) return "";
        return content.length() > maxLength
                ? content.substring(0, maxLength) + "..."
                : content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + getId() +
                ", title='" + getDescription() + '\'' +
                ", publishDate=" + publishDate +
                ", category=" + category +
                ", featured=" + featured +
                ", active=" + isActive() +
                '}';
    }
}