package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Wm;

public class WmObjectGeneratorTest extends TestCase {
    private String json = "{\"language\":\"ru\",\"found\":\"130588\",\"places\":[{\"id\":5238507,\"language_id\":1," +
            "\"language_iso\":\"ru\",\"urlhtml\":\"<a class=\\\"wikimapia-link\\\" href=\\\"http:\\/\\/wikimapia.org" +
            "\\/5238507\\/ru\\/%D0%9F%D0%B5%D1%82%D1%80%D0%B0-%D0%B3%D0%BE%D1%80%D0%BE%D0%B4-%D0%B2-%D1%81%D0%BA%D0" +
            "%B0%D0%BB%D0%B0%D1%85\\\">\\u041f\\u0435\\u0442\\u0440\\u0430: \\u0433\\u043e\\u0440\\u043e\\u0434 " +
            "\\u0432 \\u0441\\u043a\\u0430\\u043b\\u0430\\u0445<\\/a>\",\"title\":\"\\u041f\\u0435\\u0442\\u0440" +
            "\\u0430: \\u0433\\u043e\\u0440\\u043e\\u0434 \\u0432 \\u0441\\u043a\\u0430\\u043b\\u0430\\u0445\"," +
            "\"tags\":[{\"id\":88,\"title\":\"\\u0433\\u043e\\u0440\\u043e\\u0434\"},{\"id\":261,\"title\":\"" +
            "\\u043f\\u0430\\u043c\\u044f\\u0442\\u043d\\u0438\\u043a, \\u043c\\u043e\\u043d\\u0443\\u043c\\u0435" +
            "\\u043d\\u0442\"},{\"id\":2229,\"title\":\"\\u0430\\u0440\\u0445\\u0435\\u043e\\u043b\\u043e\\u0433" +
            "\\u0438\\u0447\\u0435\\u0441\\u043a\\u0438\\u0439 \\u043f\\u0430\\u043c\\u044f\\u0442\\u043d\\u0438" +
            "\\u043a\\/\\u0440\\u0430\\u0441\\u043a\\u043e\\u043f\\u043a\\u0438\"}],\"distance\":86619,\"description" +
            "\":\"\\u041f\\u0435\\u0442\\u0440\\u0430 \\u044d\\u0442\\u043e \\u0433\\u043e\\u0440\\u043e\\u0434, " +
            "\\u0432\\u044b\\u0440\\u0435\\u0437\\u0430\\u043d\\u043d\\u044b\\u0439 \\u0432 \\u0441\\u043a\\u0430" +
            "\\u043b\\u0435 \\u043d\\u0430\\u0431\\u0430\\u0442\\u0435\\u044f\\u043c\\u0438 \\u0432 312 \\u0433 " +
            "\\u0434\\u043e \\u043d.\\u044d. \\u041f\\u043e\\u043c\\u0438\\u043c\\u043e \\u043d\\u0435\\u0432\\u0435" +
            "\\u0440\\u043e\\u044f\\u0442\\u043d\\u043e \\u0437\\u0430\\u0442\\u0435\\u0439\\u043b\\u0438\\u0432" +
            "\\u044b\\u0445 \\u043e\\u0440\\u043d\\u0430\\u043c\\u0435\\u043d\\u0442\\u043e\\u0432, \\u043d\\u0430" +
            "\\u0431\\u0430\\u0442\\u0435\\u0438 \\u0441\\u043e\\u0437\\u0434\\u0430\\u043b\\u0438 \\u0441\\u0438" +
            "\\u0441\\u0442\\u0435\\u043c\\u0443 \\u0446\\u0438\\u0441\\u0442\\u0435\\u0440\\u043d, \\u0442\\u0430" +
            "\\u043a\\u0436\\u0435 \\u0432\\u044b\\u0440\\u0435\\u0437\\u0430\\u043d\\u043d\\u0443\\u044e \\u0432 " +
            "\\u0441\\u043a\\u0430\\u043b\\u0435, \\u043a\\u043e\\u0442\\u043e\\u0440\\u0430\\u044f \\u043f\\u043e" +
            "\\u0437\\u0432\\u043e\\u043b\\u0438\\u043b\\u0430 \\u0438\\u043c \\u043e\\u0431\\u043e\\u0441\\u043d" +
            "\\u043e\\u0432\\u0430\\u0442\\u044c\\u0441\\u044f \\u0432 \\u043d\\u0430\\u0441\\u0442\\u043e\\u043b" +
            "\\u044c\\u043a\\u043e \\u0437\\u0430\\u0441\\u0443\\u0448\\u043b\\u0438\\u0432\\u043e\\u043c \\u043a" +
            "\\u043b\\u0438\\u043c\\u0430\\u0442\\u0435. \\u0421\\u0430\\u043c\\u044b\\u043c \\u0441\\u043b\\u043e" +
            "\\u0436\\u043d\\u044b\\u043c \\u0440\\u0435\\u0437\\u043d\\u044b\\u043c \\u0437\\u0434\\u0430\\u043d" +
            "\\u0438\\u0435\\u043c \\u044f\\u0432\\u043b\\u044f\\u0435\\u0442\\u0441\\u044f \\u042d\\u043b\\u044c-" +
            "\\u0425\\u0430\\u0437\\u043d\\u0435 (Al Khazneh) - \\u0445\\u0440\\u0430\\u043c, \\u043a\\u043e\\u0442" +
            "\\u043e\\u0440\\u044b\\u0439 \\u0431\\u044b\\u043b \\u044f\\u0432\\u043d\\u043e \\u043f\\u043e\\u0441" +
            "\\u0442\\u0440\\u043e\\u0435\\u043d \\u043f\\u043e\\u0434 \\u0432\\u043b\\u0438\\u044f\\u043d\\u0438" +
            "\\u0435\\u043c \\u0434\\u0440\\u0435\\u0432\\u043d\\u0435\\u0433\\u0440\\u0435\\u0447\\u0435\\u0441" +
            "\\u043a\\u043e\\u0439 \\u0430\\u0440\\u0445\\u0438\\u0442\\u0435\\u043a\\u0442\\u0443\\u0440\\u044b.\"," +
            "\"wikipedia\":\"http:\\/\\/ru.wikipedia.org\\/wiki\\/\\u041f\\u0435\\u0442\\u0440\\u0430\",\"is_building" +
            "\":false,\"is_region\":false,\"is_deleted\":false,\"parent_id\":\"0\",\"polygon\":[{\"x\":35.4489085," +
            "\"y\":30.3247569},{\"x\":35.4489433,\"y\":30.3242453},{\"x\":35.4491579,\"y\":30.3242476}," +
            "{\"x\":35.4491096,\"y\":30.3247523}],\"edit_info\":{\"user_id\":861194,\"user_name\":\"Malay_EA\"," +
            "\"date\":1392284343,\"is_unbindable\":null,\"deletion_state\":false,\"is_in_deletion_queue\":false," +
            "\"is_in_undeletion_queue\":false},\"is_protected\":false,\"photos\":[{\"id\":508676,\"size\":109404," +
            "\"status\":0,\"object_id\":5238507,\"user_id\":260508,\"user_name\":\"qqnamuniu\",\"time\":0," +
            "\"time_str\":\"9 years ago\",\"last_user_id\":260508,\"last_user_name\":\"qqnamuniu\",\"960_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/76_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/76_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/76_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/76_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/76_75@2x.jpg\"},{\"id\":508677,\"size\":111348," +
            "\"status\":0,\"object_id\":5238507,\"user_id\":260508,\"user_name\":\"qqnamuniu\",\"time\":0," +
            "\"time_str\":\"9 years ago\",\"last_user_id\":260508,\"last_user_name\":\"qqnamuniu\",\"960_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/77_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/77_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/77_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/77_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/50\\/86\\/77_75@2x.jpg\"},{\"id\":226283,\"size\":60227," +
            "\"status\":0,\"object_id\":5238507,\"user_id\":137551,\"user_name\":\"rocomua\",\"time\":0," +
            "\"time_str\":\"9 years ago\",\"last_user_id\":137551,\"last_user_name\":\"rocomua\",\"960_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/22\\/62\\/83_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/22\\/62\\/83_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/22\\/62\\/83_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/22\\/62\\/83_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/22\\/62\\/83_75@2x.jpg\"},{\"id\":4570746,\"size\":2348271," +
            "\"status\":0,\"object_id\":5238507,\"user_id\":1226129,\"user_name\":\"giancanu\",\"time\":1419782074," +
            "\"time_str\":\"29 days ago\",\"last_user_id\":1226129,\"last_user_name\":\"giancanu\",\"full_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/46_full.jpg\",\"960_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/46_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/46_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/46_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/46_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/46_75@2x.jpg\"},{\"id\":4570756,\"size\":2424579," +
            "\"status\":0,\"object_id\":5238507,\"user_id\":1226129,\"user_name\":\"giancanu\",\"time\":1419782226," +
            "\"time_str\":\"29 days ago\",\"last_user_id\":1226129,\"last_user_name\":\"giancanu\",\"full_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/56_full.jpg\",\"960_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/56_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/56_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/56_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/56_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/56_75@2x.jpg\"},{\"id\":4570770,\"size\":2310257," +
            "\"status\":0,\"object_id\":5238507,\"user_id\":1226129,\"user_name\":\"giancanu\",\"time\":1419782367," +
            "\"time_str\":\"29 days ago\",\"last_user_id\":1226129,\"last_user_name\":\"giancanu\",\"full_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/70_full.jpg\",\"960_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/70_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/70_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/70_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/70_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/04\\/57\\/07\\/70_75@2x.jpg\"}],\"comments\":[{\"place_id\":5238507," +
            "\"num\":1,\"lang_id\":1,\"user_id\":407146,\"user_ip\":-1008296553,\"user_photo\":\"\\/p\\/u\\/00\\/00" +
            "\\/40\\/71\\/46_50.jpg\",\"name\":\"hoche\",\"message\":\"100 \\u0412\\u0435\\u043b\\u0438\\u043a\\u0438" +
            "\\u0445 \\u0434\\u0432\\u043e\\u0440\\u0446\\u043e\\u0432 - 100 Great palaces - http:\\/" +
            "\\/hoche83.livejournal.com\\/9499.html\",\"good\":0,\"bad\":0,\"block\":false,\"date\":1253875631," +
            "\"moder_uid\":0,\"moder_name\":null,\"is_deleted\":false,\"replies\":[]}],\"location\":" +
            "{\"lon\":35.4490332,\"lat\":30.3245011,\"north\":30.3247569,\"south\":30.3242453,\"east\":35.4491579," +
            "\"west\":35.4489085,\"country\":\"Jordan\",\"state\":\"Maan\",\"place\":\"Wadi Musa\"," +
            "\"country_adm_id\":117834,\"gadm\":[{\"id\":\"1000\",\"country\":\"0\",\"level\":\"0\"," +
            "\"is_last_level\":\"0\",\"name\":\"World\",\"iso\":null,\"type\":null,\"translation\":\"\\u041c\\u0438" +
            "\\u0440 \"},{\"id\":\"109\",\"country\":\"109\",\"level\":\"0\",\"is_last_level\":\"0\",\"name\":" +
            "\"Jordan\",\"iso\":null,\"type\":null,\"translation\":\"\\u0418\\u043e\\u0440\\u0434\\u0430\\u043d" +
            "\\u0438\\u044f\"},{\"id\":\"117828\",\"country\":\"109\",\"level\":\"1\",\"is_last_level\":\"0\"," +
            "\"name\":\"Ma`an\",\"iso\":null,\"type\":null,\"translation\":null}],\"city_id\":\"0\",\"city\":false," +
            "\"zoom\":19},\"availableLanguages\":{\"en\":{\"lang_id\":0,\"lang_name\":\"English\",\"object_local_slug" +
            "\":\"Aneisho-Tomb\",\"native_name\":\"English\",\"object_url\":\"http:\\/\\/wikimapia.org\\/" +
            "\\/Aneisho-Tomb\"},\"ru\":{\"lang_id\":1,\"lang_name\":\"Russian\",\"object_local_slug\":" +
            "\"%D0%9F%D0%B5%D1%82%D1%80%D0%B0-%D0%B3%D0%BE%D1%80%D0%BE%D0%B4-%D0%B2-%D1%81%D0%BA%D0%B0%D0%BB%D0%B0" +
            "%D1%85\",\"native_name\":\"\\u0420\\u0443\\u0441\\u0441\\u043a\\u0438\\u0439\",\"object_url\":\"http:\\/" +
            "\\/wikimapia.org\\/\\/ru\\/%D0%9F%D0%B5%D1%82%D1%80%D0%B0-%D0%B3%D0%BE%D1%80%D0%BE%D0%B4-%D0%B2-%D1%81" +
            "%D0%BA%D0%B0%D0%BB%D0%B0%D1%85\"}}},{\"id\":1672163,\"language_id\":1,\"language_iso\":\"ru\"," +
            "\"urlhtml\":\"<a class=\\\"wikimapia-link\\\" href=\\\"http:\\/\\/wikimapia.org\\/1672163\\/ru" +
            "\\/%D0%93%D0%BE%D1%80%D0%BE%D0%B4-%D0%94%D0%B0%D0%B2%D0%B8%D0%B4%D0%B0\\\">\\u0413\\u043e\\u0440\\u043e" +
            "\\u0434 \\u0414\\u0430\\u0432\\u0438\\u0434\\u0430<\\/a>\",\"title\":\"\\u0413\\u043e\\u0440\\u043e" +
            "\\u0434 \\u0414\\u0430\\u0432\\u0438\\u0434\\u0430\",\"tags\":[{\"id\":44605,\"title\":\"\\u0434\\u043e" +
            "\\u0441\\u0442\\u043e\\u043f\\u0440\\u0438\\u043c\\u0435\\u0447\\u0430\\u0442\\u0435\\u043b\\u044c" +
            "\\u043d\\u043e\\u0441\\u0442\\u044c\"},{\"id\":44876,\"title\":\"\\u0438\\u0441\\u0442\\u043e\\u0440" +
            "\\u0438\\u0447\\u0435\\u0441\\u043a\\u0438\\u0439 \\u0446\\u0435\\u043d\\u0442\\u0440 \\u0433\\u043e" +
            "\\u0440\\u043e\\u0434\\u0430\"}],\"distance\":88845,\"wikipedia\":\"http:\\/\\/ru.wikipedia.org\\/wiki" +
            "\\/\\u0413\\u043e\\u0440\\u043e\\u0434_\\u0414\\u0430\\u0432\\u0438\\u0434\\u0430\",\"is_building" +
            "\":false,\"is_region\":false,\"is_deleted\":false,\"parent_id\":\"0\",\"polygon\":[{\"x\":35.2343939," +
            "\"y\":31.7709692},{\"x\":35.2346079,\"y\":31.7719542},{\"x\":35.2347909,\"y\":31.7726562}," +
            "{\"x\":35.2355839,\"y\":31.7740972},{\"x\":35.2357879,\"y\":31.7747912},{\"x\":35.2365179,\"y\":" +
            "31.7748452},{\"x\":35.2368075,\"y\":31.7748452},{\"x\":35.2366412,\"y\":31.7743942},{\"x\":35.2366571," +
            "\"y\":31.7739793},{\"x\":35.2370652,\"y\":31.7734777},{\"x\":35.2368829,\"y\":31.7723642},{\"x\":" +
            "35.2368289,\"y\":31.7721642},{\"x\":35.2366039,\"y\":31.7717172},{\"x\":35.2360459,\"y\":31.7707962}," +
            "{\"x\":35.2351549,\"y\":31.7698382},{\"x\":35.2349409,\"y\":31.7699752},{\"x\":35.2346729,\"y\":" +
            "31.7702302}],\"edit_info\":{\"user_id\":1260117,\"user_name\":\"bubnilka\",\"date\":1357488509," +
            "\"is_unbindable\":null,\"deletion_state\":false,\"is_in_deletion_queue\":false,\"is_in_undeletion_queue" +
            "\":false},\"is_protected\":false,\"photos\":[{\"id\":297387,\"size\":134962,\"status\":0,\"object_id" +
            "\":1672163,\"user_id\":0,\"user_name\":\"Guest\",\"time\":0,\"time_str\":\"9 years ago\",\"last_user_id" +
            "\":0,\"last_user_name\":\"Guest\",\"user_ip\":\"0\",\"960_url\":\"http:\\/\\/photos.wikimapia.org\\/p" +
            "\\/00\\/00\\/29\\/73\\/87_960.jpg\",\"1280_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29" +
            "\\/73\\/87_1280.jpg\",\"big_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73" +
            "\\/87_big.jpg\",\"thumbnail_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73" +
            "\\/87_75.jpg\",\"thumbnailRetina_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73" +
            "\\/87_75@2x.jpg\"},{\"id\":297389,\"size\":118828,\"status\":0,\"object_id\":1672163,\"user_id\":0," +
            "\"user_name\":\"Guest\",\"time\":0,\"time_str\":\"9 years ago\",\"last_user_id\":0,\"last_user_name\":" +
            "\"Guest\",\"user_ip\":\"0\",\"960_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73" +
            "\\/89_960.jpg\",\"1280_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/89_1280.jpg\"," +
            "\"big_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/89_big.jpg\",\"thumbnail_url\":" +
            "\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/89_75.jpg\",\"thumbnailRetina_url\":\"http:" +
            "\\/\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/89_75@2x.jpg\"},{\"id\":297391,\"size\":104047," +
            "\"status\":0,\"object_id\":1672163,\"user_id\":0,\"user_name\":\"Guest\",\"time\":0,\"time_str\":" +
            "\"9 years ago\",\"last_user_id\":0,\"last_user_name\":\"Guest\",\"user_ip\":\"0\",\"960_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/91_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/91_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/91_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/91_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/91_75@2x.jpg\"},{\"id\":297394,\"size\":103080," +
            "\"status\":0,\"object_id\":1672163,\"user_id\":0,\"user_name\":\"Guest\",\"time\":0,\"time_str\":" +
            "\"9 years ago\",\"last_user_id\":0,\"last_user_name\":\"Guest\",\"user_ip\":\"0\",\"960_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/94_960.jpg\",\"1280_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/94_1280.jpg\",\"big_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/94_big.jpg\",\"thumbnail_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/94_75.jpg\",\"thumbnailRetina_url\":\"http:\\/" +
            "\\/photos.wikimapia.org\\/p\\/00\\/00\\/29\\/73\\/94_75@2x.jpg\"}],\"comments\":[],\"location\":" +
            "{\"lon\":35.2357295,\"lat\":31.7723417,\"north\":31.7748452,\"south\":31.7698382,\"east\":35.2370652," +
            "\"west\":35.2343939,\"country\":\"Israel\",\"state\":\"Jerusalem\",\"place\":\"Jerusalem\"," +
            "\"country_adm_id\":143198,\"gadm\":[{\"id\":\"1000\",\"country\":\"0\",\"level\":\"0\",\"is_last_level\"" +
            ":\"0\",\"name\":\"World\",\"iso\":null,\"type\":null,\"translation\":\"\\u041c\\u0438\\u0440 \"}," +
            "{\"id\":\"168\",\"country\":\"168\",\"level\":\"0\",\"is_last_level\":\"0\",\"name\":\"Palestina\"," +
            "\"iso\":null,\"type\":null,\"translation\":null},{\"id\":\"143189\",\"country\":\"168\",\"level\":\"1\"," +
            "\"is_last_level\":\"0\",\"name\":\"West Bank\",\"iso\":null,\"type\":null,\"translation\":\"\\u0417" +
            "\\u0430\\u043f\\u0430\\u0434\\u043d\\u044b\\u0439 \\u0431\\u0435\\u0440\\u0435\\u0433 \\u0440\\u0435" +
            "\\u043a\\u0438 \\u0418\\u043e\\u0440\\u0434\\u0430\\u043d\"}],\"city_id\":\"8452050\",\"city\":\"\\u0418" +
            "\\u0435\\u0440\\u0443\\u0441\\u0430\\u043b\\u0438\\u043c\",\"zoom\":16},\"availableLanguages\":{\"be\":{" +
            "\"lang_id\":23,\"lang_name\":\"Belarusian\",\"object_local_slug\":\"%D0%93%D0%BE%D1%80%D0%B0%D0%B4-%D0" +
            "%94%D0%B0%D0%B2%D1%96%D0%B4%D0%B0\",\"native_name\":\"\\u0411\\u0435\\u043b\\u0430\\u0440\\u0443\\u0441" +
            "\\u043a\\u0430\\u044f\",\"object_url\":\"http:\\/\\/wikimapia.org\\/\\/be\\/%D0%93%D0%BE%D1%80%D0%B0%D0" +
            "%B4-%D0%94%D0%B0%D0%B2%D1%96%D0%B4%D0%B0\"},\"en\":{\"lang_id\":0,\"lang_name\":\"English\"," +
            "\"object_local_slug\":\"City-of-David\",\"native_name\":\"English\",\"object_url\":\"http:\\/" +
            "\\/wikimapia.org\\/\\/City-of-David\"},\"es\":{\"lang_id\":3,\"lang_name\":\"Spanish\"," +
            "\"object_local_slug\":\"Jerusal%C3%A9n-Ciudad-de-David\",\"native_name\":\"Espa\\u00f1ol\",\"object_url" +
            "\":\"http:\\/\\/wikimapia.org\\/\\/es\\/Jerusal%C3%A9n-Ciudad-de-David\"},\"fr\":{\"lang_id\":2," +
            "\"lang_name\":\"French\",\"object_local_slug\":\"Cit%C3%A9-de-David\",\"native_name\":\"Fran\\u00e7ais" +
            "\",\"object_url\":\"http:\\/\\/wikimapia.org\\/\\/fr\\/Cit%C3%A9-de-David\"},\"he\":{\"lang_id\":18," +
            "\"lang_name\":\"Hebrew\",\"object_local_slug\":\"%D7%A2%D7%99%D7%A8-%D7%93%D7%95%D7%93\",\"native_name\"" +
            ":\"\\u05e2\\u05d1\\u05e8\\u05d9\\u05ea\",\"object_url\":\"http:\\/\\/wikimapia.org\\/\\/he\\/%D7%A2%D7" +
            "%99%D7%A8-%D7%93%D7%95%D7%93\"},\"ru\":{\"lang_id\":1,\"lang_name\":\"Russian\",\"object_local_slug\":" +
            "\"%D0%93%D0%BE%D1%80%D0%BE%D0%B4-%D0%94%D0%B0%D0%B2%D0%B8%D0%B4%D0%B0\",\"native_name\":\"\\u0420\\u0443" +
            "\\u0441\\u0441\\u043a\\u0438\\u0439\",\"object_url\":\"http:\\/\\/wikimapia.org\\/\\/ru\\/%D0%93%D0%BE" +
            "%D1%80%D0%BE%D0%B4-%D0%94%D0%B0%D0%B2%D0%B8%D0%B4%D0%B0\"},\"uk\":{\"lang_id\":37,\"lang_name\":" +
            "\"Ukrainian\",\"object_local_slug\":\"%D0%9C%D1%96%D1%81%D1%82%D0%BE-%D0%94%D0%B0%D0%B2%D1%96%D0%B4" +
            "%D0%B0\",\"native_name\":\"\\u0423\\u043a\\u0440\\u0430\\u0457\\u043d\\u0441\\u044c\\u043a\\u0430\"," +
            "\"object_url\":\"http:\\/\\/wikimapia.org\\/\\/uk\\/%D0%9C%D1%96%D1%81%D1%82%D0%BE-%D0%94%D0%B0%D0%B2" +
            "%D1%96%D0%B4%D0%B0\"}}},{\"id\":23807916,\"language_id\":1,\"language_iso\":\"ru\",\"urlhtml\":" +
            "\"<a class=\\\"wikimapia-link\\\" href=\\\"http:\\/\\/wikimapia.org\\/23807916\\/ru\\/%D0%93%D0%BE" +
            "%D1%80%D0%BE%D0%B4-Haql-%D0%A1%D0%B0%D1%83%D0%B4%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F-%D0%90%D1%80%D0%B0" +
            "%D0%B2%D0%B8%D1%8F\\\">\\u0413\\u043e\\u0440\\u043e\\u0434 Haql (\\u0421\\u0430\\u0443\\u0434\\u043e" +
            "\\u0432\\u0441\\u043a\\u0430\\u044f \\u0410\\u0440\\u0430\\u0432\\u0438\\u044f)<\\/a>\",\"title\":\"" +
            "\\u0413\\u043e\\u0440\\u043e\\u0434 Haql (\\u0421\\u0430\\u0443\\u0434\\u043e\\u0432\\u0441\\u043a" +
            "\\u0430\\u044f \\u0410\\u0440\\u0430\\u0432\\u0438\\u044f)\",\"tags\":[{\"id\":88,\"title\":\"\\u0433" +
            "\\u043e\\u0440\\u043e\\u0434\"}],\"distance\":191016,\"description\":\"\\u043d\\u0435 \\u0434\\u0430" +
            "\\u043b\\u0435\\u043a\\u043e \\u043e\\u0442 \\u0433\\u0440\\u0430\\u043d\\u0438\\u0446\\u044b \\u0441 " +
            "\\u0418\\u043e\\u0440\\u0434\\u0430\\u043d\\u0438\\u0435\\u0439\",\"is_building\":false,\"is_region" +
            "\":false,\"is_deleted\":false,\"parent_id\":\"0\",\"polygon\":[{\"x\":34.9401148,\"y\":29.2752581}," +
            "{\"x\":34.9558219,\"y\":29.2784025},{\"x\":34.9587401,\"y\":29.2869369},{\"x\":34.9473246,\"y\":" +
            "29.2940484},{\"x\":34.9347933,\"y\":29.2912787}],\"edit_info\":{\"user_id\":1561305,\"user_name\":" +
            "\"Ivan_E\",\"date\":1333600120,\"is_unbindable\":null,\"deletion_state\":false,\"is_in_deletion_queue" +
            "\":false,\"is_in_undeletion_queue\":false},\"is_protected\":false,\"photos\":[{\"id\":2375782,\"size\":" +
            "3512290,\"status\":0,\"object_id\":23807916,\"user_id\":1561305,\"user_name\":\"Ivan_E\",\"time\":" +
            "1333600119,\"time_str\":\"3 years ago\",\"last_user_id\":1561305,\"last_user_name\":\"Ivan_E\",\"" +
            "full_url\":\"http:\\/\\/photos.wikimapia.org\\/p\\/00\\/02\\/37\\/57\\/82_full.jpeg\",\"960_url\":\"" +
            "http:\\/\\/photos.wikimapia.org\\/p\\/00\\/02\\/37\\/57\\/82_960.jpg\",\"1280_url\":\"http:\\/\\/" +
            "photos.wikimapia.org\\/p\\/00\\/02\\/37\\/57\\/82_1280.jpg\",\"big_url\":\"http:\\/\\/" +
            "photos.wikimapia.org\\/p\\/00\\/02\\/37\\/57\\/82_big.jpg\",\"thumbnail_url\":\"http:\\/\\/" +
            "photos.wikimapia.org\\/p\\/00\\/02\\/37\\/57\\/82_75.jpg\",\"thumbnailRetina_url\":\"http:\\/\\/" +
            "photos.wikimapia.org\\/p\\/00\\/02\\/37\\/57\\/82_75@2x.jpg\"}],\"comments\":[],\"location\":{\"lon\":" +
            "34.9467667,\"lat\":29.2846532,\"north\":29.2940484,\"south\":29.2752581,\"east\":34.9587401,\"west\":" +
            "34.9347933,\"country\":\"Saudi Arabia\",\"state\":\"Tabuk\",\"place\":\"Hhaql\",\"country_adm_id\":0,\"" +
            "city_id\":\"0\",\"city\":false,\"zoom\":13},\"availableLanguages\":{\"en\":{\"lang_id\":0,\"lang_name\"" +
            ":\"English\",\"object_local_slug\":\"Haql\",\"native_name\":\"English\",\"object_url\":\"http:\\/\\/" +
            "wikimapia.org\\/\\/Haql\"},\"ru\":{\"lang_id\":1,\"lang_name\":\"Russian\",\"object_local_slug\":\"" +
            "%D0%93%D0%BE%D1%80%D0%BE%D0%B4-Haql-%D0%A1%D0%B0%D1%83%D0%B4%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F-%D0%90" +
            "%D1%80%D0%B0%D0%B2%D0%B8%D1%8F\",\"native_name\":\"\\u0420\\u0443\\u0441\\u0441\\u043a\\u0438\\u0439\"," +
            "\"object_url\":\"http:\\/\\/wikimapia.org\\/\\/ru\\/%D0%93%D0%BE%D1%80%D0%BE%D0%B4-Haql-%D0%A1%D0%B0%D1" +
            "%83%D0%B4%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F-%D0%90%D1%80%D0%B0%D0%B2%D0%B8%D1%8F\"}}}],\"page\":1," +
            "\"count\":50}";
    private String davidCity = "{\"bound\":[{\"x\":35.2343939,\"y\":31.7709692},{\"x\":35.2346079,\"y\":31.7719542}," +
            "{\"x\":35.2347909,\"y\":31.7726562},{\"x\":35.2355839,\"y\":31.7740972}," +
            "{\"x\":35.2357879,\"y\":31.7747912},{\"x\":35.2365179,\"y\":31.7748452}," +
            "{\"x\":35.2368075,\"y\":31.7748452},{\"x\":35.2366412,\"y\":31.7743942}," +
            "{\"x\":35.2366571,\"y\":31.7739793},{\"x\":35.2370652,\"y\":31.7734777}," +
            "{\"x\":35.2368829,\"y\":31.7723642},{\"x\":35.2368289,\"y\":31.7721642}," +
            "{\"x\":35.2366039,\"y\":31.7717172},{\"x\":35.2360459,\"y\":31.7707962}," +
            "{\"x\":35.2351549,\"y\":31.7698382},{\"x\":35.2349409,\"y\":31.7699752}," +
            "{\"x\":35.2346729,\"y\":31.7702302}]," +
            "\"center\":{\"x\":35.2357295,\"y\":31.7723417}," +
            "\"title\":\"Город Давида\"}";
    private WmObjectGenerator generator;

    @Before
    public void setUp() throws Exception {
        // given
        generator = new WmObjectGenerator(new Gson().fromJson(json, Wm.class));
    }

    @Test
    public void testGetPlace() throws Exception {
        // given
        generator.setPlaceTitle("Город Давида");
        // when then
        assertEquals(davidCity, new Gson().toJson(generator.getPlace()));
    }

    @Test
    public void testSetPlaceTitle() throws Exception {
        // given
        generator.setPlaceTitle("Несуществующее место");
        // when then
        assertNull(generator.getPlace());
    }
}