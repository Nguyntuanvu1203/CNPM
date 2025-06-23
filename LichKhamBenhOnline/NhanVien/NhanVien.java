package LichKhamBenhOnline.NhanVien;

public class NhanVien {
    private String username;
    private String password;

    public NhanVien(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}