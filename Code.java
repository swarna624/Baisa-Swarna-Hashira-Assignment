import java.io.*;
import java.util.*;

class Main{

    private static long decodeBase(String value, int base) {
        return Long.parseLong(value, base);
    }
    private static long lagrangeInterpolation(List<long[]> points) {
        int k = points.size();
        double c = 0.0;

        for (int i = 0; i < k; i++) {
            long x_i = points.get(i)[0];
            long y_i = points.get(i)[1];
            double term = y_i;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    long x_j = points.get(j)[0];
                    term *= (double) x_j / (x_j - x_i);
                }
            }
            c += term;
        }
        return Math.round(c);
    }
    private static Map<String, Map<String, String>> parseJson(String json) {
        Map<String, Map<String, String>> data = new HashMap<>();
        json = json.replaceAll("\\s+", "");
        json = json.substring(1, json.length() - 1);

        String[] entries = json.split("},");
        for (String entry : entries) {
            if (!entry.endsWith("}")) entry += "}";
            String[] parts = entry.split(":", 2);
            String key = parts[0].replaceAll("\"", "");
            String value = parts[1];

            if (value.startsWith("{")) {
                Map<String, String> inner = new HashMap<>();
                value = value.substring(1, value.length() - 1);
                String[] kvs = value.split(",");
                for (String kv : kvs) {
                    String[] kvSplit = kv.split(":");
                    inner.put(kvSplit[0].replaceAll("\"", ""), kvSplit[1].replaceAll("\"", ""));
                }
                data.put(key, inner);
            }
        }
        return data;
    }

    private static long findSecret(String jsonString) {
        Map<String, Map<String, String>> jsonData = parseJson(jsonString);

        Map<String, String> keys = jsonData.get("keys");
        int n = Integer.parseInt(keys.get("n"));
        int k = Integer.parseInt(keys.get("k"));

        List<long[]> points = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String idx = String.valueOf(i);
            if (!jsonData.containsKey(idx)) continue;

            Map<String, String> entry = jsonData.get(idx);
            int base = Integer.parseInt(entry.get("base"));
            String encodedValue = entry.get("value");

            long y = decodeBase(encodedValue, base);
            points.add(new long[]{i, y});

            if (points.size() == k) break;
        }

        return lagrangeInterpolation(points);
    }
    public static void main(String[] args) {
        String testCase1 = "{ \"keys\": { \"n\":\"4\", \"k\":\"3\" }, " +
                " \"1\": {\"base\":\"10\", \"value\":\"12\"}, " +
                " \"2\": {\"base\":\"2\", \"value\":\"110\"}, " +
                " \"3\": {\"base\":\"8\", \"value\":\"14\"}, " +
                " \"4\": {\"base\":\"16\", \"value\":\"C\"} }";

        String testCase2 = "{ \"keys\": { \"n\":\"3\", \"k\":\"2\" }, " +
                " \"1\": {\"base\":\"10\", \"value\":\"20\"}, " +
                " \"2\": {\"base\":\"2\", \"value\":\"10100\"}, " +
                " \"3\": {\"base\":\"8\", \"value\":\"24\"} }";

        System.out.println("Test Case 1 -> Secret: " + findSecret(testCase1));
        System.out.println("Test Case 2 -> Secret: " + findSecret(testCase2));
    }
}
