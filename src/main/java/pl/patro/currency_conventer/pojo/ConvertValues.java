package pl.patro.currency_conventer.pojo;

public class ConvertValues {

    private double from;
    private double to;

    public ConvertValues(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public ConvertValues() {
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }
}
