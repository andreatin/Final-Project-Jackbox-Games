package hangman.Dictionary;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

import hangman.List.ListInterface;
import hangman.List.MyLinkedList;

public class MyHashtable implements DictionaryInterface {

    protected int tableSize;
    protected int size;
    // The LinkedList is of type Entry
    protected MyLinkedList[] table;

    public MyHashtable(int tableSize) {
        table = new MyLinkedList[tableSize];
        this.tableSize = tableSize;
    }

    public int biggestBucket()
    {
        int biggestBucket = 0;
        for(int i = 0; i < table.length; i++) {
            // Loop through the table looking for non-null locations.
            if (table[i] != null) {
                // If you find a non-null location, compare the bucket size against the largest
                // bucket size found so far. If the current bucket size is bigger, set biggestBucket
                // to this new size.
                MyLinkedList bucket = table[i];
                if (biggestBucket < bucket.size())
                    biggestBucket = bucket.size();
            }
        }
        return biggestBucket; // Return the size of the biggest bucket found.
    }

    // Returns the average bucket length. Gives a sense of how many collisions are happening overall.
    public float averageBucket() {
        float bucketCount = 0; // Number of buckets (non-null table locations)
        float bucketSizeSum = 0; // Sum of the size of all buckets
        for(int i = 0; i < table.length; i++) {
            // Loop through the table
            if (table[i] != null) {
                // For a non-null location, increment the bucketCount and add to the bucketSizeSum
                MyLinkedList bucket = table[i];
                bucketSizeSum += bucket.size();
                bucketCount++;
            }
        }

        // Divide bucketSizeSum by the number of buckets to get an average bucket length.
        return bucketSizeSum/bucketCount;
    }

    public String toString()
    {
        String s = "";
        for(int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
            if (table[tableIndex] != null) {
                MyLinkedList bucket = table[tableIndex];
                for(int listIndex = 0; listIndex < bucket.size(); listIndex++) {
                    Entry e = (Entry)bucket.get(listIndex);
                    s = s + "key: " + e.key + ", value: " + e.word + "\n";
                }
            }
        }
        return s;
    }

    protected class Entry
    {
        int key;
        Object word;

        Entry(int key, Object word) {
            this.key = key;
            this.word = word;
        }
    }

    // Implement these methods
    public boolean isEmpty() 
    {
        if(size == 0)
        {
            return true;
        }
        else 
        {
            return false;
        }
    } // returns true if the Dictionary is empty, false otherwise.

    public int size()
    {
        int tableNum = 0;
        for(int i = 0; i<tableSize; i++)
        {
            tableNum += size;
        }
       
        return tableNum;
    } //Returns the number of key/value pairs stored in the dictionary.

    // Adds a value stored under the given key. If the key has already been stored in the Dictionary,
    // replaces the value associated with the key and returns the old value. If the key isn't in the dictionary
    
    public Object put(int key, Object word)
    {
        // 1. Compute an array index given the key
        int arrayIndex = Math.abs(key) % tableSize;

        // 2. If that location in the table is null,
        // that means nothing has been previously stored using a key with this hash code.

        if(table[arrayIndex] == null)
        {
            MyLinkedList bucket = new MyLinkedList();
            Entry entry = new Entry(key,word);
            bucket.add(0, entry);//adding key/value pair to linkedlist 
            table[arrayIndex] = bucket; //adding linkedlist to the table 
            size++; //number of key/value pairs increments
        }
        else if(table[arrayIndex] != null)//value exists for the key
        {
            for(int i=0; i < table[arrayIndex].size(); i++)
            {
                Entry bucketE = (Entry) table[arrayIndex].get(i);
                if(bucketE.key == key)
                {
                    Entry oldValue = new Entry(key, bucketE.word);
                    bucketE.word = word;
                    return oldValue.word;
                }
            }

            Entry entry = new Entry(key, word);
            table[arrayIndex].add(0, entry);
            size++;

            return null;
        }
        return null;
    }

    public Object get(int key)
    {
        int arrayIndex = Math.abs(key) % tableSize;
        MyLinkedList bucket = new MyLinkedList();
        
  
        if(table[arrayIndex] == null)
        {
            return null;
        }

        for(int i=0; i<table[arrayIndex].size(); i++)
        {
            Entry entry = (Entry) table[arrayIndex].get(i);
            if(entry.key == key)
            {
                return entry.word;
            }
        }

        return null;
    } // Retrieves the value stored with the key.

    public void remove(int key)
    {
        int arrayIndex = Math.abs(key) % tableSize;
       if(table[arrayIndex] == null)
       {}
       else   
        {
            for(int i = 0; i<table[arrayIndex].size(); i++)
            {
                Entry entry = (Entry) table[arrayIndex].get(i);
                if(entry.key == key)
                {
                    table[arrayIndex].remove(i);
                    size--;
                }
            }
        }
    } // Deletes the key/value pair stored with the given key.

    public void clear()
    {
        table = null;
    } // Empties the dictionary.

    public int[] getKeys(){
        // 1. Create an int[] with a size equal to the number of unique keys in the hashtable
        int[] unique = new int[size];
        int keyCount =0;
        // 2. Iterate through the hashtable array.
        
        for(int i = 0; i< table.length; i++)
        {
            //for each table location that isn't null
            if(table[i] != null)
            {
                // a. Iterate though the bucket (linked list)

                    // getting the key out of each Entry and storing it in
                    // the array of  ints you created in step 1.
                for(int j = 0; j<table[i].size(); j++)
                {
                    Entry TableEntry = (Entry) table[i].get(j);
                    unique[keyCount] = TableEntry.key;
                    keyCount++;
                }
            }
        }

        return unique;
    }

}
