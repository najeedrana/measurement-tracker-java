package ui;

import dao.MeasurementDao;
import model.Measurement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MainFrame extends JFrame {

    private final MeasurementDao dao;

    private JTextField txtDate;
    private JTextField txtWeight;
    private JTextField txtWaist;
    private JTextField txtChest;
    private JTextArea txtNotes;
    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame(MeasurementDao dao) {
        this.dao = dao;
        setTitle("Measurement Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        initComponents();
        loadTableData();
    }

    private void initComponents() {
        // top form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        txtDate = new JTextField(LocalDate.now().toString()); // yyyy-MM-dd
        txtWeight = new JTextField();
        txtWaist = new JTextField();
        txtChest = new JTextField();
        txtNotes = new JTextArea(3, 20);

        formPanel.add(new JLabel("Date (yyyy-mm-dd):"));
        formPanel.add(txtDate);

        formPanel.add(new JLabel("Weight (kg):"));
        formPanel.add(txtWeight);

        formPanel.add(new JLabel("Waist (cm):"));
        formPanel.add(txtWaist);

        formPanel.add(new JLabel("Chest (cm):"));
        formPanel.add(txtChest);

        formPanel.add(new JLabel("Notes:"));
        formPanel.add(new JScrollPane(txtNotes));

        JButton btnAdd = new JButton("Add measurement");
        btnAdd.addActionListener(e -> onAddClicked());

        // table for data
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Date", "Weight", "Waist", "Chest", "Notes"}, 0
        );
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(btnAdd, BorderLayout.EAST);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
    }

    private void onAddClicked() {
        try {
            String date = txtDate.getText().trim();
            double weight = Double.parseDouble(txtWeight.getText().trim());
            double waist = Double.parseDouble(txtWaist.getText().trim());
            double chest = Double.parseDouble(txtChest.getText().trim());
            String notes = txtNotes.getText().trim();

            Measurement m = new Measurement(date, weight, waist, chest, notes);
            dao.addMeasurement(m);
            loadTableData();
            clearInputs();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for weight, waist and chest.",
                    "Input error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        List<Measurement> list = dao.getAll();
        tableModel.setRowCount(0); // clear

        for (Measurement m : list) {
            tableModel.addRow(new Object[]{
                    m.getId(),
                    m.getDate(),
                    m.getWeight(),
                    m.getWaist(),
                    m.getChest(),
                    m.getNotes()
            });
        }
    }

    private void clearInputs() {
        txtWeight.setText("");
        txtWaist.setText("");
        txtChest.setText("");
        txtNotes.setText("");
        txtDate.setText(LocalDate.now().toString());
    }
}
