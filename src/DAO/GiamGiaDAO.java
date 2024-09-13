package DAO;

import Custom.JDBCUtil;
import DTO.GiamGia;
import java.sql.*;
import java.util.ArrayList;

public class GiamGiaDAO {

    public ArrayList<GiamGia> getListGiamGia() {
        ArrayList<GiamGia> dsgg = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "SELECT * FROM GiamGia WHERE trangThai=1";
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGiam(rs.getInt("maGiam"));
                gg.setTenGiamGia(rs.getString("tenGiamGia"));
                gg.setPhanTramGiam(rs.getInt("phanTramGiam"));
                gg.setDieuKien(rs.getInt("dieuKien"));
                gg.setNgayBD(rs.getDate("ngayBD"));
                gg.setNgayKT(rs.getDate("ngayKT"));
                gg.setTrangThai(rs.getInt("trangThai"));
                dsgg.add(gg);

            }
            JDBCUtil.closeConnection(connection);
            return dsgg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean addGiamGia(GiamGia gg) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = " INSERT INTO GiamGia(maGiam, tenGiamGia, phanTramGiam, dieuKien, ngayBD,ngayKT, trangThai)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gg.getMaGiam());
            ps.setString(2, gg.getTenGiamGia());
            ps.setInt(3, gg.getPhanTramGiam());
            ps.setInt(4, gg.getDieuKien());
            ps.setDate(5, new Date(gg.getNgayBD().getTime()));
            ps.setDate(6, new Date(gg.getNgayKT().getTime()));
            ps.setInt(7, gg.getTrangThai());

            int result = ps.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteGiamGia(int maGiam) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "update giamgia set trangThai = 0 WHERE maGiam=?"; // xóa sẽ đổi trạng thái sang 0 
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, maGiam);
            int result = ps.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean updateGiamGia(int maGiam, GiamGia gg) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = " UPDATE GiamGia SET maGiam=?, tenGiamGia=?, phanTramGiam=?, dieuKien=?, ngayBD=?, ngayKT=?, trangThai=? WHERE maGiam=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gg.getMaGiam());
            ps.setString(2, gg.getTenGiamGia());
            ps.setInt(3, gg.getPhanTramGiam());
            ps.setInt(4, gg.getDieuKien());
            ps.setDate(5, new Date(gg.getNgayBD().getTime()));
            ps.setDate(6, new Date(gg.getNgayKT().getTime()));
            ps.setInt(7, gg.getTrangThai());
            ps.setInt(8, gg.getMaGiam());
            int result = ps.executeUpdate();
            JDBCUtil.closeConnection(connection);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public GiamGia selectById(int maGiamGia) {
        GiamGia gg = new GiamGia();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from GiamGia where maGiam = ? and trangThai = 1";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, maGiamGia);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                gg.setMaGiam(rs.getInt("maGiam"));
                gg.setTenGiamGia(rs.getString("tenGiamGia"));
                gg.setPhanTramGiam(rs.getInt("phanTramGiam"));
                gg.setDieuKien(rs.getInt("dieuKien"));
                gg.setNgayBD(rs.getDate("ngayBD"));
                gg.setNgayKT(rs.getDate("ngayKT"));
                gg.setTrangThai(rs.getInt("trangThai"));
            }
            JDBCUtil.closeConnection(c);
            return gg;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNewMa() {
        int ma = -1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select max(maGiam) as maGiam from giamgia";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ma = rs.getInt("maGiam");
            }
            JDBCUtil.closeConnection(c);
            return ma;
        } catch (SQLException e) {
            e.printStackTrace();
            return ma;
        }
    }

    public ArrayList<GiamGia> searchGiamGias(String keyword) {
        ArrayList<GiamGia> dsgg = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM giamgia WHERE concat(maGiam,tenGiamGia) LIKE ? AND trangThai=1";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%" + keyword + "%");
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia();
                gg.setMaGiam(rs.getInt("maGiam"));
                gg.setTenGiamGia(rs.getString("tenGiamGia"));
                gg.setPhanTramGiam(rs.getInt("phanTramGiam"));
                gg.setDieuKien(rs.getInt("dieuKien"));
                gg.setNgayBD(rs.getDate("ngayBD"));
                gg.setNgayKT(rs.getDate("ngayKT"));
                gg.setTrangThai(rs.getInt("trangThai"));
                dsgg.add(gg);
            }
            JDBCUtil.closeConnection(c);
            return dsgg;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
