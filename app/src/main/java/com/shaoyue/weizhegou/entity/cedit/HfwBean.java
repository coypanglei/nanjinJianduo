package com.shaoyue.weizhegou.entity.cedit;

import java.util.List;

public class HfwBean {



        private List<ListBeanX> list;

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * 汇法网信息 : [{"title":"高亮(最高法执行)","list":[{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"32072219870xxx4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2016）苏0722执1898号"},{"title":"执行内容","value":"执行标的:25745.96"},{"title":"立案时间","value":"2016-06-22"},{"title":"执行状态","value":"终结本次执行程序"},{"title":"异议备注","value":"匹配度：97.5%"}]},{"title":"高亮(失信被执行人)","list":[{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"32072219870xxx4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2016）苏0722执1898号"},{"title":"执行内容","value":"被告高亮赔偿原告张顺美各项损失共计25745.96元,于本判决生效之日起十日内付清；赔偿义务人如果未按本判决指定的期间履行给付金钱义务,应当依照《中华人民共和国民事诉讼法》第二百五十三条之规定,加倍支付迟延履行期间的债务利息。案件受理费500元,减半收取250元,由被告高亮负担（已由原告垫付,待被告给付上述款项时一并支付给原告）。如不服本判决,可在判决书送达之日起十五日内,向本院递交上诉状,并按对方当事人的人数提出副本,上诉于江苏省连云港市中级人民法院,同时向连云港市中级人民法院预交上诉案件受理费500元。连云港市中院开户行：江苏省连云港市农行苍梧支行,账号：440301040009094,也可到农行东海县支行营业厅交款。依照《中华人民共和国民事诉讼法》第二百二十四条、第二百三十九条规定,本判决生效后,权利人可向本院或者与本院同级的被执行财产所在地法院申请强制执行,申请强制执行期限为二年"},{"title":"日期类别","value":"立案时间"},{"title":"具体日期","value":"2016-06-22"},{"title":"执行状态","value":"终结本次执行程序"},{"title":"异议备注","value":"匹配度：97.5%"}]},{"title":"原告张顺美诉被告高亮机动车交通事故责任纠纷一案(判决文书)","list":[{"title":"当事人姓名或名称","value":"高亮"},{"title":"证件号码","value":"32072219870xxx4814"},{"title":"审理机关","value":"江苏省东海县人民法院"},{"title":"案号","value":"（2015）连东民初字第1635号"},{"title":"涉案事由","value":"机动车交通事故责任纠纷"},{"title":"涉案金额","value":"25746.0000"},{"title":"诉讼地位","value":"被告"},{"title":"结案时间","value":"2015-11-05"},{"title":"异议备注","value":"匹配度：97.5%"}]},{"title":"高亮(最高法执行)","list":[{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"32072219870***4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2015）连东执字第1583号"},{"title":"执行内容","value":"金额:11117.21"},{"title":"立案时间","value":"2015-08-11"},{"title":"执行状态","value":"执行中"},{"title":"异议备注","value":"匹配度：97.5%"}]},{"title":"高亮(失信被执行人)","list":[{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"3207221987****4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2014）东执字第1619号"},{"title":"执行内容","value":"给付56689元"},{"title":"日期类别","value":"立案时间"},{"title":"具体日期","value":"2014-08-07"},{"title":"执行状态","value":""},{"title":"异议备注","value":"匹配度：96.5%"}]},{"title":"高亮(最高法执行)","list":[{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"3207221987****4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2014）东执字第1619号"},{"title":"执行内容","value":"金额:56689.00"},{"title":"立案时间","value":"2014-08-07"},{"title":"执行状态","value":"执行中"},{"title":"异议备注","value":"匹配度：100%"}]},{"title":"高亮(最高法执行)","list":[{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"3207221987****4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2014）连东执字第1619号"},{"title":"执行内容","value":"执行标的:56689"},{"title":"立案时间","value":"2014-08-07"},{"title":"执行状态","value":"执行中"},{"title":"异议备注","value":"匹配度：100%"}]}]
             * totalnumber : 7
             */

            private String totalnumber;
            private List<汇法网信息Bean> 汇法网信息;

            public String getTotalnumber() {
                return totalnumber;
            }

            public void setTotalnumber(String totalnumber) {
                this.totalnumber = totalnumber;
            }

            public List<汇法网信息Bean> get汇法网信息() {
                return 汇法网信息;
            }

            public void set汇法网信息(List<汇法网信息Bean> 汇法网信息) {
                this.汇法网信息 = 汇法网信息;
            }

            public static class 汇法网信息Bean {
                /**
                 * title : 高亮(最高法执行)
                 * list : [{"title":"被执行人姓名或名称","value":"高亮"},{"title":"证件号码","value":"32072219870xxx4814"},{"title":"执行法院","value":"江苏省东海县人民法院"},{"title":"执行案号","value":"（2016）苏0722执1898号"},{"title":"执行内容","value":"执行标的:25745.96"},{"title":"立案时间","value":"2016-06-22"},{"title":"执行状态","value":"终结本次执行程序"},{"title":"异议备注","value":"匹配度：97.5%"}]
                 */

                private String title;
                private List<ListBean> list;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * title : 被执行人姓名或名称
                     * value : 高亮
                     */

                    private String title;
                    private String value;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }

}
