import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Matrix {

    private int rows;
    private int cols;
    private List<List<Pair<Double>>> data = new ArrayList<>();

    public Matrix(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < cols; i++) {
            data.add(new ArrayList<>());
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private List<List<Pair<Double>>> getData() {
        return data;
    }

    public Pair<Double> getElement(int i, int j) {
        return getData().get(i).get(j);
    }

    public Pair<Double> getElement(Pair<Integer> indices) {
        return getData().get(indices.getX()).get(indices.getY());
    }

    public Pair<Double> getElement(int s) {
        return getData().get(s/cols).get(s%cols);
    }


    public void fillFromFile(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        int lineNum = 0;

        for (String line : lines) {

            String[] lineElements = line.split("\\s+");

            for (int i = 0; i < lineElements.length; i++) {

                String element = lineElements[i];

                if (!element.matches("^\\(\\d+(\\.\\d+)?,\\d+(\\.\\d+)?\\)$")) {

                    System.err.println("\"" + element + "\" is not recognized; try to mark elements as \"(value,value)\"");
                    return;
                }

                else {

                    Double valX, valY;
                    String[] values = element.split(",");

                    // using substring() for ignoring parenthesis at the beginning and at the end
                    valX = Double.parseDouble(values[0].substring(1));
                    valY = Double.parseDouble(values[1].substring(0, values[1].length()-1));
                    Pair pair = new Pair(valX, valY);

                    //System.out.println(pair);
                    this.data.get(lineNum).add(pair);
                }
            }

            lineNum++;
        }
    }
}
