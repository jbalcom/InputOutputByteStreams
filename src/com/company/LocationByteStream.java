package com.company;


import java.util.LinkedHashMap;
import java.util.Map;

public class LocationByteStream {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public LocationByteStream(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if (exits != null) {
            this.exits = new LinkedHashMap<String, Integer>(exits);
        }else {
            this.exits = new LinkedHashMap<String, Integer>();
        }
        this.exits.put("Q", 0);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<String, Integer>(exits);
    }

    protected void addExits(String direction, int location) {
        exits.put(direction, location);
    }
}