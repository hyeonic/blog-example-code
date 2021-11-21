package me.hyeonic.object.chapter02.discountcondition;

import me.hyeonic.object.chapter02.Screening;

// 할인 조건
public interface DiscountCondition {
    // 할인이 가능한 경우 true 반환
    boolean isSatisfiedBy(Screening screening);
}