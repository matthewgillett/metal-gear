package org.finra.metal.gear.Sentiment;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentData {
    private Connection connection;

    public SentimentData(String hostName, int port, String dbName) throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":" + port + "/" + dbName);
    }

    public void initDatabase() throws IOException, SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(Resources.toString(Resources.getResource("sentiment.sql"), Charsets.UTF_8));
    }

    public Map<Long, SentimentText> getSentimentMap(long firmId) throws SQLException {
        Statement stmt = connection.createStatement();

        Map<Long, SentimentText> sentimentMap = new HashMap<>();

        try (ResultSet rs = stmt.executeQuery("SELECT * FROM texts " +
                "WHERE firm_id = '" + firmId + "'")) {
            while (rs.next()) {
                SentimentText data = new SentimentText(rs.getString(3), rs.getString(4), rs.getDouble(5));
                sentimentMap.put(rs.getLong(1), data);
            }
        }

        return sentimentMap;
    }

    public boolean insertTextData(long firmId, long textId, SentimentText textData) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO texts VALUES (?, ?, ?, ?, ?)")) {

            stmt.setLong(1, textId);
            stmt.setLong(2, firmId);
            stmt.setString(3, textData.getUserId());
            stmt.setString(4, textData.getText());
            stmt.setDouble(5, textData.getSentimentDecimal());

            return stmt.execute();
        }
    }
}
