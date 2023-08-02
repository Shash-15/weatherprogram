import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast {
    private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Get weather");
                System.out.println("2. Get Wind Speed");
                System.out.println("3. Get Pressure");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter the date (YYYY-MM-DD format): ");
                        String dateWeather = br.readLine();
                        double temperature = getTemperature(dateWeather);
                        if (temperature != Double.MIN_VALUE) {
                            System.out.println("Temperature on " + dateWeather + ": " + temperature + " Kelvin\n");
                        } else {
                            System.out.println("No data available for the input date.\n");
                        }
                        break;

                    case 2:
                        System.out.print("Enter the date (YYYY-MM-DD format): ");
                        String dateWindSpeed = br.readLine();
                        double windSpeed = getWindSpeed(dateWindSpeed);
                        if (windSpeed != Double.MIN_VALUE) {
                            System.out.println("Wind Speed on " + dateWindSpeed + ": " + windSpeed + " m/s\n");
                        } else {
                            System.out.println("No data available for the input date.\n");
                        }
                        break;

                    case 3:
                        System.out.print("Enter the date (YYYY-MM-DD format): ");
                        String datePressure = br.readLine();
                        double pressure = getPressure(datePressure);
                        if (pressure != Double.MIN_VALUE) {
                            System.out.println("Pressure on " + datePressure + ": " + pressure + " hPa\n");
                        } else {
                            System.out.println("No data available for the input date.\n");
                        }
                        break;

                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fetchDataFromAPI() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            return response.toString();
        } else {
            throw new IOException("Error fetching data from the API. Response Code: " + responseCode);
        }
    }

    private static double getTemperature(String date) throws IOException {
        String jsonData = fetchDataFromAPI();
        // Parse the JSON data and retrieve the temperature for the given date
        // Implement JSON parsing logic here and return the temperature
        return Double.MIN_VALUE; // Placeholder value, replace with the actual temperature value
    }

    private static double getWindSpeed(String date) throws IOException {
        String jsonData = fetchDataFromAPI();
        // Parse the JSON data and retrieve the wind speed for the given date
        // Implement JSON parsing logic here and return the wind speed
        return Double.MIN_VALUE; // Placeholder value, replace with the actual wind speed value
    }

    private static double getPressure(String date) throws IOException {
        String jsonData = fetchDataFromAPI();
        // Parse the JSON data and retrieve the pressure for the given date
        // Implement JSON parsing logic here and return the pressure
        return Double.MIN_VALUE; // Placeholder value, replace with the actual pressure value
    }
}
