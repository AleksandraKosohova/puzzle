package puzzle;

public enum UserRole {
    ROLE_ADMIN, ROLE_USER;

    @Override
    public String toString() {
        return name();
    }
}
