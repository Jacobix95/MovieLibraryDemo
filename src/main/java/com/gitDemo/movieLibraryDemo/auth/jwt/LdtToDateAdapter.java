package com.gitDemo.movieLibraryDemo.auth.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LdtToDateAdapter extends Date {
    public LdtToDateAdapter(LocalDateTime localDateTime) {
        super(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
