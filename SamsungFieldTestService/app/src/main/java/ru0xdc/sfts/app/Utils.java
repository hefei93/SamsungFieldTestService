package ru0xdc.sfts.app;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Utils {
    private static final int CHARS_PER_LINE = 34;
    private static final String TAG = "FieldTestService";

    private  Utils() {
    }

    static List<String> unpackListOfStrings(byte aob[]) {

        if (aob.length == 0) {
            Log.v(TAG, "Length = 0");
            return Collections.emptyList();
        }

        int lines = aob.length / CHARS_PER_LINE;

        String[] display = new String[lines];
        for (int i = 0; i < lines; i++) {
            int offset, byteCount;
            offset = i * CHARS_PER_LINE + 2;
            byteCount = 0;

            if (offset + byteCount >= aob.length) {
                Log.e(TAG, "Unexpected EOF");
                break;
            }

            while (aob[offset + byteCount] != 0 && (byteCount < CHARS_PER_LINE)) {
                byteCount += 1;
                if (offset + byteCount >= aob.length) {
                    Log.e(TAG, "Unexpected EOF");
                    break;
                }
            }
            display[i] = new String(aob, offset, byteCount).trim();
        }
        return Arrays.asList(display);
    }

}
