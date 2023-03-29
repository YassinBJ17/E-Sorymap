package ldap.learn;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVParser {

	static ArrayList<Flux> fluxMfc = new ArrayList<>();

	public static ArrayList<Flux> getFluxMfc() {
		return fluxMfc;
	}

	public static void getFluxofCSVFile(File file) {



		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			Map<String, String> results = new HashMap<>();

			String[] line;
			int lineNumber = 0;
			String systemInformation = new String();
			System.out.println("doooooooone");
			while ((line = reader.readNext()) != null) {
				if (line.length >= 2 && line[1].equals("Rounded Rectangle Container")) {
					results.put(line[0], line[11]);
					//results.put("textArea1", line[11]);
					//results.add(map);
				}
				if (line[1].equals("Text")) {
					systemInformation = line[11];
					//results.put("textArea1", line[11]);
					//results.add(map);
				}

				lineNumber++;

				// Ignorer les premières lignes inutiles
				if (lineNumber > 5) {

					String shape = line[2];


					// Vérifier si la ligne représente un acteur externe




					String source = line[6];
					String destination = line[7];

					// Vérifier si la ligne représente un flux
					if (source != null && !source.isEmpty() && destination != null && !destination.isEmpty()) {

						String flux = line[11];
						String sender = getValueByKey(results , line[6]);
						String receiver = getValueByKey(results , line[7]);
						if(sender == null) {
							sender = systemInformation;
						}
						if(receiver == null) {
							receiver = systemInformation;
						}
	                            /*
	                            System.out.println("Flux : " + flux);.out.println("Recepteur : " + receiver);
	                            */
						fluxMfc.add(new Flux(sender , receiver , flux));

					}
				}
			}


			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		File csvFile = new File("src/main/java/ldap/learn/Blank diagram (5).csv");
		getFluxofCSVFile(csvFile);
	}

	public static String getValueByKey(Map<String, String> map, String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		} else {
			return null;
		}
	}
}












/*
public class CSVParser {

    public static void main(String[] args) throws CsvValidationException {

        String csvFile = "src/main/java/ldap/learn/Blank diagram (4).csv";
        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(csvFile));

            String[] line;
            int lineNumber = 0;

            // Parcourir les lignes du fichier CSV
            while ((line = reader.readNext()) != null) {

                lineNumber++;

                // Ignorer les premières lignes inutiles
                if (lineNumber > 5) {

                    String shape = line[2];
                     
                    
                    // Vérifier si la ligne représente un acteur externe


               

                        String source = line[6];
                        String destination = line[7];

                        // Vérifier si la ligne représente un flux
                        if (source != null && !source.isEmpty() && destination != null && !destination.isEmpty()) {

                            String flux = line[11];
                            String sender = line[6];
                            String receiver = line[7];
                            System.out.println("Flux : " + flux);
                            System.out.println("Emetteur : " + sender);
                            System.out.println("Recepteur : " + receiver);

                        }
                        

                    }

                }

            

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
*/


/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {

    public static void main(String[] args) {
        String csvFile = "src/main/java/ldap/learn/Blank diagram (2).csv";
        String line;
        String delimiter = ",";
        String searchString = ",,,,,,,,"; // La chaîne de 8 virgules que l'on cherche
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (line.contains(searchString)) {
                    int startIndex = line.indexOf(searchString) + searchString.length();
                    int endIndex = line.indexOf(delimiter, startIndex);
                    if (endIndex != -1) {
                        String resultString = line.substring(startIndex, endIndex);
                        System.out.println(resultString);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/


