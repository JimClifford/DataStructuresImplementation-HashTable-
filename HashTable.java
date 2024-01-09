// Class representing a HashTable with basic operations
public class HashTable {
    // Fields to store the capacity, array for entries, and current size
    int capacity;
    Entry[] table;
    int size;

    // Constructor to initialize the hashtable with a given initial capacity
    public HashTable(int initialCapacity) {
        capacity = initialCapacity;
        table = new Entry[capacity];
        size = 0;
    }

    // Getter method to retrieve the current size of the hashtable
    public int tableLength() {
        return this.size;
    }

    // Method to check if the hashtable is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Primary hash function using the ID of an entry
    public int primaryHash(Entry entry) {
        int key = entry.getID();
        return key % this.capacity;
    }

    // Secondary hash function using the ID of an entry
    public int secondaryHash(Entry entry) {
        int key = entry.getID();
        // Must always return non-zero and different from primaryHash to avoid an infinite loop
        int prime = 7; // If initial capacity is greater than 10
        return (prime - (key % prime));
    }

    // Secondary hash function using an integer key
    // Used for getting a new hash to be used as a step size if an index is occupied
    public int secondaryHash(int key) {
        // Must always return non-zero and different from primaryHash to avoid an infinite loop
        int prime = 7; // If initial capacity is greater than 10
        return (prime - (key % prime));
    }

    // Method to insert an entry into the hashtable
    public String insert(Entry entry) {
        int key = entry.getID();
        String value = entry.getGrade();

        if (tableLength() == capacity) {
            return "Hash table is full";
        }

        int newIndex = -1;
        int primaryIndex = this.primaryHash(entry);
        int secondaryIndex = this.secondaryHash(entry);

        int i = 0;
        while (i < capacity) {
            int index = (primaryIndex + i * secondaryIndex) % capacity;
            if (this.table[index] == null) {
                newIndex = index;
                break;
            } else if (this.table[index].ID == key) {
                this.table[index].grade = value; // Updating the value for an existing key
                return "Value updated";
            }
            i += secondaryHash(key); // Step size for the next iteration
        }

        if (newIndex != -1) {
            this.table[newIndex] = entry;
            size++;
            return "Value inserted";
        } else {
            return "Hash table is full";
        }
    }

    // Method to delete an entry from the hashtable
    public String deleteEntry(Entry entry) {
        int key = entry.getID();
        int primaryIndex = this.primaryHash(entry);
        int secondaryIndex = this.secondaryHash(entry);

        int i = 0;
        while (i < capacity) {
            int index = (primaryIndex + i * secondaryIndex) % capacity;
            if ((this.table[index] != null) && (this.table[index].ID == key)) {
                table[index] = null;
                size--;
                return "Key/Entry deleted";
            }
            i += secondaryHash(key); // Step size for the next iteration
        }
        return "Key/Entry not found";
    }

    // Method to search for an entry in the hashtable
    public void search(Entry entry) {
        int x = findEntryIndex(entry);
        if (x == -1) {
            System.out.println("The entry was not found");
        } else {
            System.out.println("The entry was found at index: " + x);
        }
    }

    // Method to find the index of an entry in the hashtable
    public int findEntryIndex(Entry entry) {
        int key = entry.getID();
        for (int i = 0; i < capacity; i++) {
            // Check if the entry at the calculated index matches the specified entry
            if (this.table[i] != null && this.table[i].getID() == key) {
                // Entry found at this index
                return i;
            }
        }
        // Entry not found in the entire hashtable
        return -1;
    }

    // Method to display the contents of the hashtable
    public void displayTable() {
        System.out.println("Hash Table Contents:");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Index: " + i + ", Key: " + this.table[i].getID() + ", Value: " + this.table[i].getGrade());
            } else {
                System.out.println("Index: " + i + ", Empty");
            }
        }
    }

    // Main method for testing the HashTable
    public static void main(String[] args) {
        // Creating a HashTable with an initial capacity of 11
        HashTable hashTable = new HashTable(11);

        // Creating entries to insert into the hashtable
        Entry entry1 = new Entry(221, "A");
        Entry entry2 = new Entry(472, "B");
        Entry entry3 = new Entry(893, "C");
        Entry entry4 = new Entry(321, "A");
        Entry entry5 = new Entry(105, "B+");
        Entry entry6 = new Entry(391, "C+");
        Entry entry7 = new Entry(111, "A");
        Entry entry8 = new Entry(897, "B");
        Entry entry9 = new Entry(438, "C");
        Entry entry10 = new Entry(171, "D+");
        Entry entry11 = new Entry(999, "B");
        Entry entry12 = new Entry(666, "E");

        // Inserting entries into the hashtable
        hashTable.insert(entry1);
        hashTable.insert(entry2);
        hashTable.insert(entry3);
        hashTable.insert(entry4);
        hashTable.insert(entry5);
        hashTable.insert(entry6);

        // Displaying the hashtable contents after the initial insertions
        hashTable.displayTable();

        // Inserting more entries into the hashtable
        hashTable.insert(entry7);
        hashTable.insert(entry8);
        hashTable.insert(entry9);
        hashTable.insert(entry10);
        hashTable.insert(entry11);
        hashTable.insert(entry12);

        // Displaying the hashtable contents after additional insertions
        hashTable.displayTable();

        // Deleting an entry and displaying the updated hashtable
        hashTable.deleteEntry(entry4);
        hashTable.displayTable();

        // Deleting another entry and printing the result
        String y = hashTable.deleteEntry(entry10);
        System.out.println(y);

        // Getting the current size of the hashtable
        int x = hashTable.tableLength();
        System.out.println(x);

        // Searching for an entry in the hashtable
        hashTable.search(entry12);
    }
}


