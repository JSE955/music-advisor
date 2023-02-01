package advisor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Scanner scan = new Scanner(System.in);
        String input = "";
        boolean go = true;
        boolean authorization = false;

        // Examples of mock data
        String[] featuredPlaylists = new String[] {"Mellow Morning", "Wake Up and Smell the Coffee",
                                                    "Monday Motivation", "Songs to Sing in the Shower"};
        String[] newAlbums = new String[] {"Mountains [Sia, Diplo, Labrinth",
                                            "Runaway [Lil Peep]",
                                            "The Greatest Show [Panic! At The Disco]",
                                            "All Out Life [Slipknot]"};
        String[] categories = new String[] {"Top Lists", "Pop", "Mood", "Latin"};
        String[] moodPlaylists = new String[] {"Walk Like A Badass",
                                                "Rage Beats",
                                                "Arab Mood Booster",
                                                 "Sunday Stroll"};

        // Spotify Application Fields
        String clientId = "a15be1aafac14bd6b5d0433c486d02a7";
        String redirectUri = "http://localhost:8080";

        while (go) {
            input = scan.nextLine();
            switch (input) {
                case "auth":
                    authorization = true;

                    // Start HTTP Server and run HTTP Server
                    HttpServer server = HttpServer.create();
                    server.bind(new InetSocketAddress(8080), 0);
                    server.createContext("/",
                            new HttpHandler() {
                                public void handle(HttpExchange exchange) throws IOException {
                                    String code = "";
                                    String agree = "Got the code. Return back to your program.";
                                    String cancel = "Authorization code not found. Try again.";
                                    String query = exchange.getRequestURI().getQuery();
                                    exchange.sendResponseHeaders(200, code.length());
                                    exchange.getResponseBody().write(query.getBytes());
                                    exchange.getResponseBody().close();
                                }
                            }
                    );
                    server.start();

                    // Create HTTP Client
                    //HttpClient client = HttpClient.newBuilder().build();

                    // Build Authorization Request Link
                    StringBuilder link = new StringBuilder("https://accounts.spotify.com/authorize?client_id=");
                    link.append(clientId);
                    link.append("&redirect_uri=");
                    link.append(redirectUri);
                    link.append("&response_type=code");

                    /* Create HTTP Request
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(link.toString()))
                            .GET()
                            .build();

                    // Send HTTP Request
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                    StringBuilder link = new StringBuilder("https://accounts.spotify.com/authorize?client_id=");
                    link.append(clientId);
                    link.append("&redirect_uri=");
                    link.append(redirectUri);
                    link.append("&response_type=code");
                    link.append("&redirect_uri=http://localhost:8080&response_type=code\n---SUCCESS---\n");*/
                    System.out.println("use this link to request the access code:");
                    System.out.println(link.toString());
                    System.out.println("waiting for code...");

                    //server.stop(10);
                    // System.out.println("\n---SUCCESS---");
                    break;
                case "featured":
                    if (!authorization) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        StringBuilder featured = new StringBuilder("---FEATURED---\n");
                        for (String featuredPlaylist : featuredPlaylists) {
                            featured.append(featuredPlaylist).append("\n");
                        }
                        System.out.print(featured.toString());
                    }
                    break;
                case "new":
                    if (!authorization) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        StringBuilder newReleases = new StringBuilder("---NEW RELEASES---\n");
                        for (String newAlbum : newAlbums) {
                            newReleases.append(newAlbum).append("\n");
                        }
                        System.out.print(newReleases.toString());
                    }
                    break;
                case "categories":
                    if (!authorization) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        StringBuilder categoryList = new StringBuilder("---CATEGORIES---\n");
                        for (String category : categories) {
                            categoryList.append(category).append("\n");
                        }
                        System.out.print(categoryList.toString());
                    }
                    break;
                case "playlists Mood":
                    if (!authorization) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        StringBuilder moodList = new StringBuilder("---MOOD PLAYLISTS---\n");
                        for (String mood : moodPlaylists) {
                            moodList.append(mood).append("\n");
                        }
                        System.out.print(moodList.toString());
                    }
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    go = false;
                    break;
                default:
                    System.out.println("This is a mistake.");
                    break;
            }
        }
    }
}
