package com.expense.maintenance.ExpenseMaintenance.bucket;

public enum BucketName {

    IMAGE("springboot-image-upload-demo");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
