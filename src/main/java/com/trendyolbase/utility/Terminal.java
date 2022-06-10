package com.trendyolbase.utility;

public class Terminal {
    /// Runs the given command in the terminal.
    public static void runCommand(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            Log.fail("Terminal kodu çalışırken hata çıktı.", e);
        }
    }
}
