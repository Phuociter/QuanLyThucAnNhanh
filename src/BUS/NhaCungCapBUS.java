/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Custom.InputValidator;
import Custom.dialog;
import DAO.NhaCungCapDAO;
import DTO.NhaCungCap;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class NhaCungCapBUS {

    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

    public NhaCungCapBUS() {
    }

    public ArrayList<NhaCungCap> getList() {
        return nhaCungCapDAO.selectAll();
    }

    private boolean CheckEmpty(NhaCungCap nhaCungCap, String content) {
        if (InputValidator.IsEmpty(nhaCungCap.getTenNCC()) || InputValidator.IsEmpty(nhaCungCap.getDiaChi()) || InputValidator.IsEmpty(nhaCungCap.getDienThoai())) {
            new dialog(content, dialog.ERROR_DIALOG);
            return true;
        }
        return false;
    }

    private boolean checkPhoneNumber(String phoneNumber, String content) {
        if (!InputValidator.isValidPhoneNumber(phoneNumber)) {
            new dialog(content, dialog.ERROR_DIALOG);
            return false;
        }
        return true;
    }

    private boolean checkDuplicateName(NhaCungCap nhaCungCap, String content) {
        ArrayList<NhaCungCap> nhaCungCaps = nhaCungCapDAO.selectAll();
        for (NhaCungCap ncc : nhaCungCaps) {
            if (nhaCungCap.getMaNCC() == ncc.getMaNCC()) {
                continue;
            }
            if (nhaCungCap.getTenNCC().equals(ncc.getTenNCC())) {
                new dialog(content, dialog.ERROR_DIALOG);
                return true;
            }
        }
        return false;
    }

    private boolean checkInfors(NhaCungCap nhaCungCap) {
        if (CheckEmpty(nhaCungCap, "Không được để trống thông tin")) {
            return false;
        }
        if (!checkPhoneNumber(nhaCungCap.getDienThoai(), "Số điện thoại không hợp lệ")) {
            return false;
        }
        if (checkDuplicateName(nhaCungCap, "Tên đã tồn tại")) {
            return false;
        }
        return true;
    }

    public boolean Update(NhaCungCap nhaCungCap) {
        if (!checkInfors(nhaCungCap)) {
            return false;
        }
        int rs = nhaCungCapDAO.update(nhaCungCap);
        if (rs == 0) {
            new dialog("Sửa không thành công", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Sửa thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public boolean Insert(NhaCungCap nhaCungCap) {
        if (!checkInfors(nhaCungCap)) { //kiểm tra dữ liệu đầu vào 
            return false;
        }
        int rs = nhaCungCapDAO.insert(nhaCungCap);// tiến hành thêm nhà cung cấp
        if (rs == 0) {
            new dialog("Thêm không thành công", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Thêm thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public boolean Delete(NhaCungCap nhaCungCap) {
        int rs = nhaCungCapDAO.delete(nhaCungCap);
        if (rs == 0) {
            new dialog("Xóa không thành công", dialog.ERROR_DIALOG);
            return false;
        }
        new dialog("Xóa thành công", dialog.SUCCESS_DIALOG);
        return true;
    }

    public int getNextMaNCC() {
        return nhaCungCapDAO.getNewMa() + 1;
    }

}
