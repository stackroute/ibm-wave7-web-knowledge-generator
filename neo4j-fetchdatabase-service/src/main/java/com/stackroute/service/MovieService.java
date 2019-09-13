package com.stackroute.service;


import com.stackroute.model.Node;
import com.stackroute.Repository.MovieRepository;
import com.stackroute.model.Node1;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Node> getData(Node1 node) {
        String key1 = null;
        String key2 = null;
        if ((node.getNode2()).equals("Starring") || (node.getNode2()).equals("Director") || (node.getNode2()).equals("Writer")||(node.getNode2()).equals("Producer")||(node.getNode2()).equals("Language")||(node.getNode1()).equals("Movie")){
            key1 = "name";
        }
        if ((node.getNode2()).equals("Movie")||(node.getNode1()).equals("Starring")) {
            key1 = "title";
        }

        if ((node.getNode2()).equals("ReleasedYear")) {
            key1 = "year";
        }
        if (node.getNode3() != null) {
            if ((node.getNode3()).equals("Starring") || (node.getNode3()).equals("Director") || (node.getNode3()).equals("Writer")||(node.getNode3()).equals("Producer")||(node.getNode3()).equals("Language")) {
                key2 = "name";
            }
            if ((node.getNode3()).equals("Movie")) {
                key2 = "title";
            }

            if ((node.getNode3()).equals("ReleasedYear")) {
                key2 = "year";
            }
        }


        System.out.println(node);
//        System.out.println(movieRepository.suggestions1(node,key1));
        if ((node.getNode3()==null)||((node.getNode3().equals("MANY"))&&(node.getValue2()==null)) ){

                Collection<Node> nodesuggestions = movieRepository.suggestions1(node, key1);
                System.out.println(nodesuggestions);
                for (Node node1 : nodesuggestions) {
                    //System.out.println(node1);
                    String movie = node.getValue1();
                    HashMap<String, String> queries = new HashMap<>();
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
                        String questions = "Who is " + part + " of " + movie + "?";
                        String[] values = name.split("'");
                        queries.put(questions, values[1].trim());

                    } else if (part.trim().equals("ReleasedYear")) {
                        String question1 = "In which year " + movie + " released?";
                        String[] value1 = name.split("'");
                        queries.put(question1, value1[1].trim());
                    } else if (part.trim().equals("Language")) {
                        String question2 = "In which language " + movie + " released?";
                        String[] value1 = name.split("'");
                        queries.put(question2, value1[1].trim());
                    }
                    else if(part.trim().equals("Movie"))
                    {
                        String question3= "Movie in which "+node.getValue1()+" also acted";
                        String[] value3 = name.split("'");
                        queries.put(question3, value3[1].trim());
                    }

                    System.out.println(queries);
                }


            return movieRepository.findData(node, key1);
        } else {
//            System.out.println(movieRepository.suggestions2(node,key1,key2));

            System.out.println(key1);
            System.out.println(key2);
            System.out.println(movieRepository.findData2(node, key1, key2));
            Collection<Node> nodesuggestions = movieRepository.suggestions1(node, key1);
            System.out.println(nodesuggestions);
            for (Node node1 : nodesuggestions) {
                //System.out.println(node1);
                String movie = node.getValue1();
                HashMap<String, String> queries = new HashMap<>();
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
                    String questions = "Who is " + part + " of " + movie + "?";
                    String[] values = name.split("'");
                    queries.put(questions, values[1].trim());

                } else if (part.trim().equals("Released year")) {
                    String question1 = "In which year " + movie + " released?";
                    String[] value1 = name.split("'");
                    queries.put(question1, value1[1].trim());
                } else if (part.trim().equals("Language")) {
                    String question2 = "In which language " + movie + " released?";
                    String[] value1 = name.split("'");
                    queries.put(question2, value1[1].trim());
                }
                else if(part.trim().equals("Movie"))
                {
                    String question3= "Movie in which "+node.getValue1()+" also acted";
                    String[] value3 = name.split("'");
                    queries.put(question3, value3[1].trim());
                }

                System.out.println(queries);
            }
            return movieRepository.findData2(node, key1, key2);
        }
    }
}



