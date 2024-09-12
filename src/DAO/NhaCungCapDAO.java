/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Custom.JDBCUtil;
import DTO.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class NhaCungCapDAO {

    public NhaCungCapDAO() {

    }

    public NhaCungCap selectById(NhaCungCap t) {
        NhaCungCap nhaCungCap = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from nhacungcap where maNCC=? and trangThai=1";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMaNCC());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNCC = rs.getInt("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String dienThoai = rs.getString("dienThoai");
                int trangThai = rs.getInt("trangThai");
                nhaCungCap = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai, trangThai);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCap;
    }

    public ArrayList<NhaCungCap> selectByCondition(String condition) {
        ArrayList<NhaCungCap> nhaCungCaps = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from nhacungcap where " + condition;
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNCC = rs.getInt("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String dienThoai = rs.getString("dienThoai");
                int trangThai = rs.getInt("trangThai");
                NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai, trangThai);
                nhaCungCaps.add(nhaCungCap);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCaps;
    }

    public int insert(NhaCungCap t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "insert into nhacungcap(maNCC,tenNCC,diaChi,dienThoai,trangThai) "
                    + "values(?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMaNCC());
            pst.setString(2, t.getTenNCC());
            pst.setString(3, t.getDiaChi());
            pst.setString(4, t.getDienThoai());
            pst.setInt(5, t.getTrangThai());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(NhaCungCap t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "update nhacungcap set tenNCC=? ,diaChi=?,dienThoai=?,trangThai=? where maNCC=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getTenNCC());
            pst.setString(2, t.getDiaChi());
            pst.setString(3, t.getDienThoai());
            pst.setInt(4, t.getTrangThai());
            pst.setInt(5, t.getMaNCC());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(NhaCungCap t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "update nhacungcap set trangThai=0 where maNCC=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getMaNCC());

            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<NhaCungCap> selectAll() {
        ArrayList<NhaCungCap> nhaCungCaps = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select * from nhacungcap where trangThai=1";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNCC = rs.getInt("maNCC");
                String tenNCC = rs.getString("tenNCC");
                String diaChi = rs.getString("diaChi");
                String dienThoai = rs.getString("dienThoai");
                int trangThai = rs.getInt("trangThai");
                NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, diaChi, dienThoai, trangThai);
                nhaCungCaps.add(nhaCungCap);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCaps;
    }

    public int getNewMa() {
        int ma=-1;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql ="select max(maNCC) as maNCC from nhacungcap";
            Statement st = c.createStatement();
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                ma=rs.getInt("maNCC");
            }
            JDBCUtil.closeConnection(c);
            return ma;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ma;
    }

}
