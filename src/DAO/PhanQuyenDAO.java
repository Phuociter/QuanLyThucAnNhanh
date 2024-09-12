package DAO;

import Custom.JDBCUtil;
import DTO.PhanQuyen;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhanQuyenDAO {

    public PhanQuyenDAO() {
    }

    public int insert(PhanQuyen t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "insert into phanquyen(maQuyen,tenQuyen,nhapHang,qlSanPham,qlNhanVien,qlKhachHang,thongKe,trangThai) "
                    + "values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMaQuyen());
            pst.setString(2, "" + t.getTenQuyen());
            pst.setInt(3, t.getQlNhapHang());
            pst.setInt(4, t.getQlSanPham());
            pst.setInt(5, t.getQlNhanVien());
            pst.setInt(6, t.getQlKhachHang());
            pst.setInt(7, t.getThongKe());
            pst.setInt(8, t.getTrangThai());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(PhanQuyen t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "update phanquyen set nhapHang=?,qlSanPham=?,qlNhanVien=?,qlKhachHang=?,thongKe=?,trangThai=? where maQuyen=? ";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getQlNhapHang());
            pst.setInt(2, t.getQlSanPham());
            pst.setInt(3, t.getQlNhanVien());
            pst.setInt(4, t.getQlKhachHang());
            pst.setInt(5, t.getThongKe());
            pst.setInt(6, t.getTrangThai());
            pst.setInt(7, t.getMaQuyen());
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(int ma) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "update phanquyen set trangThai=0 where maQuyen = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, ma);

            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<PhanQuyen> selectAll() {
        ArrayList<PhanQuyen> phanQuyens = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from phanquyen where trangThai=1";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maQuyen = rs.getInt("maQuyen");
                String tenQuyen = rs.getString("tenQuyen");
                int nhapHang = rs.getInt("nhapHang");
                int qlSanPham = rs.getInt("qlSanPham");
                int qlNhanVien = rs.getInt("qlNhanVien");
                int qlKhachHang = rs.getInt("qlKhachHang");
                int thongke = rs.getInt("thongKe");
                int trangThai = rs.getInt("trangThai");
                PhanQuyen phanQuyen = new PhanQuyen(maQuyen, tenQuyen, nhapHang, qlSanPham, qlNhanVien, qlKhachHang, thongke, trangThai);
                phanQuyens.add(phanQuyen);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phanQuyens;
    }

    public PhanQuyen selectById(PhanQuyen t) {
        PhanQuyen phanQuyen = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from phanquyen where maQuyen=? and trangThai=1";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMaQuyen());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maQuyen = rs.getInt("maQuyen");
                String tenQuyen = rs.getString("tenQuyen");
                int nhapHang = rs.getInt("nhapHang");
                int qlSanPham = rs.getInt("qlSanPham");
                int qlNhanVien = rs.getInt("qlNhanVien");
                int qlKhachHang = rs.getInt("qlKhachHang");
                int thongke = rs.getInt("thongKe");
                int trangThai = rs.getInt("trangThai");
                phanQuyen = new PhanQuyen(maQuyen, tenQuyen, nhapHang, qlSanPham, qlNhanVien, qlKhachHang, thongke, trangThai);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phanQuyen;
    }

    public ArrayList<PhanQuyen> selectByCondition(String condition) {
        ArrayList<PhanQuyen> phanQuyens = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from phanquyen where " + condition;
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int maQuyen = rs.getInt("maQuyen");
                String tenQuyen = rs.getString("tenQuyen");
                int nhapHang = rs.getInt("nhapHang");
                int qlSanPham = rs.getInt("qlSanPham");
                int qlNhanVien = rs.getInt("qlNhanVien");
                int qlKhachHang = rs.getInt("qlKhachHang");
                int thongke = rs.getInt("thongKe");
                int trangThai = rs.getInt("trangThai");
                PhanQuyen phanQuyen = new PhanQuyen(maQuyen, tenQuyen, nhapHang, qlSanPham, qlNhanVien, qlKhachHang, thongke, trangThai);
                phanQuyens.add(phanQuyen);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phanQuyens;
    }

    public int getNewMa() {
        int ma = -1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select max(maQuyen) as maQuyen from phanquyen";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ma = rs.getInt("maQuyen");
            }
            JDBCUtil.closeConnection(c);
            return ma;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }

    public int getIdByName(String name) {
        int ma = -1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from phanquyen where tenQuyen = ? and trangThai = 1";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ma = rs.getInt("maQuyen");
            }
            JDBCUtil.closeConnection(c);
            return ma;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }
}
