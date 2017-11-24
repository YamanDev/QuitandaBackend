package br.com.yaman.quitanda.wrapper;

/**
 * 
 * @author marcus.martins
 *
 * @param <T>
 */
public class WrapperJsonObject<T> {
    private T t;

    public WrapperJsonObject() {
    }

    public WrapperJsonObject(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WrapperJsonObject)) return false;

        WrapperJsonObject<?> that = (WrapperJsonObject<?>) o;

        return !(getT() != null ? !getT().equals(that.getT()) : that.getT() != null);

    }

    @Override
    public int hashCode() {
        return getT() != null ? getT().hashCode() : 0;
    }
}
