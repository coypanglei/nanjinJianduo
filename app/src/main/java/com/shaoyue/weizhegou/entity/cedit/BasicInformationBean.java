package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.List;

public class BasicInformationBean extends BaseBean {

    public static final int SELECT = 0;

    public static final int EDIT = 1;

    public static final int TIME = 2;

    public static final int EDIT_LARGE = 3;

    public static final int SELECT_CHANGE = 5;

    public static final int TITLE = 4;


    /**
     * total : 17
     * records : [{"paramtype":"select","titile":"是否阳光信贷","name":"sfygxk","defaultvalue":null,"ordernum":1,"optionlist":[{"name":"是"},{"name":"否"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":2,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":3,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":4,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":5,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":6,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":7,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":8,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":9,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":10,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":11,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":12,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":13,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":14,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":15,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":16,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null},{"paramtype":"select","titile":"产品类型","name":"cplx","defaultvalue":null,"ordernum":17,"optionlist":[{"name":"公职人员消费贷款"},{"name":"居民贷"},{"name":"阳光信贷"},{"name":"晶都易贷"},{"name":"电商贷"},{"name":"光伏货"},{"name":"农业机械贷款"},{"name":"其他消费贷款"},{"name":"个体工商户贷款"},{"name":"装修贷"},{"name":"其他经营性贷款"}],"type":"个人信息","category":"基本信息","dateformat":null}]
     */

    private int total;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        public RecordsBean() {

        }
        public RecordsBean(String require, String paramtype, String titile) {
            this.require = require;
            this.paramtype = paramtype;
            this.titile = titile;
        }

        /**
         * paramtype : select
         * titile : 是否阳光信贷
         * name : sfygxk
         * defaultvalue : null
         * ordernum : 1
         * optionlist : [{"name":"是"},{"name":"否"}]
         * type : 个人信息
         * category : 基本信息
         * dateformat : null
         */

        private int SpanSize;

        public int getSpanSize() {
            return SpanSize;
        }

        public void setSpanSize(int spanSize) {
            SpanSize = spanSize;
        }

        private boolean edit;

        private String require;

        public boolean isEdit() {
            return edit;
        }

        public void setEdit(boolean edit) {
            this.edit = edit;
        }

        public String getRequire() {
            return require;
        }

        public void setRequire(String require) {
            this.require = require;
        }

        private String paramtype;
        private String titile;
        private String name;
        private String defaultvalue;
        private int ordernum;
        private String type;
        private String category;
        private String dateformat;
        private List<OptionlistBean> optionlist;
        private List<BasicInformationBean.RecordsBean> list;

        public List<RecordsBean> getList() {
            return list;
        }

        public void setList(List<RecordsBean> list) {
            this.list = list;
        }

        public String getParamtype() {
            return paramtype;
        }

        public void setParamtype(String paramtype) {
            this.paramtype = paramtype;
        }

        public String getTitile() {
            return titile;
        }

        public void setTitile(String titile) {
            this.titile = titile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDefaultvalue() {
            return defaultvalue;
        }

        public void setDefaultvalue(String defaultvalue) {
            this.defaultvalue = defaultvalue;
        }

        public int getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDateformat() {
            return dateformat;
        }

        public void setDateformat(String dateformat) {
            this.dateformat = dateformat;
        }

        public List<OptionlistBean> getOptionlist() {
            return optionlist;
        }

        public void setOptionlist(List<OptionlistBean> optionlist) {
            this.optionlist = optionlist;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "SpanSize=" + SpanSize +
                    ", edit=" + edit +
                    ", require='" + require + '\'' +
                    ", paramtype='" + paramtype + '\'' +
                    ", titile='" + titile + '\'' +
                    ", name='" + name + '\'' +
                    ", defaultvalue='" + defaultvalue + '\'' +
                    ", ordernum=" + ordernum +
                    ", type='" + type + '\'' +
                    ", category='" + category + '\'' +
                    ", dateformat=" + dateformat +
                    ", optionlist=" + optionlist +
                    '}';
        }

        public static class OptionlistBean {
            /**
             * name : 是
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
