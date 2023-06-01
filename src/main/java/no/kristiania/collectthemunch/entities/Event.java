package no.kristiania.collectthemunch.entities;

import java.util.List;

public class Event {

    private String name;
    private Integer id;
    private String description;
    private List<Category> categories;

    public Event(){
        this.id = 0;
        this.description = "";
        this.name = "";
        this.categories = null;
    }

    public Event(String description, List<Category> categories) {
        setDescription(description);
        setCategories(categories);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
