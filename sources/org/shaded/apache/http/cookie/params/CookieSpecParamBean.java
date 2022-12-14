package org.shaded.apache.http.cookie.params;

import java.util.Collection;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.params.HttpAbstractParamBean;
import org.shaded.apache.http.params.HttpParams;

@NotThreadSafe
public class CookieSpecParamBean extends HttpAbstractParamBean {
    public CookieSpecParamBean(HttpParams params) {
        super(params);
    }

    public void setDatePatterns(Collection<String> patterns) {
        this.params.setParameter(CookieSpecPNames.DATE_PATTERNS, patterns);
    }

    public void setSingleHeader(boolean singleHeader) {
        this.params.setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, singleHeader);
    }
}
