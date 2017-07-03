package se.soahki.countyinfo.dbseed;

import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;

import java.sql.*;
import java.util.List;

/**
 * One time runner class to seed an empty database with municipalities and counties.
 * Utilise the seed() method in main if seeding is to happen. Seeding is based on
 * text files, where the path as of this class creation is hardcoded. Same goes for
 * the database. Change necessary values in this class if it is to be run in any other
 * environment.
 */
public class Runner {

    public static void main(String[] args) throws Exception {

    }

    private static void seed() throws Exception{
        TextParser parser = new TextParser();
        List<County> counties = parser.getCounties();
        List<Municipality> municipalities = parser.getMunicipalities();

        Class.forName("org.h2.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:h2:tcp://localhost/~/databases/countyinfo", "sa", "");

        Statement statement = connection.createStatement();


        for (County county : counties) {
            insertCounty(statement, county);
        }

        for (Municipality municipality : municipalities) {
            insertMunicipality(statement, municipality);
        }

        connection.close();
    }

    private static void insertCounty(Statement statement, County county) throws SQLException {
        String sql = "INSERT INTO county (code, name) VALUES ('%s', '%s')";
        sql = String.format(sql, county.getCode(), county.getName());

        System.out.println("Executing: " + sql);

        statement.executeUpdate(sql);
    }

    private static void insertMunicipality(Statement statement, Municipality municipality) throws SQLException {
        String sql = "INSERT INTO municipality (code, name, county_id) VALUES ('%s', '%s', %d)";
        Long countyKey = getDBCountyKey(statement, municipality);
        sql = String.format(sql, municipality.getCode(), municipality.getName(), countyKey);
        System.out.println("Executing: " + sql);

        statement.executeUpdate(sql);
    }

    private static Long getDBCountyKey(Statement statement, Municipality municipality) throws SQLException {
        String countyCode = municipality.getCounty().getCode();
        System.out.println(countyCode);
        String sql = "SELECT id FROM county WHERE code LIKE '" + countyCode + "'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        return rs.getLong("id");
    }
}
