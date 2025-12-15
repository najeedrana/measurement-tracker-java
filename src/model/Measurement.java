package model;

public class Measurement {
    private int id;
    private String date;
    private double weight;
    private double waist;
    private double chest;
    private String notes;

    public Measurement() {
    }

    public Measurement(int id, String date, double weight, double waist, double chest, String notes) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.waist = waist;
        this.chest = chest;
        this.notes = notes;
    }

    public Measurement(String date, double weight, double waist, double chest, String notes) {
        this(0, date, weight, waist, chest, notes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
