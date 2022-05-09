package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileSystem {
    
    private class Directory {
        HashMap<String, Directory> subDirectories;
        HashMap<String, String> files;

        public Directory() {
            subDirectories = new HashMap<>();
            files = new HashMap<>();
        }
    }

    Directory root;

    public FileSystem() {
        root = new Directory();
    }

    public List<String> ls(String path) {
        Directory pointer = root; // pointer used for traversal
        List<String> outputs = new ArrayList<>();

        if (!path.equals("/")) {
            String[] pathSplit = path.split("/");
            for (int i = 1; i < pathSplit.length - 1; i++) {
                pointer = pointer.subDirectories.get(pathSplit[i]);
            }
            
            // Last component of pathSplit represents a file
            if (pointer.files.containsKey(pathSplit[pathSplit.length - 1])) {
                outputs.add(pointer.files.get(pathSplit[pathSplit.length - 1]));
                return outputs;
            // Otherwise last component of pathSplit represents a directory
            } else { 
                pointer = pointer.subDirectories.get(pathSplit[pathSplit.length - 1]);;
            }
        }

        outputs.addAll(new ArrayList<String>(pointer.subDirectories.keySet()));
        outputs.addAll(new ArrayList<String>(pointer.files.keySet()));
        Collections.sort(outputs);
        return outputs;
    }

    public void mkdir(String path) {
        Directory pointer = root;
        String[] pathSplit = path.split("/");
        for (int i = 1; i < pathSplit.length; i++) {

            // If the directory doesn't exist, create it
            if (!pointer.subDirectories.containsKey(pathSplit[i])) {
                pointer.subDirectories.put(pathSplit[i], new Directory());
            }

            pointer = pointer.subDirectories.get(pathSplit[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Directory pointer = root;
        String[] pathSplit = filePath.split("/");
        for (int i = 1; i < pathSplit.length - 1; i++) {
            pointer = pointer.subDirectories.get(pathSplit[i]);
        }
        pointer.files.put(pathSplit[pathSplit.length - 1], pointer.files.getOrDefault(pathSplit[pathSplit.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
        Directory pointer = root;
        String[] pathSplit = filePath.split("/");
        for (int i = 1; i < pathSplit.length - 1; i++) {
            pointer = pointer.subDirectories.get(pathSplit[i]);
        }
        return pointer.files.get(pathSplit[pathSplit.length - 1]);
    }
}