package com.mercury.algorithm.ten._04_kmp;

/**
 * KMP算法：实现字符串匹配问题
 */
public class MatchString {

    public static void main(String[] args) {
        String pattern = "GTGTGCF";
        String str = "ATGTGAGCTGGTGTGTGCFAA";
        System.out.println(kmp(str, pattern));
    }


    /**
     * i不移动，只有j在移动==》 后缀没匹配到，就去前缀匹配
     * @param str
     * @param pattern
     * @return
     */
    public static int kmp(String str, String pattern) {
        int[] next = getNext(pattern);

        // 指向pattern串
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            // 如果模式串j位置和原串i位置不相同，那么移动模式串j指针
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                // 从模式串前缀处重新开始比较
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            // 找到了，返回str串中的下标
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }


    /**
     * 方法描述：获取next数组 （对模式串进行操作） ==>  next[i] 存的是 [ 0 , i-1] 子串中前缀和后缀最大相同的部分
     *
     * 举例：
     *      1、比如： GTGTG =>子串为GTGT => 前缀为G,GT,GTG  后缀为T,GT,TGT
     *          => 最长前后缀相同部分为GT => 所以next[4] = 2
     *
     * @param pattern
     * @return
     */
    public static int[] getNext(String pattern) {
        int strLength = pattern.length();
        int[] next = new int[strLength];
        // i：数组下标; => 指向当前串,i-1 即为子串
        // j：子串中前后缀最大相同部分的长度 => 指向相同部分的后一位（做判断用）
        int j = 0;
        for (int i = 2; i < strLength; i++) {
            while (j != 0 && pattern.charAt(i - 1) != pattern.charAt(j)) {
                // ABDDAB C ABDDAB  ->此时再加入D
                // => ABDDAB C ABDDAB D  j指向C，i-1指向D， C != D
                // ABDDAB串的前缀为AB  后缀也为AB  ==》 将串再分小点
                // 但此时通过后缀AB C不等于AB D
                // 那我们再去前缀AB D看是否等于该值
                j = next[j];
            }
            // 子串的后缀和前缀相同，j+1
            if (pattern.charAt(i - 1) == pattern.charAt(j)) {
                j++;
            }

            next[i] = j;
        }
        return next;
    }


}
