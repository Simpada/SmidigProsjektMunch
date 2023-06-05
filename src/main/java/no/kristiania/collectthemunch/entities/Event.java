package no.kristiania.collectthemunch.entities;

import java.util.List;

public class Event {

    private int id;
    private String name;
    private String description;
    private List<String> categories;
    private byte[] eventPoster;

    public Event(){
        this.id = 0;
        this.description = "";
        this.name = "";
        this.categories = null;
        eventPoster = new byte[0];
    }

    public Event(String description, List<String> categories) {
        setDescription(description);
        setCategories(categories);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
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

    public byte[] getEventPoster() {
        return eventPoster;
    }

    public void setEventPoster(byte[] eventPoster) {
        this.eventPoster = eventPoster;
    }
}
