/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.CTHoaDon;
import Custom.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class CTHoaDonDAO {
    public ArrayList<CTHoaDon> getListCTHD(){
        ArrayList<CTHoaDon> lsCTHD = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM cthoadon";
            Statement stmt = JDBCUtil.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()){
                CTHoaDon tempCTHD = new CTHoaDon();
                tempCTHD.setMaHD(rs.getInt(1));
                tempCTHD.setMaSP(rs.getInt(2));
                tempCTHD.setSoLuong(rs.getInt(3));
                tempCTHD.setDonGia(rs.getInt(4));
                tempCTHD.setThanhTien(rs.getInt(5));
                lsCTHD.add(tempCTHD);
            }
        }
        catch (SQLException ex){
        }
        return lsCTHD;
    }
    public boolean addCTHoaDon(CTHoaDon cthd) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sqlUpdateSP = "update sanpham set soLuong = soLuong - ? where maSP = ?"; // cập nhật số lượng sản phẩm
            PreparedStatement preparedStatement = c.prepareStatement(sqlUpdateSP);
            preparedStatement.setInt(1, cthd.getSoLuong());
            preparedStatement.setInt(2, cthd.getMaSP());
            preparedStatement.executeUpdate();
            
            String sql = "INSERT INTO dbo.cthoadon VALUES(?,?,?,?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, cthd.getMaHD());
            prep.setInt(2, cthd.getMaSP());
            prep.setInt(3, cthd.getSoLuong());
            prep.setInt(4, cthd.getDonGia());
            prep.setInt(5, cthd.getThanhTien());
            result = prep.executeUpdate() > 0;
            JDBCUtil.closeConnection(c);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }
//    public boolean deleteCTHoaDon(int maHD, int maSP) {
//        boolean result = false;
//        try {
//            Connection c = JDBCUtil.getConnection();
//            String sql = "DELETE FROM cthoadon WHERE MaHD="+maHD+" AND MaSP="+maSP;
//            Statement stmt = c.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//            JDBCUtil.closeConnection(c);
//        } catch(SQLException ex) {
//            return false;
//        }
//        return result;
//    }
    public boolean updateCTHoaDon(int maHD, int maSP, CTHoaDon cthd) {
        boolean result = false;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE cthoadon SET MaHD=?, MaSP=?, SoLuong=?, DonGia=? ThanhTien=? "
                    + "WHERE MaHD=? AND MaSP=?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, cthd.getMaHD());
            prep.setInt(2, cthd.getMaSP());
            prep.setInt(3, cthd.getSoLuong());
            prep.setInt(4, cthd.getDonGia());
            prep.setInt(5, cthd.getThanhTien());
            prep.setInt(6, maHD);
            prep.setInt(7, maSP);
            result = prep.executeUpdate() > 0;
            JDBCUtil.closeConnection(c);
        } catch(SQLException ex) {
            return false;
        }
        return result;
    }
}
