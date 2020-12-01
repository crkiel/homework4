package com.homework4.homework4.Models;

import javax.persistence.*;

@Entity
@Table(name="Weather")
public class Weather
{
    @Id
    @Column(name="id")
    private String id;
    @Column(name="CityName")
    private String name;
    @Column(name="Humidity")
    private String humidity;
    @Column(name="Temp")
    private String temp;
    @Column(name="TempFeel")
    private String tempFeel;
    @Column(name="TempMax")
    private String tempMax;
    @Column(name="TempMin")
    private String tempMin;


    public Weather()
    {

    }

    public Weather( String id,String name,String humidity,
                    String temp, String tempFeel, String tempMax,
                    String tempMin)
    {
        this.id=id;
        this.name=name;
        this.humidity=humidity;
        this.temp=temp;
        this.tempFeel=tempFeel;
        this.tempMax=tempMax;
        this.tempMin=tempMin;

    }


    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getHumidity(){
        return humidity;
    }

    public void setHumidity(String humidity){
        this.humidity=humidity;
    }
    public String getTemp(){
        return temp;
    }
    public void setTemp(String temp) {
        this.temp=temp;
    }
    public String getTempFeel() {
        return tempFeel;
    }
    public void setTempFeel(String tempFeel) {
        this.tempFeel=tempFeel;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }
}
