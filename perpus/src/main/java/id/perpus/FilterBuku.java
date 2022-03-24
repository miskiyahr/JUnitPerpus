package id.perpus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface FilterBuku {
  boolean apply(Buku b);
}

