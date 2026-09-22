package com.carwash.ui;

import com.carwash.service.ReportService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class ReportsPanel extends JPanel {

    private final ReportService reportService = new ReportService();
    private final JLabel todayLabel = new JLabel("R0.00");
    private final JLabel weekLabel = new JLabel("R0.00");

    private final DefaultTableModel dailyModel = new DefaultTableModel(
            new Object[]{"Date", "Revenue"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final DefaultTableModel topCustomerModel = new DefaultTableModel(
            new Object[]{"Customer", "Total Spend", "Visits"}, 0) {
        @Override public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    public ReportsPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel summary = new JPanel(new GridLayout(1, 2, 16, 0));

        summary.setBorder(BorderFactory.createTitledBorder("Summary"));
        summary.add(labeledBox("Revenue Today", todayLabel));
        summary.add(labeledBox("Revenue This Week", weekLabel));
        add(summary, BorderLayout.NORTH);

        JPanel tables = new JPanel(new GridLayout(1, 2, 8, 8));

        JPanel dailyPanel = new JPanel(new BorderLayout());

        dailyPanel.setBorder(BorderFactory.createTitledBorder("Revenue by Day (last 14 days)"));
        dailyPanel.add(new JScrollPane(new JTable(dailyModel)), BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setBorder(BorderFactory.createTitledBorder("Top Customers"));
        topPanel.add(new JScrollPane(new JTable(topCustomerModel)), BorderLayout.CENTER);

        tables.add(dailyPanel);
        tables.add(topPanel);
        add(tables, BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refresh());

        JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
        south.add(refreshBtn);
        add(south, BorderLayout.SOUTH);

        refresh();
    }

    private JPanel labeledBox(String title, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(new JLabel(title), BorderLayout.NORTH);

        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD, 20F));

        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    public void refresh() {
        try {
            todayLabel.setText("R" + reportService.revenueToday());
            weekLabel.setText("R" + reportService.revenueThisWeek());

            dailyModel.setRowCount(0);
            for (Object[] row : reportService.revenueByDay(14)) {
                dailyModel.addRow(new Object[]{
                        row[0], "R" + row[1]}
                );
            }

            topCustomerModel.setRowCount(0);
            for (Object[] row : reportService.topCustomers(10)) {
                topCustomerModel.addRow(new Object[]{
                        row[0], "R" + row[1], row[2]}
                );
            }
        } catch (SQLException ex) {
            showDbError(ex);
        }
    }

    private void showDbError(SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
