package marcat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;


// 횡단 관심사항을 정의한 클래스
public class LoggingService {

    //Log4j를 사용하기 위한 선언부
    Logger log = LogManager.getLogger(LoggingService.class);
    String logStr = null;

    public void beforeLogging(JoinPoint point) {

        log.info("-------------------------------------");
        log.info("-------------------------------------");

        // 횡단 관심 사항을 적용할 대상 class의 이름
        log.info("0:" + point.getTarget().getClass().getName());

        // 전달되는 모든 파라미터들을 Object의 배열로 가져온다.
        log.info("1:" + Arrays.toString(point.getArgs()));

        // 해당 Advice의 타입을 알아낸다.
        log.info("2:" + point.getKind());

        // 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용
        log.info("3:" + point.getSignature().getName());

        // target 객체를 알아낼 때 사용
        log.info("4:" + point.getTarget().toString());

        // Advice를 행하는 객체를 알아낼 때 사용
        log.info("5:" + point.getThis().toString());
    }

    public Object doAround(ProceedingJoinPoint pjp) {
        long Time = System.currentTimeMillis();
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            logStr = "Method:" + pjp.getTarget().getClass() + "." + pjp.getSignature().getName() + "()";
            logStr = logStr + "The error message is as follows:[" + e + "]";
            log.info(logStr);
        }
        return result;
    }

    public void doAfter(JoinPoint jp, Object obj) {
        log.info("afterAllMethod: " + jp.getSignature());
        log.info("return: " + obj);
    }

    //target 메소드의 동작 시간을 로그한다.
//    @Around("execution(public * marcat..*Service.*(..))")
//    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        logger.info(Arrays.toString(pjp.getArgs()));
//
//        //실제 타겟을 실행하는 부분이다. 이 부분이 없으면 advice가 적용된 메소드가 동작을 안할것 같다.
//        Object result = pjp.proceed();  //proceed는 Exception 보다 상위 Throwable을 처리해야 한다.
//
//        long endTime = System.currentTimeMillis();
//        logger.info(pjp.getSignature().getName() + " : " + ( endTime - startTime));  //target 메소드의 동작 시간을 출력한다.
//        logger.info("==============================");
//
//        //Around를 사용할 경우 반드시 Object를 리턴해야 한다.
//        return result;
//    }
}
