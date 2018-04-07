package com.google.android.gms.samples.vision.ocrreader;

/**
 * Created by rushali on 6/4/18.
 */

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


/**
 * Created by rusha on 5/24/2017.
 */

public class QueryUtils {


    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    public static ArrayList<Thesaurus> fetchNewsData(String requestURL) {
        // Create URL object
        URL url = createURL(requestURL);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHTTPRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        // Parse JSON string and create an {@ArrayList<NewsItem>} object
        ArrayList<Thesaurus> t = extractData(jsonResponse);

        return t;
    }

    public static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error Creating URL", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHTTPRequest(URL url) throws IOException {

        // If the url is empty, return early
        String jsonResponse = null;
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200), then read the input stream and
            // parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results", e);
        } finally {
            // Close connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            // Close stream
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputstream) throws IOException {
        StringBuilder streamOutput = new StringBuilder();
        if (inputstream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputstream, Charset
                    .forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                streamOutput.append(line);
                line = reader.readLine();
            }
        }
        return streamOutput.toString();
    }



    private static ArrayList<Thesaurus> extractData(String newsDataJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsDataJSON)) {
            return null;
        }

        // Create empty ArrayList in which the parsed data will be adde

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        Thesaurus t = new Thesaurus();
        ArrayList<Thesaurus> a = new ArrayList<>();

        try {
            // Build a list of NewsItem Objects
            JSONObject rootObject = new JSONObject(newsDataJSON);
            JSONObject jsonResults = null;
            if(rootObject.has("adjective"))
            jsonResults = rootObject.getJSONObject("adjective");

            if(jsonResults!=null) {
                JSONArray resultsArray1 = null;
                if (jsonResults.has("syn") && jsonResults.getJSONArray("syn") != null)
                    resultsArray1 = jsonResults.getJSONArray("syn");

                JSONArray resultsArray2 = null;
                if (jsonResults.has("ant") && jsonResults.getJSONArray("ant") != null)
                    resultsArray2 = jsonResults.getJSONArray("ant");

                JSONArray resultsArray3 = null;
                if (jsonResults.has("rel") && jsonResults.getJSONArray("rel") != null)
                    resultsArray3 = jsonResults.getJSONArray("rel");

                JSONArray resultsArray4 = null;
                if (jsonResults.has("sim") && jsonResults.getJSONArray("sim") != null)
                    resultsArray4 = jsonResults.getJSONArray("sim");

                // Variables for JSON parsing

                String[] arr1 = null;
                if (resultsArray1 != null) {
                    arr1 = new String[resultsArray1.length()];
                    for (int i = 0; i < resultsArray1.length(); i++)
                        arr1[i] = resultsArray1.getString(i);
                }


                String[] arr2 = null;
                if (resultsArray2 != null) {
                    arr2 = new String[resultsArray2.length()];
                    for (int i = 0; i < resultsArray2.length(); i++)
                        arr2[i] = resultsArray2.getString(i);
                }


                String[] arr3 = null;
                if (resultsArray3 != null) {
                    arr3 = new String[resultsArray3.length()];
                    for (int i = 0; i < resultsArray3.length(); i++)
                        arr3[i] = resultsArray3.getString(i);
                }


                String[] arr4 = null;
                if (resultsArray4 != null) {
                    arr4 = new String[resultsArray4.length()];
                    for (int i = 0; i < resultsArray4.length(); i++)
                        arr4[i] = resultsArray4.getString(i);
                }

                t = new Thesaurus(arr1, arr3, arr2, arr4);
                a.add(t);
            }




            JSONObject jsonResults1 = null;
            if(rootObject.has("noun"))
            jsonResults1 = rootObject.getJSONObject("noun");

            if(jsonResults1!=null) {
                JSONArray resultsArray1 = null;
                if (jsonResults1.has("syn") && jsonResults1.getJSONArray("syn") != null)
                    resultsArray1 = jsonResults1.getJSONArray("syn");

                JSONArray resultsArray2 = null;
                if (jsonResults1.has("ant") && jsonResults1.getJSONArray("ant") != null)
                    resultsArray2 = jsonResults1.getJSONArray("ant");

                JSONArray resultsArray3 = null;
                if (jsonResults1.has("rel") && jsonResults1.getJSONArray("rel") != null)
                    resultsArray3 = jsonResults1.getJSONArray("rel");

                JSONArray resultsArray4 = null;
                if (jsonResults1.has("sim") && jsonResults1.getJSONArray("sim") != null)
                    resultsArray4 = jsonResults1.getJSONArray("sim");

                // Variables for JSON parsing

                String[] arr1 = null;
                if (resultsArray1 != null) {
                    arr1 = new String[resultsArray1.length()];
                    for (int i = 0; i < resultsArray1.length(); i++)
                        arr1[i] = resultsArray1.getString(i);
                }


                String[] arr2 = null;
                if (resultsArray2 != null) {
                    arr2 = new String[resultsArray2.length()];
                    for (int i = 0; i < resultsArray2.length(); i++)
                        arr2[i] = resultsArray2.getString(i);
                }


                String[] arr3 = null;
                if (resultsArray3 != null) {
                    arr3 = new String[resultsArray3.length()];
                    for (int i = 0; i < resultsArray3.length(); i++)
                        arr3[i] = resultsArray3.getString(i);
                }


                String[] arr4 = null;
                if (resultsArray4 != null) {
                    arr4 = new String[resultsArray4.length()];
                    for (int i = 0; i < resultsArray4.length(); i++)
                        arr4[i] = resultsArray4.getString(i);
                }

                t = new Thesaurus(arr1, arr3, arr2, arr4);
                a.add(t);
            }





            JSONObject jsonResults2 = null;
            if(rootObject.has("verb"))
            jsonResults2 = rootObject.getJSONObject("verb");

            if(jsonResults2!=null) {
                JSONArray resultsArray1 = null;
                if (jsonResults2.has("syn") && jsonResults2.getJSONArray("syn") != null)
                    resultsArray1 = jsonResults2.getJSONArray("syn");

                JSONArray resultsArray2 = null;
                if (jsonResults2.has("ant") && jsonResults2.getJSONArray("ant") != null)
                    resultsArray2 = jsonResults2.getJSONArray("ant");

                JSONArray resultsArray3 = null;
                if (jsonResults2.has("rel") && jsonResults2.getJSONArray("rel") != null)
                    resultsArray3 = jsonResults2.getJSONArray("rel");

                JSONArray resultsArray4 = null;
                if (jsonResults2.has("sim") && jsonResults2.getJSONArray("sim") != null)
                    resultsArray4 = jsonResults2.getJSONArray("sim");

                // Variables for JSON parsing

                String[] arr1 = null;
                if (resultsArray1 != null) {
                    arr1 = new String[resultsArray1.length()];
                    for (int i = 0; i < resultsArray1.length(); i++)
                        arr1[i] = resultsArray1.getString(i);
                }


                String[] arr2 = null;
                if (resultsArray2 != null) {
                    arr2 = new String[resultsArray2.length()];
                    for (int i = 0; i < resultsArray2.length(); i++)
                        arr2[i] = resultsArray2.getString(i);
                }


                String[] arr3 = null;
                if (resultsArray3 != null) {
                    arr3 = new String[resultsArray3.length()];
                    for (int i = 0; i < resultsArray3.length(); i++)
                        arr3[i] = resultsArray3.getString(i);
                }


                String[] arr4 = null;
                if (resultsArray4 != null) {
                    arr4 = new String[resultsArray4.length()];
                    for (int i = 0; i < resultsArray4.length(); i++)
                        arr4[i] = resultsArray4.getString(i);
                }

                t = new Thesaurus(arr1, arr3, arr2, arr4);
                a.add(t);
            }


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing JSON results", e);
        }

        return a;
    }
}


