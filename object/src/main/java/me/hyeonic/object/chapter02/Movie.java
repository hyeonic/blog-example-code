package me.hyeonic.object.chapter02;

import me.hyeonic.object.chapter02.discountpolicy.DiscountPolicy;

import java.time.Duration;

public class Movie {
    private String name;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String name, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.name = name;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

    // 실행 시점에 할인 정책을 간단하게 변경 가능
    public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}