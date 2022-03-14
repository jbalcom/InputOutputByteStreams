package com.company;

import java.io.*;
import java.util.*;

public class LocationsByteStream implements Map<Integer, LocationByteStream> {
    private static Map<Integer, LocationByteStream> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{
        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
            for (LocationByteStream location : locations.values()){
                locFile.writeInt(location.getLocationID());
                locFile.writeUTF(location.getDescription());
                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
                locFile.writeInt(location.getExits().size() - 1);
                for (String direction : location.getExits().keySet()){
                    if (!direction.equalsIgnoreCase("Q")){
                        System.out.println("\t\t" + direction + ", " + location.getExits().get(direction));
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
                    }
                }
            }
        }
    }

    static {
        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while (!eof){
                try {
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    int locID = locFile.readInt();
                    String description = locFile.readUTF();
                    int numExits = locFile.readInt();
                    System.out.println("Read location " + locID + " : " + description);
                    System.out.println("Found " + numExits + " exits.");
                    for (int i = 0; i < numExits; i++){
                        String direction = locFile.readUTF();
                        int destination = locFile.readInt();
                        exits.put(direction, destination);
                        System.out.println("\t\t" + direction + ", " + destination);
                    }
                    locations.put(locID, new LocationByteStream(locID, description, exits));
                }catch (EOFException e){
                    eof = true;
                }
            }
        }catch (IOException e){
            System.out.println("IO Exception");
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