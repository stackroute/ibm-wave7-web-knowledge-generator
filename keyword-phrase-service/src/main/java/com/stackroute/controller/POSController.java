package com.stackroute.controller;



import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/api/v1")
public class POSController {
    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;
String input="";
    @KafkaListener(topics = "Fetch_Webpage", groupId = "group_id")
    public void consumer(String message) throws IOException {

        this.input=message;
        System.out.println("consumed url is:"+input);
    }

    @GetMapping
    @RequestMapping(value="/pos")
    public Set<String> ner()
    {

        CoreDocument coreDocument = new CoreDocument(this.input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels=coreDocument.tokens();
        return new HashSet<String>(collectionList(coreLabels));
    }


    private List<String> collectionList(List<CoreLabel> coreLabels)
    {
        List<String> res= Arrays.asList(new String[coreLabels.size()]);
        int i=0;
        for(CoreLabel corelabel : coreLabels)
        {
            String pos=corelabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if(pos.equals("NNP") || pos.equals("VBN") || pos.equals("VBZ") || pos.equals("CD"))
            {
                res.set(i, corelabel.originalText());
                i++;
            }


        }
        return res;
    }
}
