package advisor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);
        String input = "";
        boolean go = true;
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

        while (go) {
            input = scan.nextLine();
            switch (input) {
                case "featured":
                    StringBuilder featured = new StringBuilder("---FEATURED---\n");
                    for (String featuredPlaylist : featuredPlaylists) {
                        featured.append(featuredPlaylist).append("\n");
                    }
                    System.out.print(featured.toString());
                    break;
                case "new":
                    StringBuilder newReleases = new StringBuilder("---NEW RELEASES---\n");
                    for (String newAlbum : newAlbums) {
                        newReleases.append(newAlbum).append("\n");
                    }
                    System.out.print(newReleases.toString());
                    break;
                case "categories":
                    StringBuilder categoryList = new StringBuilder("---CATEGORIES---\n");
                    for (String category : categories) {
                        categoryList.append(category).append("\n");
                    }
                    System.out.print(categoryList.toString());
                    break;
                case "playlists Mood":
                    StringBuilder moodList = new StringBuilder("---MOOD PLAYLISTS---\n");
                    for (String mood : moodPlaylists) {
                        moodList.append(mood).append("\n");
                    }
                    System.out.print(moodList.toString());
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
