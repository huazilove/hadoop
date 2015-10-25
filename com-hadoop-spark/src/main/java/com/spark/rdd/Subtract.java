package com.spark.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 移除aRDD里面与bRDD相同的值，并把aRDD里面移除之后的值，保存在新RDD里面
 */
public class Subtract
{
//    public static void main(String[] args)
//    {
//        init();
//    }

    private static void init()
    {
        SparkConf conf = new SparkConf();
        conf.setAppName("binend flatMap");
        conf.setMaster("spark://binend:7077");

        JavaSparkContext jsc = new JavaSparkContext(conf);
        jsc.addJar("/home/titanic/soft/WorkSpace_intellij/hadoop-spark/com-hadoop-spark/target/com-hadoop-spark-1.0-SNAPSHOT.jar");

        List<String> aList = new ArrayList<String>();
        List<String> bList = new ArrayList<String>();

        for(int i = 0 ; i <= 30; i++)
        {
            aList.add(i+"");
        }


        for(int x = 20 ; x <= 40; x++)
        {
            bList.add(x+"");
        }

        JavaRDD<String> aRDD = jsc.parallelize(aList);
        JavaRDD<String> bRDD = jsc.parallelize(bList);

        JavaRDD<String> cRDD = aRDD.subtract(bRDD);


        int count = (int)cRDD.count();
        List<String> cLit = cRDD.top(count);

        for(String s : cLit)
        {
            System.out.println(s);
        }
    }
}
