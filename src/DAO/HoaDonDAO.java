package DAO;

import Custom.JDBCUtil;
import DTO.HoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDonDAO {

    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> dshd = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoadon ORDER BY maHD DESC";
            Statement stmt = Objects.requireNonNull(JDBCUtil.getConnection()).createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setMaGiam(rs.getInt(4));
                hd.setNgayLap(rs.getDate(5));
                hd.setTongTien(rs.getInt(6));
                dshd.add(hd);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dshd;
    }

    public boolean addHoaDon(HoaDon hd) {
        boolean result;
        try {
            Connection c = JDBCUtil.getConnection();
            String sqlUpdateKH = "update khachhang set tongChiTieu = tongChiTieu + ? where maKH = ?"; // cập nhật chi tiêu khách hàng
            PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateKH);
            preparedStatement.setInt(1, hd.getTongTien());
            preparedStatement.setInt(2, hd.getMaKH());
            preparedStatement.executeUpdate();

            String sql = "INSERT INTO hoadon(MaHD, MaKH, MaNV, MaGiam, NgayLap, TongTien) VALUES(?,?, ?, ?, ?, ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, hd.getMaHD());
            prep.setInt(2, hd.getMaKH());
            prep.setInt(3, hd.getMaNV());
            prep.setInt(4, hd.getMaGiam());
            prep.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(6, hd.getTongTien());
            result = prep.executeUpdate() > 0;
            JDBCUtil.closeConnection(c);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    public ArrayList<HoaDon> getListHoaDonTheoDateVaTongTien(Date dateMin, Date dateMax, int tongtienMin, int tongtienMax) { // lấy list dshd theo ngày và tổng tiền
        try {
            Connection c = JDBCUtil.getConnection();
            //String sql = "SELECT * FROM hoadon WHERE (NgayLap BETWEEN ? AND ?) AND (tongTien BETWEEN ? AND ?)";
            String sql = "SELECT * FROM hoadon WHERE (NgayLap BETWEEN ? AND ?) AND (tongTien BETWEEN ? AND ?) ORDER BY MaHD DESC";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            pre.setInt(3, tongtienMin);
            pre.setInt(4, tongtienMax);
            ResultSet rs = pre.executeQuery();
            ArrayList<HoaDon> dshd = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setMaGiam(rs.getInt(4));
                hd.setNgayLap(rs.getDate(5));
                hd.setTongTien(rs.getInt(6));
                dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HoaDon getHoaDonTheoMHD(int maHD) {
        HoaDon HD = null;
        try {
            String sql = "SELECT * FROM hoadon where maHD=" + maHD;
            Statement stmt = JDBCUtil.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HD = new HoaDon();
                HD.setMaHD(rs.getInt(1));
                HD.setMaKH(rs.getInt(2));
                HD.setMaNV(rs.getInt(3));
                HD.setMaGiam(rs.getInt(4));
                HD.setNgayLap(rs.getDate(5));
                HD.setTongTien(rs.getInt(6));
            }
            return HD;
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoDate(Date dateMin, Date dateMax) { // lấy list dshd theo ngày và tổng tiền
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM hoadon WHERE (NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)) ORDER BY MaHD DESC";
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            ResultSet rs = pre.executeQuery();
            ArrayList<HoaDon> dshd = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setMaGiam(rs.getInt(4));
                hd.setNgayLap(rs.getDate(5));
                hd.setTongTien(rs.getInt(6));
                dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getNewId() {
        int ma = -1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select max(maHD) as maHD from hoadon";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ma = rs.getInt("maHD");
            }
            JDBCUtil.closeConnection(c);
            return ma;
        } catch (SQLException e) {
            e.printStackTrace();
            return ma;
        }
    }
    public int getMaxTongTien() {
        int max = 0;
        Connection c = null;
        try {
            c = JDBCUtil.getConnection();
            String sql = "select max(tongTien) as Max from hoadon";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                max = rs.getInt("Max");
            } else {
                System.out.println("No records found in hoadon table.");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (c != null) {
                JDBCUtil.closeConnection(c);
            }
        }
        return max;
    }
    public int GetMaGiam(int maHD){
        int maGiam = 0;
        Connection c = null;
        try {
            c = JDBCUtil.getConnection();
            String sql = "select maGiam as mg from hoadon Where maHD = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,maHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                maGiam = rs.getInt("mg");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (c!=null){
                JDBCUtil.closeConnection(c);
            }
        }
        return maGiam;
    }
    public int getMaHDMin(){
        Connection c = null;
        int min = Integer.MAX_VALUE;
        try{
            c = JDBCUtil.getConnection();
            String sql = "SELECT MIN (maHD) as min from hoadon";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                min = rs.getInt("min");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(c != null){
                JDBCUtil.closeConnection(c);
            }
            return min;
        }
    }
    public int getMaHDMax(){
        Connection c = null;
        int max = Integer.MIN_VALUE;
        try{
            c = JDBCUtil.getConnection();
            String sql = "SELECT MAX (maHD) as max from hoadon";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                max = rs.getInt("max");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(c != null){
                JDBCUtil.closeConnection(c);
            }
            return max;
        }
    }
}
