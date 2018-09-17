import java.util.*;

public class Logic {

    public Boolean dominationCheck(Pair<Double> sitA, Pair<Double> sitB) {
        return (sitA.getX() > sitB.getX() && sitA.getY() >= sitB.getY()) ||
                (sitA.getX() >= sitB.getX() && sitA.getY() > sitB.getY());
    }

    public List<Pair<Integer>> getParetoOptimalSituations(Matrix winFuncMatrix) {

        // activeSituations holds boolean as a value of some matrix summary index
        // corresponding to the elements in winFuncMatrix.
        // Summary index of matrix is (i*cols+j)
        Map<Integer, Boolean> activeSituations = new HashMap<>();
        int rows = winFuncMatrix.getRows();
        int cols = winFuncMatrix.getCols();

        for (int i = 0; i < rows*cols; i++) {
            activeSituations.put(i, true);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                Pair<Double> inspecting = winFuncMatrix.getElement(i, j);
                int s = 0;

                for (; s < rows*cols; s++) {

                    Pair<Double> another = winFuncMatrix.getElement(s);

                    if (dominationCheck(inspecting, another)) {
                        activeSituations.put(s, false);
                    }

                    if (dominationCheck(another, inspecting)) {
                        activeSituations.put(i*cols + j, false);
                    }
                }
            }
        }

        List<Pair<Integer>> result = new ArrayList<>();

        for (Integer sumIndex : activeSituations.keySet()) {
            if (activeSituations.get(sumIndex) == true) {
                result.add(new Pair<Integer>(sumIndex/cols, sumIndex%cols));
            }
        }

        return result;
    }
}
