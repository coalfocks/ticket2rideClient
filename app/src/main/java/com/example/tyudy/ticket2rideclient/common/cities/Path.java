package com.example.tyudy.ticket2rideclient.common.cities;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by Trevor on 3/8/2017.
 */

public class Path implements Serializable {

    private ColorENUM pathColor;
    private int distance;
    private ArrayList<City> connectedCities;
    private User owner;
    private String name;

    public Path(ColorENUM c, int dist, City city1, City city2, String n) {
        pathColor = c;
        distance = dist;
        connectedCities = new ArrayList<>();
        connectedCities.add(city1);
        connectedCities.add(city2);
        owner = null;
        name = n;
    }

    public void setOwner(User p) { owner = p; }
    public User getOwner() { return owner; }
    public ArrayList<City> getCities() { return connectedCities; }
    public int getPoints() {
        switch (distance) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            default:
                return 1000; // Hidden bug :grin:
        }
    }

    public int getDistance(){
        return distance;
    }

    public ColorENUM getPathColor(){
        return pathColor;
    }

    /**
     * A method to find if a path contains the given city
     * @param city The desired city
     * @return True if path is connected to city, false otherwise
     */
    public boolean containsCity(City city) {

        HashSet<City> cities = new HashSet<>(connectedCities);
        return cities.contains(city);
    }

    /**
     * Check if the path has an owner
     * @return - true if the path is owned false if not
     */
    public boolean hasOwner(){
        if(owner != null){
            return true;
        } else {
            return false;
        }
    }
    
    public String getName() {
        return this.name;
    }
}
