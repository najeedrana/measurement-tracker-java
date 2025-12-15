import dao.MeasurementDao;
import ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // DB file will be created in project folder
            MeasurementDao dao = new MeasurementDao("measurements.db");
            MainFrame frame = new MainFrame(dao);
            frame.setVisible(true);
        });
    }
}
