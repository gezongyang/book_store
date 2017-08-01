package com.sdonkey.score.util;


/**
 * @author 赵超
 *         处理广告剩余市场的util
 */
public class DurationUtil {

    /**
     * @param total  总时长
     * @param finish 已播放时长
     * @return
     */
    public static String getToPlay_Video_Times(String total, String finish) {
        if (total != null && finish != null && total.trim() != "" && finish.trim() != "") {
            Integer totalTimes = Integer.valueOf(total);
            Integer finishTimes = Integer.valueOf(finish);
            if (totalTimes >= finishTimes) {
                Integer lastTimes = totalTimes - finishTimes;
                return secToTime(lastTimes);
            }
            return "0''";
        }
        return null;
    }

    private static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
//            return "00:00";
            return "0''";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                if (second < 60 && minute == 0) {
                    timeStr = unitFormat(second) + "''";
                } else {
                    timeStr = unitFormat(minute) + "'" + unitFormat(second) + "''";
                }
            } else {
                hour = minute / 60;
//                if (hour > 99)
//                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
//                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
                timeStr = hour + " " + unitFormat(minute) + "'" + unitFormat(second) + "''";
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

}
