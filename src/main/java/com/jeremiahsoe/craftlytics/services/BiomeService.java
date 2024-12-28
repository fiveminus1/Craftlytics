package com.jeremiahsoe.craftlytics.services;

import com.jeremiahsoe.craftlytics.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BiomeService {
    private final DatabaseManager databaseManager;

    public BiomeService(){
        this.databaseManager = DatabaseManager.getInstance();
    }

    public void logBiomeExploration(String playerUuid, String biomeName, String location) throws SQLException {
        String sql = "INSERT INTO Biomes_Explored (player_uuid, biome_name, location) VALUES (?,?,?)";
        try(Connection conn = databaseManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, playerUuid);
            statement.setString(2, biomeName);
            statement.setString(3, location);
            statement.executeUpdate();
            System.out.println("Biome update detected"); //debugging statement
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
