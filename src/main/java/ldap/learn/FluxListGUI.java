package ldap.learn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FluxListGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTable table1, table2;

    public FluxListGUI(ArrayList<Flux> fluxList1, ArrayList<Flux> fluxList2 , ArrayList<Flux> missingInFluxList1 ,ArrayList<Flux> missingInFluxList2 ) {
        super("Liste de flux");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du tableau pour les flux du MFC
        String[] columns1 = {"Message", "Émetteur", "Récepteur"};
        Object[][] data1 = new Object[fluxList1.size()][3];
        for (int i = 0; i < fluxList1.size(); i++) {
            Flux flux = fluxList1.get(i);
            data1[i][0] = flux.getMessage();
            data1[i][1] = flux.getEmetteur();
            data1[i][2] = flux.getRecepteur();
        }
        table1 = new JTable(data1, columns1);

        // Ajout du tableau pour les flux du MFC à un JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setPreferredSize(new Dimension(580, 380));
        scrollPane1.setBorder(new TitledBorder("Les flux du MFC"));

        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Flux flux = fluxList1.get(row);
                if (missingInFluxList1.contains(flux)) { // check if the current flux is in missingInFluxList2
                    c.setBackground(Color.RED); // set background color to red
                } else {
                    c.setBackground(Color.green); // set background color to default
                }
                return c;
            }
        });



        // Création du tableau pour les flux du BPMN
        String[] columns2 = {"Message", "Émetteur", "Récepteur"};
        Object[][] data2 = new Object[fluxList2.size()][3];
        for (int i = 0; i < fluxList2.size(); i++) {
            Flux flux = fluxList2.get(i);
            data2[i][0] = flux.getMessage();
            data2[i][1] = flux.getEmetteur();
            data2[i][2] = flux.getRecepteur();
        }
        table2 = new JTable(data2, columns2);

        // Ajout du tableau pour les flux du BPMN à un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setPreferredSize(new Dimension(580, 320));
        scrollPane2.setBorder(new TitledBorder("Les flux du BPMN"));

        table2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Flux flux = fluxList2.get(row);
                if (missingInFluxList2.contains(flux)) { // check if the current flux is in missingInFluxList2
                    c.setBackground(Color.RED); // set background color to red
                } else {
                    c.setBackground(Color.green); // set background color to default
                }
                return c;
            }
        });



        // Ajout des deux JScrollPane au panel principal
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane1, BorderLayout.NORTH);
        panel.add(scrollPane2, BorderLayout.SOUTH);
        getContentPane().add(panel);

        setVisible(true);
    }
}
