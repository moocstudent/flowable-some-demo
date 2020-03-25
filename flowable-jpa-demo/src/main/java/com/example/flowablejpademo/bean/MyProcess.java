package com.example.flowablejpademo.bean;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@ToString(callSuper = true)
@Table(name="PROCESS")
public class MyProcess extends BaseEntity implements Serializable {

    private String processId;

    private String processName;

    private String processKey;

    public MyProcess() {
    }

    public MyProcess(String processId, String processName, String processKey) {
        this.processId = processId;
        this.processName = processName;
        this.processKey = processKey;
    }

    public MyProcess(Long id, Date createTime, Date updateTime, String processId, String processName, String processKey) {
        super(id, createTime, updateTime);
        this.processId = processId;
        this.processName = processName;
        this.processKey = processKey;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }
}
