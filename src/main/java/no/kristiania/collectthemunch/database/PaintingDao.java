package no.kristiania.collectthemunch.database;

import no.kristiania.collectthemunch.entities.Painting;
import no.kristiania.collectthemunch.entities.Rarity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintingDao extends AbstractDao{

    public PaintingDao(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Painting painting) {

    }

    public List<Painting> retrieveAll() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings";

            return getPaintings(connection, query);
        }
    }

    public List<Painting> retrieveForUser(int userId) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Paintings_Collected WHERE user_id = ?";

            return getPaintings(connection, query);
        }
    }

    private List<Painting> getPaintings(Connection connection, String query) throws SQLException {
        try (var statement = connection.prepareStatement(query)) {
            try (var resultSet = statement.executeQuery()) {
                List<Painting> paintings = new ArrayList<>();

                while (resultSet.next()) {
                    var painting = mapFromResultSet(resultSet);
                    paintings.add(painting);
                }
                return paintings;
            }
        }
    }

    public Painting retrieve(int paintingId) {
        return null;
    }

    public Painting retrieve(String title) {
        return null;
    }

    private Painting mapFromResultSet(ResultSet resultSet) throws SQLException {
        var painting = new Painting();

        painting.setPaintingId(resultSet.getInt("painting_id"));
        painting.setTitle(resultSet.getString("title"));
        painting.setAuthor(resultSet.getString("author"));
        painting.setPainting_image(resultSet.getBytes("painting_image"));
        painting.setArt_information(resultSet.getString("art_information"));
        painting.setRarity(Rarity.parseToEnum(resultSet.getString("rarity")));
        painting.setPoints(resultSet.getInt("points"));

        return painting;
    }

}
