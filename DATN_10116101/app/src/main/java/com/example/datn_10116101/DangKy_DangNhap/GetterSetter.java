package com.example.datn_10116101.DangKy_DangNhap;

public class GetterSetter {
    private int id;
    private String TaiKhoan;
    private String MatKhau;
    private String TenDangNhap;

    public  GetterSetter (int id, String TaiKhoan , String MatKhau, String TenDangNhap)
    {
        this.id = id ;
        this.TaiKhoan =TaiKhoan;
        this.MatKhau = MatKhau;
        this.TenDangNhap = TenDangNhap;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }
}
