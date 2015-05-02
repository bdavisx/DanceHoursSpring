package com.tartner.dancehours.querymodel.danceuser;

import java.util.List;
import java.util.UUID;

public class DanceUserListTO {
    private UUID userId;
    private String email;
    private List<DanceUserListRoleListTO> roles;

    public DanceUserListTO() {}

    public DanceUserListTO( final UUID userId, final String email,
        final List<DanceUserListRoleListTO> roles ) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }

    public UUID getUserId() { return userId; }
    public void setUserId( final UUID userId ) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail( final String email ) { this.email = email; }
    public List<DanceUserListRoleListTO> getRoles() { return roles; }
    public void setRoles( final List<DanceUserListRoleListTO> roles ) { this.roles = roles; }
}
