import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    List<String> output = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");

            if (parameters[0].equals("anewstringtoadd")) {
                output.add(parameters[1]); // append to list
                return String.format(parameters[1]);
            } else if (parameters[0].equals("pineapple")) {
                output.add("pineapple");
                return "pineapple";
            } else if (parameters[0].equals("apple")) {
                output.add("apple");
                return "apple";
            } else {
                return "404 Not Found!";
            }
        } else {
            return "404 Not Found!";
        }
    }
}

class NumberServer { // opening server
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
