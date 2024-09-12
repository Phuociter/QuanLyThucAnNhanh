/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Custom.*;

import DTO.CTPhieuNhap;

/**
 *
 * @author nguye
 */
public class CTPhieuNhapDAO {
     
    // Phương thức lấy danh sách chi tiết phiếu nhập dựa trên mã phiếu nhập
    public ArrayList<CTPhieuNhap> getListCTPhieuNhapByMaPN() {
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        // Thực hiện kết nối cơ sở dữ liệu và truy vấn dữ liệu
        try (Connection c = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM ctphieunhap";
            PreparedStatement stmt =c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt("MaPN"));
                ctpn.setMaSP(rs.getInt("MaSP"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getInt("DonGia"));
                ctpn.setThanhTien(rs.getInt("thanhTien")); // đã bổ sung thành tiền
                dsctpn.add(ctpn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dsctpn;
    }

    // Phương thức thêm một chi tiết phiếu nhập mới vào cơ sở dữ liệu
    public boolean addCTPhieuNhap(CTPhieuNhap ctpn) {
        try (Connection c = JDBCUtil.getConnection()) {
            String sqlUpdateSP = "update sanpham set soLuong = soLuong + ? where maSP = ?"; // cập nhật số lượng sản phẩm
            PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateSP);
            preparedStatement.setInt(1, ctpn.getSoLuong());
            preparedStatement.setInt(2, ctpn.getMaSP());
            preparedStatement.executeUpdate();
            
            String sql = "INSERT INTO ctphieunhap (MaPN, MaSP, SoLuong, DonGia, thanhTien) VALUES (?, ?, ?, ?, ?)"; // đã bổ sung thành tiền
            PreparedStatement stmt =c.prepareStatement(sql);
            stmt.setInt(1, ctpn.getMaPN());
            stmt.setInt(2, ctpn.getMaSP());
            stmt.setInt(3, ctpn.getSoLuong());
            stmt.setInt(4, ctpn.getDonGia());
            stmt.setInt(5, ctpn.getThanhTien());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức cập nhật thông tin một chi tiết phiếu nhập trong cơ sở dữ liệu
    public boolean updateCTPhieuNhap(CTPhieuNhap ctpn) {
        try (            Connection c = JDBCUtil.getConnection()) {
            String sql = "UPDATE ctphieunhap SET SoLuong = ?, DonGia = ? WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement stmt =c.prepareStatement(sql);
            stmt.setInt(1, ctpn.getSoLuong());
            stmt.setInt(2, ctpn.getDonGia());
            stmt.setInt(3, ctpn.getMaPN());
            stmt.setInt(4, ctpn.getMaSP());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa một chi tiết phiếu nhập khỏi cơ sở dữ liệu
    public boolean deleteCTPhieuNhap(int maPN, int maSP) {
        try (            Connection c = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM ctphieunhap WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement stmt =c.prepareStatement(sql);
            stmt.setInt(1, maPN);
            stmt.setInt(2, maSP);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public CTPhieuNhap timkiemCTPhieuNhap(int maPN, int maSP) {
        CTPhieuNhap ctpn = null;
        try ( Connection c = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM ctphieunhap WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement stmt =c.prepareStatement(sql);
            stmt.setInt(1, maPN);
            stmt.setInt(2, maSP);
            System.out.println("đã conce");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ctpn = new CTPhieuNhap();
                ctpn.setMaPN(rs.getInt("MaPN"));
                ctpn.setMaSP(rs.getInt("MaSP"));
                ctpn.setSoLuong(rs.getInt("SoLuong"));
                ctpn.setDonGia(rs.getInt("DonGia"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ctpn;
    }
}
