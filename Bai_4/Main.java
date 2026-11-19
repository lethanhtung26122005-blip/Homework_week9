package Homework.Homework_week9.Bai_4;
import java.util.ArrayList;
import java.util.List;

// ================= (a) ADAPTER =================

// Target: interface hệ thống yêu cầu
interface Sorter {
    int[] sort(int[] arr);
}

// Adaptee: thư viện cũ, không sửa được
class LegacySorter {
    public int[] quickSort(int[] arr) {
        int[] a = arr.clone();          // không làm thay đổi mảng gốc
        quickSort(a, 0, a.length - 1);
        return a;
    }

    private void quickSort(int[] a, int low, int high) {
        if (low >= high) return;
        int pivot = a[(low + high) / 2];
        int i = low, j = high;
        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i <= j) {
                int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                i++; j--;
            }
        }
        quickSort(a, low, j);
        quickSort(a, i, high);
    }
}

// Adapter: cài đặt Sorter, gọi sang LegacySorter
class SorterAdapter implements Sorter {
    private final LegacySorter legacy;

    public SorterAdapter(LegacySorter legacy) {
        this.legacy = legacy;
    }

    @Override
    public int[] sort(int[] arr) {
        return legacy.quickSort(arr);   // chuyển sort() thành quickSort()
    }
}

// ================= (b) PROTOTYPE =================

class ReportTemplate implements Cloneable {
    private String title;
    private String footer;
    private List<String> sections;

    public ReportTemplate(String title, String footer, List<String> sections) {
        this.title = title;
        this.footer = footer;
        this.sections = new ArrayList<>(sections);
    }

    public void setTitle(String title) { this.title = title; }
    public void addSection(String s)   { sections.add(s); }

    // Sao chép sâu: tạo List mới cho bản sao
    @Override
    public ReportTemplate clone() {
        try {
            ReportTemplate copy = (ReportTemplate) super.clone(); // chép title, footer
            copy.sections = new ArrayList<>(this.sections);       // chép sâu danh sách
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);   // không xảy ra vì đã implements Cloneable
        }
    }

    public void print() {
        System.out.println("=== " + title + " ===");
        for (String s : sections) {
            System.out.println(" - " + s);
        }
        System.out.println("--- " + footer + " ---");
        System.out.println();
    }
}

// ================= MAIN =================

public class Main {
    public static void main(String[] args) {

        // ----- (a) Adapter -----
        System.out.println("##### (a) Adapter #####");
        Sorter sorter = new SorterAdapter(new LegacySorter());
        int[] arr = {5, 2, 9, 1, 7, 3};
        int[] sorted = sorter.sort(arr);

        System.out.println("Mang goc    : " + java.util.Arrays.toString(arr));
        System.out.println("Sau khi sort: " + java.util.Arrays.toString(sorted));

        // ----- (b) Prototype -----
        System.out.println("\n##### (b) Prototype #####");
        List<String> sections = new ArrayList<>();
        sections.add("Gioi thieu");
        sections.add("Noi dung");
        sections.add("Ket luan");

        ReportTemplate template = new ReportTemplate(
                "Bao cao mau", "Cong ty ABC - 2026", sections);

        ReportTemplate r1 = template.clone();
        r1.setTitle("Bao cao doanh thu Q1");
        r1.addSection("Phu luc doanh thu");     // chỉ ảnh hưởng r1

        ReportTemplate r2 = template.clone();
        r2.setTitle("Bao cao nhan su Q1");

        template.print();   // template gốc
        r1.print();
        r2.print();
    }
}