package DAO;

import Custom.JDBCUtil;
import DTO.TaiKhoan;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BUS.DangNhapBUS;

public class TaiKhoanDAO {

    public TaiKhoanDAO() {

    }

    public TaiKhoan selectById(int maNV, int opt) {
        TaiKhoan taiKhoan = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where maNV=?";
            if (opt == 1) {
                sql = sql + " and trangThai=1";
            }
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, maNV);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNhanVien = rs.getInt("maNV");
                int maQuyen = rs.getInt("maQuyen");
                String tenDangNhap = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                taiKhoan = new TaiKhoan(maNhanVien, maQuyen, tenDangNhap, matKhau, trangThai);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }

    public ArrayList<TaiKhoan> selectByCondition(String condition) {
        ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("maNV");
                int maQuyen = rs.getInt("maQuyen");
                String tenDangNhap = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, maQuyen, tenDangNhap, matKhau, trangThai);
                taiKhoans.add(taiKhoan);
            }
            JDBCUtil.closeConnection(c);
            return taiKhoans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(TaiKhoan tk) {
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "insert into taikhoan(maNV,maQuyen,tenDangNhap,matKhau,trangThai) "
                    + "values(?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, tk.getMaNhanVien());
            pst.setInt(2, tk.getMaQuyen());
            pst.setString(3, tk.getTenDangNhap());
            pst.setString(4, tk.getMatKhau());
            pst.setInt(5, tk.getTrangThai());
            pst.executeUpdate();

            JDBCUtil.closeConnection(c);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int update(TaiKhoan tk) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "update taikhoan set maQuyen=?,tenDangNhap=?,matKhau=?,trangThai=? where maNV=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, tk.getMaNhanVien());
            pst.setString(2, tk.getTenDangNhap());
            pst.setString(3, tk.getMatKhau());
            pst.setInt(4, tk.getTrangThai());
            pst.setInt(5, tk.getMaNhanVien());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(TaiKhoan tk) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "update taikhoan set trangThai=0 where maNV=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, tk.getMaNhanVien());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<TaiKhoan> selectAll() {
        ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where trangThai=1";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("maNV");
                int maQuyen = rs.getInt("maQuyen");
                String tenDangNhap = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                int trangThai = rs.getInt("trangThai");
                TaiKhoan taiKhoan = new TaiKhoan(maNhanVien, maQuyen, tenDangNhap, matKhau, trangThai);
                taiKhoans.add(taiKhoan);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoans;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where tenDangNhap = '" + tenDangNhap + "'";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String layTenDangNhapTheoMa(int maNV) {
        try {
            String sql = "SELECT tenDangNhap FROM taikhoan WHERE maNV=" + maNV;
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public int layQuyenTheoMa(int maNV) {
        try {
            String sql = "SELECT maQuyen FROM taikhoan WHERE maNV=" + maNV;
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public boolean datLaiMatKhau(int maNV, String tenDangNhap) {
        try {
            String sql = "UPDATE taikhoan SET matKhau=? WHERE maNV=?";
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, tenDangNhap);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean datLaiQuyen(int maNV, int quyen) {
        try {
            String sql = "UPDATE taikhoan SET maQuyen=? WHERE maNV=?";
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setInt(1, quyen);
            pre.setInt(2, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean khoaTaiKhoan(int maNV) {
        try {
            String sql = "UPDATE taikhoan SET trangThai=0 WHERE maNV=?";
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean moKhoaTaiKhoan(int maNV) {
        try {
            String sql = "UPDATE taikhoan SET trangThai=1 WHERE maNV=?";
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setInt(1, maNV);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            String sql = "UPDATE taikhoan SET matKhau=? WHERE maNV=? AND matKhau=?";
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            pre.setString(1, matKhauMoi);
            pre.setInt(2, DangNhapBUS.taiKhoanLogin.getMaNhanVien());
            pre.setString(3, matKhauCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public int getTrangThai(int ma) {
        try {
            String sql = "SELECT trangThai FROM taikhoan WHERE maNV=" + ma;
            Connection c = JDBCUtil.getConnection();
            PreparedStatement pre = c.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }

}
