import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class ChampionTest {
    private List<Champion> championList = new ArrayList<Champion>(); //ctrl누르고 객체이름 누르면 선언된 부분? 으로 갈 수 있다

    @Before //테스트 하기전에 한번 실행하는거 setup같은느낌
    public void setUp() {

        //5개의 챔피언 객체를 만듭니다.
        Champion topChamp = new Champion("다리우스", "탑");
        Champion jungleChamp = new Champion("리신", "정글");
        Champion midChamp = new Champion("르블랑", "미드");
        Champion adcChamp = new Champion("베인", "바텀");
        Champion supportChamp = new Champion("레오나", "바텀");

        //앞서 만든 List 에 각 챔피언을 추가합니다.
        championList.add(topChamp);
        championList.add(jungleChamp);
        championList.add(midChamp);
        championList.add(adcChamp);
        championList.add(supportChamp);
    }

    //List<String>을 생성하고 값이 비어 있는지를 테스트 empty()
    @Test
    public void givenCollectionWhenEmptyCorrect() {
        List<String> emptyList = new ArrayList<>();
        assertThat(emptyList,empty()); // assert that import 필수
//        assertThat(emptyList, empty());
    }

    //notNullValue 활용한 테스트
    @Test
    public void notNullCheck() {
        String lck = "LCK";
        assertThat(lck,notNullValue());
//        assertThat(lck, notNullValue());
    }

    //nullValue 활용한 테스트
    @Test
    public void givenStringWhenNullIsCorrect() {
        String lck = null;
        assertThat(lck,nullValue());
//        assertThat(lck, nullValue());
    }


    //문자열 관련 테스트 anyOf, containsString, endWith
    @Test
    public void testForRelatedString() {
        String sampleString1 = "Player Focus";
        String sampleString2 = "Player point";
        String startString = "Player";
        String endString = "point";

        assertThat(sampleString1,is(startsWith(startString)));//startwith는 ()안에 문자열로 시작하는지 test하는 작업
        assertThat(sampleString2,is(endsWith(endString)));
        assertThat(sampleString1,anyOf(startsWith(startString),containsString(endString))); //anyof안에 두 조건 중 하나라도 성립이 되면 성공
//        //allof는 안에 있는 조건이 모두 성립이 되어야 test 성공
        assertThat(sampleString2,allOf(startsWith(startString), endsWith(endString)));
//        assertThat(sampleString1, anyOf(startsWith(startString), containsString(endString)));
//        assertThat(sampleString2, is(endsWith(endString)));
    }

    //부동소수점 범위 closeTo 테스트
    @Test
    public void testForFloatingPoint() {
//        assertThat(3.14, closeTo(3, 0.2));
        assertThat(3.14,closeTo(3,0.1)); // actual로 값을 주고 close to에서 operand가 오차범위가 0.2 안에 있다면 성공
        //error가 0.2라면 성공 0.1이면 실패
    }

    //anything 테스트
    @Test
    public void shouldNotErrorGetReference() {
//        assertThat(championList.get(2), anything());
        assertThat(championList.get(2),anything());//anything ?? anything이면 값이 어떻든 값만 가져오면 성공임 만약 값을 가져오지 않는다면 실패
        assertThat(championList.get(2),is(championList.get(2))); //같은지 확인하는 작업

    }

    //객체 크기 검증 테스트 hasSize
    @Test
    public void shouldChampionCountFive() {
        assertTrue(championList.size() == 5);
        assertThat(championList.size(), is(5));
        assertThat(championList,hasSize(5)); //size를 안써줄까?? hassize 자체가 배열의 크기를 계산하기 때문에 굳이 배열의 크기를 구하지않아도됌
//        assertTrue(championList.size() == 5);
//        assertThat(championList.size(), is(5));
//        assertThat(championList, hasSize(5));
    }

    //서폿 챔피언은 타릭이어야 한다라는 조건으로 테스트 코드 작성
    @Test
    public void shouldSupportChampionIsTaric() {
        Champion supportChamp = new Champion("타릭", "바텀");
        assertThat("타릭",is(supportChamp.getName()));
        assertThat("타릭",is(equalTo(supportChamp.getName())));
//        assertThat("타릭", is(supportChamp.getName()));
//        assertThat("타릭", is(equalTo(supportChamp.getName())));
//        assertThat("타릭", equalTo(supportChamp.getName()));
    }

    //hasProperty 활용하여 속성이 포함되어 있는지 테스트
    @Test
    public void shouldHasPropertyPosition() {
        //hasproperty 는 속성이 있는지 확인해주는 역할
        assertThat(championList.get(0),hasProperty("position")); //ctrl + shift + backspaceㅎ하면 이전 작업으로 갔다
        assertThat(championList.get(0),hasProperty("name"));
//        assertThat(championList.get(0), hasProperty("position"));
        assertThat(championList.get(0), hasProperty("position", equalTo("탑")));
    }

    //hasToString 활용 테스트 // 해당 문자열을 가지고 있는지 테스트
    @Test
    public void shouldHaveSomeChampName() {
        //List<String> champListNames = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가느", "블라디미르");
        List<String> iLikeChampNames = Arrays.asList("조이","티모","아이번","징크스","카밀");
        assertThat(iLikeChampNames.get(0),hasToString("조이" )); // 포함되어있는지! 문자열이 같을 때는 equal to를 이용
//        assertThat(champListNames.get(0), hasToString("루시안"));
    }

    //property와 value가 같은지 테스트
    @Test
    public void shouldHaveSamePropertyAndValue() {
        List<String> championNames1 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        List<String> championNames2 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        //assertThat(championNames1,samePropertyValuesAs(championNames2)); //property에 대한 value를 같은지 확인하는거라 아예 다른게 아니면 웬만하면 성공하는것 같다
        assertThat(championNames1,samePropertyValuesAs(championList));//실패 ! 두 객체가 아예 다르기 때문에 championlist는 champion 객체에 대해서
        //championNames는 string 배열
//        assertThat(championNames1, samePropertyValuesAs(championNames2));
    }

    //탑 챔피언은 다리우스여야 한다라는 조건으로 테스트 코드 작성, stream 활용예
    @Test
    public void shouldTopChampionIsDarius() {
        Optional<Champion> filterChampion = championList.stream() //null 감지를 위해서 optional 을 사용
                .filter(c->c.getPosition().equals("정글")) // c는 배열 중 하나를 가져오는 것
                .findFirst();
        System.out.println("result : " + filterChampion );
        String champName = filterChampion.get().getName();
        assertThat(champName,is("리신"));
        //stream 연속된 데이터(배열이든 리스트든)에 대해서 filter 명령어를 통해 포지션이 정글인 데이터에 대해서만 거르고, 그리고 findfirst을 통해
        //찾은 것 중 제일 첫번째로 찾은거
        //반복문 보다는 stream을 사용해서 진행하는 것이 편하다. 반복문 돌리는 것보다는
//        Optional<Champion> filterdChampion = championList.stream()
//                .filter(c -> c.getPosition().equals("탑"))
//                .findFirst();
//        String champName = filterdChampion.get().getName();
//        assertTrue(champName.equals("다리우스"));
//        assertThat("다리우스", is(champName));
    }

}