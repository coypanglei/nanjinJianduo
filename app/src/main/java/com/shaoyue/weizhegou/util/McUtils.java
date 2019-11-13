package com.shaoyue.weizhegou.util;

public class McUtils {

    public static String getString(String status) {
        //-1 待采集
        //0  待认领
        //1  待现场检验
        //2  待协查
        //3  待小组组长检查
        //4  待授信部检查
        //200 完成
        //500 终止
        switch (status) {
            case "-1":
                return "待采集";
            case "0":
                return "待认领";
            case "1":
                return "待现场检验";
            case "2":
                return "待协查";
            case "3":
                return "待小组组长检查";
            case "4":
                return "待信贷部总经理审核";
            case "6":
                return "待授信部总经理审核";
            case "5":
                return "待授信部审批岗审核";
            case "200":
                return "完成";
            case "500":
                return "终止";

            default:
                break;
        }
        return "";
    }
}
