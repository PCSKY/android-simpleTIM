package com.example.zdm.simpletim.utils;

// pinyin4j需要引入jar包
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: 得到指定汉字的拼音
 */
public class PinYinUtils {
    // 获取汉字的拼音
    public static String getPinyin(String hanzi) {
        StringBuffer sb = new StringBuffer();
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);    // 转换成大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 无声调表示
        // 对单个汉字进行转换
        char[] arr = hanzi.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(Character.isWhitespace(arr[i])) {
                continue;   // 如果是空格
            }
            try {
                String pinyinArr[] = PinyinHelper.toHanyuPinyinStringArray(arr[i], format);
                if(pinyinArr != null) sb.append(pinyinArr[0]);   // 提取首字母
                else sb.append(arr[i]);
            } catch ( BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination ) {
                // 不是正确的汉字
                badHanyuPinyinOutputFormatCombination.printStackTrace();
                sb.append(arr[i]);
            }
        }
        return sb.toString().toUpperCase();
    }
}
