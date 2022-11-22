package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

@GwtIncompatible
@Beta
public abstract class LinearTransformation {
    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d);

    public static LinearTransformationBuilder mapping(double x1, double y1) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x1) && DoubleUtils.isFinite(y1));
        return new LinearTransformationBuilder(x1, y1);
    }

    public static final class LinearTransformationBuilder {

        /* renamed from: x1 */
        private final double f364x1;

        /* renamed from: y1 */
        private final double f365y1;

        private LinearTransformationBuilder(double x1, double y1) {
            this.f364x1 = x1;
            this.f365y1 = y1;
        }

        public LinearTransformation and(double x2, double y2) {
            boolean z = true;
            Preconditions.checkArgument(DoubleUtils.isFinite(x2) && DoubleUtils.isFinite(y2));
            if (x2 != this.f364x1) {
                return withSlope((y2 - this.f365y1) / (x2 - this.f364x1));
            }
            if (y2 == this.f365y1) {
                z = false;
            }
            Preconditions.checkArgument(z);
            return new VerticalLinearTransformation(this.f364x1);
        }

        public LinearTransformation withSlope(double slope) {
            Preconditions.checkArgument(!Double.isNaN(slope));
            if (DoubleUtils.isFinite(slope)) {
                return new RegularLinearTransformation(slope, this.f365y1 - (this.f364x1 * slope));
            }
            return new VerticalLinearTransformation(this.f364x1);
        }
    }

    public static LinearTransformation vertical(double x) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x));
        return new VerticalLinearTransformation(x);
    }

    public static LinearTransformation horizontal(double y) {
        Preconditions.checkArgument(DoubleUtils.isFinite(y));
        return new RegularLinearTransformation(0.0d, y);
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    private static final class RegularLinearTransformation extends LinearTransformation {
        @LazyInit
        LinearTransformation inverse;
        final double slope;
        final double yIntercept;

        RegularLinearTransformation(double slope2, double yIntercept2) {
            this.slope = slope2;
            this.yIntercept = yIntercept2;
            this.inverse = null;
        }

        RegularLinearTransformation(double slope2, double yIntercept2, LinearTransformation inverse2) {
            this.slope = slope2;
            this.yIntercept = yIntercept2;
            this.inverse = inverse2;
        }

        public boolean isVertical() {
            return false;
        }

        public boolean isHorizontal() {
            return this.slope == 0.0d;
        }

        public double slope() {
            return this.slope;
        }

        public double transform(double x) {
            return (this.slope * x) + this.yIntercept;
        }

        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            if (result != null) {
                return result;
            }
            LinearTransformation result2 = createInverse();
            this.inverse = result2;
            return result2;
        }

        public String toString() {
            return String.format("y = %g * x + %g", new Object[]{Double.valueOf(this.slope), Double.valueOf(this.yIntercept)});
        }

        private LinearTransformation createInverse() {
            if (this.slope != 0.0d) {
                return new RegularLinearTransformation(1.0d / this.slope, (-1.0d * this.yIntercept) / this.slope, this);
            }
            return new VerticalLinearTransformation(this.yIntercept, this);
        }
    }

    private static final class VerticalLinearTransformation extends LinearTransformation {
        @LazyInit
        LinearTransformation inverse;

        /* renamed from: x */
        final double f366x;

        VerticalLinearTransformation(double x) {
            this.f366x = x;
            this.inverse = null;
        }

        VerticalLinearTransformation(double x, LinearTransformation inverse2) {
            this.f366x = x;
            this.inverse = inverse2;
        }

        public boolean isVertical() {
            return true;
        }

        public boolean isHorizontal() {
            return false;
        }

        public double slope() {
            throw new IllegalStateException();
        }

        public double transform(double x) {
            throw new IllegalStateException();
        }

        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            if (result != null) {
                return result;
            }
            LinearTransformation result2 = createInverse();
            this.inverse = result2;
            return result2;
        }

        public String toString() {
            return String.format("x = %g", new Object[]{Double.valueOf(this.f366x)});
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0d, this.f366x, this);
        }
    }

    private static final class NaNLinearTransformation extends LinearTransformation {
        static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        public boolean isVertical() {
            return false;
        }

        public boolean isHorizontal() {
            return false;
        }

        public double slope() {
            return Double.NaN;
        }

        public double transform(double x) {
            return Double.NaN;
        }

        public LinearTransformation inverse() {
            return this;
        }

        public String toString() {
            return "NaN";
        }
    }
}
