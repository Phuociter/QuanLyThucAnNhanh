package DTO;

import java.util.ArrayList;

public class ThongKe {

    private int tongDoanhThu;
    private ArrayList<Integer> DoanhThuCacThang;
    private ArrayList<KhachHang> listTopKhachHang;
    private ArrayList<SPDaBan> sPDaBans;
    private ArrayList<Double> PhanTramDoanhThuTheoQuy;

    public ThongKe() {
    }

    public ArrayList<Integer> getDoanhThuCacThang() {
        return DoanhThuCacThang;
    }

    public void setDoanhThuCacThang(ArrayList<Integer> DoanhThuCacThang) {
        this.DoanhThuCacThang = DoanhThuCacThang;
    }

    public ArrayList<KhachHang> getListTopKhachHang() {
        return listTopKhachHang;
    }

    public void setListTopKhachHang(ArrayList<KhachHang> listTopKhachHang) {
        this.listTopKhachHang = listTopKhachHang;
    }

    public ArrayList<SPDaBan> getsPDaBans() {
        return sPDaBans;
    }

    public void setsPDaBans(ArrayList<SPDaBan> sPDaBans) {
        this.sPDaBans = sPDaBans;
    }

    public ThongKe(ArrayList<Integer> DoanhThuCacThang, int tongDoanhThu, ArrayList<KhachHang> listTopKhachHang, ArrayList<SPDaBan> sPDaBans, ArrayList<Double> PhanTramDoanhThuTheoQuy) {
        this.DoanhThuCacThang = DoanhThuCacThang;
        this.tongDoanhThu = tongDoanhThu;
        this.listTopKhachHang = listTopKhachHang;
        this.sPDaBans = sPDaBans;
        this.PhanTramDoanhThuTheoQuy = PhanTramDoanhThuTheoQuy;
    }

    public int getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(int tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public ArrayList<Double> getPhanTramDoanhThuTheoQuy() {
        return PhanTramDoanhThuTheoQuy;
    }

    public void setPhanTramDoanhThuTheoQuy(ArrayList<Double> PhanTramDoanhThuTheoQuy) {
        this.PhanTramDoanhThuTheoQuy = PhanTramDoanhThuTheoQuy;
    }
}
