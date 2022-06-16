package main.dao;

import lombok.SneakyThrows;
import main.db.DBManager;
import main.db.tables.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDAO {

    private final Connection connection;

    public ArtistDAO() {
        connection = DBManager.getInstance().getConnection();
    }

    @SneakyThrows
    public List<Artist> findAll() {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from artist");
        List<Artist> artists = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ArtistId");
            String name = resultSet.getString("Name");
            artists.add(new Artist(id, name));
        }
        return artists;
    }

    @SneakyThrows
    public Optional<Artist> findById(int id) {
        PreparedStatement ps = connection.prepareStatement("select * from artist where ArtistId = ?");
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            int id1 = resultSet.getInt("ArtistId");
            String name = resultSet.getString("Name");
            return Optional.of(new Artist(id1, name));
        } else {
            return Optional.empty();
        }
    }

    @SneakyThrows
    public void addArtist(Artist artist) {
        PreparedStatement ps = connection.prepareStatement("insert into artist (Name) values (?)");
        ps.setString(1, artist.getName());
        ps.executeUpdate();
    }
}
