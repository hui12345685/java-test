package org.example.aspect;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RdLockAspect {


    @Resource
    private RedissonClient redissonClient;


    @Pointcut("@annotation(org.example.aspect.RdLock)")
    public void rdLockPointcut() {
    }


    @Around("rdLockPointcut() && @annotation(rdLock)")
    public Object rdLockPointcut(ProceedingJoinPoint joinPoint, RdLock rdLock) {
        String classMethodName = getClassMethodName(joinPoint);
        String lockKey = rdLock.key();
        if (lockKey == null || lockKey.isEmpty()) {
            lockKey = classMethodName; // 这里也可以对classMethodName做个编码
        }

        RLock rLock = redissonClient.getLock(lockKey);
        // 执行业务逻辑
        Object obj = null;
        try {
            boolean lock = rLock.tryLock(rdLock.keyExpireTime(), TimeUnit.SECONDS);
            if (!lock) {
                log.info("[{}] get lock failed", classMethodName);
                return null;
            }

            log.info("[{}] Task start, current time:{}", classMethodName, System.currentTimeMillis());
            obj = joinPoint.proceed(); // 这一行才是执行的具体的业务逻辑
            log.info("[{}] Task end, current time:{}", classMethodName, System.currentTimeMillis());
        } catch (Throwable e) {
            log.error("[{}] Exception occurs.", classMethodName, e);
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
        return obj;
    }

    private String getClassMethodName(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return String.format("%s#%s", className, methodName);
    }
}
