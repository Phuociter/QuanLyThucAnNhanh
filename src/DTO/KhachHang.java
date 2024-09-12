package DTO;

public class KhachHang {

    private int maKH;
    private String ten;
    private String gioiTinh;
    private String dienThoai;
    private String email;
    private String diaChi;
    private int tongChiTieu;
    private int trangThai;
  

    public KhachHang() {
    }

    public KhachHang(int maKH, String ten, String diaChi, String gioiTinh, String dienThoai,String email, int tongChiTieu, int trangThai) {
        this.maKH = maKH;
        this.ten = ten;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.tongChiTieu = tongChiTieu;
        this.dienThoai = dienThoai;
        this.email=email;
        this.trangThai = trangThai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(int tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }

}
