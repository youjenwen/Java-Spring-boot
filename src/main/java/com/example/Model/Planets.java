package com.example.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//這件實體類別
@Document(collection = "planets")
public class Planets {
    @Id
    private  String id;
    private String name;
    private int orderFromSun;
    private boolean hasRings;
    private String[] mainAtmosphere;

    private SurfaceTemperatureC surfaceTemperatureC;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderFromSun() {
        return orderFromSun;
    }

    public void setOrderFromSun(int orderFromSun) {
        this.orderFromSun = orderFromSun;
    }

    public boolean isHasRings() {
        return hasRings;
    }

    public void setHasRings(boolean hasRings) {
        this.hasRings = hasRings;
    }

    public String[] getMainAtmosphere() {
        return mainAtmosphere;
    }

    public void setMainAtmosphere(String[] mainAtmosphere) {
        this.mainAtmosphere = mainAtmosphere;
    }

    public SurfaceTemperatureC getSurfaceTemperatureC() {
        return surfaceTemperatureC;
    }

    public void setSurfaceTemperatureC(SurfaceTemperatureC surfaceTemperatureC) {
        this.surfaceTemperatureC = surfaceTemperatureC;
    }
}