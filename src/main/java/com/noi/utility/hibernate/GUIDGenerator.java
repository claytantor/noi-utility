package com.noi.utility.hibernate;

import java.util.UUID;

public class GUIDGenerator {
	public static String createId() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }

}
