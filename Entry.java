// Class representing an entry in a hashtable
public class Entry {
    // Fields to store the ID and grade of the entry
    int ID;
    String grade;

    // Constructor to initialize the entry with an ID and grade
    public Entry(int id, String grade) {
        ID = id;
        this.grade = grade;
    }

    // Getter method to retrieve the ID of the entry
    public int getID() {
        return ID;
    }

    // Getter method to retrieve the grade of the entry
    public String getGrade() {
        return this.grade;
    }

    // Setter method to update the ID of the entry
    public void setID(int id) {
        this.ID = id;
    }

    // Setter method to update the grade of the entry
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
