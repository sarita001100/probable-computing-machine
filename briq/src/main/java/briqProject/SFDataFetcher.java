package briqProject;


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SFDataFetcher {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://data.sfgov.org/resource/p4e4-a5a7.json");
        
        try {
            ClassicHttpResponse response = httpClient.execute(httpGet);
            String json = EntityUtils.toString(response.getEntity());
            
            // Parse JSON using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            
            // Add the "is_roof" field to each JSON object
            for (JsonNode entry : rootNode) {
                String description = entry.get("description").asText().toLowerCase();
                boolean isRoofRelated = description.contains("roof");
                ((ObjectNode) entry).put("is_roof", isRoofRelated);
                
                // Format the timestamp field as "MM-dd-yyyy"
//                String timestamp = entry.get("timestamp").asText();
//                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//                SimpleDateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy");
//                Date date = inputFormat.parse(timestamp);
//                String formattedTimestamp = outputFormat.format(date);
//                ((ObjectNode) entry).put("timestamp", formattedTimestamp);
            }
            
            // Generate the JSON filename with current timestamp
            SimpleDateFormat fileTimestampFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
            String jsonFilename = "sfgov_" + fileTimestampFormat.format(new Date()) + ".json";
            
            // Write the modified JSON data to a JSON file
            try (FileWriter fileWriter = new FileWriter(jsonFilename)) {
                objectMapper.writeValue(fileWriter, rootNode);
            }
            
            // Generate the CSV filename with current timestamp
            String csvFilename = "/HOME/briq/sfgov_" + fileTimestampFormat.format(new Date()) + ".csv";
            
            // Write the modified JSON data to a CSV file
       //     CsvWriter.writeCsv(rootNode, csvFilename);
            
            System.out.println("JSON and CSV files have been generated.");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
