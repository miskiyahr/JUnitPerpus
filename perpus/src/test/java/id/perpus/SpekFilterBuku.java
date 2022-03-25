package id.perpus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

interface FilterBoundaryTests {
    FilterBuku get();

}

@Tag("Filter")
@DisplayName("Filter Berdasarkan")
class SpekFilterBuku {
    private Buku book1;
    private Buku book2;

    @BeforeEach 
    void init() {
    	book1 = new Buku("The Pragmatic Programmer: Your Journey to Mastery, 20th Anniversary Edition", "Andy Hunt and Dave Thomas", LocalDate.of(2019, Month.JULY, 30));
        book2 = new Buku("Cracking the Coding Interview", "Gayle Laakmann McDowell", LocalDate.of(2008, Month.JUNE, 9));
    } 

    @Nested
    @DisplayName("Buku diterbitkan setelah tahun 2010")
    class BookPublishedAfterFilterSpec implements FilterBoundaryTests {
        FilterBuku filter;

        @BeforeEach
        void init() {
            filter = FilterTahunTerbit.After(2010);
        }

        @Override
        public FilterBuku get() {
            return filter;
        }

        @Test
        @DisplayName("Kecocokan buku")
        void validasiTanggalTerbitSetelahTahun() {
            assertTrue(filter.apply(book1));
            assertFalse(filter.apply(book2));
        }
    }

    @Nested
    @DisplayName("Buku diterbitkan sebelum tahun 2010")
    class BukuTerbitSebelumFilter implements FilterBoundaryTests {

        FilterBuku filter;

        @BeforeEach
        void init() {
            filter = FilterTahunTerbit.Before(2010);
        }

        @Override
        public FilterBuku get() {
            return filter;
        }

        @Test
        @DisplayName("Kecocokan buku")
        void validasiTanggalTerbitSebelumTahun() {
            assertFalse(filter.apply(book1));
            assertTrue(filter.apply(book2));
        }
    }

    @Test
    @DisplayName("Kriteria gabungan memanggil banyak filter")
    void shouldFilterOnMultiplesCriteria() {
        FilterBuku mockedFilter = Mockito.mock(FilterBuku.class);
        Mockito.when(mockedFilter.apply(book1)).thenReturn(true);
        FilterGabungan filterGabungan = new FilterGabungan();
        filterGabungan.tambahFilter(mockedFilter);
        filterGabungan.apply(book1);
        Mockito.verify(mockedFilter).apply(book1);
    }

    @Test
    @DisplayName("Kriteria gabungan memanggil semua kasus kegagalan")
    void shouldInvokeAllInFailure() {
        FilterGabungan filterGabungan = new FilterGabungan();

        FilterBuku invokedMockedFilter = Mockito.mock(FilterBuku.class);
        Mockito.when(invokedMockedFilter.apply(book1)).thenReturn(false);
        filterGabungan.tambahFilter(invokedMockedFilter);

        FilterBuku secondInvokedMockedFilter = Mockito.mock(FilterBuku.class);
        Mockito.when(secondInvokedMockedFilter.apply(book1)).thenReturn(true);
        filterGabungan.tambahFilter(secondInvokedMockedFilter);

        assertFalse(filterGabungan.apply(book1));
        Mockito.verify(invokedMockedFilter).apply(book1);
        Mockito.verify(secondInvokedMockedFilter).apply(book1);
    }

    @Test
    @DisplayName("Kriteria gabungan memanggil semua filter")
    void shouldInvokeAllFilters() {
    	FilterGabungan filterGabungan = new FilterGabungan();
        FilterBuku firstInvokedMockedFilter = Mockito.mock(FilterBuku.class);
        Mockito.when(firstInvokedMockedFilter.apply(book1)).thenReturn(true);
        filterGabungan.tambahFilter(firstInvokedMockedFilter);

        FilterBuku secondInvokedMockedFilter = Mockito.mock(FilterBuku.class);
        Mockito.when(secondInvokedMockedFilter.apply(book1)).thenReturn(true);
        filterGabungan.tambahFilter(secondInvokedMockedFilter);
        assertTrue(filterGabungan.apply(book1));
        Mockito.verify(firstInvokedMockedFilter).apply(book1);
        Mockito.verify(secondInvokedMockedFilter).apply(book1);
    }

}

