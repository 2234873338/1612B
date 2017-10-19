package com.example.chinacalendar.bean;

/**
 * Created by Qiyan on 2017/8/28.
 */

public class DateData {

    /**
     * reason : successed
     * result : {"id":"2720","yangli":"2017-08-08","yinli":"丁酉(鸡)年闰六月十七","wuxing":"炉中火 危执位","chongsha":"冲鸡(辛酉)煞西","baiji":"丁不剃头头必生疮 卯不穿井水泉不香","jishen":"月德合 天恩 五合 益後 鸣犬对","yi":"嫁娶  开光 祭祀 祈福 求嗣 安香 出火 解除 伐木 入宅 移徙 安床 开市 交易 立券 栽种 出火 出行 安葬","xiongshen":"土府 天吏 五虚 朱雀","ji":"掘井 理发 作灶 动土 破土 开池"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * id : 2720
         * yangli : 2017-08-08
         * yinli : 丁酉(鸡)年闰六月十七
         * wuxing : 炉中火 危执位
         * chongsha : 冲鸡(辛酉)煞西
         * baiji : 丁不剃头头必生疮 卯不穿井水泉不香
         * jishen : 月德合 天恩 五合 益後 鸣犬对
         * yi : 嫁娶  开光 祭祀 祈福 求嗣 安香 出火 解除 伐木 入宅 移徙 安床 开市 交易 立券 栽种 出火 出行 安葬
         * xiongshen : 土府 天吏 五虚 朱雀
         * ji : 掘井 理发 作灶 动土 破土 开池
         */

        private String id;
        private String yangli;
        private String yinli;
        private String wuxing;
        private String chongsha;
        private String baiji;
        private String jishen;
        private String yi;
        private String xiongshen;
        private String ji;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getYinli() {
            return yinli;
        }

        public void setYinli(String yinli) {
            this.yinli = yinli;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getBaiji() {
            return baiji;
        }

        public void setBaiji(String baiji) {
            this.baiji = baiji;
        }

        public String getJishen() {
            return jishen;
        }

        public void setJishen(String jishen) {
            this.jishen = jishen;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }
    }
}
