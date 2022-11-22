package com.google.api.client.util;

import com.google.common.base.Strings;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateTime implements Serializable {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Pattern RFC3339_PATTERN = Pattern.compile(RFC3339_REGEX);
    private static final String RFC3339_REGEX = "(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d{1,9})?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?";
    private static final long serialVersionUID = 1;
    private final boolean dateOnly;
    private final int tzShift;
    private final long value;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DateTime(Date date, TimeZone zone) {
        this(false, date.getTime(), zone == null ? null : Integer.valueOf(zone.getOffset(date.getTime()) / 60000));
    }

    public DateTime(long value2) {
        this(false, value2, (Integer) null);
    }

    public DateTime(Date value2) {
        this(value2.getTime());
    }

    public DateTime(long value2, int tzShift2) {
        this(false, value2, Integer.valueOf(tzShift2));
    }

    public DateTime(boolean dateOnly2, long value2, Integer tzShift2) {
        int offset;
        this.dateOnly = dateOnly2;
        this.value = value2;
        if (dateOnly2) {
            offset = 0;
        } else {
            offset = tzShift2 == null ? TimeZone.getDefault().getOffset(value2) / 60000 : tzShift2.intValue();
        }
        this.tzShift = offset;
    }

    public DateTime(String value2) {
        DateTime dateTime = parseRfc3339(value2);
        this.dateOnly = dateTime.dateOnly;
        this.value = dateTime.value;
        this.tzShift = dateTime.tzShift;
    }

    public long getValue() {
        return this.value;
    }

    public boolean isDateOnly() {
        return this.dateOnly;
    }

    public int getTimeZoneShift() {
        return this.tzShift;
    }

    public String toStringRfc3339() {
        StringBuilder sb = new StringBuilder();
        Calendar dateTime = new GregorianCalendar(GMT);
        dateTime.setTimeInMillis(this.value + (((long) this.tzShift) * 60000));
        appendInt(sb, dateTime.get(1), 4);
        sb.append('-');
        appendInt(sb, dateTime.get(2) + 1, 2);
        sb.append('-');
        appendInt(sb, dateTime.get(5), 2);
        if (!this.dateOnly) {
            sb.append('T');
            appendInt(sb, dateTime.get(11), 2);
            sb.append(':');
            appendInt(sb, dateTime.get(12), 2);
            sb.append(':');
            appendInt(sb, dateTime.get(13), 2);
            if (dateTime.isSet(14)) {
                sb.append('.');
                appendInt(sb, dateTime.get(14), 3);
            }
            if (this.tzShift == 0) {
                sb.append('Z');
            } else {
                int absTzShift = this.tzShift;
                if (this.tzShift > 0) {
                    sb.append('+');
                } else {
                    sb.append('-');
                    absTzShift = -absTzShift;
                }
                appendInt(sb, absTzShift / 60, 2);
                sb.append(':');
                appendInt(sb, absTzShift % 60, 2);
            }
        }
        return sb.toString();
    }

    public String toString() {
        return toStringRfc3339();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DateTime)) {
            return false;
        }
        DateTime other = (DateTime) o;
        if (this.dateOnly == other.dateOnly && this.value == other.value && this.tzShift == other.tzShift) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.dateOnly ? serialVersionUID : 0;
        jArr[2] = (long) this.tzShift;
        return Arrays.hashCode(jArr);
    }

    public static DateTime parseRfc3339(String str) {
        return parseRfc3339WithNanoSeconds(str).toDateTime();
    }

    public static SecondsAndNanos parseRfc3339ToSecondsAndNanos(String str) {
        return parseRfc3339WithNanoSeconds(str).toSecondsAndNanos();
    }

    public static final class SecondsAndNanos implements Serializable {
        private final int nanos;
        private final long seconds;

        public static SecondsAndNanos ofSecondsAndNanos(long seconds2, int nanos2) {
            return new SecondsAndNanos(seconds2, nanos2);
        }

        private SecondsAndNanos(long seconds2, int nanos2) {
            this.seconds = seconds2;
            this.nanos = nanos2;
        }

        public long getSeconds() {
            return this.seconds;
        }

        public int getNanos() {
            return this.nanos;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SecondsAndNanos that = (SecondsAndNanos) o;
            if (this.seconds == that.seconds && this.nanos == that.nanos) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.seconds), Integer.valueOf(this.nanos)});
        }

        public String toString() {
            return String.format("Seconds: %d, Nanos: %d", new Object[]{Long.valueOf(this.seconds), Integer.valueOf(this.nanos)});
        }
    }

    private static class Rfc3339ParseResult implements Serializable {
        private final int nanos;
        private final long seconds;
        private final boolean timeGiven;
        private final Integer tzShift;

        private Rfc3339ParseResult(long seconds2, int nanos2, boolean timeGiven2, Integer tzShift2) {
            this.seconds = seconds2;
            this.nanos = nanos2;
            this.timeGiven = timeGiven2;
            this.tzShift = tzShift2;
        }

        /* access modifiers changed from: private */
        public DateTime toDateTime() {
            return new DateTime(!this.timeGiven, TimeUnit.SECONDS.toMillis(this.seconds) + TimeUnit.NANOSECONDS.toMillis((long) this.nanos), this.tzShift);
        }

        /* access modifiers changed from: private */
        public SecondsAndNanos toSecondsAndNanos() {
            return new SecondsAndNanos(this.seconds, this.nanos);
        }
    }

    private static Rfc3339ParseResult parseRfc3339WithNanoSeconds(String str) throws NumberFormatException {
        Matcher matcher = RFC3339_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new NumberFormatException("Invalid date/time format: " + str);
        }
        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2)) - 1;
        int day = Integer.parseInt(matcher.group(3));
        boolean isTimeGiven = matcher.group(4) != null;
        String tzShiftRegexGroup = matcher.group(9);
        boolean isTzShiftGiven = tzShiftRegexGroup != null;
        int hourOfDay = 0;
        int minute = 0;
        int second = 0;
        int nanoseconds = 0;
        int tzShiftInteger = null;
        if (!isTzShiftGiven || isTimeGiven) {
            if (isTimeGiven) {
                hourOfDay = Integer.parseInt(matcher.group(5));
                minute = Integer.parseInt(matcher.group(6));
                second = Integer.parseInt(matcher.group(7));
                if (matcher.group(8) != null) {
                    nanoseconds = Integer.parseInt(Strings.padEnd(matcher.group(8).substring(1), 9, '0'));
                }
            }
            Calendar dateTime = new GregorianCalendar(GMT);
            dateTime.clear();
            dateTime.set(year, month, day, hourOfDay, minute, second);
            long value2 = dateTime.getTimeInMillis();
            if (isTimeGiven && isTzShiftGiven) {
                if (Character.toUpperCase(tzShiftRegexGroup.charAt(0)) != 'Z') {
                    int tzShift2 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
                    if (matcher.group(10).charAt(0) == '-') {
                        tzShift2 = -tzShift2;
                    }
                    value2 -= ((long) tzShift2) * 60000;
                    tzShiftInteger = Integer.valueOf(tzShift2);
                } else {
                    tzShiftInteger = 0;
                }
            }
            return new Rfc3339ParseResult(value2 / 1000, nanoseconds, isTimeGiven, tzShiftInteger);
        }
        throw new NumberFormatException("Invalid date/time format, cannot specify time zone shift without specifying time: " + str);
    }

    private static void appendInt(StringBuilder sb, int num, int numDigits) {
        if (num < 0) {
            sb.append('-');
            num = -num;
        }
        int x = num;
        while (x > 0) {
            x /= 10;
            numDigits--;
        }
        for (int i = 0; i < numDigits; i++) {
            sb.append('0');
        }
        if (num != 0) {
            sb.append(num);
        }
    }
}
