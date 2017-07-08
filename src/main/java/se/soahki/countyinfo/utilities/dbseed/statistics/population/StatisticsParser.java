package se.soahki.countyinfo.utilities.dbseed.statistics.population;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to insert total population per year and municipality, parsed from a text file.
 * Will only be run once to seed database with this data. Any further update should not
 * be run from here.
 *
 * Source for data is SCB: http://www.scb.se/
 */
public class StatisticsParser {
    private static Connection connection;

    public StatisticsParser() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager
                .getConnection("jdbc:h2:tcp://localhost/~/databases/countyinfo", "sa", "");
    }

    public void run() {
        List<String[]> lineComponents = readFromFile();
        try {
            List<BasicPop> pops = initializePops(lineComponents);
            for (BasicPop pop : pops) {
                System.out.println(pop);
                insertPopulation(pop);
            }
            System.out.println("Successfully inserted " + pops.size() + " instances.");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<BasicPop> initializePops(List<String[]> components) throws SQLException, ClassNotFoundException {
        List<BasicPop> pops = new ArrayList<>();
        for (int i = 0; i < components.size() - 1; i++) {
            String name = components.get(i+1)[0];
            Integer numInhabitants;
            Integer year;
            Long municipalityId = getMunicipalitiesId(name);
            for (int j = 0; j < components.get(i).length - 1; j++) {
                year = Integer.parseInt(components.get(0)[j+1]);
                numInhabitants = Integer.parseInt(components.get(i+1)[j+1]);
                BasicPop pop = new BasicPop(numInhabitants, year, municipalityId);
                pops.add(pop);
            }
        }

        return pops;
    }

    private List<String[]> readFromFile() {
        String path = "C:\\Users\\Anders\\Downloads\\be0101-kom-1950-2016_text.txt";
        List<String[]> lines = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                String[] lineComponents = line.split("\t");
                //testSplit(lineComponents);
                lines.add(lineComponents);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private void testSplit(String[] lineComponents) {
        for (String string : lineComponents) {
            System.out.print(string + "\t");
        }
        System.out.println();
    }

    private void insertPopulation(BasicPop pop) throws SQLException {
        String sql = "INSERT INTO population (inhabitants, year, municipality_id) VALUES ("
                + pop.getNumInhabitants() + ", " + pop.getYear() + ", " + pop.getMunicipalityId() + ")";

        Statement statement = connection.createStatement();
        System.out.println("Executing: " + sql);
        statement.executeUpdate(sql);

    }

    private Long getMunicipalitiesId(String name) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:h2:tcp://localhost/~/databases/countyinfo", "sa", "");
        Statement statement = connection.createStatement();
        String sql = "SELECT id FROM municipality WHERE name = '" + name + "'";
        System.out.println("Executing: " + sql);
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            return rs.getLong("id");
        } else {
            return null;
        }
    }

    class BasicPop {
        Long id;
        Integer numInhabitants;
        Integer year;
        Long municipalityId;

        public BasicPop(Integer numInhabitants, Integer year, Long municipalityId) {
            this.numInhabitants = numInhabitants;
            this.year = year;
            this.municipalityId = municipalityId;
        }

        public void setNumInhabitants(Integer numInhabitants) {
            this.numInhabitants = numInhabitants;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public void setMunicipalityId(Long municipalityId) {
            this.municipalityId = municipalityId;
        }

        public Integer getNumInhabitants() {
            return numInhabitants;
        }

        public Integer getYear() {
            return year;
        }

        public Long getMunicipalityId() {
            return municipalityId;
        }

        @Override
        public String toString() {
            return String.format("Mun.Id \t Year \t Population \n%d\t%d\t%d", municipalityId, year, numInhabitants );
        }
    }

}
