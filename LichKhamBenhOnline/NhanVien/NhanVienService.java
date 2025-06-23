package LichKhamBenhOnline.NhanVien;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NhanVienService {
    static class LichKham {
        String maLich;
        String tenBacSi;
        LocalDateTime thoiGian;

        public LichKham(String maLich, String tenBacSi, LocalDateTime thoiGian) {
            this.maLich = maLich;
            this.tenBacSi = tenBacSi;
            this.thoiGian = thoiGian;
        }

        @Override
        public String toString() {
            return maLich + "," + tenBacSi + "," + thoiGian;
        }

        public String hienThi() {
            return "Mã lịch: " + maLich + " | Bác sĩ: " + tenBacSi + " | Thời gian: " + thoiGian;
        }
    }

    static List<LichKham> danhSachLich = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "lichkham.txt";

    public static void main(String[] args) {
        docTuFile();
        int chon;
        do {
            System.out.println("\n=== Quản lý lịch khám - Nhân viên ===");
            System.out.println("1. Thêm lịch khám");
            System.out.println("2. Hiển thị tất cả");
            System.out.println("3. Sửa lịch khám");
            System.out.println("4. Xoá lịch khám");
            System.out.println("5. Ghi ra file");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            chon = scanner.nextInt(); scanner.nextLine();

            switch (chon) {
                case 1: themLich(); break;
                case 2: hienThi(); break;
                case 3: suaLich(); break;
                case 4: xoaLich(); break;
                case 5: ghiRaFile(); break;
                case 0: System.out.println("👋 Tạm biệt!"); break;
                default: System.out.println("❌ Lựa chọn không hợp lệ.");
            }
        } while (chon != 0);
    }

    static void themLich() {
        System.out.print("Nhập mã lịch: ");
        String ma = scanner.nextLine();
        System.out.print("Nhập tên bác sĩ: ");
        String bs = scanner.nextLine();
        System.out.print("Nhập thời gian (yyyy-MM-dd HH:mm): ");
        String tg = scanner.nextLine();

        try {
            LocalDateTime thoigian = LocalDateTime.parse(tg.replace(" ", "T"));
            danhSachLich.add(new LichKham(ma, bs, thoigian));
            System.out.println("✅ Thêm thành công.");
        } catch (Exception e) {
            System.out.println("❌ Sai định dạng thời gian.");
        }
    }

    static void hienThi() {
        if (danhSachLich.isEmpty()) {
            System.out.println("📭 Danh sách trống.");
            return;
        }
        System.out.println("📋 Danh sách lịch khám:");
        for (LichKham l : danhSachLich) {
            System.out.println("- " + l.hienThi());
        }
    }

    static void suaLich() {
        System.out.print("Nhập mã lịch cần sửa: ");
        String ma = scanner.nextLine();
        for (LichKham l : danhSachLich) {
            if (l.maLich.equals(ma)) {
                System.out.print("Tên bác sĩ mới: ");
                l.tenBacSi = scanner.nextLine();
                System.out.print("Thời gian mới (yyyy-MM-dd HH:mm): ");
                String tg = scanner.nextLine();
                l.thoiGian = LocalDateTime.parse(tg.replace(" ", "T"));
                System.out.println("✅ Sửa thành công.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy mã lịch.");
    }

    static void xoaLich() {
        System.out.print("Nhập mã lịch cần xoá: ");
        String ma = scanner.nextLine();
        boolean removed = danhSachLich.removeIf(l -> l.maLich.equals(ma));
        if (removed) {
            System.out.println("🗑️ Xoá thành công.");
        } else {
            System.out.println("❌ Không tìm thấy mã lịch.");
        }
    }

    static void ghiRaFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (LichKham l : danhSachLich) {
                bw.write(l.toString());
                bw.newLine();
            }
            System.out.println("💾 Ghi file thành công.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file.");
        }
    }

    static void docTuFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    danhSachLich.add(new LichKham(parts[0], parts[1], LocalDateTime.parse(parts[2])));
                }
            }
            System.out.println("📂 Đọc file thành công.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file.");
        }
    }
}
