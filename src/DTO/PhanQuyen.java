package DTO;

public class PhanQuyen {

    private int maQuyen;
    private String tenQuyen;
    private int qlNhapHang;
    private int qlSanPham;
    private int qlNhanVien;
    private int qlKhachHang;
    private int thongKe;
    private int trangThai;

    public PhanQuyen() {
    }

    
    
    public PhanQuyen(int maQuyen, String tenQuyen, int qlNhapHang, int qlSanPham, int qlNhanVien, int qlKhachHang, int thongKe, int trangThai) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.qlNhapHang = qlNhapHang;
        this.qlSanPham = qlSanPham;
        this.qlNhanVien = qlNhanVien;
        this.qlKhachHang = qlKhachHang;
        this.thongKe = thongKe;
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public int getQlNhapHang() {
        return qlNhapHang;
    }

    public void setQlNhapHang(int qlNhapHang) {
        this.qlNhapHang = qlNhapHang;
    }

    public int getQlSanPham() {
        return qlSanPham;
    }

    public void setQlSanPham(int qlSanPham) {
        this.qlSanPham = qlSanPham;
    }

    public int getQlNhanVien() {
        return qlNhanVien;
    }

    public void setQlNhanVien(int qlNhanVien) {
        this.qlNhanVien = qlNhanVien;
    }

    public int getQlKhachHang() {
        return qlKhachHang;
    }

    public void setQlKhachHang(int qlKhachHang) {
        this.qlKhachHang = qlKhachHang;
    }

    public int getThongKe() {
        return thongKe;
    }

    public void setThongKe(int thongKe) {
        this.thongKe = thongKe;
    }

}
