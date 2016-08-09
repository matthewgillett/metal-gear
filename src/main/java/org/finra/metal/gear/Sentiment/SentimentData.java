package org.finra.metal.gear.Sentiment;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentData {
    private Connection connection;

    public SentimentData(String hostName, int port, String dbName, String userName, String password) throws SQLException {

        Properties properties = new Properties();

        if (userName != null)
            properties.setProperty("user", userName);
        if (password != null)
            properties.setProperty("password", password);
        properties.setProperty("ssl", "false");
        connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":" + port + "/" + dbName,
                properties);
    }

    public void initDatabase() throws IOException, SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(Resources.toString(Resources.getResource("sentiment.sql"), Charsets.UTF_8));
    }

    public List<SentimentText> getSentimentList(long firmId) throws SQLException {
        List<SentimentText> sentimentMap = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM texts " +
                    "WHERE firm_id = '" + firmId + "'")) {
                while (rs.next()) {
                    SentimentText data = new SentimentText(rs.getString(3), rs.getString(4), rs.getDouble(5));
                    sentimentMap.add(data);
                }
            }
        }

        return sentimentMap;
    }

    public boolean insertTextData(long firmId, SentimentText textData) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO texts VALUES (nextval('text_id_num'), ?, ?, ?, ?)")) {

            stmt.setLong(1, firmId);
            stmt.setString(2, textData.getUserId());
            stmt.setString(3, textData.getText());
            stmt.setDouble(4, textData.getSentimentDecimal());

            return stmt.execute();
        }
    }

    public int updateAverageSentiment(long firmId, int sentiment) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            return stmt.executeUpdate("insert into sentiments values ('" + firmId + "', " +
                    sentiment + ", now())");
        }
    }

    public Timestamp getLastUpdate(long firmId) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("select update_ts from sentiments " +
                    "where firm_id = '" + firmId + "' " +
                    "order by update_ts desc limit 1")) {
                if (rs.next()) {
                    return rs.getTimestamp(1);
                }
            }
        }
        return null;
    }

    public long getFirmId(String firmName) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT firm_id FROM firms " +
                    "WHERE firm_name = '" + firmName + "'")) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return 0;
    }

    public String getFirmName(long firmId) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT firm_id FROM firms " +
                    "WHERE firm_id = '" + firmId + "'")) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return null;
    }

    public int[] loadFirms(String resource, String delimiter) throws IOException, SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("delete from firms");
        }

        String data = Resources.toString(Resources.getResource(resource), Charsets.UTF_8);

        String[] lines = data.split("\n");

        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO firms VALUES (?, ?)")) {
            for (String line : lines) {
                String[] values = line.split(delimiter);

                stmt.setLong(1, Long.valueOf(values[0]));
                stmt.setString(2, values[6]);

                stmt.addBatch();
            }
            return stmt.executeBatch();
        }
    }
}
