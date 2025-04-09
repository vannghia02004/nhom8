package quang_tap_hoa;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Lớp sản phẩm
class SanPham {

    String ten;
    double gia;
    String loai;

    public SanPham(String ten, double gia, String loai) {
        this.ten = ten;
        this.gia = gia;
        this.loai = loai;
    }

    public Object[] toRow() {
        return new Object[]{ten, gia, loai};
    }
}

// Giao diện chính
public class Quan_tap_hoa extends JFrame {

    private JTextField txtTen, txtGia;
    private JComboBox<String> cmbLoai;
    private JTable table;
    private DefaultTableModel tableModel;
    private java.util.List<SanPham> danhSach = new ArrayList<>();

    public Quan_tap_hoa() {
        setTitle("Quản lý tạp hóa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));

        panelInput.add(new JLabel("Tên sản phẩm:"));
        txtTen = new JTextField();
        panelInput.add(txtTen);

        panelInput.add(new JLabel("Giá bán:"));
        txtGia = new JTextField();
        panelInput.add(txtGia);

        panelInput.add(new JLabel("Loại mặt hàng:"));
        cmbLoai = new JComboBox<>(new String[]{"Thực phẩm", "Đồ uống", "Gia dụng", "Khác"});
        panelInput.add(cmbLoai);

        JButton btnThem = new JButton("Thêm sản phẩm");
        btnThem.addActionListener(e -> themSanPham());
        panelInput.add(btnThem);

        getContentPane().add(panelInput, BorderLayout.NORTH);

        // Bảng
        String[] columnNames = {"Tên sản phẩm", "Giá bán", "Loại"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void themSanPham() {
        String ten = txtTen.getText().trim();
        String giaStr = txtGia.getText().trim();
        String loai = cmbLoai.getSelectedItem().toString();

        if (ten.isEmpty() || giaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        double gia;
        try {
            gia = Double.parseDouble(giaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ.");
            return;
        }

        SanPham sp = new SanPham(ten, gia, loai);
        danhSach.add(sp);
        tableModel.addRow(sp.toRow());

        txtTen.setText("");
        txtGia.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Quan_tap_hoa().setVisible(true);
        });
    }
}
