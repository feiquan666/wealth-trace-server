package com.feiquan.tools.util;

public class WtStringUtil {

    public static String formatWithBraces(String template, Object... args) {
        if (template == null || template.isEmpty()) {
            return template;
        }

        StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        int cursor = 0;

        while (cursor < template.length()) {
            int brace = template.indexOf("{}", cursor);
            if (brace == -1) {
                // no more placeholders
                sb.append(template.substring(cursor));
                break;
            }

            // append text before placeholder
            sb.append(template, cursor, brace);

            // append arg if exists
            if (argIndex < args.length) {
                sb.append(args[argIndex++]);
            } else {
                sb.append("{}"); // no arg left
            }

            cursor = brace + 2; // skip {}
        }

        return sb.toString();
    }
}
