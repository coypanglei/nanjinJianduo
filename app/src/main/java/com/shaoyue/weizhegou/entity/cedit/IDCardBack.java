package com.shaoyue.weizhegou.entity.cedit;

import com.shaoyue.weizhegou.base.BaseBean;

public class IDCardBack extends BaseBean {

    /**
     * log_id : 6790051746250251614
     * words_result_num : 3
     * image_status : normal
     * words_result : {"失效日期":{"location":{"width":239,"top":786,"left":888,"height":39},"words":"20230115"},"签发机关":{"location":{"width":476,"top":675,"left":610,"height":45},"words":"徐州市公安局泉山分局"},"签发日期":{"location":{"width":230,"top":785,"left":609,"height":41},"words":"20130115"}}
     */

    private long log_id;
    private int words_result_num;
    private String image_status;
    private WordsResultBean words_result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public String getImage_status() {
        return image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    public WordsResultBean getWords_result() {
        return words_result;
    }

    public void setWords_result(WordsResultBean words_result) {
        this.words_result = words_result;
    }

    public static class WordsResultBean {
        /**
         * 失效日期 : {"location":{"width":239,"top":786,"left":888,"height":39},"words":"20230115"}
         * 签发机关 : {"location":{"width":476,"top":675,"left":610,"height":45},"words":"徐州市公安局泉山分局"}
         * 签发日期 : {"location":{"width":230,"top":785,"left":609,"height":41},"words":"20130115"}
         */

        private 失效日期Bean 失效日期;
        private 签发机关Bean 签发机关;
        private 签发日期Bean 签发日期;

        public 失效日期Bean get失效日期() {
            return 失效日期;
        }

        public void set失效日期(失效日期Bean 失效日期) {
            this.失效日期 = 失效日期;
        }

        public 签发机关Bean get签发机关() {
            return 签发机关;
        }

        public void set签发机关(签发机关Bean 签发机关) {
            this.签发机关 = 签发机关;
        }

        public 签发日期Bean get签发日期() {
            return 签发日期;
        }

        public void set签发日期(签发日期Bean 签发日期) {
            this.签发日期 = 签发日期;
        }

        public static class 失效日期Bean {
            /**
             * location : {"width":239,"top":786,"left":888,"height":39}
             * words : 20230115
             */

            private LocationBean location;
            private String words;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public static class LocationBean {
                /**
                 * width : 239
                 * top : 786
                 * left : 888
                 * height : 39
                 */

                private int width;
                private int top;
                private int left;
                private int height;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 签发机关Bean {
            /**
             * location : {"width":476,"top":675,"left":610,"height":45}
             * words : 徐州市公安局泉山分局
             */

            private LocationBeanX location;
            private String words;

            public LocationBeanX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanX location) {
                this.location = location;
            }

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public static class LocationBeanX {
                /**
                 * width : 476
                 * top : 675
                 * left : 610
                 * height : 45
                 */

                private int width;
                private int top;
                private int left;
                private int height;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class 签发日期Bean {
            /**
             * location : {"width":230,"top":785,"left":609,"height":41}
             * words : 20130115
             */

            private LocationBeanXX location;
            private String words;

            public LocationBeanXX getLocation() {
                return location;
            }

            public void setLocation(LocationBeanXX location) {
                this.location = location;
            }

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            public static class LocationBeanXX {
                /**
                 * width : 230
                 * top : 785
                 * left : 609
                 * height : 41
                 */

                private int width;
                private int top;
                private int left;
                private int height;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
