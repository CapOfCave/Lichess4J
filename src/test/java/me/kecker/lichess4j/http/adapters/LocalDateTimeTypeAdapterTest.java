package me.kecker.lichess4j.http.adapters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeTypeAdapterTest {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneOffset.UTC;
    private static final long DATE_EPOCH_MILLIS = 1618500124000L;
    private static final LocalDateTime DATE_LOCAL_DATE_TIME_UTC = LocalDateTime.of(2021, 4, 15, 15,
            22, 04);
    private static final int ZONE_OFFSET_HOURS = 3;

    @Mock
    private JsonReader jsonReaderMock;
    @Mock
    private JsonWriter jsonWriterMock;

    private LocalDateTimeTypeAdapter objectUnderTest;

    @Before
    public void setup() {
        this.objectUnderTest = new LocalDateTimeTypeAdapter();
    }

    @Test
    public void read_happyDay_returnsLocalDateTime() throws IOException {
        try (MockedStatic<ZoneId> mockedZoneId = Mockito.mockStatic(ZoneId.class)) {
            mockedZoneId.when(ZoneId::systemDefault)
                    .thenReturn(DEFAULT_ZONE_ID);
            when(this.jsonReaderMock.nextLong()).thenReturn(DATE_EPOCH_MILLIS);

            LocalDateTime localDateTime = this.objectUnderTest.read(this.jsonReaderMock);

            assertEquals(DATE_LOCAL_DATE_TIME_UTC, localDateTime);
        }
    }

    @Test
    public void read_withZoneOffset_returnsLocalizedLocalDateTime() throws IOException {
        try (MockedStatic<ZoneId> mockedZoneId = Mockito.mockStatic(ZoneId.class)) {
            mockedZoneId.when(ZoneId::systemDefault)
                    .thenReturn(ZoneOffset.ofHours(ZONE_OFFSET_HOURS));
            when(this.jsonReaderMock.nextLong()).thenReturn(DATE_EPOCH_MILLIS);

            LocalDateTime localDateTime = this.objectUnderTest.read(this.jsonReaderMock);

            LocalDateTime expected = getOffsetLocalDateTime(ZONE_OFFSET_HOURS);
            assertEquals(expected, localDateTime);
        }
    }

    @Test
    public void read_readerNull_returnsNull() throws IOException {
        LocalDateTime localDateTime = this.objectUnderTest.read(null);
        assertEquals(null, localDateTime);
    }

    @Test
    public void write_happyDay_writesLocalDateTime() throws IOException {
        try (MockedStatic<ZoneId> mockedZoneId = Mockito.mockStatic(ZoneId.class)) {
            mockedZoneId.when(ZoneId::systemDefault)
                    .thenReturn(DEFAULT_ZONE_ID);
            when(this.jsonWriterMock.value(DATE_EPOCH_MILLIS)).thenReturn(this.jsonWriterMock);

            this.objectUnderTest.write(this.jsonWriterMock, DATE_LOCAL_DATE_TIME_UTC);

            verify(this.jsonWriterMock, times(1)).value(DATE_EPOCH_MILLIS);
        }
    }

    @Test
    public void write_withZoneOffset_writesLocalizedLocalDateTime() throws IOException {
        try (MockedStatic<ZoneId> mockedZoneId = Mockito.mockStatic(ZoneId.class)) {
            mockedZoneId.when(ZoneId::systemDefault)
                    .thenReturn(ZoneOffset.ofHours(ZONE_OFFSET_HOURS));

            when(this.jsonWriterMock.value(DATE_EPOCH_MILLIS)).thenReturn(this.jsonWriterMock);

            this.objectUnderTest.write(this.jsonWriterMock, getOffsetLocalDateTime(
                    ZONE_OFFSET_HOURS));

            verify(this.jsonWriterMock, times(1)).value(DATE_EPOCH_MILLIS);

        }
    }

    @Test
    public void write_valueNull_writesNull() throws IOException {
        this.objectUnderTest.write(this.jsonWriterMock, null);

        verify(this.jsonWriterMock, times(1)).nullValue();
        verify(this.jsonWriterMock, never()).value(Mockito.anyLong());
    }

    private LocalDateTime getOffsetLocalDateTime(int offset) {
        return DATE_LOCAL_DATE_TIME_UTC.plusHours(offset);
    }

}
