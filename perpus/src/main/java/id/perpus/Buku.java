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
}
