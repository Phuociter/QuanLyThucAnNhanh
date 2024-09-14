package DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Custom.*;
import DTO.PhieuNhap;
import java.sql.Statement;

public class PhieuNhapDAO {

    public PhieuNhapDAO() {

    }

    public ArrayList<PhieuNhap> getallPhieuNhap() {

        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {

            Connection c = JDBCUtil.getConnection();
            System.out.println("connect");
            String sql = "SELECT * FROM phieunhap";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Xử lý lỗi tùy ý, có thể log hoặc throw ngoại lệ.
            return null;
        }
        return dspn;

    }

    public ArrayList<PhieuNhap> getPhieuNhapbyId(int maPN) {

        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {

            Connection c = JDBCUtil.getConnection();
            System.out.println("connect");
            String sql = "SELECT * FROM phieunhap where MaPN = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, maPN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Xử lý lỗi tùy ý, có thể log hoặc throw ngoại lệ.
            return null;
        }
        return dspn;
    }

    public ArrayList<PhieuNhap> getPhieuNhapByNgayLap(Date startDate, Date endDate) {
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            System.out.println("connect");
            String sql = "SELECT * FROM phieunhap WHERE ngayLap BETWEEN ? AND ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setDate(1, (java.sql.Date) startDate);
            stmt.setDate(2, (java.sql.Date) endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return dspn;
    }

    public ArrayList<PhieuNhap> getPhieuNhapByGia(int minPrice, int maxPrice) {
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE TongTien BETWEEN ? AND ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, minPrice);
            stmt.setInt(2, maxPrice);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return dspn;
    }

    public boolean themPhieuNhap(PhieuNhap pn) {

        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO phieunhap (MaNCC, MaNV, NgayLap, TongTien) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, pn.getMaNCC());
            stmt.setInt(2, pn.getMaNV());
            stmt.setDate(3, new java.sql.Date(pn.getNgayLap().getTime()));
            stmt.setInt(4, pn.getTongTien());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean capNhatPhieuNhap(PhieuNhap pn) {

        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE phieunhap SET MaNCC=?, MaNV=?, NgayLap=?, TongTien=? WHERE MaPN=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, pn.getMaNCC());
            stmt.setInt(2, pn.getMaNV());
            stmt.setDate(3, new java.sql.Date(pn.getNgayLap().getTime()));
            stmt.setInt(4, pn.getTongTien());
            stmt.setInt(5, pn.getMaPN());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getNewId() {
        int ma = -1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select max(maPN) as maPN from phieunhap";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ma = rs.getInt("maPN");
            }
            JDBCUtil.closeConnection(c);
            return ma;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }
    public ArrayList<PhieuNhap> getPhieuNhapByNgayLapVaGia(Date startDate, Date endDate, int minPrice, int maxPrice) {
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            System.out.println("connect");
            String sql = "SELECT * FROM phieunhap WHERE ngayLap BETWEEN ? AND ? AND TongTien BETWEEN ? AND ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setDate(1, (java.sql.Date) startDate);
            stmt.setDate(2, (java.sql.Date) endDate);
            stmt.setInt(3, minPrice);
            stmt.setInt(4, maxPrice);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                dspn.add(pn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Xử lý lỗi tùy ý, có thể log hoặc throw ngoại lệ.
            return null;
        }
        return dspn;
    }

    public PhieuNhap getById(int maPN) {
        PhieuNhap pn = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM phieunhap WHERE maPN = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, maPN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pn = new PhieuNhap();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNCC(rs.getInt(2));
                pn.setMaNV(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
            }
            JDBCUtil.closeConnection(c);
            return pn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
