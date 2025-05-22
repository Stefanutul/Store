package com.example.Store.Exceptions;

public class VideoGameNotFoundException extends RuntimeException {
    public VideoGameNotFoundException(Long id) {
        super("Video game with id: " + id + " not found");
    }
}