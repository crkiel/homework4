package com.homework4.homework4.controllers;
import com.homework4.homework4.Models.Weather;
import com.homework4.homework4.Models.WeatherRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MainController
{
    //Repo for weather
    @Autowired
    WeatherRepo weatherRepo;

    //Select City for weather information and save to database
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(@RequestParam("id") String id)
    {
        ModelAndView mv = new ModelAndView("redirect:/");
        String weather = getWeatherById(id);
        Weather weathertosave;
        try
        {   //JSON object is built and displayed
            weathertosave=new Weather();
            JSONObject json = new JSONObject(weather);
            mv.addObject("id",json.getString("id"));
            weathertosave.setId(json.getString("id"));
            mv.addObject("name", json.getString("name"));
            weathertosave.setName(json.getString("name"));
            mv.addObject("humidity", json.getJSONObject("main").get("humidity").toString());
            weathertosave.setHumidity(json.getJSONObject("main").get("humidity").toString());
            mv.addObject("temp", json.getJSONObject("main").get("temp").toString());
            weathertosave.setTemp(json.getJSONObject("main").get("temp").toString());
            mv.addObject("tempFeel", json.getJSONObject("main").get("feels_like").toString());
            weathertosave.setTempFeel(json.getJSONObject("main").get("feels_like").toString());
            mv.addObject("tempMax", json.getJSONObject("main").get("temp_max").toString());
            weathertosave.setTempMax(json.getJSONObject("main").get("temp_max").toString());
            mv.addObject("tempMin", json.getJSONObject("main").get("temp_min").toString());
            weathertosave.setTempMin(json.getJSONObject("main").get("temp_min").toString());
            weatherRepo.save(weathertosave);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(weatherRepo.findAll());
        return mv;
    }

    //Load Data into table on the web application to display
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ModelAndView load()
    {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("weatherlist", weatherRepo.findAll());
        System.out.println(weatherRepo.findAll());
        return mv;
    }

    //Delete an entry from the loaded table
    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteWeather(@PathVariable("id") String id)
    {
        ModelAndView mv = new ModelAndView("redirect:/");
        weatherRepo.deleteById(id);
        return mv;
    }


    //Request is made and JSON data is put in a string
    private String getWeatherById(String id) {
        try {
            String Key = "61c2f73c5d34c1717a334ba972a49063";
            //change to fahrenheit https://openweathermap.force.com/s/article/switching-between-temperature-units-2019-10-24-21-47-24#:~:text=Information&text=Body-,OpenWeatherMap%20website%20provides%20mesaurements%20in%20three%20units%3A%20Kelvins,a%20default%2C%20Fahrenheit%20and%20Celsius.
            URL ForGet = new URL("http://api.openweathermap.org/data/2.5/weather?id="+ id +"&units=imperial&appid="+Key);

            HttpURLConnection connection = (HttpURLConnection) ForGet.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response.toString();
            } else {
                return "Unexpected HTTP response";
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }
}







