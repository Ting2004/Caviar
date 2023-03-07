package Caviar;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class Functions {

    public static int finalPos;

    public static void initialise() throws IOException {
        //setUpCorpus("D:/datasource.txt");
        setUpFromDictionaries();
    }


    //查询所有分词可能
    public static void lookUp(String x, Node currentRoot) {
        int pos = 1;
        while (pos <= x.length()) {
            if (Run.dictionary.containsKey(x.substring(0, pos))) {
                Node n = new Node(x.substring(0, pos));
                currentRoot.getChildren().add(n);
                lookUp(x.substring(pos), n);
            }
            pos++;
        }


    }

    //返回没有标点符号的纯文字
    public static String word(String s) {
        if (s.contains("[")) {
            s = s.substring(1);
        }
        String[] result = s.split("/");
        return result[0];
    }

    //寻找最佳路径 非维特比
    public static void findBest(ArrayList<Stack<String>> allPath) {
        double highestPossibility = 0.0;
        for (int i = 0; i < allPath.size(); i++) {
            Stack<String> path = allPath.get(i);
            int pos = 0;
            double possibility = 1.0;
            while (pos < path.size() - 1) {
                possibility *= (calculatePossibility(path.get(pos), path.get(pos + 1)));
                pos++;
            }
            //System.out.println(possibility);
            if (possibility > highestPossibility) {
                highestPossibility = possibility;
                finalPos = i;
            }
        }
    }

    //计算概率
    public static double calculatePossibility(String s1, String s2) {
        int occur = 0, combine = 0;
        double minimum = 0.05;
        if (Run.dictionary.containsKey(s1) && Run.combinationDictionary.containsKey(s1 + s2)) {
            occur = Run.dictionary.get(s1);
            combine = Run.combinationDictionary.get(s1 + s2);
        } else {
            return minimum;
        }
        return (double) combine / occur;
    }

    public static void setUpCorpus(String sourcePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath)));
        String str = null;

        //选中txt文件
        FileOutputStream out1 = new FileOutputStream(Run.dict);
        OutputStreamWriter wrt1 = new OutputStreamWriter(out1);

        FileOutputStream out2 = new FileOutputStream(Run.combinationDict);
        OutputStreamWriter wrt2 = new OutputStreamWriter(out2);

        //生成词典dict
        while ((str = br.readLine()) != null) {
            String[] l = str.split(" ");
            for (int i = 1; i < l.length; i++) {
                String current = word(l[i]);
                if (Run.dictionary.containsKey(current))
                    Run.dictionary.put(current, Run.dictionary.get(current).intValue() + 1);
                else
                    Run.dictionary.put(current, 1);
            }
            for (int i = 0; i < l.length - 2; i++) {
                String a = l[i];
                String b = l[i + 2];
                String combination = a + b;
                if (a.contains("/w") || b.contains("/w")) {
                } else {
                    if (Run.combinationDictionary.containsKey(combination)) {
                        Run.combinationDictionary.put(combination, Run.combinationDictionary.get(combination).intValue() + 1);
                    } else {
                        Run.combinationDictionary.put(combination, 1);
                    }
                }
            }
        }

        //输出字典txt
        for (Map.Entry<String, Integer> e : Run.dictionary.entrySet()) {
            String text = e.getKey();
            Integer occurrence = e.getValue();
            wrt1.write(text + " " + occurrence + "\r\n");

        }
        for (Map.Entry<String, Integer> e : Run.combinationDictionary.entrySet()) {
            String text = e.getKey();
            Integer occurrence = e.getValue();
            wrt2.write(text + " " + occurrence + "\r\n");
        }
        wrt1.close();
        wrt2.close();

    }


    public static void setUpFromDictionaries() throws IOException {
        String str = null;

        //选中txt文件
        FileInputStream in1 = new FileInputStream(Run.dict);
        InputStreamReader rd1 = new InputStreamReader(in1);
        BufferedReader br1 = new BufferedReader(rd1);

        FileInputStream in2 = new FileInputStream(Run.combinationDict);
        InputStreamReader rd2 = new InputStreamReader(in2);
        BufferedReader br2 = new BufferedReader(rd2);

        //生成词典dict
        while ((str = br1.readLine()) != null) {
            String[] l = str.split(" ");
            Run.dictionary.put(l[0], Integer.parseInt(l[1]));
        }
        while ((str = br2.readLine()) != null) {
            String[] l = str.split(" ");
            Run.combinationDictionary.put(l[0], Integer.parseInt(l[1]));
        }

    }
}
