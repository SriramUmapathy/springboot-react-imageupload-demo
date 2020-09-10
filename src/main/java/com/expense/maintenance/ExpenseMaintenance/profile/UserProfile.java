package com.expense.maintenance.ExpenseMaintenance.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserProfile {

    private UUID id;

    private String userName;

    private String userProfileImageLink;

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userProfileImageLink);
    }
}
