package tools;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Pair<E, F> implements Serializable {
    private static final long serialVersionUID = 9179541993413738569L;

    public E left;

    public F right;

    public Pair(E left, F right) {
        this.left = left;
        this.right = right;
    }

    public Pair(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toString() {
        return this.left.toString() + ":" + this.right.toString();
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + ((this.left == null) ? 0 : this.left.hashCode());
        result = 31 * result + ((this.right == null) ? 0 : this.right.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pair other = (Pair) obj;
        if (this.left == null) {
            if (other.left != null) {
                return false;
            }
        } else if (!this.left.equals(other.left)) {
            return false;
        }
        if (this.right == null) {
            return other.right == null;
        } else {
            return this.right.equals(other.right);
        }
    }
}
