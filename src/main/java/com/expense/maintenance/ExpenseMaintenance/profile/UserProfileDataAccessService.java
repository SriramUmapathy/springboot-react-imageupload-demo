package com.expense.maintenance.ExpenseMaintenance.profile;

import com.expense.maintenance.ExpenseMaintenance.datastore.DummyUserProfileDataStrore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserProfileDataAccessService {

    private final DummyUserProfileDataStrore dummyUserProfileDataStrore;

    @Autowired
    public UserProfileDataAccessService(DummyUserProfileDataStrore dummyUserProfileDataStrore) {
        this.dummyUserProfileDataStrore = dummyUserProfileDataStrore;
    }

    List<UserProfile> getUserProfile() {
        return dummyUserProfileDataStrore.getUserProfiles();
    }

}
