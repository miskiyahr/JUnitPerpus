package id.perpus;

import java.time.LocalDate;

public class Buku implements Comparable<Buku> {
    private final String judul;
    private final String pengarang;
    private final LocalDate tanggalTerbit;
    private LocalDate mulaiBaca;
    private LocalDate selesaiBaca;

    public Buku(String judul, String pengarang, LocalDate tanggalTerbit) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tanggalTerbit = tanggalTerbit;
    }
    
    public String getjudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public LocalDate getTanggalTerbit() {
        return tanggalTerbit;
    }

    public boolean isRead() {
        return mulaiBaca != null && selesaiBaca != null;
    }

    public boolean isProgress() {
        return mulaiBaca != null && selesaiBaca == null;
    }
}
