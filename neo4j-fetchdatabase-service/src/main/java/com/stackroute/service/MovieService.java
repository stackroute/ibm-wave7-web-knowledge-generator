package com.stackroute.service;


import com.stackroute.model.Node;
import com.stackroute.Repository.MovieRepository;
import com.stackroute.model.Node1;
import com.stackroute.model.Result;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Result getData(Node1 node) {
        String key1 = "name";
        String key2 = "name";
        String key3 = "name";
        if (node.getValue2() == null) {
            if ((node.getNode2()).equals("Starring") || (node.getNode2()).equals("Director") || (node.getNode2()).equals("Writer") || (node.getNode2()).equals("Producer") || (node.getNode2()).equals("Language") || (node.getNode1()).equals("Movie")) {
                key1 = "name";
            }
            if ((node.getNode2()).equals("Movie") || (node.getNode1()).equals("Starring")) {
                key1 = "title";
            }
            if ((node.getNode2()).equals("ReleasedYear")) {
                key1 = "year";
            }
        }
        if (node.getNode3() != null) {
            if ((node.getNode3()).equals("Starring") || (node.getNode3()).equals("Director") || (node.getNode3()).equals("Writer") || (node.getNode3()).equals("Producer") || (node.getNode3()).equals("Language")) {
                key2 = "name";
            }
            if ((node.getNode3()).equals("Movie") || (node.getNode1()).equals("Starring")) {
                key2 = "title";
            }
            if ((node.getNode3()).equals("ReleasedYear")) {
                key2 = "year";
            }
        }
        System.out.println(node);
//        System.out.println(movieRepository.suggestions1(node,key1));
        if ((node.getNode3() == null) || ((node.getNode3().equals("MANY")) && (node.getValue2() == null))) {
            Collection<Node> nodesuggestions = movieRepository.suggestions1(node, key1);
            HashMap<String, String> queries1 = getsuggestions(nodesuggestions, node);
            System.out.println(queries1);
            Result result = new Result();
            result.setNode(movieRepository.findData(node, key1));
            result.setSuggestions(queries1);
            System.out.println(result);
            return result;
        } else if (node.getValue3() == null) {
//            System.out.println(movieRepository.suggestions2(node,key1,key2));
            Collection<Node> nodesuggestions = movieRepository.suggestions1(node, key1);
            Collection<Node> nodesuggestions1 = movieRepository.suggestions1(node, key2);
            HashMap<String, String> queries1 = getsuggestions(nodesuggestions, node);
            System.out.println(key1);
            System.out.println(key2);
            HashMap<String, String> queries = new HashMap<>();
            Result result = new Result();
            result.setNode(movieRepository.findData2(node, key1, key2));
            result.setSuggestions(queries1);
            System.out.println(result);
            return result;
        } else {
            Collection<Node> nodesuggestions = movieRepository.suggestions1(node, key1);
            Collection<Node> nodesuggestions1 = movieRepository.suggestions1(node, key2);
            Collection<Node> nodesuggestions2 = movieRepository.suggestions1(node, key3);
            HashMap<String, String> queries1 = getsuggestions(nodesuggestions, node);
            queries1 = getsuggestions(nodesuggestions1, node);
            queries1 = getsuggestions(nodesuggestions2, node);
            System.out.println(queries1);
            System.out.println(key1);
            System.out.println(key2);
            Result result = new Result();
            result.setNode(movieRepository.findData2(node, key1, key2));
            System.out.println(result);
            return result;
        }
    }

    private HashMap<String, String> getsuggestions(Collection<Node> nodesuggestions, Node1 node) {
        HashMap<String, String> queries = new HashMap<>();
        int index = 1;
        for (Node node1 : nodesuggestions) {
            //System.out.println(node1);
            String movie = node.getValue1();
            String question = "";
            String name = node1.getName().toString();
            System.out.println(name);
            //System.out.println(movie);
            int i;
            for (i = 0; i < name.length(); i++) {
                if (name.charAt(i) == '{') {
                    break;
                }
            }
            String part = name.substring(0, i);
            //System.out.println(part);
            if (part.trim().equals("Starring") || part.trim().equals("Director") || part.trim().equals("Producer")) {
                String questions = index + ") Who is " + part + " of " + movie + "?";
                String[] values = name.split("'");
                queries.put(questions, values[1].trim());
                index++;
            } else if (part.trim().equals("ReleasedYear")) {
                String question1 = index + ") In which year " + movie + " released?";
                String[] value1 = name.split("'");
                queries.put(question1, value1[1].trim());
                index++;
            } else if (part.trim().equals("Language")) {
                String question2 = index + ") In which language " + movie + " released?";
                String[] value1 = name.split("'");
                queries.put(question2, value1[1].trim());
                index++;
            } else if (part.trim().equals("Movie")) {
                String question3 = index + ") Movie in which " + node.getValue1() + " also acted";
                String[] value3 = name.split("'");
                queries.put(question3, value3[1].trim());
                index++;
            }
            System.out.println(queries);
        }
        return queries;
    }
}





