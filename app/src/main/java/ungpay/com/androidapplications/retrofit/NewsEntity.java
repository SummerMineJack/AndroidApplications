package ungpay.com.androidapplications.retrofit;

import java.util.List;

/**
 * created by Summer on 2020/7/2
 */
public class NewsEntity {


    private String stat;
    private List<DataBean> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uniquekey : 461b8ad7670fc6ac2238651e50514940
         * title : 早餐不知道吃什么？来看这6款平底锅早餐，做法分享，照着做吧
         * date : 2020-07-02 10:08
         * category : 头条
         * author_name : 良润烘焙
         * url : https://mini.eastday.com/mobile/200702100847216.html
         * thumbnail_pic_s : https://03imgmini.eastday.com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_18_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://03imgmini.eastday.com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_17_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://03imgmini.eastday.com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_22_mwpm_03200403.jpg
         */

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }
    }
}
