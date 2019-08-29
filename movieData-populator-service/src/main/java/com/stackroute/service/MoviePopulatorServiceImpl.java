package com.stackroute.service;
import com.stackroute.repository.MoviePopulatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Service
public class MoviePopulatorServiceImpl implements MoviePopulatorService{

    @Autowired
    MoviePopulatorRepository moviePopulatorRepository;

    public MoviePopulatorServiceImpl(MoviePopulatorRepository moviePopulatorRepository) {
        this.moviePopulatorRepository = moviePopulatorRepository;
    }

    @Override

    public void fetchDataFromOmdbApi() {
//
            System.out.println("service");
            final String uri = "http://www.omdbapi.com/?apikey=43866b21&i=tt0048545";
            RestTemplate restTemplate = new RestTemplate();
           // ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);

            System.out.println(result.getBody());

          String jsonString = result.getBody();

        JSONObject output;

            try {
                output = new JSONObject(jsonString);
                System.out.println(output);
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(output);
                File file=new File("./moviedata.csv");
                String csv = CDL.toString(jsonArray);
                FileUtils.writeStringToFile(file, csv);
                System.out.println("Data has been Sucessfully Writeen to "+file);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
//                // TODO Auto-generated catch block
                e.printStackTrace();
            }


    }
}