package com.example.flowabledemo;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println(execution.getVariable("employee")+"的请假被驳回了~安心上班吧");
    }
}
