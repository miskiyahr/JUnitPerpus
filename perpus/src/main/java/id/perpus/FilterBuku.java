package id.perpus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface FilterBuku {
  boolean apply(Buku b);
}

class FilterTahunTerbit implements FilterBuku {
  private Function<LocalDate, Boolean> comparison;

  static FilterTahunTerbit After(int tahun) {
    final LocalDate tanggal = LocalDate.of(tahun, 12, 31);
    FilterTahunTerbit filter = new FilterTahunTerbit();
    filter.comparison = tanggal::isBefore;
    return filter;
  }
  
  static FilterTahunTerbit Before(int tahun) {
    final LocalDate tanggal = LocalDate.of(tahun, 1, 1);
    FilterTahunTerbit filter = new FilterTahunTerbit();
    filter.comparison = tanggal::isAfter;
    return filter;
  }
  
  @Override
  public boolean apply(final Buku b) {
    return b!=null && comparison.apply(b.getTanggalTerbit());
  }
}



