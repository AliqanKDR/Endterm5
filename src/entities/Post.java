package entities;

import java.time.LocalDate;

public class Post {
    private int id;
    private String title;
    private String description;
    private LocalDate addedDate;
    private Category category;

    public Post() {}

    public Post(int id, String title, String description, LocalDate addedDate, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.addedDate = addedDate;
        this.category = category;
    }

    public Post(String title, String description, LocalDate addedDate, Category category) {
        this.title = title;
        this.description = description;
        this.addedDate = addedDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", addedDate=" + addedDate +
                ", category=" + category +
                '}';
    }
}
