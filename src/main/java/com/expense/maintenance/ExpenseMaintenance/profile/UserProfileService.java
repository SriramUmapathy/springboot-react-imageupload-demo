package com.expense.maintenance.ExpenseMaintenance.profile;

import com.expense.maintenance.ExpenseMaintenance.bucket.BucketName;
import com.expense.maintenance.ExpenseMaintenance.fileStore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfile() {
        return userProfileDataAccessService.getUserProfile();
    }

    public void uploadUserProfileImage(UUID id, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        UserProfile user = getUserProfileOrThrow(id);
        Map<String, String> metaData = extractMetaData(file);
        String path = String.format("%s/%s", BucketName.IMAGE.getBucketName(), user.getId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path, fileName, Optional.of(metaData), file.getInputStream());
        }catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(UUID id) {
        UserProfile user = getUserProfileOrThrow(id);
        String path = String.format("%s/%s", BucketName.IMAGE.getBucketName(),
                user.getId());

        return user.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type",file.getContentType());
        metaData.put("Content-Length",String.valueOf(file.getSize()));
        return metaData;
    }

    private UserProfile getUserProfileOrThrow(UUID id) {
        return userProfileDataAccessService
                .getUserProfile()
                .stream()
                .filter(userProfile -> userProfile.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", id)));
    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType()))
            throw new IllegalStateException("File mush be an image ["+file.getContentType()+"]");
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("Can not upload empty file ["+file.getSize()+"]");
    }

}
