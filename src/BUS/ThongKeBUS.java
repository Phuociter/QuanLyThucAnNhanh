package BUS;

import Custom.dialog;
import DAO.ThongKeDAO;
import DTO.KhachHang;
import DTO.SPDaBan;
import DTO.ThongKe;
import java.util.ArrayList;

public class ThongKeBUS {

    ThongKeDAO thongKeDAO = new ThongKeDAO();

    public ThongKeBUS() {
    }

    public ThongKe thongKe(int year) {
        ArrayList<SPDaBan> sPDaBans = thongKeDAO.getListSanPhamDaBan();
        if (sPDaBans == null) {
            new dialog("Lỗi thống kê sản phẩm", dialog.ERROR_DIALOG);
            return null;
        }
        ArrayList<Integer> DoanhThuCacThang = getDoanhThuCacThangTheoNam(year);
        if (DoanhThuCacThang == null) {
            return null;
        }

        ArrayList<KhachHang> TopChiTieuKH = getTopChiTieuKH();
        if (TopChiTieuKH == null) {
            new dialog("Lỗi thống kê chi tiêu khách hàng", dialog.ERROR_DIALOG);
            return null;
        }
        int tongDoanhThu = getTongDoanhThuNam(DoanhThuCacThang);
        ArrayList<Double> PhanTramDoanhThuTheoQuy = getPhanTramDoanhThuTheoQuy(DoanhThuCacThang, tongDoanhThu);
        return new ThongKe(DoanhThuCacThang, tongDoanhThu, TopChiTieuKH, sPDaBans, PhanTramDoanhThuTheoQuy);
    }

    private ArrayList<Integer> getDoanhThuCacThangTheoNam(int year) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int tongTien = thongKeDAO.getDoanhThuThangTrongNam(i + 1, year);
            if (tongTien == -1) {
                new dialog("Lỗi thống kê doanh thu tháng " + (i + 1), dialog.ERROR_DIALOG);
                return null;
            }
            arrayList.add(tongTien);

        }
        return arrayList;
    }

    private int getTongDoanhThuNam(ArrayList<Integer> DoanhThuCacThang) {
        int tongDoanhThu = 0;
        for (Integer doanhThu : DoanhThuCacThang) {
            tongDoanhThu += doanhThu;
        }
        return tongDoanhThu;
    }

    private ArrayList<KhachHang> getTopChiTieuKH() {
        return thongKeDAO.getListTopChiTieuKhachHang();
    }

    private ArrayList<Double> getPhanTramDoanhThuTheoQuy(ArrayList<Integer> DoanhThuCacThang, int tongDoanhThu) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int doanhThuQuy = 0;
            for (int j = 0; j < 3; j++) {
                doanhThuQuy += DoanhThuCacThang.get(i * 3 + j);
            }
            double phanTram = (double) doanhThuQuy / tongDoanhThu * 100.0;
            arrayList.add(phanTram);
        }
        return arrayList;
    }
}
