package DTO;

public class SPDaBan {
    private int maSP;
    private String tenSP;
    private int daBan;
    public SPDaBan() {
    }

    public SPDaBan(int maSP, String tenSP, int daBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.daBan = daBan;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getDaBan() {
        return daBan;
    }

    public void setDaBan(int daBan) {
        this.daBan = daBan;
    }

    
    
}
