package DTO;

import java.util.ArrayList;

public class ThongKe {

    private int tongDoanhThu;
    private ArrayList<Integer> DoanhThuCacThang;
    private ArrayList<KhachHang> listTopKhachHang;
    private ArrayList<SPDaBan> sPDaBans;
    private ArrayList<Double> PhanTramDoanhThuTheoQuy;
    private int tongLoiNhuan;
    private ArrayList<Integer> LoiNhuanCacThang;
    private ArrayList<Double> PhanTramLoiNhuanTheoQuy;

    public int getTongLoiNhuan() {
        return tongLoiNhuan;
    }

    public void setTongLoiNhuan(int tongLoiNhuan) {
        this.tongLoiNhuan = tongLoiNhuan;
    }

    public ArrayList<Integer> getLoiNhuanCacThang() {
        return LoiNhuanCacThang;
    }

    public void setLoiNhuanCacThang(ArrayList<Integer> loiNhuanCacThang) {
        LoiNhuanCacThang = loiNhuanCacThang;
    }

    public ArrayList<Double> getPhanTramLoiNhuanTheoQuy() {
        return PhanTramLoiNhuanTheoQuy;
    }

    public void setPhanTramLoiNhuanTheoQuy(ArrayList<Double> phanTramLoiNhuanTheoQuy) {
        PhanTramLoiNhuanTheoQuy = phanTramLoiNhuanTheoQuy;
    }

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

    public ThongKe(ArrayList<Integer> DoanhThuCacThang,ArrayList<Integer> LoiNhuanCacThang,
     int tongDoanhThu, int tongLoiNhuan, ArrayList<KhachHang> listTopKhachHang, ArrayList<SPDaBan> sPDaBans,
     ArrayList<Double> PhanTramDoanhThuTheoQuy, ArrayList<Double> PhanTramLoiNhuanTheoQuy) {

        this.DoanhThuCacThang = DoanhThuCacThang;
        this.LoiNhuanCacThang = LoiNhuanCacThang;
        this.tongDoanhThu = tongDoanhThu;
        this.tongLoiNhuan = tongLoiNhuan;
        this.listTopKhachHang = listTopKhachHang;
        this.sPDaBans = sPDaBans;
        this.PhanTramDoanhThuTheoQuy = PhanTramDoanhThuTheoQuy;
        this.PhanTramLoiNhuanTheoQuy = PhanTramLoiNhuanTheoQuy;
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
