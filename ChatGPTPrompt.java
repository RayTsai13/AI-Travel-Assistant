import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * This class represents a prompt and answer to the OpenAI API.
 * It contains static methods to process the prompt from the API.
 * Note: This code was HEAVILY inspired and guided by this tutorial:
 * https://rollbar.com/blog/how-to-use-chatgpt-api-with-java/
 *
 * @author Raymond Tsai
 * @version 4/28/2024
 */

public class ChatGPTPrompt {
    private String[] answer;
    private String prompt;

    public ChatGPTPrompt(String prompt) {
        this.prompt = prompt;
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "sk-proj-3vFpThOiFM8fcSIJM9L7T3BlbkFJPhPhkn3nciXUMtnNvwiw";
        String model = "gpt-3.5-turbo";

        try {
            //establishing connection to API
            URL obj = new URL(url);
            HttpURLConnection link = (HttpURLConnection) obj.openConnection();
            link.setRequestMethod("POST");
            link.setRequestProperty("Authorization", "Bearer " + apiKey);
            link.setRequestProperty("Content-Type", "application/json");

            //entering prompt
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            link.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(link.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            //reading output from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(link.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            answer = formatMessageFromJSONResponse(extractMessageFromJSONResponse(response.toString()));
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * A static method that extracts the actual response from the JSON
     * blurb that contains a lot of other information
     *
     * @param response
     *      The raw String from the JSON response
     * @return String
     *      The actual response from the API
     */
    private static String extractMessageFromJSONResponse (String response) {
        int start = response.indexOf("content") + 11;
        int end =  response.indexOf("\"", start);
        return response.substring(start, end);
    }

    /**
     * A static method that turns the String into a String array,
     * with every String representing a line.
     *
     * @param response
     * @return
     */
    private static String[] formatMessageFromJSONResponse (String response) {
        String[] splitted = response.split("\\\\n");
        return splitted;
    }

    public String[] getAnswer() {
        return this.answer;
    }
}
