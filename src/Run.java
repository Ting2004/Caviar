package Caviar;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Run {
    public static Map<String, Integer> dictionary = new HashMap<String, Integer>();
    public static Map<String, Integer> combinationDictionary = new HashMap<String, Integer>();
    public static File dict = new File("D:Caviar//dictionary.txt");
    public static File combinationDict = new File("D:Caviar//combinationDictionary.txt");

    public static void main(String[] args) throws IOException {
        Functions.initialise();
        Scanner input = new Scanner(System.in);
        String val = null;       // 记录输入的字符串
        do{
            System.out.println("【鱼子酱】请输入中文后回车：");
            val = input.next();       // 等待输入值
            System.out.println("您输入的是："+val);
            Node begin = new Node("");
            Functions.lookUp(val, begin);
            begin.traverseFullPath(begin);
            //System.out.println(begin.allPath);
            Functions.findBest(begin.allPath);
            System.out.println(begin.allPath.get(Functions.finalPos));
        }while(!val.equals("end"));   // 如果输入的值不版是#就继续输入
        System.out.println("【鱼子酱】结束分词");
        input.close();

//            System.out.println("【鱼子酱】请反馈分词是否正确（Y/N)并回车:");
//            if (scan().compareTo("Y") == 0) {
//                addToDictionary(Node.allPath.get(Functions.finalPos));
//            }

            //setUpCorpus();

//        if (in == "Y") {
//            addToCorpus(Node.allPath.get(Functions.finalPos));
//            System.out.println("【鱼子酱】已将此次分词结果返回至词典");
//            System.out.println("————————Mission Accomplished————————");
//        } else {
//            System.out.println("————————Mission failed————————");
//    }
//        }

    }


    public static String scan() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
            System.out.println("Input:" + input);
        }
        scanner.close();
        return input;

    }

    public static void addToDictionary(Stack<String> words) {
        for (String w : words) {
            if (dictionary.containsKey(w)) {
                System.out.println(w + " ");
                dictionary.put(w, dictionary.get(w).intValue() + 1);
            } else
                dictionary.put(w, 1);
        }

        for (int i = 0; i < words.size() - 1; i++) {
            String a = words.get(i);
            String b = words.get(i + 1);
            String combination = a + b;
            System.out.println(combination + " ");
            if (combinationDictionary.containsKey(combination)) {
                combinationDictionary.put(combination, combinationDictionary.get(combination).intValue() + 1);
            } else {
                combinationDictionary.put(combination, 1);
            }
        }
    }

    public static void addToCorpus(Stack<String> words) throws IOException {
        //生成reader和writer
        FileOutputStream out1 = new FileOutputStream(dict);
        //FileInputStream in1 = new FileInputStream(dict);
        OutputStreamWriter writer1 = new OutputStreamWriter(out1);
        //InputStreamReader reader1 = new InputStreamReader(in1);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:Caviar/dictionary.txt")));


        FileOutputStream out2 = new FileOutputStream(combinationDict);
        FileInputStream in2 = new FileInputStream(combinationDict);
        OutputStreamWriter writer2 = new OutputStreamWriter(out2);
        InputStreamReader reader2 = new InputStreamReader(in2);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("D:Caviar/combinationDictionary.txt")));


        String str = null;

        for (String word : words) {
            while ((str = br1.readLine()) != null) {
                String content = str.substring(0, str.indexOf(" "));
                if (word.compareTo(content) == 0) {
                    int occ = Integer.valueOf(str.substring(str.indexOf(" ") + 1));

                }

            }
        }


    }


}

