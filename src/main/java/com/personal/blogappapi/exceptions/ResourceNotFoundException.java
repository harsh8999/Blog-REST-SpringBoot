package com.personal.blogappapi.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourseName;
    String fieldName;
    long fieldValue;
    /**
     * @param arg0
     * @param resourseName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceNotFoundException(String resourseName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s: %s", resourseName, fieldName, fieldValue));
        this.resourseName = resourseName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
}
