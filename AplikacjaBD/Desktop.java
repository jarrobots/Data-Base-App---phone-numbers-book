package AplikacjaBD;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Desktop extends JFrame {
    private JPanel rootPanel;
    private JTextField nazwisko;
    private JTextField imie;
    private JTextField numerTelefonu;
    private JButton OKButton;
    private JRadioButton dodajRadioButton;
    private JRadioButton edytujRadioButton;
    private JTextField wyszukaj;
    private JButton wyszukajButton;
    private JTable table1;
    private JSpinner id;
    private JRadioButton usunRadioButton;
    private JRadioButton telefonRadioButton;
    private JRadioButton nazwiskoRadioButton;
    private JRadioButton imieRadioButton;
    private JButton pokazWszystkoButton;
    private String wybor;

    public Desktop() throws SQLException {
        Controler controler = new Controler();
        add(rootPanel);
        setTitle("Ksiazka Telefoniczna");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DefaultTableModel model = new DefaultTableModel();
        table1.setFillsViewportHeight(true);
        table1.setAutoCreateRowSorter(true);
        table1.setPreferredScrollableViewportSize(new Dimension(500,500));
        model.addColumn("ID");
        model.addColumn("imie");
        model.addColumn("nazwisko");
        model.addColumn("telefon");
        table1.setModel(model);
        show(model,controler);
        id.setVisible(false);
        nazwisko.setVisible(false);
        imie.setVisible(false);
        numerTelefonu.setVisible(false);

        dodajRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (dodajRadioButton.isSelected()) {
                    edytujRadioButton.setSelected(false);
                    usunRadioButton.setSelected(false);
                    id.setVisible(false);
                    nazwisko.setVisible(true);
                    imie.setVisible(true);
                    numerTelefonu.setVisible(true);

                } else {
                    dodajRadioButton.setSelected(true);
                }
            }
        });
        edytujRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (edytujRadioButton.isSelected()) {
                    dodajRadioButton.setSelected(false);
                    usunRadioButton.setSelected(false);
                    id.setVisible(true);
                    nazwisko.setVisible(true);
                    imie.setVisible(true);
                    numerTelefonu.setVisible(true);

                } else {
                    edytujRadioButton.setSelected(true);
                }
            }
        });
        usunRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (usunRadioButton.isSelected()) {
                    dodajRadioButton.setSelected(false);
                    edytujRadioButton.setSelected(false);
                    id.setVisible(true);
                    nazwisko.setVisible(false);
                    imie.setVisible(false);
                    numerTelefonu.setVisible(false);

                } else {
                    edytujRadioButton.setSelected(true);
                }
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (dodajRadioButton.isSelected()) {
                    String _imie, _nazwisko, _numer;
                    _imie = imie.getText();
                    _nazwisko = nazwisko.getText();
                    _numer = numerTelefonu.getText();
                    try {
                        controler.dodaj(_imie, _nazwisko, _numer);
                        show(model,controler);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (edytujRadioButton.isSelected()) {
                    int _id = 0;
                    String _imie, _nazwisko, _numer;
                    _id = (int) id.getValue();
                    _imie = imie.getText();
                    _nazwisko = nazwisko.getText();
                    _numer = numerTelefonu.getText();
                    try {
                        controler.zmien(_imie, _nazwisko, _numer, _id);
                        show(model,controler);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (usunRadioButton.isSelected()) {
                    int _id = 0;
                    _id = (int) id.getValue();
                    try {
                        controler.usun(_id);
                        show(model,controler);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (!usunRadioButton.isSelected() && !dodajRadioButton.isSelected() && !edytujRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(rootPanel, "Proszę wybrać opcję edytuj, albo dodaj, albo usun");
                }
                nazwisko.setText("");
                nazwisko.setVisible(false);
                imie.setText("");
                imie.setVisible(false);
                numerTelefonu.setText("");
                numerTelefonu.setVisible(false);
                id.setVisible(false);
                edytujRadioButton.setSelected(false);
                dodajRadioButton.setSelected(false);
                usunRadioButton.setSelected(false);
            }
        });

        telefonRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (telefonRadioButton.isSelected()) {
                    nazwiskoRadioButton.setSelected(false);
                    imieRadioButton.setSelected(false);
                    wybor = "telefon";
                } else {
                    telefonRadioButton.setSelected(true);
                }
            }
        });
        nazwiskoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nazwiskoRadioButton.isSelected()) {
                    imieRadioButton.setSelected(false);
                    telefonRadioButton.setSelected(false);
                    wybor = "nazwisko";
                } else {
                    nazwiskoRadioButton.setSelected(true);
                }
            }
        });
        imieRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (imieRadioButton.isSelected()) {
                    nazwiskoRadioButton.setSelected(false);
                    telefonRadioButton.setSelected(false);
                    wybor = "imie";

                } else {
                    imieRadioButton.setSelected(true);
                }
            }
        });
        wyszukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String str;
                if (telefonRadioButton.isSelected() || imieRadioButton.isSelected() || nazwiskoRadioButton.isSelected()) {
                    if (!wyszukaj.getText().equals("")) {
                        str = wyszukaj.getText();
                        try {
                            research(model,controler,wybor,str);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPanel, "wypełnij pole tekstowe");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "wybierz telefon, albo imie, albo nazwisko");
                }
                wyszukaj.setText("");
                telefonRadioButton.setSelected(false);
                imieRadioButton.setSelected(false);
                nazwiskoRadioButton.setSelected(false);
            }
        });
        pokazWszystkoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    show(model,controler);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void show(DefaultTableModel model, Controler x) throws SQLException {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (Row row : x.pokaz()) {
            model.addRow(new Object[]{row.getId(), row.getImie(), row.getNazwisko(), row.getTelefon()});
        }
    }
    public void research(DefaultTableModel model, Controler x, String type, String str) throws SQLException {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (Row row : x.wyszukaj(type,str)) {
            model.addRow(new Object[]{row.getId(), row.getImie(), row.getNazwisko(), row.getTelefon()});
        }
    }
}
