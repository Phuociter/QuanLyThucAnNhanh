package DAO;

import DTO.NhanVien;
import Custom.JDBCUtil;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "select * from nhanvien where trangThai=1";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<NhanVien> listNV = new ArrayList<>();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setDienThoai(rs.getString(5));
                nv.setTrangThai(rs.getInt(6));
                nv.setChucVu(rs.getString(7));
                listNV.add(nv);
            }
            return listNV;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateInfoNhanVien(NhanVien nv) {
        boolean ketqua = false;
        try {
            Connection connection = JDBCUtil.getConnection();

            String sql = "update nhanvien set ho=?, ten=?, gioiTinh=?, dienThoai=?, chucVu=? where maNV=?";
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, nv.getHo());
            pre.setString(2, nv.getTen());
            pre.setString(3, nv.getGioiTinh());
            pre.setString(4, nv.getDienThoai());
            pre.setString(5, nv.getChucVu());
            pre.setInt(6, nv.getMaNV());
            ketqua = pre.executeUpdate() > 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketqua;
    }

    public boolean capNhatChucVu(NhanVien nv) {
        boolean ketqua = false;
        try {
            Connection connection = JDBCUtil.getConnection();

            String sql = "update nhanvien set chucVu=? where maNV=?";
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, nv.getChucVu());
            pre.setInt(2, nv.getMaNV());
            ketqua = pre.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketqua;
    }

    public boolean deleteNhanVien(int MaNV) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sqlKhoaTK = "update taikhoan set trangThai = 0 where maNV = ?"; // khóa tài khoản của nhân viên 
            PreparedStatement preparedStatement = connection.prepareStatement(sqlKhoaTK);
            preparedStatement.setInt(1, MaNV);
            int rs = preparedStatement.executeUpdate();

            String sql = "update nhanvien set trangThai=? where maNV=?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setInt(2, MaNV);
            pre.executeUpdate();
            int rowsAffected = pre.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int layMaNhanVienCuoiCung() {
        int maNV = -1;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT TOP 1 maNV FROM nhanvien ORDER BY maNV DESC";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                maNV = rs.getInt("maNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    public boolean themNhanVien(NhanVien nv) {
        boolean ketqua = false;
        try {
            Connection connection = JDBCUtil.getConnection();

            String sql = "insert into nhanvien(maNV, ho, ten, gioiTinh, dienThoai, trangThai, chucVu)  values(?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            int maNV = layMaNhanVienCuoiCung() + 1;
            pre.setInt(1, maNV);
            pre.setString(2, nv.getHo());
            pre.setString(3, nv.getTen());
            pre.setString(4, nv.getGioiTinh());
            pre.setString(5, nv.getDienThoai());
            pre.setInt(6, nv.getTrangThai());
            pre.setString(7, nv.getChucVu());
            ketqua = pre.executeUpdate() > 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public NhanVien getNhanVien(int maNV) {
        NhanVien nv = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "select * from nhanvien where maNV = ? and trangThai=1";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, maNV);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(maNV);
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setDienThoai(rs.getString(5));
                nv.setTrangThai(rs.getInt(6));
                nv.setChucVu(rs.getString(7));
            }

            return nv;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public NhanVien getNhanVienTheoTen(String tenNV) {
        NhanVien nv = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String slq = "select * from nhanvien where ten = ? and trangThai=1";
            PreparedStatement pre = connection.prepareStatement(slq);
            pre.setString(1, tenNV);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setDienThoai(rs.getString(5));
                nv.setTrangThai(rs.getInt(6));
                nv.setChucVu(rs.getString(7));
            }

            return nv;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deletaFKHoandon_PhieuNhap() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "ALTER TABLE hoadon DROP CONSTRAINT FK_maNV_hoadon;"
                    + "ALTER TABLE phieunhap DROP CONSTRAINT FK_maNV_phieunhap;";

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.executeUpdate();
            return true;

        } catch (SQLException e) {

            return false;
        }
    }

    public boolean updateFKHoandon_PhieuNhap() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "ALTER TABLE hoadon ADD CONSTRAINT FK_maNV_hoadon FOREIGN KEY (maNV) REFERENCES nhanvien(maNV);"
                    + "ALTER TABLE phieunhap ADD CONSTRAINT FK_maNV_phieunhap FOREIGN KEY (maNV) REFERENCES nhanvien(maNV);";

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean xoaAllInfor() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "delete from nhanvien;";

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean importNhanVienFromExcel(NhanVien nv) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO nhanvien(maNV, ho, ten, gioiTinh, dienThoai, trangThai,chucVu) VALUES (?, ?, ?, ?, ?, ?,?);";

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, nv.getMaNV());
            pre.setString(2, nv.getHo());
            pre.setString(3, nv.getTen());
            pre.setString(4, nv.getGioiTinh());
            pre.setString(5, nv.getDienThoai());
            pre.setInt(6, nv.getTrangThai());
            pre.setString(7, nv.getChucVu());
            pre.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
