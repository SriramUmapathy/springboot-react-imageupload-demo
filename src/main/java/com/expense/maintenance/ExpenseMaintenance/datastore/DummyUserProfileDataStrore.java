package com.expense.maintenance.ExpenseMaintenance.datastore;

import com.expense.maintenance.ExpenseMaintenance.profile.UserProfile;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Data
public class DummyUserProfileDataStrore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("ba3c40dc-e753-4253-bfdd-7d090275a3ac"), "User1", "pan.jpeg-52679944-2223-4e65-95e1-7dabd82cb274"));
        USER_PROFILES.add(new UserProfile(UUID.fromString("56f28a24-65f7-44c8-b14f-2fdeb76e775d"), "User2", "officeIdBack.jpeg-ba04741e-fea1-40fc-bcda-28263dbd7c89"));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
