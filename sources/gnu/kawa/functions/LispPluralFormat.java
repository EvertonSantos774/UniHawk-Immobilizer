package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

/* compiled from: LispFormat */
class LispPluralFormat extends ReportFormat {
    boolean backup;

    /* renamed from: y */
    boolean f54y;

    LispPluralFormat() {
    }

    public static LispPluralFormat getInstance(boolean backup2, boolean y) {
        LispPluralFormat fmt = new LispPluralFormat();
        fmt.backup = backup2;
        fmt.f54y = y;
        return fmt;
    }

    public int format(Object[] args, int start, Writer dst, FieldPosition fpos) throws IOException {
        if (this.backup) {
            start--;
        }
        int start2 = start + 1;
        boolean plural = args[start] != IntNum.one();
        if (this.f54y) {
            print(dst, plural ? "ies" : "y");
        } else if (plural) {
            dst.write(115);
        }
        return start2;
    }
}
