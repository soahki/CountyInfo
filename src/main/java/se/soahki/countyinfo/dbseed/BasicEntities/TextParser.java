package se.soahki.countyinfo.dbseed.BasicEntities;

import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TextParser {
    private List<County> counties = new ArrayList<>();
    private List<Municipality> municipalities = new ArrayList<>();

    public TextParser() {
        counties = new ParseCounties().getCounties("C:\\Users\\Anders\\Documents\\Programmering\\RegionalProject\\src\\main\\resources\\localities\\regions.txt");
        municipalities = new ParseMunicipalities().getMunicipalities("C:\\Users\\Anders\\Documents\\Programmering\\RegionalProject\\src\\main\\resources\\localities\\municipalities.txt");
    }


    public List<County> getCounties() {
        return counties;
    }

    public List<Municipality> getMunicipalities() {
        return municipalities;
    }

    class ParseCounties {
        public List<County> getCounties(String path) {
            List<County> regions = new ArrayList<>();
            try(
                    FileInputStream fis = new FileInputStream(path);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String name;
                    String countyCode;

                    if (line.equals(""))
                        continue;

                    String[] parseLine = line.split(", ");
                    if (parseLine.length != 2)
                        continue;

                    name = parseLine[0];
                    countyCode = parseLine[1];
                    regions.add(new County(name, countyCode));
                }

            } catch(IOException ioe) {
                ioe.printStackTrace();
            }

            return regions;
        }
    }


    /**
     * Read txt-file to parse Municipality-objects from it. The txt is set up with name, region code and municipality code,
     * separted by comma. The class contains the static method getMunicipalities which returns a List of Municipality-objects,
     * from the passed path (String).
     */
    class ParseMunicipalities {

        /**
         * Parses Municipality-objects from the passed String path to a text-file. The text-file should be set up accordingly:
         * [String name], [String regionCode], [String municipalityCode]
         * ...
         * Returns a List of Municipality-objects.
         * @param path
         * @return
         */
        public List<Municipality> getMunicipalities(String path) {
            List<Municipality> municipalities = new ArrayList<>();

            try(
                    FileInputStream fis = new FileInputStream(path);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String name;
                    String regionCode;
                    String municipalityCode;
                    if (line.equals(""))
                        continue;

                    String[] parseLine = line.split(",");
                    if (parseLine.length != 3)
                        continue;

                    name = parseLine[0].trim();
                    regionCode = parseLine[1].trim();
                    municipalityCode = parseLine[2].trim();

                    County countyKey = null;
                    for (County county : getCounties()) {
                        if (county.getCode().equals(regionCode)) {
                            countyKey = county;
                            break;
                        }
                    }

                    municipalities.add(new Municipality(name, municipalityCode, countyKey));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return municipalities;
        }
    }

}