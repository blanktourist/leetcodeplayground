package main.java;

import java.util.ArrayList;
import java.util.List;

// Array based implementation. Stack/Deque/Doubly-linked list/etc are also possible
public class BrowserHistory {
    
    private List<String> urls = new ArrayList<>();      // Array used to maintain the history
    private int index = -1;                             // The index of the page the user is currently on
    private int lastIndex = -1;                         // The index of the last page in the history
    
    public BrowserHistory(String homepage) {
        visit(homepage);
    }

    public void visit(String url) {
        index++;
        if (index < urls.size()) {
            urls.set(index, url);
        } else {
            urls.add(url);
        }
        lastIndex = index;
    }

    public String back(int steps) {
        index = Math.max(0, index - steps);
        return urls.get(index);
    }

    public String forward(int steps) {
        index = Math.min(lastIndex, index + steps);
        return urls.get(index);
    }
}