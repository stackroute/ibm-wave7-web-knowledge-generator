package com.stackroute.controller;



import com.stackroute.core.Pipeline;
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
       // System.out.println("consumed url is:"+input);
    }

    @GetMapping
    @RequestMapping(value="/pos")
    public HashMap<String,String> ner()
    {

        //System.out.println("input"+input);

        HashMap<String,String> collect=new HashMap<>();
        String[] nodes=input.split("%");
        nodes[0]=" ";
        int flag=0;
        for(int i=0;i<nodes.length;i++)
        {
            if(!nodes[i].equals(" ")&&flag==0)
            {
                collect.put("title",nodes[i].trim());
                flag+=1;
            }
            else if(nodes[i].trim().equals("Directed by")||nodes[i].trim().equals("Produced by")||nodes[i].trim().equals("Starring"))
            {
                String crew=nodes[i].trim();
                int length=findDirectors(nodes,i+1).length();
                String res=findDirectors(nodes,i+1).substring(0,length-1);
                collect.put(crew,res);
            }
            else if(nodes[i].equals(" Release date"))
            {
                String result=findNext(nodes,i+1).trim();
                int length=result.length();
                collect.put("Release year",result.substring(length-4));
            }
        }
        System.out.println("qwe"+collect);
        return collect;
    }

    public static String findDirectors(String[] nodes, int posn)
    {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String directors="";

        for(int i=posn;i<nodes.length;i++)
        {
            CoreDocument coreDocument=new CoreDocument(nodes[i]);
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreLabel> coreLabels=coreDocument.tokens();
            for(CoreLabel coreLabel:coreLabels)
            {
                String pos=coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if(pos.equals("NNP"))
                {
                    directors+=nodes[i]+",";
                    break;

                }
                else
                {
                    return directors;
                }
            }
        }
        return directors;
    }
    public static String findNext(String[] nodes, int posn)
    {
        for(int i=posn;i<nodes.length;i++)
        {
            if(nodes[i].equals(" "))
            {

            }
            else
            {
                return nodes[i];
            }
        }
        return null;
    }
}
