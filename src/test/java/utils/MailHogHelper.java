package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class MailHogHelper {

    public static String getVerificationLink() throws Exception {
        String apiUrl = "http://localhost:8025/api/v2/messages?limit=1";

        // Mengambil hanya email terbaru (?limit=1)
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Membaca response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        // Parse JSON
        JSONObject json = new JSONObject(content.toString());
        JSONArray items = json.getJSONArray("items");

        if (items.length() == 0) {
            throw new Exception("No emails found in MailHog");
        }

        // Mengambil email pertama (terbaru)
        JSONObject latestEmail = items.getJSONObject(0);
        String rawBody = latestEmail.getJSONObject("Content").getString("Body");

        // Decode dan debug
        String body = decodeQuotedPrintable(rawBody);
        System.out.println("=== RAW EMAIL CONTENT ===");
        System.out.println(body);

        // Ekstrak link dengan pattern lebih akurat
        Pattern urlPattern = Pattern.compile(
                "http://localhost:8000/verify_account/[a-zA-Z0-9]+",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = urlPattern.matcher(body);

        if (matcher.find()) {
            String link = matcher.group();
            System.out.println("Extracted verification link: " + link);
            return link;
        }

        throw new Exception("Verification link not found in email body");
    }

    public static String decodeQuotedPrintable(String qp) {
        // Mempertahankan decode yang ada
        qp = qp.replaceAll("=\\r?\\n", "");
        qp = qp.replaceAll("=3D", "=");

        StringBuffer decoded = new StringBuffer();
        Pattern pattern = Pattern.compile("=([0-9A-Fa-f]{2})");
        Matcher matcher = pattern.matcher(qp);

        while (matcher.find()) {
            char decodedChar = (char) Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(decoded, String.valueOf(decodedChar));
        }
        matcher.appendTail(decoded);

        return decoded.toString();
    }
}
