package grapher;

import java.util.ArrayList;
import java.util.List;

//Used to store data in the graph
public class DataList {
    private List<Double> data;

    public DataList() {
        data = new ArrayList<>();
    }

    public List<Double> getData() {
        return data;
    }

    public void add(double value) {
        data.add(value);
    }
}
