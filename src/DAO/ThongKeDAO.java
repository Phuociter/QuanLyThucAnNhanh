package DAO;

import Custom.JDBCUtil;
import DTO.KhachHang;
import DTO.SPDaBan;
import DTO.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ThongKeDAO {

    public ThongKeDAO() {
    }

    public ArrayList<SPDaBan> getListSanPhamDaBan() {
        ArrayList<SPDaBan> sPDaBans = new ArrayList<>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select sp.maSP,sp.tenSP, sum(cthd.soLuong) as daBan from sanpham sp inner join cthoadon cthd on sp.maSP = cthd.maSP where sp.trangThai=1 group by sp.maSP,sp.tenSP order by daBan desc";
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int maSP = rs.getInt(1);
                String tenSP = rs.getString(2);
                int daBan = rs.getInt(3);
                SPDaBan sPDaBan = new SPDaBan(maSP, tenSP, daBan);
                sPDaBans.add(sPDaBan);
            }
            JDBCUtil.closeConnection(c);
            return sPDaBans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<KhachHang> getListTopChiTieuKhachHang() {
        try {
            String sql = "select top 10 * from khachhang where trangThai = 1 order by tongChiTieu desc";
            Connection c = JDBCUtil.getConnection();
            Statement pre = c.createStatement();
            ResultSet rs = pre.executeQuery(sql);
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
            ex.printStackTrace();
        }
        return null;
    }

    public int getDoanhThuThangTrongNam(int month, int year) {
        int doanhThu = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "select tongTien from hoadon where YEAR(ngayLap) = ? and MONTH(ngayLap) = ?";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                doanhThu += rs.getInt(1);
            }
            JDBCUtil.closeConnection(c);
            return doanhThu;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
