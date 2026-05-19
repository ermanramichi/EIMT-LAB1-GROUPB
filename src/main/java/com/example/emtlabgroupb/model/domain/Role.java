package com.example.emtlabgroupb.model.domain;

/**
 * Application roles.
 *
 * The Spring Security convention is "ROLE_*", and that's what is stored in the database
 * and embedded in the JWT. For the UI / API consumers we expose the short names
 * (USER, ADMINISTRATOR) via {@link #displayName()}.
 */
public enum Role {
    ROLE_USER("USER"),
    ROLE_HOST("HOST"),
    ROLE_ADMIN("ADMINISTRATOR");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    /**
     * Resolves a {@link Role} from either its enum constant name ({@code ROLE_ADMIN})
     * or its display name ({@code ADMINISTRATOR}). Case-insensitive.
     */
    public static Role fromAny(String value) {
        if (value == null) return ROLE_USER;
        String v = value.trim().toUpperCase();
        for (Role r : values()) {
            if (r.name().equalsIgnoreCase(v) || r.displayName.equalsIgnoreCase(v)) {
                return r;
            }
        }
        return ROLE_USER;
    }
}
