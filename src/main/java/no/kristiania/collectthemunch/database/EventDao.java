package no.kristiania.collectthemunch.database;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import no.kristiania.collectthemunch.entities.Event;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends AbstractDao{

    @Inject
    public EventDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Event event) throws SQLException, ItemNotSavedException {
        try(var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Events (name, description, poster) VALUES (?, ?, ?)";
            try(var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, event.getName());
                statement.setString(2, event.getDescription());
                statement.setBytes(3, event.getEventPoster());
                statement.executeUpdate();
                try(var generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        event.setId(generatedKeys.getInt(1));
                    } else {
                        throw new ItemNotSavedException("Event " + event.getName() + " was not saved.");
                    }
                }
            }
        }
    }

    public List<Event> retrieveEventsByName(String userSearch) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Events WHERE LOWER(name) LIKE ?";
            try (var statement = connection.prepareStatement(query)) {
                // Search string to lower case and use wildcard on both sides to match
                // a string in the middle of a event name
                String searchPattern = "%" + userSearch.toLowerCase() + "%";
                statement.setString(1, searchPattern);
                try (var resultSet = statement.executeQuery()) {
                    List<Event> result = new ArrayList<>();
                    if (resultSet.next()) {
                        do {
                            result.add(mapFromResultSet(resultSet));
                        } while (resultSet.next());
                    } else {
                        throw new NotFoundException("No events found by search string " + userSearch);
                    }
                    return result;
                }
            }
        }
    }

    public List<Event> retrieveAllEvents() throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Events";
            try(var statement = connection.prepareStatement(query)) {
                try(var resultSet = statement.executeQuery()) {
                    List<Event> result = new ArrayList<>();
                    while (resultSet.next()) {
                        result.add(mapFromResultSet(resultSet));
                    }
                    if(result.isEmpty()) {
                        throw new NotFoundException("No events found in database.");
                    }
                    return result;
                }
            }
        }
    }

    private Event retrieveEventById(int eventId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Events WHERE event_id = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, eventId);
                try(var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapFromResultSet(resultSet);
                    } else {
                        throw new NotFoundException("Event not found with ID: " + eventId);
                    }
                }
            }
        }
    }
    //TODO: Should there be events without categories?
    private List<String> retrieveCategoriesByEventId(int eventId) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Categories WHERE event_id = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setInt(1, eventId);
                try(var resultSet = statement.executeQuery()) {
                    List<String> result = new ArrayList<>();
                    while (resultSet.next()) {
                        result.add(resultSet.getString("category"));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No categories found for event with id: " + eventId);
                    }
                    return result;
                }
            }
        }
    }

    private void saveEventCategories(Event event) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "INSERT INTO Categories (event_id, category) VALUES (?,?)";
            try(var statement = connection.prepareStatement(query)) {
                for (String category : event.getCategories()) {
                    statement.setInt(1, event.getId());
                    statement.setString(2, category);
                    statement.executeUpdate();
                }
            }
        }
    }

    public List<Event> retrieveEventsByCategory(String category) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT *" +
                            "FROM Events E " +
                            "JOIN Categories C ON E.event_id = C.event_id " +
                            "WHERE C.category = ?";
            try(var statement = connection.prepareStatement(query)) {
                statement.setString(1, category);
                try(var resultSet = statement.executeQuery()) {
                    List<Event> allEvents = new ArrayList<>();
                    while (resultSet.next()) {
                        allEvents.add(mapFromResultSet(resultSet));
                    }
                    if (allEvents.isEmpty()) {
                        throw new NotFoundException("No events with category " + category + " found.");
                    }
                    return allEvents;
                }
            }
        }
    }
    /*
    public List<Event> getFilteredEventsByUserPreference(List<String> preferences) {
        // Retrieve all events from the database, will at most be an empty list for now
        List<Event> eventCategories = new ArrayList<>();
        try {
            eventCategories = retrieveAllValidCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Event> filteredEvents = new ArrayList<>();
        if (eventCategories != null){
            // Filter the events based on the provided preferences
            try {
                filteredEvents = retrieveEventsByPreferences(preferences, eventCategories);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return filteredEvents;
    }

    public List<Event> retrieveAllValidCategories() throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM CATEGORIES";
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
                        currentEvent.getCategories().add(category);
                    }
                    return eventList;
                }
            }
        }
    }

    public List<Event> retrieveEventsByPreferences(List<String> preferences, List<Event> events) throws SQLException {

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
            List<String> eventCategories = event.getCategories();

            // Check if eventCategories contains any of the user preferences
            boolean hasMatchingPreference = eventCategories.stream()
                    .anyMatch(preferences::contains);
            // If any of the categories in the event matches any
            // of the categories the user has as a preference:
            // Add that event
            if (hasMatchingPreference) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }
    */
    public List<Event> testFilteredEvents(List<String> preferences) throws SQLException {
        List<Integer> eventIds = getEventsThatMatchPreferences(preferences);
        List<Event> eventsByFilteredIds = getEventsByFilteredIds(eventIds);
        return eventsByFilteredIds;
    }

    private List<Integer> getEventsThatMatchPreferences(List<String> preferences) throws SQLException {
        List<Integer> eventIds = new ArrayList<>();

        try(var connection = dataSource.getConnection()) {
            String query = "SELECT DISTINCT event_id FROM Categories WHERE category IN (?)";
            try(var statement = connection.prepareStatement(query)) {
                // Convert the categories list to a SQL array
                statement.setArray(1, connection.createArrayOf("VARCHAR", preferences.toArray()));
                try (var resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int eventId = resultSet.getInt("event_id");
                        eventIds.add(eventId);
                    }
                }
            }
        }

        return eventIds;
    }

    private List<Event> getEventsByFilteredIds(List<Integer> eventIds) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Events WHERE event_id IN (?)";
            try(var statement = connection.prepareStatement(query)) {
                // Set the list of event IDs as a parameter to the query
                statement.setArray(1, connection.createArrayOf("INT", eventIds.toArray()));
                try(var resultSet = statement.executeQuery()) {
                    // The final list of events that will be returned to the frontend
                    List<Event> result = new ArrayList<>();
                    while(resultSet.next()) {
                        result.add(mapFromResultSet(resultSet));
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No events found with users preferences");
                    }
                    return result;
                }
            }
        }
    }

    public void saveEvent(Event event) throws SQLException, ItemNotSavedException {
        save(event);
        saveEventCategories(event);
    }

    public Event getEventById(int eventId) throws SQLException {
        Event event = retrieveEventById(eventId);
        event.setCategories(retrieveCategoriesByEventId(event.getId()));
        return event;
    }

    private Event mapFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getInt("event_id"));
        event.setName(resultSet.getString("name"));
        event.setDescription(resultSet.getString("description"));
        event.setEventPoster(resultSet.getBytes("poster"));
        event.setCategories(retrieveCategoriesByEventId(event.getId()));
        return event;
    }
}
