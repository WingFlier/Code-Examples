 public class PostHandler extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {


            String urlParams = null;
            DataOutputStream stream = null;
            URL url = null;
            try
            {
                url = new URL("http://httpbin.org/post");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                urlParams = "num=123456";
                connection.setRequestMethod("POST");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5 ");
                connection.setDoOutput(true);
                stream = new DataOutputStream(connection.getOutputStream());
                int responseCode = connection.getResponseCode();

                String resp = convertStreamToString(connection);
                Log.d("logging_tag", resp);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (ProtocolException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    private String convertStreamToString(HttpURLConnection connection) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append('\n');
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }