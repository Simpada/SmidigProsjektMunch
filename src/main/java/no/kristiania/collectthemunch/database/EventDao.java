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

    private void save(Event event) throws SQLException, ItemNotSavedException {
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

    public Event retrieveEventById(int eventId) throws SQLException {
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

    private List<Integer> retrieveUserSpecificEventIds(List<String> preferences) throws SQLException {
        try(var connection = dataSource.getConnection()) {
            String query = "SELECT DISTINCT event_id FROM Categories WHERE category IN (?)";
            try(var statement = connection.prepareStatement(query)) {
                // Convert the categories list to a SQL array
                statement.setArray(1, connection.createArrayOf("VARCHAR", preferences.toArray()));
                try (var resultSet = statement.executeQuery()) {
                    List<Integer> result = new ArrayList<>();
                    while (resultSet.next()) {
                        int eventId = resultSet.getInt("event_id");
                        result.add(eventId);
                    }
                    if (result.isEmpty()) {
                        throw new NotFoundException("No events found that match the users preferences");
                    }
                    return result;
                }
            }
        }
    }

    private List<Event> retrieveEventsByUserPreferences(List<Integer> eventIds) throws SQLException {
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

    public List<Event> retrieveFilteredEventsByUserPreference(List<String> preferences) throws SQLException {
        //TODO: retrieve all events if preferences is empty?
        List<Integer> eventIds = retrieveUserSpecificEventIds(preferences);
        return retrieveEventsByUserPreferences(eventIds);
    }

    public void saveEvent(Event event) throws SQLException, ItemNotSavedException {
        save(event);
        saveEventCategories(event);
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
