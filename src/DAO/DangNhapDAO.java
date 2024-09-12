/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import Custom.JDBCUtil;
import DTO.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nguye
 */
public class DangNhapDAO {

    public DangNhapDAO() {
    }

    public TaiKhoan checkAccount(TaiKhoan tk) {
        TaiKhoan taiKhoan = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where tenDangNhap=? and matKhau=? and trangThai=1";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, tk.getTenDangNhap());
            pst.setString(2, tk.getMatKhau());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                taiKhoan = new TaiKhoan(rs.getInt("maNV"), rs.getInt("maQuyen"), rs.getString("tenDangNhap"), rs.getString("matKhau"), rs.getInt("trangThai"));
            }
            JDBCUtil.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }
}
