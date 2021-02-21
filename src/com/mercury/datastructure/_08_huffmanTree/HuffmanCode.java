package com.mercury.datastructure._08_huffmanTree;


import java.util.*;

/**
 *
 */
public class HuffmanCode {

    public static void main(String[] args) {
        //
        String content = "i like like like java do you like a java";
        //1、赫夫曼压缩
        byte[] contentBytes = content.getBytes();
        System.out.println("【压缩】 原字节数组为：" + Arrays.toString(contentBytes));
        Byte[] huffmanBytes = huffmanZip(contentBytes);
        System.out.println("【压缩】 新字节数组为：" + Arrays.toString(huffmanBytes));

        System.out.println();
        byte[] oldBytes = huffmanUnZip(huffmanBytes, huffmanCodes);
        System.out.println("【解压】 原字节数组为：" + Arrays.toString(oldBytes));
        System.out.println("【解压】 原字符串为：" + new String(oldBytes));


    }

    /**
     * 二、赫夫曼解压
     *
     * @param huffmanBytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] huffmanUnZip(Byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        StringBuffer newBuffer = new StringBuffer(); // 保存二进制
        // 1、将新数组转换成二进制
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = i != huffmanBytes.length - 1;
            newBuffer.append(bit2Byte(flag, huffmanBytes[i]));
        }

        System.out.println("【解压】二进制为：" + newBuffer.toString());
        // 2、查找匹配赫夫曼编码(反转)，将二进制转换成原数组
        Map<String, Byte> reverseHuffmanCodes = reverseHuffmanCodes(huffmanCodes);
        StringBuffer sb = new StringBuffer(); // 暂存 二进制
        List<Byte> list = new ArrayList<>();

        char[] chars = newBuffer.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) { // 遍历二进制
            sb.append(chars[i]);
            // 查找赫夫曼编码(反转) ==> 根据value（二进制）去查key（字母）
            if (reverseHuffmanCodes.containsKey(sb.toString())) {
                list.add(reverseHuffmanCodes.get(sb.toString()));
                sb = new StringBuffer();
            }
        }


        byte[] oldBytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            oldBytes[i] = list.get(i);
        }
        return oldBytes;
    }

    /**
     * 反转赫夫曼编码表
     *
     * @param huffmanCodes <String：二进制, Byte：字母>
     * @return
     */
    private static Map<String, Byte> reverseHuffmanCodes(Map<Byte, String> huffmanCodes) {
        Map<String, Byte> reverseHuffmanCodes = new HashMap<>();
        for (Byte key : huffmanCodes.keySet()) {
            reverseHuffmanCodes.put(huffmanCodes.get(key), key);
        }
        return reverseHuffmanCodes;
    }

    /**
     * byte转成二进制
     *
     * @param flag         true:不是最后一个字符   false：是最后一个字符 ==》直接全部打印出来
     * @param originalByte 需要处理的数组
     * @return
     */
    private static String bit2Byte(boolean flag, byte originalByte) {
        int temp = originalByte;
        if (flag) {
            temp |= 256;  // 如果temp为1 ==》  0000 0001 | 1 0000 0000  ==》0000 0001
        }
        String str = Integer.toBinaryString(temp);//转换为2进制字符串

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }


    }

    /**
     * 一、赫夫曼压缩
     *
     * @param contentBytes
     * @return
     */
    private static Byte[] huffmanZip(byte[] contentBytes) {
        // 1、将原字节数组转换成赫夫曼节点
        List<HuffmanNode> nodes = getNodes(contentBytes);
        // 2、 创建赫夫曼树
        HuffmanNode root = createHuffmanTree(nodes);
        // 3、获取赫夫曼编码  ==》从root开始遍历获取赫夫曼树的赫夫曼编码
        Map<Byte, String> codes = getCodes(root, "", new StringBuffer());
        // 4、根据赫夫曼编码将原字符串压缩,并返回新数组
        return zip(contentBytes, codes);
    }

    /**
     * 根据赫夫曼编码，将原字节数组压缩，并根据赫夫曼二进制返回新字节数组
     *
     * @param contentBytes 原字节数组
     * @param codes        赫夫曼编码
     * @return
     */
    private static Byte[] zip(byte[] contentBytes, Map<Byte, String> codes) {
        // 1、根据赫夫曼编码和原字节数组，得到编码后的二进制
        StringBuffer binaryBuffer = new StringBuffer();
        for (byte data : contentBytes) {
            binaryBuffer.append(codes.get(data));
        }

        System.out.println("【压缩】二进制为：" + binaryBuffer.toString());

        // 2、编码后的二进制==》新字节数组
        int len = (binaryBuffer.length() + 7) / 8; // 得到新字节数组的长度
        Byte[] huffmanBytes = new Byte[len];
        int index = 0;
        for (int i = 0; i < binaryBuffer.length(); i += 8) { // 8位为1字节
            String strByte;
            // 如果不足8位,那么取全部的
            if (i + 8 > binaryBuffer.length()) {
                strByte = binaryBuffer.substring(i);
            } else {
                strByte = binaryBuffer.substring(i, i + 8);
            }
            // 将二进制转换成byte
            huffmanBytes[index++] = (byte) Integer.parseInt(strByte, 2);

        }

        return huffmanBytes;
    }

    /**
     * 保存每个字母的赫夫曼编码
     * Byte:  字母
     * String: 字母对应的赫夫曼编码 （二进制）
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    /**
     * 获取赫夫曼树对应的赫夫曼编码
     *
     * @param node
     * @param code
     * @param stringBuffer
     * @return
     */
    private static Map<Byte, String> getCodes(HuffmanNode node, String code, StringBuffer stringBuffer) {
        StringBuffer buffer = new StringBuffer(stringBuffer); // 保存直到当前节点的编码
        buffer.append(code);

        if (node != null) {
            // 非叶子节点，继续递归
            if (node.data == null) {
                getCodes(node.left, "0", buffer); // 左递归
                getCodes(node.right, "1", buffer); // 右递归

            } else { //叶子节点，保存到 huffmanCodes 中
                huffmanCodes.put(node.data, buffer.toString());

            }
        }
        return huffmanCodes;
    }


    /**
     * 创建赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static HuffmanNode createHuffmanTree(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            // 取前两个节点，组成新节点
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parentNode = new HuffmanNode(null, leftNode.value + rightNode.value); // 注意：父节点没有字母
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * 将byte数组转换成node集合
     *
     * @param contentBytes
     * @return
     */
    private static List<HuffmanNode> getNodes(byte[] contentBytes) {
        List<HuffmanNode> nodes = new ArrayList<>();
        Map<Byte, Integer> byteMap = new HashMap<>();
        // 遍历byte数组 得到每个字符出现的次数
        for (Byte data : contentBytes) {
            Integer count = byteMap.get(data);
            if (count != null) {
                byteMap.put(data, ++count);
            } else {
                byteMap.put(data, 1);
            }

        }

        for (Byte data : byteMap.keySet()) {
            nodes.add(new HuffmanNode(data, byteMap.get(data)));
        }

        return nodes;

    }

}


class HuffmanNode implements Comparable<HuffmanNode> {
    Byte data; // 字母的assic码
    Integer value; // 权值：字母出现次数

    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, Integer value) {
        this.data = data;
        this.value = value;
    }

    /**
     * 前序遍历二叉树
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }

    /**
     * 按从小到大排列
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}