package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램
        BaseballGame baseballGame = new BaseballGame();
        BaseballUI baseballUI = new BaseballUI();
        baseballUI.printStart();
        //컴퓨터 랜덤 값 생성
        List<Integer> computer = baseballGame.computer();

        while(true) {
            baseballUI.printInput();
            //유저값 입력
            String playerStr = Console.readLine();
            //입력값 예외 체크
            baseballGame.inputChk(playerStr);
            baseballGame.playGame(playerStr, computer);
            //결과 출력
            boolean result = baseballGame.showResult();

            if (result) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                baseballUI.printReStart();
                String startBtn = Console.readLine();
                //컴퓨터 랜덤 값 재생성
                if(startBtn.equals("1")) computer = baseballGame.computer();
                //프로그램 종료
                else if (startBtn.equals("2")) {
                    return;
                } else  throw new IllegalArgumentException("1 또는 2만 입력 가능합니다.");
            }
        }
    }
}