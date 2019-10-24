package com.man.priest.problem;


import com.man.priest.core.*;
import com.man.priest.method.EigenvectorMethod;
import com.man.priest.problem.Utile.GetDataByURL;
import com.man.priest.problem.Utile.GetRightData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ClassName: ResultItem <br/>
 * Description: ONLINE<br/>
 *
 * @author PlatinaBoy<br />
 */
public class ProcessMain {
    public static void main(String[] args) {
        ProcessMain.getScore("SHET201808286;;;a;;b;1;10000;3000-5000;0;0;0;;;;0;0;0;1;400000.00;1;c;1;12;700.00;1;d;cx0101");

    }
    public static String getScore(String args) {
////        String childid = args[0];
////        String dataByUrl = GetDataByURL.getDataByUrl(childid);
//        String dataByUrl = GetDataByURL.getDataByUrl("SHET201810309");
//        String[] split = dataByUrl.split(",");
//        String firstStr = GetRightData.trimBothEndsChars(split[1].substring(8), "\"");
//        String[] str = firstStr.split(";");
//		命令行单个被评估对象信息输入
        String chileInfo = args;
        String[] str = chileInfo.split(";", 28);
        DataRecord dataRecord = new DataRecord(str);
        DecisionProblem problem;
        double result;
        List<Map<String, Object>> maps = changeDital(dataRecord);
        Object dproblem = maps.get(0).get("Dproblem");
        problem = (DecisionProblem) dproblem;
        result = getSolverScore(problem);
//        System.out.println(result);
        //四舍五入保留两位小数
        Object everyScore = maps.get(1).get("everyScore");
        String allScore = (String)everyScore;
        String consequence = allScore +"\n"+ result;
        return consequence;

    }




    private static Double getSolverScore(DecisionProblem problem) {
        Solver solver = new Solver();
        solver.addMethod(new EigenvectorMethod());
        List<ProblemSolution> solutions = solver.solve(problem);
        return solutions.get(0).getWeights()[0];
    }

    private static List<Map<String, Object>> changeDital(DataRecord ss) {
        DecisionProblem problem = new DecisionProblem("评估");
        //评估
        problem.addAlternative("评估对象1");
        //基本信息5*5行列式
        Criterion goal0 = problem.addCriteria("a");
        Criterion goal1 = problem.addCriteria("b");
        Criterion goal2 = problem.addCriteria("c");
        Criterion goal3 = problem.addCriteria("d");
        Criterion goal4 = problem.addCriteria("e");
        //家庭情况3*3行列式
        Criterion goal00 = goal0.addCriterion("f");
        Criterion goal01 = goal0.addCriterion("g");
        Criterion goal02 = goal0.addCriterion("h");
        //医保2*2行列式
        Criterion goal10 = goal1.addCriterion("i");
        Criterion goal11 = goal1.addCriterion("g");
        //收支3*3行列式
        Criterion goal20 = goal2.addCriterion("k");
        Criterion goal21 = goal2.addCriterion("l");
        Criterion goal22 = goal2.addCriterion("m");
        Criterion goal23 = goal2.addCriterion("n");
        Criterion goal24 = goal2.addCriterion("o");
        Criterion goal25 = goal2.addCriterion("p");
        Criterion goal26 = goal2.addCriterion("q");
        Criterion goal27 = goal2.addCriterion("r");
        Criterion goal28 = goal2.addCriterion("s");
        Criterion goal29 = goal2.addCriterion("t");
        Criterion goal210 = goal2.addCriterion("u");
        Criterion goal211 = goal2.addCriterion("v");


        //借贷款行列式2*2
        Criterion goal30 = goal3.addCriterion("w");
        Criterion goal31 = goal3.addCriterion("x");
        //资产行列式4*4
        Criterion goal40 = goal4.addCriterion("y");
        Criterion goal41 = goal4.addCriterion("z");
        Criterion goal42 = goal4.addCriterion("z1");
        Criterion goal43 = goal4.addCriterion("z2");
        /**
         * 目标成判断矩阵，矩阵对角线为自己与自己的比较，其他值为与其他层的比较权重值
         * 不把所有因素放在一起比较，而是两两相互比较；
         * 对此时采用相对尺度，以尽可能减少性质不同因素相互比较的困难，以提高准确度。
         * */

        //一致阵
        problem.setPCMatrix(new PCMatrix(new double[][]{
                {1.0D, 0.5D, 0.25D, 0.5D, 0.5D},
                {2.0D, 1.0D, 0.5D, 1.0D, 1.0D},
                {4.0D, 2.0D, 1.0D, 2.0D, 2.0D},
                {2.0D, 1.0D, 0.5D, 1.0D, 1.0D},
                {2.0D, 1.0D, 0.5D, 1.0D, 1.0D}}));
        //以下为因素层判断矩阵
        //家庭情况准则权重
        goal0.setPCMatrix(new PCMatrix(new double[][]{
                {1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D}}));
        //医保准则权重
        goal1.setPCMatrix(new PCMatrix(new double[][]{
                {1.0D, 0.5D},
                {2.0D, 1.0D}}));
        //收支情况准则权重值(收入花费平台筹款)
        goal2.setPCMatrix(new PCMatrix(new double[][]{
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1 / 3D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D},
                {3.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D}}));
        //借贷情况准则权重值
        goal3.setPCMatrix(new PCMatrix(new double[][]{
                {1.0D, 1.0D},
                {1.0D, 1.0D}}));
        //资产情况准则权重值
        goal4.setPCMatrix(new PCMatrix(new double[][]{
                {1.0D, 1.0D, 0.2D, 0.2D},
                {1.0D, 1.0D, 0.2D, 0.2D},
                {5.0D, 5.0D, 1.0D, 1.0D},
                {5.0D, 5.0D, 1.0D, 1.0D}}));

        //用户参数
        double[] family = getScoreByFamily(ss);
        double[] disease = getScoreByDisease(ss);
        double[] inOutCome = getScoreByInOutCome(ss);
        double[] loan = getScoreByLoan(ss);
        double[] asset = getScoreByAsset(ss);
        //家庭情况评分
        goal00.setPCMatrix(new PCMatrix(new double[][]{{family[0]}}));
        goal01.setPCMatrix(new PCMatrix(new double[][]{{family[1]}}));
        goal02.setPCMatrix(new PCMatrix(new double[][]{{family[2]}}));


        goal10.setPCMatrix(new PCMatrix(new double[][]{{disease[0]}}));
        goal11.setPCMatrix(new PCMatrix(new double[][]{{disease[1]}}));

        goal20.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[0]}}));
        goal21.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[1]}}));
        goal22.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[2]}}));
        goal23.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[3]}}));
        goal24.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[4]}}));
        goal25.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[5]}}));
        goal26.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[6]}}));
        goal27.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[7]}}));
        goal28.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[8]}}));
        goal29.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[9]}}));
        goal210.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[10]}}));
        goal211.setPCMatrix(new PCMatrix(new double[][]{{inOutCome[11]}}));


        goal30.setPCMatrix(new PCMatrix(new double[][]{{loan[0]}}));
        goal31.setPCMatrix(new PCMatrix(new double[][]{{loan[1]}}));

        goal40.setPCMatrix(new PCMatrix(new double[][]{{asset[0]}}));
        goal41.setPCMatrix(new PCMatrix(new double[][]{{asset[1]}}));
        goal42.setPCMatrix(new PCMatrix(new double[][]{{asset[2]}}));
        goal43.setPCMatrix(new PCMatrix(new double[][]{{asset[3]}}));

        Double famliyScore = (family[0] + family[1] + family[2]) / family.length;
        Double diseaseSCore = (disease[0] + disease[1]) / disease.length;
        Double inOutComeScore = (inOutCome[0] + inOutCome[1] + inOutCome[2]
                + inOutCome[3] + inOutCome[4] + inOutCome[5] + inOutCome[6]
                + inOutCome[7] + inOutCome[8] + inOutCome[9] + inOutCome[10] + inOutCome[11]) / inOutCome.length;
        Double loanScore = (loan[0] + loan[1]) / loan.length;
        Double assetSorce = (asset[0] + asset[1] + asset[2] + asset[3]) / asset.length;
        String result1 = String.format(String.format("%.2f", famliyScore));
        String result2 = String.format(String.format("%.2f", diseaseSCore));
        String result3 = String.format(String.format("%.2f", inOutComeScore));
        String result4 = String.format(String.format("%.2f", loanScore));
        String result5 = String.format(String.format("%.2f", assetSorce));
        String everyScore = "家庭评分：" + result1 + "疾病程度" + result2 + "收支情况" + result3 + "借贷情况" + result4 + "资产情况" + result5;
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Object dproblem = stringObjectHashMap.put("Dproblem", problem);
        Object allScore = stringObjectHashMap.put("everyScore", everyScore);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add((Map<String, Object>) dproblem);
        list.add((Map<String, Object>) allScore);

        return list;
    }


    //根据输入数据，进行评分，评分细则
    //家庭评分细则
    private static double[] getScoreByFamily(DataRecord ss) {
        double[] res = {0.0D, 0.0D, 0.0D};
        //户籍类型评分细则
        switch (ss.getRegister()) {
            case 2:
                res[0] = 0.0D
                break;
            case 3:
                res[0] = 0.0D;
                break;
            case 1:
                res[0] =0.0D;
                break;
            case 4:
                res[0] = 0.0D;
                break;
            default:
                res[0] = 0.0D;
        }
        //家庭成员评分细则
        String relative = ss.getRelative() == null ? "" : ss.getRelative();
        if ((relative.contains("父亲")) || (relative.contains("母亲"))) {
            res[1] = 0.0D;
        } else if ((relative.contains("爷爷")) || (relative.contains("奶奶")) ||
                (relative.contains("外公")) || (relative.contains("外婆"))) {
            res[1] = 0.0D;
        } else if (relative.contains("姐妹")) {
            res[1] = 0.0D;
        } else if (relative.contains("非直系")) {
            res[1] = 0.0D;
        } else {
            res[1] = 0.0D;
        }
        //家庭成员学历评分细则
        String eduication = ss.getEducation() == null ? "" : ss.getEducation();
        if (eduication.contains("博士")) {
            res[2] = 0.0D;
        } else if (eduication.contains("硕士")) {
            res[2] = 0.0D;
        } else if (eduication.contains("本科")) {
            res[2] = 0.0D;
        } else if (eduication.contains("专科")) {
            res[2] = 0.0D;
        } else if (eduication.contains("高中及以下")) {
            res[2] = 0.0D;
        } else {
            res[2] = 0.0D;
        }
        return res;
    }

    //疾病医保评分细则
    private static double[] getScoreByDisease(DataRecord ss) {
        //医保评分细则
        double[] res = {0.0D, 0.0D};
        String medicare = ss.getMedicare() == null ? "" : ss.getMedicare();
        if (medicare.contains("城镇医保")) {
            res[0] = 0.0D;
        } else if (medicare.contains("新农合")) {
            res[0] = 0.0D;
        } else if (medicare.contains("无")) {
            res[0] = 0.0D;
        } else {
            res[0] = 0.0D;
        }
        if (ss.getHasComInsurance() == null) {
            res[1] = 10.0D;
        } else if (ss.getHasComInsurance()) {
            res[1] = 4.0D;
        } else {
            res[1] = 2.0D;
        }
        return res;
    }

    //收入评分细则
    private static double[] getScoreByInOutCome(DataRecord ss) {
        double[] res = {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D};
        //收入评分细则
        //积蓄
        if (ss.getYearlyIncome() == null) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 100000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 90000 && ss.getYearlyIncome() <= 100000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 80000 && ss.getYearlyIncome() <= 90000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 70000 && ss.getYearlyIncome() <= 80000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 60000 && ss.getYearlyIncome() <= 70000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 50000 && ss.getYearlyIncome() <= 60000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 40000 && ss.getYearlyIncome() <= 50000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 30000 && ss.getYearlyIncome() <= 40000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 20000 && ss.getYearlyIncome() <= 30000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 10000 && ss.getYearlyIncome() <= 20000) {
            res[0] = 0.0D;
        } else if (ss.getYearlyIncome() > 0 && ss.getYearlyIncome() <= 10000) {
            res[0] = 0.0D;
        } else {
            res[0] = 0.0D;
        }
        //固定工作-年收入
        String fixedYearIncome = ss.getFixedYearIncome() == null ? "" : ss.getFixedYearIncome();
        if (ss.getFixedYearIncome() == null) {
            res[1] = 0.0D;
        } else if (fixedYearIncome.contains("40000以上")) {
            res[1] = 0.0D;
        } else if (fixedYearIncome.contains("20001-40000")) {
            res[1] = 0.0D;
        } else if (fixedYearIncome.contains("10001-20000")) {
            res[1] = 0.0D;
        } else if (fixedYearIncome.contains("5001-10000")) {
            res[1] = 0.0D;
        } else if (fixedYearIncome.contains("5000以下")) {
            res[1] =0.0D;
        } else {
            res[1] = 0.0D;
        }
        //打零工-年收入 字段无值无字典表*******
        String dayJobYearIncome = ss.getDayJobYearIncome() == null ? "" : ss.getDayJobYearIncome();
        if (ss.getDayJobYearIncome() == null) {
            res[2] = 0.0D;
        } else if (dayJobYearIncome.contains("20000以上")) {
            res[2] = 0.0D;
        } else if (dayJobYearIncome.contains("10001-20000")) {
            res[2] = 0.0D;
        } else if (dayJobYearIncome.contains("5001-10000")) {
            res[2] = 0.0D;
        } else if (dayJobYearIncome.contains("2501-5000")) {
            res[2] = 0.0D;
        } else if (dayJobYearIncome.contains("2500以下")) {
            res[2] = 0.0D;
        } else {
            res[2] = 0.0D;
        }
        //种地收入
        String farmIncome = ss.getFarmIncome() == null ? "" : ss.getFarmIncome();
        if (ss.getFarmIncome() == null) {
            res[3] = 0.0D;
        } else if (farmIncome.contains("8万以上")) {
            res[3] = 0.0D;
        } else if (farmIncome.contains("5-8万")) {
            res[3] = 0.0D;
        } else if (farmIncome.contains("3-5万")) {
            res[3] = 0.0D;
        } else if (farmIncome.contains("1-3万")) {
            res[3] = 0.0D;
        } else if (farmIncome.contains("1万以下")) {
            res[3] = 0.0D;
        } else {
            res[3] = 0.0D;
        }
        // 养殖收入
        String cultureIncome = ss.getCultureIncome() == null ? "" : ss.getCultureIncome();
        if (ss.getCultureIncome() == null) {
            res[4] = 0.0D;
        } else if (cultureIncome.contains("8万以上")) {
            res[4] = 0.0D;
        } else if (cultureIncome.contains("5-8万")) {
            res[4] =0.0D;
        } else if (cultureIncome.contains("3-5万")) {
            res[4] =0.0D;
        } else if (cultureIncome.contains("1-3万")) {
            res[4] =0.0D;
        } else if (cultureIncome.contains("1万以下")) {
            res[4] = 0.0D;
        } else {
            res[4] = 0.0D;
        }
        //其他收入-退休金
        String otherIncomeWithPension = ss.getOtherIncomeWithPension() == null ? "" : ss.getOtherIncomeWithPension();
        if (ss.getOtherIncomeWithPension() == null) {
            res[5] = 0.0D;
        } else if (otherIncomeWithPension.contains("1000-2000")) {
            res[5] = 0.0D;
        } else if (otherIncomeWithPension.contains("500-1000")) {
            res[5] = 0.0D;
        } else if (otherIncomeWithPension.contains("0-500")) {
            res[5] = 0.0D;
        } else if (otherIncomeWithPension.contains("无")) {
            res[5] = 0.0D;
        } else {
            res[5] = 0.0D;
        }
        //其他收入-低保金
        String otherIncomeWithLowGold = ss.getOtherIncomeWithLowGold() == null ? "" : ss.getOtherIncomeWithLowGold();
        if (ss.getOtherIncomeWithLowGold() == null) {
            res[6] = 0.0D;
        } else if (otherIncomeWithLowGold.contains("1000-2000")) {
            res[6] = 0.0D;
        } else if (otherIncomeWithLowGold.contains("600-1000")) {
            res[6] = 0.0D;
        } else if (otherIncomeWithLowGold.contains("0-600")) {
            res[6] = 0.0D;
        } else if (otherIncomeWithLowGold.contains("无")) {
            res[6] = 0.0;
        } else {
            res[6] = 0.0D;
        }
        //其他收入-租金
        String otherIncomeWithRental = ss.getOtherIncomeWithRental() == null ? "" : ss.getOtherIncomeWithRental();
        if (ss.getOtherIncomeWithRental() == null) {
            res[7] = 0.0D;
        } else if (otherIncomeWithRental.contains("1000-2000")) {
            res[7] = 0.0D;
        } else if (otherIncomeWithRental.contains("700-1000")) {
            res[7] = 0.0D;
        } else if (otherIncomeWithRental.contains("0-700")) {
            res[7] = 0.0D;
        } else if (otherIncomeWithRental.contains("无")) {
            res[7] = 0.0D;
        } else {
            res[7] = 0.0D;
        }
        if (ss.getRenting() == null) {
            res[8] = 0.0D;

        } else if (ss.getRenting()) {
            res[8] = 0.0D;
            if (ss.getTenancyPeriod() > 0 && ss.getTenancyPeriod() <= 3) {
                res[9] = 0.0D;
            } else if (ss.getTenancyPeriod() > 3 && ss.getTenancyPeriod() <= 6) {
                res[9] = 0.0D;
            } else if (ss.getTenancyPeriod() > 6 && ss.getTenancyPeriod() <= 9) {
                res[9] = 0.0D;
            } else {
                res[9] = 0.0D;
            }
            if (ss.getUseCostPerMonth() > 0 && ss.getUseCostPerMonth() <= 800) {
                res[10] = 0.0D;
            } else if (ss.getUseCostPerMonth() > 800 && ss.getUseCostPerMonth() <= 1000) {
                res[10] = 0.0D;
            } else if (ss.getUseCostPerMonth() > 1000 && ss.getUseCostPerMonth() <= 1500) {
                res[10] = 0.0D;
            } else if (ss.getUseCostPerMonth() > 1500 && ss.getUseCostPerMonth() <= 2000) {
                res[10] = 0.0D;
            } else if (ss.getUseCostPerMonth() > 2000 && ss.getUseCostPerMonth() <= 2500) {
                res[10] = 0.0D;
            } else if (ss.getUseCostPerMonth() > 2500 && ss.getUseCostPerMonth() <= 3000) {
                res[10] = 0.0D;
            } else if (ss.getUseCostPerMonth() > 3000 && ss.getUseCostPerMonth() <= 2500) {
                res[10] = 0.0D;
            } else {
                res[8] = 0.0D;
                res[9] = 0.0D;
                res[10] = 0.0D;
            }
        }

        //疑似贷款
        switch (ss.getDonation()) {
            case 0:
                res[11] = 0.0D;
                break;
            case 1:
                res[11] = 0.0D;
                break;
            case 2:
                res[11] = 0.0D;
                break;
            case 3:
                res[11] = 0.0D;
                break;
            case 4:
                res[11] = 0.0D;
                break;
            default:
                res[11] = 0.0D;
        }


        return res;
    }

    //借贷款评分细则
    private static double[] getScoreByLoan(DataRecord ss) {
        double[] res = {0.0D, 0.0D};
        if (ss.getHasLoan() == null) {
            res[0] = 0.0D;
        } else if (ss.getHasLoan()) {
            res[0] = 0.0D;
        } else {
            res[0] = 0.0D;
        }
        if (ss.getLoan() == null) {
            res[1] = 0.0D;
        } else if (ss.getLoan() > 0 && ss.getLoan() < 30000.0D) {
            res[1] = 0.0D;
        } else if (ss.getLoan() > 30000.0D && ss.getLoan() < 50000.0D) {
            res[1] = 0.0D;
        } else if (ss.getLoan() > 50000.0D && ss.getLoan() < 70000.0D) {
            res[1] = 0.0D;
        } else if (ss.getLoan() > 70000.0D && ss.getLoan() < 90000.0D) {
            res[1] =0.0D;
        } else if (ss.getLoan() > 90000.0D && ss.getLoan() < 150000.0D) {
            res[1] = 0.0D;
        } else if (ss.getLoan() > 150000.0D && ss.getLoan() < 200000.0D) {
            res[1] = 0.0D;
        } else if (ss.getLoan() > 200000.0D && ss.getLoan() < 300000.0D) {
            res[1] = 0.0D;
        } else {
            res[1] = 0.0D;
        }
        return res;
    }

    //固定资产评分细则
    private static double[] getScoreByAsset(DataRecord ss) {
        double[] res = {0.0D, 0.0D, 0.0D, 0.0D};
        if (ss.isHasHouse() == null) {
            res[0] = 0.0D;
        } else if (ss.isHasHouse()) {
            res[0] = 0.0D;
        } else {
            res[0] = 0.0D;
        }
        String houseType = ss.getHouseType() == null ? "" : ss.getHouseType();
        if (ss.getHouseType() == null) {
            res[1] = 0.0D;
        } else if (houseType.contains("商品房")) {
            res[1] = 0.0D;
        } else if (houseType.contains("回迁房")) {
            res[1] = 0.0D;
        } else if (houseType.contains("父母住房")) {
            res[1] = 0.0D;
        } else if (houseType.contains("砖瓦房")) {
            res[1] = 0.0D;
        } else if (houseType.contains("土坯房")) {
            res[1] = 0.0D;
        } else if (houseType.contains("其他")) {
            res[1] = 0.0D;
        } else {
            res[1] = 0.0D;
        }


        if (ss.isHasCar() == null) {
            res[2] = 0.0D;
        } else if (ss.isHasCar()) {
            res[2] = 0.0D;
            if (ss.getCarType() != "") {
                res[3] = 0.0D;
            } else
                res[3] = 0.0D;
        } else {
            res[2] = 0.0D;
            res[3] = 0.0D;

        }
        return res;
    }


}