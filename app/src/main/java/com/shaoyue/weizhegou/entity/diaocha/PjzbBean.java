package com.shaoyue.weizhegou.entity.diaocha;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class PjzbBean extends BaseBean {


    /**
     * sum : 35
     * list : [{"name":"个人情况","total":"8","list":[{"id":"abbea7f25d44885071e06cbe8418b84f","bm":"nh01","mc":"年龄","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":4,"zt":"1","dx":"个人情况","xh":1,"zbgs":"SxsqJtxx_nl","nr":"【18,28），1分、【28,35），2分、【35,50）岁，4分、【50,60）岁,3分、≥60，0分","gs":"value>=18&&value<28,1;value>=28&&value<35,2;value>=35&&value<50,4;value>=50&&value<60,3;value>=60,0;   ","bz":null,"delFlag":null,"createBy":"admin","createTime":"2028-10-09 15:46:18","updateBy":"admin","updateTime":"2021-04-09 16:16:12","description":null,"fenshu":"2","zhi":"29"},{"id":"5b13c854a7ac29d6018a21d7050d0f48","bm":"nh02","mc":"学历","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"个人情况","xh":2,"zbgs":"SxsqJtxx_xl","nr":"本科及以上2分；本科以下1分","gs":"value=='本科'||value=='研究生'||value=='博士'||value=='博士后'||value=='硕士',2;value=='大专'||value=='高中'||value=='初中',1","bz":null,"delFlag":null,"createBy":"admin","createTime":"2025-10-09 15:46:18","updateBy":"jeecg","updateTime":"2023-04-15 18:52:02","description":null,"fenshu":"2","zhi":"研究生"},{"id":"849c64f22eef926336b1491a306cfc23","bm":"nh03","mc":"健康状况","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"个人情况","xh":3,"zbgs":"SxsqJtxx_jkzk","nr":"健康，2分；一般，1分","gs":"value=='健康',2;value=='良好',1","bz":null,"delFlag":null,"createBy":"admin","createTime":"2022-10-09 15:46:18","updateBy":"jeecg","updateTime":"2019-10-15 18:53:58","description":null,"fenshu":"2","zhi":"健康"},{"id":"10275d987db4fca699d258ae5e11cb0d","bm":"nh04","mc":"户口","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"个人情况","xh":4,"zbgs":"SxdcJbxx_hk","nr":"常住户口2分、临时户口1分","gs":"value=='常住户口',2;value=='临时户口',1","bz":null,"delFlag":null,"createBy":"admin","createTime":"2022-10-09 15:46:18","updateBy":"jeecg","updateTime":"2019-10-15 18:56:58","description":null,"fenshu":"2","zhi":"常住户口"}],"manfenTotal":"10"},{"name":"家庭情况","total":"9","list":[{"id":"74696d0931050adef81ed92337424e24","bm":"nh05","mc":"婚姻状况","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"家庭情况","xh":5,"zbgs":"SxsqJtxx_hyzk","nr":"已婚，2分、丧偶，1分、未婚，1分、离异，0分","gs":"value=='已婚',2;value=='丧偶'||value=='未婚',1;value=='离异',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2025-10-09 15:46:18","updateBy":"jeecg","updateTime":"2023-10-15 18:58:02","description":null,"fenshu":"1","zhi":"未婚"},{"id":"232d64ac32a32b8f0f322e883919690c","bm":"nh06","mc":"家庭直接供养人口","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"家庭情况","xh":6,"zbgs":"SxdcSddc_gyrk","nr":"无供养人，2分；1人至2人，1分；大于等于3人，0分","gs":"value=='无人供养',2;value=='1人'||value=='2人',1;value=='3人以上',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2028-10-09 15:46:18","updateBy":"jeecg","updateTime":"2017-12-15 19:00:39","description":null,"fenshu":"0","zhi":"3人以上"},{"id":"5b73390ae4050646c86b0acaec73d428","bm":"nh07","mc":"社会评价","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":3,"zt":"1","dx":"家庭情况","xh":7,"zbgs":"SxdcSddc_shpj","nr":"社会信誉优良，口碑极佳，有较强的社会影响力的3分、社交诚信，口碑较好，有一定的社会地位的2分，社交无不良诚信记录，品行端正的1分、品行不断，社交诚信记录差的0分","gs":"value=='优秀',3;value=='良好',2;value=='一般',1;value=='差',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2025-10-09 15:46:18","updateBy":"jeecg","updateTime":"2019-03-15 19:03:11","description":null,"fenshu":"3","zhi":"优秀"},{"id":"55c2ba4d4b3ccaea89acfe29758fbbfb","bm":"nh08","mc":"现有房产情况","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":4,"zt":"1","dx":"家庭情况","xh":8,"zbgs":"SxdcSddc_fcqk","nr":"自有可转让，4分；自有不可转让，2分；其它，1分","gs":"value=='自有可转让',4;value=='自有不可转让',2;value=='其它',1","bz":null,"delFlag":null,"createBy":"admin","createTime":"2025-10-09 15:46:18","updateBy":"jeecg","updateTime":"2019-04-15 19:04:52","description":null,"fenshu":"1","zhi":"自由可转让"},{"id":"bd34ae75c3468e62b774f9d93adc3260","bm":"nh09","mc":"家庭状况总体评价","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"家庭情况","xh":9,"zbgs":"SxdcSddc_jtzkztpj","nr":"家庭和睦，2分、家庭不和睦，可控制1分、家庭不和睦，不可控制，0分","gs":"value=='家庭和睦',2;value.indexOf('不可控')!=-1,0;value.indexOf('可控制')!=-1,1;","bz":null,"delFlag":null,"createBy":"admin","createTime":"2028-10-09 15:46:18","updateBy":"jeecg","updateTime":"2019-10-15 19:10:15","description":null,"fenshu":"2","zhi":"家庭和睦"},{"id":"78f1fbf74d30668d1008b4085e93b89b","bm":"nh10","mc":"家庭经济来源","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":2,"zt":"1","dx":"家庭情况","xh":10,"zbgs":"SxdcSddc_jtjjlyqk","nr":"稳定，2分；较稳定，1分；不稳定，0分","gs":"value=='稳定',2;value=='较稳定',1;value=='不稳定',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2022-10-09 15:46:18","updateBy":"jeecg","updateTime":"2019-10-17 15:13:01","description":null,"fenshu":"2","zhi":"稳定"}],"manfenTotal":"15"},{"name":"财务情况","total":"0","list":[{"id":"329310e42c8e8a680d56997422921778","bm":"nh11","mc":"家庭净资产 地区比值倍数","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":20,"zt":"1","dx":"财务情况","xh":11,"zbgs":"SxsqJbxx_test","nr":"≥72，20分；[50，72），16分；[35，50），12分；[24，36），8分；[12，24），6分；[5，12），4分；＜5，0分","gs":"value=='test',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2019-10-09 15:46:18","updateBy":null,"updateTime":null,"description":null,"fenshu":"0","zhi":""},{"id":"62b45bdbce19a20aebcc95bfef062e04","bm":"nh12","mc":"家庭人均净收入 地区比值倍数","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":24,"zt":"1","dx":"财务情况","xh":12,"zbgs":"SxsqJbxx_test","nr":"≥4，24分；[2.5，4），10分；[1.5，2.5），8分；[0.9，1.5），6分；[0.5，0.9），4分；[0.2，0.5），2分；＜0.2，0分","gs":"value=='test',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2019-10-09 15:46:18","updateBy":null,"updateTime":null,"description":null,"fenshu":"0","zhi":""},{"id":"2d82fa6870b963b1dc8f93587117eba4","bm":"nh13","mc":"家庭净资产对外 担保倍数","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":10,"zt":"1","dx":"财务情况","xh":13,"zbgs":"SxsqJbxx_test","nr":"无担保，5分；≥5，10分；；[3-5)，8分；[1.8-3)，5分；[1.0-1.8)，2分；＜1，0分","gs":"value=='test',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2019-10-09 15:46:18","updateBy":null,"updateTime":null,"description":null,"fenshu":"0","zhi":""}],"manfenTotal":"54"},{"name":"行社关系","total":"18","list":[{"id":"9126f47424077c7afbf4ac23014c8551","bm":"nh14","mc":"个人信用记录","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":15,"zt":"1","dx":"行社关系","xh":14,"zbgs":"SxsqJbxx_grxyjl","nr":"有借款无不良记录, 15分、 无借款无不良记录,12分、 有借款有不良记录, 8分、 无借款有不良记录,5分、 有不良贷款余额（含担保不良贷款）0分","gs":"value=='有借款无不良记录',15;value=='无借款无不良记录',12;value=='有借款有不良记录',8;value=='无借款有不良记录',5;value=='有不良贷款余额(含担保不良贷款)',0","bz":null,"delFlag":null,"createBy":"admin","createTime":"2022-10-09 15:46:18","updateBy":"admin","updateTime":"2019-10-16 09:34:22","description":null,"fenshu":"12","zhi":"无借款无不良记录"},{"id":"f26ac10c18dce95fe2b504c282ba19b7","bm":"nh15","mc":"风险预警信号","mx":"bfd06fd4c8f27a274ca913278743898f","lb":"2","mf":6,"zt":"1","dx":"行社关系","xh":15,"zbgs":"SxdcJbxx_fxyjxh","nr":"无不良信息，6分；客户原因造成的黄色预警信息，4分；橙色预警信息，1分；红色预警信息或存在执行执行记录，0分","gs":"value=='红色预警信息或存在执行记录',0;value=='无不良信息',6;value=='客户原因造成的黄色预警信息',4;value=='橙色预警信息',1","bz":null,"delFlag":null,"createBy":"admin","createTime":"2022-10-09 15:46:18","updateBy":"admin","updateTime":"2019-10-16 09:38:32","description":null,"fenshu":"6","zhi":"无不良信息"}],"manfenTotal":"21"}]
     * manfenSum : 100
     * moblidPj : [{"得分":"2","项目":"个人情况","满分":4,"指标定义或计算公式":"【18,28），1分、【28,35），2分、【35,50）岁，4分、【50,60）岁,3分、≥60，0分","指标":"29","关键指标名称":"年龄"},{"得分":"2","项目":"个人情况","满分":2,"指标定义或计算公式":"本科及以上2分；本科以下1分","指标":"研究生","关键指标名称":"学历"},{"得分":"2","项目":"个人情况","满分":2,"指标定义或计算公式":"健康，2分；一般，1分","指标":"健康","关键指标名称":"健康状况"},{"得分":"2","项目":"个人情况","满分":2,"指标定义或计算公式":"常住户口2分、临时户口1分","指标":"常住户口","关键指标名称":"户口"},{"得分":"8","项目":"个人情况","满分":"10","指标定义或计算公式":"","指标":"","关键指标名称":"小计"},{"得分":"1","项目":"家庭情况","满分":2,"指标定义或计算公式":"已婚，2分、丧偶，1分、未婚，1分、离异，0分","指标":"未婚","关键指标名称":"婚姻状况"},{"得分":"0","项目":"家庭情况","满分":2,"指标定义或计算公式":"无供养人，2分；1人至2人，1分；大于等于3人，0分","指标":"3人以上","关键指标名称":"家庭直接供养人口"},{"得分":"3","项目":"家庭情况","满分":3,"指标定义或计算公式":"社会信誉优良，口碑极佳，有较强的社会影响力的3分、社交诚信，口碑较好，有一定的社会地位的2分，社交无不良诚信记录，品行端正的1分、品行不断，社交诚信记录差的0分","指标":"优秀","关键指标名称":"社会评价"},{"得分":"1","项目":"家庭情况","满分":4,"指标定义或计算公式":"自有可转让，4分；自有不可转让，2分；其它，1分","指标":"自由可转让","关键指标名称":"现有房产情况"},{"得分":"2","项目":"家庭情况","满分":2,"指标定义或计算公式":"家庭和睦，2分、家庭不和睦，可控制1分、家庭不和睦，不可控制，0分","指标":"家庭和睦","关键指标名称":"家庭状况总体评价"},{"得分":"2","项目":"家庭情况","满分":2,"指标定义或计算公式":"稳定，2分；较稳定，1分；不稳定，0分","指标":"稳定","关键指标名称":"家庭经济来源"},{"得分":"9","项目":"家庭情况","满分":"15","指标定义或计算公式":"","指标":"","关键指标名称":"小计"},{"得分":"0","项目":"财务情况","满分":20,"指标定义或计算公式":"≥72，20分；[50，72），16分；[35，50），12分；[24，36），8分；[12，24），6分；[5，12），4分；＜5，0分","指标":"","关键指标名称":"家庭净资产 地区比值倍数"},{"得分":"0","项目":"财务情况","满分":24,"指标定义或计算公式":"≥4，24分；[2.5，4），10分；[1.5，2.5），8分；[0.9，1.5），6分；[0.5，0.9），4分；[0.2，0.5），2分；＜0.2，0分","指标":"","关键指标名称":"家庭人均净收入 地区比值倍数"},{"得分":"0","项目":"财务情况","满分":10,"指标定义或计算公式":"无担保，5分；≥5，10分；；[3-5)，8分；[1.8-3)，5分；[1.0-1.8)，2分；＜1，0分","指标":"","关键指标名称":"家庭净资产对外 担保倍数"},{"得分":"0","项目":"财务情况","满分":"54","指标定义或计算公式":"","指标":"","关键指标名称":"小计"},{"得分":"12","项目":"行社关系","满分":15,"指标定义或计算公式":"有借款无不良记录, 15分、 无借款无不良记录,12分、 有借款有不良记录, 8分、 无借款有不良记录,5分、 有不良贷款余额（含担保不良贷款）0分","指标":"无借款无不良记录","关键指标名称":"个人信用记录"},{"得分":"6","项目":"行社关系","满分":6,"指标定义或计算公式":"无不良信息，6分；客户原因造成的黄色预警信息，4分；橙色预警信息，1分；红色预警信息或存在执行执行记录，0分","指标":"无不良信息","关键指标名称":"风险预警信号"},{"得分":"18","项目":"行社关系","满分":"21","指标定义或计算公式":"","指标":"","关键指标名称":"小计"},{"得分":"35","项目":"","满分":"100","指标定义或计算公式":"","指标":"综合得分","关键指标名称":"总计"}]
     */

    private List<MoblidPjBean> moblidPj;

    public List<MoblidPjBean> getMoblidPj() {
        return moblidPj;
    }

    public void setMoblidPj(List<MoblidPjBean> moblidPj) {
        this.moblidPj = moblidPj;
    }

    public static class MoblidPjBean {
        /**
         * 得分 : 2
         * 项目 : 个人情况
         * 满分 : 4
         * 指标定义或计算公式 : 【18,28），1分、【28,35），2分、【35,50）岁，4分、【50,60）岁,3分、≥60，0分
         * 指标 : 29
         * 关键指标名称 : 年龄
         */

        private String 得分;
        private String 项目;
        private int 满分;
        private String 指标定义或计算公式;
        private String 指标;
        private String 关键指标名称;

        public String get得分() {
            return 得分;
        }

        public void set得分(String 得分) {
            this.得分 = 得分;
        }

        public String get项目() {
            return 项目;
        }

        public void set项目(String 项目) {
            this.项目 = 项目;
        }

        public int get满分() {
            return 满分;
        }

        public void set满分(int 满分) {
            this.满分 = 满分;
        }

        public String get指标定义或计算公式() {
            return 指标定义或计算公式;
        }

        public void set指标定义或计算公式(String 指标定义或计算公式) {
            this.指标定义或计算公式 = 指标定义或计算公式;
        }

        public String get指标() {
            return 指标;
        }

        public void set指标(String 指标) {
            this.指标 = 指标;
        }

        public String get关键指标名称() {
            return 关键指标名称;
        }

        public void set关键指标名称(String 关键指标名称) {
            this.关键指标名称 = 关键指标名称;
        }
    }
}
