package LichKhamBenhOnline.NhanVien;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LichKham {
    private String maLich;
    private String tenBacSi;
    private LocalDateTime thoiGian;
    private boolean daXacNhan;
    private List<String> danhSachBenhNhan = new ArrayList<>();

    public LichKham(String maLich, String tenBacSi, LocalDateTime thoiGian) {
        this.maLich = maLich;
        this.tenBacSi = tenBacSi;
        this.thoiGian = thoiGian;
        this.daXacNhan = false;
    }

    public String getMaLich() {
        return maLich;
    }

    public String getTenBacSi() {
        return tenBacSi;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public boolean isDaXacNhan() {
        return daXacNhan;
    }

    public void setDaXacNhan(boolean daXacNhan) {
        this.daXacNhan = daXacNhan;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setTenBacSi(String tenBacSi) {
        this.tenBacSi = tenBacSi;
    }

    public List<String> getDanhSachBenhNhan() {
        return danhSachBenhNhan;
    }

    public void themBenhNhan(String tenBN) {
        danhSachBenhNhan.add(tenBN);
    }

    @Override
    public String toString() {
        return maLich + "," + tenBacSi + "," + thoiGian + "," + daXacNhan + "," + String.join(";", danhSachBenhNhan);
    }

    public static LichKham fromString(String line) {
        String[] parts = line.split(",", 5);
        LichKham lk = new LichKham(parts[0], parts[1], LocalDateTime.parse(parts[2]));
        lk.setDaXacNhan(Boolean.parseBoolean(parts[3]));
        if (parts.length == 5 && !parts[4].isEmpty()) {
            for (String bn : parts[4].split(";")) {
                lk.themBenhNhan(bn);
            }
        }
        return lk;
    }

    public String hienThi() {
        return "Mã: " + maLich + " | Bác sĩ: " + tenBacSi + " | Thời gian: " + thoiGian +
               " | Xác nhận: " + (daXacNhan ? "✅" : "❌") + " | BN: " + danhSachBenhNhan.size();
    }
}