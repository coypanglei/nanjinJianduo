package com.shaoyue.weizhegou.entity.dhgl;

import com.shaoyue.weizhegou.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class DgZxcxBean extends BaseBean {


    /**
     * records : [{"jk":[{"gzlzhs":"0","gzlye":"0","zclzhs":"0","hjzhs":"0","bllye":"0","zclye":"0","lx":"中长期借款","bllzhs":"0","hjye":"0"},{"gzlzhs":"0","gzlye":"0","zclzhs":"2","hjzhs":"2","bllye":"0","zclye":"3740","lx":"短期借款","bllzhs":"0","hjye":"3740"},{"gzlzhs":"0","gzlye":"0","zclzhs":"0","hjzhs":"0","bllye":"0","zclye":"0","lx":"循环透支","bllzhs":"0","hjye":"0"},{"gzlzhs":"0","gzlye":"0","zclzhs":"0","hjzhs":"0","bllye":"0","zclye":"0","lx":"贴现","bllzhs":"0","hjye":"0"},{"gzlzhs":"0","gzlye":"0","zclzhs":"2","hjzhs":"2","bllye":"0","zclye":"3740","lx":"合计","bllzhs":"0","hjye":"3740"}],"fxhxy":{"syed":"0","yyed":"2000","ze":"2000"},"wjqjgs":"2","dbjy":{"gzlye":"0","blye":"0","ye":"0"},"jgs":"2","jdjy":{"gzlye":"0","blye":"0","bpche":"0","ye":"3740"},"xhxy":{"syed":"0","yyed":"0","ze":"0"},"snf":"2008","shknf":""}]
     * total : 1
     * size : 10
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * jk : [{"gzlzhs":"0","gzlye":"0","zclzhs":"0","hjzhs":"0","bllye":"0","zclye":"0","lx":"中长期借款","bllzhs":"0","hjye":"0"},{"gzlzhs":"0","gzlye":"0","zclzhs":"2","hjzhs":"2","bllye":"0","zclye":"3740","lx":"短期借款","bllzhs":"0","hjye":"3740"},{"gzlzhs":"0","gzlye":"0","zclzhs":"0","hjzhs":"0","bllye":"0","zclye":"0","lx":"循环透支","bllzhs":"0","hjye":"0"},{"gzlzhs":"0","gzlye":"0","zclzhs":"0","hjzhs":"0","bllye":"0","zclye":"0","lx":"贴现","bllzhs":"0","hjye":"0"},{"gzlzhs":"0","gzlye":"0","zclzhs":"2","hjzhs":"2","bllye":"0","zclye":"3740","lx":"合计","bllzhs":"0","hjye":"3740"}]
         * fxhxy : {"syed":"0","yyed":"2000","ze":"2000"}
         * wjqjgs : 2
         * dbjy : {"gzlye":"0","blye":"0","ye":"0"}
         * jgs : 2
         * jdjy : {"gzlye":"0","blye":"0","bpche":"0","ye":"3740"}
         * xhxy : {"syed":"0","yyed":"0","ze":"0"}
         * snf : 2008
         * shknf :
         */

        private FxhxyBean fxhxy;
        private String wjqjgs;
        private DbjyBean dbjy;
        private String jgs;
        private JdjyBean jdjy;
        private XhxyBean xhxy;
        private String snf;
        private String shknf;
        private List<JkBean> jk = new ArrayList<>();

        public FxhxyBean getFxhxy() {
            return fxhxy;
        }

        public void setFxhxy(FxhxyBean fxhxy) {
            this.fxhxy = fxhxy;
        }

        public String getWjqjgs() {
            return wjqjgs;
        }

        public void setWjqjgs(String wjqjgs) {
            this.wjqjgs = wjqjgs;
        }

        public DbjyBean getDbjy() {
            return dbjy;
        }

        public void setDbjy(DbjyBean dbjy) {
            this.dbjy = dbjy;
        }

        public String getJgs() {
            return jgs;
        }

        public void setJgs(String jgs) {
            this.jgs = jgs;
        }

        public JdjyBean getJdjy() {
            return jdjy;
        }

        public void setJdjy(JdjyBean jdjy) {
            this.jdjy = jdjy;
        }

        public XhxyBean getXhxy() {
            return xhxy;
        }

        public void setXhxy(XhxyBean xhxy) {
            this.xhxy = xhxy;
        }

        public String getSnf() {
            return snf;
        }

        public void setSnf(String snf) {
            this.snf = snf;
        }

        public String getShknf() {
            return shknf;
        }

        public void setShknf(String shknf) {
            this.shknf = shknf;
        }

        public List<JkBean> getJk() {
            return jk;
        }

        public void setJk(List<JkBean> jk) {
            this.jk = jk;
        }

        public static class FxhxyBean {
            /**
             * syed : 0
             * yyed : 2000
             * ze : 2000
             */

            private String syed;
            private String yyed;
            private String ze;

            public String getSyed() {
                return syed;
            }

            public void setSyed(String syed) {
                this.syed = syed;
            }

            public String getYyed() {
                return yyed;
            }

            public void setYyed(String yyed) {
                this.yyed = yyed;
            }

            public String getZe() {
                return ze;
            }

            public void setZe(String ze) {
                this.ze = ze;
            }
        }

        public static class DbjyBean {
            /**
             * gzlye : 0
             * blye : 0
             * ye : 0
             */

            private String gzlye;
            private String blye;
            private String ye;

            public String getGzlye() {
                return gzlye;
            }

            public void setGzlye(String gzlye) {
                this.gzlye = gzlye;
            }

            public String getBlye() {
                return blye;
            }

            public void setBlye(String blye) {
                this.blye = blye;
            }

            public String getYe() {
                return ye;
            }

            public void setYe(String ye) {
                this.ye = ye;
            }
        }

        public static class JdjyBean {
            /**
             * gzlye : 0
             * blye : 0
             * bpche : 0
             * ye : 3740
             */

            private String gzlye;
            private String blye;
            private String bpche;
            private String ye;

            public String getGzlye() {
                return gzlye;
            }

            public void setGzlye(String gzlye) {
                this.gzlye = gzlye;
            }

            public String getBlye() {
                return blye;
            }

            public void setBlye(String blye) {
                this.blye = blye;
            }

            public String getBpche() {
                return bpche;
            }

            public void setBpche(String bpche) {
                this.bpche = bpche;
            }

            public String getYe() {
                return ye;
            }

            public void setYe(String ye) {
                this.ye = ye;
            }
        }

        public static class XhxyBean {
            /**
             * syed : 0
             * yyed : 0
             * ze : 0
             */

            private String syed;
            private String yyed;
            private String ze;

            public String getSyed() {
                return syed;
            }

            public void setSyed(String syed) {
                this.syed = syed;
            }

            public String getYyed() {
                return yyed;
            }

            public void setYyed(String yyed) {
                this.yyed = yyed;
            }

            public String getZe() {
                return ze;
            }

            public void setZe(String ze) {
                this.ze = ze;
            }
        }

        public static class JkBean {
            /**
             * gzlzhs : 0
             * gzlye : 0
             * zclzhs : 0
             * hjzhs : 0
             * bllye : 0
             * zclye : 0
             * lx : 中长期借款
             * bllzhs : 0
             * hjye : 0
             */

            private String gzlzhs;
            private String gzlye;
            private String zclzhs;
            private String hjzhs;
            private String bllye;
            private String zclye;
            private String lx;
            private String bllzhs;
            private String hjye;

            public String getGzlzhs() {
                return gzlzhs;
            }

            public void setGzlzhs(String gzlzhs) {
                this.gzlzhs = gzlzhs;
            }

            public String getGzlye() {
                return gzlye;
            }

            public void setGzlye(String gzlye) {
                this.gzlye = gzlye;
            }

            public String getZclzhs() {
                return zclzhs;
            }

            public void setZclzhs(String zclzhs) {
                this.zclzhs = zclzhs;
            }

            public String getHjzhs() {
                return hjzhs;
            }

            public void setHjzhs(String hjzhs) {
                this.hjzhs = hjzhs;
            }

            public String getBllye() {
                return bllye;
            }

            public void setBllye(String bllye) {
                this.bllye = bllye;
            }

            public String getZclye() {
                return zclye;
            }

            public void setZclye(String zclye) {
                this.zclye = zclye;
            }

            public String getLx() {
                return lx;
            }

            public void setLx(String lx) {
                this.lx = lx;
            }

            public String getBllzhs() {
                return bllzhs;
            }

            public void setBllzhs(String bllzhs) {
                this.bllzhs = bllzhs;
            }

            public String getHjye() {
                return hjye;
            }

            public void setHjye(String hjye) {
                this.hjye = hjye;
            }
        }
    }
}
