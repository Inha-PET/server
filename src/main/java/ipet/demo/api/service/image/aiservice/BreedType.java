package ipet.demo.api.service.image.aiservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BreedType {
    CHIHUAHUA(0, "치와와", "chihuahua"),
    JAPANESE_SPANIEL(1, "일본 스파니얼", "japanese_spaniel"),
    MALTESE_DOG(2, "말티즈", "maltese_dog"),
    PEKINESE(3, "페키니즈", "pekinese"),
    SHIH_TZU(4, "시츄", "shih"),
    BLENHEIM_SPANIEL(5, "블렌하임 스파니얼", "blenheim_spaniel"),
    PAPILLON(6, "파피용", "papillon"),
    TOY_TERRIER(7, "토이 테리어", "toy_terrier"),
    RHODESIAN_RIDGEBACK(8, "로디지안 릿지백", "rhodesian_ridgeback"),
    AFGHAN_HOUND(9, "아프간 하운드", "afghan_hound"),
    BASSET(10, "바셋", "basset"),
    BEAGLE(11, "비글", "beagle"),
    BLOODHOUND(12, "블러드하운드", "bloodhound"),
    BLUETICK(13, "블루틱", "bluetick"),
    BLACK_AND_TAN_COONHOUND(14, "검정과 황갈색 쿤하운드", "black-and-tan_coonhound"),
    WALKER_HOUND(15, "워커 하운드", "walker_hound"),
    ENGLISH_FOXHOUND(16, "잉글리시 폭스하운드", "english_foxhound"),
    REDBONE(17, "레드본", "redbone"),
    BORZOI(18, "보르조이", "borzoi"),
    IRISH_WOLFHOUND(19, "아이리시 울프하운드", "irish_wolfhound"),
    ITALIAN_GREYHOUND(20, "이탈리안 그레이하운드", "italian_greyhound"),
    WHIPPET(21, "휘핏", "whippet"),
    IBIZAN_HOUND(22, "이비잔 하운드", "ibizan_hound"),
    NORWEGIAN_ELKHOUND(23, "노르웨이언 엘크하운드", "norwegian_elkhound"),
    OTTERHOUND(24, "오터하운드", "otterhound"),
    SALUKI(25, "살루키", "saluki"),
    SCOTTISH_DEERHOUND(26, "스코티시 디어하운드", "scottish_deerhound"),
    WEIMARANER(27, "와이머라너", "weimaraner"),
    STAFFORDSHIRE_BULLTERRIER(28, "스태퍼드셔 불테리어", "staffordshire_bullterrier"),
    AMERICAN_STAFFORDSHIRE_TERRIER(29, "아메리칸 스태퍼드셔 테리어", "american_staffordshire_terrier"),
    BEDLINGTON_TERRIER(30, "베들링턴 테리어", "bedlington_terrier"),
    BORDER_TERRIER(31, "보더 테리어", "border_terrier"),
    KERRY_BLUE_TERRIER(32, "케리 블루 테리어", "kerry_blue_terrier"),
    IRISH_TERRIER(33, "아이리시 테리어", "irish_terrier"),
    NORFOLK_TERRIER(34, "노리치 테리어", "norfolk_terrier"),
    NORWICH_TERRIER(35, "노리치 테리어", "norwich_terrier"),
    YORKSHIRE_TERRIER(36, "요크셔 테리어", "yorkshire_terrier"),
    WIRE_HAIRED_FOX_TERRIER(37, "와이어헤어 폭스 테리어", "wire"),
    LAKELAND_TERRIER(38, "레이클랜드 테리어", "lakeland_terrier"),
    SEALYHAM_TERRIER(39, "실리엄 테리어", "sealyham_terrier"),
    AIREDALE(40, "에어데일", "airedale"),
    CAIRN(41, "케언", "cairn"),
    AUSTRALIAN_TERRIER(42, "오스트레일리안 테리어", "australian_terrier"),
    DANDIE_DINMONT(43, "단디 딘몬트 테리어", "dandie_dinmont"),
    BOSTON_BULL(44, "보스턴 불", "boston_bull"),
    MINIATURE_SCHNAUZER(45, "미니어처 슈나우저", "miniature_schnauzer"),
    GIANT_SCHNAUZER(46, "자이언트 슈나우저", "giant_schnauzer"),
    STANDARD_SCHNAUZER(47, "스탠다드 슈나우저", "standard_schnauzer"),
    SCOTCH_TERRIER(48, "스코티시 테리어", "scotch_terrier"),
    TIBETAN_TERRIER(49, "티베탄 테리어", "tibetan_terrier"),
    SILKY_TERRIER(50, "실키 테리어", "silky_terrier"),
    SOFT_COATED_WHEATEN_TERRIER(51, "소프트 코티드 휘튼 테리어", "soft"),
    WEST_HIGHLAND_WHITE_TERRIER(52, "웨스트 하이랜드 화이트 테리어", "west_highland_white_terrier"),
    LHASA(53, "라사", "lhasa"),
    FLAT_COATED_RETRIEVER(54, "플랫코티드 리트리버", "flat"),
    CURLY_COATED_RETRIEVER(55, "컬리코티드 리트리버", "curly"),
    GOLDEN_RETRIEVER(56, "골든 리트리버", "golden_retriever"),
    LABRADOR_RETRIEVER(57, "래브라도 리트리버", "labrador_retriever"),
    CHESAPEAKE_BAY_RETRIEVER(58, "체서피크 베이 리트리버", "chesapeake_bay_retriever"),
    GERMAN_SHORT_HAIRED_POINTER(59, "저먼 쇼트헤어 포인터", "german_short"),
    VIZSLA(60, "비즐라", "vizsla"),
    ENGLISH_SETTER(61, "잉글리시 세터", "english_setter"),
    IRISH_SETTER(62, "아이리시 세터", "irish_setter"),
    GORDON_SETTER(63, "고든 세터", "gordon_setter"),
    BRITTANY_SPANIEL(64, "브리트니 스파니얼", "brittany_spaniel"),
    CLUMBER(65, "클럼버", "clumber"),
    ENGLISH_SPRINGER(66, "잉글리시 스프링어 스파니얼", "english_springer"),
    WELSH_SPRINGER_SPANIEL(67, "웰시 스프링어 스파니얼", "welsh_springer_spaniel"),
    COCKER_SPANIEL(68, "코커 스파니얼", "cocker_spaniel"),
    SUSSEX_SPANIEL(69, "서새스 스파니얼", "sussex_spaniel"),
    IRISH_WATER_SPANIEL(70, "아이리시 워터 스파니얼", "irish_water_spaniel"),
    KUVASZ(71, "쿠바스", "kuvasz"),
    SCHIPPERKE(72, "스키퍼키", "schipperke"),
    GROENENDAEL(73, "그루넨달", "groenendael"),
    MALINOIS(74, "말리노아", "malinois"),
    BRIARD(75, "브리아드", "briard"),
    KELPIE(76, "켈피", "kelpie"),
    KOMONDOR(77, "코몬도르", "komondor"),
    OLD_ENGLISH_SHEEPDOG(78, "올드 잉글리시 쉽독", "old_english_sheepdog"),
    SHETLAND_SHEEPDOG(79, "셰틀랜드 쉽독", "shetland_sheepdog"),
    COLLIE(80, "콜리", "collie"),
    BORDER_COLLIE(81, "보더 콜리", "border_collie"),
    BOUVIER_DES_FLANDRES(82, "부비에 데 플랑드르", "bouvier_des_flandres"),
    ROTTWEILER(83, "롯트와일러", "rottweiler"),
    GERMAN_SHEPHERD(84, "저먼 셰퍼드", "german_shepherd"),
    DOBERMAN(85, "도베르만 핀셔", "doberman"),
    MINIATURE_PINSCHER(86, "미니어처 핀셔", "miniature_pinscher"),
    GREATER_SWISS_MOUNTAIN_DOG(87, "그레이터 스위스 마운틴 독", "greater_swiss_mountain_dog"),
    BERNESE_MOUNTAIN_DOG(88, "버니즈 마운틴 독", "bernese_mountain_dog"),
    APPENZELLER(89, "아펜젤러", "appenzeller"),
    ENTLEBUCHER(90, "엔틀부처", "entlebucher"),
    BOXER(91, "복서", "boxer"),
    BULL_MASTIFF(92, "불 마스티프", "bull_mastiff"),
    TIBETAN_MASTIFF(93, "티베탄 마스티프", "tibetan_mastiff"),
    FRENCH_BULLDOG(94, "프렌치 불독", "french_bulldog"),
    GREAT_DANE(95, "그레이트 데인", "great_dane"),
    SAINT_BERNARD(96, "세인트 버나드", "saint_bernard"),
    ESKIMO_DOG(97, "에스키모 독", "eskimo_dog"),
    MALAMUTE(98, "말라뮤트", "malamute"),
    SIBERIAN_HUSKY(99, "시베리안 허스키", "siberian_husky"),
    AFFENPINCHER(100, "아펜핀셔", "affenpinscher"),
    BASENJI(101, "바센지", "basenji"),
    PUG(102, "퍼그", "pug"),
    LEONBERG(103, "레온베르거", "leonberg"),
    NEWFOUNDLAND(104, "뉴펀들랜드", "newfoundland"),
    GREAT_PYRENEES(105, "그레이트 피레니즈", "great_pyrenees"),
    SAMOYED(106, "사모예드", "samoyed"),
    POMERANIAN(107, "포메라니안", "pomeranian"),
    CHOW(108, "차우", "chow"),
    KEESHOND(109, "키스혼드", "keeshond"),
    BRABANCON_GRIFFON(110, "브라방콩 그리폰", "brabancon_griffon"),
    PEMBROKE(111, "펨브록 웰시 코기", "pembroke"),
    CARDIGAN(112, "카디건 웰시 코기", "cardigan"),
    TOY_POODLE(113, "토이 푸들", "toy_poodle"),
    MINIATURE_POODLE(114, "미니어처 푸들", "miniature_poodle"),
    STANDARD_POODLE(115, "스탠다드 푸들", "standard_poodle"),
    MEXICAN_HAIRLESS(116, "멕시칸 헤어리스", "mexican_hairless"),
    DINGO(117, "들개", "dingo"),
    DHOLE(118, "돌", "dhole"),
    AFRICAN_HUNTING_DOG(119, "아프리카 사냥개", "african_hunting_dog");

    private final int id;
    private final String koreanName;
    private final String englishName;

    public static BreedType getBreedTypeByEnglishName(String breedName) {
        for (BreedType breedType : values()) {
            if (breedType.getEnglishName().equals(breedName)) {
                return breedType;
            }
        }
        return null;
    }

    public static BreedType getBreedTypeByKoreanName(String koreanName) {
        for (BreedType breedType : values()) {
            if (breedType.getKoreanName().equals(koreanName)) {
                return breedType;
            }
        }
        return null;
    }

}