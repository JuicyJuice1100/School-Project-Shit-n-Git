import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.sql.SQLException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.AggregateIterable;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;

public class Driver {
    private static Properties config;

    private static void loadProperties() {
        try {
            FileInputStream file = new FileInputStream("config.properties");
            config = new Properties();
            config.load(file);
            file.close();
        } catch (Exception e) {
            System.out.println("An error occurred while trying to load config.properties.");
        }
    }

    public static class Query{
        private String query, parameters;
        private int num;
        private Scanner scan;

        public Query(){
            scan = new Scanner(System.in);
            query = scan.next();
            num = scan.nextInt();
            parameters = scan.next();
        }
    }
    

    public static void main(String[] args) {
        loadProperties();
        Query query = new Query();

        MongoClient mongoClient = null;
         /*try {
             mongoClient = getMongoClient();
             MongoDatabase mongoDB = mongoClient.getDatabase(config.getProperty("database"));
             System.out.println("Connected to MongoDB");
             // At this point, we can begin interacting with the database.
             //Get all documents
             MongoCollection<Document> collection = mongoDB.getCollection("Patron");
             for (Document document : collection.find()) {
                 System.out.println("Found a document: " + document.toString());
             }

             //Run a query:
             //Find all patrons with age > 25 and age <= 60; project their name and age only
             MongoCursor<Document> cursor = collection.find(
                                                 and(
                                                     gt("age", 25), lte("age", 60)
                                                    )
                                             )
                                             .projection(fields(include("patron", "age"),excludeId()))
                                             .iterator();
              //http://mongodb.github.io/mongo-java-driver/3.4/javadoc/com/mongodb/client/model/Filters.html

             try {
                     while (cursor.hasNext()) {
                         System.out.println(cursor.next().toJson());
                     }
             }
             finally {
                     cursor.close();
             }

             //Run an aggregation:
             //Count number of patrons with age > 25 and <=60
             AggregateIterable<Document> output = collection.aggregate(
                                     Arrays.asList(
                                         Aggregates.match(and(
                                                         gt("age", 25), lte("age", 60)
                                                          )),
                                         Aggregates.group("null", Accumulators.sum("count", 1))
                                         )
             );
             for (Document dbObject : output)
                 System.out.println(dbObject);

         }
         catch (Exception e) {
             // Handle errors here
         }
         finally {
             // We must assure the connection gets closed, even in the event of an exception.
             if (mongoClient != null) {
                 mongoClient.close();
             }
         }*/
        
        if(query.query.equalsIgnoreCase("Query")){
            switch(query.num){
                case 1:
                    try{
                        mongoClient = getMongoClient();
                        MongoDatabase mongoDB = mongoClient.getDatabase(config.getProperty("database"));
                        System.out.println("Connected to MongoDB");
                        // At this point, we can begin interacting with the database.
                        //Get all documents
                        MongoCollection<Document> collection = mongoDB.getCollection("Branch");
                        for (Document document : collection.find()) {
                            System.out.println("Found a document: " + document.toString());
                        }
                        AggregateIterable<Document> output = collection.aggregate(
                                                Arrays.asList(
                                                    Aggregates.unwind("loan"),
                                                    Aggregates.match(gt("loan.amount", query.parameters)),
                                                    Aggregates.group("null", Accumulators.sum("count", 1))
                                                    )
                        );
                        for (Document dbObject : output)
                            System.out.println(dbObject);
                    }  
                    catch (SQLException e) 
                    {System.out.println("SQL Error " + e.getMessage());}
                    catch (Exception e) 
                    {System.out.println("General Error " + e.getMessage());}
                    finally {
                        // We must assure the connection gets closed, even in the event of an exception.
                        if (mongoClient != null) {
                            mongoClient.close();
                        }
                    }
                    break;
                case 2:
                    try{
                        mongoClient = getMongoClient();
                        MongoDatabase mongoDB = mongoClient.getDatabase(config.getProperty("database"));
                        System.out.println("Connected to MongoDB");
                        // At this point, we can begin interacting with the database.
                        //Get all documents
                        MongoCollection<Document> collection = mongoDB.getCollection("Branch");
                        for (Document document : collection.find()) {
                            System.out.println("Found a document: " + document.toString());
                        }
                        AggregateIterable<Document> output = collection.aggregate(
                                                Arrays.asList(
                                                    Aggregates.unwind("loan"),
                                                    Aggregates.match(gt("loan.amount", query.parameters)),
                                                    Aggregates.group("loan.cname", Accumulators.sum("sum", "loan.amount"))
                                                    )
                        );
                        for (Document dbObject : output)
                            System.out.println(dbObject);
                    }
                    catch (SQLException e) 
                    {System.out.println("SQL Error " + e.getMessage());}
                    catch (Exception e) 
                    {System.out.println("General Error " + e.getMessage());}
                    finally {
                        // We must assure the connection gets closed, even in the event of an exception.
                        if (mongoClient != null) {
                            mongoClient.close();
                        }
                    }
                    break;
                case 3:
                    try{
                        mongoClient = getMongoClient();
                        MongoDatabase mongoDB = mongoClient.getDatabase(config.getProperty("database"));
                        System.out.println("Connected to MongoDB");
                        // At this point, we can begin interacting with the database.
                        //Get all documents
                        MongoCollection<Document> collection = mongoDB.getCollection("Branch");
                        for (Document document : collection.find()) {
                            System.out.println("Found a document: " + document.toString());
                        }
                        AggregateIterable<Document> output = collection.aggregate(
                            Arrays.asList(
                                Aggregates.unwind("deposit"),
                                Aggregates.unwind("loan"),
                                Aggregates.match(or(eq("deposit.cname", query.parameters), eq("loan.cname", query.parameters))),
                                Aggregates.group("deposit.cname",  Accumulators.sum("sum", "deposit.amount"), Accumulators.sum("sum", "loan.amount"))
                                )
                        );
                        for (Document dbObject : output)
                            System.out.println(dbObject);
                    }
                    catch (SQLException e) 
                    {System.out.println("SQL Error " + e.getMessage());}
                    catch (Exception e) 
                    {System.out.println("General Error " + e.getMessage());}
                    finally {
                        // We must assure the connection gets closed, even in the event of an exception.
                        if (mongoClient != null) {
                            mongoClient.close();
                        }
                    }
                    break;
                default:
                    System.out.println("invalid query call");
            }
        } else {
            System.out.println("invalid query call");
        }
    }


    private static MongoClient getMongoClient() throws Exception {
        MongoClient client = null;
        try {
            // Set up server connection info.
            ServerAddress address = new ServerAddress(config.getProperty("server"), Integer.parseInt(config.getProperty("port")));

            // Set up connection credentials. Without this step, we cannot make changes to
            // the database.
            MongoCredential credential = MongoCredential.createCredential(
                    config.getProperty("username"),
                    config.getProperty("authenticationDatabase"),
                    config.getProperty("password").toCharArray()
                    );
            client = new MongoClient(address, Arrays.asList(credential));
        } catch (Exception e) {
            System.out.println("An error occurred while creating the MongoClient.");
            throw e;
        }
        return client;
    }
}
