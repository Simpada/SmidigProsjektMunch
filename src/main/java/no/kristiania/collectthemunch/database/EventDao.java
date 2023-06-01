package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import no.kristiania.collectthemunch.entities.Category;
import no.kristiania.collectthemunch.entities.Event;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends AbstractDao{

    @Inject
    public EventDao(DataSource dataSource) {
        super(dataSource);
    }

    public Event getEventById(int eventId) throws SQLException {
        Event event = new Event();
        event = getEventByIdFromDatabase(eventId);
        event.setCategories(getCategoriesByEventId(event.getId()));
        return event;
    }

    public void save(Event event) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Events (description) VALUES (?)";
            try(var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, event.getDescription());
                statement.executeUpdate();
                try(var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    event.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    private Event getEventByIdFromDatabase(int eventId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Events WHERE event_id = ?";
            try(var statement = connection.prepareStatement(query)) {
                try(var resultSet = statement.executeQuery()) {

                }
            }
        }
    }

    private List<Category> getCategoriesByEventId(int eventId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Categories WHERE event_id = ?";
            try(var statement = connection.prepareStatement(query)) {

            }
        }
    }

    public List<Event> getAllEventsFromDatabase() throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Categories";
            try(var statement = connection.prepareStatement(query)) {
                try(var resultSet = statement.executeQuery()) {

                    List<Event> eventList = new ArrayList<>();
                    Event currentEvent = null;

                    while (resultSet.next()) {
                        int eventId = resultSet.getInt("event_id");
                        String category = resultSet.getString("category");

                        if (currentEvent == null || currentEvent.getId() != eventId) {
                            // Create a new event object when encountering a new event ID
                            currentEvent = new Event();
                            currentEvent.setId(eventId);
                            currentEvent.setCategories(new ArrayList<>());
                            eventList.add(currentEvent);
                        }

                        // Add the category to the current event's list of categories
                        currentEvent.getCategories().add(Category.valueOf(category));
                    }

                    return eventList;
                }
            }
        }
    }

    public List<Event> getEventsByPreferences(List<String> preferences, List<Event> events) throws SQLException {

        List<Event> filteredEvents = filterEventsByPreferences(preferences, events);
        // Create a list of eventIds that we want to display
        List<Integer> eventIds = new ArrayList<>();
        for (Event event : filteredEvents) {
            eventIds.add(event.getId());
        }

        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Events WHERE event_id IN (?)";
            try(var statement = connection.prepareStatement(query)) {
                // Set the list of event IDs as a parameter to the query
                statement.setArray(1, connection.createArrayOf("INT", eventIds.toArray()));
                try(var resultSet = statement.executeQuery()) {
                    // The final list of events that will be returned to the frontend
                    List<Event> resultEvents = new ArrayList<>();

                    while (resultSet.next()) {
                        // Populate a new event object with the event description
                        // and add its corresponding categories from the filtered events
                        Event event = new Event();
                        int id = resultSet.getInt("event_id");
                        String description = resultSet.getString("description");

                        //TODO: Do the event objects returned to the frontend need the categories?
                        for (Event filteredEvent : filteredEvents) {
                            if (filteredEvent.getId() == id) {
                                event.setCategories(filteredEvent.getCategories());
                            }
                        }

                        resultEvents.add(event);
                    }
                    return resultEvents;
                }
            }
        }
    }

    private List<Event> filterEventsByPreferences(List<String> preferences, List<Event> events) {

        List<Event> filteredEvents = new ArrayList<>();

        for (Event event : events) {
            // A temporary list of the events categories
            List<Category> eventCategories = event.getCategories();

            // Convert the preferences retrieved from the user that are in
            // strings to valid enums.
            // TODO: Strings toUpperCase?
            List<Category> preferenceCategories = preferences.stream()
                    .map(Category::valueOf).toList();

            // Check if eventCategories contains any of the user preferences
            boolean hasMatchingPreference = eventCategories.stream()
                    .anyMatch(preferenceCategories::contains);
            // If any of the categories in the event matches any
            // of the categories the user has as a preference:
            // Add that event
            if (hasMatchingPreference) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }
}
