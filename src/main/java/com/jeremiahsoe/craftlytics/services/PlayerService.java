package com.jeremiahsoe.craftlytics.services;

import com.jeremiahsoe.craftlytics.database.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class PlayerService {
    private final DatabaseManager databaseManager;

    public PlayerService(){
        this.databaseManager = DatabaseManager.getInstance();
    }

    public void updateOrInsertPlayer(String playerUuid, String playerName){
        String checkSql = "SELECT id FROM Players WHERE player_uuid = ?";
        String updateSql = "UPDATE Players SET last_seen = CURRENT_TIMESTAMP, player_name = ? WHERE player_uuid = ?";
        String insertSql = "INSERT INTO Players (player_uuid, player_name) VALUES (?, ?)";

        try(Connection conn = databaseManager.getConnection()){
            try(PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, playerUuid);
                if(checkStmt.executeQuery().next()){ //if a player exists, should update last_seen
                    try(PreparedStatement updateStmt = conn.prepareStatement(updateSql)){
                        updateStmt.setString(1, playerName);
                        updateStmt.setString(2, playerUuid);
                        updateStmt.executeUpdate();
                        System.out.println("Existing player detected"); // debugging print
                    }
                }
                else{ //if player does not exist, should insert them into table
                    try(PreparedStatement insertStmt = conn.prepareStatement(insertSql)){
                        insertStmt.setString(1, playerUuid);
                        insertStmt.setString(2, playerName);
                        insertStmt.executeUpdate();
                        System.out.println("New player detected"); // debugging print
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace(); //can replace logging later
        }
    }
}
