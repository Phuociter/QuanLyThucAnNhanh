package DAO;

import Custom.JDBCUtil;
import DTO.KhachHang;

import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {

    public ArrayList<KhachHang> getListKhachHang() {
        try {
            String sql = "SELECT * FROM KhachHang WHERE trangThai=1";
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhachHang> dskh = new ArrayList<>();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setGioiTinh(rs.getString(3));
                kh.setDienThoai(rs.getString(4));
                kh.setEmail(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setTongChiTieu(rs.getInt(7));
                kh.setTrangThai(rs.getInt(8));
                dskh.add(kh);
            }
            return dskh;
        } catch (SQLException ex) {
        }
        return null;
    }

    public KhachHang getKhachHang(int maKH) { // kt mã khách hàng có trùng hay không
        KhachHang kh = null; //khởi tạo cho nó rỗng lúc ban đầu
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE MaKH=? AND trangThai=1";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, maKH);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setGioiTinh(rs.getString(3));
                kh.setDienThoai(rs.getString(4));
                kh.setEmail(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setTongChiTieu(rs.getInt(7));
                kh.setTrangThai(rs.getInt(8));
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException ex) {
            return null;
        }
        return kh;
    }

    public boolean addKhachHang(KhachHang kh) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO khachhang VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, kh.getMaKH());
            prep.setString(2, kh.getTen());
            prep.setString(3, kh.getGioiTinh());
            prep.setString(4, kh.getDienThoai());
            prep.setString(5, kh.getEmail());
            prep.setString(6, kh.getDiaChi());
            prep.setInt(7, kh.getTongChiTieu());
            prep.setInt(8, kh.getTrangThai());
            result = prep.executeUpdate() > 0;
            JDBCUtil.closeConnection(c);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int deleteKhachHang(int maKH) {
        int result = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE khachhang SET trangThai=0 WHERE MaKH=?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, maKH);
            result = prep.executeUpdate();
            JDBCUtil.closeConnection(c);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean updateKhachHang(KhachHang kh) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE khachhang SET Ten=?, GioiTinh=?, DienThoai=?, Email=?, DiaChi=?, TongChiTieu=?, trangThai=?  WHERE MaKH=?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, kh.getTen());
            prep.setString(2, kh.getGioiTinh());
            prep.setString(3, kh.getDienThoai());
            prep.setString(4, kh.getEmail());
            prep.setString(5, kh.getDiaChi());
            prep.setInt(6, kh.getTongChiTieu());
            prep.setInt(7, kh.getTrangThai());
            prep.setInt(8, kh.getMaKH());
            result = prep.executeUpdate() > 0;
            JDBCUtil.closeConnection(c);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return result;
        }
    }

    public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE khachhang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
            Statement stmt = c.createStatement();
            result = stmt.executeUpdate(sql) > 0;
            JDBCUtil.closeConnection(c);
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }

    public int getNewMa() {
        int ma = -1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select max(maKH) as maKH from khachhang";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ma = rs.getInt("maKH");
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }

    public ArrayList<KhachHang> searchKhachHang(String keyword) {
        ArrayList<KhachHang> resultList = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM khachhang WHERE concat(maKH,ten) LIKE ? AND trangThai=1";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%" + keyword + "%");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setGioiTinh(rs.getString(3));
                kh.setDienThoai(rs.getString(4));
                kh.setEmail(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setTongChiTieu(rs.getInt(7));
                kh.setTrangThai(rs.getInt(8));
                resultList.add(kh);
            }
            JDBCUtil.closeConnection(c);
            return resultList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
