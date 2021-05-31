import java.awt.Component;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "*.txt", "txt");
        fileChooser.setFileFilter(filter);

        JFrame frame = new JFrame();
        int eCode = fileChooser.showOpenDialog(frame);

        if (eCode != JFileChooser.APPROVE_OPTION) {            
            System.out.println("No file has been chosen; aborting...");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Matrix rows num: ");
        int matrix_rows = scanner.nextInt();

        System.out.print("Matrix cols num: ");
        int matrix_cols = scanner.nextInt();

        scanner.close();

        Matrix m = new Matrix(matrix_rows, matrix_cols);
        String path = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Opening file " + path + "...");

        try {
            m.fillFromFile(path);
        }

        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Logic logic = new Logic();
        System.out.println("=================");

        for (Pair<Integer> pair : logic.getParetoOptimalSituations(m)) {
            System.out.println(pair);
        }

        System.exit(0);
    }
}
