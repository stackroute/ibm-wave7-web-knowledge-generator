package com.stackroute.controller;

<<<<<<< HEAD
=======



>>>>>>> f30945102d142b2d1705088dce996bfdbb348780
import com.stackroute.model.TypeNER;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1")
public class NERController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    @PostMapping
    @RequestMapping(value="/ner")
    public Set<String> ner(@RequestBody final String input, @RequestParam final TypeNER typeNER)
    {

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels=coreDocument.tokens();
        return new HashSet<String>(collectList(coreLabels, typeNER));
    }

    private List<String> collectList(List<CoreLabel> coreLabels, final TypeNER typeNER)
    {
        return coreLabels
                .stream()
                .filter(coreLabel -> typeNER.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }
}
