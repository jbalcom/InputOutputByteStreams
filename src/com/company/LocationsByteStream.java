package com.company;

import java.io.*;
import java.util.*;

public class LocationsByteStream implements Map<Integer, LocationByteStream> {
    private static Map<Integer, LocationByteStream> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{
       try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
           for (LocationByteStream location : locations.values()){
               locFile.writeObject(location);
           }
       }

    }

    static {
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    LocationByteStream location = (LocationByteStream) locFile.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExits().size() + " exits");

                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        }catch (InvalidClassException e){
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO Exception " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public LocationByteStream get(Object key) {
        return locations.get(key);
    }

    @Override
    public LocationByteStream put(Integer key, LocationByteStream value) {
        return locations.put(key, value);
    }

    @Override
    public LocationByteStream remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends LocationByteStream> map) {
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<LocationByteStream> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, LocationByteStream>> entrySet() {
        return locations.entrySet();
    }
}
