import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercises{
    public static void main(String args[]) throws IOException, InterruptedException {
        

        Runnable longRunningCalculation = () -> {
            try {
                Exercises.getWebpage("https://www.bbc.co.uk");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };
         
        Thread backgroundCalculationThread = new Thread(longRunningCalculation);
        backgroundCalculationThread.start();
        System.out.println("Thread has been started");
        backgroundCalculationThread.join();
        System.out.println("Thread has been completed");
    }

    private static String getWebpage(String url) throws IOException {
        URL newUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) newUrl.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();


        System.out.println(content.toString());
        return content.toString();
    }

    

}


