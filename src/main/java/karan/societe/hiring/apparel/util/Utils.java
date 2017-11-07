package karan.societe.hiring.apparel.util;

public class Utils {

    public static <T> T coalesce(T o1, T o2) {
        return o1 != null ? o1 : o2;
    }
}
