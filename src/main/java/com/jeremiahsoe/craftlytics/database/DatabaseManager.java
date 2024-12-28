package com.jeremiahsoe.craftlytics.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private HikariDataSource dataSource;

    private DatabaseManager(){}

    public static synchronized DatabaseManager getInstance(){
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void init(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/craftlytics");
        config.setUsername("craftlytics_user");
        config.setPassword("6346");
        config.setDriverClassName(org.postgresql.Driver.class.getName());
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

//    public void logBiomeExploration(String playerUuid, String biomeName, String location){
//        String sql = "INSERT INTO Biomes_Explored (player_uuid, biome_name, location) VALUES (?,?,?)";
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql)) {
//
//            statement.setString(1, playerUuid);
//            statement.setString(2, biomeName);
//            statement.setString(3, location);
//            statement.executeUpdate();
//        }
//        catch (SQLException e){
//            e.printStackTrace(); //edit later
//        }
//
//    }

    public void close(){
        if(dataSource != null){
            dataSource.close();
        }
    }
}
