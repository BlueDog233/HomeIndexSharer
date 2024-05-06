package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextProcessor {

    // split函数：提取指定的两对分隔符之间的文本
    public static List<String> split(String str) {
        String[] parts = str.split("~!@#");
        List<String> results = new ArrayList<>();
        // 检查是否有足够的部分来提取文本

        int partslength=parts.length;
        while(partslength>=2){
            results.add(parts[partslength-2]);  // 第一对分隔符之间的文本
            partslength-=2;
        }
        Collections.reverse(results);
        return results;
    }

    // replace函数：替换指定对分隔符之间的文本
    public static String replace(String str, String str2, int pairIndex) {
        String[] parts = str.split("~!@#");
        StringBuilder sb = new StringBuilder();
        if (parts.length >= pairIndex * 2 + 1) {  // 检查是否有足够的部分来进行替换
            for (int i = 0; i < parts.length; i++) {
                if (i == pairIndex * 2 -1) {  // 是需要替换的部分
                    sb.append(str2);
                } else {  // 第一部分，不需要加分隔符
                    sb.append(parts[i]);
                }
            }
        } else {
            return str;  // 如果分隔符对不足，返回原始字符串
        }
        return sb.toString();
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        String input = "你好我是带切割文本\n你好啊你好啊\n1\n2\n3\n~!@#$%\n4\nasasa\n3545\n~!@#$%\nabc\n~!@#$%\ndef\n~!@#$%\nghi";
        String replacement = "ILOVEYOU";

        System.out.println("split函数输出:");
        List<String> splits = split(input);
        for (String split : splits) {
            System.out.println(split);
        }

        System.out.println("replace函数输出 (第一对分隔符):");
        System.out.println(replace(input, replacement, 0));
        System.out.println("replace函数输出 (第二对分隔符):");
        System.out.println(replace(input, replacement, 1));
    }
}
