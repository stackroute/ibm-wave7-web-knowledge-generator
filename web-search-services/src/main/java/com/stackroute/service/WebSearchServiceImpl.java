package com.stackroute.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Modals.CodeBeautify;
import com.stackroute.Modals.Result;
import com.stackroute.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebSearchServiceImpl implements WebSearchService {
    @Autowired
    private SearchRepository searchRepository;

    public WebSearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public List<Result> getSearchResults(String searchString) throws URISyntaxException {
        List<Result>  output= new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://app.zenserp.com/api/v2/search?q="+searchString+"&hl=en&gl=US&location=United%20States&search_engine=google.com&apikey=9df762e0-c56a-11e9-b82a-eb4e4c7a090f";
        URI uri = new URI(url);
        String string = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        CodeBeautify result = null;
        try {
            result = objectMapper.readValue(string, CodeBeautify.class);
            List<Result> resultList = result.getOrganic();
            output = saveResults(resultList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public List<Result> saveResults(List<Result> resultList) throws URISyntaxException {
        List<Result>  output= new ArrayList<>();

            for (Result searchResult: resultList) {
                Result result1 = searchRepository.save(searchResult);
                output.add(searchRepository.save(result1));
            }
        return output;
    }
}


