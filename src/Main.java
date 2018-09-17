public class Main {

    public static void main(String[] args) {

        Matrix m = new Matrix(11 ,11);
        Logic logic = new Logic();

        try {
            m.fillFromFile("data/cournot.txt");
        }

        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        for (Pair<Integer> pair : logic.getParetoOptimalSituations(m)) {
            System.out.println(pair);
        }
    }
}
