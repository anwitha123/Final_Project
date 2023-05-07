package com.weatherstack.app;
//package com.example.weatherapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.http.client.ClientProtocolException;

public class WeatherAppGUI {

    private JFrame frame;
    private JTextField locationField;
    private JLabel inputCity;
    private JLabel temperatureLabel;
    private JLabel temperatureLabel2;

    public WeatherAppGUI() throws ClientProtocolException, IOException  {
        // Create the main window
        frame = new JFrame("Weather App");
        frame.setSize(600, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create the location field
        inputCity = new JLabel("City");
        frame.add(inputCity);
        locationField = new JTextField("Enter location here");
        locationField.setColumns(50);
        frame.add(locationField);

        // Create the temperature label
        temperatureLabel = new JLabel("Temperature: ");
        frame.add(temperatureLabel);
        
        temperatureLabel2 = new JLabel("");
        frame.add(temperatureLabel2);
        
        // Create the button to trigger the weather API call
        JButton getWeatherButton = new JButton("Get Weather");
        getWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    // When the button is clicked, call the weather API and update the temperature label
                    String location = locationField.getText();
                    WeatherApp weatherApp = new WeatherApp(location);
                    System.out.println(weatherApp);
                    temperatureLabel2.setText(weatherApp.toString());
                } catch (java.net.UnknownHostException ex) {
                	temperatureLabel2.setText("No Network");
                    ex.printStackTrace();
                }
            	catch ( NullPointerException ex) {
                	temperatureLabel2.setText("City not found");
                    ex.printStackTrace();
                }
            	catch ( ClientProtocolException ex) {
                	temperatureLabel2.setText(ex.toString());
                    ex.printStackTrace();
                } catch (IOException ex) {
                	temperatureLabel2.setText(ex.toString());
                    ex.printStackTrace();
                }
            }   });
        //set icon image
        BufferedImage icon = ImageIO.read(new File("C:\\Users\\SUBRATO\\eclipse-workspace\\WeatherStackApp\\src\\com\\weatherstack\\app\\icon.png"));
        frame.setIconImage(icon);


        frame.add(getWeatherButton);

        // Show the window
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        // Create the GUI
        new WeatherAppGUI();
    }
}
