package me.dio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tb_feature")
@Table(
        indexes = {
                @Index(name = "idx_feature_active", columnList = "active"),
                @Index(name = "idx_feature_priority", columnList = "priority")
        }
)
public class Feature extends BaseItem {

    @NotNull(message = "Priority cannot be null")
    @Column(name = "priority", nullable = false)
    private Integer priority = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 30, nullable = false)
    private FeatureCategory category = FeatureCategory.GENERAL;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "is_premium", nullable = false)
    private boolean premium = false;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public FeatureCategory getCategory() {
        return category;
    }

    public void setCategory(FeatureCategory category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public enum FeatureCategory {
        GENERAL, PAYMENT, INVESTMENT, PROMOTION, NEWS, SUPPORT
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + getId() +
                ", description='" + getDescription() + '\'' +
                ", priority=" + priority +
                ", category=" + category +
                ", premium=" + premium +
                ", active=" + isActive() +
                '}';
    }
}