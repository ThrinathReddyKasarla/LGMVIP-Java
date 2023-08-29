import java.util.*;

class Website {
    String title;
    String url;
    int relevance;

    public Website(String title, String url, int relevance) {
        this.title = title;
        this.url = url;
        this.relevance = relevance;
    }
}

class SearchEngine {
    private List<Website> websites;
    private List<String> searchHistory;
    private Set<String> visitedPages;

    public SearchEngine() {
        websites = new ArrayList<>();
        searchHistory = new ArrayList<>();
        visitedPages = new HashSet<>();
    }

    public void addWebsite(String title, String url, int relevance) {
        websites.add(new Website(title, url, relevance));
    }

    public List<Website> search(String keyword) {
        searchHistory.add(keyword);

        List<Website> searchResults = new ArrayList<>();

        for (Website website : websites) {
            if (website.title.toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(website);
            }
        }

        searchResults.sort((a, b) -> Integer.compare(b.relevance, a.relevance));

        return searchResults;
    }

    public void visitPage(String url) {
        visitedPages.add(url);
    }

    public void displayVisitedPages() {
        System.out.println("Visited Pages:");
        for (String page : visitedPages) {
            System.out.println("- " + page);
        }
    }

    public void displaySearchHistory() {
        System.out.println("Search History:");
        for (String keyword : searchHistory) {
            System.out.println("- " + keyword);
        }
    }
}

public class Search_Engine {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.addWebsite("Wikipedia", "https://www.wikipedia.org", 10);
        searchEngine.addWebsite("Google", "https://www.google.com", 8);
        searchEngine.addWebsite("Stack Overflow", "https://stackoverflow.com", 9);
        searchEngine.addWebsite("GitHub", "https://www.github.com", 7);
        searchEngine.addWebsite("OpenAI", "https://www.openai.com", 6);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a keyword to search (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            List<Website> searchResults = searchEngine.search(input);

            int resultCount = Math.min(searchResults.size(), 30);

            System.out.println("Top " + resultCount + " search results for '" + input + "':");
            for (int i = 0; i < resultCount; i++) {
                Website website = searchResults.get(i);
                System.out.println((i + 1) + ". " + website.title + " - " + website.url);
            }

            // Simulate visiting a page
            if (!searchResults.isEmpty()) {
                System.out.print("Enter the number of the page you want to visit (or 0 to skip): ");
                int pageNum = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (pageNum > 0 && pageNum <= resultCount) {
                    Website website = searchResults.get(pageNum - 1);
                    searchEngine.visitPage(website.url);
                    System.out.println("Visited: " + website.url);
                }
            }
        }

        // Display history
        System.out.println("\nSearch History and Visited Pages:");
        searchEngine.displaySearchHistory();
        searchEngine.displayVisitedPages();
    }
}
