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
        String apiUrl = "http://localhost:8025/api/v2/messages";

        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder content = new StringBuilder();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        JSONObject json = new JSONObject(content.toString());
        JSONArray items = json.getJSONArray("items");

        if (items.length() == 0) {
            throw new Exception("Tidak ada email ditemukan di MailHog");
        }

        JSONObject latestEmail = items.getJSONObject(items.length() - 1);
        String rawBody = latestEmail.getJSONObject("Content").getString("Body");

        // ✅ Decode quoted-printable content
        String body = decodeQuotedPrintable(rawBody);

        // Debug output
        System.out.println("=== Decoded Email Body ===");
        System.out.println(body);

        // ✅ Ambil link dari href tag HTML
        Pattern urlPattern = Pattern.compile("href=[\"'](http[s]?://[^\\s\"'>]*?/verify_account/[^\\s\"'>]+)[\"']");
        Matcher matcher = urlPattern.matcher(body);

        if (matcher.find()) {
            return matcher.group(1); // hanya ambil isi dari href="..."
        } else {
            throw new Exception("Link verifikasi tidak ditemukan di email");
        }
    }

    // ✅ Fungsi decode quoted-printable sederhana
    public static String decodeQuotedPrintable(String qp) {
        // Hapus soft line breaks (quoted-printable continuation)
        qp = qp.replaceAll("=\\r?\\n", "");
        qp = qp.replaceAll("=3D", "="); // ubah =3D jadi =

        StringBuffer decoded = new StringBuffer();
        Pattern pattern = Pattern.compile("=([0-9A-Fa-f]{2})");
        Matcher matcher = pattern.matcher(qp);

        while (matcher.find()) {
            String hex = matcher.group(1);
            char decodedChar = (char) Integer.parseInt(hex, 16);
            matcher.appendReplacement(decoded, Matcher.quoteReplacement(String.valueOf(decodedChar)));
        }
        matcher.appendTail(decoded);

        return decoded.toString();
    }

}
