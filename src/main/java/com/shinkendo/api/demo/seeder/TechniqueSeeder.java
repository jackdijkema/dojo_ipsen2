package com.shinkendo.api.demo.seeder;


import com.shinkendo.api.demo.dao.CurriculumDAO;
import com.shinkendo.api.demo.dao.TechniqueDAO;
import com.shinkendo.api.demo.model.Curriculum;
import com.shinkendo.api.demo.model.Technique;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TechniqueSeeder {
    private final TechniqueDAO techniqueDAO;
    private final CurriculumDAO curriculumDAO;
    private final Logger logger;

    public void seed() {

        List<Curriculum> curriculumList = this.curriculumDAO.findAll();

        if (!techniqueDAO.findAll().isEmpty()) {
            logger.info("Techniques have already been seeded");
            return;
        }

        Map<Integer, Curriculum> curriculumMap = new HashMap<>();

        for (Curriculum curriculum : curriculumList) {
            curriculumMap.put(curriculum.getRank().getOrderId(), curriculum);
        }

        List<Technique> techniques = List.of(
                new Technique(" 真武道館", "Shinbudoukan", "Kyougi - Riron", "school of divine valor true spiritual/martial power", curriculumMap.get(1)),
                new Technique(" 道場", "Doujou", "Kyougi - Riron", "hall (temple) for martial arts training - place of enlightenment", curriculumMap.get(1)),
                new Technique(" 技", "Waza", "Kyougi - Riron", "technique", curriculumMap.get(1)),
                new Technique(" 稽古", "Keiko", "Kyougi - Riron", "practice training study", curriculumMap.get(1)),
                new Technique(" 武道 - 武術", "Budou - bujutsu", "Kyougi - Riron", null, curriculumMap.get(1)),
                new Technique(null, "Musubidachi", "Kamae", null, curriculumMap.get(1)),
                new Technique(null, "Shizentai", "Kamae", null, curriculumMap.get(1)),
                new Technique(null, "Jigotai", "Kamae", null, curriculumMap.get(1)),
                new Technique(null, "Hanmi migi/hidari", "Kamae", null, curriculumMap.get(1)),
                new Technique(null, "Tagai no kamae ai/gyaku hanmi", "Kamae", null, curriculumMap.get(1)),
                new Technique(null, "Hanmi handachi (kneeling)", "Kamae", null, curriculumMap.get(1)),
                new Technique(null, "Taiso ichi", "Taiso - ashisabaki - taisabaki", null, curriculumMap.get(1)),
                new Technique(null, "Taisabaki ichi", "Taiso - ashisabaki - taisabaki", null, curriculumMap.get(1)),
                new Technique(null, "Mae mawari ichi ni san", "Taiso - ashisabaki - taisabaki", null, curriculumMap.get(1)),
                new Technique(null, "Mae / ushiro mawari kaiten", "Taiso - ashisabaki - taisabaki", null, curriculumMap.get(1)),
                new Technique(null, "Ichinotachi migi/hidari", "Suburi", null, curriculumMap.get(1)),
                new Technique(null, "Ichinotachi kesa migi/hidari", "Suburi", null, curriculumMap.get(1)),
                new Technique(null, "Ichimonji suburi", "Suburi", null, curriculumMap.get(1)),
                new Technique(null, "Ichimonji suburi kesa", "Suburi", null, curriculumMap.get(1)),
                new Technique(null, "Shiho suburi", "Suburi", null, curriculumMap.get(1)),
                new Technique(null, "Shiho kesa", "Suburi", null, curriculumMap.get(1)),
                new Technique(null, "Gohou no kamae", "Tanrengata", null, curriculumMap.get(1)),
                new Technique(null, "Happogiri", "Tanrengata", null, curriculumMap.get(1)),
                new Technique(null, "Happo ura", "Tanrengata", null, curriculumMap.get(1)),
                new Technique(null, "Gohou battohou kihon ichi", "Battohou", null, curriculumMap.get(1)),
                new Technique(null, "Gohou battohou kihon ni", "Battohou", null, curriculumMap.get(1)),
                new Technique(null, "Tsukigaeshi", "Tachiuchi", null, curriculumMap.get(1)),
                new Technique(null, "Kirigaeshi 90°", "Tachiuchi", null, curriculumMap.get(1)),
                new Technique(" 見蕩稽古", "Mitorigeiko", "Kyougi - Riron", "training through observation (to watch in fascination)", curriculumMap.get(2)),
                new Technique(" 五輪五法五行", "Gorin gohou gogyo", "Kyougi - Riron", "five rings five methods / laws five lines", curriculumMap.get(2)),
                new Technique(" 打ち太刀 - 仕太刀", "Uchidachi – shidachi", "Kyougi - Riron", "initiator of a technique – receiver and retaliator of a technique", curriculumMap.get(2)),
                new Technique(" 打ち手 - 受け手", "Uchite – Ukete", "Kyougi - Riron", "striker – receiver", curriculumMap.get(2)),
                new Technique(" 武士道", "Bushido", "Kyougi - Riron", null, curriculumMap.get(2)),
                new Technique(" 侍", "Samurai", "Kyougi - Riron", null, curriculumMap.get(2)),
                new Technique(" 禅", "Zen", "Kyougi - Riron", null, curriculumMap.get(2)),
                new Technique(null, "Taiso ichi ni", "Ashisabaki-Taisabaki", null, curriculumMap.get(2)),
                new Technique(null, "Taisabaki ichi", "Ashisabaki-Taisabaki", null, curriculumMap.get(2)),
                new Technique(null, "Ushiro mawari ichi ni san", "Ashisabaki-Taisabaki", null, curriculumMap.get(2)),
                new Technique(null, "Mae / ushiro mawari kaiten tobi", "Ashisabaki-Taisabaki", null, curriculumMap.get(2)),
                new Technique(null, "Ichinotachi zengo", "Suburi", null, curriculumMap.get(2)),
                new Technique(null, "Ichinotachi zengo kesa", "Suburi", null, curriculumMap.get(2)),
                new Technique(null, "Ichinotachi zengo kesa makuri", "Suburi", null, curriculumMap.get(2)),
                new Technique(null, "Kesa-kaeshi", "Suburi", null, curriculumMap.get(2)),
                new Technique(null, "Kirikaeshi zengo", "Suburi", null, curriculumMap.get(2)),
                new Technique(null, "Kirikaeshi zengo kesa", "Suburi", null, curriculumMap.get(2)),
                new Technique(null, "Happo no kamae", "Tanrengata", null, curriculumMap.get(2)),
                new Technique(null, "Happogiri ura", "Tanrengata", null, curriculumMap.get(2)),
                new Technique(null, "Shoden no kata", "Tanrengata", null, curriculumMap.get(2)),
                new Technique(null, "Gohou battohou santen", "Battohou", null, curriculumMap.get(2)),
                new Technique(null, "Gohou battohou santen ura", "Battohou", null, curriculumMap.get(2)),
                new Technique(null, "Rokudo tachiuchi", "Tachiuchi", null, curriculumMap.get(2)),
                new Technique(null, "Uchikomi ichi", "Tachiuchi", null, curriculumMap.get(2)),
                new Technique(null, "Tsukikomi ichi", "Tachiuchi", null, curriculumMap.get(2)),
                new Technique(null, "Shizan kihon ichi", "Tameshigiri", null, curriculumMap.get(2)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(2)),
                new Technique(" 残心", "Zanshin", "Kyougi – Riron", "lingering awareness", curriculumMap.get(3)),
                new Technique(" 柔よく剛を制す", "Ju yoku go o seishi", "Kyougi – Riron", "soft controls hard", curriculumMap.get(3)),
                new Technique(" 剛よく柔を断つ", "Go yoku ju o tatsu", "Kyougi – Riron", "hard cuts soft", curriculumMap.get(3)),
                new Technique(" 引けば押せ", "Hike ba ose", "Kyougi – Riron", "when pulled push", curriculumMap.get(3)),
                new Technique(" 押せば回れ", "Ose ba maware", "Kyougi – Riron", "when pushed turn", curriculumMap.get(3)),
                new Technique(null, "Taisabaki ni", "Ashisabaki-Taisabaki", null, curriculumMap.get(3)),
                new Technique(null, "Fumikae – juushin no ido", "Ashisabaki-Taisabaki", null, curriculumMap.get(3)),
                new Technique(null, "Fumikae – uchikomi ichi ni san", "Ashisabaki-Taisabaki", null, curriculumMap.get(3)),
                new Technique(null, "Kihon dosa", "Suburi", null, curriculumMap.get(3)),
                new Technique(null, "Rokudo", "Suburi", null, curriculumMap.get(3)),
                new Technique(null, "Happogiri ura", "Tanrengata", null, curriculumMap.get(3)),
                new Technique(null, "Shoden no kata", "Tanrengata", null, curriculumMap.get(3)),
                new Technique(null, "Juni ni kamae", "Tanrengata", null, curriculumMap.get(3)),
                new Technique(null, "Kagamiishi usen", "Tanrengata", null, curriculumMap.get(3)),
                new Technique(null, "Kagamiishi sasen", "Tanrengata", null, curriculumMap.get(3)),
                new Technique(null, "Gohou battohou shiho", "Battohou", null, curriculumMap.get(3)),
                new Technique(null, "Ippondachi", "Tachiuchi", null, curriculumMap.get(3)),
                new Technique(null, "Santen dachi", "Tachiuchi", null, curriculumMap.get(3)),
                new Technique(null, "Battohou soutai", "Tachiuchi", null, curriculumMap.get(3)),
                new Technique(null, "Shizan shi", "Tameshigiri", null, curriculumMap.get(3)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(3)),
                new Technique(" 刀の礼法", "Katana no reiho", "Kyougi – Riron", "sword examination", curriculumMap.get(4)),
                new Technique(" 初心", "Sho shin", "Kyougi – Riron", "beginners mind (original intention initial resolution)", curriculumMap.get(4)),
                new Technique(" 力", "Chikara (riki)", "Kyougi – Riron", "power strength", curriculumMap.get(4)),
                new Technique(" 拍子", "Hyoushi", "Kyougi – Riron", "rhythm", curriculumMap.get(4)),
                new Technique(" 速度", "Sokudo", "Kyougi – Riron", "speed", curriculumMap.get(4)),
                new Technique(" 胆力", "Tanryoku", "Kyougi – Riron", "courage/nerve", curriculumMap.get(4)),
                new Technique(null, "Mae hineri ichi ni san", "Ashisabaki", null, curriculumMap.get(4)),
                new Technique(null, "Ushiro hineri ichi ni", "Ashisabaki", null, curriculumMap.get(4)),
                new Technique(null, "Makiuchi zengo", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Makiuchi sayu", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Makiuchi kaeshiuchi", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Makiuchi migi mawari", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Kaeshiuchi hidari mawari", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Kirikaeshi 1", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Kirikaeshi 2", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Kirikaeshi 3", "Suburi", null, curriculumMap.get(4)),
                new Technique(null, "Kagamiishi usen", "Tanrengata", null, curriculumMap.get(4)),
                new Technique(null, "Kagamiishi sasen", "Tanrengata", null, curriculumMap.get(4)),
                new Technique(null, "Chuden sei", "Tanrengata", null, curriculumMap.get(4)),
                new Technique(null, "Gohou battohou shiho ura", "Battohou", null, curriculumMap.get(4)),
                new Technique(null, "Gohou battohou makiuchi ichi/ni", "Battohou", null, curriculumMap.get(4)),
                new Technique(null, "Uchikomi ni", "Tachiuchi", null, curriculumMap.get(4)),
                new Technique(null, "Tsukikomi ni", "Tachiuchi", null, curriculumMap.get(4)),
                new Technique(null, "Uchitsuki ichi-ni", "Tachiuchi", null, curriculumMap.get(4)),
                new Technique(null, "Kirigaeshi 180° + 90°", "Tachiuchi", null, curriculumMap.get(4)),
                new Technique(null, "Battohou sanbondachi", "Tachiuchi", null, curriculumMap.get(4)),
                new Technique(null, "Makiuchi basic", "Tachiuchi", null, curriculumMap.get(4)),
                new Technique(null, "Shizan kihon san", "Tameshigiri", null, curriculumMap.get(4)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(4)),
                new Technique(" 刀の手入れ", "Katana no teire", "Kyougi – Riron", "sword cleaning care and maintenance", curriculumMap.get(5)),
                new Technique(" 真剣道九曜紋", "Shinkendo kuyomon", "Kyougi – Riron", null, curriculumMap.get(5)),
                new Technique(" 無心", "Mushin", "Kyougi – Riron", "no (empty) mind", curriculumMap.get(5)),
                new Technique(" 不動心", "Fudoushin", "Kyougi – Riron", "immovable mind", curriculumMap.get(5)),
                new Technique(" 見切り", "Mikiri", "Kyougi – Riron", "exactly evading an attack by the smallest amount possible", curriculumMap.get(5)),
                new Technique(" 目線", "Mesen", "Kyougi – Riron", "gaze (enzan no me -遠山 – distant mountain gaze)", curriculumMap.get(5)),
                new Technique(" 目附", "Metsuke", "Kyougi – Riron", "reference of view", curriculumMap.get(5)),
                new Technique(null, "Tsuriashi – tsugiashi – sashiashi", "Ashisabaki", null, curriculumMap.get(5)),
                new Technique(null, "a. Kagigata", "Ashisabaki", null, curriculumMap.get(5)),
                new Technique(null, "b. Jujigata", "Ashisabaki", null, curriculumMap.get(5)),
                new Technique(null, "c. Masugata", "Ashisabaki", null, curriculumMap.get(5)),
                new Technique(null, "d. Sotai tsuriashi – tsugiashi – sashiashi", "Ashisabaki", null, curriculumMap.get(5)),
                new Technique(null, "Shizan suburi 1", "Suburi", null, curriculumMap.get(5)),
                new Technique(null, "Shizan suburi 2", "Suburi", null, curriculumMap.get(5)),
                new Technique(null, "Nami ichi ni san", "Suburi", null, curriculumMap.get(5)),
                new Technique(null, "Namigaeshi ichi ni san", "Suburi", null, curriculumMap.get(5)),
                new Technique(null, "Chuden sei", "Tanrengata", null, curriculumMap.get(5)),
                new Technique(null, "Chuden do", "Tanrengata", null, curriculumMap.get(5)),
                new Technique(null, "Hangetsu ura", "Battohou", null, curriculumMap.get(5)),
                new Technique(null, "Mangetsu", "Battohou", null, curriculumMap.get(5)),
                new Technique(null, "Kasumi kirigaeshi", "Tachiuchi", null, curriculumMap.get(5)),
                new Technique(null, "Juppondachi", "Tachiuchi", null, curriculumMap.get(5)),
                new Technique(null, "Ryusui", "Tachiuchi", null, curriculumMap.get(5)),
                new Technique(null, "Isonami", "Tachiuchi", null, curriculumMap.get(5)),
                new Technique(null, "Shizan go", "Tameshigiri", null, curriculumMap.get(5)),
                new Technique(null, "Toyama ryu gunto soho", "Gaiden", null, curriculumMap.get(5)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(5)),
                new Technique(" 基本四根", "Kihon Shikon", "Kyougi – Riron", "basic four fundamentals", curriculumMap.get(6)),
                new Technique(" 自他共栄", "Ji ta kyo ei", "Kyougi – Riron", "co-prosperity mutual welfare and benefit (Judo)", curriculumMap.get(6)),
                new Technique(" 精力善用", "Sei ryoku zenyo", "Kyougi – Riron", "maximum-efficient use of power (Judo)", curriculumMap.get(6)),
                new Technique(null, "Taisabaki san", "Taiso-Ashisabaki-Taisabaki", null, curriculumMap.get(6)),
                new Technique(null, "Shiho kesa kiriage", "Suburi", null, curriculumMap.get(6)),
                new Technique(null, "Ichimonji kesa kiriage", "Suburi", null, curriculumMap.get(6)),
                new Technique(null, "Ichimonji santen", "Suburi", null, curriculumMap.get(6)),
                new Technique(null, "Ichimonji goten", "Suburi", null, curriculumMap.get(6)),
                new Technique(null, "Chuden sei", "Tanrengata", null, curriculumMap.get(6)),
                new Technique(null, "Chuden do", "Tanrengata", null, curriculumMap.get(6)),
                new Technique(null, "Hangetsu gaeshi", "Battohou", null, curriculumMap.get(6)),
                new Technique(null, "Mangetsu gaeshi", "Battohou", null, curriculumMap.get(6)),
                new Technique(null, "Kasumi kirigaeshi", "Tachiuchi", null, curriculumMap.get(6)),
                new Technique(null, "Juppondachi", "Tachiuchi", null, curriculumMap.get(6)),
                new Technique(null, "Ryusui", "Tachiuchi", null, curriculumMap.get(6)),
                new Technique(null, "Isonami", "Tachiuchi", null, curriculumMap.get(6)),
                new Technique(null, "Shizan roku", "Tameshigiri", null, curriculumMap.get(6)),
                new Technique(null, "Gohou battohou santen giri mayoko", "Tameshigiri", null, curriculumMap.get(6)),
                new Technique(null, "Toyama ryu gunto soho", "Gaiden", null, curriculumMap.get(6)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(6)),
                new Technique(" 九曜十二訓", "Kuyojunikun", "Kyougi – Riron", null, curriculumMap.get(7)),
                new Technique(" 兵法", "Heihou", "Kyougi – Riron", "art of war / strategy", curriculumMap.get(7)),
                new Technique(" 軍法", "Gunpou", "Kyougi – Riron", "tactics", curriculumMap.get(7)),
                new Technique(" 剣禅一如", "Kenzenichi", "Kyougi – Riron", "meaning of the ken and Zen is one (in battle / sitting without thought)", curriculumMap.get(7)),
                new Technique(null, "Hishigata", "Ashisabaki", null, curriculumMap.get(7)),
                new Technique(null, "a. Tatebishi", "Ashisabaki", null, curriculumMap.get(7)),
                new Technique(null, "b. Yokobishi", "Ashisabaki", null, curriculumMap.get(7)),
                new Technique(null, "c. Sotobishi", "Ashisabaki", null, curriculumMap.get(7)),
                new Technique(null, "d. Tatebishi henka", "Ashisabaki", null, curriculumMap.get(7)),
                new Technique(null, "e. Sotai hishigata", "Ashisabaki", null, curriculumMap.get(7)),
                new Technique(null, "Oogi (happo kirigaeshi)", "Suburi", null, curriculumMap.get(7)),
                new Technique(null, "Oogi hyakusanjugo", "Suburi", null, curriculumMap.get(7)),
                new Technique(null, "Oogi gaeshi (juroppo kirigaeshi)", "Suburi", null, curriculumMap.get(7)),
                new Technique(null, "Oogi gaeshi hyakusanjugo", "Suburi", null, curriculumMap.get(7)),
                new Technique(null, "Kasumi gaeshi", "Suburi", null, curriculumMap.get(7)),
                new Technique(null, "Kasumi-gaeshi sotai", "Suburi", null, curriculumMap.get(7)),
                new Technique(null, "Jugo", "Tanrengata", null, curriculumMap.get(7)),
                new Technique(null, "Jugo Shiho", "Tanrengata", null, curriculumMap.get(7)),
                new Technique(null, "Goshiki", "Battohou", null, curriculumMap.get(7)),
                new Technique(null, "Goshiki santen giri", "Battohou", null, curriculumMap.get(7)),
                new Technique(null, "Goshiki santen", "Battohou", null, curriculumMap.get(7)),
                new Technique(null, "Santen ura", "Battohou", null, curriculumMap.get(7)),
                new Technique(null, "Kasumi kirigaeshi", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Juppondachi", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Ryusui", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Isonami", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Makiuchi advanced", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Ichimonji", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Tomoe ichi-ni", "Tachiuchi", null, curriculumMap.get(7)),
                new Technique(null, "Shizan Shichi", "Tameshigiri", null, curriculumMap.get(7)),
                new Technique(null, "Toyama ryu battojutsu", "Gaiden", null, curriculumMap.get(7)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(7)),
                new Technique(" 八道", "Hachido", "Kyougi – Riron", "eight fold path", curriculumMap.get(8)),
                new Technique(" 武産", "Takemusu", "Kyougi – Riron", "martial movement spontaneously created without active thought resulting in pure technique", curriculumMap.get(8)),
                new Technique(null, "Shido ho", "Shido ho", "Ability to teach classes and respond well to students questions", curriculumMap.get(8)),
                new Technique(null, "Ronbun", "Ronbun", "“What is Shinkendo?”", curriculumMap.get(8)),
                new Technique(null, "Shitsumon", "Shitsumon", "Open questions from examiners", curriculumMap.get(8)),
                new Technique(null, "Kensabaki", "Suburi", null, curriculumMap.get(8)),
                new Technique(null, "Tombo", "Suburi", null, curriculumMap.get(8)),
                new Technique(null, "Tombo gaeshi", "Suburi", null, curriculumMap.get(8)),
                new Technique(null, "Rokuten giri", "Suburi", null, curriculumMap.get(8)),
                new Technique(null, "Tanrengata", "Tanrengata", null, curriculumMap.get(8)),
                new Technique(null, "Battohou", "Battohou", null, curriculumMap.get(8)),
                new Technique(null, "Ichimonji", "Tachiuchi", null, curriculumMap.get(8)),
                new Technique(null, "Tomoe ichi-ni", "Tachiuchi", null, curriculumMap.get(8)),
                new Technique(null, "Tomoe henka", "Tachiuchi", null, curriculumMap.get(8)),
                new Technique(null, "Juppondachi jyuichi", "Tachiuchi", null, curriculumMap.get(8)),
                new Technique(null, "Ippondachi henka", "Tachiuchi", null, curriculumMap.get(8)),
                new Technique(null, "Shizan hachi", "Tameshigiri", null, curriculumMap.get(8)),
                new Technique(null, "Shizan kyu", "Tameshigiri", null, curriculumMap.get(8)),
                new Technique(null, "Toyama ryu battojutsu", "Gaiden", null, curriculumMap.get(8)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(8)),
                new Technique(" 五育", "Goiku", "Kyougi – Riron", "five basis points of education", curriculumMap.get(9)),
                new Technique(" 身心の鍛錬", "Shin-shin no Tanren", "Kyougi – Riron", "training body mind and spirit", curriculumMap.get(9)),
                new Technique(" 自力 他力 自然力 神通力", "Jiriki tariki shizen ryoku jintsuuriki", "Kyougi – Riron", "self power other power power of nature supernatural/divine power", curriculumMap.get(9)),
                new Technique(null, "Shido ho", "Shido ho", "Ability to lead class (plan curriculum run classes and respond well to students questions)", curriculumMap.get(9)),
                new Technique(null, "Ronbun", "Ronbun", "“What are the requirements for an instructor?”", curriculumMap.get(9)),
                new Technique(null, "Shitsumon", "Shitsumon", "Open questions from examiners", curriculumMap.get(9)),
                new Technique(null, "TEN", "Suburi", null, curriculumMap.get(9)),
                new Technique(null, "CHI", "Suburi", null, curriculumMap.get(9)),
                new Technique(null, "JIN", "Suburi", null, curriculumMap.get(9)),
                new Technique(null, "TEN", "Tanrengata", null, curriculumMap.get(9)),
                new Technique(null, "CHI", "Tanrengata", null, curriculumMap.get(9)),
                new Technique(null, "JIN", "Tanrengata", null, curriculumMap.get(9)),
                new Technique(null, "Goshiki santen ura", "Battohou", null, curriculumMap.get(9)),
                new Technique(null, "Gohou battohou gotengiri", "Battohou", null, curriculumMap.get(9)),
                new Technique(null, "Shiho", "Tachiuchi", null, curriculumMap.get(9)),
                new Technique(null, "Juppon omote", "Tachiuchi", null, curriculumMap.get(9)),
                new Technique(null, "Ichimonji santen", "Tachiuchi", null, curriculumMap.get(9)),
                new Technique(null, "Uchitsuki random", "Tachiuchi", null, curriculumMap.get(9)),
                new Technique(null, "Shizan jyu", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "Shizan sotai", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "Dotangiri", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "Karatakewari", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "lnazumagiri", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "Thrown target", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "Henka / Jyu-Waza", "Tameshigiri", null, curriculumMap.get(9)),
                new Technique(null, "Toyama ryu battodo-iaido", "Gaiden", null, curriculumMap.get(9)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(9)),
                new Technique(" 力愛不二", "Riki Ai Funi", "Kyougi – Riron", "strength and love stand together (Shorinji Kempo)", curriculumMap.get(10)),
                new Technique(" 陰陽", "In-you", "Kyougi – Riron", "cosmic dual forces yin and yang sun and moon (shade negative female secret dark VS. sunshine positive male light)", curriculumMap.get(10)),
                new Technique(" 気剣体一致", "Ki-ken-tai-ichi", "Kyougi – Riron", "spirit sword & body as one (all three elements as unite in perfect movement; the ideal which all practice should strive for as a goal)", curriculumMap.get(10)),
                new Technique(null, "Shido ho", "Shido ho", "Ability to lead own classes teach any technique plan curriculum and respond well to student’s questions and input", curriculumMap.get(10)),
                new Technique(null, "Ronbun", "Ronbun", "“The relationship between teacher and student in budo.”", curriculumMap.get(10)),
                new Technique(null, "Shitsumon", "Shitsumon", "Open questions from examiners", curriculumMap.get(10)),
                new Technique(null, "TEN", "Suburi", null, curriculumMap.get(10)),
                new Technique(null, "CHI", "Suburi", null, curriculumMap.get(10)),
                new Technique(null, "JIN", "Suburi", null, curriculumMap.get(10)),
                new Technique(null, "TEN", "Tanrengata", null, curriculumMap.get(10)),
                new Technique(null, "CHI", "Tanrengata", null, curriculumMap.get(10)),
                new Technique(null, "JIN", "Tanrengata", null, curriculumMap.get(10)),
                new Technique(null, "Jiyuu waza", "Tanrengata", null, curriculumMap.get(10)),
                new Technique(null, "TEN", "Battohou", null, curriculumMap.get(10)),
                new Technique(null, "CHI", "Battohou", null, curriculumMap.get(10)),
                new Technique(null, "JIN", "Battohou", null, curriculumMap.get(10)),
                new Technique(null, "Hiki ashi noto", "Battohou", null, curriculumMap.get(10)),
                new Technique(null, "Jiyuu waza", "Battohou", null, curriculumMap.get(10)),
                new Technique(null, "Shin Gyo So", "Tachiuchi", null, curriculumMap.get(10)),
                new Technique(null, "Chushinken", "Tachiuchi", null, curriculumMap.get(10)),
                new Technique(null, "Toyama ryu Kumitachi", "Tachiuchi", null, curriculumMap.get(10)),
                new Technique(null, "Gashiuchi", "Tachiuchi", null, curriculumMap.get(10)),
                new Technique(null, "Jiyuu waza", "Tachiuchi", null, curriculumMap.get(10)),
                new Technique(null, "lnazumagiri", "Tameshigiri", null, curriculumMap.get(10)),
                new Technique(null, "Katategiri", "Tameshigiri", null, curriculumMap.get(10)),
                new Technique(null, "Jiyuu waza", "Tameshigiri", null, curriculumMap.get(10)),
                new Technique(null, "Toyama ryu battodo-iaido", "Gaiden", null, curriculumMap.get(10)),
                new Technique(null, "Techniques selected by examiners", "Shite waza", null, curriculumMap.get(10))
        );

        for (Technique i : techniques) {
            try {
                this.techniqueDAO.save(i);
            } catch (Exception e) {
                logger.warn("Couldn't seed: " + e);
            }
        }
    }


}
