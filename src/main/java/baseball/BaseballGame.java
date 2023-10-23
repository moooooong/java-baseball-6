package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballGame {
    //strike count
    private int strikeCount;
    //ball count
    private int ballCount;
    private List<Integer> computerNum;
    //1에서 9 사이의 서로 다른 랜덤한 3자리 숫자를 생성

    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 9;
    private static final int NUMBER_LENGTH = 3;

    public List<Integer> computer(){
        List<Integer> computer = new ArrayList<>();
        int randomNum = Randoms.pickNumberInRange(MIN_RANGE, MAX_RANGE);
        computer.add(randomNum);
        while (computer.size() < NUMBER_LENGTH) {
            randomNum = Randoms.pickNumberInRange(MIN_RANGE, MAX_RANGE);
            if(!computer.contains(randomNum)) computer.add(randomNum);
        }
        this.computerNum = computer;
        System.out.println("BaseballGame computerNum = " + computerNum);
        return computerNum;
    }
    //사용자 입력 문자열을 숫자 목록으로 변환
    public static List<Integer> playerNum(String playerStr){
        List<Integer> playerNum = new ArrayList<>();
        for(int a = 0; a<playerStr.length(); a++){
            char playerChar = playerStr.charAt(a);
            int playerInt = Character.getNumericValue(playerChar);
            playerNum.add(playerInt);
        }
        return playerNum;
    }
    //사용자 입력 값의 예외 체크
    public void inputChk(String playerStr){
        if(playerStr == null || playerStr.isEmpty()){
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        } else if(playerStr.length() != NUMBER_LENGTH){
            throw new IllegalArgumentException("3자리 숫자만 입력 가능합니다. 입력한 숫자: " + playerStr);
        } else if(!isNumeric(playerStr)){
            throw  new IllegalArgumentException("숫자만 입력 가능합니다. 입력한 숫자: " + playerStr);
        } else if(!containsRepeats(playerStr)){
            throw new IllegalArgumentException("중복된 숫자는 입력이 불가능합니다. 입력한 숫자: " + playerStr);
        } else if(playerStr.contains("0")){
            throw new IllegalArgumentException("숫자는 1~9까지 입력 가능합니다. 입력한 숫자: " + playerStr);
        }
    }
    //숫자 확인
    private boolean isNumeric(String playerStr){
        return playerStr.matches("\\d+");
    }
    //중복 숫자 체크
    public boolean containsRepeats(String playerStr){
        boolean result = false;
        List<Integer> playerNumList = BaseballGame.playerNum(playerStr);
        Set<Integer> playerNumSet = new HashSet<>(playerNumList);

        if(playerNumList.size() == playerNumSet.size()) result = true;

        return result;
    }
    //스트라이크, 볼 계산
    public void playGame(String playerStr, List<Integer> computer){
        this.computerNum = computer;
        int strikeCount = 0;
        int ballCount = 0;
        List<Integer> playerNum = BaseballGame.playerNum(playerStr);
        for(int i = 0; i<this.computerNum.size(); i++)
            for (int j = 0; j < playerNum.size(); j++) {
                if (this.computerNum.get(i) == playerNum.get(j)) {
                    if (i == j) strikeCount++;
                    else ballCount++;
                }
            }
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }
    //스트라이크와 볼 수에 따라 결과를 표시
    public boolean showResult(){
        boolean result = false;
        if(threeStrikes()){
            System.out.println("3스트라이크");
            result = true;
        } else if(nothing())
            System.out.println("낫싱");
         else if(ballAndStrike())
            System.out.println(ballCount + "볼 " + strikeCount + "스트라이크");
         else if(ballOnly())
            System.out.println(ballCount + "볼");
         else if(strikeOnly())
            System.out.println(strikeCount + "스트라이크");
        return result;
    }
    private boolean threeStrikes(){
        return strikeCount == 3;
    }
    private boolean nothing() {
        return (strikeCount == 0 && ballCount == 0);
    }
    private boolean ballAndStrike(){
        return (strikeCount != 0 && ballCount != 0);
    }
    private boolean ballOnly(){
        return (strikeCount == 0 && ballCount != 0);
    }
    private boolean strikeOnly(){
        return (strikeCount != 0 && ballCount == 0);
    }

}
