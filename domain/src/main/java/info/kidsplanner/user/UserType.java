package info.kidsplanner.user;

public enum UserType {
    PARENT, CHILD;

    public boolean isParent() {
        return PARENT.equals(this);
    }

    public boolean isChild() {
        return CHILD.equals(this);
    }
}
