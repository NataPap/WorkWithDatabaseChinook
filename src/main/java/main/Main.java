package main;

import lombok.SneakyThrows;
import main.dao.ArtistDAO;
import main.db.DBManager;
import main.db.tables.Artist;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private ArtistDAO artistDAO;
    public static void main(String[] args) {
        new Main().run();
    }

    @SneakyThrows
    private void run() {
        artistDAO = new ArtistDAO();
        int m;
        while ((m=menu())!=0) {
            switch (m) {
                case 1 -> showAll();
                case 2 -> showById();
                case 3 -> addArtist();
            }
        }
        DBManager.getInstance().getConnection().close();
    }

    private void addArtist() {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        artistDAO.addArtist(new Artist(0, name));
        System.out.println("Ok!");
    }

    private void showById() {
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        artistDAO.findById(id).ifPresent(System.out::println);
    }

    private int menu() {
        System.out.println("""
                1. Show all
                2. Show by id
                3. Add
                0. Exit
                """);
        return new Scanner(System.in).nextInt();
    }

    private void showAll() {
        List<Artist> artists = artistDAO.findAll();
        for (Artist artist : artists) {
            System.out.println(artist.getName());
        }
    }
}
