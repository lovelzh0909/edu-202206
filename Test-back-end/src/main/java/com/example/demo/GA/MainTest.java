//package com.example.demo.GA;
//
//import com.example.demo.GA.Paper;
//import com.example.demo.GA.Population;
//import com.example.demo.GA.Rule;
//
///**
// * 测试类
// *
// * @author lixiaolin
// * @createDate 2016-06-22 20:23
// */
//public class MainTest {
//    public static void main(String[] args) {
//        Paper resultPaper = null;
//        // 迭代计数器
//        int count = 0;
//        int runCount = 100;
//        // 适应度期望值
//        double expand = 0.98;
//        //规则试卷id、考试id、总分、难度、单选难度、分数、填空、分、文本、分、知识点、创造时间
//        Rule rule =new Rule(1,2,20,1,2,5,1,5,1,5,"1","2");
//        // 可自己初始化组卷规则rule
//        if (rule != null) {
//            // 初始化种群
//            Population population = new Population(20, true, rule);
//            System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());
//            while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
//                count++;
//                population = GA.evolvePopulation(population, rule);
//                System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
//            }
//            System.out.println("进化次数： " + count);
//            System.out.println(population.getFitness().getAdaptationDegree());
//            resultPaper = population.getFitness();
//        }
//        System.out.println(resultPaper);
//    }
//}
