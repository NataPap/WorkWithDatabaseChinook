package main.db.tables;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Artist {
    private int id;
    private String name;
}
