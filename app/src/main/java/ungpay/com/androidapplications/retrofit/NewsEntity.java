package ungpay.com.androidapplications.retrofit;

import java.util.List;

/**
 * created by Summer on 2020/7/2
 */
public class NewsEntity {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"461b8ad7670fc6ac2238651e50514940","title":"早餐不知道吃什么？来看这6款平底锅早餐，做法分享，照着做吧","date":"2020-07-02 10:08","category":"头条","author_name":"良润烘焙",
     * "url":"https://mini.eastday.com/mobile/200702100847216.html","thumbnail_pic_s":"https://03imgmini.eastday
     * .com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_18_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday
     * .com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_17_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
     * .com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_22_mwpm_03200403.jpg"},{"uniquekey":"8d61fda6e347c15cb753c8d43e44a2bb","title":"三城正式联手！重庆成都之外，川渝诞生一个新经济圈",
     * "date":"2020-07-02 09:56","category":"头条","author_name":"西部之城事","url":"https://mini.eastday.com/mobile/200702095657655.html","thumbnail_pic_s":"https://02imgmini.eastday
     * .com/mobile/20200702/20200702095657_02567349d0e7dbf7c068154a075963f0_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702095657_02567349d0e7dbf7c068154a075963f0_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702095657_02567349d0e7dbf7c068154a075963f0_5_mwpm_03200403.jpg"},{"uniquekey":"02e3c68084061401bf0e5e2c47e97bcb",
     * "title":"跌至2358元，256GB+三星AMOLED+50倍变焦，悬浮液冷散热+4160mAh","date":"2020-07-02 09:53","category":"头条","author_name":"DNF怪咖","url":"https://mini.eastday.com/mobile/200702095339698.html",
     * "thumbnail_pic_s":"https://02imgmini.eastday.com/mobile/20200702/20200702095339_f002856703d21d22db9bb79536ee0c4d_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702095339_f002856703d21d22db9bb79536ee0c4d_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702095339_f002856703d21d22db9bb79536ee0c4d_2_mwpm_03200403.jpg"},{"uniquekey":"ee1282226a1a444168d818e56b920198","title":"首发骁龙865 Plus 华硕ROG游戏手机3定了",
     * "date":"2020-07-02 09:47","category":"头条","author_name":"快科技","url":"https://mini.eastday.com/mobile/200702094701613.html","thumbnail_pic_s":"https://06imgmini.eastday
     * .com/mobile/20200702/20200702094701_c84eb3fad4473aff17a4b045a7a7350c_1_mwpm_03200403.jpg"},{"uniquekey":"7b80973819955a56b654a004237b6c57","title":"全球洗面奶哪个牌子好 世界洗面奶排行榜10强",
     * "date":"2020-07-02 09:45","category":"头条","author_name":"容姐时尚说","url":"https://mini.eastday.com/mobile/200702094534440.html","thumbnail_pic_s":"https://05imgmini.eastday
     * .com/mobile/20200702/20200702094534_02e601a8cae79c90df561d89b449412a_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday
     * .com/mobile/20200702/20200702094534_02e601a8cae79c90df561d89b449412a_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday
     * .com/mobile/20200702/20200702094534_02e601a8cae79c90df561d89b449412a_5_mwpm_03200403.jpg"},{"uniquekey":"a5d3fd009024725fa56cb8bafb0a53d2","title":"北京继续发布雷电蓝色预警：大部分地区有雷阵雨天气",
     * "date":"2020-07-02 09:42","category":"头条","author_name":"中国新闻网","url":"https://mini.eastday.com/mobile/200702094253435.html","thumbnail_pic_s":"https://00imgmini.eastday
     * .com/mobile/20200702/20200702094253_dafe19197093c2c1388b5d2039729624_1_mwpm_03200403.jpg"},{"uniquekey":"ac1f4c1d894b819c98dbc069b5a3c1a8","title":"尼康P1000和华为P40Pro+长焦对比：差距依然十分明显",
     * "date":"2020-07-02 09:39","category":"头条","author_name":"3C毒物","url":"https://mini.eastday.com/mobile/200702093949081.html","thumbnail_pic_s":"https://09imgmini.eastday
     * .com/mobile/20200702/2020070209_ec99df7db82d4a58ac45caf3fc1cf1aa_3632_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday
     * .com/mobile/20200702/2020070209_305cdc8bd5cc47c8b22047ef42f7e2f4_9484_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday
     * .com/mobile/20200702/2020070209_73b3a8062f2245a0bdfeb2bc257db82f_8834_mwpm_03200403.jpg"},{"uniquekey":"f74417a9d00c175bf8e15370a8e50f9a",
     * "title":"盖亚任务\u2014\u2014绘制出银河系中心棒状恒星集合的地图","date":"2020-07-02 09:38","category":"头条","author_name":"天文在线","url":"https://mini.eastday.com/mobile/200702093837763.html",
     * "thumbnail_pic_s":"https://01imgmini.eastday.com/mobile/20200702/2020070209_9b9cd38052304ee08366c99b0d7fc63c_7612_cover_mwpm_03200403.jpg"},{"uniquekey
     * ":"cd24001293b4ffe65b6ab1dad821dd6b","title":"《局中人》《爱我就别想太多》遭网友狂吐槽","date":"2020-07-02 09:26","category":"头条","author_name":"光明网","url":"https://mini.eastday
     * .com/mobile/200702092613860.html","thumbnail_pic_s":"https://09imgmini.eastday.com/mobile/20200702/20200702092613_786f7006160f6653dfb30edcecfa7c8a_1_mwpm_03200403.jpg"},{"uniquekey
     * ":"b3bb779304c8f98fa08f2cf53f312b27","title":"刺伤警员的香港男子企图连夜潜逃英国，警方接其亲友举报在机场将其抓获！","date":"2020-07-02 09:22","category":"头条","author_name":"环球网","url":"https://mini.eastday
     * .com/mobile/200702092220716.html","thumbnail_pic_s":"https://08imgmini.eastday.com/mobile/20200702/20200702092220_d6e9b5aebb0691ed0a3607b864c286a5_1_mwpm_03200403.jpg",
     * "thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20200702/20200702092220_6e521718c90eaa5b177f5c936eb47e5a_2_mwpm_03200403.jpg"},{"uniquekey
     * ":"9dfffdfb92a039be8002bed71bf9b497","title":"复赛最大罚单，姚明严厉整治CBA乱象，要为裁判树立起无上威严","date":"2020-07-02 09:21","category":"头条","author_name":"毛罗话体坛","url":"https://mini.eastday
     * .com/mobile/200702092100776.html","thumbnail_pic_s":"https://01imgmini.eastday.com/mobile/20200702/2020070209_5e234f0558624b0b98b85a16deb5b512_2699_mwpm_03200403.jpg",
     * "thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20200702/2020070209_ce070e7735024996a7f344f309331a5c_9437_mwpm_03200403.jpg"},{"uniquekey
     * ":"2d6d9618de78efe8b527c5cf85e8435e","title":"这早餐连吃7天也不腻！蓬松柔软香喷喷，无油无糖，健康100分","date":"2020-07-02 09:17","category":"头条","author_name":"美美家的小厨房TB","url":"https://mini.eastday
     * .com/mobile/200702091701904.html","thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/20200702091701_771b1d8ea4685fe20e8cc3b72ff99a07_5_mwpm_03200403.jpg",
     * "thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20200702/20200702091701_771b1d8ea4685fe20e8cc3b72ff99a07_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
     * .com/mobile/20200702/20200702091701_771b1d8ea4685fe20e8cc3b72ff99a07_9_mwpm_03200403.jpg"},{"uniquekey":"aff9739ee7a307d737255f0afd1d894f","title":"北京新增1例确诊在丰台","date":"2020-07-02
     * 09:13","category":"头条","author_name":"光明网","url":"https://mini.eastday.com/mobile/200702091338290.html","thumbnail_pic_s":"https://01imgmini.eastday
     * .com/mobile/20200702/20200702091338_85b868791adf3c15303dce6c117eb4bc_1_mwpm_03200403.jpg"},{"uniquekey":"5555f14705a283680728bf372a9a1aa5","title":"重走红色新闻路丨红色南泥湾 陕北好江南",
     * "date":"2020-07-02 09:11","category":"头条","author_name":"陕西传媒网","url":"https://mini.eastday.com/mobile/200702091117378.html","thumbnail_pic_s":"https://01imgmini.eastday
     * .com/mobile/20200702/20200702091117_c9a836da73623f0e2c17d7e3c349ace9_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday
     * .com/mobile/20200702/20200702091117_c9a836da73623f0e2c17d7e3c349ace9_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday
     * .com/mobile/20200702/20200702091117_c9a836da73623f0e2c17d7e3c349ace9_2_mwpm_03200403.jpg"},{"uniquekey":"44902f9aa07d459ad0e3bb1ecb4dc8e5","title":"郭麒麟穿汉服忘记告诉杨幂，谁注意杨幂问的啥？让郭麒麟情何以堪",
     * "date":"2020-07-02 09:07","category":"头条","author_name":"喵吃娱资讯号","url":"https://mini.eastday.com/mobile/200702090713681.html","thumbnail_pic_s":"https://04imgmini.eastday
     * .com/mobile/20200702/20200702090713_0c52c9abdebbc0d241ae91c227359cff_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday
     * .com/mobile/20200702/20200702090713_0c52c9abdebbc0d241ae91c227359cff_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday
     * .com/mobile/20200702/20200702090713_0c52c9abdebbc0d241ae91c227359cff_8_mwpm_03200403.jpg"},{"uniquekey":"150da6dda290007f0e848e2b723976a6","title":"继续做账？巴萨尤文酝酿再做交易 格列兹曼一换二",
     * "date":"2020-07-02 09:07","category":"头条","author_name":"足坛欧美汇","url":"https://mini.eastday.com/mobile/200702090711389.html","thumbnail_pic_s":"https://01imgmini.eastday
     * .com/mobile/20200702/20200702090711_94bf0608f9fa28e3d69b63b03e87054e_1_mwpm_03200403.jpg"},{"uniquekey":"905949d7bec79527ddd060a3ffef02d9","title":"想要优美的曲线？常练6式瑜伽，让你的身体更紧致",
     * "date":"2020-07-02 09:04","category":"头条","author_name":"瑜伽坊","url":"https://mini.eastday.com/mobile/200702090404593.html","thumbnail_pic_s":"https://03imgmini.eastday
     * .com/mobile/20200702/2020062713_25c038feb74142fdbbfc81b985db3bd0_3272_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday
     * .com/mobile/20200702/2020062713_1dcc52c1a316496d9cb5d11c874da578_7493_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
     * .com/mobile/20200702/2020062713_af798f3d27834f1391f44efc8eee8792_9143_cover_mwpm_03200403.jpg"},{"uniquekey":"d074f064fe89b127c55572c3537b1d06","title":"央八《小娘惹》开播，我却迷上了里面的美食！",
     * "date":"2020-07-02 09:03","category":"头条","author_name":"快资讯","url":"https://mini.eastday.com/mobile/200702090315100.html","thumbnail_pic_s":"https://02imgmini.eastday
     * .com/mobile/20200702/20200702090315_0a01b4f165341bb36461e81dfe552ad7_10_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702090315_0a01b4f165341bb36461e81dfe552ad7_14_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702090315_0a01b4f165341bb36461e81dfe552ad7_2_mwpm_03200403.jpg"},{"uniquekey":"51ad1a3deb51cb074bc3b048ca4a1ced",
     * "title":"《我的前半生》与黄磊最直接的关系就是堪称\u201c深夜食堂\u201d与\u201c麻烦家族\u201d的合体！","date":"2020-07-02 09:03","category":"头条","author_name":"快资讯","url":"https://mini.eastday
     * .com/mobile/200702090314869.html","thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/20200702090314_6e5767d55204f67d066c6bc78a683f14_3_mwpm_03200403.jpg",
     * "thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20200702/20200702090314_6e5767d55204f67d066c6bc78a683f14_14_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini
     * .eastday.com/mobile/20200702/20200702090314_6e5767d55204f67d066c6bc78a683f14_9_mwpm_03200403.jpg"},{"uniquekey":"8d03bf083bba74a6ec37f745cb74a8b3","title":"济南市总投资265665
     * .1万元项目！2020年山东济南市重大建设项目","date":"2020-07-02 09:02","category":"头条","author_name":"中项网","url":"https://mini.eastday.com/mobile/200702090258009.html",
     * "thumbnail_pic_s":"https://01imgmini.eastday.com/mobile/20200702/20200702090258_2650e561542d1a00d62cf97fde4a3e26_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday
     * .com/mobile/20200702/20200702090258_2650e561542d1a00d62cf97fde4a3e26_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday
     * .com/mobile/20200702/20200702090258_2650e561542d1a00d62cf97fde4a3e26_3_mwpm_03200403.jpg"},{"uniquekey":"703287c5769a97ba561cb797fee60316","title":"7月1日转会汇总：巴萨尤文谈格里兹曼交易，拜仁一日双签",
     * "date":"2020-07-02 09:01","category":"头条","author_name":"欧洲足球观察","url":"https://mini.eastday.com/mobile/200702090138887.html","thumbnail_pic_s":"https://06imgmini.eastday
     * .com/mobile/20200702/20200702090138_1c0b94e51d9563f6a38caa73fdbb331a_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday
     * .com/mobile/20200702/20200702090138_1c0b94e51d9563f6a38caa73fdbb331a_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday
     * .com/mobile/20200702/20200702090138_1c0b94e51d9563f6a38caa73fdbb331a_4_mwpm_03200403.jpg"},{"uniquekey":"c2cee7d747e04ec41f39c35640f565f0","title":"事情正在起变化，这件事欧美真要翻脸了！",
     * "date":"2020-07-02 09:00","category":"头条","author_name":"解放网","url":"https://mini.eastday.com/mobile/200702090021526.html","thumbnail_pic_s":"https://02imgmini.eastday
     * .com/mobile/20200702/20200702090021_eac6bed5ef68524857cf3a062e4b4461_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702090021_eac6bed5ef68524857cf3a062e4b4461_2_mwpm_03200403.jpg"},{"uniquekey":"ce3dc0b1d9325ab309db1d0f22347bb7","title":"小伙自带洗漱用品到派出所：我是不是被通缉了？民警一查：还真是",
     * "date":"2020-07-02 08:57","category":"头条","author_name":"北青网","url":"https://mini.eastday.com/mobile/200702085759752.html","thumbnail_pic_s":"https://04imgmini.eastday
     * .com/mobile/20200702/20200702085759_5f206f26cd09038b1d080ed68c794027_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday
     * .com/mobile/20200702/20200702085759_5f206f26cd09038b1d080ed68c794027_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday
     * .com/mobile/20200702/20200702085759_5f206f26cd09038b1d080ed68c794027_1_mwpm_03200403.jpg"},{"uniquekey":"d000acf52e93d3320061d0f7c0f13148",
     * "title":"重磅对决！辽宁VS广厦：郭艾伦PK孙铭徽，辽宁报一箭之仇冲三连胜","date":"2020-07-02 08:55","category":"头条","author_name":"看球讲道李","url":"https://mini.eastday.com/mobile/200702085518334.html",
     * "thumbnail_pic_s":"https://02imgmini.eastday.com/mobile/20200702/20200702085518_68e7e9cde832f540ef628d67608e185f_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702085518_68e7e9cde832f540ef628d67608e185f_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
     * .com/mobile/20200702/20200702085518_68e7e9cde832f540ef628d67608e185f_4_mwpm_03200403.jpg"},{"uniquekey":"f2b424f58ee1aa10ae6cb346adca4d9a","title":"阔太方媛为好友庆生 与吴佩慈同框各具美态",
     * "date":"2020-07-02 08:54","category":"头条","author_name":"中国娱乐网","url":"https://mini.eastday.com/mobile/200702085420413.html","thumbnail_pic_s":"https://06imgmini.eastday
     * .com/mobile/20200702/20200702085420_ad326ae9edf54bd787bb7cbdcab847cd_1_mwpm_03200403.jpg"},{"uniquekey":"050b57bbfc75923c181ab92873324cde","title":"民调：美国人对疫情的焦虑情绪达到最高水平",
     * "date":"2020-07-02 08:53","category":"头条","author_name":"央视网","url":"https://mini.eastday.com/mobile/200702085322557.html","thumbnail_pic_s":"https://01imgmini.eastday
     * .com/mobile/20200702/20200702085322_5e59b8c29fea7d1aa0e0c301f0f1dd82_1_mwpm_03200403.jpg"},{"uniquekey":"0509291c9774d824658c64e7aa68fdf7","title":"《北京市医院安全秩序管理规定》7月1日施行 受暴力威胁时
     * 医生可暂停诊疗","date":"2020-07-02 08:53","category":"头条","author_name":"央视网","url":"https://mini.eastday.com/mobile/200702085322494.html","thumbnail_pic_s":"https://01imgmini.eastday
     * .com/mobile/20200702/20200702085322_54e5c23d4f0cc308c5f0bef1cf57a436_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday
     * .com/mobile/20200702/20200702085322_54e5c23d4f0cc308c5f0bef1cf57a436_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday
     * .com/mobile/20200702/20200702085322_54e5c23d4f0cc308c5f0bef1cf57a436_1_mwpm_03200403.jpg"},{"uniquekey":"de8b85c47458801d3d81dfec1f232861",
     * "title":"《向往》他不满黄磊差别对待？连催10次鹿晗去干活，原因却很暖心","date":"2020-07-02 08:43","category":"头条","author_name":"快资讯","url":"https://mini.eastday.com/mobile/200702084303521.html",
     * "thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/20200702084303_56458f6222f1cbbc7123b1c61edcdb68_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday
     * .com/mobile/20200702/20200702084303_56458f6222f1cbbc7123b1c61edcdb68_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
     * .com/mobile/20200702/20200702084303_56458f6222f1cbbc7123b1c61edcdb68_13_mwpm_03200403.jpg"},{"uniquekey":"30038ffc7b83e50919015afb3d2392eb",
     * "title":"牛肉怎么吃？大叔教你牛肉炖萝卜，鲜香味美，简单易做，超好吃！","date":"2020-07-02 08:27","category":"头条","author_name":"大叔下厨房","url":"https://mini.eastday.com/mobile/200702082737125.html",
     * "thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/2020070208_138d9474bbe44e7cbd04aa6880872695_2343_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday
     * .com/mobile/20200702/2020070208_2abe8940d14549268bb644902a448800_7375_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
     * .com/mobile/20200702/2020070208_70529db69d8d47d584eaf7f095eb63aa_4709_mwpm_03200403.jpg"},{"uniquekey":"d8d00473de910451b0a8705cba4eff6e","title":"开国元勋后代向香山革命纪念馆捐赠革命文物史料",
     * "date":"2020-07-02 08:25","category":"头条","author_name":"人民网本地站","url":"https://mini.eastday.com/mobile/200702082544702.html","thumbnail_pic_s":"https://07imgmini.eastday
     * .com/mobile/20200702/20200702082544_c386e7a7c012be54308b9f4eaae244e7_1_mwpm_03200403.jpg"}]}
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
         * stat : 1
         * data : [{"uniquekey":"461b8ad7670fc6ac2238651e50514940","title":"早餐不知道吃什么？来看这6款平底锅早餐，做法分享，照着做吧","date":"2020-07-02 10:08","category":"头条","author_name":"良润烘焙",
         * "url":"https://mini.eastday.com/mobile/200702100847216.html","thumbnail_pic_s":"https://03imgmini.eastday
         * .com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_18_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday
         * .com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_17_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
         * .com/mobile/20200702/20200702100847_15a4ecd75dc541cafe96ed909bb57d61_22_mwpm_03200403.jpg"},{"uniquekey":"8d61fda6e347c15cb753c8d43e44a2bb","title":"三城正式联手！重庆成都之外，川渝诞生一个新经济圈",
         * "date":"2020-07-02 09:56","category":"头条","author_name":"西部之城事","url":"https://mini.eastday.com/mobile/200702095657655.html","thumbnail_pic_s":"https://02imgmini.eastday
         * .com/mobile/20200702/20200702095657_02567349d0e7dbf7c068154a075963f0_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
         * .com/mobile/20200702/20200702095657_02567349d0e7dbf7c068154a075963f0_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
         * .com/mobile/20200702/20200702095657_02567349d0e7dbf7c068154a075963f0_5_mwpm_03200403.jpg"},{"uniquekey":"02e3c68084061401bf0e5e2c47e97bcb",
         * "title":"跌至2358元，256GB+三星AMOLED+50倍变焦，悬浮液冷散热+4160mAh","date":"2020-07-02 09:53","category":"头条","author_name":"DNF怪咖","url":"https://mini.eastday.com/mobile/200702095339698
         * .html","thumbnail_pic_s":"https://02imgmini.eastday.com/mobile/20200702/20200702095339_f002856703d21d22db9bb79536ee0c4d_4_mwpm_03200403.jpg",
         * "thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20200702/20200702095339_f002856703d21d22db9bb79536ee0c4d_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini
         * .eastday.com/mobile/20200702/20200702095339_f002856703d21d22db9bb79536ee0c4d_2_mwpm_03200403.jpg"},{"uniquekey":"ee1282226a1a444168d818e56b920198","title":"首发骁龙865 Plus
         * 华硕ROG游戏手机3定了","date":"2020-07-02 09:47","category":"头条","author_name":"快科技","url":"https://mini.eastday.com/mobile/200702094701613.html","thumbnail_pic_s":"https://06imgmini
         * .eastday.com/mobile/20200702/20200702094701_c84eb3fad4473aff17a4b045a7a7350c_1_mwpm_03200403.jpg"},{"uniquekey":"7b80973819955a56b654a004237b6c57","title":"全球洗面奶哪个牌子好
         * 世界洗面奶排行榜10强","date":"2020-07-02 09:45","category":"头条","author_name":"容姐时尚说","url":"https://mini.eastday.com/mobile/200702094534440.html","thumbnail_pic_s":"https://05imgmini
         * .eastday.com/mobile/20200702/20200702094534_02e601a8cae79c90df561d89b449412a_8_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday
         * .com/mobile/20200702/20200702094534_02e601a8cae79c90df561d89b449412a_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday
         * .com/mobile/20200702/20200702094534_02e601a8cae79c90df561d89b449412a_5_mwpm_03200403.jpg"},{"uniquekey":"a5d3fd009024725fa56cb8bafb0a53d2","title":"北京继续发布雷电蓝色预警：大部分地区有雷阵雨天气",
         * "date":"2020-07-02 09:42","category":"头条","author_name":"中国新闻网","url":"https://mini.eastday.com/mobile/200702094253435.html","thumbnail_pic_s":"https://00imgmini.eastday
         * .com/mobile/20200702/20200702094253_dafe19197093c2c1388b5d2039729624_1_mwpm_03200403.jpg"},{"uniquekey":"ac1f4c1d894b819c98dbc069b5a3c1a8",
         * "title":"尼康P1000和华为P40Pro+长焦对比：差距依然十分明显","date":"2020-07-02 09:39","category":"头条","author_name":"3C毒物","url":"https://mini.eastday.com/mobile/200702093949081.html",
         * "thumbnail_pic_s":"https://09imgmini.eastday.com/mobile/20200702/2020070209_ec99df7db82d4a58ac45caf3fc1cf1aa_3632_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini
         * .eastday.com/mobile/20200702/2020070209_305cdc8bd5cc47c8b22047ef42f7e2f4_9484_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday
         * .com/mobile/20200702/2020070209_73b3a8062f2245a0bdfeb2bc257db82f_8834_mwpm_03200403.jpg"},{"uniquekey":"f74417a9d00c175bf8e15370a8e50f9a",
         * "title":"盖亚任务\u2014\u2014绘制出银河系中心棒状恒星集合的地图","date":"2020-07-02 09:38","category":"头条","author_name":"天文在线","url":"https://mini.eastday.com/mobile/200702093837763.html",
         * "thumbnail_pic_s":"https://01imgmini.eastday.com/mobile/20200702/2020070209_9b9cd38052304ee08366c99b0d7fc63c_7612_cover_mwpm_03200403.jpg"},{"uniquekey
         * ":"cd24001293b4ffe65b6ab1dad821dd6b","title":"《局中人》《爱我就别想太多》遭网友狂吐槽","date":"2020-07-02 09:26","category":"头条","author_name":"光明网","url":"https://mini.eastday
         * .com/mobile/200702092613860.html","thumbnail_pic_s":"https://09imgmini.eastday.com/mobile/20200702/20200702092613_786f7006160f6653dfb30edcecfa7c8a_1_mwpm_03200403.jpg"},{
         * "uniquekey":"b3bb779304c8f98fa08f2cf53f312b27","title":"刺伤警员的香港男子企图连夜潜逃英国，警方接其亲友举报在机场将其抓获！","date":"2020-07-02 09:22","category":"头条","author_name":"环球网","url":"https://mini
         * .eastday.com/mobile/200702092220716.html","thumbnail_pic_s":"https://08imgmini.eastday.com/mobile/20200702/20200702092220_d6e9b5aebb0691ed0a3607b864c286a5_1_mwpm_03200403.jpg",
         * "thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20200702/20200702092220_6e521718c90eaa5b177f5c936eb47e5a_2_mwpm_03200403.jpg"},{"uniquekey
         * ":"9dfffdfb92a039be8002bed71bf9b497","title":"复赛最大罚单，姚明严厉整治CBA乱象，要为裁判树立起无上威严","date":"2020-07-02 09:21","category":"头条","author_name":"毛罗话体坛","url":"https://mini.eastday
         * .com/mobile/200702092100776.html","thumbnail_pic_s":"https://01imgmini.eastday.com/mobile/20200702/2020070209_5e234f0558624b0b98b85a16deb5b512_2699_mwpm_03200403.jpg",
         * "thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20200702/2020070209_ce070e7735024996a7f344f309331a5c_9437_mwpm_03200403.jpg"},{"uniquekey
         * ":"2d6d9618de78efe8b527c5cf85e8435e","title":"这早餐连吃7天也不腻！蓬松柔软香喷喷，无油无糖，健康100分","date":"2020-07-02 09:17","category":"头条","author_name":"美美家的小厨房TB","url":"https://mini.eastday
         * .com/mobile/200702091701904.html","thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/20200702091701_771b1d8ea4685fe20e8cc3b72ff99a07_5_mwpm_03200403.jpg",
         * "thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20200702/20200702091701_771b1d8ea4685fe20e8cc3b72ff99a07_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini
         * .eastday.com/mobile/20200702/20200702091701_771b1d8ea4685fe20e8cc3b72ff99a07_9_mwpm_03200403.jpg"},{"uniquekey":"aff9739ee7a307d737255f0afd1d894f","title":"北京新增1例确诊在丰台",
         * "date":"2020-07-02 09:13","category":"头条","author_name":"光明网","url":"https://mini.eastday.com/mobile/200702091338290.html","thumbnail_pic_s":"https://01imgmini.eastday
         * .com/mobile/20200702/20200702091338_85b868791adf3c15303dce6c117eb4bc_1_mwpm_03200403.jpg"},{"uniquekey":"5555f14705a283680728bf372a9a1aa5","title":"重走红色新闻路丨红色南泥湾 陕北好江南",
         * "date":"2020-07-02 09:11","category":"头条","author_name":"陕西传媒网","url":"https://mini.eastday.com/mobile/200702091117378.html","thumbnail_pic_s":"https://01imgmini.eastday
         * .com/mobile/20200702/20200702091117_c9a836da73623f0e2c17d7e3c349ace9_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday
         * .com/mobile/20200702/20200702091117_c9a836da73623f0e2c17d7e3c349ace9_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday
         * .com/mobile/20200702/20200702091117_c9a836da73623f0e2c17d7e3c349ace9_2_mwpm_03200403.jpg"},{"uniquekey":"44902f9aa07d459ad0e3bb1ecb4dc8e5",
         * "title":"郭麒麟穿汉服忘记告诉杨幂，谁注意杨幂问的啥？让郭麒麟情何以堪","date":"2020-07-02 09:07","category":"头条","author_name":"喵吃娱资讯号","url":"https://mini.eastday.com/mobile/200702090713681.html",
         * "thumbnail_pic_s":"https://04imgmini.eastday.com/mobile/20200702/20200702090713_0c52c9abdebbc0d241ae91c227359cff_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini
         * .eastday.com/mobile/20200702/20200702090713_0c52c9abdebbc0d241ae91c227359cff_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday
         * .com/mobile/20200702/20200702090713_0c52c9abdebbc0d241ae91c227359cff_8_mwpm_03200403.jpg"},{"uniquekey":"150da6dda290007f0e848e2b723976a6","title":"继续做账？巴萨尤文酝酿再做交易 格列兹曼一换二",
         * "date":"2020-07-02 09:07","category":"头条","author_name":"足坛欧美汇","url":"https://mini.eastday.com/mobile/200702090711389.html","thumbnail_pic_s":"https://01imgmini.eastday
         * .com/mobile/20200702/20200702090711_94bf0608f9fa28e3d69b63b03e87054e_1_mwpm_03200403.jpg"},{"uniquekey":"905949d7bec79527ddd060a3ffef02d9","title":"想要优美的曲线？常练6式瑜伽，让你的身体更紧致",
         * "date":"2020-07-02 09:04","category":"头条","author_name":"瑜伽坊","url":"https://mini.eastday.com/mobile/200702090404593.html","thumbnail_pic_s":"https://03imgmini.eastday
         * .com/mobile/20200702/2020062713_25c038feb74142fdbbfc81b985db3bd0_3272_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday
         * .com/mobile/20200702/2020062713_1dcc52c1a316496d9cb5d11c874da578_7493_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
         * .com/mobile/20200702/2020062713_af798f3d27834f1391f44efc8eee8792_9143_cover_mwpm_03200403.jpg"},{"uniquekey":"d074f064fe89b127c55572c3537b1d06","title":"央八《小娘惹》开播，我却迷上了里面的美食！",
         * "date":"2020-07-02 09:03","category":"头条","author_name":"快资讯","url":"https://mini.eastday.com/mobile/200702090315100.html","thumbnail_pic_s":"https://02imgmini.eastday
         * .com/mobile/20200702/20200702090315_0a01b4f165341bb36461e81dfe552ad7_10_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
         * .com/mobile/20200702/20200702090315_0a01b4f165341bb36461e81dfe552ad7_14_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
         * .com/mobile/20200702/20200702090315_0a01b4f165341bb36461e81dfe552ad7_2_mwpm_03200403.jpg"},{"uniquekey":"51ad1a3deb51cb074bc3b048ca4a1ced",
         * "title":"《我的前半生》与黄磊最直接的关系就是堪称\u201c深夜食堂\u201d与\u201c麻烦家族\u201d的合体！","date":"2020-07-02 09:03","category":"头条","author_name":"快资讯","url":"https://mini.eastday
         * .com/mobile/200702090314869.html","thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/20200702090314_6e5767d55204f67d066c6bc78a683f14_3_mwpm_03200403.jpg",
         * "thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20200702/20200702090314_6e5767d55204f67d066c6bc78a683f14_14_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini
         * .eastday.com/mobile/20200702/20200702090314_6e5767d55204f67d066c6bc78a683f14_9_mwpm_03200403.jpg"},{"uniquekey":"8d03bf083bba74a6ec37f745cb74a8b3","title":"济南市总投资265665
         * .1万元项目！2020年山东济南市重大建设项目","date":"2020-07-02 09:02","category":"头条","author_name":"中项网","url":"https://mini.eastday.com/mobile/200702090258009.html",
         * "thumbnail_pic_s":"https://01imgmini.eastday.com/mobile/20200702/20200702090258_2650e561542d1a00d62cf97fde4a3e26_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini
         * .eastday.com/mobile/20200702/20200702090258_2650e561542d1a00d62cf97fde4a3e26_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday
         * .com/mobile/20200702/20200702090258_2650e561542d1a00d62cf97fde4a3e26_3_mwpm_03200403.jpg"},{"uniquekey":"703287c5769a97ba561cb797fee60316",
         * "title":"7月1日转会汇总：巴萨尤文谈格里兹曼交易，拜仁一日双签","date":"2020-07-02 09:01","category":"头条","author_name":"欧洲足球观察","url":"https://mini.eastday.com/mobile/200702090138887.html",
         * "thumbnail_pic_s":"https://06imgmini.eastday.com/mobile/20200702/20200702090138_1c0b94e51d9563f6a38caa73fdbb331a_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini
         * .eastday.com/mobile/20200702/20200702090138_1c0b94e51d9563f6a38caa73fdbb331a_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday
         * .com/mobile/20200702/20200702090138_1c0b94e51d9563f6a38caa73fdbb331a_4_mwpm_03200403.jpg"},{"uniquekey":"c2cee7d747e04ec41f39c35640f565f0","title":"事情正在起变化，这件事欧美真要翻脸了！",
         * "date":"2020-07-02 09:00","category":"头条","author_name":"解放网","url":"https://mini.eastday.com/mobile/200702090021526.html","thumbnail_pic_s":"https://02imgmini.eastday
         * .com/mobile/20200702/20200702090021_eac6bed5ef68524857cf3a062e4b4461_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday
         * .com/mobile/20200702/20200702090021_eac6bed5ef68524857cf3a062e4b4461_2_mwpm_03200403.jpg"},{"uniquekey":"ce3dc0b1d9325ab309db1d0f22347bb7",
         * "title":"小伙自带洗漱用品到派出所：我是不是被通缉了？民警一查：还真是","date":"2020-07-02 08:57","category":"头条","author_name":"北青网","url":"https://mini.eastday.com/mobile/200702085759752.html",
         * "thumbnail_pic_s":"https://04imgmini.eastday.com/mobile/20200702/20200702085759_5f206f26cd09038b1d080ed68c794027_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini
         * .eastday.com/mobile/20200702/20200702085759_5f206f26cd09038b1d080ed68c794027_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday
         * .com/mobile/20200702/20200702085759_5f206f26cd09038b1d080ed68c794027_1_mwpm_03200403.jpg"},{"uniquekey":"d000acf52e93d3320061d0f7c0f13148",
         * "title":"重磅对决！辽宁VS广厦：郭艾伦PK孙铭徽，辽宁报一箭之仇冲三连胜","date":"2020-07-02 08:55","category":"头条","author_name":"看球讲道李","url":"https://mini.eastday.com/mobile/200702085518334.html",
         * "thumbnail_pic_s":"https://02imgmini.eastday.com/mobile/20200702/20200702085518_68e7e9cde832f540ef628d67608e185f_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini
         * .eastday.com/mobile/20200702/20200702085518_68e7e9cde832f540ef628d67608e185f_5_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday
         * .com/mobile/20200702/20200702085518_68e7e9cde832f540ef628d67608e185f_4_mwpm_03200403.jpg"},{"uniquekey":"f2b424f58ee1aa10ae6cb346adca4d9a","title":"阔太方媛为好友庆生 与吴佩慈同框各具美态",
         * "date":"2020-07-02 08:54","category":"头条","author_name":"中国娱乐网","url":"https://mini.eastday.com/mobile/200702085420413.html","thumbnail_pic_s":"https://06imgmini.eastday
         * .com/mobile/20200702/20200702085420_ad326ae9edf54bd787bb7cbdcab847cd_1_mwpm_03200403.jpg"},{"uniquekey":"050b57bbfc75923c181ab92873324cde","title":"民调：美国人对疫情的焦虑情绪达到最高水平",
         * "date":"2020-07-02 08:53","category":"头条","author_name":"央视网","url":"https://mini.eastday.com/mobile/200702085322557.html","thumbnail_pic_s":"https://01imgmini.eastday
         * .com/mobile/20200702/20200702085322_5e59b8c29fea7d1aa0e0c301f0f1dd82_1_mwpm_03200403.jpg"},{"uniquekey":"0509291c9774d824658c64e7aa68fdf7","title":"《北京市医院安全秩序管理规定》7月1日施行 受暴力威胁时
         * 医生可暂停诊疗","date":"2020-07-02 08:53","category":"头条","author_name":"央视网","url":"https://mini.eastday.com/mobile/200702085322494.html","thumbnail_pic_s":"https://01imgmini.eastday
         * .com/mobile/20200702/20200702085322_54e5c23d4f0cc308c5f0bef1cf57a436_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday
         * .com/mobile/20200702/20200702085322_54e5c23d4f0cc308c5f0bef1cf57a436_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday
         * .com/mobile/20200702/20200702085322_54e5c23d4f0cc308c5f0bef1cf57a436_1_mwpm_03200403.jpg"},{"uniquekey":"de8b85c47458801d3d81dfec1f232861",
         * "title":"《向往》他不满黄磊差别对待？连催10次鹿晗去干活，原因却很暖心","date":"2020-07-02 08:43","category":"头条","author_name":"快资讯","url":"https://mini.eastday.com/mobile/200702084303521.html",
         * "thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/20200702084303_56458f6222f1cbbc7123b1c61edcdb68_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini
         * .eastday.com/mobile/20200702/20200702084303_56458f6222f1cbbc7123b1c61edcdb68_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
         * .com/mobile/20200702/20200702084303_56458f6222f1cbbc7123b1c61edcdb68_13_mwpm_03200403.jpg"},{"uniquekey":"30038ffc7b83e50919015afb3d2392eb",
         * "title":"牛肉怎么吃？大叔教你牛肉炖萝卜，鲜香味美，简单易做，超好吃！","date":"2020-07-02 08:27","category":"头条","author_name":"大叔下厨房","url":"https://mini.eastday.com/mobile/200702082737125.html",
         * "thumbnail_pic_s":"https://03imgmini.eastday.com/mobile/20200702/2020070208_138d9474bbe44e7cbd04aa6880872695_2343_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini
         * .eastday.com/mobile/20200702/2020070208_2abe8940d14549268bb644902a448800_7375_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday
         * .com/mobile/20200702/2020070208_70529db69d8d47d584eaf7f095eb63aa_4709_mwpm_03200403.jpg"},{"uniquekey":"d8d00473de910451b0a8705cba4eff6e","title":"开国元勋后代向香山革命纪念馆捐赠革命文物史料",
         * "date":"2020-07-02 08:25","category":"头条","author_name":"人民网本地站","url":"https://mini.eastday.com/mobile/200702082544702.html","thumbnail_pic_s":"https://07imgmini.eastday
         * .com/mobile/20200702/20200702082544_c386e7a7c012be54308b9f4eaae244e7_1_mwpm_03200403.jpg"}]
         */

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
}
