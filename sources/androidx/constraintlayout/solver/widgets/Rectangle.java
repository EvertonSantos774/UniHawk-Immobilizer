package androidx.constraintlayout.solver.widgets;

public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f263x;

    /* renamed from: y */
    public int f264y;

    public void setBounds(int x, int y, int width2, int height2) {
        this.f263x = x;
        this.f264y = y;
        this.width = width2;
        this.height = height2;
    }

    /* access modifiers changed from: package-private */
    public void grow(int w, int h) {
        this.f263x -= w;
        this.f264y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    /* access modifiers changed from: package-private */
    public boolean intersects(Rectangle bounds) {
        return this.f263x >= bounds.f263x && this.f263x < bounds.f263x + bounds.width && this.f264y >= bounds.f264y && this.f264y < bounds.f264y + bounds.height;
    }

    public boolean contains(int x, int y) {
        return x >= this.f263x && x < this.f263x + this.width && y >= this.f264y && y < this.f264y + this.height;
    }

    public int getCenterX() {
        return (this.f263x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f264y + this.height) / 2;
    }
}
