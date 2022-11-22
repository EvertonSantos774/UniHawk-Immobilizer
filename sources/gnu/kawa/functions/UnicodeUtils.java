package gnu.kawa.functions;

import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.text.BreakIterator;

public class UnicodeUtils {

    /* renamed from: Cc */
    static final Symbol f57Cc;

    /* renamed from: Cf */
    static final Symbol f58Cf;

    /* renamed from: Cn */
    static final Symbol f59Cn;

    /* renamed from: Co */
    static final Symbol f60Co;

    /* renamed from: Cs */
    static final Symbol f61Cs;

    /* renamed from: Ll */
    static final Symbol f62Ll;

    /* renamed from: Lm */
    static final Symbol f63Lm;

    /* renamed from: Lo */
    static final Symbol f64Lo;

    /* renamed from: Lt */
    static final Symbol f65Lt;

    /* renamed from: Lu */
    static final Symbol f66Lu;

    /* renamed from: Mc */
    static final Symbol f67Mc;

    /* renamed from: Me */
    static final Symbol f68Me;

    /* renamed from: Mn */
    static final Symbol f69Mn;

    /* renamed from: Nd */
    static final Symbol f70Nd;

    /* renamed from: Nl */
    static final Symbol f71Nl;

    /* renamed from: No */
    static final Symbol f72No;

    /* renamed from: Pc */
    static final Symbol f73Pc;

    /* renamed from: Pd */
    static final Symbol f74Pd;

    /* renamed from: Pe */
    static final Symbol f75Pe;

    /* renamed from: Pf */
    static final Symbol f76Pf;

    /* renamed from: Pi */
    static final Symbol f77Pi;

    /* renamed from: Po */
    static final Symbol f78Po;

    /* renamed from: Ps */
    static final Symbol f79Ps;

    /* renamed from: Sc */
    static final Symbol f80Sc;

    /* renamed from: Sk */
    static final Symbol f81Sk;

    /* renamed from: Sm */
    static final Symbol f82Sm;

    /* renamed from: So */
    static final Symbol f83So;

    /* renamed from: Zl */
    static final Symbol f84Zl;

    /* renamed from: Zp */
    static final Symbol f85Zp;

    /* renamed from: Zs */
    static final Symbol f86Zs;

    public static boolean isWhitespace(int ch) {
        if (ch == 32 || (ch >= 9 && ch <= 13)) {
            return true;
        }
        if (ch < 133) {
            return false;
        }
        if (ch == 133 || ch == 160 || ch == 5760 || ch == 6158) {
            return true;
        }
        if (ch < 8192 || ch > 12288) {
            return false;
        }
        if (ch <= 8202 || ch == 8232 || ch == 8233 || ch == 8239 || ch == 8287 || ch == 12288) {
            return true;
        }
        return false;
    }

    public static String capitalize(String str) {
        StringBuilder sbuf = new StringBuilder();
        BreakIterator wb = BreakIterator.getWordInstance();
        wb.setText(str);
        int start = wb.first();
        for (int end = wb.next(); end != -1; end = wb.next()) {
            boolean isWord = false;
            int p = start;
            while (true) {
                if (p >= end) {
                    break;
                } else if (Character.isLetter(str.codePointAt(p))) {
                    isWord = true;
                    break;
                } else {
                    p++;
                }
            }
            if (!isWord) {
                sbuf.append(str, start, end);
            } else {
                sbuf.append(Character.toTitleCase(str.charAt(start)));
                sbuf.append(str.substring(start + 1, end).toLowerCase());
            }
            start = end;
        }
        return sbuf.toString();
    }

    public static String foldCase(CharSequence str) {
        int len = str.length();
        if (len == 0) {
            return "";
        }
        StringBuilder sbuf = null;
        int start = 0;
        int i = 0;
        while (true) {
            int ch = i == len ? -1 : str.charAt(i);
            boolean sigma = ch == 931 || ch == 963 || ch == 962;
            if (ch < 0 || ch == 304 || ch == 305 || sigma) {
                if (sbuf == null && ch >= 0) {
                    sbuf = new StringBuilder();
                }
                if (i > start) {
                    String converted = str.subSequence(start, i).toString().toUpperCase().toLowerCase();
                    if (sbuf == null) {
                        return converted;
                    }
                    sbuf.append(converted);
                }
                if (ch < 0) {
                    return sbuf.toString();
                }
                if (sigma) {
                    ch = 963;
                }
                sbuf.append((char) ch);
                start = i + 1;
            }
            i++;
        }
    }

    public static Symbol generalCategory(int ch) {
        switch (Character.getType(ch)) {
            case 1:
                return f66Lu;
            case 2:
                return f62Ll;
            case 3:
                return f65Lt;
            case 4:
                return f63Lm;
            case 5:
                return f64Lo;
            case 6:
                return f69Mn;
            case 7:
                return f68Me;
            case 8:
                return f67Mc;
            case 9:
                return f70Nd;
            case 10:
                return f71Nl;
            case 11:
                return f72No;
            case 12:
                return f86Zs;
            case 13:
                return f84Zl;
            case 14:
                return f85Zp;
            case 15:
                return f57Cc;
            case 16:
                return f58Cf;
            case 18:
                return f60Co;
            case 19:
                return f61Cs;
            case 20:
                return f74Pd;
            case 21:
                return f79Ps;
            case 22:
                return f75Pe;
            case 23:
                return f73Pc;
            case 24:
                return f78Po;
            case 25:
                return f82Sm;
            case 26:
                return f80Sc;
            case 27:
                return f81Sk;
            case 28:
                return f83So;
            case 29:
                return f77Pi;
            case 30:
                return f76Pf;
            default:
                return f59Cn;
        }
    }

    static {
        Namespace empty = Namespace.EmptyNamespace;
        f67Mc = empty.getSymbol("Mc");
        f73Pc = empty.getSymbol("Pc");
        f57Cc = empty.getSymbol("Cc");
        f80Sc = empty.getSymbol("Sc");
        f74Pd = empty.getSymbol("Pd");
        f70Nd = empty.getSymbol("Nd");
        f68Me = empty.getSymbol("Me");
        f75Pe = empty.getSymbol("Pe");
        f76Pf = empty.getSymbol("Pf");
        f58Cf = empty.getSymbol("Cf");
        f77Pi = empty.getSymbol("Pi");
        f71Nl = empty.getSymbol("Nl");
        f84Zl = empty.getSymbol("Zl");
        f62Ll = empty.getSymbol("Ll");
        f82Sm = empty.getSymbol("Sm");
        f63Lm = empty.getSymbol("Lm");
        f81Sk = empty.getSymbol("Sk");
        f69Mn = empty.getSymbol("Mn");
        f64Lo = empty.getSymbol("Lo");
        f72No = empty.getSymbol("No");
        f78Po = empty.getSymbol("Po");
        f83So = empty.getSymbol("So");
        f85Zp = empty.getSymbol("Zp");
        f60Co = empty.getSymbol("Co");
        f86Zs = empty.getSymbol("Zs");
        f79Ps = empty.getSymbol("Ps");
        f61Cs = empty.getSymbol("Cs");
        f65Lt = empty.getSymbol("Lt");
        f59Cn = empty.getSymbol("Cn");
        f66Lu = empty.getSymbol("Lu");
    }
}
