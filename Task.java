import java.util.Date;

// Task Entity
public class Task {
    private int number;
    private String description;
    private int status;
    private Date deadline;

    // constructor
    public Task(int number, String description, int status, Date deadline) {
        this.number = number;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    // All getters and setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
