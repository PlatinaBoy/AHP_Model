package com.man.priest.problem.Utile;

/**
 * ClassName: GetRightData <br/>
 * Description: <br/>
 *
 * @author PlatinaBoy<br />
 */
public class GetRightData {
    /**
     * 去除首尾指定字符
     * @param srcStr 字符串
     * @param splitter 指定字符
     * @return
     */
    public static String trimBothEndsChars(String srcStr, String splitter) {
        String regex = "^" + splitter + "*|" + splitter + "*$";
        return srcStr.replaceAll(regex, "");
    }
}
