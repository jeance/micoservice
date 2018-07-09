package com.te.micoservice.common.utils;

import com.te.micoservice.common.definition.PublicConsts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by hcw07605 on 2017/2/28.
 */
public class ExtendMethod {

    /**
     * 获取异常具体堆栈位置和信�
     *
     * @param ex
     * @return
     */
    public static String getExceptionAllInformation(Exception ex) {
        StringBuffer sb = new StringBuffer();
        sb.append("\terror:").append(ex.toString()).append("\r\n");
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sb.append("\tat ").append(s).append("\r\n");
        }
        return sb.toString();
    }

    /**
     * 获取post数据
     *
     * @param request
     * @return String
     * @author hcw
     */
    public static String getPostString(HttpServletRequest request) {
        String result = "";
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return result;
        }
        byte[] buffer = new byte[contentLength];
        try {
            for (int i = 0; i < contentLength; ) {
                int readlen = request.getInputStream().read(buffer, i, contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            result = e.getMessage();
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 获取post数据(Content-Type:application/x-www-form-urlencoded)
     *
     * @param request  HttpServletRequest
     * @param encoding 编码格式
     * @return String
     * @author hcw
     */
   /* public static String getPostString(HttpServletRequest request, String encoding) throws IOException {
        return IOUtils.toString(request.getInputStream(), encoding);
    }*/

    /**
     * 将“参数名=参数�参数�参数值��map
     *
     * @param requestParam “参数名=参数�参数�参数值�
     * @return Map<String,String>
     * @author hcw
     */
    public static Map<String, String> getStringToMap(String requestParam) {
        Map<String, String> map = new HashMap<String, String>();
        String[] params = requestParam.split("&");

        for (String param : params) {
            String[] value = param.split("=");
            if (value.length != 2) {
                map.put(value[0], "");
            } else {
                map.put(value[0], value[1]);
            }
        }
        return map;
    }

    /**
     * 获取当前系统Date(-62135596800000+0800)格式的时�
     *
     * @return String
     * @author xp
     */
    public static String getJsFormatTime() {
        final long time = System.currentTimeMillis();
        String jsTime = "Date(" + time + "+0800)";
        return jsTime;
    }


    /**
     * Url编码
     *
     * @param content 待编码内�
     * @param en      编码格式
     * @return String
     * @author xp
     */
    public static String urlEncoding(String content, String en) {
        String result = "";
        try {
            result = URLEncoder.encode(content, en);
        } catch (UnsupportedEncodingException e) {
            result = "";
        }
        return result;
    }

    /**
     * Url解码
     *
     * @param content 待解码内�
     * @param en      编码格式
     * @return String
     * @author xp
     */
    public static String urlDencoding(String content, String en) {

        String result = "";
        try {
            result = URLDecoder.decode(content, en);
        } catch (UnsupportedEncodingException e) {
            result = "";
        }
        return result;
    }

    /**
     * 日期转换成字符串
     *
     * @param date   Date类型日期
     * @param format 时间格式（如：yyyy-MM-dd HH:mm:ss.SSS�
     * @return String
     * @author hcw
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 字符串转日期（响应可能为null�
     *
     * @param date   String类型日期
     * @param format 时间格式（如：yyyy-MM-ss HH:mm:ss�
     * @return String
     * @author hcw
     */
    public static Date stringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据时间获取
     *
     * @param date
     * @param name
     * @return
     */
    public static int getDateValue(Date date, String name) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        int result = 0;
        switch (name) {
            case "year":
                result = calendar.get(Calendar.YEAR);
                break;
            case "month":
                result = calendar.get(Calendar.MONTH) + 1;
                break;
            case "day":
                result = calendar.get(Calendar.DAY_OF_MONTH);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 根据索引获取枚举对象(超出索引范围返回Null对象)
     *
     * @param <T>
     * @param clazz
     * @param ordinal
     * @return <T>
     * @author xp
     */
    public static <T extends Enum<T>> T valueOf(Class<T> clazz, int ordinal) {
        T enumT;
        try {
            enumT = (T) clazz.getEnumConstants()[ordinal];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return enumT;
        //PublicEnum.PayStatus t=  ExtendMethod.valueOf(PublicEnum.PayStatus.class,0);
        //System.out.println(t.name());
    }

    /**
     * 根据name获取枚举对象(不存在的枚举则返回Null对象)
     *
     * @param <T>
     * @param enumType
     * @param name
     * @return <T>
     * @author xp
     */
    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {

        T enumT;
        try {
            enumT = (T) Enum.valueOf(enumType, name);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return enumT;
    }

    /**
     * 获取分页总数
     *
     * @param pageSize
     * @param total
     * @return
     */
    public static int getPageCount(int pageSize, long total) {
        int pageCount = 0;
        if (total % pageSize > 0) {
            pageCount = (int) total / pageSize + 1;
        } else {
            pageCount = (int) total / pageSize;
        }
        return pageCount;
    }

    /**
     * 拼接like语句参数
     *
     * @param param
     * @return
     */
    public static String getLikeParam(String param) {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(param).append("%'");
        return sb.toString();
    }

    /**
     * 获取轨迹表Item字段中指定项的值，注意必须�^"分隔项，":"分隔值，如：id:123456^name:XXX^age:20
     *
     * @param trajectoryItem
     * @param key
     * @return
     */
    public static String getTrajectoryItemValueByKey(String trajectoryItem, String key) {
        if (!StringHelper.isNullOrEmpty(trajectoryItem) && !StringHelper.isNullOrEmpty(key)) {
            try {
                String[] aryItems = trajectoryItem.split("\\^");
                for (String item : aryItems) {
                    if (item.contains(":")) {
                        String[] aryKeys = item.split(":");
                        if (aryKeys[0].equals(key)) {
                            return item.substring(key.length() + 1);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return "";
    }

    /**
     * 字符串转成有符号位数
     *
     * @param numbers
     * @param radix
     * @return
     */
    public static String parseSignedInt(String numbers, int radix) {
        String result = "0";
        if (radix == 16) {
            char[] number = numbers.toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append("-");
            int baseNumber = Integer.parseInt("f", 16);
            for (int i = 1; i < number.length - 1; i++) {
                if (i < number.length - 2) {
                    sb.append(Integer.toHexString(Integer.parseInt(String.valueOf(number[i]), 16) - baseNumber));
                } else {
                    sb.append(Integer.toHexString(Integer.parseInt(String.valueOf(number[i]), 16) - baseNumber) + 1);
                }
            }
            if (sb.length() >= 2) {
                result = sb.toString();
            }
        }
        return result;
    }

    /**
     * 日期小时数增�
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addHour(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, num);
        return calendar.getTime();
    }

    /**
     * 日期天数增减
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    /**
     * 日期月份增减
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addMonth(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        return calendar.getTime();
    }

    /**
     * 日期年份增减
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addYear(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, num);
        return calendar.getTime();
    }

    /**
     * String转Int
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static int tryParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * String转Int
     *
     * @param value
     * @return
     */
    public static int tryParseInt(String value) {
        return tryParseInt(value, 0);
    }


    /**
     * 根据cookie名称获取cookie�
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        String value = "";
        for (Cookie cookie : request.getCookies()) {
            if (cookieName.equals(cookie.getName())) {
                try {
                    value = URLDecoder.decode(cookie.getValue(), PublicConsts.CommonCosts.CHARSET_UTF8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
